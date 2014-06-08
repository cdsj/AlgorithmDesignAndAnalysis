import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;


public class main
{
	
	public static void main(String[] args)
	{
		int boardSize = 2;
		SudokuBoard sb = new SudokuBoard(boardSize);
		LasVegasWPM lvwpm = new LasVegasWPM(boardSize, sb);
		PureLasVegas plv = new PureLasVegas(boardSize, sb);
		
		//plv.solveSudoku();
		lvwpm.solveSudoku();
		
	}

}
