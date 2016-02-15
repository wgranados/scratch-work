/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related the whole molecule
 * */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

public class Molecule {
	
	BufferedImage onScreen = new BufferedImage(700,640,BufferedImage.TYPE_INT_RGB);
	Graphics g = onScreen.createGraphics();
	private Sprites images = new Sprites();
	private String name;
	private int priority;
	private boolean IsItCyclo;
	private Carbon begin;
	private Carbon end;
	private int length;
	private Map <Integer, String> LenConverter;
	private Map <Integer, String> CountConverter;
	private Map <Integer, String> PreConverter;
	private Map <Integer, String> PostConverter;
	private int [] [] functionalGroups;
	
	
	public Molecule(){
		//this.atoms = new ArrayList<Atom>();
		//this.bonds = new ArrayList<Bond>();
		//this.functionalGroups = new PriorityQueue<Atom>();
		//this.parentChainSize = 0;
		this.name = "";
		this.begin=null;
		this.end=null;
		this.length=0;
		this.priority=20;
		this.setIsItCyclo(false);
		LenConverter=new HashMap<Integer, String>();
		CountConverter=new HashMap<Integer, String>();
		PreConverter=new HashMap<Integer, String>();
		PostConverter=new HashMap<Integer, String>();
		LenConverter.put(1, "meth");
		LenConverter.put(2, "eth");
		LenConverter.put(3, "prop");
		LenConverter.put(4, "but");
		LenConverter.put(5, "pent");
		LenConverter.put(6, "hex");
		LenConverter.put(7, "hept");
		LenConverter.put(8, "oct");
		LenConverter.put(9, "non");
		LenConverter.put(10, "dec");
		LenConverter.put(11, "undec");
		LenConverter.put(12, "dodec");
		CountConverter.put(1, "");
		CountConverter.put(2, "di");
		CountConverter.put(3, "tri");
		CountConverter.put(4, "tetra");
		PreConverter.put(1, "carboxy");//done
		PreConverter.put(2, "oxy carbonyl");//done
		PreConverter.put(3, "carbomoyl");//done
		PreConverter.put(4, "formyl");//done
		PreConverter.put(5, "oxo");//done
		PreConverter.put(6, "hydroxyl");//done
		PreConverter.put(7, "amino");//done
		PreConverter.put(8, "chloro");//done
		PreConverter.put(11, "nitro");//done
		PreConverter.put(12, "fluoro");//done
		PreConverter.put(13, "bromo");//done
		PreConverter.put(14, "iodo");//done
		
		PostConverter.put(1, "oic acid");
		PostConverter.put(2, "oate");
		PostConverter.put(3, "amide");
		PostConverter.put(4, "al");
		PostConverter.put(5, "one");
		PostConverter.put(6, "ol");
		PostConverter.put(7, "amine");
		PostConverter.put(9, "yne");
		PostConverter.put(10, "ene");
		functionalGroups=new int [14][12];
		for (int i=1; i<=14; i++){
			for (int j=1; j<=12; j++)
			{
				functionalGroups[i-1][j-1]=0;
			}
		}
		
	}
	
	
	/**
	 * Starts the molecule
	 * @param carbon Carbon that is starting carbon in molecule
	 */
	public void StartMolecule (Carbon carbon){
		begin=carbon;
		end=carbon;
		begin.FillH();
		length=1;
	}
	
	
	/**
	
	 * Adjoins the end of chain carbons together to form a ring structure
	 */
	public void MakeLoop (int bondPosition){
		begin.BondTo(end, bondPosition);
		begin.RemoveH();
		end.RemoveH();
		IsItCyclo=true;
		for (int i=1; i<=length; i++)
		{
			functionalGroups[5-1][i-1]+=functionalGroups[4-1][i-1];
			functionalGroups[4-1][i-1]=0;
		}
		if (priority==4) {priority=5;}
	}

	
	/**
	 * Adds a new carbon atom to the chain after a certain carbon
	 * It also edits the description of the molecule to be accurate given this change
	 * @param NewCarbon is the carbon to be added to the molecule
	 * @param OldCarbon is the carbon that the new carbon will be added to such that
	 * the carbon number of the new one is 1 greater than the old one
	 */
	public void addAtom(Carbon NewCarbon, Carbon OldCarbon, int newCarbonPosition){
		//add bond and atom
		//determine parent chain length
		if (this.end == OldCarbon) {
			end.BondTo(NewCarbon, newCarbonPosition);
			end=NewCarbon;
			length+=1;
			NewCarbon.setCarbonNum(length);
		}
		else if (this.begin == OldCarbon) {
			Carbon current = this.begin;
			Carbon next = this.begin;
			for(int i = 1; i <= this.length; i++){
				next = current.NextCarbon();
				current.setCarbonNum(current.CarbonNum + 1);
				current =  next;
			}
			begin.BondTo(NewCarbon, newCarbonPosition);
			begin=NewCarbon;
			begin.setCarbonNum(1);
			length+=1;
		}
		else {
			length+=1;
			NewCarbon.BondTo(OldCarbon.NextCarbon(), newCarbonPosition);
			
			OldCarbon.BondTo(NewCarbon, newCarbonPosition);
			
			OldCarbon.NextCarbon().setCarbonNum(OldCarbon.getCarbonNum() +2 );
			
			NewCarbon.setCarbonNum(OldCarbon.getCarbonNum()+1);
			
			OldCarbon.RemoveBond(NewCarbon.NextCarbon());
		}

		NewCarbon.FillH();
	}
	
	
	/**
	 * Forms a carbon double bond between a new carbon to be added and an already present carbon
	 * calls the addAtom method for carbon
	 * Makes changes to molecule description to be consistent with this addition
	@param NewCarbon is the carbon to be added to the molecule
	 * @param OldCarbon is the carbon that the new carbon will be added to such that
	 * the carbon number of the new one is 1 greater than the old one
	 */
	public void DoubleBondC(Carbon NewCarbon, Carbon OldCarbon, int firstBondPosition, int secondBondPosition){
		this.addAtom(NewCarbon, OldCarbon, firstBondPosition);
		OldCarbon.BondTo(NewCarbon, secondBondPosition);
		OldCarbon.RemoveH();
		NewCarbon.RemoveH();
		//functional group
	}
	
