package com.capgemini.chess.service;

import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.to.UserStatsTO;
import com.capgemini.chess.service.to.UserUpdateTO;

public interface UserServiceFacade {
	
	//UserStatsTO getStats(Long id) throws UserProfileValidationException;
	UserStatsTO getStats(String login) throws UserProfileValidationException;
	void updateUserProfile(UserUpdateTO userUpdateTO) throws UserProfileValidationException, InvalidPasswordException;
}
