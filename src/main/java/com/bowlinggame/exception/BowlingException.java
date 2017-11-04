package com.bowlinggame.exception;

/**
 *  exception for the bowling game
 *  @author aditibhatnagar
 */
public class BowlingException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 316359175869791481L;

	public BowlingException(String message) {
        super(message);
    }
}
