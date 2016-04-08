/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related to specific molecule known as hydroxide
 * */
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;


public class Hydroxide extends Atom{
	public List<Bond> bondsArray = new ArrayList();
	public Hydroxide (int positionX, int positionY,Image image){
		super(positionX, positionY);
		this.setAtomImage(image);
		this.setArea(this.getPositionX(), this.getPositionY(), 50, 50);
		this.setName("Hydroxide");
		this.setSymbol("OH");
		this.setWeight(17.01);
		this.setCapableBonds(1);
		this.setEN(3.16);
		bondsArray.add(null);
		bondsArray.add(null);
		bondsArray.add(null);
		bondsArray.add(null);
	}
	public void BondTo (Carbon carbon, int bondPosition){
		carbon.bondsArray.set(bondPosition, null);
		Bond bondC = new Bond();
		bondC.setBegin(carbon);
		bondC.setEnd(this);
		carbon.bondsArray.set(bondPosition, bondC);
		
		Bond bondOH = new Bond();
		bondOH.setBegin(this);
		bondOH.setEnd(carbon);
	}

}
