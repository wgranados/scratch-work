/** Name: William Granados
 *  Date: 09/06/14
 *  Purpose: Used as a skeleton for the unit class when a
 *  		 knight player is to be created
 * */
public class Knight extends Unit {
	
	/** Creates a unit of type knight, stats are normal.
	 *  @param x units x position on the grid
	 *  @param y units y position on the grid
	 *  @param ally boolean for whether or not the unit belongs to the player*/
	public Knight(int x, int y, boolean ally){
		super(x,y,"knight",ally);
		this.setHitPoints(80);
		this.setAttack(48);
		this.setDefense(48);
		this.setMagic(20);
		this.setResistance(10);
		this.setSpeed(37);
		this.setSteps(3);
		this.setAlive(true);
	}
	
	/** Creates a unit of type knight, stats are overpowered.
	 *  @param x units x position on the grid
	 *  @param y units y position on the grid
	 *  @param ally boolean for whether or not the unit belongs to the player
	 *  @param overPowered boolean for whether or not the unit is overpowered (value is insignificant but sound cools)*/
	public Knight(int x, int y, boolean ally, boolean overPowered){
		super(x,y,"knight",ally);
		this.setHitPoints(100);
		this.setAttack(58);
		this.setDefense(48);
		this.setMagic(20);
		this.setResistance(10);
		this.setSpeed(37);
		this.setSteps(5);
		this.setAlive(true);
		this.setOverPowered(true);
	}
	

	
	


}
