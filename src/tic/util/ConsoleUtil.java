package tic.util;

import tic.tac.toe.Board;

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
	public static void DrawGameBoard(Board board)
	{
		for (int x = 0; x < 3; x++)
		{
			for (int y = 0; y < 3; y++)
			{
				// only draw a pipe if not the last collumn
				boolean drawPipe = y < 2;

				String draw = " " + board.get(x, y);

				if (drawPipe)
					draw += " |";

				// draw to console
				System.out.print(draw);
			}

			System.out.println();

			if (x < 2)
				System.out.println("-----------");
		}
	}
}
