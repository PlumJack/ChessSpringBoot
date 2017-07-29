package com.capgemini.chess.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.dataaccess.UserDao;
import com.capgemini.chess.dataaccess.dao.impl.UserDaoImpl;
import com.capgemini.chess.exception.UserProfileExistsInDatabaseException;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatsTO;

import junit.framework.Assert;

//@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(classes = ChessApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserDaoImplTests {
	
	
	//@InjectMocks
	@Autowired
	private UserDao userDao;

	@Configuration
	static class RankServiceTestContextConfiguration {
		@Bean
		public UserDao userDao() {
			return new UserDaoImpl();
		}
	}
	
	
	@Before
    public void setUp() throws UserProfileExistsInDatabaseException {
		//userDao
		//userDao = new UserDaoImpl();
		
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
	public void test() throws UserProfileExistsInDatabaseException {
		//setUp();
		//userDao = new UserDaoImpl();
		UserStatsTO stats = userDao.getStats("login123");
		assertEquals(5, stats.getGamesPlayed());
	}

}
