package ro.dialogdata.jug;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ro.dialogdata.jug.backend.services.UserService;

@ComponentScan
@Configuration
@EnableAutoConfiguration
public class Application extends WebMvcConfigurerAdapter {

	/**
	 * Starts application with Embedded Tomcat
	 */
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).properties(
				"security.basic.enabled=true").run(args);
	}

	/**
	 * Required for Spring Security
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

	/**
	 * Spring Security Configuration
	 */
	@Bean
	public ApplicationSecurity applicationSecurity() {
		return new ApplicationSecurity();
	}

	/**
	 * Simple Encoding Filter Example ( Alternative to web.xml )
	 * Default url pattern is : /*
	 */
	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	/**
	 * Character Encoding Filter Example with Filter Registration ( specific URL Patterns )
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(characterEncodingFilter());
		ArrayList<String> patterns = new ArrayList<>();
		patterns.add("/register");
		patterns.add("/postMessage");
		patterns.add("/login");
		registrationBean.setUrlPatterns(patterns);
		return registrationBean;
	}

	@Order(Ordered.LOWEST_PRECEDENCE - 8)
	protected static class ApplicationSecurity extends
			WebSecurityConfigurerAdapter {

		/**
		 * Custom userService ( Database Authentication )
		 */
		@Autowired
		private UserService userService;

		/**
		 * Request Filtering Configuration ( allowed and denied URL's )
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/", "/fonts/**", "/less/**", "/register",
							"/logout", "/img/**", "/js/**", "/css/**",
							"/resources/**").permitAll().anyRequest()
					.authenticated().and().formLogin().loginPage("/login")
					.permitAll().and().logout().logoutUrl("/logout")
					.logoutSuccessUrl("/").permitAll();
		}

		/**
		 * AuthenticationManager configuration -> this tells the authManager to use the custom userService
		 */
		@Override
		protected void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setUserDetailsService(userService);
			auth.authenticationProvider(provider);
		}
	}
}
