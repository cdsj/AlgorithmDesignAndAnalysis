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
	
	public void placeMarker(MarkerObject marker){
		allPlacementsCollection.add(marker);
		
		for (Iterator<MarkerObject>  marks = allPlacementsCollection.iterator(); marks.hasNext();)
		{
			MarkerObject mark = (MarkerObject) marks.next();
			sudokuBoard[mark.getyVal()][mark.getxVal()]=mark.getValue();
		}
		
		// Print the closest soultion.
		if(allPlacementsCollection.size()>indicator){
			indicator=allPlacementsCollection.size();
			System.out.println();
			printBoard();
			System.out.println();
			System.out.println("Placed numbers: "+indicator);
			System.out.println("------------------");
		}
		
	}
	
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

	public void clearPlacedMarkersCollection()
	{
		allPlacementsCollection.clear();
	}
	
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
