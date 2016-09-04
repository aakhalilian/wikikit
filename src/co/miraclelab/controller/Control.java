package co.miraclelab.controller;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.miraclelab.webframe.controller.MainControl;
import co.miraclelab.webframe.layoutservice.LayoutService;
import co.miraclelab.webframe.utilities.AppProperties;
import co.miraclelab.webframe.utilities.EmailService;
import co.miraclelab.webframe.utilities.EncryptService;
import co.miraclelab.webframe.utilities.LogService;
import co.miraclelab.webframe.utilities.MongoService;
import co.miraclelab.webframe.utilities.ServiceAccessor;
import co.miraclelab.webframe.utilities.XMLService;

public class Control extends MainControl{

	public Control(AppProperties properties, ServletContext servletContext, LogService logService,
			EncryptService encryptService, XMLService xmlService, EmailService mailService, MongoService mongoService,
			LayoutService layoutService, HttpServletRequest request, HttpServletResponse response) {
		super(properties, servletContext, logService, encryptService, xmlService, mailService, mongoService, layoutService,
				request, response);
		mailService.initMailService();
	}
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET) 
	public String redirectToMain() throws IOException {
		return "redirect:/main";
	}
	
	@RequestMapping(value = { "/main" }, method = RequestMethod.GET)
	public String welcomePage(Model model) throws IOException {
		ServiceAccessor.modelDispatch(model);
		model.addAttribute("pageTitle","Miracle Lab Main"); 
		return "main";
	}

}
