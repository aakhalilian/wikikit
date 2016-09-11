package co.miraclelab.wikikit.condition;

import co.miraclelab.webframe.layoutservice.Condition;
import co.miraclelab.wikikit.services.ComponentAccessor;
import co.miraclelab.wikikit.services.UserService;

public class UserisLoggedIn implements Condition {

	@Override
	public boolean isTrue() {
		UserService userService=ComponentAccessor.getUserservice();
		return (userService.getLoggedinUser()!=null);
	}

}
