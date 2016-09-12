package co.miraclelab.wikikit.ut;


import org.junit.Before;
import org.junit.Test;

import co.miraclelab.wikikit.services.ArticleService;

public class ArticleServiceTest {
	ArticleService as;
	
	@Before
	public void setUp() throws Exception {
		as=new ArticleService();
	}

	@Test
	public void test() {
		System.out.println(as.getUniqueKey());
	}

}
