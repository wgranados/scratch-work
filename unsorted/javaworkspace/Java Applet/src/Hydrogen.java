/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related to specific atom known as hydrogen
 * */
public class Hydrogen extends Atom {
	
	Sprites Images = new Sprites();
	
	/** Creates an object of type Hydrogen which is an extension of the Atom class
	 * @param positionX x-position on the screen
	 * @param positionY y-position on the screen
	 * @param image image assigned
	 * */
	public Hydrogen(int positionX, int positionY) {
		super(positionX, positionY);
		this.setAtomImage(Images.getHydrogenMolecule());
		this.setArea(this.getPositionX(), this.getPositionY(), 50, 50);
		this.setName("Hydrogen");
		this.setSymbol("H");
		this.setElementNumber(1);
		this.setWeight(1.01);
		this.setCapableBonds(1);
		this.setEN(2.2);
	}
	/** Creates an object of type Hydrogen which is an extension of the Atom class for testing purposes*/
	public Hydrogen()
	{
		super();
		this.setName("Hydrogen");
		this.setAtomImage(Images.getHydrogenMolecule());
		this.setSymbol("H");
		this.setElementNumber(1);
		this.setWeight(1.01);
		this.setCapableBonds(1);
		this.setEN(2.2);
	}
	
}

