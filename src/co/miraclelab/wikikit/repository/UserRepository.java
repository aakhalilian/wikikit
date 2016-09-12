package co.miraclelab.wikikit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import co.miraclelab.wikikit.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(String name);
}
