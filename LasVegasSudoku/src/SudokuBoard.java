import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JTextPane;


public class SudokuBoard
{
	
	private int boardSize;
	private Integer[][] sudokuBoard;
	private Collection<MarkerObject> allPlacementsCollection;
	private Collection<MarkerObject> validPlacesCollection;
	private int indicator=0;
	
	public SudokuBoard(int boardSize){
		this.boardSize= boardSize;
		this.allPlacementsCollection = new ArrayList<MarkerObject>();
		this.sudokuBoard = new Integer[boardSize*boardSize][boardSize*boardSize];
		this.validPlacesCollection = new ArrayList<MarkerObject>();
	}
	
	/*
	 * Checks if a specific marker fulfills the rules of the sudoku board
	 */
	public boolean isValidPlacement(MarkerObject marker){
		
		for (Iterator<MarkerObject> markerInterator = allPlacementsCollection.iterator(); markerInterator.hasNext();)
		{
			MarkerObject mark = (MarkerObject) markerInterator.next();
			
			if(mark.getxVal()==marker.getxVal()&&mark.getValue()==marker.getValue()){
				return false;
			}
			
			if(mark.getyVal()==marker.getyVal()&&mark.getValue()==marker.getValue()){
				return false;
			}
			
			if(mark.getyVal()==marker.getyVal()&&mark.getxVal()==marker.getxVal()){
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * Finds and returns all valid locations for a specific values within a certain quadrant on the sudoku board. 
	 * A set of markers with valid coordinates is returned
	 */
	public Collection<MarkerObject> getValidCoordinatesForQuadrant(int value,int xVal,int yVal,int boardSize){
		validPlacesCollection.clear();
		for (int i = xVal*boardSize; i < (boardSize)+xVal*boardSize; i++)
		{
			for (int j = yVal*boardSize; j < (boardSize)+yVal*boardSize; j++)
			{
				MarkerObject m = new MarkerObject(value, i, j);
				if(isValidPlacement(m)){
					validPlacesCollection.add(m);
				}
			}
		}
		return validPlacesCollection;
	}
	
	/*
	 * Checks if a specific value is needed in a specific quadrant
	 */
	public boolean isValueNeededInQuadrant(int value,int xVal,int yVal,int boardSize)
	{
		for (int i = xVal*boardSize; i < (boardSize)+xVal*boardSize; i++)
		{
			for (int j = yVal*boardSize; j < (boardSize)+yVal*boardSize; j++)
			{
				if(sudokuBoard[j][i]==value){
					return false;
				}
			}
		}
		return true;
	}
	
	/*
	 * Checks if all slots on the sudoku board is filled
	 */
	public boolean isBoardFilled()
	{
		if(allPlacementsCollection.size()>=(Math.pow(boardSize, 4)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*
	 * Places marker on the sudoku board
	 */
	public void placeMarker(MarkerObject marker){
		allPlacementsCollection.add(marker);
		
		for (Iterator<MarkerObject>  marks = allPlacementsCollection.iterator(); marks.hasNext();)
		{
			MarkerObject mark = (MarkerObject) marks.next();
			sudokuBoard[mark.getyVal()][mark.getxVal()]=mark.getValue();
		}
		
		// Print the closest solution.
		if(allPlacementsCollection.size()>indicator){
			indicator=allPlacementsCollection.size();
			System.out.println();
			printBoard();
			System.out.println();
			System.out.println("Placed numbers: "+indicator);
			System.out.println("------------------");
		}
	}
	
	/*
	 * Adds preset values to the sudoku board. This method is used if the user would like to have a some values on the sudoku board before running the algorithms.
	 */
	public void addLocationsToSudokuBoard(int value, int xVal, int yVal)
	{
		if(xVal<=(boardSize*boardSize) && yVal<=(boardSize*boardSize)){
			placeMarker(new MarkerObject(value, xVal, yVal));
		}
	}
	
	public boolean isSlotEmpty(int xCoord, int yCoord)
	{
		if(sudokuBoard[yCoord][xCoord]!=0){
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/*
	 * Prints a representation of the sudoku board within the console
	 */
	public void printBoard(){
		for (int i = 0; i < boardSize*boardSize; i++)
		{
			for (int j = 0; j < boardSize*boardSize; j++)
			{
				if(j%boardSize==0&&j!=0)
				{
					System.out.print("  ");
				}
				if(i%boardSize==0&&j==0)
				{
					System.out.println();
				}
				System.out.print(sudokuBoard[i][j]);
				
			}
			System.out.println();
		}
		
		/*
		System.out.println();
		for (Iterator<MarkerObject> iterator = allPlacementsCollection.iterator(); iterator.hasNext();)
		{
			MarkerObject type = (MarkerObject) iterator.next();
			System.out.println("Value: "+type.getValue()+" coord: "+type.getxVal()+" , "+type.getyVal());
		}
		*/
	}

	/*
	 * Clears all placed markers from the collection containing all placed markers on the sudoku board 
	 */
	public void clearPlacedMarkersCollection()
	{
		allPlacementsCollection.clear();
	}
	
	/*
	 * Clears the array used to print out the sudoku board of all values
	 */
	public void clearArray(){
		for (int i = 0; i < boardSize*boardSize; i++)
		{
			for (int j = 0; j < boardSize*boardSize; j++)
			{
				sudokuBoard[i][j]=0;
			}
		}
	}
	
}
