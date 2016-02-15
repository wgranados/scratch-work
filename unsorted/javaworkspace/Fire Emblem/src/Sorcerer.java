/** Name: William Granados
 *  Date: 09/06/14
 *  Purpose: Used as a skeleton for the unit class when a
 *  		 Sorcerer player is to be created
 * */
public class Sorcerer extends Unit{
	
	/** Creates a unit of type sorcerer, stats are normal.
	 *  @param x units x position on the grid
	 *  @param y units y position on the grid
	 *  @param ally boolean for whether or not the unit belongs to the player*/
	public Sorcerer(int x, int y,boolean ally){
		super(x,y,"sorceror",ally);
		this.setHitPoints(80);
		this.setAttack(30);
		this.setDefense(30);
		this.setMagic(70);
		this.setResistance(110);
		this.setSpeed(40);
		this.setSteps(5);
		this.setAlive(true);
		
	}
	
	/** Creates a unit of type sorcerer, stats are overpowered.
	 *  @param x units x position on the grid
	 *  @param y units y position on the grid
	 *  @param ally boolean for whether or not the unit belongs to the player
	 *  @param overPowered boolean for whether or not the unit is overpowered (value is insignificant but sound cools)*/
	public Sorcerer(int x, int y,boolean ally, boolean overPowered){
		super(x,y,"sorceror",ally);
		this.setHitPoints(100);
		this.setAttack(30);
		this.setDefense(30);
		this.setMagic(80);
		this.setResistance(110);
		this.setSpeed(40);
		this.setSteps(6);
		this.setAlive(true);
		this.setOverPowered(overPowered);
		
	}
	


	
}
