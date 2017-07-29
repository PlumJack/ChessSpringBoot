package com.capgemini.chess.service;

import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.to.UserUpdateTO;

public interface UpdateUserProfileService {
	
	void updateUser(UserUpdateTO userUpdateTO) throws UserProfileValidationException, InvalidPasswordException;

}
