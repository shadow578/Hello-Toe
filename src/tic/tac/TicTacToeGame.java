package tic.tac;

import java.util.Random;

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
	 * the game board, size 3x3
	 * 
	 * 0,0 | 1,0 | 2,0
	 * 0,1 | 1,1 | 2,1
	 * 0,2 | 1,2 | 2,2
	 * 
	 * X is Player 1,
	 * O is Player 2,
	 * SPACE is empty
	 */
	private char[][] gameBoard;

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

		// init board
		resetBoard();

		// randomize what player starts
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
				p = (isPlayer1Turn ? player1 : player2).act(getBoard(), sym, random());
			} while (p == null // cannot be null
					|| p.x < 0 || p.x > 2 // x in range 0 - 2
					|| p.y < 0 || p.y > 2 // y in range 0 - 2
					|| gameBoard[p.y][p.x] != BLANK); // has to be BLANK space

			// claim the space selected
			gameBoard[p.y][p.x] = isPlayer1Turn ? PLAYER1 : PLAYER2;

			// switch player turns
			isPlayer1Turn = !isPlayer1Turn;

			// check for game end states
			if (GameUtil.hasWon(gameBoard, PLAYER1))
			{
				// player 1 won
				return new MatchResult(player1, player2, player1, player2, false, getBoard());
			}
			else if (GameUtil.hasWon(gameBoard, PLAYER2))
			{
				// player 2 won
				return new MatchResult(player1, player2, player2, player1, false, getBoard());
			}
			else if (!GameUtil.hasEmptySpaces(gameBoard, BLANK))
			{
				// no empty spaces are left, but no player won. This is a draw
				return new MatchResult(player1, player2, null, null, true, getBoard());
			}
		} while (true);
	}

	/**
	 * @return a clone of the internal game board. modifying this does nothing, so dont try it ;)
	 */
	public char[][] getBoard()
	{
		return gameBoard.clone();
	}

	/**
	 * @return a random instance to be used by players
	 */
	public Random random()
	{
		return rng;
	}

	/**
	 * reset the game board to all empty spaces
	 */
	private void resetBoard()
	{
		// init array
		gameBoard = new char[3][3];

		// set all cells to empty
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				gameBoard[x][y] = BLANK;
	}
}
