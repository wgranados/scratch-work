/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related to specific atom known as fluorine
 * */
import java.awt.Image;


public class Fluorine extends Atom {
	
	/** Creates an object of type Fluorine which is an extension of the Atom class
	 * @param positionX x-position on the screen
	 * @param positionY y-position on the screen
	 * @param image image assigned
	 * */
	public Fluorine(int positionX, int positionY,Image image) {
		super(positionX, positionY);
		this.setAtomImage(image);
		this.setArea(this.getPositionX(), this.getPositionY(), 50, 50);
		this.setName("Fluorine");
		this.setSymbol("F");
		this.setElementNumber(9);
		this.setWeight(19.00);
		this.setCapableBonds(1);
		this.setEN(3.98);
	}
	/** Creates an object of type Clorine which is an extension of the Atom class for testing purposes*/
	public Fluorine(){
		super();
		this.setName("Fluorine");
		this.setSymbol("F");
		this.setElementNumber(9);
		this.setWeight(19.00);
		this.setCapableBonds(1);
		this.setEN(3.98);
	}
}