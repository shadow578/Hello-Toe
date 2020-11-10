package tic.util;

public class ConsoleUtil
{
	/**
	 * Clear the console. Only works in CMD and Terminal, not eclipse integrated console
	 */
	public static void ClearConsole()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/**
	 * draw a tic tac toe board to the console using System.out
	 * @param board the board to draw
	 */
	public static void DrawGameBoard(char[][] board)
	{
		for (int x = 0; x < board.length; x++)
		{
			for (int y = 0; y < board[x].length; y++)
			{
				// only draw a pipe if not the last collumn
				boolean drawPipe = y < (board[x].length - 1);

				String draw = " " + board[x][y];

				if (drawPipe)
					draw += " |";

				// draw to console
				System.out.print(draw);
			}

			System.out.println();

			if (x < (board.length - 1))
				System.out.println("-----------");
		}
	}
}
