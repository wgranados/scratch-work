
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.EventListener;


public class Navigation implements KeyListener, MouseListener, ActionListener{
	
	private boolean mouseClicked;
	String toAdd;
	AppScreen app = null;
	private Point2D mouse;
	
	public Navigation(Main m){
		//this.app = app;
		mouse = new Point2D.Double();
	}
	
	
	// mouse clicked
	public boolean isMouseClicked() {
		return mouseClicked;
	}
	public void setMouseClicked(boolean mouseClicked) {
		this.mouseClicked = mouseClicked;
	}

	// KeyListener
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	// MouseListener
	public void mousePressed(MouseEvent e) {
		this.setMouse(e.getX(), e.getY());
		this.setMouseClicked(true);
	}
	public void mouseReleased(MouseEvent e) {
		this.setMouseClicked(false);
	}
	
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		this.setMouse(x,y);
		
	}
	public void mouseEntered(MouseEvent e) {
		this.setMouse(e.getX(), e.getY());
	}
	public void mouseExited(MouseEvent arg0) {
		this.setMouseClicked(false);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		toAdd = a.getActionCommand();
	}

	public Point2D getMouse() {
		return this.mouse;
	}

	private void setMouse(int mouseX, int mouseY) {
		this.mouse.setLocation(mouseX,mouseY);
	}


}
