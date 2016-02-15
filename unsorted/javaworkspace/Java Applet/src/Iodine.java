/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related to specific atom known as iodine
 * */
import java.awt.Image;


public class Iodine extends Atom {
	
	/** Creates an object of type Iodine which is an extension of the Atom class
	 * @param positionX x-position on the screen
	 * @param positionY y-position on the screen
	 * @param image image assigned
	 * */
	public Iodine(int positionX, int positionY,Image image) {
		super(positionX, positionY);
		this.setAtomImage(image);
		this.setArea(this.getPositionX(), this.getPositionY(), 50, 50);
		this.setName("Iodine");
		this.setSymbol("I");
		this.setElementNumber(53);
		this.setWeight(125.90);
		this.setCapableBonds(1);
		this.setEN(2.66);
	}
	/** Creates an object of type Iodine which is an extension of the Atom class for testing purposes*/
	public Iodine(){
		super();
		this.setName("Iodine");
		this.setSymbol("I");
		this.setElementNumber(53);
		this.setWeight(125.90);
		this.setCapableBonds(1);
		this.setEN(2.66);
	}
}
