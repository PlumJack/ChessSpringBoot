package com.capgemini.chess.service.impl;

import org.springframework.stereotype.Service;

import com.capgemini.chess.service.to.UserStatsCalculationService;
import com.capgemini.chess.service.to.UserStatsTO;

@Service
public class UserStatsCalculationServiceImpl implements UserStatsCalculationService {

	@Override
	public UserStatsTO addWin(UserStatsTO userStatsTO) {
		userStatsTO.setGamesPlayed(userStatsTO.getGamesPlayed()+1);
		userStatsTO.setGamesWon(userStatsTO.getGamesWon()+1);
		userStatsTO.setPoints(userStatsTO.getPoints()+10);
		userStatsTO.setLevel(calculateLevel(userStatsTO));
		return userStatsTO;
	}

	@Override
	public UserStatsTO addDraw(UserStatsTO userStatsTO) {
		userStatsTO.setGamesPlayed(userStatsTO.getGamesPlayed()+1);
		userStatsTO.setGamesDrawn(userStatsTO.getGamesDrawn()+1);
		userStatsTO.setLevel(calculateLevel(userStatsTO));
		return userStatsTO;
	}

	@Override
	public UserStatsTO addLoss(UserStatsTO userStatsTO) {
		userStatsTO.setGamesPlayed(userStatsTO.getGamesPlayed()+1);
		userStatsTO.setGamesLost(userStatsTO.getGamesLost()+1);
		userStatsTO.setPoints(userStatsTO.getPoints()-10);
		userStatsTO.setLevel(calculateLevel(userStatsTO));
		return userStatsTO;
	}
	
	private int calculateLevel(UserStatsTO u){
		return (int)(10 * u.getGamesWon() / u.getGamesPlayed());
	}

}
