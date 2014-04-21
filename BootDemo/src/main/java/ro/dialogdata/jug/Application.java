package ro.dialogdata.jug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ro.dialogdata.jug.backend.services.UserService;

@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableAsync
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).properties(
				"security.basic.enabled=true").run(args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

	@Bean
	public ApplicationSecurity applicationSecurity() {
		return new ApplicationSecurity();
	}
	
	@Order(Ordered.LOWEST_PRECEDENCE - 8)
	protected static class ApplicationSecurity extends
			WebSecurityConfigurerAdapter {
		
		@Autowired
	    private UserService userService;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/", "/fonts/**","/less/**","/register"
							,"/logout","/img/**","/js/**","/css/**","/resources/**").permitAll()
					.anyRequest()
					.authenticated()
					.and().formLogin().loginPage("/login")
					.permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setUserDetailsService(userService);
			auth.authenticationProvider(provider); 
		}
	}
}
