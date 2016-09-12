package co.miraclelab.wikikit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.miraclelab.webframe.utilities.UniqueKeyGenerator;
import co.miraclelab.wikikit.model.Article;
import co.miraclelab.wikikit.repository.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	ArticleRepository articleRepository;
	public static final String ARTICLEVAR="activeArticle";
	
	public boolean isExist(String key){
		return (articleRepository.findByKey(key)!=null);
	}
	
	public void createArticle(Article article) throws Throwable{
		if(this.isExist(article.getKey())){
			throw new Throwable("article-key-already-exist");
		}
		articleRepository.save(article);		
	}
	
	public String getUniqueKey(){
		String key="";
		boolean uniquness=false;
		while(!uniquness){
			key=UniqueKeyGenerator.generate(8);
			uniquness= !this.isExist(key);
		}
		return key;
	}

}
