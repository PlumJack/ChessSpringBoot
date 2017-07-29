package com.capgemini.chess.service;

import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserProfileValidationException;

public interface MatchValidationService {
	
	void validateMatch(Long id) throws MatchValidationException;

}