	/**
	 * Forms a carbon triple bond between a new carbon to be added and an already present carbon
	 * calls the addAtom method for carbon
	 * Makes changes to molecule description to be consistent with this addition
	 *@param NewCarbon is the carbon to be added to the molecule
	 * @param OldCarbon is the carbon that the new carbon will be added to such that
	 * the carbon number of the new one is 1 greater than the old one
	 */
	public void Triple(Carbon NewCarbon, Carbon OldCarbon, int firstBondPosition) {
		int secondBondPosition = -1;
		int thirdBondPosition = -1;
		if(firstBondPosition == 0){
			
			secondBondPosition = 2;
			thirdBondPosition = 3;
		}
		if(firstBondPosition == 1){
			
			secondBondPosition = 3;
			thirdBondPosition = 2;
		}
		if(firstBondPosition == 2){
	
			secondBondPosition = 0;
			thirdBondPosition = 1;
		}
		if(firstBondPosition == 3){
	
			secondBondPosition = 1;
			thirdBondPosition = 0;
		}
		this.addAtom(NewCarbon, OldCarbon, firstBondPosition);
		OldCarbon.BondTo(NewCarbon, secondBondPosition);
		OldCarbon.BondTo(NewCarbon, thirdBondPosition);
		OldCarbon.RemoveH();
		NewCarbon.RemoveH();
		//functional group
	}
	
