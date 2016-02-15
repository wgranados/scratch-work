/** Name: William Granados
 *  Date: 6/8/15
 *  Purpose: handles the gui interface for the applet
 * */
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Main extends JApplet{
	
	// misceallanous
	private Container container;
	// labels
	private JLabel progLabel;
	private JLabel testLabel;
	private JLabel inputLabel;
	private JLabel outputLabel;
	// text fields
	private JTextField codePath;
	private JTextField testdataPath;
	private JTextField inputPath;
	private JTextField outputPath;
	private JTextField output;
	// buttons
	private JButton run;
	
	// other folder
	private Grader grader;
	
	public void init(){
		this.setSize(350,200);
		this.setName("Offline Grader");
		this.container = getContentPane();
		this.grader = new Grader();
		// initialize the objects
		this.progLabel    = new JLabel("        Source path:");
		this.testLabel    = new JLabel("    Test data path:");
		this.inputLabel   = new JLabel("            Input path:");
		this.outputLabel   = new JLabel("         Output path:");
		this.codePath     = new JTextField(20);
		this.testdataPath = new JTextField(20);
		this.inputPath    = new JTextField(20);
		this.outputPath    = new JTextField(20);
		this.run 		  = new JButton("Run Code");
		this.output 	  = new JTextField(30);
		// add mouse listener to button
		this.run.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){
				if(grader.grade(codePath.getText().trim(), testdataPath.getText().trim(), inputPath.getText().trim(),outputPath.getText().trim()))
					output.setText("Accepted Answer");
				else
					output.setText("Wrong Answer");	
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0){}
		});
		// initlizaing the layout
		this.container.setLayout(new FlowLayout());
		// adding the components to the applet
		this.container.add(this.progLabel);
		this.container.add(this.codePath);
		this.container.add(this.testLabel);
		this.container.add(this.testdataPath);
		this.container.add(this.inputLabel);
		this.container.add(this.inputPath);
		this.container.add(this.outputLabel);
		this.container.add(this.outputPath);
		this.container.add(this.run);
		this.container.add(this.output);		
	}
	
	public static void main(String[]args){
		
	}
}
