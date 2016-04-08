/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: Handle the funtionality of the molecule 
 * */
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// missing a few of the atom buttons right now
@SuppressWarnings("serial")
public class AppScreen extends JPanel{

	Main connectionToMain;
	BufferedImage mol = new BufferedImage(700,640,BufferedImage.TYPE_INT_RGB);

	
	
	ToolBox toolbox = new ToolBox(){
		
	};
	DrawArea drawArea = new DrawArea(){{
		this.setPreferredSize(new Dimension(700,640));
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setOpaque(false);
		this.setVisible(true);
	}};
	/** Creates the main graphical user interface used to display the program to the user*/
	public AppScreen(Main m){
		super();
		this.connectionToMain = m;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(960, 640));
		this.setVisible(true);
		this.add(this.drawArea, BorderLayout.WEST);
		this.add(this.toolbox, BorderLayout.EAST);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}

	/** Creates a ToolBox class used to update the toolbox layout*/	
	class ToolBox extends JPanel{

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			//g.drawImage(images.getToolBoxBackground(), 0, 0, null);
			
		}
	}	
	/** Create a DrawArea class used to update the contents present in the drawArea section*/
	class DrawArea extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(mol, 0, 0, null);
		}
		
	}


}

