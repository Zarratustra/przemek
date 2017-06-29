package agh.przemek.controller;

import org.springframework.data.repository.CrudRepository;

import agh.przemek.model.Credentials;
import agh.przemek.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findOneByUsernameAndPassword(String username, String password);

	User findOneByUsername(String username);

	default boolean exists(Credentials creds) {
		return findOneByUsernameAndPassword(creds.getUsername(), creds.getPassword()) != null;
	}

	default boolean exists(String username) {
		return findOneByUsername(username) != null;
	}
}
