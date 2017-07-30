package com.capgemini.chess.service;

import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.to.UserUpdateTO;

public interface UserProfileValidationService {
	
	//void validateEmail(String email) throws UserProfileValidationException;
	
	void validateId(Long id) throws UserProfileValidationException;
	void validateLogin(String login) throws UserProfileValidationException;
	
	//void validateUserUpdate(UserUpdateTO userUpdateTO) throws UserProfileValidationException;
}
