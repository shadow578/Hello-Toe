package tic.players;

import java.util.Random;

import tic.tac.Player;
import tic.tac.toe.Position;
import tic.tac.toe.Symbols;

/**
 * Meet Dummy, the most inteligent bot on earth - not
 * 
 * Dummy uses the {@code Random} passed to {@code Player.act} to choose a random, blank position to take.
 */
public class DummyBot extends Player
{
	@Override
	public Position act(char[][] board, Symbols sym, Random random)
	{
		// find a random space that is still blank, 1000 tries
		for(int t = 0; t < 1000; t++)
		{
			// get random position
			System.out.print(".");
			int x = random.nextInt(3),
					y = random.nextInt(3);

			// check that position is blank
			if (board[y][x] == sym.BLANK)
				return new Position(x, y);
		}
		
		//fallback: get the first possible location
		for(int x = 0; x < board.length; x++)
			for(int y = 0; y < board[x].length; y++)
				if(board[x][y] == sym.BLANK)
					return new Position(x, y);
		return null;
	}

	@Override
	public String getDisplayName()
	{
		return "Dummy 🎉";
	}
}
