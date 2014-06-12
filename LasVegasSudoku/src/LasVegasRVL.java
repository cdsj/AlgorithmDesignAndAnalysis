import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/*
 *	Las Vegas Algorithm Randomize among Valid Locations
 *  The algorithm recalculates coordinates if the randomized coordinate for a marker is occupied on the board or if the 
 *  marker fails the validity check.
 */
public class LasVegasRVL
{
	private SudokuBoard sb;
	private int boardSize;
	private MarkerObject marker;
	private Collection<MarkerObject> validLocations;
	private Date startTime;
	private Date endTime;
	private Long numberOfRestarts;
	
	public LasVegasRVL(int boardSize, SudokuBoard sb){
		this.boardSize = boardSize;
		this.sb = sb;
		this.numberOfRestarts=(long) 0;
	}
	
	/*
	 * Places values down, one quadrant at the time. The algorithm collects all valid locations for a specific value within a quadrant and preformes a random selection
	 * among these locations. If no valid places are found, the algorithm starts over from the beginning.
	 */
	public void solveSudoku(){
		startTime = new Date();
		boolean done = false;
		boolean restart=false;
		while(!done)
		{
			sb.clearPlacedMarkersCollection();
			sb.clearArray();
			//addCoordinates();
			for (int valueToPlace = 1; valueToPlace < boardSize*boardSize+1; valueToPlace++)
			{
				for (int y = 0; y < boardSize; y++)
				{
					for (int x = 0; x < boardSize; x++)
					{
						if(sb.isValueNeededInQuadrant(valueToPlace, x, y, boardSize)){
							validLocations = sb.getValidCoordinatesForQuadrant(valueToPlace, x, y, boardSize);
							if(validLocations.size()==0)
							{
								restart=true;
								break;
							}
							else
							{
								marker = pickRandomValidLocation(validLocations);
								sb.placeMarker(marker);
							}
						}
						else
						{
							System.out.println("Not needed for quadrant: "+x+" , "+y);
						}
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
	
	/*
	 * Method is used to add values to the sudoku board before the algorithms are run.
	 */
	public void addCoordinates(){
		sb.addLocationsToSudokuBoard(6, 2, 0);
		sb.addLocationsToSudokuBoard(9, 4, 0);
		sb.addLocationsToSudokuBoard(2, 6, 0);
		sb.addLocationsToSudokuBoard(1, 0, 1);
		sb.addLocationsToSudokuBoard(6, 3, 1);
		sb.addLocationsToSudokuBoard(8, 5, 1);
		sb.addLocationsToSudokuBoard(8, 1, 2);
		sb.addLocationsToSudokuBoard(1, 7, 2);
	}
	
	@SuppressWarnings("null")
	public MarkerObject pickRandomValidLocation(Collection<MarkerObject> validLocations){
		int locationToPic = (int) (Math.random()*validLocations.size());
		int counter=0;
		MarkerObject markerObject=null;
		
		for (Iterator<MarkerObject> iterator = validLocations.iterator(); iterator.hasNext();)
		{
			markerObject = (MarkerObject) iterator.next();
			if(counter==locationToPic)
			{
				break;
			}
			counter++;
		}
		return markerObject;
	}
}
