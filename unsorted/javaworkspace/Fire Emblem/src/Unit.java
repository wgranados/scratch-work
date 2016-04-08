/** Name: William Granados
 *  Date: 09/06/14
 *  Purpose: Used as the bases for the creation of a unit that is
 *  		 to be used by the player or computer.
 * */
public class Unit {
	private int x,y;
	
	
	private int hitPoints;
	private int attack;
	private int defense;
	private int speed;
	private int magic;
	private int resistance;
	private int steps;
	private boolean ally;
	private boolean alive;
	private boolean movable;
	private boolean overPowered;
	
	private String playerClass;
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	// constructors 	
	public Unit(int x, int y, String playerClass, boolean ally){
		this.setX(x);
		this.setY(y);
		this.setPlayerClass(playerClass);
		this.setAlly(ally);
		this.setMovable(true);
		
	}
	
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	/** Returns a modifier to a units attack based on the enemies player class.
	 *  @param enemyClass class of the enemy
	 *  */
	public double modifier(String enemyClass){
		
		
		if(this.playerClass.equals("swordmaster")){
			if(enemyClass.equals("warrior")){
				return 2;
			}
			else if(enemyClass.equals("knight")){
				return 0.5;
			}
		}
		
		else if(this.playerClass.equals("knight")){
			if(enemyClass.equals("swordmaster")){
				return 2;
			}
			else if(enemyClass.equals("warrior")){
				return 0.5;
			}
		}
		
		else if(this.playerClass.equals("warrior")){
			if(enemyClass.equals("knight")){
				return 2;
			}
			else if(enemyClass.equals("swordmaster")){
				return 0.5;
			}
		}
		
		return 1;
		
	}
	
	/** Attacks the unit specified.
	 *  @param enemyUnit specified unit to be attacked*/
	public void attack(Unit enemyUnit){
		
		double damage = 0;
		
		if(this.playerClass.equals("sorceror"))
			damage =  this.modifier(enemyUnit.getPlayerClass()) * Math.abs(enemyUnit.getResistance()-2*this.getMagic()) /3; 
		else
			damage =  this.modifier(enemyUnit.getPlayerClass()) * Math.abs(enemyUnit.getDefense()-2*this.getAttack())  /3; 
		
		double retaliate = 0;
		
		if(enemyUnit.getPlayerClass().equals("sorceror"))
			retaliate =  enemyUnit.modifier(this.getPlayerClass()) * Math.abs(this.getResistance()-2*enemyUnit.getMagic()) /3; 
		else
			retaliate =  enemyUnit.modifier(this.getPlayerClass()) * Math.abs(this.getDefense()-2*enemyUnit.getAttack()) /3; 
		
		
		
		// enemy unit is faster
		if(enemyUnit.getSpeed() > this.getSpeed()){
			this.setHitPoints(this.getHitPoints() - (int)retaliate);
			if(this.getHitPoints() <= 0){
				this.setAlive(false);
				return;
			}
			else{
				enemyUnit.setHitPoints(enemyUnit.getHitPoints() - (int)damage);
				if(enemyUnit.getHitPoints() <= 0){
					enemyUnit.setAlive(false);
					return;
				}
				
			}
			
		}
		
		else{
			enemyUnit.setHitPoints(enemyUnit.getHitPoints() - (int)damage);
			if(enemyUnit.getHitPoints() <= 0){
				enemyUnit.setAlive(false);
				return;
			}
			else{
				this.setHitPoints(this.getHitPoints() - (int)retaliate);
				if(this.getHitPoints() <= 0){
					this.setAlive(false);
					return;
				}
				
			}
				
		}
		
	}
	
	/** Returns the amount of damage that can be dealt through attacking the specified unit*/
	public int damageDealt(Unit enemyUnit){
		double damage = 0;
		
		if(this.playerClass.equals("sorceror"))
			damage =  this.modifier(enemyUnit.getPlayerClass()) * Math.abs(enemyUnit.getResistance()-2*this.getMagic()) /3; 
		else
			damage =  this.modifier(enemyUnit.getPlayerClass()) * Math.abs(enemyUnit.getDefense()-2*this.getAttack())  /3; 
		
		return (int)damage;
	}
	
