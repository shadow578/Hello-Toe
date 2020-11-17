package tic;

import java.util.Scanner;

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
	 * global scanner for console input
	 */
	public static Scanner consoleIn;

	/**
	 * main entry point of the app
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		// create scanner for console
		consoleIn = new Scanner(System.in);

		// create new game and player instances
		TicTacToeGame game = new TicTacToeGame();
		InteractivePlayer player = new InteractivePlayer();

		// choose a difficulty level
		int difficultyChoice;
		do
		{
			System.out.printf("choose a difficulty level:%n [1] Easy%n [2] Hard%n [3] Two- Player%n> ");
			difficultyChoice = consoleIn.nextInt();
		} while (difficultyChoice < 1 || difficultyChoice > 3);

		// start the game with difficutlty
		switch (difficultyChoice)
		{
		case 1:
			// easy, DummyBot
			match(game, player, new DummyBot());
			break;
		case 2:
			// hard, MaxBot
			match(game, player, new MaxBot());
			break;
		case 3:
			// two- player
			match(game, player, new InteractivePlayer());
			break;
		}

		// close scanner
		consoleIn.close();
	}

	/**
	 * match to players in a game of tic tac toe
	 * @param game the game instance to use
	 * @param p1 the first player
	 * @param p2 the second player
	 */
	private static void match(TicTacToeGame game, Player p1, Player p2)
	{
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
