package ro.dialogdata.jug.web.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.dialogdata.jug.backend.repos.MessageRepository;
import ro.dialogdata.jug.backend.repos.UserRepository;
import ro.dialogdata.jug.common.model.Message;
import ro.dialogdata.jug.web.auth.AuthenticationUtils;

@Controller
@RequestMapping("/")
public class MessageController {

	private final static int PAGE_SIZE = 7;
	
	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	@ModelAttribute("message")
	public Message getMessage() {
		return new Message();
	}

	private void initModel(Model model,String page) {
		int pageInt = 0;
		Pageable pageRequest = new PageRequest(0,PAGE_SIZE);
		if(page!=null){
			try{
			pageInt = Integer.parseInt(page);
			pageRequest = new PageRequest(pageInt,PAGE_SIZE);
			}catch(Exception e){
				//don't care
			}
		}
		List<Message> messages = messageRepository.findMostRecent(pageRequest);
		model.addAttribute("messages", messages);
		model.addAttribute("page",pageInt);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showHomeView(@RequestParam(value = "page", required = false) String page,Model model) {
		initModel(model,page);
		return "home";
	}
	
	@RequestMapping(value = "postMessage",method = RequestMethod.GET)
	public String showView(@RequestParam(value = "page", required = false) String page,Model model) {
		initModel(model,page);
		return "home";
	}

	@RequestMapping(value = "postMessage", method = RequestMethod.POST)
	public String register(@RequestParam(value = "page", required = false) String page,Message message, Model model) {
		if (!(message.getValue().trim().length() == 0)) {
			message.setDate(new Date());
			message.setUser(userRepository
					.findByUsernameIgnoreCase(AuthenticationUtils
							.getCurrentUsername()));
			messageRepository.save(message);
		}
		initModel(model,page);
		return "home";
	}
}
