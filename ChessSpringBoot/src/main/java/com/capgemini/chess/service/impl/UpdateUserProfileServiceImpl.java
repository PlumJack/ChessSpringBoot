package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.exception.InvalidPasswordException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.PasswordValidationService;
import com.capgemini.chess.service.UpdateUserProfileService;
import com.capgemini.chess.service.UserProfileValidationService;
import com.capgemini.chess.service.to.UserUpdateTO;

@Service
public class UpdateUserProfileServiceImpl implements UpdateUserProfileService {
	
	private UserProfileValidationService userProfileValidationService = null;
	private UserDao userDao = null;
	private PasswordValidationService passwordValidationService = null;
	
	@Autowired
	public UpdateUserProfileServiceImpl(UserProfileValidationService userProfileValidationService,
			UserDao userDao, PasswordValidationService passwordValidationService) {
		this.userProfileValidationService = userProfileValidationService;
		this.userDao = userDao;
		this.passwordValidationService = passwordValidationService;
	}




	@Override
	public void updateUser(UserUpdateTO userUpdateTO) throws UserProfileValidationException, InvalidPasswordException {
		userProfileValidationService.validateLogin(userUpdateTO.getLogin());
		passwordValidationService.validatePassword(userUpdateTO);
		userDao.updateUserProfile(userUpdateTO);
	}

}
