/** Name: William Granados
 *  Date: 09/06/14
 *  Purpose: handles the the decision making for the computer units
 * */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;


public class ArtificialIntelligence {
	private Tile[][] levelLayout;
	
	private boolean[][] visited = new boolean[24][32];
	
	// strategies
	/** Employs a breadth-first search and finds the nearest enemy
	 *  @param computerUnit unit that will execute this strategy*/
	public void excecuteEasyStrategy(Unit computerUnit){
		Queue<Pair> queue = new LinkedList<Pair>();
		
		queue.add( new Pair(computerUnit.getX(),computerUnit.getY(), computerUnit.getX(), computerUnit.getY(), 0));
		
		while(!queue.isEmpty()){
			int x = queue.peek().getX();
			int y = queue.peek().getY();
			int prevX = queue.peek().getPrevX();
			int prevY = queue.peek().getPrevY();
			int d = queue.peek().getDepth();
			
			queue.poll();
			
			//System.out.println("Traversing tile " + "X: " + x + "Y:" + y);
			if(x < 0 || y < 0 || x > 23 || y > 31){
				continue;
			}
			if(d > computerUnit.getSteps()){
				continue;
			}
			if(visited[x][y]){
				continue;
			}
			visited[x][y] = true;
			if(!levelLayout[x][y].getIsTraversable()){
				continue;
			}
			
			
			if(levelLayout[x][y].getOccupiedUnit() != null){
				if(levelLayout[x][y].getOccupiedUnit().isAlly() == true){
					
					computerUnit.attack(levelLayout[x][y].getOccupiedUnit());
					if(!levelLayout[x][y].getOccupiedUnit().isAlive())
						levelLayout[x][y].setOccupiedUnit(null);
					
					levelLayout[ prevX][ prevY ].setOccupiedUnit( levelLayout[ computerUnit.getX() ][ computerUnit.getY() ].getOccupiedUnit() );
					levelLayout[ computerUnit.getX()][ computerUnit.getY() ].setOccupiedUnit(null); 
					
					computerUnit.setX(prevX);
					computerUnit.setY(prevY);
					
					break;
				}
				else if(levelLayout[x][y].getOccupiedUnit() != computerUnit)
					continue;
				
			}
			
			
			queue.add( new Pair(x+1, y , x, y , d+1));
			queue.add( new Pair(x-1, y , x, y , d+1));
			queue.add( new Pair(x, y+1 , x, y , d+1));
			queue.add( new Pair(x, y-1 , x, y , d+1));
			
			
		}
		resetVisited();
	}
	/** Employs a depth-first search and finds the nearest enemies.
	 *  Enemies are then stored and sorted in order of decreasing damage output.
	 *  @param computerUnit unit that will execute this strategy*/
	public void executeIntermediateStrategy(Unit computerUnit){
		Stack<Pair> stack = new Stack<Pair>();
		Vector<ComparisonPair> potentialEnemyUnits = new Vector<ComparisonPair>();
		
		stack.add( new Pair(computerUnit.getX(),computerUnit.getY(), computerUnit.getX(), computerUnit.getY(), 0));
		
		while(!stack.isEmpty()){
			int x = stack.peek().getX();
			int y = stack.peek().getY();
			int prevX = stack.peek().getPrevX();
			int prevY = stack.peek().getPrevY();
			int d = stack.peek().getDepth();
			
			stack.pop();
			
			if(x < 0 || y < 0 || x > 23 || y > 31){
				continue;
			}
			if(d > computerUnit.getSteps()){
				continue;
			}
			if(visited[x][y]){
				continue;
			}
			visited[x][y] = true;
			if(!levelLayout[x][y].getIsTraversable()){
				continue;
			}
			
			
			if(levelLayout[x][y].getOccupiedUnit() != null){
				if(levelLayout[x][y].getOccupiedUnit().isAlly() == true){
					potentialEnemyUnits.add( new ComparisonPair(prevX,prevY, levelLayout[x][y].getOccupiedUnit()));
					
				}
				else if(levelLayout[x][y].getOccupiedUnit() != computerUnit)
					continue;
				
			}
			
			
			stack.add( new Pair(x+1, y , x, y , d+1));
			stack.add( new Pair(x-1, y , x, y , d+1));
			stack.add( new Pair(x, y+1 , x, y , d+1));
			stack.add( new Pair(x, y-1 , x, y , d+1));
			
			
		}
		// bubble sort because that's all I know how to implement :(
		int exchanges = 0;
		for(int i = 0;i < potentialEnemyUnits.size();i++){
			exchanges = 0;
			for(int j = 0; j < potentialEnemyUnits.size()-1;j++){
				if(computerUnit.damageDealt(potentialEnemyUnits.get(j).getUnit()) < computerUnit.damageDealt(potentialEnemyUnits.get(j+1).getUnit())){
					 ComparisonPair temp = potentialEnemyUnits.get(j);
					 potentialEnemyUnits.set(j, potentialEnemyUnits.get(j+1));
					 potentialEnemyUnits.set(j+1, temp);
					 exchanges++;
				}
				
			}
			if(exchanges==0)
	            break;
		}
		if(potentialEnemyUnits.size() > 0){
			computerUnit.attack(potentialEnemyUnits.get(0).getUnit());
			if(!levelLayout[potentialEnemyUnits.get(0).getUnit().getX()][potentialEnemyUnits.get(0).getUnit().getY()].getOccupiedUnit().isAlive())
				levelLayout[potentialEnemyUnits.get(0).getUnit().getX()][potentialEnemyUnits.get(0).getUnit().getY()].setOccupiedUnit(null);
			
			levelLayout[ potentialEnemyUnits.get(0).getPrevX() ][ potentialEnemyUnits.get(0).getPrevY() ].setOccupiedUnit( levelLayout[ computerUnit.getX() ][ computerUnit.getY() ].getOccupiedUnit() );
			levelLayout[ computerUnit.getX()][ computerUnit.getY() ].setOccupiedUnit(null); 
			
			computerUnit.setX(potentialEnemyUnits.get(0).getPrevX());
			computerUnit.setY(potentialEnemyUnits.get(0).getPrevY());
		}
		resetVisited();
		
	}
	/** Employs a depth-first search and finds the nearest enemies.
	 *  Enemies are then stored and sorted in order of likelihood to be koe'd by a hit.
	 *  @param computerUnit unit that will execute this strategy*/
	public void executeExpertStrategy(Unit computerUnit){
		Stack<Pair> stack = new Stack<Pair>();
		Vector<ComparisonPair> potentialEnemyUnits = new Vector<ComparisonPair>();
		
		stack.add( new Pair(computerUnit.getX(),computerUnit.getY(), computerUnit.getX(), computerUnit.getY(), 0));
		
		while(!stack.isEmpty()){
			int x = stack.peek().getX();
			int y = stack.peek().getY();
			int prevX = stack.peek().getPrevX();
			int prevY = stack.peek().getPrevY();
			int d = stack.peek().getDepth();
			
			stack.pop();
			
			if(x < 0 || y < 0 || x > 23 || y > 31){
				continue;
			}
			if(d > computerUnit.getSteps()){
				continue;
			}
			if(visited[x][y]){
				continue;
			}
			visited[x][y] = true;
			if(!levelLayout[x][y].getIsTraversable()){
				continue;
			}
			
			
			if(levelLayout[x][y].getOccupiedUnit() != null){
				if(levelLayout[x][y].getOccupiedUnit().isAlly() == true){
					potentialEnemyUnits.add( new ComparisonPair(prevX,prevY, levelLayout[x][y].getOccupiedUnit()));
					
				}
				else if(levelLayout[x][y].getOccupiedUnit() != computerUnit)
					continue;
				
			}
			
			
			stack.add( new Pair(x+1, y , x, y , d+1));
			stack.add( new Pair(x-1, y , x, y , d+1));
			stack.add( new Pair(x, y+1 , x, y , d+1));
			stack.add( new Pair(x, y-1 , x, y , d+1));
			
			
		}
		// bubble sort because that's all I know how to implement :(
		int exchanges = 0;
		for(int i = 0;i < potentialEnemyUnits.size();i++){
			exchanges = 0;
			for(int j = 0; j < potentialEnemyUnits.size()-1;j++){
				if(potentialEnemyUnits.get(j).getUnit().getHitPoints() > potentialEnemyUnits.get(j+1).getUnit().getHitPoints() ){
					 ComparisonPair temp = potentialEnemyUnits.get(j);
					 potentialEnemyUnits.set(j, potentialEnemyUnits.get(j+1));
					 potentialEnemyUnits.set(j+1, temp);
					 exchanges++;
				}
				
			}
			if(exchanges==0)
	            break;
		}
		if(potentialEnemyUnits.size() > 0){
			computerUnit.attack(potentialEnemyUnits.get(0).getUnit());
			if(!levelLayout[potentialEnemyUnits.get(0).getUnit().getX()][potentialEnemyUnits.get(0).getUnit().getY()].getOccupiedUnit().isAlive())
				levelLayout[potentialEnemyUnits.get(0).getUnit().getX()][potentialEnemyUnits.get(0).getUnit().getY()].setOccupiedUnit(null);
			
			levelLayout[ potentialEnemyUnits.get(0).getPrevX() ][ potentialEnemyUnits.get(0).getPrevY() ].setOccupiedUnit( levelLayout[ computerUnit.getX() ][ computerUnit.getY() ].getOccupiedUnit() );
			levelLayout[ computerUnit.getX()][ computerUnit.getY() ].setOccupiedUnit(null); 
			
			computerUnit.setX(potentialEnemyUnits.get(0).getPrevX());
			computerUnit.setY(potentialEnemyUnits.get(0).getPrevY());
		}
		resetVisited();
	}

	// reset methods
	/** resets the visited array used when searching for enemies*/
	public void resetVisited(){
		for(int i = 0;i < 24;i++){
			for(int j = 0;j < 32;j++){
				visited[i][j] = false;
			}
		}
		
	}
	
	// getters and setters
	/** returns the level layout version of the AI*/
	public Tile[][] getLevelLayout() {
		return levelLayout;
	}
	/** sets the level layout version of the AI*/
	public void setLevelLayout(Tile[][] levelLayout) {
		this.levelLayout = levelLayout;
	}

	
	
}


