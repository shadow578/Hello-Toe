package tic.players;

import java.util.ArrayList;
import java.util.Random;

import tic.tac.Player;
import tic.tac.toe.Board;
import tic.tac.toe.Position;
import tic.tac.toe.Symbols;

/**
 * Get to know Max - the biggest 
 * in the tic-tac-toe world (although he is a bit short): 
 * 
 * Even when max was little, he was encouraged to always act 
 * intelligently and with foresight. 
 * This made him not only very unpopular among his peers, but also a bad loser.
 * When he lost a game of tic-tac-toe against his father at a young age, he vowed 
 * never to lose another game. This still holds true today, making 
 * him one of the most feared opponents.
 * 
 * Fun Fact: 
 * Because he is so short, his friends (if he had any) call him Mini-Max.
 * 
 * TL;DR:
 * a bot that uses minimax algorithm to always win
 */
public class MaxBot extends Player
{
	@Override
	public Position act(Board board, Symbols symbols, Random random)
	{
		// test all possible moves using minimax
		Position best = null;
		double bestScore = -10000;
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				if (board.get(x, y) == symbols.BLANK)
				{
					// this is a blank position, add a copy of the board with that position set to p
					Board mov = board.clone();
					mov.set(x, y, symbols.SELF);

					// calculate score for move and add to list
					double score = miniMax(mov, symbols, 0, false);

					// check if score is higher than current best
					if (score > bestScore)
					{
						// System.out.printf("update best move to (%d;%d) score %.2f%n", x, y, score);
						bestScore = score;
						best = new Position(x, y);
					}
				}

		// take the best move
		// System.out.printf("taking move (%d;%d) with score %.2f%n", best.x, best.y,
		// bestScore);
		return best;
	}

	/**
	 * the main (recursive) minimax function.
	 * It tries every moves and finds the best (or worst if minimizer) move we can take
	 * @param board the initial game board to work with
	 * @param sym the symbol set
	 * @param currentMove what move are we currently on? (internal, set to 0)
	 * @param isMaximizer are we in the maximizer role? (internal, set to true unless you want to loose)
	 * @return the final best score
	 */
	double miniMax(Board board, Symbols sym, int currentMove, boolean isMaximizer)
	{
		// check for board terminal state
		if (isBoardTerminalState(board, sym))
			return getScoreForBoard(board, sym, currentMove);

		double bestValue;
		if (isMaximizer)
		{
			// maximizer tries to find the highest score
			// (we play PERFECT, so we take the path of the highest score)
			bestValue = -10000;
			for (Board move : getAllMoves(board, sym.BLANK, sym.SELF))
				bestValue = Math.max(bestValue, miniMax(move, sym, currentMove + 1, false));
		}
		else
		{
			// minimizer tries to find the smallest score
			// (opponent is assumed to play PERFECT, so it takes the path where we get the
			// lowest score; worst- case for us)
			bestValue = 10000;
			for (Board move : getAllMoves(board, sym.BLANK, sym.OPPONENT))
				bestValue = Math.min(bestValue, miniMax(move, sym, currentMove + 1, true));
		}

		return bestValue;
	}

	/**
	 * calculate all possible moves for the player p (p is placed in any possible position)
	 * 
	 * @param baseBoard the base board
	 * @param blank the char for a blank position
	 * @param p the player char to insert
	 * @return
	 */
	ArrayList<Board> getAllMoves(Board baseBoard, char blank, char p)
	{
		// enumerate all positions, place p in every blank spot
		ArrayList<Board> moves = new ArrayList<Board>();
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				if (baseBoard.get(x, y) == blank)
				{
					// this is a blank position, add a copy of the board with that position set to p
					Board mov = baseBoard.clone();
					mov.set(x, y, p);
					moves.add(mov);
				}

		// return all possible boards
		return moves;
	}

	/**
	 * assign a score to a board state
	 * this also takes into account the number of moves it took to get to this board state. 
	 * Less moves to a win and more moves to a loss are preferred.
	 * @param board the board to score
	 * @param sym symbols to use for the board scoring
	 * @param moves how many moves did it take to get to this board?
	 * @return the score for this board
	 */
	double getScoreForBoard(Board board, Symbols sym, int moves)
	{
		if (board.checkWin(sym.SELF))
			return 100 - moves;
		else if (board.checkWin(sym.OPPONENT))
			return -100 + moves;
		else
			return 0;
	}

	/**
	 * check if the board is in a terminal (= game over) state
	 * A Terminal state can be either:
	 * - a win for the bot
	 * - a win for the opponent
	 * - a tie (no moves left)
	 * 
	 * @param board the board to check
	 * @param sym the symbols to use for the board
	 * @return is the board in a terminal state?
	 */
	boolean isBoardTerminalState(Board board, Symbols sym)
	{
		return !board.hasAnyEmptySpace(sym.BLANK) // no empty spaces (tie)
				|| board.checkWin(sym.SELF) // SELF won
				|| board.checkWin(sym.OPPONENT); // OPPONENT won
	}

	@Override
	public String getDisplayName()
	{
		return "Mini-Max";
	}
}
