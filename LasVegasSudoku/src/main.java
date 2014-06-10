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
		LasVegasRO lvr = new LasVegasRO(boardSize, sb);
		LasVegasGS lvgs = new LasVegasGS(boardSize, sb);
		LasVegasSVS lvsvs = new LasVegasSVS(boardSize, sb);
		LasVegasRORI lvrori = new LasVegasRORI(boardSize, sb);
		LasVegasRVL lvrvl = new LasVegasRVL(boardSize, sb);
		
		//lvgs.solveSudoku();
		//lvsvs.solveSudoku();
		//lvr.solveSudoku();
		//lvrori.solveSudoku();
		lvrvl.solveSudoku();
	}

}
