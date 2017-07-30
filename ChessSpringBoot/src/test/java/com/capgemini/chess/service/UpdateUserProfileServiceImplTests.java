package com.capgemini.chess.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.dataaccess.UserDao;

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserProfileServiceImplTests {
	
	private static boolean setUpIsDone = false;

	//@InjectMocks
	//private UserProfileValidationService userProfileValidationServiceImpl;
	
	//@Mock
	//private UserDao userDao;

	@Before
    public void setUp() {
		if (setUpIsDone) {
	        return;
	    }
		
		

		setUpIsDone = true;
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
