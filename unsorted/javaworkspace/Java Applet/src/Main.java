/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: main class that connects the front end and the back end
 * */
import javax.swing.JApplet;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class Main extends JApplet {
	

	private Navigation nav;
	private Sprites sprites;
	private Molecule molecule;
	private AppScreen appScreen = new AppScreen(this);
	private Navigation n;
	private boolean beginState;
	
	/** initiates variables used*/
	public void start(){
		this.nav = new Navigation(appScreen, this);
		this.sprites = new Sprites();
		beginState = true;
		super.init();
		setSize(960,640);
		addKeyListener(nav);
		addMouseListener(nav);
		this.n = appScreen.Nav;
		this.add(appScreen);
		molecule = new Molecule();
	}
	
	/** Serves the purpose of adding molecules to the screen
	 *  @param toAdd type of molecule to be added to the screen
	 *  @param mouse Point2D mouse object of the users mouse
	 * */
	public void add(String toAdd, Point2D mouse){
		if(toAdd == "Carbon" && this.beginState){
			Carbon first = new Carbon((int)mouse.getX(), (int)mouse.getY(), sprites.getCarbonMolecule());
			molecule.StartMolecule(first);
			this.beginState = false;
			
			molecule.construct();
			appScreen.mol = molecule.onScreen;
			appScreen.drawArea.repaint();
			
		}
		else if(toAdd == "Carbon" && !this.beginState){
			Atom replacable = molecule.search(mouse);
			
			if(replacable == null){ 
				System.out.println("Error");
				return;
			}
			Carbon respectiveCarbon = molecule.mainCarbon(replacable);
			Carbon newCarbon = new Carbon(replacable.getPosX(), replacable.getPosY(), sprites.getCarbonMolecule());
			if( !molecule.replaceWithCarbon(respectiveCarbon, replacable, newCarbon)){
				System.out.println("Error");
				return;
			}
			newCarbon.FillH();

			
			molecule.construct();
			appScreen.mol = molecule.onScreen;
			appScreen.drawArea.repaint();
			
		}
		else if(toAdd == "Chlorine" && !this.beginState){
			
			Atom replacable = molecule.search(mouse);
			if(replacable == null){ 
				System.out.println("Error");
				return;
			}
			Carbon respectiveCarbon = (Carbon)molecule.mainCarbon(replacable);
			Chlorine newChlorine = new Chlorine(replacable.getPosX(), replacable.getPosY(), sprites.getChlorineMolecule());
			
			molecule.addAtom(newChlorine, respectiveCarbon, molecule.findBondPosition(respectiveCarbon, replacable));
				
			
			molecule.construct();
			appScreen.mol = molecule.onScreen;
			appScreen.drawArea.repaint();		
			
		}
		else if(toAdd == "Fluorine" && !this.beginState){
			
			Atom replacable = molecule.search(mouse);
			if(replacable == null){ 
				System.out.println("Error");
				return;
			}
			Carbon respectiveCarbon = (Carbon)molecule.mainCarbon(replacable);
			Fluorine newFluorine = new Fluorine(replacable.getPosX(), replacable.getPosY(), sprites.getFluorineMolecule());
			
			//System.out.println();
			molecule.addAtom(newFluorine, respectiveCarbon, molecule.findBondPosition(respectiveCarbon, replacable));
			//molecule.addAtom(newFluorine, respectiveCarbon, molecule.findBondPosition(respectiveCarbon, replacable));
			
			
			molecule.construct();
			appScreen.mol = molecule.onScreen;
			appScreen.drawArea.repaint();		
			
		}
		else if(toAdd == "Bromine" && !this.beginState){
			
			Atom replacable = molecule.search(mouse);
			if(replacable == null){ 
				System.out.println("Error");
				return;
			}
			Carbon respectiveCarbon = (Carbon)molecule.mainCarbon(replacable);
			Bromine newBromine = new Bromine(replacable.getPosX(), replacable.getPosY(), sprites.getBromineMolecule());
			molecule.addAtom(newBromine, respectiveCarbon, molecule.findBondPosition(respectiveCarbon, replacable));
			
			
			molecule.construct();
			appScreen.mol = molecule.onScreen;
			appScreen.drawArea.repaint();		
			
		}
		else if(toAdd == "Iodine" && !this.beginState){
			
			Atom replacable = molecule.search(mouse);
			if(replacable == null){ 
				System.out.println("Error");
				return;
			}
			Carbon respectiveCarbon = (Carbon)molecule.mainCarbon(replacable);
			Iodine newIodine = new Iodine(replacable.getPosX(), replacable.getPosY(), sprites.getIodineMolecule());
			molecule.addAtom(newIodine, respectiveCarbon, molecule.findBondPosition(respectiveCarbon, replacable));
			
			
			molecule.construct();
			appScreen.mol = molecule.onScreen;
			appScreen.drawArea.repaint();		
			
		}
		else if(toAdd == "Hydroxide" && !this.beginState){
			
			Atom replacable = molecule.search(mouse);
			if(replacable == null){ 
				System.out.println("Error");
				return;
			}
			Carbon respectiveCarbon = (Carbon)molecule.mainCarbon(replacable);
			Hydroxide newHydroxide = new Hydroxide(replacable.getPosX(), replacable.getPosY(), sprites.getHydroxideMolecule());
			
			molecule.addHydroxyl(newHydroxide, respectiveCarbon, molecule.findBondPosition(respectiveCarbon, replacable));
			
			molecule.construct();
			appScreen.mol = molecule.onScreen;
			appScreen.drawArea.repaint();		
			
		}
		else if(toAdd == "NO2" && !this.beginState){
			
			Atom replacable = molecule.search(mouse);
			if(replacable == null){ 
				System.out.println("Error");
				return;
			}
			Carbon respectiveCarbon = (Carbon)molecule.mainCarbon(replacable);
			NO2 newNitrate = new NO2(replacable.getPosX(), replacable.getPosY(), sprites.getNitrogenDiOxideMolecule());
			
			molecule.addNO2(newNitrate, respectiveCarbon, molecule.findBondPosition(respectiveCarbon, replacable));
			
			molecule.construct();
			appScreen.mol = molecule.onScreen;
			appScreen.drawArea.repaint();		
			
		}
		else if(toAdd == "Oxygen" && !this.beginState){
			
			Atom replacable1 = molecule.search(mouse);
			Atom replacable2 = null;
			Carbon respectiveCarbon = (Carbon)molecule.mainCarbon(replacable1);
			if( molecule.findBondPosition(respectiveCarbon, replacable1) == 1){
				
				replacable2 = respectiveCarbon.bondsArray.get(3).getEnd();
			}
			if( molecule.findBondPosition(respectiveCarbon, replacable1) == 3){
				
				replacable2 = respectiveCarbon.bondsArray.get(0).getEnd();
			}
			if( molecule.findBondPosition(respectiveCarbon, replacable1) == 0){
	
				replacable2 = respectiveCarbon.bondsArray.get(2).getEnd();
			}
			if( molecule.findBondPosition(respectiveCarbon, replacable1) == 2){
	
				replacable2 = respectiveCarbon.bondsArray.get(1).getEnd();
			}
			Oxygen newOxygen = new Oxygen(replacable1.getPosX(), replacable1.getPosY(), sprites.getOxygenMolecule());
			newOxygen.bondPositions.setLocation(molecule.findBondPosition(respectiveCarbon, replacable1), molecule.findBondPosition(respectiveCarbon, replacable2));
			molecule.DoubleBondO(newOxygen, respectiveCarbon, molecule.findBondPosition(respectiveCarbon, replacable1), molecule.findBondPosition(respectiveCarbon, replacable2));
		}
		molecule.updateName();
		appScreen.text.setText(molecule.getName());
		
		molecule.construct();
		appScreen.mol = molecule.onScreen;
		appScreen.drawArea.repaint();	
		
	}
	
	/** Serves the purpose of removing molecules from the screen
	 *  @param toAdd type of molecule to be added to the screen
	 *  @param mouse Point2D mouse object of the users mouse
	 * */
	public void remove(Point2D mouse){
		
	}

	public void endMolecule() {
		molecule = new Molecule();
		molecule.construct();
		appScreen.mol = molecule.onScreen;
		appScreen.text.setText("");
		appScreen.drawArea.repaint();	
		beginState =true;

	}
	
	
}
