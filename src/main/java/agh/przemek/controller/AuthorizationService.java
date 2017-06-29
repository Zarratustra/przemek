package agh.przemek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agh.przemek.model.Credentials;

@Service
public class AuthorizationService {

	@Autowired
	private CredentialsRepository credentialsRepository;

	public void basicAuthorization(String authHeader) throws ForbiddenException {
		if (!authHeader.startsWith("Basic")) {
			throw new ForbiddenException("Not supported authorization method");
		}

		Credentials creds = Credentials.fromBase64(authHeader.split(" ")[1]);
		if (!credentialsRepository.exists(creds)) {
			throw new ForbiddenException("Not authorized credentials: " + creds);
		}
	}
}
