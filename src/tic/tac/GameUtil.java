package tic.tac;

import java.security.InvalidParameterException;

public class GameUtil
{
	/**
	 * check if the player with symbol p (X or O has won
	 * 
	 * winning situations are:
	 * a | b | c
	 * d | e | f
	 * g | h | i
	 * 
	 * (horizontal)
	 * - abc
	 * - def
	 * - ghi
	 * (vertical)
	 * - adg
	 * - beh
	 * - cfi
	 * (diagonal)
	 * - aei
	 * - ceg
	 * @param board the board to check on (3x3)
	 * @param p the player to check for
	 * @return is this player in a winning situation
	 */
	public static boolean hasWon(char[][] board, char p)
	{
		// check board is 3x3
		if (board.length < 3
				|| board[0].length < 3
				|| board[1].length < 3
				|| board[2].length < 3)
			throw new InvalidParameterException("board has to be 3x3");

		// check all combinations
		return checkL(board, p, 0, 0, 1, 0, 2, 0) // abc
				|| checkL(board, p, 0, 1, 1, 1, 2, 1) // def
				|| checkL(board, p, 0, 2, 1, 2, 2, 2) // ghi
				|| checkL(board, p, 0, 0, 0, 1, 0, 2) // adg
				|| checkL(board, p, 1, 0, 1, 1, 1, 2) // beh
				|| checkL(board, p, 2, 0, 2, 1, 2, 2) // cfi
				|| checkL(board, p, 0, 0, 1, 1, 2, 2) // aei
				|| checkL(board, p, 2, 0, 1, 1, 0, 2);// ceg
	}

	/**
	 * check tree positions of the game board for the given character c
	 * @param board the board to check on (3x3)
	 * @param c the character to check for
	 * @param ax first x position
	 * @param ay first y position
	 * @param bx second x position
	 * @param by second y position
	 * @param cx third x position
	 * @param cy third y position
	 * @return are all three positions equal to c?
	 */
	private static boolean checkL(char[][] board, char c, int ax, int ay, int bx, int by, int cx, int cy)
	{
		return board != null
				&& board[ax][ay] == c
				&& board[bx][by] == c
				&& board[cx][cy] == c;
	}

	/**
	 * check if there are any empty spaces left
	 * @param board the board to check on (3x3)
	 * @param blank the symbol for a blank space
	 * @return are there any empty spaces left?
	 */
	public static boolean hasEmptySpaces(char[][] board, char blank)
	{
		// check board is 3x3
		if (board.length < 3
				|| board[0].length < 3
				|| board[1].length < 3
				|| board[2].length < 3)
			throw new InvalidParameterException("board has to be 3x3");
		
		//check empty spaces
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				if (board[x][y] == blank)
					return true;
		return false;
	}

	/**
	 * create a exact copy of the given board
	 * @param board the board to copy
	 * @return the copy of the board
	 */
	public static char[][] copyBoard(char[][] board)
	{
		// check board is 3x3
		if (board.length < 3
				|| board[0].length < 3
				|| board[1].length < 3
				|| board[2].length < 3)
			throw new InvalidParameterException("board has to be 3x3");
		
		//create new board
		char[][] copy = new char[3][3];
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++)
				copy[x][y] = board[x][y];
		
		return copy;
	}
}
