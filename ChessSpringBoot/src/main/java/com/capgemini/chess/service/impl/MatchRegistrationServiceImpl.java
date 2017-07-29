package com.capgemini.chess.service.impl;

import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.MatchDao;
import com.capgemini.chess.exception.MatchExistsInDatabaseException;
import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserProfileValidationException;
import com.capgemini.chess.service.MatchValidationService;
import com.capgemini.chess.service.MatchRegistrationService;
import com.capgemini.chess.service.UserProfileValidationService;
import com.capgemini.chess.service.UserStatsUpdateService;
import com.capgemini.chess.service.to.MatchTO;

@Service
public class MatchRegistrationServiceImpl implements MatchRegistrationService {

	private MatchDao matchDao;
	private MatchValidationService matchValidationService;
	private UserProfileValidationService userProfileValidationService;
	private UserStatsUpdateService userStatsUpdateService;
	
	@Override
	public void registerNewMatch(MatchTO newMatch) throws UserProfileValidationException, MatchValidationException, MatchExistsInDatabaseException {
		
		userProfileValidationService.validateId(newMatch.getHostPlayerId());
		userProfileValidationService.validateId(newMatch.getGuestPlayerId());
		
		MatchTO matchTO = matchDao.save(newMatch);
		matchValidationService.validateMatch(matchTO.getId());
		
		userStatsUpdateService.updateStats(newMatch);

	}

}
