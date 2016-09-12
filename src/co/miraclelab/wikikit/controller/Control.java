package co.miraclelab.wikikit.controller;
import java.io.IOException;
import java.security.GeneralSecurityException;

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
import co.miraclelab.wikikit.model.Article;
import co.miraclelab.wikikit.model.User;
import co.miraclelab.wikikit.services.ArticleService;
import co.miraclelab.wikikit.services.ComponentAccessor;
import co.miraclelab.wikikit.services.UserService;

@Controller
public class Control extends MainControl{
	private final UserService userService;
	private final ArticleService articleService;
	
	public Control(AppProperties properties, ServletContext servletContext, LogService logService,
			EncryptService encryptService, XMLService xmlService, EmailService mailService, MongoTemplate mongoTemplate,
			LayoutService layoutService, VelocityEngine templateEngine, HttpServletRequest request,
			HttpServletResponse response,
			UserService userService, ArticleService articleService) {
		super(properties, servletContext, logService, encryptService, xmlService, mailService, mongoTemplate, layoutService,
				templateEngine, request, response);
		mailService.initMailService();
		this.userService=userService;
		ComponentAccessor.setUserservice(userService);
		this.articleService=articleService;
		ComponentAccessor.setArticleService(articleService);
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
		String username=request.getParameter("email");
		String password=request.getParameter("password");
		try {
			if(userService.login(username, password))
				 return "success";
			else return "error";
		} catch (Throwable e) {
			return "error";
		}
	}
	
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET) 
	public String logout() throws IOException {
		userService.logout();
		return "redirect:/main";
	}
	
	@RequestMapping(value = { "/createUser" }, method = RequestMethod.GET)
	public String createUser() throws IOException {
		User user=new User();
		try {
			user.setPassword(request.getParameter("password"));
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setUsername(request.getParameter("name"));
		try {
			userService.createUser(user);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "redirect:/main";
	}
	
	@RequestMapping(value = { "/createArticle" }, method = RequestMethod.GET)
	public String createArticle() throws IOException {
		Article article=new Article();
		article.setTitle(request.getParameter("title"));
		article.setContent("test Content");
		article.setKey(articleService.getUniqueKey());
		try {
			articleService.createArticle(article);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "redirect:/main";
	}

}
