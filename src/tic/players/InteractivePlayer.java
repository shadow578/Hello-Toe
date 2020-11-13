package tic.players;

import java.util.Random;
import java.util.Scanner;

import tic.tac.Player;
import tic.tac.toe.Board;
import tic.tac.toe.Position;
import tic.tac.toe.Symbols;
import tic.util.ConsoleUtil;

/**
 * A Interactive Player - meaning the bot is you, the player. I/O using the console
 */
public class InteractivePlayer extends Player
{
	/**
	 * console input scanner
	 */
	Scanner in;

	@Override
	public void init()
	{
		in = new Scanner(System.in);
	}

	@Override
	public Position act(Board board, Symbols symbols, Random random)
	{
		// clear console first
		ConsoleUtil.ClearConsole();

		// write player names and symbol info
		System.out.printf("%nYou are %s%nYour Opponent is %s%n%n", symbols.SELF, symbols.OPPONENT);

		// draw the board, but replace blanks with numbers
		for (int x = 0; x < 3; x++)
		{
			for (int y = 0; y < 3; y++)
			{
				// only draw a pipe if not the last collumn
				boolean drawPipe = y < 2;

				// draw numbers instead of blanks
				char cell = board.get(x, y);
				String draw = " ";
				if (cell == symbols.BLANK)
					draw += (y + (3 * x)) + 1;
				else
					draw += cell;

				if (drawPipe)
					draw += " |";

				// draw to console
				System.out.print(draw);
			}

			System.out.println();

			if (x < 2)
				System.out.println("-----------");
		}

		// get number to take from player
		System.out.println();
		System.out.print("Enter position to take: ");
		int n = in.nextInt() - 1;

		// get x/y position from n
		int y = n % 3;
		int x = n / 3;
		System.out.printf("x= %d, y= %d%n", x, y);
		return new Position(x, y);
	}

	@Override
	public String getDisplayName()
	{
		return "Interactive Player";
	}

}
