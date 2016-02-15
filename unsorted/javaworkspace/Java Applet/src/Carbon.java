/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related to specific atom known as carbon
 * */
import java.awt.Image;


public class Carbon extends Atom {
	/** Creates an object of type Carbon which is an extension of the Atom class
	 * @param positionX x-position on the screen
	 * @param positionY y-position on the screen
	 * @param image image assigned
	 * */
	public Carbon(int positionX, int positionY, Image image) {
		super(positionX, positionY);
		this.setArea(this.getPositionX(), this.getPositionY(), 50, 50);
		this.picture = image;
		this.setName("Carbon");
		this.setSymbol("C");
		this.setElementNumber(12);
		this.setWeight(12.01);
		this.setCapableBonds(4);
		this.setCurrentBonds(0);
		this.setEN(2.55);
		this.CarbonNum=1;
	}
	/** Creates an object of type Carbon which is an extension of the Atom class for testing purposes*/
	public Carbon(int positionX, int positionY) {
		super(positionX, positionY);
		this.setName("Carbon");
		this.setSymbol("C");
		this.setElementNumber(12);
		this.setWeight(12.01);
		this.setCapableBonds(4);
		this.setCurrentBonds(0);
		this.setEN(2.55);
		this.CarbonNum=1;
	}
	public Carbon (){
		super();
		this.setName("Carbon");
		this.setSymbol("C");
		this.setElementNumber(12);
		this.setWeight(12.01);
		this.setCapableBonds(4);
		this.setCurrentBonds(0);
		this.setEN(2.55);
		this.CarbonNum=1;
	}
	public Carbon NextCarbon (){
		for (int i=0; i<=currentBonds-1; i++)
		{
			try{
			if ((GetAtom(i).getName().equals("Carbon"))&&(GetAtom(i).getCarbonNum()==this.CarbonNum+1))
			{
				return (Carbon) GetAtom(i);
			}
			}
			catch(Exception e){
				
			}
		}
		return null;
	}
	

	public void FillH (){
		for (int i = 0; i < 4; i++)
		{
			if (this.capableBonds<=0) {
				break;
			}
			//addition to if statement stop hydrogens from overlapping over atoms
			if(i == 0 && this.bondsArray.get(0) == null){
				Hydrogen hydrogen=new Hydrogen (this.getPosX() - 70, this.getPosY());
				this.BondTo(hydrogen, 0);
			}
			else if(i == 1 && this.bondsArray.get(1) == null){
				Hydrogen hydrogen=new Hydrogen (this.getPosX() + 70, this.getPosY());
				this.BondTo(hydrogen, 1);
			}
			else if(i == 2 && this.bondsArray.get(2) == null){
				Hydrogen hydrogen=new Hydrogen (this.getPosX(), this.getPosY() - 70);
				this.BondTo(hydrogen, 2);
			}
			else if(i == 3 && this.bondsArray.get(3) == null){
				Hydrogen hydrogen=new Hydrogen (this.getPosX(), this.getPosY() + 70);
				this.BondTo(hydrogen, 3);
			}
				
		}
	}
	
	public void RemoveH (){
		for (int i=0; i<=currentBonds-1; i++){
			if ((GetAtom(i).name.equals("Hydrogen"))&&(capableBonds<0)){
				this.RemoveBond(GetAtom(i));
			}
		}
	}
	public int CountH (){
		int n=0;
		for (int i=0; i<=3; i++){
			if (bondsArray.get(i).getEnd().getName() == "Hydrogen"){
				n+=1;
			}
		}
		return n;
	}

	public boolean anyAtomAt(int x, int y){
		
		for (int i=0; i< this.currentBonds; i++){
			System.out.println("hey");
			if (this.bondsArray.get(i).getEnd().getPosX() == x && this.bondsArray.get(i).getEnd().getPosY() == y){
				return true;
			}
		}
		return false;
	}
}
