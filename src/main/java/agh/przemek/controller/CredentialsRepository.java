package agh.przemek.controller;

import org.springframework.data.repository.CrudRepository;

import agh.przemek.model.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {

	Credentials findOneByUsernameAndPassword(String username, String password);

	default boolean exists(Credentials credentials) {
		return findOneByUsernameAndPassword(credentials.getUsername(), credentials.getPassword()) != null;
	}
}
