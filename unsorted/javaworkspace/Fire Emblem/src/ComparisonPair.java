/** Name: William Granados
 *  Date: 09/06/14
 *  Purpose: Used as a storage device for player information when 
 *  		 ArtificialIntelligence.java runs a breadth-first search
 *  		 or a depth first search their strategies.
 * */

public class ComparisonPair {
	
	private Unit unit;
	private int prevX;
	private int prevY;
	
	/** Creates an object of type comparisonPair
	 *  @param prevX x position on grid before computer unit reaches the player unit
	 *  @param prevY y position on grid before computer unit reaches the player unit
	 *  @param unit  unit that is encountered after searching
	 *  */
	public ComparisonPair(int prevX, int prevY, Unit unit){
		this.setPrevX(prevX);
		this.setPrevY(prevY);
		this.setUnit(unit);
		
	}

	// getters and setters
	/** Returns the previous x position before reaching the unit*/
	public int getPrevX() {
		return prevX;
	}
	/** Sets the previous x position before reaching the unit*/
	public void setPrevX(int prevX) {
		this.prevX = prevX;
	}
	
	/** Returns the previous y position before reaching the unit*/
	public int getPrevY() {
		return prevY;
	}
	/** Sets the previous y position before reaching the unit*/
	public void setPrevY(int prevY) {
		this.prevY = prevY;
	}
	
	/** Returns the unit that is encountered*/
	public Unit getUnit() {
		return unit;
	}
	/** Sets the unit that is encountered*/
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}
