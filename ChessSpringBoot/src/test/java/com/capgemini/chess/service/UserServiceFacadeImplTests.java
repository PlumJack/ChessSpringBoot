package com.capgemini.chess.service;

import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.impl.UserServiceFacadeImpl;
import com.capgemini.chess.service.impl.UserStatsReaderServiceImpl;
import com.capgemini.chess.service.to.UserStatsTO;

import junit.framework.Assert;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceFacadeImplTests {

	@InjectMocks
	private UserServiceFacadeImpl userServiceFacadeImpl;
	
	@Mock
	private UserStatsReaderServiceImpl userStatsReaderServiceImpl;
	
	
	@Test
	public void test() throws UserProfileValidationException {
		//given
		UserStatsTO userStatsTo = new UserStatsTO();
		userStatsTo.setGamesPlayed(5);
		userStatsTo.setGamesWon(3);
		userStatsTo.setGamesLost(2);
		//Mockito.when(userDao.getStats(1)).then((Answer<UserStatsTO>) userStatsTo);
		Mockito.when(userStatsReaderServiceImpl.getStats("login123")).thenReturn(userStatsTo);
		//when(userStatsReaderServiceImpl.getStats("login123")).thenReturn(userStatsTo);
		//when
		UserStatsTO returnedUserStatsTo = userStatsReaderServiceImpl.getStats("login123");
		//then
		Assert.assertEquals(3, returnedUserStatsTo.getGamesWon());
	}
	
}
