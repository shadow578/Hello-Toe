package tic.players;

import java.util.Random;

import tic.App;
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
	 * the name entered by the player
	 */
	String name = "";

	@Override
	public void init(char symbol)
	{
		// ask for name
		System.out.printf("Player [%c], please enter your name: ", symbol);
		do
		{
			name = App.consoleIn.nextLine();
		} while (name.isBlank() || name.isEmpty());
	}

	@Override
	public Position act(Board board, Symbols symbols, Random random)
	{
		// clear console first
		ConsoleUtil.ClearConsole();

		// write player names and symbol info
		System.out.printf("%n%s's Turn%nYou play as %s%n%n", name, symbols.SELF);

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
		int n = App.consoleIn.nextInt() - 1;

		// get x/y position from n
		int y = n % 3;
		int x = n / 3;
		System.out.printf("x= %d, y= %d%n", x, y);
		return new Position(x, y);
	}

	@Override
	public String getDisplayName()
	{
		return name;
	}

}
