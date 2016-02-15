/** Name: William Granados
 *  Date: 09/06/14
 *  Purpose: Used as a skeleton for the unit class when a
 *  		 warrior player is to be created
 * */
public class Warrior extends Unit{
	
	/** Creates a unit of type warrior, stats are normal.
	 *  @param x units x position on the grid
	 *  @param y units y position on the grid
	 *  @param ally boolean for whether or not the unit belongs to the player*/
	public Warrior(int x, int y,boolean ally){
		super(x,y,"warrior", ally);
		this.setHitPoints(80);
		this.setAttack(48);
		this.setDefense(40);
		this.setMagic(30);
		this.setResistance(110);
		this.setSpeed(40);
		this.setSteps(4);
		this.setAlive(true);
		
		
	}
	
	/** Creates a unit of type warrior, stats are normal.
	 *  @param x units x position on the grid
	 *  @param y units y position on the grid
	 *  @param ally boolean for whether or not the unit belongs to the player
	 *  @param overPowered boolean for whether or not the unit is overpowered (value is insignificant but sound cools)*/
	public Warrior(int x, int y,boolean ally, boolean overPowered){
		super(x,y,"warrior", ally);
		this.setHitPoints(100);
		this.setAttack(58);
		this.setDefense(40);
		this.setMagic(30);
		this.setResistance(110);
		this.setSpeed(40);
		this.setSteps(5);
		this.setAlive(true);
		this.setOverPowered(overPowered);
		
		
		
	}


	
}
