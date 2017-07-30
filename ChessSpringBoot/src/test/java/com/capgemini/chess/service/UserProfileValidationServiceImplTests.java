package com.capgemini.chess.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.mockito.BDDMockito.given;
//import static org.hamcrest.Matchers.*;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.dataaccess.dao.impl.UserDaoImpl;
import com.capgemini.chess.exception.UserProfileExistsInDatabaseException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.impl.UserProfileValidationServiceImpl;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileValidationServiceImplTests {

	private static boolean setUpIsDone = false;
		
	//UserProfileValidationService userProfileValidationService;
	
	//@InjectMocks
	private UserProfileValidationService userProfileValidationService;
	
	@Mock
	//@Spy
	private UserDao userDao;
	
	
	
	@Before
	    public void setUp() {
			userProfileValidationService = new UserProfileValidationServiceImpl(userDao);
	
			BDDMockito.given(userDao.findById(2L)).willReturn(createUserProfile(2));
			BDDMockito.given(userDao.findById(5L)).willReturn(null);
			BDDMockito.given(userDao.findByLogin("login2")).willReturn(createUserProfile(2));
			BDDMockito.given(userDao.findByLogin("login5")).willReturn(null);

		}
	
	
	private UserProfileTO createUserProfile(int i){
		UserProfileTO userProfileTO = new UserProfileTO();
		userProfileTO.setId(new Long(i));
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
