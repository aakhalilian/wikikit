package co.miraclelab.wikikit.contextProvider;

import org.apache.velocity.VelocityContext;

import co.miraclelab.webframe.layoutservice.ContextProvider;
import co.miraclelab.wikikit.model.User;
import co.miraclelab.wikikit.services.ComponentAccessor;
import co.miraclelab.wikikit.services.UserService;

public class UserSection implements ContextProvider {

	@Override
	public VelocityContext ProvideContext(VelocityContext context) {
		UserService userService=ComponentAccessor.getUserservice();
		User user=userService.getLoggedinUser();
		context.put("loggedInUser", user);
		return context;
	}

}