	/** Returns the amount of damage that can be incurred through attacking the specified unit*/
	public int damageIncurred(Unit enemyUnit){
		double retaliate = 0;
		
		if(enemyUnit.getPlayerClass().equals("sorceror"))
			retaliate =  enemyUnit.modifier(this.getPlayerClass()) * Math.abs(this.getResistance()-2*enemyUnit.getMagic()) /3; 
		else
			retaliate =  enemyUnit.modifier(this.getPlayerClass()) * Math.abs(this.getDefense()-2*enemyUnit.getAttack()) /3; 
		
		
		return (int)retaliate;
	}
	
	/** Checks whether or not the units are the same*/
	public boolean equals(Unit comparisonUnit){
		if(this.getX() == comparisonUnit.getX()){
			if(this.getY() == comparisonUnit.getY()){
				return true;
			}
		}
		return true;
	}

	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	//getters & setters
	/** Returns the x position of the unit.*/
	public int getX(){
		return x;
	}
	/** Sets the x position of the unit.
	 *  @param x value to be substituted*/
	public void setX(int x){
		this.x = x;
	}
	
	/** Returns the y position of the unit.*/
	public int getY(){
		return y;
	}
	/** Sets the y position of the unit.
	 *  @param y value to be substituted*/
	public void setY(int y){
		this.y = y;
	}
	
	/** Returns the player class of the unit.*/
	public String getPlayerClass() {
		return playerClass;
	}
	/** Sets the player class of the unit.
	 *  @param playerClass value to be substituted*/
	public void setPlayerClass(String playerClass) {
		this.playerClass = playerClass;
	}

	/** Returns the maximum steps of the unit can traverse.*/
	public int getSteps() {
		return steps;
	}
	/** Sets the maximum steps of the unit.
	 *  @param steps value to be substituted*/
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	/** Returns the hit points stat of the unit.*/
	public int getHitPoints() {
		return hitPoints;
	}
	/** Sets the hit points stat of the unit.
	 *  @param hitPoints value to be substituted*/
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	/** Returns the attack stat of the unit.*/
	public int getAttack() {
		return attack;
	}
	/** Sets the attack stat of the unit.
	 *  @param attack value to be substituted*/
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/** Returns the defense state of the unit.*/
	public int getDefense() {
		return defense;
	}
	/** Sets the defense stat of the unit.
	 *  @param defense value to be substituted*/
	public void setDefense(int defense) {
		this.defense = defense;
	}

	/** Returns the magic stat of the unit.*/
	public int getMagic() {
		return magic;
	}
	/** Sets the magic stat of the unit.
	 *  @param magic value to be substituted*/
	public void setMagic(int magic) {
		this.magic = magic;
	}

	/** Returns the resistance stat of the unit.*/
	public int getResistance() {
		return resistance;
	}
	/** Sets the resistance stat of the unit.
	 *  @param resistance value to be substituted*/
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	/** Returns the speed stat of the unit.*/
	public int getSpeed() {
		return speed;
	}
	/** Sets the speed stat of the unit.
	 *  @param speed value to be substituted*/
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/** Returns whether or not the unit is an ally.*/
	public boolean isAlly() {
		return ally;
	}
	/** Sets the whether or not the unit is an ally.
	 *  @param ally value to be substituted*/
	public void setAlly(boolean ally) {
		this.ally = ally;
	}

	/** Returns whether or not the unit is alive.*/
	public boolean isAlive() {
		return alive;
	}
	/** Sets whether or not the unit is alive.
	 *  @param alive value to be substituted*/
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/** Returns whether or not the unit can move.*/
	public boolean isMovable() {
		return movable;
	}
	/** Sets whether or not the unit is movable.
	 *  @param movable value to be substituted*/
	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	/** Returns whether or not the unit is overpowered.*/
	public boolean isOverPowered() {
		return overPowered;
	}
	/** Sets whether or not the unit is overpowered.
	 *  @param overpowered value to be substituted*/
	public void setOverPowered(boolean overPowered) {
		this.overPowered = overPowered;
	}
	
}
