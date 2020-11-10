package tic.tac.toe;

import tic.tac.Player;

public class MatchResult
{
	/**
	 * players that participated in this match.
	 */
	public final Player[] players;

	/**
	 * the winner and looser of this game.
	 * If the game ended in a draw, both winner and looser will be null
	 */
	public final Player winner, looser;

	/**
	 * was this game a draw?
	 * if so, both winner and looser will be null too
	 */
	public final boolean wasDraw;

	/**
	 * the final game board
	 */
	public final char[][] finalBoard;

	/**
	 * initialize a new result with the given winner, looser and final board
	 * @param _winner the winner of the game
	 * @param _looser the looser of the game
	 * @param board the final game board
	 */
	public MatchResult(Player p1, Player p2, Player _winner, Player _looser, boolean draw, char[][] board)
	{
		players = new Player[]
		{
				p1,
				p2
		};
		wasDraw = draw;
		finalBoard = board;

		if (wasDraw)
		{
			winner = null;
			looser = null;
		}
		else
		{
			winner = _winner;
			looser = _looser;
		}
	}
}
