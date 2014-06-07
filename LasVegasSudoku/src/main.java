import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;


public class main
{
	private static MarkerObject marker;
	private static int boardSize;
	private static Integer[][] sudokuBoard;
	private static Collection<MarkerObject> markerCollection = new ArrayList<MarkerObject>();
	public static void main(String[] args)
	{
		boardSize= 2;
		Collection<MarkerObject> allPlacementsCollection = new ArrayList<MarkerObject>();
		sudokuBoard = new Integer[boardSize*boardSize][boardSize*boardSize];
		runSudoku(allPlacementsCollection);
		System.out.println("DONE");
		//printBoard();
		

	}
	
	public static void clearArray(){
		for (int i = 0; i < boardSize*boardSize; i++)
		{
			for (int j = 0; j < boardSize*boardSize; j++)
			{
				sudokuBoard[i][j]=0;
			}
		}
	}
	public static void runSudoku(Collection<MarkerObject> allPlacementsCollection){
		
		boolean done = false;
		boolean restart=false;
		while(!done)
		{
			allPlacementsCollection.clear();
			clearArray();
			for (int valueToPlace = 1; valueToPlace < boardSize*boardSize+1; valueToPlace++)
			{
				System.out.println("VAL2: "+valueToPlace);
				for (Iterator<MarkerObject> markersToPlace = createMarkers(valueToPlace).iterator(); markersToPlace.hasNext();)
				{
					MarkerObject marker = (MarkerObject) markersToPlace.next();
					if(isValidPlacement(marker, allPlacementsCollection)){
						placeMarker(marker,allPlacementsCollection);
						
					}
					else{
						//System.out.println("I Start Over");
						//System.out.println("**************************************");
						
						restart=true;
						break;
					}
				}
				/*
				 * HÄÄÄÄÄÄÄÄÄÄÄR ÄR DET FEEEEL!
				 */
				if(restart==true)
				{
					break;
				}
			}
			System.out.println(allPlacementsCollection.size());
			if(allPlacementsCollection.size()>=5)
			{
				done=true;
				printBoard(allPlacementsCollection);
				//(Math.pow(boardSize, 1))
			}
		}
	}
	
	public static boolean isValidPlacement(MarkerObject marker, Collection<MarkerObject> allPlacementsCollection){
		
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
			
	/*	if(!(sBoard[xCoord][yCoord]==null)){
			return false;
		}
		
		for (int x = 0; x < boardSize*boardSize; x++)
		{
			if(sBoard[x][yCoord]==valueToBePlaced){
				return false;
			}
		}
		
		for (int y = 0; y < boardSize*boardSize; y++)
		{
			if(sBoard[xCoord][y]==valueToBePlaced){
				return false;
			}
		}
		
		return true;
		
		*/
	}
	
	public static void placeMarker(MarkerObject marker, Collection<MarkerObject> allPlacementsCollection){
		allPlacementsCollection.add(marker);
		
		for (Iterator<MarkerObject>  marks = allPlacementsCollection.iterator(); marks.hasNext();)
		{
			MarkerObject mark = (MarkerObject) marks.next();
			sudokuBoard[mark.getxVal()][mark.getyVal()]=mark.getValue();
		}
		
		//printBoard();
		
	}
	
	public static void printBoard(Collection<MarkerObject> allPlacementsCollection){
		for (int i = 0; i < boardSize*boardSize; i++)
		{
			for (int j = 0; j < boardSize*boardSize; j++)
			{
				System.out.print(sudokuBoard[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		for (Iterator iterator = allPlacementsCollection.iterator(); iterator.hasNext();)
		{
			MarkerObject type = (MarkerObject) iterator.next();
			System.out.println("Value: "+type.getValue()+" coord: "+type.getxVal()+" , "+type.getyVal());
		}
		
		System.out.println("SIZE: "+allPlacementsCollection.size());
	}
	
	@SuppressWarnings("null")
	public static Collection<MarkerObject> createMarkers(int value){
		markerCollection.clear();
		System.out.println("Value: "+value);
		for (int x = 0; x < boardSize; x++)
		{
			for (int y = 0; y < boardSize; y++)
			{
				marker = new MarkerObject(value, (int) (Math.random()*(boardSize)+x*boardSize), (int) (Math.random()*(boardSize)+y*boardSize));	
				markerCollection.add(marker);
			}
		}
		return markerCollection;
	}

}
