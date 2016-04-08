/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related to specific molecule known as nitrogen dioxide
 * */
import java.awt.Image;


public class NO2 extends Atom{
	protected Nitrogen N;
	protected Oxygen O;
	protected Oxygen O2;
	public NO2 (int positionX, int positionY, Image image)
	{
		super(positionX, positionY);
		this.setAtomImage(image);
		this.setArea(this.getPositionX(), this.getPositionY(), 50, 50);
		this.setName("Nitrate");
		this.setSymbol("NO2");
		this.setWeight(17.01);
		this.setCapableBonds(1);
		this.setEN(3.16);
		bondsArray.add(null);
		bondsArray.add(null);
		bondsArray.add(null);
		bondsArray.add(null);
	}
	public NO2(){
	}

}
