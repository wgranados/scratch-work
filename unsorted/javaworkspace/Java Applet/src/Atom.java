
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;

/**
 * 
 * 
 * */
public class Atom {
	protected String name;
	protected String Symbol;
	protected String bondType;
	protected int CarbonNum;
	
	protected int positionX,positionY;
	protected int elementNumber;
	protected double weight;
	protected double electroNegativity;//stands for electronegativity - i.e. ability to hold bonding electrons
	
	protected int capableBonds;
	protected int currentBonds;
	protected ArrayList<Bond> bondsArray;
	
	protected Image picture;
	protected Rectangle2D area;
	
	// constructors
	/**Creates a new object of the type Atom
	 * @param name
	 * @param symbol
	 * @param elementNumber
	 * @param weight
	 * @param capableBonds
	 * @param positionX
	 * @param positionY
	 * @param electroNegativity
	 * */
	public Atom(String name, String symbol, int elementNumber,double weight, int capableBonds, int positionX, int positionY, double electroNegativity){
			this.setName(name);
			this.setSymbol(symbol);
			this.setElementNumber(elementNumber);
			this.setWeight(weight);
			this.setCurrentBonds(capableBonds);
			this.setCurrentBonds(0);
			this.setPositionX(positionX);
			this.setPositionY(positionY);
		
			this.area = new Rectangle2D.Double(this.positionX,this.positionY,50,50);
	
			this.setEN(electroNegativity);
			this.bondsArray = new ArrayList<Bond>();
			this.bondsArray.add(null);
			this.bondsArray.add(null);
			this.bondsArray.add(null);
			this.bondsArray.add(null);
			this.setCarbonNum(0);
			
			
	}
	/**Creates a new object of the type Atom
	 * @param positionX
	 * @param positionY
	 * */
	public Atom(int positionX,int positionY){
		this.setPositionX(positionX);
		this.setPositionY(positionY);
		this.bondsArray = new ArrayList<Bond>();
		bondsArray.add(null);
		bondsArray.add(null);
		bondsArray.add(null);
		bondsArray.add(null);
		this.setCarbonNum(0);
		
		
	}
	/**Creates a new object of the type Atom for backend testing purposes 
	 * */
	public Atom (){
		this.bondsArray = new ArrayList<Bond>();
		bondsArray.add(null);
		bondsArray.add(null);
		bondsArray.add(null);
		bondsArray.add(null);
		this.setCarbonNum(0);
	}
	
	
	// carbon number
	/** returns the atoms carbon position in the chain
	 *  @return carbonNum*/
	public int getCarbonNum() {
		return this.CarbonNum;
	}
	/** sets the atoms carbon position in the chain 
	 * @param carbonNum carbon position in the chain
	 * */
	public void setCarbonNum(int carbonNum) {
		CarbonNum = carbonNum;
	}
	
	
	// electro negativity
	/** returns the electro negativity of the atom object
	 *  @return electronegativity*/
	public double getEN() {
		return electroNegativity;
	}
	/** sets the electro negativity of the atom object
	 * @param  electroNegativity the electro negativity values to be substituted
	 * */
	public void setEN(double electroNegativity) {
		this.electroNegativity = electroNegativity;
	}
	/** returns the net electronegativity difference of the atom
	 *  @return total**/
	public double NetENdiff () {
		double total=0;
		for (int i=0; i<= bondsArray.size() -1; i++){
			total+=bondsArray.get(i).ENdiff(this);
		}
		return total;
	}
	
	
	
	// element name
	/** returns the UIPAC name of the atom 
	 *  @return name*/
	public String getName() {
		return name;
	}
	/** sets the UIPAC name of the atom object
	 * @param  name name to be subtituted 
	 * */
	public void setName(String name) {
		this.name = name;
	}
	
	// element symbol
	/** returns the element number of the atom
	 *  @return symbol*/
	public String getSymbol() {
		return Symbol;
	}
	/** sets the element number of the element
	 * @param symbol symbol to be substituted
	 * */
	public void setSymbol(String symbol) {
		this.Symbol = symbol;
	}
	
	// element number
	/** returns the atom's position on the periodic table
	 * 	@return elementNumber*/
	public int getElementNumber() {
		return elementNumber;
	}
	/** sets the atoms position on the periodic table
	 *  @param elementNumber element number to be substituted
	 * */
	public void setElementNumber(int elementNumber) {
		this.elementNumber = elementNumber;
	}
	
	// weight
	/** returns the atom's atomic weight of the atom
	 *  @return weight*/
	public double getWeight() {
		return weight;
	}
	/** sets the atom's atomic weight
	 *  @param weight weight to be substituted
	 * */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	
	// bonds array
	/** returns the arraylist containing the bonds attached to the atom*/
	public ArrayList<Bond> getBondsArray() {
		return bondsArray;
	}
	/** returns the ith element in the bonds array
	 * @param i position in the bonds array
	 * @return bond**/
	public Bond getBondsPosition(int i) {
		return bondsArray.get(i);
	}
	/** sets the bonds array
	 *  @param bondsArray bonds array to be substituted*/
	public void setBondsArray(ArrayList<Bond> bondsArray) {
		this.bondsArray = bondsArray;
	}
	/** returns the atom at the ith position
	 *  @param bondnum
	 *  @return atom */
	public Atom GetAtom (int bondnum){
		Bond bond=bondsArray.get(bondnum);
		if (this == bond.getBegin()) {
			return bond.getEnd();
		}
		else return bond.getBegin();
	}
	
