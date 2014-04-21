package ro.dialogdata.jug.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.dialogdata.jug.web.auth.AuthenticationUtils;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	@RequestMapping(method = RequestMethod.GET)
	public String logout(Model model) {
		AuthenticationUtils.logout();
		return "redirect:/";
	}
}
