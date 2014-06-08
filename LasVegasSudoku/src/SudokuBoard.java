import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class SudokuBoard
{
	
	private int boardSize;
	private Integer[][] sudokuBoard;
	private Collection<MarkerObject> allPlacementsCollection;
	private int indicator=0;
	
	public SudokuBoard(int boardSize){
		this.boardSize= boardSize;
		this.allPlacementsCollection = new ArrayList<MarkerObject>();
		this.sudokuBoard = new Integer[boardSize*boardSize][boardSize*boardSize];
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
			sudokuBoard[mark.getxVal()][mark.getyVal()]=mark.getValue();
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
	
	public boolean isSlotEmpty(int xCoord, int yCoord)
	{
		if(sudokuBoard[xCoord][yCoord]!=0){
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
		System.out.println();
		
		for (Iterator<MarkerObject> iterator = allPlacementsCollection.iterator(); iterator.hasNext();)
		{
			MarkerObject type = (MarkerObject) iterator.next();
			System.out.println("Value: "+type.getValue()+" coord: "+type.getxVal()+" , "+type.getyVal());
		}
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
