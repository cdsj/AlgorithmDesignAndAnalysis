import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/*
 * The algorithm randomizes the coordinates for a single marker at a time and checks if it can be placed
 * The algorithm starts over if the picked coordinates are occupied or if the slot is invalid for the specific value
 */
public class LasVegasSVS
{
	private SudokuBoard sb;
	private int boardSize;
	private MarkerObject marker;
	private Collection<MarkerObject> markersToPlace;
	private Collection<MarkerObject> markerCollection;
	private Long numberOfRestarts;
	private Date startTime;
	private Date endTime;
	
	public LasVegasSVS(int boardSize, SudokuBoard sb){
		this.boardSize = boardSize;
		this.sb = sb;
		this.markerCollection = new ArrayList<MarkerObject>();
		this.numberOfRestarts=(long) 0;
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
		int xCoordinate = (int) (Math.random()*(boardSize)+xVal*boardSize);
		int yCoordinate = (int) (Math.random()*(boardSize)+yVal*boardSize);
		marker = new MarkerObject(value,xCoordinate,yCoordinate);	
		return marker;
	}
}
