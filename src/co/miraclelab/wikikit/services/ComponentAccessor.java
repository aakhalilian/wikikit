package co.miraclelab.wikikit.services;

public class ComponentAccessor {
	private static UserService userservice;
	private static ArticleService articleService;

	public static UserService getUserservice() {
		return userservice;
	}

	public static void setUserservice(UserService userservice) {
		ComponentAccessor.userservice = userservice;
	}

	public static ArticleService getArticleService() {
		return articleService;
	}

	public static void setArticleService(ArticleService articleService) {
		ComponentAccessor.articleService = articleService;
	}

}
