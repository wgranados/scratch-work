package williamBots;
import robocode.*;
import java.awt.Color;

public class Terminated extends AdvancedRobot
{	
	private boolean targetFound;
	private int counter;

	public void run() {
		initAppearence();
		targetFound = false;
		//setAdjustGunForRobotTurn(true);
		while(true) {
			if(!targetFound){
				turnRight(20);
				ahead(200);
				scan();
				//turnRight(25);
			}
			this.targetFound = false;
		}
	}
	public void initAppearence(){
		setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.black);
		setBulletColor(Color.green);
		setScanColor(Color.black);
	}

	// normalizes a bearing to between +180 and -180
	double normalizeBearing(double angle) {
		while (angle >  180) angle -= 360;
		while (angle < -180) angle += 360;
		return angle;
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		this.targetFound = true;
		this.counter++; 
		setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
	//turnRight(e.getBearing());
		fire(Math.min(400 / e.getDistance(), 3));
  		out.println("found: " + e.getDistance());
		ahead(e.getDistance());
		scan();
	}
	
	 public void onHitRobot(HitRobotEvent e) {
            
            fire(3);
            ahead(50);
      }
	public void onBulletHit(BulletHitEvent bhe){
		counter = 0;		
		out.println(counter);
	}
	public void onHitByBullet(HitByBulletEvent e) {
		turnRight(20);
		ahead(100);
	}
	
	public void onHitWall(HitWallEvent e) {
		turnRight(45);
	}	
}


