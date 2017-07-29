package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.exception.MatchExistsInDatabaseException;
import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserProfileExistsInDatabaseException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.MatchRegistrationService;
import com.capgemini.chess.service.UpdateUserProfileService;
import com.capgemini.chess.service.UserServiceFacade;
import com.capgemini.chess.service.UserStatsReaderService;
import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.service.to.MatchTO;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;
import com.capgemini.chess.service.to.UserUpdateTO;

@Service
public class UserServiceFacadeImpl implements UserServiceFacade {
	
	
	private UserDao userDao = null;
	private UserStatsReaderService userStatsReaderService = null;
	private UpdateUserProfileService updateUserProfileService = null;
	private MatchRegistrationService matchRegistrationService = null;

	@Autowired
	public UserServiceFacadeImpl(UserDao userDao, UserStatsReaderService userStatsReaderService,
			UpdateUserProfileService updateUserProfileService, MatchRegistrationService matchRegistrationService) {
		this.userDao = userDao;
		this.userStatsReaderService = userStatsReaderService;
		this.updateUserProfileService = updateUserProfileService;
		this.matchRegistrationService = matchRegistrationService;
	}

	public UserProfileTO saveUserProfile(UserProfileTO userProfileTo) throws UserProfileExistsInDatabaseException{
		return userDao.save(userProfileTo);
	}
	
	public UserStatsTO getStats(String login) throws UserProfileValidationException {
		return userStatsReaderService.getStats(login);
	}

	@Override
	public void updateUserProfile(UserUpdateTO userUpdateTO) throws UserProfileValidationException, InvalidPasswordException {
		updateUserProfileService.updateUser(userUpdateTO);
		
	}

	@Override
	public void registerNewMatch(MatchTO matchTO)
			throws UserProfileValidationException, MatchValidationException, MatchExistsInDatabaseException {
		matchRegistrationService.registerNewMatch(matchTO);
		
	}
	
}
