/** Name: Andrew Jay, Vaidik Patel, William Granados
 *  Date: 24/04/14
 *  Purpose: to handle details related to bonding atoms
 * */
import java.awt.Rectangle;


public class Bond {
	private Atom begin;
	private Atom end;
	private String bondType;
	Sprites images = new Sprites();
	
	/** Creates an Object of type Bond
	 *  @param Atom begin atom to be set at the front of the bond
	 *  @param Atom end atom to be set at the end of the bond	
	 *  */
	public Bond(Atom begin,Atom end){
		this.setBegin(begin);
		this.setEnd(end);
	}
	/** Creates an Object of type Bond for testing purposes*/
	public Bond() { 
	}

	// begin
	/** returns the Atom at the begininning of the bond
	 *  @return Atom begin*/
	public Atom getBegin() {
		return begin;
	}
	/** sets the Atom at the beginning of the bond
	 *  @param Atom begin new atom to be substituted*/
	public void setBegin(Atom begin) {
		this.begin = begin;
	}
	
	// end
	/** returns the Atom at the ending of the bond
	 *  @return Atom begin*/
	public Atom getEnd() {
		return end;
	}
	/** sets the Atom at the beginning of the bond
	 *  @param Atom begin new atom to be substituted*/
	public void setEnd(Atom end) {
		this.end = end;
	}

	// bond type
	/** returns the type of bond that was created
	 * @return bondType*/
	public String getBondType() {
		return bondType;
	}
	/** sets the bond type that was created */
	public void setBondType(String bondType) {
		this.bondType = bondType;
	}
	
	// polarity
	/** checks if the electronegativity difference is polar*/
	public boolean IsItPolar () {
		if (begin.getEN()-end.getEN()>=0.5) return true;
		if (end.getEN()-begin.getEN()>=0.5) return true;
		return false;
	}
	/* returns the electronegativity difference between both atoms
	 * @return difference**/
	public double ENdiff (Atom Atom) {

		if (Atom==begin){
			return begin.getEN()-end.getEN();
		}
		else {
			return end.getEN()-begin.getEN();
		}
	}
}

