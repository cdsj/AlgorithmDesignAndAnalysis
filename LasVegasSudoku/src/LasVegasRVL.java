import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

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
	private Collection<MarkerObject> markersToPlace;
	private Collection<MarkerObject> markerCollection;
	private Collection<MarkerObject> validLocations;
	private Date startTime;
	private Date endTime;
	private Long numberOfRestarts;
	
	public LasVegasRVL(int boardSize, SudokuBoard sb){
		this.boardSize = boardSize;
		this.sb = sb;
		this.markerCollection = new ArrayList<MarkerObject>();
		this.numberOfRestarts=(long) 0;
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
						validLocations = sb.getValidCoordinatesForQuadrant(valueToPlace, x, y, boardSize);
						
						for (Iterator<MarkerObject> iterator = validLocations.iterator(); iterator
								.hasNext();)
						{
							MarkerObject mark = (MarkerObject) iterator.next();
						}
						
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
				}
				
				if(restart)
				{
					numberOfRestarts++;
					if(numberOfRestarts%100000==0)
					{
						//em.out.println("Number of Restarts: "+numberOfRestarts);
					}
					restart=false;
					break;
				}
			}
			if(sb.isBoardFilled())
			{
				done=true;
				System.out.println("DONE");
				System.out.println("Total Number of Restarts: "+numberOfRestarts);
			}
		}
		endTime = new Date();
		System.out.println("Total Run Time: "+(endTime.getTime()-startTime.getTime())+" ms");
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
