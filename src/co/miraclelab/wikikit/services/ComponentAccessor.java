package co.miraclelab.wikikit.services;

public class ComponentAccessor {
	private static UserService userservice;

	public static UserService getUserservice() {
		return userservice;
	}

	public static void setUserservice(UserService userservice) {
		ComponentAccessor.userservice = userservice;
	}

}
