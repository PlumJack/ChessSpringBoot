package com.capgemini.chess.service.impl;

import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.MatchDao;
import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.MatchValidationService;
import com.capgemini.chess.service.UserProfileValidationService;
import com.capgemini.chess.service.to.MatchTO;

@Service
public class MatchValidationServiceImpl implements MatchValidationService {

	private MatchDao matchDao;
	
	private UserProfileValidationService userProfileValidationService;
	
	@Override
	public void validateMatch(Long id) throws MatchValidationException {
		MatchTO matchTO = matchDao.findById(id);
		if(matchTO == null){
			throw new MatchValidationException("There is no match with that Id.");
		}
	}

}
