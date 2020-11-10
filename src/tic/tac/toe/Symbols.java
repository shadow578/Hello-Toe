package tic.tac.toe;

public class Symbols
{
	/**
	 * symbols for:
	 * BLANK: a blank space on the board
	 * SELF:  the own player
	 * OPPONENT: the opponent player
	 */
	public final char BLANK, SELF, OPPONENT;
	
	public Symbols(char blank, char self, char opponent)
	{
		BLANK = blank;
		SELF = self;
		OPPONENT = opponent;
	}
}
