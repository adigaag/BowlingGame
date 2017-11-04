package com.bowlinggame.test;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bowlinggame.business.BowlingGame;
import com.bowlinggame.exception.BowlingException;

/**
 * 
 * @author aditibhatnagar
 *
 */
public class BowlingGameTest {
	private BowlingGame bowlGame;

	void rollAttempts(int n, int pins, BowlingGame g) {
		for (int i = 0; i < n; i++) bowlGame.roll(pins);
	}

	@Before
	public void setUp() {
		this.bowlGame = new BowlingGame();
	}

	@Test(expected = BowlingException.class)
    public void illegalBowlException() {
		bowlGame.roll(200);
    }
	
	
	@Test
	public void spare() {
		bowlGame.roll(5);
		bowlGame.roll(5);
		bowlGame.roll(3);
		rollAttempts(17, 0, bowlGame);
		assertEquals(16, bowlGame.getScore());
	}
	
	@Test
	public void strike() {
		bowlGame.roll(10);
		bowlGame.roll(3);
		bowlGame.roll(4);
		rollAttempts(16, 0, bowlGame);
		assertEquals(24, bowlGame.getScore());
	}
	
	@Test
	public void perfectGame() {
		rollAttempts(12, 10, bowlGame);
		assertEquals(300, bowlGame.getScore());
	}
	

	@Test
	public void allZero() {
		rollAttempts(20, 0, bowlGame);
		assertEquals(0, bowlGame.getScore());
	}
	
	@Test
	public void allOnes() {
		rollAttempts(20, 1, bowlGame);
		assertEquals(20, bowlGame.getScore());
	}
	
	@After
	public void tearDown() {
		this.bowlGame = null;
	}

}
