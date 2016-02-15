/** Name: William Granados
 *  Date: 09/06/14
 *  Purpose: handles the decision making
 * */

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.Vector;
import javax.swing.JFrame;

public class FireEmblem extends Canvas implements Runnable{
	
	//screen dimensions and variables
	static final int WIDTH = 1000;//1024
	static final int HEIGHT = 600; //4:3 aspect ratio width/4*3
	private JFrame frame;

	//game updates per second
	static final int UPS = 60;
	
	//variables for the thread
	private Thread thread;
	private boolean running;
	
	//used for drawing items to the screen
	private Graphics2D graphics;
	
	// class objects
	private Sprites sprites;
	private Music music;
	private Mouse mouse = new Mouse();
	private Misceallanous misceallanous;
	private Tile[][] levelLayout;
	private ArtificialIntelligence strategy;
	private Vector< Unit > playerUnits = new Vector< Unit>();
	private Vector< Unit > computerUnits = new Vector< Unit>();
	private int[][] visited = new int[24][32];
	private int computerUnitsIterator = 0;
	
	// player selection items
	int[] playerSelection = new int[2];
	int[] oldPlayerSelection = new int[2];
	int[] playerSelectionOriginalUnitArea = new int[2];
	
	private Unit currentlyMovingUnit;
	
	private int tempUnitsOriginal = 5;
	private int tempUnits = 5;
	
	private boolean unitSelected;
	private boolean voteOnce;
	
	private Rectangle2D[] mainMenuOptions;
	private Rectangle2D returnButton;
	private Rectangle2D[] characterOptions; 
	private Rectangle2D[] emblemOptions;
	private Rectangle2D[] difficultyOptions;
	private String characterSelection = "";
	
	// game/screen states
	private int gameState ;
	private int ratingScreenState = 0;
	
	private final int GAME_MENU 				= 0;
	private final int GAME_SELECTUNITS 			= 1;
	private final int GAME_PLAYERONETURN 		= 2;
	private final int GAME_PLAYERTWOTURN 		= 3;
	private final int GAME_MOVEMENT 			= 4;
	private final int GAME_ATTACK 				= 5;
	private final int GAME_INSTRUCTIONS 		= 6;
	private final int GAME_RATINGS 				= 7;
	private final int GAME_HIGHSCORES 		    = 8;
	private final int GAME_DIFFICULTYSETTINGS   = 9;
	private final int GAME_CREDENTIALS 			= 10;
	private final int GAME_EXIT 				= 11;
	
	// stuff 
	private int score  = 0;
	private int level = 1;
	private int difficulty = 0;
	
	
	// rating items
	private double currentRating;
	private int numberOfRatings;
	
	// high scores items
	private String[] highscoresNameList;
	private int[] highscoresList;
		
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	// variable initialization related
	/**Initializes items needed for the game play.*/
	public void init() {
		initObjects();
		initRectangle2DObjects();
		initVariables();
		initRatings();
		initHighscores();
	}
	/**Initializes small variables variables.*/
	public void initVariables(){
		this.gameState = GAME_MENU;
		this.characterSelection = "2";
		this.score += tempUnits*100;
		
	}
	/** Initializes objects that requires the use of classes.*/
	public void initObjects(){
			this.sprites = new Sprites();
			this.music = new Music();
			this.misceallanous = new Misceallanous();
			this.initLevelLayout();
			this.initComputerUnits();
			this.updateLevelLayout();
			this.strategy = new ArtificialIntelligence();
	}
	/** Initializes rectangle objects that are to be used for player input.*/
	public void initRectangle2DObjects(){
		this.returnButton = new Rectangle2D.Double(364,106,32,32);
		
		this.characterOptions = new Rectangle2D[4];
		this.characterOptions[0] = new Rectangle2D.Double(840, 60, 40, 27);
		this.characterOptions[1] = new Rectangle2D.Double(923, 60, 40, 27);
		this.characterOptions[2] = new Rectangle2D.Double(840, 125, 40, 27);
		this.characterOptions[3] = new Rectangle2D.Double(923, 125, 40, 27);
		
		this.emblemOptions = new Rectangle2D[5];
		for(int i = 0;i < 5;i++){
			this.emblemOptions[i] = new Rectangle2D.Double(405 + 40*i, 270, 40, 35);	
		}
		
		this.difficultyOptions = new Rectangle2D[3];
		for(int i = 0;i < 3;i++){
			this.difficultyOptions[i] = new Rectangle2D.Double(433, 195 + i*50, 30, 30);
			
		}
	
		this.mainMenuOptions = new Rectangle2D[7];
		for(int i = 0;i < 7;i++){
			this.mainMenuOptions[i] = new Rectangle2D.Double(350 , 140 + i*53 , 300, 53);
		}
		
	}
	/** Initializes player units through player input*/
	public void initPlayerUnits(){
			if(checkUnitSelection(mouse.getMousePoint2D()) >= 0 ){
				this.characterSelection = "" + checkUnitSelection(mouse.getMousePoint2D());
				
			}
			if(this.tempUnits > 0){
				if(this.mouse.getMouseClick()){
					
					this.checkPlayerSelection(mouse.getMousePoint2D());
					if(this.unitSelected && this.playerSelection[0] > -1){
						for(int i = 0;i < this.playerUnits.size();i++){
							if(this.playerUnits.get(i).getX() == this.playerSelection[0] && this.playerUnits.get(i).getY() == this.playerSelection[1]){
								unitSelected = false;
								return;
							}
						}
						if(!this.levelLayout[playerSelection[0]][playerSelection[1]].isPlacable()){
							unitSelected = false;
							return;
						}
						
						if(this.characterSelection.equals("0"))
							this.playerUnits.add(new Knight(playerSelection[0],playerSelection[1],true));
						
						else if(this.characterSelection.equals("1"))
							this.playerUnits.add(new Sorcerer(playerSelection[0],playerSelection[1],true));
						
						else if(this.characterSelection.equals("2"))
							this.playerUnits.add(new SwordMaster(playerSelection[0],playerSelection[1],true));
						
						else if(this.characterSelection.equals("3"))
							this.playerUnits.add(new Warrior(playerSelection[0],playerSelection[1],true));
						
						this.levelLayout[playerSelection[0]][playerSelection[1]].setOccupiedUnit(playerUnits.get(playerUnits.size()-1));
						
						this.tempUnits--;
						
					}
					
				}
				this.unitSelected = false;
			}
			
	}
	/** Retrieves information about the level layour from a text file then updates the level layout variable.*/
	public void initLevelLayout(){
		this.misceallanous.openLevelFile("Resources/Levels/level" + level +".txt");
		this.misceallanous.createLevelGrid();
		this.levelLayout = misceallanous.getLevelGrid();	
	}
	
