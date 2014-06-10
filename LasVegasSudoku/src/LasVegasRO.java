
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


/*
 * Las Vegas Algorithm with Relocation on Occupancy
 * The algorithm recalculates coordinates if the randomized coordinate for a marker is occupied on the board
 * The algorithm starts over if the randomized location of a marker is free but invalid for the specific value
 */
public class LasVegasRO
{
	private SudokuBoard sb;
	private int boardSize;
	private MarkerObject marker;
	private Collection<MarkerObject> markersToPlace;
	private Collection<MarkerObject> markerCollection;
	private Long numberOfRestarts;
	private Date startTime;
	private Date endTime;
	
	public LasVegasRO(int boardSize, SudokuBoard sb){
		this.boardSize = boardSize;
		this.sb = sb;
		this.markerCollection = new ArrayList<MarkerObject>();
		this.numberOfRestarts=(long)0;
	}

	/*
	 * Tries to place all ones, then all twos and so on in increasing order. 
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
						marker = createMarker(valueToPlace,x,y);
						if(sb.isValidPlacement(marker)){
							sb.placeMarker(marker);
						}
						else{
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
					restart=false;
					break;
				}
			}
			if(sb.isBoardFilled())
			{
				done=true;
				System.out.println("DONE");
			}
		}
		endTime = new Date();
		System.out.println("Total Run Time: "+(endTime.getTime()-startTime.getTime())+" ms");
		System.out.println("Total Number of Restarts: "+numberOfRestarts);
	}
	
	@SuppressWarnings("null")
	public MarkerObject createMarker(int value, int xVal, int yVal){
		int xCoordinate = (int) (Math.random()*(boardSize)+xVal*boardSize);
		int yCoordinate = (int) (Math.random()*(boardSize)+yVal*boardSize);
		
		//While the randomized coordinates are occupied within the sudoku board, new coordinates are generated
		while(!sb.isSlotEmpty(xCoordinate, yCoordinate))
		{
			xCoordinate = (int) (Math.random()*(boardSize)+xVal*boardSize);
			yCoordinate = (int) (Math.random()*(boardSize)+yVal*boardSize);
		}
		
		return new MarkerObject(value,xCoordinate,yCoordinate);	
	}

}
