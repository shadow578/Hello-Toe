package tic;

import tic.players.DummyBot;
import tic.players.InteractivePlayer;
import tic.players.MaxBot;
import tic.tac.Player;
import tic.tac.TicTacToeGame;
import tic.tac.toe.MatchResult;
import tic.util.ConsoleUtil;

public class App
{
	/**
	 * main entry point of the app
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		// create new game and player instances
		TicTacToeGame game = new TicTacToeGame();
		InteractivePlayer player = new InteractivePlayer();
		DummyBot dummy = new DummyBot();
		MaxBot max = new MaxBot();

		// player vs dummy
		// match(game, player, dummy);

		// player vs max
		//match(game, player, max);

		// dummy vs max
		// match(game, dummy, max);
		
		// max vs max
		MaxBot max2 = new MaxBot();
		int winsMax1 = 0, winsMax2 = 0, draws = 0;
		for (int i = 0; i < 1000; i++)
		{
			System.out.println(i);
			MatchResult r = game.match(max, max2);
			if (r.wasDraw)
				draws++;
			else if (r.winner.equals(max))
				winsMax1++;
			else
				winsMax2++;
		}
		
		System.out.printf("Max 1 won %d times%nMax 2 won %d times%n%d Ties%nWin Ratio %.2f (should close to 50%%)%n",
				winsMax1,
				winsMax2,
				draws,
				(double) winsMax1 / (double) winsMax2);
	}

	/**
	 * match to players in a game of tic tac toe
	 * @param game the game instance to use
	 * @param p1 the first player
	 * @param p2 the second player
	 */
	private static void match(TicTacToeGame game, Player p1, Player p2)
	{
		System.out.printf("%s vs %s%n", p1.getDisplayName(), p2.getDisplayName());
		MatchResult result = game.match(p1, p2);
		printResult(result);
	}

	/**
	 * print the result of a match to sysout
	 * @param result the result to print
	 */
	private static void printResult(MatchResult result)
	{
		System.out.printf("%n%nResult of match between %s and %s:%n"
				+ " Winner is %s%n"
				+ " Looser is %s%n"
				+ " Final Board: %n",
				result.players[0].getDisplayName(), result.players[1].getDisplayName(),
				result.wasDraw ? "DRAW" : result.winner.getDisplayName(),
				result.wasDraw ? "DRAW" : result.looser.getDisplayName());
		ConsoleUtil.DrawGameBoard(result.finalBoard);
	}
}
