package com.capgemini.chess.service.to;

public interface UserStatsCalculationService {
	
	UserStatsTO addWin(UserStatsTO userStatsTO);
	UserStatsTO addDraw(UserStatsTO userStatsTO);
	UserStatsTO addLoss(UserStatsTO userStatsTO);	
}
