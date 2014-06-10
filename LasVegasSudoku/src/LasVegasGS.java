import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/*
 * The algorithm randomizes coordinates for one group of markers (based on a specific value) at a time and checks if all markers in the group can be placed
 * The algorithm starts over if the picked coordinates are occupied or if the slot is invalid for the specific value
 */
public class LasVegasGS
{
	private SudokuBoard sb;
	private int boardSize;
	private MarkerObject marker;
	private Collection<MarkerObject> markersToPlace;
	private Collection<MarkerObject> markerCollection;
	private Date startTime;
	private Date endTime;
	
	public LasVegasGS(int boardSize, SudokuBoard sb){
		this.boardSize = boardSize;
		this.sb = sb;
		this.markerCollection = new ArrayList<MarkerObject>();
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
				
				markersToPlace=createMarkers(valueToPlace);
				for (Iterator<MarkerObject> mtp = markersToPlace.iterator(); mtp.hasNext();)
				{
					MarkerObject marker = (MarkerObject) mtp.next();
					if(sb.isValidPlacement(marker)){
						sb.placeMarker(marker);
					}
					else{
						restart=true;
						break;
					}
				}
				if(restart==true)
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
	public Collection<MarkerObject> createMarkers(int value){
		markerCollection.clear();
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
