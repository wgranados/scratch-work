/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related to specific atom known as oxygen
 * */
import java.awt.Image;
import java.awt.Point;


public class Oxygen extends Atom{
	
	/** Creates an object of type Oxygen which is an extension of the Atom class
	 * @param positionX x-position on the screen
	 * @param positionY y-position on the screen
	 * @param image image assigned
	 * */
	Point bondPositions = new Point(0,1);
	public Oxygen(int positionX, int positionY, Image image) {
		super(positionX, positionY);
		this.setAtomImage(image);
		this.setArea(this.getPositionX(), this.getPositionY(), 50, 50);
		this.setName("Oxygen");
		this.setSymbol("O");
		this.setWeight(16.00);
		this.setCapableBonds(2);
		this.setEN(3.16);
		bondsArray.add(null);
		bondsArray.add(null);
		bondsArray.add(null);
		bondsArray.add(null);
	}
	/** Creates an object of type Oxygen which is an extension of the Atom class for testing purposes*/
	public Oxygen(){
		super();
		this.setName("Oxygen");
		this.setSymbol("O");
		this.setElementNumber(8);
		this.setWeight(16.00);
		this.setCapableBonds(2);
		this.setEN(3.44);
	}
}