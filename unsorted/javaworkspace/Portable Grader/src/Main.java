import javax.swing.JApplet;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class Main extends JApplet{
	private Navigation nav;
	private AppScreen appScreen;
	
	public void start(){
		super.init();
		this.appScreen = new AppScreen(this);
		this.nav = new Navigation(this);
		addKeyListener(nav);
		addMouseListener(nav);
	}

	
	
}
