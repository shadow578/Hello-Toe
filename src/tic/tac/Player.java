package tic.tac;

import java.util.Random;

import tic.tac.toe.Position;
import tic.tac.toe.Symbols;

public abstract class Player
{
	/**
	 * Initialize the player
	 */
	public void init() {
	}
	
	/**
	 * called when it's the players turn to make a move.
	 * Game board layout (x,y):
	 * 0,0 | 1,0 | 2,0
	 * 0,1 | 1,1 | 2,1
	 * 0,2 | 1,2 | 2,2
	 * 
	 * @param board a copy of the game board
	 * @param symbols the definitions for the symbols on the board
	 * @param random a random instance to be used by players
	 * @return the position the player wants to take. if this position is invalid (null; already taken; out of range), act is called again
	 */
	public abstract Position act(char[][] board, Symbols symbols, Random random);
	
	/**
	 * 
	 * @return the name to display for this bot
	 */
	public abstract String getDisplayName();
}
