package co.miraclelab.wikikit.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import co.miraclelab.wikikit.model.Article;

public interface ArticleRepository extends MongoRepository<Article, String> {
	Article findByKey(String key);
	
	@Query("{ parent.key : ?0 }")
	List<Article> findByParentKey(String key);
	
	List<Article> findByContentRegex(String contentRegex);
	
}
