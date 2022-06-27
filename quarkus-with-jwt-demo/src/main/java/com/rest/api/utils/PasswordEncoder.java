package com.rest.api.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

	public String encodePassword(String plainTextPassword) {
        try {
        	String encodePassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(10));
        	return encodePassword;
        } catch (Exception e) {
            throw new RuntimeException("Exception encoding password", e);
        }
    }


	public boolean checkPassword(String plainTextPassword, String hashedPassword) {

		if (null == hashedPassword || !hashedPassword.startsWith("$2a$")) {
			throw new RuntimeException("Hashed password is invalid");
		}

		return BCrypt.checkpw(plainTextPassword, hashedPassword);
	}
}
