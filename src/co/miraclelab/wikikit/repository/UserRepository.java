package co.miraclelab.wikikit.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.miraclelab.wikikit.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	List<User> findByUsername(String name);
}
