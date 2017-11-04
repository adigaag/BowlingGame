package com.bowlinggame.business;

import com.bowlinggame.exception.BowlingException;

/**
 * BowlingGame class
 * @author aditibhatnagar
 *
 */
public class BowlingGame {
	private int[] attempts;
	private int rollIndex = 0;
	private static int MAX_PINS = 10;
	private static int MAX_FRAMES = 10;
	private static int BONUS = 10;
	private static int MAX_ATTEMPTS = 21;

	private int frameCounter = 0;
	private boolean gameOver = false;
	private int scoreSpareOpen = 0;
	private int noOfAttempts = 0;
	private boolean isSpare = false;
	private boolean isLastFrameSpare = false;
	private boolean isLastFrameStrike = false;
	Frame frame = new Frame();
	
	/**
	 * Constructor max number of attempts array initialized
	 */
	public BowlingGame() {
		this.attempts = new int[MAX_ATTEMPTS];
	}

	/**
	 * Store roll in an array
	 * @param roll
	 */
	public void roll(int roll) {
		
		if(roll > MAX_PINS ||  roll < 0) { 
			throw new BowlingException("Illegal number of pins");
		}
		if(rollIndex > (MAX_ATTEMPTS-1)) {
			throw new BowlingException("Illegal number of attempts");
		}
		attempts[rollIndex++] = roll;
		
		if(!gameOver) {
			isSpare = false;
			if(roll == 10) {
				frameCounter++;
			} 
			if(roll < 10  && frameCounter <= 10) {
				scoreSpareOpen = scoreSpareOpen + roll;
				noOfAttempts++;
				if(noOfAttempts == 2) {
					if(scoreSpareOpen == 10) {
						isSpare = true;
					} 
					if(scoreSpareOpen > 10) {
						throw new BowlingException("Illegal number of total pins in a frame");
					} 
					frameCounter++;
				} 
			}

			if(frameCounter < 10 && noOfAttempts == 2) {
				noOfAttempts = 0;
				scoreSpareOpen = 0;
			}
			if(frameCounter == 10) {
				if(!isSpare && noOfAttempts == 2) {
					gameOver = true;
					noOfAttempts = 0;
					scoreSpareOpen = 0;
				} else if(isSpare && noOfAttempts == 2) {
					frameCounter++;
					noOfAttempts = 0;
					scoreSpareOpen = 0;
					isLastFrameSpare = true;
				} else if(roll == 10) {
					isLastFrameStrike = true;
				}
			} else if(frameCounter == 11 && isLastFrameSpare ) {
				gameOver = true;
			} else if(frameCounter > 10 && frameCounter < 12 && isLastFrameStrike  && roll < 10) {
				frameCounter++;
			} else if (frameCounter == 12 && isLastFrameStrike) {
				gameOver = true;
			}
		}
	}

	/**
	 * get the score for a game
	 * @return score as int
	 */
	public int getScore() {
		int score = 0;
		int frameIndex = 0;
		for (int i = 0; i < MAX_FRAMES; i++) {
			if (frame.isAttemptStrike(frameIndex)) {
				score += BONUS + frame.bonusOnStrike(frameIndex);
				frameIndex++;
			} else if (frame.isAttemptSpare(frameIndex)) {
				score += BONUS + frame.bonusOnSpare(frameIndex);
				frameIndex += 2;
			} else {
				score += frame.addToScore(frameIndex);
				frameIndex += 2;
			}
		}
		return score;
	}
	
	/**
	 * indicates game is over
	 * @return boolean
	 */
	public boolean isFinished() {
		return gameOver;
	}
	
	class Frame {
	
		/**
		 * add a roll
		 * @param int frameIndex
		 * @return int roll addition
		 */
		private int addToScore(int frameIndex) {
			return attempts[frameIndex] + attempts[frameIndex + 1];
		}
	
		/**
		 * Check if roll is a Strike
		 * @param frameIndex
		 * @return boolean for a strike
		 */
		private boolean isAttemptStrike(int frameIndex) {
			return attempts[frameIndex] == 10;
		}
	
		/**
		 * get bonus on Strike
		 * @param int frameIndex
		 * @return int bonus on Strike
		 */
		private int bonusOnStrike(int frameIndex) {
			return addToScore(frameIndex + 1);
		}
		
		/**
		 * Check if roll is a Spare
		 * @param frameIndex
		 * @return boolean for a Spare
		 */
		private boolean isAttemptSpare(int frameIndex) {
			return addToScore(frameIndex) == 10;
		}
	
		/**
		 * get bonus on Spare
		 * @param int frameIndex
		 * @return int bonus on Spare
		 */
		private int bonusOnSpare(int frameIndex) {
			return attempts[frameIndex + 2];
		}
	}
	
	/**
	 *  main method to accept arguments from command line
	 * @param args
	 */
	public static void main(String [] args) {
		BowlingGame bowlingGame = new BowlingGame();
		System.out.println(bowlingGame.getScore());
		System.out.println(bowlingGame.isFinished());
	
		for(int i = 0;i < args.length;i++)
	    { 
			try {
				if(!bowlingGame.isFinished()) {
				
					bowlingGame.roll(Integer.valueOf(args[i]));
				} 
			} catch(NumberFormatException exception) {
				System.out.println("Enter space separated integers only!");
				throw new BowlingException("Enter space separated integers only!");
			} catch (BowlingException e) {
				System.out.println("Invalid integer input!");
				throw new BowlingException("Invalid integer!");
			}
	    }
	
		System.out.println(bowlingGame.getScore());
		System.out.println(bowlingGame.isFinished());
		
	}
}
