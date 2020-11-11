package tic.players;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import tic.tac.GameUtil;
import tic.tac.Player;
import tic.tac.toe.Position;
import tic.tac.toe.Symbols;

/**
 * TODO add high IQ description
 * 
 * a bot that uses minimax algorithm to always win
 */
public class MaxBot extends Player
{
	class ScoredPosition
	{
		public final Position pos;
		
		public final double score;
		
		public ScoredPosition(double s, int x, int y)
		{
			pos = new Position(x, y);
			score = s;
		}
	}
	
	@Override
	public Position act(char[][] board, Symbols symbols, Random random)
	{		
		//test all possible moves using minimax
		ArrayList<ScoredPosition> moves = new ArrayList<ScoredPosition>();
		for (int x = 0; x < board.length; x++)
			for (int y = 0; y < board[x].length; y++)
				if (board[x][y] == symbols.BLANK)
				{
					// this is a blank position, add a copy of the board with that position set to p
					char[][] mov = GameUtil.copyBoard(board);
					mov[x][y] = symbols.SELF;
					
					//calculate score for move and add to list
					double score = miniMax(mov, symbols, 0, true);
					moves.add(new ScoredPosition(score, x, y));
				}
		
		//find move that scored highest
		moves.sort(new Comparator<ScoredPosition>()
		{
			@Override
			public int compare(ScoredPosition a, ScoredPosition b)
			{
				return Double.compare(a.score, b.score);
			}
		});
		
		//take the first move
		ScoredPosition m = moves.get(0);
		System.out.printf("taking move x= %d y= %d with score %f", m.pos.x, m.pos.y, m.score);
		return m.pos;
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
	double miniMax(char[][] board, Symbols sym, int currentMove, boolean isMaximizer)
	{
		// check for board terminal state (= no moves left)
		if (!GameUtil.hasEmptySpaces(board, sym.BLANK))
			return getScoreForBoard(board, sym, currentMove);
		
		double bestValue;
		if (isMaximizer)
		{
			// maximizer tries to find the highest score
			// (we play PERFECT, so we take the path of the highest score)
			bestValue = Double.MIN_VALUE;
			for (char[][] move : getAllMoves(board, sym.BLANK, sym.SELF))
				bestValue = Math.max(bestValue, miniMax(move, sym, currentMove + 1, false));
		}
		else
		{
			// minimizer tries to find the smallest score
			// (opponent is assumed to play PERFECT, so it takes the path where we get the
			// lowest score; worst- case for us)
			bestValue = Double.MAX_VALUE;
			for (char[][] move : getAllMoves(board, sym.BLANK, sym.SELF))
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
	ArrayList<char[][]> getAllMoves(char[][] baseBoard, char blank, char p)
	{
		// enumerate all positions, place p in every blank spot
		ArrayList<char[][]> boards = new ArrayList<char[][]>();
		for (int x = 0; x < baseBoard.length; x++)
			for (int y = 0; y < baseBoard[x].length; y++)
				if (baseBoard[x][y] == blank)
				{
					// this is a blank position, add a copy of the board with that position set to p
					char[][] board = GameUtil.copyBoard(baseBoard);
					board[x][y] = p;
					boards.add(board);
				}

		// return all possible boards
		return boards;
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
	double getScoreForBoard(char[][] board, Symbols sym, int moves)
	{
		if (GameUtil.hasWon(board, sym.SELF))
			return 10 - moves;
		else if (GameUtil.hasWon(board, sym.OPPONENT))
			return -10 + moves;
		else
			return 0;
	}

	@Override
	public String getDisplayName()
	{
		return "Max";
	}
}
