/** Name: William Granados
 *  Date: 09/06/14
 *  Purpose: Used as a skeleton for the unit class when a
 *  		 sword master player is to be created
 * */
public class SwordMaster extends Unit {
	
	/** Creates a unit of type sword master, stats are normal.
	 *  @param x units x position on the grid
	 *  @param y units y position on the grid
	 *  @param ally boolean for whether or not the unit belongs to the player*/
	public SwordMaster(int x, int y, boolean ally){
		super(x,y,"swordmaster",ally);
		this.setHitPoints(80);
		this.setAttack(50);
		this.setDefense(33);
		this.setMagic(34);
		this.setResistance(38);
		this.setSpeed(46);
		this.setSteps(6);
		this.setAlive(true);
		
	}
	
	/** Creates a unit of type sword master, stats are overpowered.
	 *  @param x units x position on the grid
	 *  @param y units y position on the grid
	 *  @param ally boolean for whether or not the unit belongs to the player
	 *  @param overPowered boolean for whether or not the unit is overpowered (value is insignificant but sound cools)*/
	public SwordMaster(int x, int y, boolean ally, boolean overPowered){
		super(x,y,"swordmaster",ally);
		this.setHitPoints(100);
		this.setAttack(60);
		this.setDefense(33);
		this.setMagic(34);
		this.setResistance(38);
		this.setSpeed(46);
		this.setSteps(7);
		this.setAlive(true);
		this.setOverPowered(overPowered);
		
	}
	
	
}
