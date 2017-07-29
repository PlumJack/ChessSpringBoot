package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.exception.UserProfileExistsInDatabaseException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.UpdateUserProfileService;
import com.capgemini.chess.service.UserServiceFacade;
import com.capgemini.chess.service.UserStatsReaderService;
import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;
import com.capgemini.chess.service.to.UserUpdateTO;

@Service
public class UserServiceFacadeImpl implements UserServiceFacade {
	
	
	private UserDao userDao = null;
	private UserStatsReaderService userStatsReaderService = null;
	//
	private UpdateUserProfileService updateUserProfileService = null;
	
	@Autowired
	public UserServiceFacadeImpl(UserDao userDao, UserStatsReaderService userStatsReaderService) {
		this.userDao = userDao;
		this.userStatsReaderService = userStatsReaderService;
	}

	public UserProfileTO saveUserProfile(UserProfileTO userProfileTo) throws UserProfileExistsInDatabaseException{
		return userDao.save(userProfileTo);
	}
	/*
	public UserStatsTO getStats(long id){
		return userStatsReaderService.getStats(id);
	}
	*/
	/*
	public UserStatsTO getStats(Long id) throws UserProfileValidationException {
		return userStatsReaderService.getStats(id);
	}
	*/
	
	public UserStatsTO getStats(String login) throws UserProfileValidationException {
		return userStatsReaderService.getStats(login);
	}

	@Override
	public void updateUserProfile(UserUpdateTO userUpdateTO) throws UserProfileValidationException, InvalidPasswordException {
		updateUserProfileService.updateUser(userUpdateTO);
		
	}
	
}
