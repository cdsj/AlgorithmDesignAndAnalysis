import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


public class CalculateSudokuStatistics
{
	private SudokuBoard sb;
	private int boardSize;
	private MarkerObject marker;
	private Collection<MarkerObject> markersToPlace;
	private Collection<MarkerObject> markerCollection;
	private Collection<MarkerObject> largestCollection;
	private Long numberOfRestarts;
	private Long numberOfFoundSolutions;
	private Long numberOfInvalidSolutions;
	private Date startTime;
	private Date endTime;
	
	public CalculateSudokuStatistics(int boardSize, SudokuBoard sb){
		this.boardSize = boardSize;
		this.sb = sb;
		this.markerCollection = new ArrayList<MarkerObject>();
		this.numberOfRestarts=(long) 0;
		this.numberOfFoundSolutions=(long)0;
		this.numberOfInvalidSolutions=(long)0;
	}

	public void runStatisticsCheck()
	{
		
		findAllSolutions(markerCollection, 1, 0, 0);
		printBoard(largestCollection);
		System.out.println("Number of invalid solutions: "+numberOfInvalidSolutions);
		System.out.println("Number of found solutions: "+numberOfFoundSolutions);
	}
	
	public void findAllSolutions(Collection<MarkerObject> markersPlaced, int valueToPlace, int xVal, int yVal)
	{
		System.out.println("Value: "+valueToPlace+" , X: "+xVal+" , Y: "+yVal);
		Collection<MarkerObject> placedMarkers = markersPlaced;
		createMarker(valueToPlace,xVal,yVal);
		if(isValidPlacement(marker,placedMarkers)){
			placedMarkers.add(marker);
			
			if(placedMarkers.size()>numberOfFoundSolutions){
				numberOfFoundSolutions=(long) placedMarkers.size();
				largestCollection=placedMarkers;
			}
			
			/*
			 if(placedMarkers.size()==Math.pow(boardSize, 4)&&valueToPlace==(boardSize*boardSize))
			 
				{
					numberOfFoundSolutions++;
				}
			*/
		}
		else if(placedMarkers.size()<Math.pow(boardSize, 4)&&valueToPlace<=(boardSize*boardSize)){
			numberOfInvalidSolutions++;
		}
		if(xVal<(boardSize*boardSize)-1)
		{
			findAllSolutions(placedMarkers, valueToPlace, xVal+1, yVal);
		}
		if(yVal<(boardSize*boardSize)-1)
		{
			findAllSolutions(placedMarkers, valueToPlace, xVal, yVal+1);
		}
		if(valueToPlace<(boardSize))
		{
			findAllSolutions(placedMarkers, valueToPlace+1, xVal, yVal);
		}
	}

	@SuppressWarnings("null")
	public void printBoard(Collection<MarkerObject>markers)
	{
		Integer[][] sudokuBoard = new Integer[boardSize*boardSize][boardSize*boardSize]; 
		
		for (Iterator iterator = markers.iterator(); iterator.hasNext();)
		{
			MarkerObject markerObject = (MarkerObject) iterator.next();
			sudokuBoard[markerObject.getyVal()][markerObject.getxVal()]=markerObject.getValue();
		}
		
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
	}
	
	public boolean isValidPlacement(MarkerObject marker,Collection<MarkerObject>placedMarkers){
		
		for (Iterator<MarkerObject> markerInterator = placedMarkers.iterator(); markerInterator.hasNext();)
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
	 * Tries to place all ones, then all twos and so on in increasing order. If the program tries to place a number
	 * in an invalid slot, it fails and starts over from the beginning 
	 */
	public void solveSudoku(){
		startTime = new Date();
		boolean done = false;
		boolean restart=false;
		while(!done)
		{
			sb.clearPlacedMarkersCollection();
			sb.clearArray();
			for (int valueToPlace = 1; valueToPlace < boardSize*boardSize+1; valueToPlace++)
			{
				for (int y = 0; y < boardSize; y++)
				{
					for (int x = 0; x < boardSize; x++)
					{
						createMarker(valueToPlace,x,y);
						if(sb.isValidPlacement(marker)){
							sb.placeMarker(marker);
						}
						else{
							numberOfInvalidSolutions++;
							restart=true;
							break;
						}
					}
					if(restart)
					{
						break;
					}
				}
				
				if(restart)
				{
					numberOfRestarts++;
					restart=false;
					break;
				}
			}
			if(sb.isBoardFilled())
			{
				done=true;
			}
		}
		endTime = new Date();
		sb.printBoard();
		System.out.println("---------------------");
		System.out.println("DONE");
		System.out.println("Total Run Time: "+(endTime.getTime()-startTime.getTime())+" ms");
		System.out.println("Total Number of Restarts: "+numberOfRestarts);
	}
	
	@SuppressWarnings("null")
	public MarkerObject createMarker(int value, int xVal, int yVal){
		marker = new MarkerObject(value,xVal,yVal);	
		return marker;
	}
}
