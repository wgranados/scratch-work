/** Name: William Granados
 *  Date: 09/06/14
 *  Purpose: contains the information for a tile in the level layout
 * */
import java.awt.geom.Rectangle2D;


public class Tile {

	private int x;
	private int y;
	private final int height = 25;
	private final int width = 25;
	private boolean isTraversable;
	private boolean isPlacable;
	private Rectangle2D tileBoundary;
	private Unit unit;
	
	
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	/** Creates a simple tile object. 
	 *  @param x x position of the tile
	 *  @param y y position of the tile
	 *  @param isTraversable whether or not the tile can be traversed by a unit
	 *  */
	public Tile(int x,int y, boolean isTraversable){
		this.setX(x);
		this.setY(y);
		this.setIsTraversable(isTraversable);
		this.setTileBoundary(x, y, this.width, this.height);
		this.setOccupiedUnit(null);
		this.setPlacable(false);
	}
	
	/** Creates a tile object used when the user is selecting units.
	 *  @param x x position of the tile
	 *  @param y y position of the tile
	 *  @param isTraversable whether or not the tile can be traversed by a unit
	 *  @param isPlacable whether or not the tile is a tile that the player can place a unit on when selecting units
	 *  */
	public Tile(int x,int y, boolean isTraversable, boolean isPlacable){
		this.setX(x);
		this.setY(y);
		this.setIsTraversable(isTraversable);
		this.setTileBoundary(x, y, this.width, this.height);
		this.setOccupiedUnit(null);
		this.setPlacable(isPlacable);
	}

	/** Creates a tile object for testing purposes.*/
	public Tile(){
		
	}
	
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	// getters and setters
	/** Returns the x position of the tile.*/
	public int getX() {
		return x;
	}
	/** Sets the x position of the tile.
	 *  @param x value to be substituted*/
	public void setX(int x) {
		this.x = x;
	}
	
	/** Returns the y position of the tile.*/
	public int getY() {
		return y;
	}
	/** Sets the y position of the tile.
	 *  @param y value to be substituted*/
	public void setY(int y) {
		this.y = y;
	}
	
	/** Returns the height tile.*/
	public int getHeight(){
		return height;
	}
	/** Returns the width tile.*/
	public int getWidth(){
		return width;
	}
	
	/** Returns whether or not the tile is traversable.*/
	public boolean getIsTraversable(){
		return isTraversable;
	}
	/** Sets the traversable variable of the tile.
	 *  @param isTraversible value to be substituted*/
	private void setIsTraversable(boolean isTraversible){
		this.isTraversable = isTraversible;
	}
	
	/** Returns the tile boundary of the tile.*/
	public Rectangle2D getTileBoundary() {
		return tileBoundary;
	}
	/** Sets the tile boundary of the tile.
	 *  @param x x position of the rectangle
 	 *  @param y y position of the rectangle
	 *  @param w width of the rectangle
	 *  @param h height of the rectangle
	 *  */
	private void setTileBoundary(double x, double y, double w, double h) {
		this.tileBoundary = new Rectangle2D.Double(x, y, w, h);
	}
	
	/** Returns the unit that is currently occupying this tile*/
	public Unit getOccupiedUnit() {
		return unit;
	}
	/** Sets the unit that is currently occupying this tile*/
	public void setOccupiedUnit(Unit unit) {
		this.unit = unit;
	}
	
	/** Returns whether or not the tile is placable*/
	public boolean isPlacable() {
		return isPlacable;
	}
	/** Sets whether or not the tile is placable*/
	public void setPlacable(boolean isPlacable) {
		this.isPlacable = isPlacable;
	}
	

}
