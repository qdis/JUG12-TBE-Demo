package ro.dialogdata.jug.web.controllers;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ro.dialogdata.jug.backend.repos.UserRepository;
import ro.dialogdata.jug.backend.services.UserService;
import ro.dialogdata.jug.common.enums.Role;
import ro.dialogdata.jug.common.model.User;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showRegisterView(Model model) {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(User user,ServletRequest servletRequest) {
		user.setRole(Role.USER);
		user.setUsername(StringEscapeUtils.escapeHtml(user.getUsername()));
		userRepository.save(user);
		authenticateForce(user, ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest());
		return "redirect:/";
	}

	private void authenticateForce(User user, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword());
		token.setDetails(new WebAuthenticationDetails(request));
		DaoAuthenticationProvider authenticator = new DaoAuthenticationProvider();
		authenticator.setUserDetailsService(userService);
		Authentication authentication = authenticator.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