	// positionX
	/** returns the atoms position the x-axis of the screen
	 *  @return positionX*/
	public int getPositionX() {
		return positionX;
	}
	/** sets the atoms position on the x-axis of the screen
	 *  @param positionX x-position to be subtituted*/
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	public int getPosX() {
		return positionX;
	}
	public int getPosY() {
		return positionY;
	}
	
	
	// positionY
	/** returns the atoms position the y-axis of the scree
	 	@return positionY*/
	public int getPositionY() {
		return positionY;
	}
	/** sets the atoms position on the y-axis of the screen
	 *  @param positionY y-position to be subtituted*/
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	
	// current bonds
	/** returns the number of bonds on the atom
	 * 	@return currentBonds*/
	public int getCurrentBonds() {
		return currentBonds;
	}
	/** sets the number of bonds on the atom
	 *  @param currentBonds value to be subtituted*/
	public void setCurrentBonds(int currentBonds) {
		this.currentBonds = currentBonds;
	}
	
	
	// bond type
	/** returns the bond type of the atom
	 *  @return BondType*/
	public String getBondType() {
		return bondType;
	}
	/** sets the bond type of the atom
	 *  @param bondType bond type to be substituted*/
	public void setBondType(String bondType) {
		this.bondType = bondType;
	}
	
	
	// capable bonds
	/** returns the capable bonds the atom can make
	 *  @return capableBonds*/
	public int getCapableBonds() {
		return this.capableBonds;
	}
	/** sets the capable bonds the atom can make
	 * @param capableBonds value to be subtituted*/
	public void setCapableBonds(int capableBonds) {
		this.capableBonds = capableBonds;
	}
	

	// bonds
	/** checks if the current atom can still make bonds*/
	public boolean canMakeBond (){
		if (capableBonds==0) return false;
		return true;
	}
	/** bonds an atom to the ith position
	 *  @param atom atom to be placed
	 *  @param newAtomPosition position the new atom will be placed at*/
	public void BondTo (Atom atom, int newAtomPosition){
		Bond bondA=new Bond();
		// this now forms a proper linkage between atoms. End is the atom bonded to and begin is the current atom
		bondA.setBegin(this);
		bondA.setEnd(atom);
		Bond bondN = new Bond();
		bondN.setBegin(atom);
		bondN.setEnd(this);
		//position of bonds 0:left 1:right 2:up 3:down
		int oldAtomRelativePosition = -1;
		if(newAtomPosition == 1)
			oldAtomRelativePosition = 0;
		if(newAtomPosition == 0)
			oldAtomRelativePosition = 1;
		if(newAtomPosition == 2)
			oldAtomRelativePosition = 3;
		if(newAtomPosition == 3)
			oldAtomRelativePosition = 2;
		this.bondsArray.set(newAtomPosition, bondA);
		atom.bondsArray.set(oldAtomRelativePosition, bondN);
		this.currentBonds+=1;
		this.capableBonds-=1;
		atom.setCurrentBonds(atom.getCurrentBonds()+1);
		atom.setCapableBonds(atom.getCapableBonds()-1);
	}
	/** removes the bond with the given Atom
	 * @param atom atom to be removed
	 *  **/
	public void RemoveBond (Atom atom)
	{
		for (int i=0; i<=currentBonds-1; i++)
		{
			try{
			if (GetAtom(i)==atom){
				this.currentBonds-=1;
				this.capableBonds+=1;
				atom.bondsArray.set(i, null);
				this.bondsArray.set(i, null);
				atom.capableBonds+=1;
				atom.currentBonds-=1;
			}
			}
			catch(Exception e){
				System.out.println(e.getStackTrace());
			}
		}
	}
	public void RemoveBond (int position)
	{
				this.bondsArray.set(position, null);
				this.currentBonds-=1;
				this.capableBonds+=1;
	}
	
	
	// Image object
	/** sets the image of the atom
	 *  @param image image to be substituted*/
	public void setAtomImage(Image image){
		if(image == null){
			
			System.out.println("first error");
		}
		this.picture = image;
		if(this.picture == null){
			
			System.out.println("second error");
		}
	}
	/** returns the iamge of the atom
	 *  @return image*/
	public Image getAtomImage(){
		return this.picture;
	}
	
	
	// Rectangle2D object
	/** returns the Rectangle2D object of the atom
	 * 	@return area*/
	public Rectangle2D getArea(){
		return this.area;
	}
	/** sets the Rectangle2D object of the atom
	 * @param x x-position of the rectangle
	 * @param y y-position of the rectangle
	 * @param w width of the rectangle
	 * @param h height of the rectangle
	 * **/
	public void setArea(int x, int y, int w, int h){
		Rectangle2D area = new Rectangle2D.Double(x,y,w,h);
		this.area = area;
	}
	
}