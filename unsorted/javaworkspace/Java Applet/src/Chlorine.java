/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related to specific atom known as chlorine
 * */
import java.awt.Image;


public class Chlorine extends Atom {
	
	/** Creates an object of type Chlorine which is an extension of the Atom class
	 * @param positionX x-position on the screen
	 * @param positionY y-position on the screen
	 * @param image image assigned
	 * */
	public Chlorine(int positionX, int positionY,Image image) {
		super(positionX, positionY);
		this.setAtomImage(image);
		this.setArea(this.getPositionX(), this.getPositionY(), 50, 50);
		this.setName("Chlorine");
		this.setSymbol("Cl");
		this.setElementNumber(17);
		this.setWeight(35.45);
		this.setCapableBonds(1);
		this.setEN(3.16);
	}
	/** Creates an object of type Clorine which is an extension of the Atom class for testing purposes*/
	public Chlorine(){
		super();
		this.setName("Chlorine");
		this.setSymbol("Cl");
		this.setElementNumber(17);
		this.setWeight(35.45);
		this.setCapableBonds(1);
		this.setEN(3.16);
	}
}
