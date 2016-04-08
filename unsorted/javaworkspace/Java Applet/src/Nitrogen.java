/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related to specific atom known as nitrogen
 * */
public class Nitrogen extends Atom{

	/** Creates an object of type Nitrogen which is an extension of the Atom class
	 * @param positionX x-position on the screen
	 * @param positionY y-position on the screen
	 * @param image image assigned
	 * */
	public Nitrogen(int positionX, int positionY) {
		super(positionX, positionY);
		this.setName("Nitrogen");
		this.setSymbol("N");
		this.setElementNumber(7);
		this.setWeight(14.01);
		this.setCapableBonds(3);
		this.setEN(3.04);
	}
	/** Creates an object of type Nitrogen which is an extension of the Atom class for testing purposes*/
	public Nitrogen(){
		super();
		this.setName("Nitrogen");
		this.setSymbol("N");
		this.setElementNumber(7);
		this.setWeight(14.01);
		this.setCapableBonds(3);
		this.setEN(3.04);
	}
	public void FillH (){
		for (int i = 0; i < 3; i++)
		{
			if (this.capableBonds<=0) {
				break;
			}
			if(i == 0 && this.bondsArray.get(0) == null){
				Hydrogen hydrogen=new Hydrogen (this.getPosX(), this.getPosY() -70);
				this.BondTo(hydrogen, 0);
			}
			else if(i == 1 && this.bondsArray.get(1) == null){
				Hydrogen hydrogen=new Hydrogen ((int)(this.getPosX() - 35*(Math.sqrt(3))), this.getPosY() + 35);
				this.BondTo(hydrogen, 1);
			}
			else if(i == 2 && this.bondsArray.get(2) == null){
				Hydrogen hydrogen=new Hydrogen ((int)(this.getPosX() + 35*(Math.sqrt(3))), this.getPosY() +35);
				this.BondTo(hydrogen, 2);
			}
				
		}
	}
	public void RemoveH (){
		for (int i=0; i<=currentBonds-1; i++){
			if ((GetAtom(i).name.equals("Hydrogen"))&&(capableBonds<0)){
				this.bondsArray.set(i, null);
			}
		}
	}
}