	/** Retrieves information about the computer units from a text file then updates the corresponding rating variables.*/
	public void initComputerUnits(){
		this.misceallanous.openLevelFile("Resources/Levels/level" + level +"Enemies.txt");
		this.misceallanous.createComputerUnits();;
		this.computerUnits = misceallanous.getComputerUnits();
			
		
	}
	/** Retrieves information about the rating from a text file ten updates the corresponding rating variables.*/
	public void initRatings(){
		this.misceallanous.openRatingFile();
		this.currentRating = misceallanous.getCurrentRating();
		this.numberOfRatings = misceallanous.getNumberOfRatings();
	}
	/** Retrieves information about the high scores from a text file then updates the corresponding high scores variables.*/
	public void initHighscores(){
		this.misceallanous.openHighscoresNameFile();
		this.misceallanous.openHighscoresFile();
		this.misceallanous.createHighscoresList();
		this.highscoresNameList = misceallanous.getHighScoresNameList();
		this.highscoresList = misceallanous.getHighScores();
		
	}
	
	// music related
	/** Opens the corresponding music for the level, in this case El Dorado.*/
	public void openLevelOneMapMusic(){
		this.music.mapOneOpen();
	}
	/** Closes the corresponding music for the level, in this case El Dorado.*/
	public void closeLevelOneMapMusic(){
		this.music.mapOneClose();
	}
	
	/** Opens the corresponding music for the level, in this case Clash of empires.*/
	public void openLevelTwoMapMusic(){
		this.music.mapTwoOpen();
	}
	/** Closes the corresponding music for the level, in this case Clash of Empires.*/
	public void closeLevelTwoMapMusic(){
		this.music.mapTwoClose();
	}
	
	/** Opens the corresponding music for the level, in this case Wrath of Seas.*/
	public void openLevelThreeMapMusic(){
		this.music.mapThreeOpen();
	}
	/** Closes the corresponding music for the level, in this case Wrath of Sea.*/
	public void closeLevelThreeMapMusic(){
		this.music.mapThreeClose();
	}
	
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	/** Master method that calls the corresponding update methods.*/
	public void update() {
		if(gameState == GAME_MENU)
			updateMenu();
		
		else if(gameState == GAME_SELECTUNITS)
			updateSelectUnits();		
		
		else if(gameState == GAME_PLAYERONETURN){
			updatePlayerOneTurn();
			updatePlayerUnits();
			updateComputerUnits();
			
			if(this.playerUnits.isEmpty() && !this.computerUnits.isEmpty() ){
				updateHighscoresList();
				
				if(this.level == 1)
					closeLevelOneMapMusic();
				
				else if(this.level == 2)
					closeLevelTwoMapMusic();
				
				else if(this.level == 3)
					closeLevelThreeMapMusic();
				
				updateGameState(this.GAME_MENU);

			}
			if(this.computerUnits.isEmpty()){
				
				if(this.level == 1){
					closeLevelOneMapMusic();
					
					updateLevel(level+1);
					initLevelLayout();
					initComputerUnits();
					
					// resets the amount of units the player can place
					this.tempUnits = this.tempUnitsOriginal;
					// resets the size the of the units troops so they can choose new troops in the next level
					playerUnits = new Vector< Unit >();
					
					openLevelTwoMapMusic();
					updateGameState(GAME_SELECTUNITS);
					
					
				}
				else if(level == 2){
					closeLevelTwoMapMusic();
					updateLevel(level+1);
					
					initLevelLayout();
					initComputerUnits();
					
					// resets the amount of units the player can place
					this.tempUnits = this.tempUnitsOriginal;
					// resets the size the of the units troops so they can choose new troops in the next level
					this.playerUnits = new Vector< Unit >();
					
					
					
					openLevelThreeMapMusic();
					updateGameState(GAME_SELECTUNITS);
					
				}
				else if(level == 3){
					// checks whether the player's score has gotten them on the high scores list, then redirects them to the ratings page
					closeLevelThreeMapMusic();
					updateHighscoresList();
					updateGameState(GAME_RATINGS);
				}
				
			}
		}
		else if(gameState == GAME_PLAYERTWOTURN){
			updatePlayerTwoTurn();
			updatePlayerUnits();
			updateComputerUnits();
		
		}
		else if(gameState == GAME_MOVEMENT)
			updatePlayerMovement();
		
		else if(gameState == GAME_ATTACK){
			updatePlayerAttack();
			updatePlayerUnits();
			updateComputerUnits();
		}
		
		else if(gameState == GAME_INSTRUCTIONS){
			updateGameInstructions();
		}
		else if(gameState == GAME_RATINGS){
			updateGameRatings();
		}
		else if(gameState == GAME_HIGHSCORES){
			updateGameHighscores();
		}
		else if(gameState == GAME_DIFFICULTYSETTINGS){
			updateGameDifficultySettings();
		}
		else if(gameState == GAME_CREDENTIALS){
			updateGameCredentials();
		}
		
		// this is done so that the mouse click does not always stay as true
		resetMouseClick();
	}
	