	/**
	 * Adds an oxygen to be double bonded to a specified carbon
	 * Also decides whether an aldehyde or keytone is formed depending on positioning of the carbon
	 * @param oxygen The oxygen atom that gets added
	 * @param carbon The carbon found in the molecule that the oxygen is being added to
	 */
	public void DoubleBondO (Oxygen oxygen, Carbon carbon, int firstBondPosition, int secondBondPosition){
		carbon.BondTo(oxygen, firstBondPosition);
		carbon.BondTo(oxygen, secondBondPosition);
		//if ((carbon.getCarbonNum()!=1)&&(carbon.getCarbonNum()!=length)||(IsItCyclo)){
		if (carbon.CountH()<=0){
			functionalGroups[5-1][carbon.getCarbonNum()-1]+=1;
			if (priority>5) {priority=5;}
		}
		else{
			functionalGroups[4-1][carbon.getCarbonNum()-1]+=1;
			if (priority>4) {priority=4;}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * 
	 * @param priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	// cyclo methods
	/**
	 * 
	 * @return
	 */
	public boolean isIsItCyclo() {
		return IsItCyclo;
	}
	
	/**
	 * 
	 * @param isItCyclo
	 */
	public void setIsItCyclo(boolean isItCyclo) {
		IsItCyclo = isItCyclo;
	}
	
	// length methods
	//
	/**
	 * 
	 * @return
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * 
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	// addition methods
	//
	/**
	 * 
	 * @param chlorine
	 * @param carbon
	 */
	public void addAtom(Chlorine chlorine, Carbon carbon, int bondPosition){
		carbon.RemoveBond(bondPosition);
		carbon.BondTo(chlorine, bondPosition);
		functionalGroups[8-1][carbon.getCarbonNum()-1]+=1;
	}
	
	/**
	 * 
	 * @param fluorine
	 * @param carbon
	 */
	public void addAtom(Fluorine fluorine, Carbon carbon, int bondPosition){
		carbon.RemoveBond(bondPosition);
		carbon.BondTo(fluorine, bondPosition);
		functionalGroups[12-1][carbon.getCarbonNum()-1]+=1;
	}
	/**
	 * 
	 * @param bromine
	 * @param carbon
	 */
	public void addAtom(Bromine bromine, Carbon carbon, int bondPosition){
		carbon.RemoveBond(bondPosition);
		carbon.BondTo(bromine, bondPosition);
		functionalGroups[13-1][carbon.getCarbonNum()-1]+=1;
	}
	/**
	 * 
	 * @param fluorine
	 * @param carbon
	 */
	public void addAtom(Iodine iodine, Carbon carbon, int bondPosition){
		carbon.RemoveBond(bondPosition);
		carbon.BondTo(iodine, bondPosition);
		functionalGroups[14-1][carbon.getCarbonNum()-1]+=1;
	}
	
	/**
	 * 
	 * @param nitrogen
	 * @param carbon
	 */
	public void addAtom(Nitrogen nitrogen, Carbon carbon,int bondPosition){
		nitrogen.BondTo(carbon, bondPosition);
		carbon.RemoveH();
		nitrogen.FillH();
		functionalGroups[7-1][carbon.getCarbonNum()-1]+=1;
		if (priority>7) {priority=7;}
	}
	
	/**
	 * 
	 * @param oxygen
	 * @param carbon
	 */
	public void addOxygen(Oxygen oxygen, Carbon carbon, int bondPosition){
		carbon.BondTo(oxygen, bondPosition);
		carbon.RemoveH();
	}
	
	/**
	 * 
	 * @param hydroxide
	 * @param carbon
	 */
	public void addHydroxyl(Hydroxide hydroxide, Carbon carbon, int bondPosition){
		hydroxide.BondTo(carbon, bondPosition);
		functionalGroups[6-1][carbon.getCarbonNum()-1]+=1;
		if (priority>6) priority=6;
	}
	
	/**
	 * 
	 * @param nh2
	 * @param carbon
	 */
	public void addNitrogen(Nitrogen nitrogen, Carbon carbon, int BondPosition){
		nitrogen.BondTo(carbon, BondPosition);

	}
	

	/**
	 * 
	 * @param no2
	 * @param carbon
	 */
	public void addNO2(NO2 no2, Carbon carbon,int bondPosition){
		carbon.BondTo(no2, bondPosition);
		functionalGroups[11-1][carbon.getCarbonNum()-1]+=1;
	}
		
	/**
	 * 
	 * @param carbon
	 * @param oxygen
	 */
	public void addtoO (Carbon carbon, Oxygen oxygen, int bondPosition){
		carbon.BondTo(oxygen, bondPosition);
		carbon.RemoveH();
	}
		
	
	// naming methods
	
	/**
	 * Updates the name of the molecule
	 * Uses other methods to divide task of naming into parts
	 * Does the prefix in alphabetical order (as required by IUPAC)
	 */
	public void updateName(){
		String S="";
		boolean alert=false;
		CA();
		Amide();
		PickC();
		if ((Sum(7)>0)&&(priority!=7)){//amino
			if (alert) {
				S+="-";
			}
			alert=true;
			S+=Prefix(7);
		}
		if ((Sum(13)>0)&&(priority!=13)){//bromo
			if (alert) {
				S+="-";
			}
			alert=true;
			S+=Prefix(13);
		}
		if ((Sum(3)>0)&&(priority!=3)){//carbomoyl
			if (alert) {
				S+="-";
			}
			alert=true;
			S+=Prefix(3);
		}
		if ((Sum(1)>0)&&(priority!=1)){//carboxy
			if (alert) {	
				S+="-";
			}
			alert=true;
			S+=Prefix(1);
		}
		if ((Sum(8)>0)&&(priority!=8)){//chloro
			if (alert) {
				S+="-";
			}
			alert=true;
			S+=Prefix(8);
		}
		if ((Sum(12)>0)&&(priority!=12)){//fluoro
			if (alert) {
				S+="-";
			}
			alert=true;
			S+=Prefix(12);
		}
				
		if ((Sum(4)>0)&&(priority!=4)){//formyl
			if (alert) {
				S+="-";
			}
			alert=true;
			S+=Prefix(4);
		}
		if ((Sum(6)>0)&&(priority!=6)){//hydroxyl
			alert=true;
			if (alert) {
				S+="-";
			}
			S+=Prefix(6);
		}
		if ((Sum(14)>0)&&(priority!=14)){//iodo
			if (alert) {
				S+="-";
			}
			alert=true;
			S+=Prefix(14);
		}
		if ((Sum(11)>0)&&(priority!=11)){//nitro
			alert=true;
			if (alert) {
				S+="-";
			}
			S+=Prefix(11);
		}
		if ((Sum(5)>0)&&(priority!=5)){//oxo
			if (alert) {
				S+="-";
			}
			alert=true;
			S+=Prefix(5);
		}
		if ((Sum(2)>0)&&(priority!=2)){//oxycarbomoyl
			if (alert) {
				S+="-";
			}
			alert=true;
			S+=Prefix(2);
		}
		if (IsItCyclo){ S+="cyclo";}
		S+=LenConverter.get(length);
		if (Sum(9)!=0) {
			S+=PostFix(9);
		}
		else if (Sum(10)!=0){
			S+=PostFix(10);
		}
		else{
			S+="an";
		}
		if (priority!=20){
			S+="-";
			S+=PostFix(priority);
		}
		else S+="e";
		this.setName(S);		
	}
		
	/**
	 * Gets the molecule name
	 * @return name of the molecule
	 */
	public String getName() {
		updateName();
		return name;
	}
		
	/**
	 * Sets the name of the molecule
	 * @param name The new name the molecule will take on
	 */
	public void setName(String name) {
		this.name = name;
	}
		
	/**
	 * Sums the functional groups for each carbon in molecule
	 * @param group the specified functional group
	 * @return the total number of the specified functional group
	 */
	public int Sum (int group){
		int sum=0;
		for (int i=1; i<=length; i++){
			sum+=functionalGroups[group-1][i-1];
		}
		return sum;
	}
	

	/**
	 * Determines a string representation of all the locations of this group
	 * @param group the specified functional group
	 * @return the locations of the specified functional group on this molecule as String
	 */
	public String Locations(int group){
		String S="";
		boolean alert=false;
		for (int i=1; i<=length; i++){
			for (int j=1; j<=functionalGroups[group-1][i-1]; j++){
				if (alert) S+="," + Integer.toString(i);
				else S+=Integer.toString(i);
				alert=true;
			}
		}
		return S;
	}
	
	/**
	 * Determines the prefix of the molecule
	 * @param group the specified functional group
	 * @return the prefix notating this functional group
	 */
	public String Prefix (int group){
		String S="";
		S+=Locations(group);
		S+="-";
		S+=CountConverter.get(Sum(group));
		S+=PreConverter.get(group);
		return S;
	}
	
	/**
	 * Determines the postfix of the molecule
	 * @param group the group whose postfix is determined
	 * @return the string notating the postfix of this functional group
	 */
	public String PostFix (int group){
		String S="";
		S+=Locations(group);
		S+="-";
		S+=CountConverter.get(Sum(group));
		S+=PostConverter.get(group);
		return S;
	}
	/**
	 * Checks for presence of a carboxylic acid and adjusts the array storing functional groups accordingly
	 */
	public void CA ()
	{
		if ((functionalGroups[6-1][1-1]==1)&&(functionalGroups[4-1][1-1]==1))
		{
			System.out.println("Problem 1");
			functionalGroups[6-1][1-1]=0;
			functionalGroups[4-1][1-1]=0;
			functionalGroups[1-1][1-1]=1;
			priority = 1;
		}
		if ((functionalGroups[6-1][this.length-1]==1)&&(functionalGroups[4-1][this.length-1]==1))
		{
			System.out.println("Problem 2");
			functionalGroups[6-1][this.length-1]=0;
			functionalGroups[4-1][this.length-1]=0;
			functionalGroups[1-1][this.length-1]=1;
			priority = 1;
		}
	}
	/**
	 * Checks for presence of an amide and adjusts the array storing functional groups accordingly
	 */
	public void Amide()
	{
		if ((functionalGroups[7-1][1-1]==1)&&(functionalGroups[4-1][1-1]==1))
		{
			functionalGroups[7-1][1-1]=0;
			functionalGroups[4-1][1-1]=0;
			functionalGroups[3-1][1-1]=1;
			priority = 3;
		}
		if ((functionalGroups[7-1][this.length-1]==1)&&(functionalGroups[4-1][this.length-1]==1))
		{
			functionalGroups[7-1][this.length-1]=0;
			functionalGroups[4-1][this.length-1]=0;
			functionalGroups[3-1][this.length-1]=1;
			priority = 3;
		}
	}
	/**
	 * Switches which is the first Carbon in the chain
	 * Adjust functional groups and carbon numbers to create an accurate description of molecule
	 */
	public void SwitchFirst(){
		for (int i=1; i<=11; i++){
			for (int j=1; j<=length/2; j++)
			{
				int save=functionalGroups[i-1][j-1];
				functionalGroups[i-1][j-1]=functionalGroups[i-1][length-j];
				functionalGroups[i-1][length-j]=save;
			}
		}
		Carbon current=begin;
		Carbon next=begin;
		for (int i=1; i<=length-1; i++){
			next=current.NextCarbon();
			current.setCarbonNum(length-current.getCarbonNum()+1);
			current=next;			
		}
		next.setCarbonNum(1);
		end=begin;
		begin=next;
	}
	
	/**
	 * Determines which is t	he first carbon (CarbonNum 1) in chain
	 * Rule for deciding which is first is minimize sum of priority numbers
	 */
	public void PickC (){
		if ((IsItCyclo)&&(priority!=20))
		{
			int sum=0, sum2=0, firstC=-1;
			for (int i=1; i<=length; i++)
			{
				for (int j=1; j<=length; i++)
				{
					sum+=((j-i)%length)*functionalGroups[priority-1][j-1];
				}
				if ((sum<sum2)||(firstC==-1)) {
					sum2=sum;
					firstC=i;
				}
			}
		}
		else if (priority!=20){
			int sum=0;
			for (int i=1; i<=length; i++)
			{
				sum+=i*functionalGroups[priority-1][i-1];
			}
			int sum2=0;
			for (int i=1; i<=length; i++)
			{
				sum2+=(length-i)*functionalGroups[priority-1][i-1];
			}
			if (sum2<sum){
				SwitchFirst();
			}
		}
	}
	
	
	// begin methods
	
	/**
	 * Returns the first Carbon
	 * @return First Carbon of molecule
	 */
	public Carbon getBegin() {
		return begin;
	}
	
	/**
	 * Sets the variable begin, the first Carbon
	 * @param begin The new first Carbon
	 */
	public void setBegin(Carbon begin) {
		this.begin = begin;
	}
	
	
	// construction methods
	
	/**
	 * 
	 */
	public void construct(){
		construct(this.getBegin());
	}
	
	/** 
	 * @param atomCurrent
	 * @param atomPrevious
	 */
	public void construct(Carbon currentCarbon){
		g.drawImage(images.getDrawBackground(), 0, 0, null);
		Carbon tempCarbon = currentCarbon;
		while(currentCarbon != null){

			for(int i = 0;i < 4;i++){
				//need a try catch to find any atoms that arent being drawn.. can be deleted upon comlpetion
				try{
				if(currentCarbon.bondsArray.get(i).getEnd().getName() != "Oxygen"){
				if(i == 0){
					System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getHorizontalBond(), currentCarbon.getPosX()- 50, currentCarbon.getPosY()+10, null));
				}
				else if(i == 1){
					System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getHorizontalBond(), currentCarbon.getPosX()+ 30, currentCarbon.getPosY() + 10, null));
				}
				else if(i == 2){
					System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getVerticalBond(), currentCarbon.getPosX()+ 10, currentCarbon.getPosY() - 50, null));
				}
				else if(i == 3){
					System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getVerticalBond(), currentCarbon.getPosX()+ 10, currentCarbon.getPosY() + 30, null));
				}
				}
				if(currentCarbon.bondsArray.get(i).getEnd().getName() == "Oxygen"){
					Oxygen temp = (Oxygen)currentCarbon.bondsArray.get(i).getEnd();
					if(temp.bondPositions.x == 0){
						
						System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getDoubleHorizontalBond(), currentCarbon.getPosX()- 50, currentCarbon.getPosY()+10, null));
						
						//System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getHorizontalBond(), currentCarbon.getPosX()- 50, currentCarbon.getY()+10, null));
						System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getHorizontalBond(), currentCarbon.getPosX()+ 30, currentCarbon.getPosY() + 10, null));
						System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getVerticalBond(), currentCarbon.getPosX()+ 10, currentCarbon.getPosY() + 30, null));
					
						//left
					}
					if(temp.bondPositions.x == 1){
						
						System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getHorizontalBond(), currentCarbon.getPosX()- 50, currentCarbon.getPosY()+10, null));
						//System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getHorizontalBond(), currentCarbon.getPosX()+ 30, currentCarbon.getY() + 10, null));
						System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getDoubleHorizontalBond(), currentCarbon.getPosX()+ 30, currentCarbon.getPosY() + 10, null));
						System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getVerticalBond(), currentCarbon.getPosX()+ 10, currentCarbon.getPosY() - 50, null));
						
						// right
					}
					if(temp.bondPositions.x == 2){
						System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getHorizontalBond(), currentCarbon.getPosX()- 50, currentCarbon.getPosY()+10, null));
						//System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getVerticalBond(), currentCarbon.getPosX()+ 10, currentCarbon.getY() - 50, null));
						System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getDoubleVerticalBond(), currentCarbon.getPosX()+ 8, currentCarbon.getPosY() - 50, null));
						System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getVerticalBond(), currentCarbon.getPosX()+ 10, currentCarbon.getPosY() + 30, null));
					
						// up
						
	
					}
					if(temp.bondPositions.x == 3){
						System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getHorizontalBond(), currentCarbon.getPosX()+ 30, currentCarbon.getPosY() + 10, null));
						System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getVerticalBond(), currentCarbon.getPosX()+ 10, currentCarbon.getPosY() - 50, null));
						//System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getVerticalBond(), currentCarbon.getPosX()+ 10, currentCarbon.getY() + 30, null));
						System.out.println(g.drawImage(currentCarbon.bondsArray.get(i).images.getDoubleVerticalBond(), currentCarbon.getPosX()+ 8, currentCarbon.getPosY() + 30, null));
						// down
						
					}
				}
				}
				catch(Exception e){
					System.out.println("hellllooooo " + i);
					e.printStackTrace();
				}
			}
			currentCarbon = currentCarbon.NextCarbon();					
		}
		currentCarbon = tempCarbon;
		while(currentCarbon != null){

				for(int i = 0;i < 4;i++){
					//need a try catch to find any atoms that arent being drawn.. can be deleted upon comlpetion
					try{
					Atom currentAtom = currentCarbon.getBondsArray().get(i).getEnd();
					if(currentAtom.getName() != "Carbon" || currentAtom.getName() != "Oxygen"){
							System.out.println(currentAtom.getName() + " " + currentAtom.getPosX() + " " + currentAtom.getPosY() + " " + i);
							//System.out.println(currentAtom.getAtomImage().getHeight(null) + " " + currentAtom.getAtomImage().getWidth(null));
							g.drawImage(currentAtom.getAtomImage(), currentAtom.getPosX(), currentAtom.getPosY(), currentAtom.getAtomImage().getHeight(null) ,currentAtom.getAtomImage().getWidth(null), null);
						}
					else if(currentAtom.getName() == "Oxygen"){
						System.out.println("hello");
						Oxygen temp = (Oxygen)currentAtom;
						g.drawImage(currentCarbon.bondsArray.get(temp.bondPositions.x).getEnd().getAtomImage(), currentCarbon.bondsArray.get(temp.bondPositions.x).getEnd().getPosX(), currentCarbon.bondsArray.get(temp.bondPositions.x).getEnd().getPosY(), null);
					}
					}
					catch(Exception e){
						System.out.println("hellllooooo " + i);
						e.printStackTrace();
					}
				}
				g.drawImage(currentCarbon.getAtomImage(), currentCarbon.getPosX(), currentCarbon.getPosY(), currentCarbon.getAtomImage().getHeight(null),currentCarbon.getAtomImage().getWidth(null), null);
                System.out.println(currentCarbon.getName() + " " + currentCarbon.getPosX() + " " + currentCarbon.getPosY());
				currentCarbon = currentCarbon.NextCarbon();					
		}
		System.out.println();
	}
	
	
	/**
	 * Searches for an atom that was clicked
	 * @param mouseObject object to be used to determine if user input is within a specified atom
	 * */
	public Atom search(Point2D mouseObject){
		Carbon currentCarbon = begin;
		
		while(currentCarbon != null){
			for(int i = 0;i < currentCarbon.getBondsArray().size();i++){
				try{
				if(currentCarbon.getBondsArray().get(i).getEnd().getArea().contains(mouseObject))
					return currentCarbon.getBondsArray().get(i).getEnd();
				}
				catch(Exception e){
					
					
				}
			}
			currentCarbon = currentCarbon.NextCarbon();	
			
		}
		System.out.println("Atom not found");
		return null;
		
	}
	public Carbon mainCarbon(Atom atom){
		for(int i = 0;i < 4;i++){

			try{
				if(atom.getBondsArray().get(i).getEnd().getName().equals("Carbon")){
					return (Carbon)atom.getBondsArray().get(i).getEnd();
				}
			}
			catch(Exception e){
				
			}
		}
		return null;
	}
	
	/**
	 * Searches for an atom that was clicked
	 * @param mainC  main carbon that the old atom is connected to
	 * @param oldAtom atom to be replaced
	 * @param newAtom atom to be substituted
	 * */
	public boolean replaceWithHalogen(Carbon mainC, Atom oldAtom, Atom newAtom, int bondPosition){
		if(oldAtom.getName().equals(newAtom.getName())){
			return false;
		}
		

		mainC.RemoveBond(oldAtom);	
		mainC.BondTo(newAtom, bondPosition);
		return true;
	}
	
	/**
	 * Searches for an atom that was clicked
	 * @param mainC  main carbon that the old atom is connected to
	 * @param oldAtom atom to be replaced
	 * @param newAtom atom to be substituted
	 * */
	public boolean replaceWithCarbon(Carbon mainC, Atom oldAtom, Carbon newAtom){
		if(oldAtom.getName().equals(newAtom.getName())){
			return false;
		}
		int position = findBondPosition(mainC, oldAtom);
		this.addAtom(newAtom, mainC, position);
		return true;
	}

	// as the name suggests... it finds the position of the bond on the mainAtom(mostlikely a carbon) 
	public int findBondPosition(Atom mainAtom, Atom atomBeingReplaced){
		
		int position = -1;
		for(int i = 0; i < 4; i ++){
			try{
			if(atomBeingReplaced.equals(mainAtom.bondsArray.get(i).getEnd())){
				
				position = i;
			}
			}
			catch(Exception e){
				
				
			}
		}

		return position;
	}


	public void setEnd(Carbon end) {
		// TODO Auto-generated method stub
		this.end = end;
	}
}

