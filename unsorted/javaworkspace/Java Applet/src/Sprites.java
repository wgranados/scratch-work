/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related to image handling and distribution
 * */
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Sprites {
	private Image layout;
	private Image layoutBondClicked;
	
	private Image hydrogenButtonMolecule;
	private Image hydrogenMolecule;
	private Image carbonButtonMolecule;
	private Image carbonMolecule;
	private Image nitrogenButtonMolecule;
	private Image nitrogenMolecule;
	private Image oxygenButtonMolecule;
	private Image oxygenMolecule;
	private Image chlorineButtonMolecule;
	private Image chlorineMolecule;
	private Image fluorineButtonMolecule;
	private Image fluorineMolecule;
	private Image bromineButtonMolecule;
	private Image bromineMolecule;
	private Image iodineButtonMolecule;
	private Image iodineMolecule;
	private Image hydroxideButtonMolecule;
	private Image hydroxideMolecule;
	private Image nitrogenMonoHydrideButtonMolecule;
	private Image nitrogenMonoHydrideMolecule;
	private Image nitrogenDiHydrideButtonMolecule;
	private Image nitrogenDiHydrideMolecule;
	private Image nitrogenDiOxideButtonMolecule;
	private Image nitrogenDiOxideMolecule;
	
	private Image horizontalBond;
	private Image verticalBond;
	private Image doubleHorizontalBond;
	private Image doubleVerticalBond;
	
	private Image sBond;
	private Image dBond;
	private Image tBond;
	private Image deleteBond;
	private Image drawBackground;
	private Image toolBoxBackground;
	private Image go;
	
	
	private Toolkit toolkit;
	private Image hBond;
	private Image vBond;

	public Sprites(){
		this.toolkit = Toolkit.getDefaultToolkit();
		initLayout();
		initMolecules();
	}
	public void initLayout(){
		layout = toolkit.getImage("layout.png");
		layoutBondClicked = toolkit.getImage("layoutBondClicked.png");
	}
	public void initMolecules(){
		
		// hydrogen molecules
		try {
		    hydrogenButtonMolecule= ImageIO.read(new File("hydrogen.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		try {
		    hydrogenMolecule= ImageIO.read(new File("Hydrogen2.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		
		
		// carbon molecules
		try {
		    carbonButtonMolecule= ImageIO.read(new File("carbon.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		try {
		    carbonMolecule= ImageIO.read(new File("Carbon2.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		
		// nitrogen molecules
		try {
		    nitrogenButtonMolecule= ImageIO.read(new File("nitrogen.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		try {
		    nitrogenMolecule= ImageIO.read(new File("Nitrogen2.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		
		// oxygen molecules
		try {
		    oxygenButtonMolecule= ImageIO.read(new File("oxygen.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		try {
		    oxygenMolecule= ImageIO.read(new File("Oxygen2.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		
		// chlorine molecules
		try {
		    chlorineButtonMolecule= ImageIO.read(new File("chlorine.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		try {
		    chlorineMolecule= ImageIO.read(new File("Chlorine2.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		
		// fluorine molecules
		try {
		    fluorineButtonMolecule= ImageIO.read(new File("fluorine.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		try {
		    fluorineMolecule= ImageIO.read(new File("Fluorine2.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
				
		// bromine molecules
		try {
		    bromineButtonMolecule= ImageIO.read(new File("bromine.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		try {
		    bromineMolecule= ImageIO.read(new File("Bromine2.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		// iodine molecules
		try {
		    iodineButtonMolecule= ImageIO.read(new File("iodine.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		try {
		    iodineMolecule= ImageIO.read(new File("Iodine2.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		// hydroxide molecules
		try {
		    hydroxideButtonMolecule= ImageIO.read(new File("hydroxide.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		try {
		    hydroxideMolecule= ImageIO.read(new File("Hydroxide2.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		// verical and horizontal bonds
		try {
		    verticalBond= ImageIO.read(new File("verticalLine.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		try {
		    horizontalBond = ImageIO.read(new File("horizontalLine.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		try {
		    doubleVerticalBond= ImageIO.read(new File("double vertical line.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		try {
		    doubleHorizontalBond = ImageIO.read(new File("double horizontal line.png"));
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
				
		nitrogenMonoHydrideButtonMolecule = 	toolkit.getImage("nitrogenMonoHydride.png");
		nitrogenDiHydrideButtonMolecule = 		toolkit.getImage("nitrogenDiHydride.png");
		nitrogenDiOxideButtonMolecule = 		toolkit.getImage("nitrogenDiOxide.png");
		
		nitrogenMonoHydrideMolecule =	 		toolkit.getImage("nitrogenMonoHydride.png");
		nitrogenDiHydrideMolecule = 			toolkit.getImage("nitrogenDiHydride.png");
		nitrogenDiOxideMolecule = 				toolkit.getImage("nitrogenDiOxide2.png");
		
		sBond =                         		toolkit.getImage("single bond.png");
		dBond =                         		toolkit.getImage("double bond.png");
		tBond =                         		toolkit.getImage("triple bond.png");
		vBond =                         		toolkit.getImage("verticalLine.png");
		hBond =                         		toolkit.getImage("horizontalLine.png");
		deleteBond =                    		toolkit.getImage("remove bonds button.png");
		drawBackground =                		toolkit.getImage("drawBackground.png");
		toolBoxBackground =             		toolkit.getImage("sidebar.png");
		go				  =             		toolkit.getImage("go.png");
		
		//doubleHorizontalBond = 					toolkit.getImage("double horizontal line.png");
		//doubleVerticalBond  =					toolkit.getImage("double vertical line.png");
	}
	
	public Image getLayout() {
		return layout;
	}
	public Image getLayoutBondClicked() {
		return layoutBondClicked;
	}
	
	// buttons
	public Image getCarbonMoleculeButton() {
		return carbonButtonMolecule;
	}
	public Image getNitrogenMoleculeButton() {
		return nitrogenButtonMolecule;
	}
	public Image getOxygenMoleculeButton() {
		return oxygenButtonMolecule;
	}
	public Image getChlorineMoleculeButton() {
		return chlorineButtonMolecule;
	}
	public Image getFluorineMoleculeButton() {
		return fluorineButtonMolecule;
	}
	public Image getBromineMoleculeButton() {
		return bromineButtonMolecule;
	}
	public Image getIodineMoleculeButton() {
		return iodineButtonMolecule;
	}
	public Image getHydroxideMoleculeButton() {
		return hydroxideButtonMolecule;
	}
	public Image getNitrogenMonoHydrideMoleculeButton() {
		return nitrogenMonoHydrideButtonMolecule;
	}
	public Image getNitrogenDiHydrideMoleculeButton() {
		return nitrogenDiHydrideButtonMolecule;
	}
	public Image getNitrogenDiOxideMoleculeButton() {
		return nitrogenDiOxideButtonMolecule;
	}
	public Image getHydrogenButtonMolecule(){
		return hydrogenMolecule;
	}

	// molecules
	public Image getCarbonMolecule() {
		return carbonMolecule;
	}
	public Image getNitrogenMolecule() {
		return nitrogenMolecule;
	}
	public Image getOxygenMolecule() {
		return oxygenMolecule;
	}
	public Image getChlorineMolecule() {
		return chlorineMolecule;
	}
	public Image getFluorineMolecule() {
		return fluorineMolecule;
	}
	public Image getBromineMolecule() {
		return bromineMolecule;
	}
	public Image getIodineMolecule() {
		return iodineMolecule;
	}
	public Image getHydroxideMolecule() {
		return hydroxideMolecule;
	}
	public Image getNitrogenMonoHydrideMolecule() {
		//ret
		return nitrogenMonoHydrideMolecule;

	}
	public Image getNitrogenDiHydrideMolecule() {
		return nitrogenDiHydrideMolecule;
	}
	public Image getNitrogenDiOxideMolecule() {
		return nitrogenDiOxideMolecule;
	}
	public Image getHydrogenMolecule() {
		return hydrogenMolecule;
	}
	
	
	public Image getVBond(){
		
		return vBond;
	}
	public Image getHBond(){
		
		return hBond;
	}
	public Image getSBond() {
		return sBond;
	}
	public Image getDBond() {
		return dBond;
	}
	public Image getTBond() {
		return tBond;
	}
	
	public Image getDeleteBond(){
		return deleteBond;
	}
	public Image getDrawBackground() {
		return drawBackground;
	}
	public Image getToolBoxBackground() {
		return toolBoxBackground;
	}
	public Image getGo() {
		return go;
	}
	
	
	
	public Image getHorizontalBond() {
		return horizontalBond;
	}
	public Image getVerticalBond() {
		return verticalBond;
	}
	public Image getDoubleHorizontalBond() {
		return doubleHorizontalBond;
	}
	
	public Image getDoubleVerticalBond() {
		return doubleVerticalBond;
	}
	
	
	
}
