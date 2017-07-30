package com.capgemini.chess.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.dataaccess.dao.impl.UserDaoImpl;
import com.capgemini.chess.exception.UserProfileExistsInDatabaseException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.impl.UserProfileValidationServiceImpl;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;

public class UserProfileValidationServiceImplTests {

	private static boolean setUpIsDone = false;
		
	UserProfileValidationService userProfileValidationService;
	
	private UserDao userDao;
	
	@Before
	    public void setUp() throws UserProfileExistsInDatabaseException {
			userDao = new UserDaoImpl();
			userProfileValidationService = new UserProfileValidationServiceImpl(userDao);
			userDao.save(createUserProfile(1));
			userDao.save(createUserProfile(2));
		}
	
	
	private UserProfileTO createUserProfile(int i){
		UserProfileTO userProfileTO = new UserProfileTO();
		userProfileTO.setLogin("login" + i);
		userProfileTO.setPassword("password" + i);
		userProfileTO.setName("name" + i);
		userProfileTO.setSurname("surname" + i);
		userProfileTO.setEmail("email" + i + "@email.pl");
		userProfileTO.setAboutMe("aboutMe" + i);
		userProfileTO.setLifeMotto("lifeMotto" + i);
		
		userProfileTO.setUserStatsTO(createUserStats(i));
		
		return userProfileTO;
	}
	
	private UserStatsTO createUserStats(int i){
		UserStatsTO userStatsTO = new UserStatsTO();
		userStatsTO.setLevel(0);
		userStatsTO.setPosition(i);
		userStatsTO.setPoints(i*10-20);
		userStatsTO.setGamesPlayed(i+3);
		userStatsTO.setGamesWon(i);
		userStatsTO.setGamesDrawn(1);
		userStatsTO.setGamesLost(2);
		
		return userStatsTO;
	}
	
	@Test
	public void shouldThrowExceptionWhenInvalidId() {
		
		boolean exceptionCatched = false;
		try {
			userProfileValidationService.validateId(5L);
		} catch (UserProfileValidationException e) {
			exceptionCatched = true;
		}
		assertTrue(exceptionCatched);
		
	}
	
	@Test
	public void shouldThrowExceptionWhenInvalidLogin() {
		
		boolean exceptionCatched = false;
		try {
			userProfileValidationService.validateLogin("login5");
		} catch (UserProfileValidationException e) {
			exceptionCatched = true;
		}
		assertTrue(exceptionCatched);
		
	}

	@Test
	public void shouldNotThrowExceptionWhenValidId() {
		
		boolean exceptionCatched = false;
		try {
			userProfileValidationService.validateId(2L);
		} catch (UserProfileValidationException e) {
			exceptionCatched = true;
		}
		assertFalse(exceptionCatched);
		
	}

	@Test
	public void shouldNotThrowExceptionWhenValidLogin() {
		
		boolean exceptionCatched = false;
		try {
			userProfileValidationService.validateLogin("login2");
		} catch (UserProfileValidationException e) {
			exceptionCatched = true;
		}
		assertFalse(exceptionCatched);
		
	}
	

}
