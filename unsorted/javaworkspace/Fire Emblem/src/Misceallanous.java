/**Name: William Granados
 * Date: 09/06/14
 * Purpose: Deals with anything text file related
 * */

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;


public class Misceallanous {

	
	private Vector<String> highscoresNamesLines = new Vector<String>();
	private Vector<String> highscoresLines = new Vector<String>();
	private Vector<String> ratingLines = new Vector<String>();
	private Vector<String> levelLines = new Vector<String>(); 
	private Tile[][] levelLayout = new Tile[24][32];
	
	private String[] highscoresNameList = new String[10];
	private int[] highscoresList = new int[10];
	
	private Vector< Unit > computerUnits = new Vector< Unit>();
	
	private double currentRating;
	private int numberOfRatings;
	
	private Scanner s = null;
	
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	// opening text files
	/** Opens the corresponding level text file for the specified level.*/
	public void openLevelFile(String FileName){
		File levelFile = new File(FileName);
		levelLines = new Vector<String>();
		try {
		    s = new Scanner(levelFile);
		}
		catch(Exception noFile) { 
		    System.out.println("No file found");
		}
		while(s.hasNext()){
		   levelLines.add(s.nextLine());
		}
	}	
	/** Opens the corresponding computer units text file for the specified level.*/	
	public void openLevelComputerUnitsFile(String FileName){
		File levelFile = new File(FileName);
		levelLines = new Vector<String>();
		try {
		    s = new Scanner(levelFile);
		}
		catch(Exception noFile) { 
		    System.out.println("No file found");
		}
		while(s.hasNext()){
		   levelLines.add(s.nextLine());
		}
	}
	/** Opens the corresponding high scores text file that contains the names of those who are on the list.*/
	public void openHighscoresNameFile(){
		File highScoresFile = new File("Resources/Highscores/highscoresNames.txt");
		
		try {
		    s = new Scanner(highScoresFile);
		}
		catch(Exception noFile) { 
		    System.out.println("No file found");
		}
		
		while(s.hasNext()){
		   highscoresNamesLines.add(s.nextLine());
		}
		
	}
	/** Opens the corresponding high scores text file that contains the scores of those who are on the list.*/
	public void openHighscoresFile(){
		File highScoresFile = new File("Resources/Highscores/highscores.txt");
		
		try {
		    s = new Scanner(highScoresFile);
		}
		catch(Exception noFile) { 
		    System.out.println("No file found");
		}
		
		while(s.hasNext()){
		   highscoresLines.add(s.nextLine());
		}
	
	}
	/** Opens the corresponding ratings text file that contains the latest rating variables.*/
	public void openRatingFile(){
		File ratingFile = new File("Resources/Ratings/ratings.txt");
		
		try {
		    s = new Scanner(ratingFile);
		}
		catch(Exception noFile) { 
		    System.out.println("No file found");
		}
		
		while(s.hasNext()){
		   ratingLines.add(s.nextLine());
		}
		
		this.setCurrentRating(Double.parseDouble(ratingLines.elementAt(0)));
		this.setNumberOfRatings(Integer.parseInt(ratingLines.elementAt(1)));
	
	}

