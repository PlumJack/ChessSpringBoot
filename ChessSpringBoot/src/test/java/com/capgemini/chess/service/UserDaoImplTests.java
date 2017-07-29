package com.capgemini.chess.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.exception.UserProfileExistsInDatabaseException;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(classes = ChessApplication.class)
public class UserDaoImplTests {
	
	@Mock
	private UserDao userDao;
	
	@Before
    public void setUp() throws UserProfileExistsInDatabaseException {
		//userDao
		
		UserStatsTO userStatsTO = new UserStatsTO();
		userStatsTO.setGamesPlayed(5);
		userStatsTO.setGamesWon(3);
		userStatsTO.setGamesLost(2);
		
		UserProfileTO userProfileTo = new UserProfileTO();
		userProfileTo.setLogin("login123");
		userProfileTo.setPassword("password123");
		userProfileTo.setUserStatsTO(userStatsTO);
		
		userDao.save(userProfileTo);
	}

	@Test
	public void test() {
		UserStatsTO stats = userDao.getStats("login123");
		Assert.assertEquals(5, stats.getGamesPlayed());
	}

}
