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
	
	//private StatsReaderService flightControlService = null;
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void test() {
		//given
		UserStatsTO userStatsTo = new UserStatsTO();
		userStatsTo.setGamesPlayed(5);
		userStatsTo.setGamesWon(3);
		userStatsTo.setGamesLost(2);
		//Mockito.when(userDao.getStats(1)).then((Answer<UserStatsTO>) userStatsTo);
		Mockito.when(userDao.getStats("login123")).thenReturn(userStatsTo);
		//when
		UserStatsTO returnedUserStatsTo = userDao.getStats("login123");
		//then
		Assert.assertEquals(3, returnedUserStatsTo.getGamesWon());
	}

}
