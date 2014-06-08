import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
 * Las Vegas Algorithm with placement memory
 */
public class LasVegasWPM
{
	private SudokuBoard sb;
	private int boardSize;
	private MarkerObject marker;
	private Collection<MarkerObject> markersToPlace;
	private Collection<MarkerObject> markerCollection;
	
	public LasVegasWPM(int boardSize, SudokuBoard sb){
		this.boardSize = boardSize;
		this.sb = sb;
		this.markerCollection = new ArrayList<MarkerObject>();
	}

	/*
	 * Tries to place all ones, then all twos and so on in increasing order. If the program tries to place a number
	 * in an invalid slot, it fails and starts over from the beginning 
	 */
	public void solveSudoku(){
		
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
	}
	
	@SuppressWarnings("null")
	public Collection<MarkerObject> createMarkers(int value){
		markerCollection.clear();
		for (int x = 0; x < boardSize; x++)
		{
			for (int y = 0; y < boardSize; y++)
			{
				int xCoordinate = (int) (Math.random()*(boardSize)+x*boardSize);
				int yCoordinate = (int) (Math.random()*(boardSize)+y*boardSize);
				
				//While the randomized coordinates are occupied within the sudoku board, new coordinates are generated
				while(!sb.isSlotEmpty(xCoordinate, xCoordinate))
				{
					xCoordinate = (int) (Math.random()*(boardSize)+x*boardSize);
					yCoordinate = (int) (Math.random()*(boardSize)+y*boardSize);
				}

				marker = new MarkerObject(value,xCoordinate,yCoordinate);	
				markerCollection.add(marker);
			}
		}
		return markerCollection;
	}

}
