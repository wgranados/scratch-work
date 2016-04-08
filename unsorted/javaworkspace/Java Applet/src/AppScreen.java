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
	Navigation Nav = new Navigation(this, this.connectionToMain);
	BufferedImage mol = new BufferedImage(700,640,BufferedImage.TYPE_INT_RGB);
	Sprites images = new Sprites();
	
	// Molecule JButtons 	
	JButton Carbon = new JButton(){{
		this.setActionCommand("Carbon");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(80,80));
		this.setLocation(10, 10);
		this.addActionListener(Nav);
		this.setIcon(new ImageIcon(images.getCarbonMoleculeButton()));
	}};
	JButton Chlorine = new JButton(){{
		this.setActionCommand("Chlorine");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(80,80));
		this.addActionListener(Nav);
		this.setIcon(new ImageIcon(images.getChlorineMoleculeButton()));
	}};
	JButton Bromine = new JButton(){{
		this.setActionCommand("Bromine");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(80,80));
		this.addActionListener(Nav);
		this.setIcon(new ImageIcon(images.getBromineMoleculeButton()));
	}};
	JButton Fluorine = new JButton(){{
		this.setActionCommand("Fluorine");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(80,80));
		this.addActionListener(Nav);
		this.setIcon(new ImageIcon(images.getFluorineMoleculeButton()));
	}};
	JButton Iodine = new JButton(){{
		this.setActionCommand("Iodine");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(80,80));
		this.addActionListener(Nav);
		this.setIcon(new ImageIcon(images.getIodineMoleculeButton()));
	}};
	JButton Hydroxide = new JButton(){{
		this.setActionCommand("Hydroxide");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(80,80));
		this.addActionListener(Nav);
		this.setIcon(new ImageIcon(images.getHydroxideMoleculeButton()));
	}};
	JButton NH = new JButton(){{
		this.setActionCommand("NH");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(80,80));
		this.addActionListener(Nav);
		this.setIcon(new ImageIcon(images.getNitrogenMonoHydrideMoleculeButton()));
	}};
	JButton NH2 = new JButton(){{
		this.setActionCommand("NH2");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(80,80));
		this.addActionListener(Nav);
		this.setIcon(new ImageIcon(images.getNitrogenDiHydrideMoleculeButton()));
	}};
	JButton NO2 = new JButton(){{
		this.setActionCommand("NO2");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(80,80));
		this.setMargin(new Insets(0, 0, 0, 0));
		this.addActionListener(Nav);
		this.setIcon(new ImageIcon(images.getNitrogenDiOxideMoleculeButton()));
	}};
	JButton Nitrogen = new JButton(){{
		this.setActionCommand("Nitrogen");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(80,80));
		this.setMargin(new Insets(0, 0, 0, 0));
		this.addActionListener(Nav);
		this.setIcon(new ImageIcon(images.getNitrogenMoleculeButton()));
	}};
	JButton Oxygen = new JButton(){{
		this.setActionCommand("Oxygen");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(80,80));
		this.setMargin(new Insets(0, 0, 0, 0));
		this.addActionListener(Nav);
		this.setIcon(new ImageIcon(images.getOxygenMoleculeButton()));
	}};
	
	// Bond JButtons
	JButton sBond = new JButton(){{
		this.setActionCommand("sBond");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(70,40));
		this.setIcon(new ImageIcon(images.getSBond()));
		this.addActionListener(Nav);
	}};
	JButton dBond = new JButton(){{
		this.setActionCommand("dBond");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(70,40));
		this.setIcon(new ImageIcon(images.getDBond()));
		this.addActionListener(Nav);
	}};
	JButton tBond = new JButton(){{
		this.setActionCommand("tBond");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(70,40));
		this.setIcon(new ImageIcon(images.getTBond()));
		this.addActionListener(Nav);
	}};
	
	
	JButton go = new JButton(){{
		this.setActionCommand("Go");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(120,50));
		this.setIcon(new ImageIcon(images.getGo()));
		this.addActionListener(Nav);
	}};
	JButton delete = new JButton(){{
		this.setActionCommand("Delete");
		this.setVisible(true);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setPreferredSize(new Dimension(120,50));
		this.setIcon(new ImageIcon(images.getDeleteBond()));
		this.addActionListener(Nav);
	}};
	JRadioButton make = new JRadioButton(){{
		this.setActionCommand("Make");
		this.setSelected(true);
	}};
	JRadioButton draw = new JRadioButton(){{
		this.setActionCommand("Draw");
	}};
	
	JTextArea text = new JTextArea(){{
		this.setPreferredSize(new Dimension(220, 150));
		this.setBackground(Color.gray);
	}};
	
	DrawArea drawArea = new DrawArea(){{
		this.setPreferredSize(new Dimension(700,640));
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setOpaque(false);
		this.setVisible(true);
	}};
	
	JPanel molecules = new JPanel(){{
		
		this.setPreferredSize(new Dimension(260, 250));
		this.setLayout(new GridLayout(4,3));
		this.setOpaque(false);
		this.add(Carbon);
		this.add(Chlorine);
		this.add(Fluorine);
		this.add(Bromine);
		this.add(Iodine);
		this.add(Hydroxide);
		this.add(NH);
		this.add(NH2);
		this.add(NO2);
		this.add(Nitrogen);
		this.add(Oxygen);
	}};
	
	ToolBox toolbox = new ToolBox(){{
		this.setPreferredSize(new Dimension(260, 640));
		this.setAlignmentX(RIGHT_ALIGNMENT);
		this.add(new JPanel(){{
			this.setPreferredSize(new Dimension (260,25));
			this.setOpaque(false);
		}});
		this.add(molecules);
		this.add(new JPanel(){{
			this.setPreferredSize(new Dimension (260,25));
			this.setOpaque(false);
		}});
		this.add(new JPanel(){{
			
			this.setPreferredSize(new Dimension (260,60));
			this.setOpaque(false);
			this.add(sBond);
			this.add(dBond);
			this.add(tBond);
		}});
		this.add(new JPanel(){{
			
			this.setPreferredSize(new Dimension (260,60));
			this.setOpaque(false);
			this.add(go);
			this.add(delete);
		}});
		
		this.add(make);
		this.add(draw);
		this.add(text);
		JButton hi = new JButton();
		hi.addActionListener(Nav);
		this.add(hi);
		///this.add(comp);
		//this.add(comp);
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
		this.addMouseListener(this.Nav);
		this.addKeyListener(this.Nav);
		this.Nav.app = this;
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
			g.drawImage(images.getToolBoxBackground(), 0, 0, null);
			
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

