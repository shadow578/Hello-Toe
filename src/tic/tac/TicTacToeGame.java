package tic.tac;

import java.util.Random;

import tic.tac.toe.Board;
import tic.tac.toe.MatchResult;
import tic.tac.toe.Position;
import tic.tac.toe.Symbols;

public class TicTacToeGame
{
	/**
	 * symbol used for a blank space
	 */
	private final char BLANK = ' ';

	/**
	 * symbol used for player 1
	 */
	private final char PLAYER1 = 'X';

	/**
	 * symbol used for player 2
	 */
	private final char PLAYER2 = 'O';

	/**
	 * random number generator used internally and by players
	 */
	private Random rng = new Random();

	/**
	 * match two players against each other
	 * @param player1 the first player
	 * @param player2 the second player
	 * @return the result of the match
	 */
	public MatchResult match(Player player1, Player player2)
	{
		// check players are not null
		if (player1 == null || player2 == null)
			throw new IllegalArgumentException("players cannot be null!");

		// ready player 1
		player1.init();
		player2.init();

		// init and reset board
		Board board = new Board(BLANK);

		// randomize which player starts
		boolean isPlayer1Turn = random().nextBoolean();

		// play games until one player wins
		do
		{
			// prepare symbols for current player
			Symbols sym = new Symbols(BLANK, isPlayer1Turn ? PLAYER1 : PLAYER2, isPlayer1Turn ? PLAYER2 : PLAYER1);

			// ask player to move, retry if invalid move
			Position p = null;
			do
			{
				//System.out.printf("player %d's move%n", isPlayer1Turn ? 1 : 2);
				p = (isPlayer1Turn ? player1 : player2).act(board.clone(), sym, random());
			} while (p == null // cannot be null
					|| p.x < 0 || p.x > 2 // x in range 0 - 2
					|| p.y < 0 || p.y > 2 // y in range 0 - 2
					|| board.get(p.x, p.y) != BLANK); // has to be BLANK space

			// claim the space selected
			board.set(p.x, p.y, isPlayer1Turn ? PLAYER1 : PLAYER2);

			// switch player turns
			isPlayer1Turn = !isPlayer1Turn;

			// check for game end states
			if (board.checkWin(PLAYER1))
			{
				// player 1 won
				return new MatchResult(player1, player2, player1, player2, false, board);
			}
			else if (board.checkWin(PLAYER2))
			{
				// player 2 won
				return new MatchResult(player1, player2, player2, player1, false, board);
			}
			else if (!board.hasAnyEmptySpace(BLANK))
			{
				// no empty spaces are left, but no player won. This is a draw
				return new MatchResult(player1, player2, null, null, true, board);
			}
		} while (true);
	}

	/**
	 * @return a random instance to be used by players
	 */
	public Random random()
	{
		return rng;
	}
}
