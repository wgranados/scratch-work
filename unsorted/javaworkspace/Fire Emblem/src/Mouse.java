/**Name: William Granados
 * Date: 09/06/14
 * Purpose: Handles mouse navigation between game states
 * 			and player selections
 * */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;


public class Mouse implements MouseMotionListener, MouseListener{
	
	private boolean mouseClick = false;
	private Point2D mouse = new Point2D.Double();

	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	// getters and setters
	/**returns a boolean that represents whether the mouse was clicked*/
	public boolean getMouseClick(){
		return mouseClick;
	}
	/** sets the boolean that represents whether the mouse was clicked*/
	public void setMouseClick(boolean mouseClick){
		this.mouseClick = mouseClick;
	}
	/** returns the point2d object of the mouse*/
	public Point2D getMousePoint2D(){
		return mouse;
	}
	
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	//mouse listener
	/** based on user input assesses whether the mouse was clicked*/
	public void mouseClicked(MouseEvent e) {
		this.mouseClick = true;
	}
	/** based on user input assesses whether the mouse is within the graphical interface*/
	public void mouseEntered(MouseEvent e) {
	}
	/** based on user input assesses whether the mouse is outside the graphical interface*/
	public void mouseExited(MouseEvent e) {}
	/** based on user input assesses whether the mouse is being pressed*/
	public void mousePressed(MouseEvent e) {}
	/** based on user input assesses whether the mouse is being dragged*/
	public void mouseDragged(MouseEvent e) {}
	/** based on user input assesses whether the mouse is being released*/
	public void mouseReleased(MouseEvent e) {
		this.mouseClick = false;
	}
	/** based on user input assesses whether the mouse is moving and where to it is moving*/
	public void mouseMoved(MouseEvent e) {
		this.mouse = new Point2D.Double(e.getX(),e.getY());
	}


}