	// closing text files
	/** Closes scanner present on the text file.*/
	public void closeFile(){
		s.close();
	}
	
	
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	// updating text files
	/** Checks if the value passed can be on the high scores list then prompts the user if it is possible and updates the high scores list.
	 *  @param score score that the user achieved*/
	public void updateHighscoresFile(int score){
		//straight search since I know the file
		File fileName = new File("Resources/Highscores/Highscores.txt");
		File fileName2 = new File("Resources/Highscores/HighscoresName.txt");
		FileWriter f = null;
		
		
		for(int i = 0;i < 10;i++)
		{
			if(score > highscoresList[i])
			{
				
				String name = JOptionPane.showInputDialog(null, "Please enter your name (7 chars max): ");
				if(name == null){
					name = "Anonymous";
				}
				for(int j = 9;j > i;j--){
					highscoresList[j] = highscoresList[j-1];
				}
				for(int j = 9;j > i;j--){
					highscoresNameList[j] = highscoresNameList[j-1];
				}
				highscoresList[i] = score;
				highscoresNameList[i] = name+" -";
				break;
				
			}
		}
		
		try {
		    f = new FileWriter(fileName); 
		    
		    for(int i = 0;i < 10;i++){
		    	  f.write(highscoresList[i]+"\n"); //writes highscores to file
		    }
		    
		  
		    f.close(); 
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		try {
		    f = new FileWriter(fileName2); 
		    
		    for(int i = 0;i < 10;i++)
		    {
		    	  f.write(highscoresNameList[i]+"\n"); //writes names to text file
		    }
		    
		  
		    f.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	/** Updates the rating text file so that it contains the most recent rating.
	 *  @param currentRating total amount of rating
	 *  @param numberOfRatings total amount of people who have rated
	 *  */
	public void updateRatingFile(double currentRating, int numberOfRatings){
		File fileName = new File("Resources/Ratings/rating.txt"); //straight search since I know the file
		
		FileWriter f = null;
		
		try {
		    f = new FileWriter(fileName); 
		    f.write( currentRating +"\n" + numberOfRatings + "\n");
		    f.close(); 
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	// creating/initializing objects
	/** Creates a high scores list.*/
	public void createHighscoresList(){
		
		for(int x=0;x<highscoresLines.size();x++){
			highscoresNameList[x] = highscoresNamesLines.elementAt(x);
			highscoresList[x] = Integer.parseInt(highscoresLines.elementAt(x));
		}
	}
	/** Creates the level grid.*/
	public void createLevelGrid(){
		
		for(int x = 0; x < 24 ; x++){
			String tileInformation = levelLines.elementAt(x);
			for(int y = 0 ; y < 32 ; y++){
				
				if(tileInformation.charAt(y) == 'O')
					levelLayout[x][y]= new Tile(25*y, 25*x,true);
		
				else if (tileInformation.charAt(y)=='P')
					levelLayout[x][y]= new Tile(25*y, 25*x,true,true);
				
				else if (tileInformation.charAt(y)=='X')
						levelLayout[x][y]= new Tile(25*y, 25*x,false);
			
			
				
			}
		}
		
		
	}
	/** Creates the computerUnits.*/
	public void createComputerUnits(){
		for(int x = 0; x < 24 ; x++){
			String tileInformation = levelLines.elementAt(x);
			for(int y = 0 ; y < 32 ; y++){
				
				// K - Knight
				if(tileInformation.charAt(y) == 'K'){
					computerUnits.add(new Knight(x,y,false));
					levelLayout[x][y].setOccupiedUnit(computerUnits.get(computerUnits.size()-1));
				}
				// W - Warrior
				else if (tileInformation.charAt(y)=='W'){
					computerUnits.add(new Warrior(x,y,false));
					levelLayout[x][y].setOccupiedUnit(computerUnits.get(computerUnits.size()-1));
				}
				// S - Swordmaster
				else if (tileInformation.charAt(y)=='S'){
					computerUnits.add(new SwordMaster(x,y,false));
					levelLayout[x][y].setOccupiedUnit(computerUnits.get(computerUnits.size()-1));
				}
				// C - Sorcerer
				else if (tileInformation.charAt(y)=='C'){
					computerUnits.add(new Sorcerer(x,y,false));
					levelLayout[x][y].setOccupiedUnit(computerUnits.get(computerUnits.size()-1));
				}
			
				
				
			}
		}
	}

	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	// getters and setters

	/** Returns the grid for the corresponding level.*/
	public Tile[][] getLevelGrid(){
		return levelLayout;
	}
	/** Returns the computer units for the corresponding level.*/
	public Vector< Unit > getComputerUnits() {
		return computerUnits;
	}
	
	/** Returns the current rating on the game.*/
	public double getCurrentRating() {
		return currentRating;
	}
	/** Sets the current rating on the game*/
	public void setCurrentRating(double currentRating) {
		this.currentRating = currentRating;
	}
	
	/** Returns the number of ratings on the game.*/
	public int getNumberOfRatings() {
		return numberOfRatings;
	}
	/** Sets the number of ratings on the game*/
	public void setNumberOfRatings(int numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}

	/** Returns the high scores name list array.*/
	public String[] getHighScoresNameList(){
		return highscoresNameList;
	}
	/** Returns the high scores array.*/
	public int[] getHighScores(){
		return highscoresList;
	}
	
	

	
}