	/** Updates the game when the game state is equivalent to the game_instructions variable.*/
	public void updateGameInstructions(){
		if(mouse.getMouseClick() && mouseIsWithinReturnButton()){
			updateGameState(GAME_MENU);
		}
		
	}
	/** Updates the game when the game state is equivalent to the game_ratings variable.*/
	public void updateGameRatings(){
		if(mouse.getMouseClick() && mouseIsWithinReturnButton()){
			this.voteOnce = true;
			updateGameState(GAME_MENU);
			updateRatingFile();
		}
		for(int i = 0;i < 5;i++){
			if(this.emblemOptions[i].contains(this.mouse.getMousePoint2D())){
				this.ratingScreenState = i;
				if(this.voteOnce && this.mouse.getMouseClick()){
					this.currentRating += i+1;
					this.numberOfRatings++;
					this.voteOnce = false;
				}
			}
		}
		
		
	}
	/** Updates the game when the game state is equivalent to the game_highscores variable.*/
	public void updateGameHighscores(){
		if(mouse.getMouseClick() && mouseIsWithinReturnButton()){
			updateGameState(GAME_MENU);
		}
		
	}
	/** Updates the game when the game state is equivalent to the game_difficultysettings variable.
	 *  When the method is used it updates the difficulty setting corresponding to the player input.
	 *   */
	public void updateGameDifficultySettings(){
		if(this.mouse.getMouseClick() && mouseIsWithinReturnButton()){
			updateGameState(GAME_MENU);
		}
		else{
			for(int i = 0;i < 3;i++){
				if(this.difficultyOptions[i].contains(this.mouse.getMousePoint2D()) && this.mouse.getMouseClick()){
					this.difficulty = i;
				}
				
			}
			
		}
		
	}
	/** Updates the game when the game state is equivalent to the game_credentials variable.*/
	public void updateGameCredentials(){
		if(mouse.getMouseClick() && mouseIsWithinReturnButton()){
			updateGameState(GAME_MENU);
		}
		
	}
	/** Updates the game when the game state is equivalent to the game_menu variable.*/
	public void updateMenu(){
		if(gameState == GAME_MENU){
			if(mouse.getMouseClick() && checkPlayerSelectionMenu() < 7){
				if(checkPlayerSelectionMenu() == 0){
					updateGameState(GAME_SELECTUNITS);
					
					// this is done so that the player can choose 5 units again if the game has already been played once
					resetTempUnitsOriginal();
					
					openLevelOneMapMusic();
				}
				else if(checkPlayerSelectionMenu() == 1){
					updateGameState(GAME_INSTRUCTIONS);
				}
				else if(checkPlayerSelectionMenu() == 2){
					updateGameState(GAME_RATINGS);
				}
				else if(checkPlayerSelectionMenu() == 3){
					updateGameState(GAME_HIGHSCORES);
				}
				else if(checkPlayerSelectionMenu() == 4){
					updateGameState(GAME_DIFFICULTYSETTINGS);
				}
				else if(checkPlayerSelectionMenu() == 5){
					updateGameState(GAME_CREDENTIALS);
				}
				else if(checkPlayerSelectionMenu() == 6){
					updateGameState(GAME_EXIT);
					this.running = false;
				}
				
				
			}
		}
	}
	/** Updates the game when the game state is equivalent to the game_selectunits variable.
	 *  Takes in input from the user and places the units in the corresponding areas.
	 * */
	public void updateSelectUnits(){
		initPlayerUnits();
		if(this.tempUnits == 0){
			updateGameState(GAME_PLAYERONETURN);
			this.unitSelected = false;
		}	
	}
	/** Updates the game when the game state is equivalent to the game_playerone variable.
	 * 	Takes in input from the user and displays the possible locations a certain unit can move.
	 * */
	public void updatePlayerOneTurn(){
		/* general notes for variables, playerSelection[2]
		 * playerSelection[0] = x position
		 * playerSeleection[1] = y position
		 **/
		
		// checks whether each unit has already gone if so then it changesto the player two state
		boolean notMovable = true;
		for(int i = 0;i < playerUnits.size();i++){
			if(this.playerUnits.get(i).isMovable()){
				notMovable = false;
				break;
			}
		}
	
		if(notMovable){
			resetMovement();
			updateGameState(GAME_PLAYERTWOTURN);
		}
	
		if(this.mouse.getMouseClick()){
			checkUnitMovement(this.mouse.getMousePoint2D());
			if(this.unitSelected && this.playerSelection[0] > -1){
								
				if(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit().isMovable() && this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit().isAlly()){
					updateGameState(GAME_MOVEMENT);
					this.playerSelectionOriginalUnitArea[0] = this.playerSelection[0];
					this.playerSelectionOriginalUnitArea[1] = this.playerSelection[1];
					
					this.currentlyMovingUnit = this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit();
					createPlayerRange(this.playerSelectionOriginalUnitArea[0], this.playerSelectionOriginalUnitArea[1], this.levelLayout[ playerSelectionOriginalUnitArea[0] ][ this.playerSelectionOriginalUnitArea[1] ].getOccupiedUnit().getSteps(), 0);
					
					// 3 signifies that this location in the visited array is the origin
					this.visited[playerSelectionOriginalUnitArea[0]][playerSelectionOriginalUnitArea[1]] = 3;
				}
			}
			
		}
		
	}
	/** Updates the game when the game state is equivalent to the game_playertwo variable.
	 * 	Updates the computers moves through the use of the ArtificialIntelligence class.
	 * */
	public void updatePlayerTwoTurn(){
		/* Instead of going through a for loop an iterator was done instead to show how the AI did stuff progressively,
		 * sadly the game runs much to quickly to see this happen.
		 */
		if(this.computerUnitsIterator < this.computerUnits.size()){
			
			this.strategy.setLevelLayout(this.levelLayout);
			
			if(difficulty == 0)
				this.strategy.excecuteEasyStrategy(this.computerUnits.get(this.computerUnitsIterator));
			else if(difficulty == 1)
				this.strategy.executeIntermediateStrategy(this.computerUnits.get(this.computerUnitsIterator));
			else if(difficulty == 2)
				this.strategy.executeExpertStrategy(this.computerUnits.get(this.computerUnitsIterator));
			
			this.computerUnitsIterator++;
			
		}
		else{
			this.computerUnitsIterator = 0;
			updateGameState(GAME_PLAYERONETURN);
			
		}
	
		
		
	}
	/** Updates the game when the game state is equivalent to the game_playermovement variable.
	 *  Updates a player's unit location temporarily.
	 * */
	public void updatePlayerMovement(){
		
		checkPlayerSelection(this.mouse.getMousePoint2D());
		
		// return to the original player position
		if(this.playerSelection[0] == this.playerSelectionOriginalUnitArea[0] && this.playerSelection[1] == this.playerSelectionOriginalUnitArea[1] && this.mouse.getMouseClick()){
			this.unitSelected = false;
			
			resetVisited();
			updateGameState(GAME_PLAYERONETURN);
			
		}
		// checks if the unit is surrounded and the player clicks enemies around them
		else if(this.mouse.getMouseClick()){
			
			
			if(this.playerSelection[0] >= 0 && this.playerSelection[1] >= 0){
				
				if(this.visited[playerSelection[0]][this.playerSelection[1]] == 1){
					// checks if the player click in the tile above
					if(this.playerSelection[0] == this.playerSelectionOriginalUnitArea[0]-1  && this.playerSelection[1] == this.playerSelectionOriginalUnitArea[1]){
						if(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit() != null && !this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit().isAlly()){	
							
							this.oldPlayerSelection[0] = this.playerSelectionOriginalUnitArea[0];
							this.oldPlayerSelection[1] = this.playerSelectionOriginalUnitArea[1];
							updateGameState(GAME_ATTACK);
							
						}
					
					}
					// checks if the player click on the tile below
					else if(this.playerSelection[0] == this.playerSelectionOriginalUnitArea[0]+1  && this.playerSelection[1] == this.playerSelectionOriginalUnitArea[1]){
						if(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit() != null && !this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit().isAlly()){	
							
							this.oldPlayerSelection[0] = this.playerSelectionOriginalUnitArea[0];
							this.oldPlayerSelection[1] = this.playerSelectionOriginalUnitArea[1];
							updateGameState(GAME_ATTACK);
							
							
						}
					}
					// checks if the player click on the tile to the right 
					else if(this.playerSelection[1] == this.playerSelectionOriginalUnitArea[1]+1  && this.playerSelection[0] == this.playerSelectionOriginalUnitArea[0]){
						if(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit() != null && !this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit().isAlly()){	
							
							this.oldPlayerSelection[0] = this.playerSelectionOriginalUnitArea[0];
							this.oldPlayerSelection[1] = this.playerSelectionOriginalUnitArea[1];
							updateGameState(GAME_ATTACK);
							
							
						}
					}
					// checks if the player click on the tile to the left
					else if(this.playerSelection[1] == this.playerSelectionOriginalUnitArea[1]-1  && this.playerSelection[0] == this.playerSelectionOriginalUnitArea[0]){
						if(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit() != null && !this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit().isAlly()){	
							
							this.oldPlayerSelection[0] = this.playerSelectionOriginalUnitArea[0];
							this.oldPlayerSelection[1] = this.playerSelectionOriginalUnitArea[1];
							updateGameState(GAME_ATTACK);
							
						}
					}
					
					
					
				}
				// checks if the player wishes to move their character to a new position
				if(this.visited[this.playerSelection[0]][this.playerSelection[1]] == 1 && this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit() == null){
					
					
					this.levelLayout[ this.playerSelectionOriginalUnitArea[0] ][ this.playerSelectionOriginalUnitArea[1] ].getOccupiedUnit().setX(this.playerSelection[0]);
					this.levelLayout[ this.playerSelectionOriginalUnitArea[0] ][ this.playerSelectionOriginalUnitArea[1] ].getOccupiedUnit().setY(this.playerSelection[1]);
					
					this.levelLayout[ this.playerSelection[0] ][ this.playerSelection[1] ].setOccupiedUnit( this.levelLayout[ this.playerSelectionOriginalUnitArea[0] ][ this.playerSelectionOriginalUnitArea[1] ].getOccupiedUnit() );
					this.levelLayout[ this.playerSelectionOriginalUnitArea[0] ][ this.playerSelectionOriginalUnitArea[1] ].setOccupiedUnit(null); 
					
					this.oldPlayerSelection[0] = this.playerSelection[0];
					this.oldPlayerSelection[1] = this.playerSelection[1];
					
					updateGameState(GAME_ATTACK);
					
						
				}
				
			}
		
		}
		
	}
	/** Updates the game when the game state is equivalent to the game_attack variable.
	 *  Updates the player's unit location permanently and attacks nearby enemy units if the
	 *  user decides to do so.
	 *  */
	public void updatePlayerAttack(){
		checkPlayerSelection(mouse.getMousePoint2D());
		if(this.mouse.getMouseClick()){
			
			if(this.playerSelection[0] >= 0 && this.playerSelection[1] >= 0){
				
				// checks if the player wants to return to the original area
				if(this.playerSelection[0] == this.playerSelectionOriginalUnitArea[0] && this.playerSelection[1] == this.playerSelectionOriginalUnitArea[1]){
					
					this.levelLayout[ this.oldPlayerSelection[0] ][ this.oldPlayerSelection[1] ].getOccupiedUnit().setX( this.playerSelectionOriginalUnitArea[0] );
					this.levelLayout[ this.oldPlayerSelection[0] ][ this.oldPlayerSelection[1] ].getOccupiedUnit().setY( this.playerSelectionOriginalUnitArea[1] );
					
					
					this.levelLayout[ this.playerSelectionOriginalUnitArea[0] ][ this.playerSelectionOriginalUnitArea[1] ].setOccupiedUnit( this.levelLayout[ this.oldPlayerSelection[0] ][ this.oldPlayerSelection[1] ].getOccupiedUnit() );
					this.levelLayout[ this.oldPlayerSelection[0] ][ this.oldPlayerSelection[1] ].setOccupiedUnit(null); 
					
					updateGameState(GAME_MOVEMENT);
					
				}
				// checks if the player just wants to stay in the spot and not attack
				else if(this.playerSelection[0] == this.oldPlayerSelection[0] && this.playerSelection[1] == this.oldPlayerSelection[1]){
					
					this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit().setMovable(false);
					resetVisited();
					updateGameState(GAME_PLAYERONETURN);
					
				}
				else{
					// checks if the player wants to attack an enemy unit above
					if(this.playerSelection[0] == this.oldPlayerSelection[0]-1  && this.playerSelection[1] == this.oldPlayerSelection[1]){
						if(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit() != null){	
							
							this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].getOccupiedUnit().attack(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit());
							
							if(!this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit().isAlive())
								this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].setOccupiedUnit(null);
							
							
							this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].getOccupiedUnit().setMovable(false);
							
							
							if(!this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].getOccupiedUnit().isAlive())
								this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].setOccupiedUnit(null);
							
							
							updateGameState(GAME_PLAYERONETURN);
							resetVisited();
							
						}
					
					}
					// checks if the player wants to attack an enemy unit below
					else if(this.playerSelection[0] == this.oldPlayerSelection[0]+1  && this.playerSelection[1] == this.oldPlayerSelection[1]){
						if(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit() != null){	
							
							this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].getOccupiedUnit().attack(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit());
							
							if(!this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit().isAlive())
								this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].setOccupiedUnit(null);
									
							this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].getOccupiedUnit().setMovable(false);
							
							if(!this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].getOccupiedUnit().isAlive())
								this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].setOccupiedUnit(null);
							
							
							updateGameState(GAME_PLAYERONETURN);
							resetVisited();
							
						}
					}
					// checks if the player wants to attack an enemy unit to the right
					else if(this.playerSelection[1] == this.oldPlayerSelection[1]+1  && this.playerSelection[0] == this.oldPlayerSelection[0]){
						if(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit() != null){	
							
							this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].getOccupiedUnit().attack(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit());
							
							if(!this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit().isAlive())
								this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].setOccupiedUnit(null);

							this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].getOccupiedUnit().setMovable(false);
							
							
							if(!this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].getOccupiedUnit().isAlive())
								this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].setOccupiedUnit(null);	
							
							updateGameState(GAME_PLAYERONETURN);
							resetVisited();
							
						}
					}
					// checks if the player wants to attack an enemy unit to the left
					else if(this.playerSelection[1] == this.oldPlayerSelection[1]-1  && this.playerSelection[0] == this.oldPlayerSelection[0]){
						if(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit() != null){	
							this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].getOccupiedUnit().attack(this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit());
							
							if(!this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].getOccupiedUnit().isAlive())
								this.levelLayout[this.playerSelection[0]][this.playerSelection[1]].setOccupiedUnit(null);
							
							this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].getOccupiedUnit().setMovable(false);
							
							
							if(!this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].getOccupiedUnit().isAlive())
								this.levelLayout[this.oldPlayerSelection[0]][this.oldPlayerSelection[1]].setOccupiedUnit(null);
							
							updateGameState(GAME_PLAYERONETURN);
							resetVisited();
						}
					}
					
					
					
					
				}
				
				
				
			}
			
		}
		
	}
	/** Updates the player units variable depending on if a player unit has been defeated, also penalizes
	 *  the player's score for losing a unit.
	 *  */
	public void updatePlayerUnits(){
		for(int i = 0;i < this.playerUnits.size();i++){
			if(!this.playerUnits.get(i).isAlive()){
				this.playerUnits.remove(i);
				this.score -= 100;
				this.tempUnitsOriginal--;
			}
		}
	}
	/** Updates the computer units variable depending on if a computer unit has been defeated, also rewards
	 *  the player for defeating an enemy.
	 *  */
	public void updateComputerUnits(){
		for(int i = 0;i < this.computerUnits.size();i++){
			if(!this.computerUnits.get(i).isAlive()){
				this.computerUnits.remove(i);
				this.score += 100;
			}
		}
	}
	/** Updates the visited array such that it displays all of the potential areas a specified unit can move to.
	 * @param x x position of the corresponding unit.
	 * @param y y position of the corresponding unit.
	 * @param d final depth of the recursive function.
	 * @param td current depth of the recursive function.
	 * */
	public void createPlayerRange(int x, int y, int d, int td){

		
		
		if(x < 0 || y < 0 || x > 23 || y > 31)
			return;
		
		
		
		
		if(!this.levelLayout[x][y].getIsTraversable())
			return;
		
		
		if(  x != this.playerSelectionOriginalUnitArea[0] && y!= this.playerSelectionOriginalUnitArea[1] && this.levelLayout[x][y].getOccupiedUnit() != null )
			return;
		
		
		this.visited[x][y] = 1;
		if(td == d+1){
			// uses Manhattan distance to determine whether the tile is at the end of the attacking range, this is what makes the tile red
			int manhattenDistance = Math.abs(x-this.playerSelectionOriginalUnitArea[0]) + Math.abs(y-this.playerSelectionOriginalUnitArea[1]);
			if(manhattenDistance == d+1)
				this.visited[x][y] = 2;
			return;
		}
		createPlayerRange(x+1 , y  , d, td+1);
		createPlayerRange(x-1 , y  , d, td+1);
		createPlayerRange(x   , y+1, d, td+1);
		createPlayerRange(x   , y-1, d, td+1);
		
		
		
	}

	// check for player input
	/** Checks the level layout to see if the mouse is hovering over a tile.
	 *  Executed when the game is in session.
	 *  */
	public void checkPlayerSelection(Point2D mouse){
		for(int i = 0;i < 24;i++){
			for(int j = 0;j < 32;j++){
				if(this.levelLayout[i][j].getTileBoundary().contains(mouse)){ 
					this.playerSelection[0] = i;
					this.playerSelection[1] = j;
					this.unitSelected = true;
					return;
				}
			}
			
			
		}
		
		this.playerSelection[0] = -1;
		this.playerSelection[1] = -1;
		this.unitSelected = false;
		return;
	}
	/** Checks the side bar to see if the mouse is hovering over a unit.
	 *  Executed when the game is in the game_selectunits game state.
	 *  */
	public int checkUnitSelection(Point2D mouse){
		for(int i = 0;i < 4;i++){
				if(this.characterOptions[i].contains(mouse)){
					return i;
				}
			}
		return -1;
	}
	/** Checks the level layout to see if the mouse is hovering over a tile
	 *  and ensures that the tile selected does not contain an existing unit on it.
	 *  Executed when the game is in the game_movement game state
	 *  */
	public void checkUnitMovement(Point2D mouse){
		for(int i = 0;i < 24;i++){
			for(int j = 0;j < 32;j++){
				if(this.levelLayout[i][j].getTileBoundary().contains(mouse) && this.levelLayout[i][j].getOccupiedUnit() != null){ 
					this.playerSelection[0] = i;
					this.playerSelection[1] = j;
					this.unitSelected = true;
					return;
				}
			}
			
			
		}
		
		this.playerSelection[0] = -1;
		this.playerSelection[1] = -1;
		this.unitSelected = false;
		return;
	}
	/** Checks the menu to see if the mouse is hovering over an option.
	 *  Executed when the game is in the game_menu game state.
	 *  */
	public int checkPlayerSelectionMenu(){
		
		// play button
		if(this.mainMenuOptions[0].contains(this.mouse.getMousePoint2D())){
			return 0;
		}
		// instructions button
		else if(this.mainMenuOptions[1].contains(this.mouse.getMousePoint2D())){
			return 1;
		}
		// ratings button
		else if(this.mainMenuOptions[2].contains(this.mouse.getMousePoint2D())){
			return 2;
		}
		// high scores button
		else if(this.mainMenuOptions[3].contains(this.mouse.getMousePoint2D())){
			return 3;
		}
		// difficulty settings button
		else if(this.mainMenuOptions[4].contains(this.mouse.getMousePoint2D())){
			return 4;
		}
		// credentials buttons
		else if(this.mainMenuOptions[5].contains(this.mouse.getMousePoint2D())){
			return 5;
		}
		// exit button
		else if(this.mainMenuOptions[6].contains(this.mouse.getMousePoint2D())){
			return 6;
		}
		else
			return 7;
	}
	/** Checks whether the return button contains the mouse. 
	 *  Executed when the game is in a state that is not the game menu or game .
	 *  */
	public boolean mouseIsWithinReturnButton(){
		return this.returnButton.contains(this.mouse.getMousePoint2D()) ? true:false;
	}
	
	// update variables
	/** Updates the text file containing the most current ratings.*/
	public void updateRatingFile(){
		this.misceallanous.updateRatingFile(this.currentRating, this.numberOfRatings);
	}
	/** Updates the text file containing the most current high scores.*/
	public void updateHighscoresList(){
		this.misceallanous.updateHighscoresFile(this.score);
		
	}
	/** Updates the level layout by getting information from a text file.*/
	public void updateLevelLayout(){
		this.levelLayout = this.misceallanous.getLevelGrid();	
		
	}
	/** Updates the level.
	 *  @param level desired number the level should be.*/
	public void updateLevel(int level){
		this.level = level;
	}
	/** Updates the gameState 
	 *  @param gameState variable to be substituted*/
	public void updateGameState(int gameState){
		this.gameState = gameState;
	}
	
	// reseting variables
	/** Resets the visited array that is used when calculating the player's range.*/
	public void resetVisited(){
		for(int i = 0;i < 24;i++){
			for(int j = 0;j < 32;j++){
				this.visited[i][j] = 0;
			}
		}
		
	}
	/** Resets the corresponding player units movable boolean variable.
	 *  Executed after the player has move all of their units so the 
	 *  player can move once again the next turn.*/
	public void resetMovement(){
		for(int i = 0;i < this.playerUnits.size();i++)
			this.playerUnits.get(i).setMovable(true);
	}
	/** Resets the mouse click variable  */
	public void resetMouseClick(){
		this.mouse.setMouseClick(false);
	}
	public void resetTempUnitsOriginal(){
		this.tempUnitsOriginal  = 5;
	}
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	/** master draw method that draws the corresponding draw methods*/
	public void draw() {
		
		
		if(gameState == GAME_MENU){
			drawGameMenu();
		}
		else if(gameState == GAME_SELECTUNITS){
			drawGameSelectUnits();
		}	
		else if(gameState == GAME_PLAYERONETURN){
			drawGamePlayerOneTurn();
		}
		else if(gameState == GAME_MOVEMENT){
			drawGameMovement();
		}
		else if(gameState == GAME_ATTACK){
			drawGameAttack();
		}
		else if(gameState == GAME_PLAYERTWOTURN){
			drawGamePlayerTwoTurn();
			
		}
		else if(gameState == GAME_INSTRUCTIONS){
			drawInstructions();
		}
		else if(gameState == GAME_RATINGS){
			drawRatings();
		}
		else if(gameState == GAME_HIGHSCORES){
			drawHighscores();
		}
		else if(gameState == GAME_DIFFICULTYSETTINGS){
			drawDifficultySettings();
		}
		else if(gameState == GAME_CREDENTIALS){
			drawCredentials();
		}
		else if(gameState == GAME_EXIT){
			
		}
	}
	
	/** draw the game state when the game state variable is equivalent to the game_menu variable */
	public void drawGameMenu(){
		graphics.drawImage(sprites.getMainMenuState( checkPlayerSelectionMenu() ),0,0,1000,600,null);
		graphics.dispose();
	}
	/** draw the game state when the game state variable is equivalent to the game_selectunits variable*/
	public void drawGameSelectUnits(){
		drawBackground();
		drawMap();
		drawSidebar();
		if(gameState == GAME_SELECTUNITS){
			drawRemainingTemporaryUnits();
			drawPlacableTiles();
		}
		drawPlayerUnits();
		drawComputerUnits();
		
		
		graphics.dispose();
	}
	/** draw the game state when the game state variable is equivalent to the game_playeroneturn variable*/
	public void drawGamePlayerOneTurn(){
		drawBackground();
		drawMap();
		drawSidebar();
		drawPlayerUnits();
		drawComputerUnits();			
		drawRemainingUnits();
		if(playerSelection[0] > -1)
			drawStats(levelLayout[playerSelection[0]][playerSelection[1]].getOccupiedUnit());
		
		drawScore();
		graphics.dispose();
	}
	/** draw the game state when the game state variable is equivalent to the game_movement variable*/
	public void drawGameMovement(){
		drawBackground();
		drawMap();
		drawSidebar();
		
		drawPlayerAttackingRange();
		
		drawPlayerUnits();
		drawComputerUnits();			
		drawRemainingUnits();
		
		if(playerSelection[0] > -1){
			drawStats(levelLayout[playerSelection[0]][playerSelection[1]].getOccupiedUnit());
			drawSimulatedAttack( currentlyMovingUnit, levelLayout[playerSelection[0]][playerSelection[1]].getOccupiedUnit());
			
		}
		drawScore();
		
		graphics.dispose();
	}
	/** draw the game state when the game state variable is equivalent to the game_attack variable*/
	public void drawGameAttack(){
		drawBackground();
		drawMap();
		drawSidebar();
		
		drawPlayerAttackingRange();
		
		drawPlayerUnits();
		drawComputerUnits();			
		drawRemainingUnits();
		
		if(playerSelection[0] > -1){
			drawStats(levelLayout[playerSelection[0]][playerSelection[1]].getOccupiedUnit());
			drawSimulatedAttack( currentlyMovingUnit, levelLayout[playerSelection[0]][playerSelection[1]].getOccupiedUnit());
				
			
		}
		drawScore();
		
		graphics.dispose();
	}
	/** draw the game state when the game state variable is equivalent to the game_playertwonturn variable*/
	public void drawGamePlayerTwoTurn(){
		drawBackground();
		drawMap();
		drawSidebar();
		drawPlayerUnits();
		drawComputerUnits();			
		drawRemainingUnits();
		if(playerSelection[0] > -1)
			drawStats(levelLayout[playerSelection[0]][playerSelection[1]].getOccupiedUnit());
		
		drawScore();
		
		graphics.dispose();
	}
	
	// menu related
	/** draw the game state when the game state variable is equivalent to the game_credentials variable*/
	private void drawCredentials() {
		graphics.drawImage(sprites.getCredentialsPage(),0,0,1000,600,null);
	}
	/** draw the game state when the game state variable is equivalent to the game_difficultysettings variable*/	
	private void drawDifficultySettings() {
		graphics.drawImage(sprites.getDifficultySettingsPageState(difficulty),0,0,1000,600,null);
	}
	/** draw the game state when the game state variable is equivalent to the game_highscors variable*/
	private void drawHighscores() {
		graphics.drawImage(sprites.getHighscoresPage(),0,0,1000,600,null);
		
		Font serif = new Font("Serif",Font.PLAIN,17) ;
		graphics.setFont(serif);
		graphics.setColor(new Color(0,255,205));
		for(int i = 0;i < 10;i++){
			String entry = (highscoresNameList[i] + "           ").substring(0, 7) + " " +  highscoresList[i];
			graphics.drawString(entry, 470, 180 + 25*i);
		}
	
	}
	/** draw the game state when the game state variable is equivalent to the game_ratings variable*/
	private void drawRatings() {
		graphics.drawImage(sprites.getRatingsPage(),0,0,1000,600,null);
		graphics.drawImage(sprites.getEmblemState(ratingScreenState),0,0,1000,600,null);
		
		
		String rating = "" + Double.toString(currentRating/numberOfRatings) + "000000";
		rating = rating.substring(0, 4);
		
		Font serif = new Font("Serif",Font.PLAIN,24) ;
		graphics.setFont(serif);
		graphics.setColor(new Color(0,255,205));
		graphics.drawString(rating, 485, 212);
	
		
	}
	/** draw the game state when the game state variable is equivalent to the game_instructions variable*/
	private void drawInstructions() {
		graphics.drawImage(sprites.getInstructionsPage(),0,0,1000,600,null);
		
	}
	
	/** draws the stats of a unit
	 * @param unit unit that stats are to be displayed*/
	private void drawStats(Unit unit){
		if(unit == null) return;
		String hp  = "" + unit.getHitPoints();
		String atk = "" + unit.getAttack();
		String def = "" + unit.getDefense();
		String mag = "" + unit.getMagic();
		String res = "" + unit.getResistance();
		String spd = "" + unit.getSpeed();
		String stp = "" + unit.getSteps();
		
		
		Font serif = new Font("Serif",Font.PLAIN,12) ;
		
		graphics.setFont(serif);
		graphics.setColor(new Color(0,255,205));
		graphics.drawString(hp, 890, 212);
		graphics.drawString(atk, 890, 227);
		graphics.drawString(def, 890, 245);
		
		graphics.drawString(spd, 890, 262);
		graphics.drawString(mag, 890, 280);
		
		graphics.drawString(res, 890, 298);
		graphics.drawString(stp, 890, 315);
		
	}
	/** draw the amount of damage each corresponding unit would take if an attack were to be made
	 *  @param playerUnit unit to make the attack
	 *  @param enemyUnit  unit to receive the attack and retaliate
	 *  */
	private void drawSimulatedAttack(Unit playerUnit, Unit enemyUnit){
		if( (playerUnit == null || enemyUnit == null))return;
		
		String damage = "" + playerUnit.damageDealt(enemyUnit);
		String retaliate = "" + playerUnit.damageIncurred(enemyUnit);
		
		Font serif = new Font("Serif",Font.PLAIN,12) ;
		
		graphics.setFont(serif);
		graphics.setColor(new Color(0,255,205));
		graphics.drawString(retaliate, 820, 400);
		graphics.drawString(damage, 950, 400);
		
		
		
	}
	
	// game related
	/** draws a simple black background*/
	public void drawBackground(){
		Rectangle2D background = new Rectangle2D.Double(0,0,WIDTH,HEIGHT);
		graphics.setColor(Color.BLACK);
		graphics.fill(background);
	}
	/** draws the map corresponding to its level*/
	public void drawMap(){
		if(level == 1)
			graphics.drawImage(sprites.getLevelOneMap(), 0, 0, 800, 600, null);
		else if(level == 2)
			graphics.drawImage(sprites.getLevelTwoMap(), 0, 0, 800, 600, null);
		else if(level == 3)
			graphics.drawImage(sprites.getLevelThreeMap(), 0, 0, 800, 600, null);
		
		
	}
	/** draws the side bar corresponding to its level*/
	public void drawSidebar(){
		if(gameState == GAME_SELECTUNITS){
			if(characterSelection.equals("0")){
				graphics.drawImage(sprites.getSidebarKnight(), 800 ,0 ,200 ,600,null);
			}
			else if(characterSelection.equals("1")){
				graphics.drawImage(sprites.getSidebarSorceror(), 800 ,0 ,200 ,600,null);
			}
			else if(characterSelection.equals("2")){
				graphics.drawImage(sprites.getSidebarSwordmaster(), 800 ,0 ,200 ,600,null);
			}
			else if(characterSelection.equals("3")){
				graphics.drawImage(sprites.getSidebarWarrior(), 800 ,0 ,200 ,600,null);
			}
			
		}	
		else{
			if(this.level == 1){
				graphics.drawImage(sprites.getSidebarTeamOne(), 800 ,0 ,200 ,600,null);
			}
			else if(this.level == 2){
				graphics.drawImage(sprites.getSidebarTeamTwo(), 800 ,0 ,200 ,600,null);
			}
			else if(this.level == 3){
				graphics.drawImage(sprites.getSidebarTeamThree(), 800 ,0 ,200 ,600,null);
			}
			
		}
		
		
	}
	/** draws the player's current score*/
	public void drawScore(){
		String score = "" + this.score;
		Font serif = new Font("Serif",Font.PLAIN,12) ;
		
		graphics.setFont(serif);
		graphics.setColor(new Color(0,255,205));
		graphics.drawString(score, 885, 485);
		
		
		
	}
	
	// tile related
	/** draws the initial tiles that the player can place units upon*/
	public void drawPlacableTiles(){
		for(int i = 0;i < 24;i++){
			for(int j = 0;j < 32;j++){
				if(levelLayout[i][j].isPlacable())
					graphics.drawImage(sprites.getPlacableTile(),j*25, i*25, 25, 25, null);
			}
			
		}
	}
	/** draws the units the attacking range*/
	private void drawPlayerAttackingRange(){
		
		for(int i = 0;i < 24;i++){
			for(int j = 0;j < 32;j++){
				if(visited[i][j] == 1){
					
					graphics.drawImage(sprites.getTraversableTile(), j*25,i*25, 25, 25, null);
				}
				else if(visited[i][j] == 2){
					graphics.drawImage(sprites.getAttackingTile(), j*25,i*25, 25, 25, null);
				}
				else if(visited[i][j] == 3){
					graphics.drawImage(sprites.getPlacableTile(), j*25,i*25, 25, 25, null);
				}
				
			}
		}
		
	
		
	}

	// unit related
	/** draws the the units currently in the player's possession*/
	public void drawPlayerUnits(){
			for(int i = 0;i < playerUnits.size();i++){
				if(playerUnits.get(i).getPlayerClass().equals("swordmaster")){
					if(playerUnits.get(i).isMovable())
						graphics.drawImage(sprites.getAllySwordmasterSprite(),playerUnits.get(i).getY()*25, playerUnits.get(i).getX()*25, 25, 25, null);
					else
						graphics.drawImage(sprites.getRestingSwordsmasterSprite(),playerUnits.get(i).getY()*25, playerUnits.get(i).getX()*25, 25, 25, null);
				}
				else if(playerUnits.get(i).getPlayerClass().equals("knight")){
					if(playerUnits.get(i).isMovable())
						graphics.drawImage(sprites.getAllyKnightSprite(),playerUnits.get(i).getY()*25, playerUnits.get(i).getX()*25, 25, 25, null);
					else
						graphics.drawImage(sprites.getRestingKnightSprite(),playerUnits.get(i).getY()*25, playerUnits.get(i).getX()*25, 25, 25, null);
					
				}
				else if(playerUnits.get(i).getPlayerClass().equals("warrior")){
					if(playerUnits.get(i).isMovable())
						graphics.drawImage(sprites.getAllyWarriorSprite(),playerUnits.get(i).getY()*25, playerUnits.get(i).getX()*25, 25, 25, null);
					else
						graphics.drawImage(sprites.getRestingWarriorSprite(),playerUnits.get(i).getY()*25, playerUnits.get(i).getX()*25, 25, 25, null);
					
				}  
				else if(playerUnits.get(i).getPlayerClass().equals("sorceror")){
					if(playerUnits.get(i).isMovable())
						graphics.drawImage(sprites.getAllySorcerorSprite(),playerUnits.get(i).getY()*25, playerUnits.get(i).getX()*25, 25, 25, null);
					else
						graphics.drawImage(sprites.getRestingSorcerorSprite(),playerUnits.get(i).getY()*25, playerUnits.get(i).getX()*25, 25, 25, null);
					
					
				} 
				
			}
					
	}
	/** draws the  units currently in the computer's possession*/
	public void drawComputerUnits(){
		for(int i = 0;i < computerUnits.size();i++){
			if(computerUnits.get(i).getPlayerClass().equals("swordmaster")){
				graphics.drawImage(sprites.getEnemySwordsmasterSprite(),computerUnits.get(i).getY()*25, computerUnits.get(i).getX()*25, 25, 25, null);
			}
			else if(computerUnits.get(i).getPlayerClass().equals("knight")){
				graphics.drawImage(sprites.getEnemyKnightSprite(),computerUnits.get(i).getY()*25, computerUnits.get(i).getX()*25, 25, 25, null);
			}
			else if(computerUnits.get(i).getPlayerClass().equals("warrior")){
				graphics.drawImage(sprites.getEnemyWarriorSprite(),computerUnits.get(i).getY()*25, computerUnits.get(i).getX()*25, 25, 25, null);
			} 
			else if(computerUnits.get(i).getPlayerClass().equals("sorceror")){
				graphics.drawImage(sprites.getEnemySorcerorSprite(),computerUnits.get(i).getY()*25, computerUnits.get(i).getX()*25, 25, 25, null);
			} 
			
		}
	}
	
	/** draws the amount of units the player has yet to choose to start the new round*/
	public void drawRemainingTemporaryUnits(){
		String remainingUnits = "" + tempUnits;
		Font serif = new Font("Serif",Font.PLAIN,25) ;
		graphics.setFont(serif);
		graphics.setColor(new Color(0,255,205));
		graphics.drawString(remainingUnits, 890, 300);
	}
	/** draws the amount of units the player and computer has yet*/
	public void drawRemainingUnits(){
		String remainingPlayerUnits = "" + playerUnits.size();
		String remainingComputerUnits = "" + computerUnits.size();
		Font serif = new Font("Serif",Font.PLAIN,25) ;
		graphics.setFont(serif);
		graphics.setColor(new Color(0,255,205));
		graphics.drawString(remainingPlayerUnits, 890, 75);
		graphics.drawString(remainingComputerUnits, 890, 135);
	}

	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	public FireEmblem() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
		frame = new JFrame();
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
	}
	
	public static void main(String[] args) {
		FireEmblem game = new FireEmblem();
		game.frame.setResizable(false);
		game.frame.add(game); //game is a component because it extends Canvas
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
	
	//starts a new thread for the game
	public synchronized void start() {
		thread = new Thread(this, "Game");
		running = true;
		thread.start();
	}
	//stops the game thread and quits
	public synchronized void stop() {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	//main game loop
	public void run() {
		init();
		long startTime = System.nanoTime();
		double ns = 1000000000.0 / UPS;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		long secondTimer = System.nanoTime();
		while(running) {
			long now = System.nanoTime();
			delta += (now - startTime) / ns;
			startTime = now;
			while(delta >= 1) {
				update();
				delta--;
				updates++;
			}
			render();
			frames++;
			
			if(System.nanoTime() - secondTimer > 1000000000) {
				this.frame.setTitle("Fire Emblem: Anamalous " + updates + " ups  ||  " + frames + " fps");
				secondTimer += 1000000000;
				frames = 0;
				updates = 0;
			}
		}
		System.exit(0);
	}	
	public void render() {	
		BufferStrategy bs = getBufferStrategy(); //method from Canvas class
		
		if(bs == null) {
			createBufferStrategy(3); //creates it only for the first time the loop runs (trip buff)
			return;
		}
		
		graphics = (Graphics2D)bs.getDrawGraphics();
		draw();
		graphics.dispose();
		bs.show();
	}
		
}