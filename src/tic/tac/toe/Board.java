package tic.tac.toe;

public class Board
{
	/**
	 * the internal tic-tac-toe board
	 * char[x][y]
	 * 
	 * 0,0 | 1,0 | 2,0
	 * 0,1 | 1,1 | 2,1
	 * 0,2 | 1,2 | 2,2
	 */
	protected char[][] board = new char[3][3];

	/**
	 * initialize a new board without resetting the spaces to all blanks
	 */
	protected Board() {
		
	}
	
	/**
	 * initialize a new board and reset all spaces to blank
	 * @param blank the char to use as a blank space
	 */
	public Board(char blank)
	{
		resetBoard(blank);
	}

	/**
	 * create a exact copy of the this board
	 * @return the copy of this board
	 */
	public Board clone()
	{
		// copy internal board to the internal board of a new instance
		Board b = new Board();
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				b.board[x][y] = board[x][y];

		return b;
	}

	/**
	 * get the char at the given x/y position
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the char at that position
	 */
	public char get(int x, int y)
	{
		// check bounds
		if (x < 0 || x > 3 || y < 0 || x > 3)
			throw new IndexOutOfBoundsException("x or y was out of bounds!");

		// return char at that position
		return board[x][y];
	}

	/**
	 * set the char at the given x/y position
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param c the char to set
	 */
	public void set(int x, int y, char c)
	{
		// check bounds
		if (x < 0 || x > 3 || y < 0 || x > 3)
			throw new IndexOutOfBoundsException("x or y was out of bounds!");

		// set char
		board[x][y] = c;
	}

	/**
	 * check if the board has empty spaces left
	 * @param blank the symbol to use as blank space
	 * @return are there any empty spaces left?
	 */
	public boolean hasAnyEmptySpace(char blank)
	{
		// check empty spaces
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				if (get(x, y) == blank)
					return true;
		return false;
	}

	/**
	 * check if the player with symbol p (X or O) has won
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
	 * @param p the player to check for
	 * @return is this player in a winning situation?
	 */
	public boolean checkWin(char p)
	{
		// check all combinations
		return checkL(p, 0, 0, 1, 0, 2, 0) // abc
				|| checkL(p, 0, 1, 1, 1, 2, 1) // def
				|| checkL(p, 0, 2, 1, 2, 2, 2) // ghi
				|| checkL(p, 0, 0, 0, 1, 0, 2) // adg
				|| checkL(p, 1, 0, 1, 1, 1, 2) // beh
				|| checkL(p, 2, 0, 2, 1, 2, 2) // cfi
				|| checkL(p, 0, 0, 1, 1, 2, 2) // aei
				|| checkL(p, 2, 0, 1, 1, 0, 2);// ceg
	}

	/**
	 * check three positions of the game board for the given character c
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
	private boolean checkL(char c, int ax, int ay, int bx, int by, int cx, int cy)
	{
		return get(ax, ay) == c
				&& get(bx, by) == c
				&& get(cx, cy) == c;
	}

	/**
	 * reset the game board to all empty spaces
	 * @param blank the char to use as a blank char
	 */
	private void resetBoard(char blank)
	{
		// set all cells to empty
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				set(x, y, blank);
	}

	@Override
	public String toString()
	{
		return board.toString();
	}
}
