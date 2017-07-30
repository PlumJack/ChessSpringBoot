package com.capgemini.chess.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.service.impl.UserStatsReaderServiceImpl;
import com.capgemini.chess.service.to.UserStatsTO;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class UserStatsReaderServiceImplTests {

	@InjectMocks
	private UserStatsReaderServiceImpl userStatsReaderServiceImpl;
	
	@Mock
	private UserDao userDao;
	
	@Mock
	private UserProfileValidationService userProfileValidationService;
	
	
	@Before
	public void setup(){
		UserStatsTO userStatsTo = new UserStatsTO();
		userStatsTo.setGamesWon(3);
		Mockito.when(userDao.getStats("login123")).thenReturn(userStatsTo);
		Mockito.when(userDao.getStats(123L)).thenReturn(userStatsTo);
	}
	
	@Test
	public void shouldGetStatsWithLogin() {
		//given
		//when
		UserStatsTO returnedUserStatsTo = userDao.getStats("login123");
		//then
		assertEquals(3, returnedUserStatsTo.getGamesWon());
	}

	@Test
	public void shouldGetStatsWithId() {
		//given
		//when
		UserStatsTO returnedUserStatsTo = userDao.getStats(123L);
		//then
		assertEquals(3, returnedUserStatsTo.getGamesWon());
	}
}
