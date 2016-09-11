package co.miraclelab.wikikit.controller;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.miraclelab.webframe.controller.MainControl;
import co.miraclelab.webframe.layoutservice.LayoutService;
import co.miraclelab.webframe.utilities.AppProperties;
import co.miraclelab.webframe.utilities.EmailService;
import co.miraclelab.webframe.utilities.EncryptService;
import co.miraclelab.webframe.utilities.LogService;
import co.miraclelab.webframe.utilities.ServiceAccessor;
import co.miraclelab.webframe.utilities.XMLService;

@Controller
public class Control extends MainControl{
	public Control(AppProperties properties, ServletContext servletContext, LogService logService,
			EncryptService encryptService, XMLService xmlService, EmailService mailService, MongoTemplate mongoTemplate,
			LayoutService layoutService, VelocityEngine templateEngine, HttpServletRequest request,
			HttpServletResponse response) {
		super(properties, servletContext, logService, encryptService, xmlService, mailService, mongoTemplate, layoutService,
				templateEngine, request, response);
		mailService.initMailService();
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET) 
	public String redirectToMain() throws IOException {
		return "redirect:/main";
	}
	
	@RequestMapping(value = { "/main" }, method = RequestMethod.GET)
	public String welcomePage(Model model) throws IOException {
		ServiceAccessor.modelDispatch(model);
		model.addAttribute("pageTitle","Miracle Lab wiki Main"); 
		return "main";
	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage(Model model) throws IOException {
		ServiceAccessor.modelDispatch(model);
		model.addAttribute("pageTitle","Login to"); 
		return "login";
	}
		
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	@ResponseBody
	public String loginPageResponse(){
		return request.getParameter("email")+" "+request.getParameter("password");
	}

}
