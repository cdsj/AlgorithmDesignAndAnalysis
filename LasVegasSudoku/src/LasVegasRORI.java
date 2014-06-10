import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/*
 *  Las Vegas Algorithm with Relocation on Occupancy or Invalid Placement
 *  The algorithm recalculates coordinates if the randomized coordinate for a marker is occupied on the board or if the 
 *  marker fails the validity check.
 *  
 *  The Algorithm will get stuck if no possible solution exists with the placed markers
 */
public class LasVegasRORI
{
	private SudokuBoard sb;
	private int boardSize;
	private MarkerObject marker;
	private Collection<MarkerObject> markersToPlace;
	private Collection<MarkerObject> markerCollection;
	private Date startTime;
	private Date endTime;
	
	public LasVegasRORI(int boardSize, SudokuBoard sb){
		this.boardSize = boardSize;
		this.sb = sb;
		this.markerCollection = new ArrayList<MarkerObject>();
	}

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
						while(!sb.isValidPlacement(marker)){
							marker = createMarker(valueToPlace,x,y);
						}
						sb.placeMarker(marker);
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
