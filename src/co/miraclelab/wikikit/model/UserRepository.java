package co.miraclelab.wikikit.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	List<User> findByUsername(String name);
}
