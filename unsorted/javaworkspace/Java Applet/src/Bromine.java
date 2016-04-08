/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related to specific atom known as bromine
 * */
import java.awt.Image;


public class Bromine extends Atom {
	
	/** Creates an object of type Bromine which is an extension of the Atom class
	 * @param positionX x-position on the screen
	 * @param positionY y-position on the screen
	 * @param image image assigned
	 * */
	public Bromine(int positionX, int positionY, Image image) {
		super(positionX, positionY);
		this.setAtomImage(image);
		this.setArea(this.getPositionX(), this.getPositionY(), 50, 50);
		this.setName("Bromine");
		this.setSymbol("Br");
		this.setElementNumber(35);
		this.setWeight(79.90);
		this.setCapableBonds(1);
		this.setEN(2.96);
	}
	/** Creates an object of type Bromine which is an extension of the Atom class for testing purposes*/
	public Bromine(){
		super();
		this.setName("Bromine");
		this.setSymbol("Br");
		this.setElementNumber(35);
		this.setWeight(79.90);
		this.setCapableBonds(1);
		this.setEN(2.96);
	}
}
