/** Name: William Granados
 *  Date: 09/06/14
 *  Purpose: to handle details related to image handling and distribution
 * */
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Sprites {
	private Image levelOneMap;
	private Image levelTwoMap;
	private Image levelThreeMap;
	
	private Image sidebar;
	private Image sidebarKnight;
	private Image sidebarSorceror;
	private Image sidebarSwordmaster;
	private Image sidebarWarrior;
	private Image sidebarTeamOne;
	private Image sidebarTeamTwo;
	private Image sidebarTeamThree;
	
	private Image traversableTile;
	private Image attackingTile;
	private Image placableTile;
	
	private Image mainMenu;
	private Image mainMenuPlay;
	private Image mainMenuInstructions;
	private Image mainMenuRating;
	private Image mainMenuHighScores;
	private Image mainMenuCredentials;
	private Image mainMenuDifficultySettings;
	private Image mainMenuExit;
	
	private Image instructionsPage;
	private Image ratingsPage;
	private Image highscoresPage;
	private Image difficultySettingsNormalPage;
	private Image difficultySettingsHardPage;
	private Image difficultySettingsInsanePage;
	
	private Image credentialsPage;
	
	private Image allySwordsmasterSprite;
	private Image allyWarriorSprite;
	private Image allyKnightSprite;
	private Image allySorcerorSprite;
	
	private Image enemySwordsmasterSprite;
	private Image enemyWarriorSprite;
	private Image enemyKnightSprite;
	private Image enemySorcerorSprite;
	
	
	private Image restingSwordsmasterSprite;
	private Image restingWarriorSprite;
	private Image restingKnightSprite;
	private Image restingSorcerorSprite;
	
	
	private Image emblemOne;
	private Image emblemTwo;
	private Image emblemThree;
	private Image emblemFour;
	private Image emblemFive;
	
	/** initiates sprites used in the grpahical interface*/
	public Sprites(){
		initMaps();
		initSidebars();
		initTiles();
		initMainMenu();
		initMainMenuPages();
		initCharacters();
		initEmblems();
	}
	/** code for getting images*/
	private Image uploadImage(String file){
		try {
		    return ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("Error fetching " + file);
			return null;
		}
	}
	
	/** initiates sprites related the level layout*/
	public void initMaps(){
		this.levelOneMap 				= uploadImage("Resources/Images/Maps/level1Map.png");
		this.levelTwoMap 				= uploadImage("Resources/Images/Maps/level2Map.png");
		this.levelThreeMap 				= uploadImage("Resources/Images/Maps/level3Map.png");
	}
	public void initSidebars(){
		this.sidebar 					= uploadImage("Resources/Images/Game Sidebar/Sidebar.png");
		this.sidebarKnight 				= uploadImage("Resources/Images/Game Sidebar/SidebarKnight.png");
		this.sidebarSorceror 			= uploadImage("Resources/Images/Game Sidebar/SidebarSorceror.png");
		this.sidebarSwordmaster 		= uploadImage("Resources/Images/Game Sidebar/SidebarSwordmaster.png");
		this.sidebarWarrior 			= uploadImage("Resources/Images/Game Sidebar/SidebarWarrior.png");
		
		this.sidebarTeamOne 					= uploadImage("Resources/Images/Game Sidebar/SidebarTeamOne.png");
		this.sidebarTeamTwo 					= uploadImage("Resources/Images/Game Sidebar/SidebarTeamTwo.png");
		this.sidebarTeamThree 					= uploadImage("Resources/Images/Game Sidebar/SidebarTeamThree.png");
		
	}
	/** */
	public void initTiles(){
		this.traversableTile 			= uploadImage("Resources/Images/Tiles/traversibleTile.png");
		this.attackingTile				= uploadImage("Resources/Images/Tiles/attackingTile.png");
		this.placableTile				= uploadImage("Resources/Images/Tiles/placableTile.png");
	}
	public void initMainMenu(){
		this.mainMenu             		= uploadImage("Resources/Images/Menu/mainMenu.png");
		this.mainMenuPlay         		= uploadImage("Resources/Images/Menu/mainMenuPlay.png");
		this.mainMenuInstructions 		= uploadImage("Resources/Images/Menu/mainMenuInstructions.png");
		this.mainMenuRating       		= uploadImage("Resources/Images/Menu/mainMenuRating.png");
		this.mainMenuHighScores   		= uploadImage("Resources/Images/Menu/mainMenuHigh-Scores.png");
		this.mainMenuCredentials        = uploadImage("Resources/Images/Menu/mainMenuCredentials.png");
		this.mainMenuDifficultySettings = uploadImage("Resources/Images/Menu/mainMenuDifficultySettings.png");
		this.mainMenuExit 				= uploadImage("Resources/Images/Menu/mainMenuExit.png");
	}
	public void initMainMenuPages(){
		this.instructionsPage 			= uploadImage("Resources/Images/Menu/Instructions.png");
		this.ratingsPage 				= uploadImage("Resources/Images/Menu/Ratings.png");
		this.highscoresPage				= uploadImage("Resources/Images/Menu/HighScores.png");
		this.difficultySettingsNormalPage	    = uploadImage("Resources/Images/Menu/Difficulty SettingsNormal.png");
		this.difficultySettingsHardPage	    = uploadImage("Resources/Images/Menu/Difficulty SettingsHard.png");
		this.difficultySettingsInsanePage	    = uploadImage("Resources/Images/Menu/Difficulty SettingsInsane.png");
		
		
		this.credentialsPage 			= uploadImage("Resources/Images/Menu/Credentials.png");
	}
	public void initCharacters(){
		this.allySwordsmasterSprite		    = uploadImage("Resources/Images/Characters/swordmaster1.png");
		this.allyWarriorSprite			    = uploadImage("Resources/Images/Characters/warrior1.png");
		this.allyKnightSprite			    = uploadImage("Resources/Images/Characters/knight1.png");
		this.allySorcerorSprite 			= uploadImage("Resources/Images/Characters/sorceror1.png");
		
		this.enemySwordsmasterSprite		    = uploadImage("Resources/Images/Characters/swordmaster2.png");
		this.enemyWarriorSprite			    = uploadImage("Resources/Images/Characters/warrior2.png");
		this.enemyKnightSprite			    = uploadImage("Resources/Images/Characters/knight2.png");
		this.enemySorcerorSprite 			= uploadImage("Resources/Images/Characters/sorceror2.png");
		
		this.restingSwordsmasterSprite = uploadImage("Resources/Images/Characters/swordmaster3.png");
		this.restingWarriorSprite = uploadImage("Resources/Images/Characters/warrior3.png");
		this.restingKnightSprite = uploadImage("Resources/Images/Characters/knight3.png");
		this.restingSorcerorSprite =uploadImage("Resources/Images/Characters/sorceror3.png");
		
		
	}
	
	public void initEmblems(){
		this.emblemOne = uploadImage("Resources/Images/Emblems/emblemOne.png");
		this.emblemTwo = uploadImage("Resources/Images/Emblems/emblemTwo.png");
		this.emblemThree = uploadImage("Resources/Images/Emblems/emblemThree.png");
		this.emblemFour = uploadImage("Resources/Images/Emblems/emblemFour.png");
		this.emblemFive = uploadImage("Resources/Images/Emblems/emblemFive.png");
		
	}
	
	// map related
	public Image getLevelOneMap() {
		return levelOneMap;
	}
	public Image getLevelTwoMap() {
		return levelTwoMap;
	}
	public Image getLevelThreeMap() {
		return levelThreeMap;
	}
	
	// tile related
	public Image getTraversableTile() {
		return traversableTile;
	}
	public Image getAttackingTile() {
		return attackingTile;
	}
	public Image getPlacableTile() {
		return placableTile;
	}
	
	// sidebar related
	public Image getSidebar() {
		return sidebar;
	}
	public Image getSidebarKnight() {
		return sidebarKnight;
	}
	public Image getSidebarSorceror() {
		return sidebarSorceror;
	}
	public Image getSidebarSwordmaster() {
		return sidebarSwordmaster;
	}
	public Image getSidebarWarrior() {
		return sidebarWarrior;
	}
	
	public Image getSidebarTeamOne() {
		return sidebarTeamOne;
	}
	public Image getSidebarTeamTwo() {
		return sidebarTeamTwo;
	}
	public Image getSidebarTeamThree() {
		return sidebarTeamThree;
	}
	
	// menu related
	public Image getMainMenu() {
		return mainMenu;
	}
	public Image getMainMenuPlay() {
		return mainMenuPlay;
	}
	public Image getMainMenuInstructions() {
		return mainMenuInstructions;
	}
	public Image getMainMenuRating() {
		return mainMenuRating;
	}
	public Image getMainMenuHighScores() {
		return mainMenuHighScores;
	}
	public Image getMainMenuCredentials() {
		return mainMenuCredentials;
	}
	public Image getMainMenuDifficultySettings() {
		return mainMenuDifficultySettings;
	}
	public Image getMainMenuExit() {
		return mainMenuExit;
	}
	public Image getMainMenuState(int i){
		switch(i){
			case 0: return getMainMenuPlay();
			case 1: return getMainMenuInstructions();
			case 2: return getMainMenuRating();
			case 3: return getMainMenuHighScores();
			case 4: return getMainMenuDifficultySettings();
			case 5: return getMainMenuCredentials();
			case 6: return getMainMenuExit();
			default: return getMainMenu();
		}
	}
	
	public Image getInstructionsPage() {
		return instructionsPage;
	}
	public Image getRatingsPage() {
		return ratingsPage;
	}
	
	
	public Image getHighscoresPage() {
		return highscoresPage;
	}
	public Image getDifficultySettingsPageState(int i){
		switch(i){
		case 0: return getDifficultySettingsNormalPage();
		case 1: return getDifficultySettingsHardPage();
		case 2: return getDifficultySettingsInsanePage();
		default: return getDifficultySettingsNormalPage();
		}
	}
	public Image getDifficultySettingsNormalPage() {
		return difficultySettingsNormalPage;
	}
	public Image getDifficultySettingsHardPage() {
		return difficultySettingsHardPage;
	}
	public Image getDifficultySettingsInsanePage() {
		return difficultySettingsInsanePage;
	}
	
	public Image getCredentialsPage() {
		return credentialsPage;
	}
	
	// character sprite related
	public Image getAllySwordmasterSprite() {
		return allySwordsmasterSprite;
	}
	public Image getAllyWarriorSprite() {
		return allyWarriorSprite;
	}
	public Image getAllyKnightSprite() {
		return allyKnightSprite;
	}
	public Image getAllySorcerorSprite() {
		return allySorcerorSprite;
	}
	
	public Image getEnemySwordsmasterSprite() {
		return enemySwordsmasterSprite;
	}
	public Image getEnemyWarriorSprite() {
		return enemyWarriorSprite;
	}
	public Image getEnemyKnightSprite() {
		return enemyKnightSprite;
	}	
	public Image getEnemySorcerorSprite() {
		return enemySorcerorSprite;
	}
	
	public Image getRestingSwordsmasterSprite() {
		return restingSwordsmasterSprite;
	}
	public Image getRestingWarriorSprite() {
		return restingWarriorSprite;
	}
	public Image getRestingKnightSprite() {
		return restingKnightSprite;
	}
	public Image getRestingSorcerorSprite() {
		return restingSorcerorSprite;
	}
	
	
	public Image getEmblemState(int i){
		switch(i){
		case 0: return getEmblemOne();
		case 1: return getEmblemTwo();
		case 2: return getEmblemThree();
		case 3: return getEmblemFour();
		case 4: return getEmblemFive();
		default: return getEmblemOne();
		}
	}
	public Image getEmblemOne() {
		return emblemOne;
	}
	public Image getEmblemTwo() {
		return emblemTwo;
	}
	public Image getEmblemThree() {
		return emblemThree;
	}
	public Image getEmblemFour() {
		return emblemFour;
	}
	public Image getEmblemFive() {
		return emblemFive;
	}
	
	
	
}
