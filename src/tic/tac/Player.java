package tic.tac;

import java.util.Random;

import tic.tac.toe.Board;
import tic.tac.toe.Position;
import tic.tac.toe.Symbols;

public abstract class Player
{
	/**
	 * Initialize the player
	 * @param symbol the symbol this player will use during the game
	 */
	public void init(char symbol) {
	}
	
	/**
	 * called when it's the players turn to make a move.
	 * 
	 * @param board a copy of the game board. This is a copy, so changes dont affect the actual board
	 * @param symbols the definitions for the symbols on the board
	 * @param random a random instance to be used by players
	 * @return the position the player wants to take. if this position is invalid (null; already taken; out of range), act is called again
	 */
	public abstract Position act(Board board, Symbols symbols, Random random);
	
	/**
	 * 
	 * @return the name to display for this bot
	 */
	public abstract String getDisplayName();
}
