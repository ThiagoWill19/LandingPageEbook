package com.thiagowill.landingpage.resources;



import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.thiagowill.landingpage.models.UserContact;
import com.thiagowill.landingpage.services.UserContactService;

@Controller
public class UserContactController {
		
	@Autowired
	private UserContactService service;
	@Autowired private JavaMailSender mailSender;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage(UserContact user) {
		ModelAndView mv = new ModelAndView("Index");
		
		return mv;
	}
	
	@RequestMapping(value = "/home",method = RequestMethod.POST)
	public String getContact( @Valid UserContact user,BindingResult result,
			RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("message","Preencha todos os campos!");
			return "redirect:/home";
		}
		if(!service.autenticationEmail(user)) {
			attributes.addFlashAttribute("message","E-mail ja cadastrado!");
			return "redirect:/home";
		}
		 SimpleMailMessage message = new SimpleMailMessage();
	        message.setText("Aqui está seu Ebook");
	        message.setTo(user.getEmail());
	        message.setFrom("thiagopompeu19@gmail.com");
	        try {
	            mailSender.send(message);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "redirect:/home";
	        }
		    service.save(user);
		return "Agradecimento";
	}
}
