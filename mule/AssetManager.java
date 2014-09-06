package mule;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Zack
 * @version Milestone 5
 */
public class AssetManager {

	//alphabetical order (sort of)

	public static Image annoying, arrow, hostOrJoinBG, HUD_timer, joiningGame, landSelector, landHighlights, lobbyAddBot, 
	lobbyBackground, lobbyColors, lobbyRaces, lobbyDifficulty, mapTiles, mountain1, mountain2, mountain3, mainMenuBG, 
	optionsBG, optionsName, playerPropertyButton, startGameButton, toDoImage,  playerSprites, townInside, mule, gamblingState,
	titleScreen, tileMuleIcons, storeBackground, storeButtonSelector, smallMenu, lobbyLoadGame, hud_resource_panel, hud_timer_connector,
	hud_backing_race, hud_backing_color;

	public static void initImages(){
		annoying = new ImageIcon(AssetManager.class.getResource("../assets/Annoying.png")).getImage();
		arrow = new ImageIcon(AssetManager.class.getResource("../assets/SelectArrow.png")).getImage();   
		hostOrJoinBG = new ImageIcon(AssetManager.class.getResource("../assets/hostOrJoin.png")).getImage();
		HUD_timer = new ImageIcon(AssetManager.class.getResource("../assets/Timer.png")).getImage(); 
		joiningGame = new ImageIcon(AssetManager.class.getResource("../assets/JoiningGame.png")).getImage();        
		landSelector = new ImageIcon(AssetManager.class.getResource("../assets/LandSelectors.png")).getImage();
		landHighlights = new ImageIcon(AssetManager.class.getResource("../assets/TileOwnerOverlays.png")).getImage();
		lobbyBackground = new ImageIcon(AssetManager.class.getResource("../assets/LobbyBackground.png")).getImage();
		lobbyColors = new ImageIcon(AssetManager.class.getResource("../assets/PlayerColors.png")).getImage();
		lobbyDifficulty = new ImageIcon(AssetManager.class.getResource("../assets/Lobby_DifficultyTabs.png")).getImage();
		lobbyAddBot =  new ImageIcon(AssetManager.class.getResource("../assets/Button_AddBot.png")).getImage();
		lobbyRaces =  new ImageIcon(AssetManager.class.getResource("../assets/PlayerRaceIcons.png")).getImage();

		mainMenuBG = new ImageIcon(AssetManager.class.getResource("../assets/MainMenuBG.png")).getImage();
		mapTiles =  new ImageIcon(AssetManager.class.getResource("../assets/Mule_Tiles.png")).getImage();
		mountain1 = new ImageIcon(AssetManager.class.getResource("../assets/mountainTile1.png")).getImage();
		mountain2 = new ImageIcon(AssetManager.class.getResource("../assets/mountainTile2.png")).getImage();
		mountain3 = new ImageIcon(AssetManager.class.getResource("../assets/mountainTile3.png")).getImage();
		mule = new ImageIcon(AssetManager.class.getResource("../assets/imageMule.png")).getImage();
		optionsBG = new ImageIcon(AssetManager.class.getResource("../assets/Menu_Options.png")).getImage();
		optionsName = new ImageIcon(AssetManager.class.getResource("../assets/Menu_Options_Name.png")).getImage();
		playerPropertyButton = new ImageIcon(AssetManager.class.getResource("../assets/Button_PropertySelect.png")).getImage();
		playerSprites = new ImageIcon(AssetManager.class.getResource("../assets/PlayerSprites.png")).getImage();
		startGameButton = new ImageIcon(AssetManager.class.getResource("../assets/Button_StartGame.png")).getImage();
		storeBackground = new ImageIcon(AssetManager.class.getResource("../assets/storeBackground.png")).getImage();
		storeButtonSelector = new ImageIcon(AssetManager.class.getResource("../assets/storeButtonSelector.png")).getImage();
		townInside = new ImageIcon(AssetManager.class.getResource("../assets/TownInside.png")).getImage();
		titleScreen = new ImageIcon(AssetManager.class.getResource("../assets/TitleScreen.png")).getImage();
		tileMuleIcons = new ImageIcon(AssetManager.class.getResource("../assets/TileMuleIcons.png")).getImage();
		smallMenu = new ImageIcon(AssetManager.class.getResource("../assets/smallMenu.png")).getImage();
		lobbyLoadGame = new ImageIcon(AssetManager.class.getResource("../assets/Button_LoadGame.png")).getImage();
		
		hud_resource_panel = new ImageIcon(AssetManager.class.getResource("../assets/HUD_RSCPANEL.png")).getImage();
		hud_timer_connector = new ImageIcon(AssetManager.class.getResource("../assets/HUD_TimerConnectors.png")).getImage();
		hud_backing_race = new ImageIcon(AssetManager.class.getResource("../assets/HUD_PlayerRaceBackings.png")).getImage();
		hud_backing_color = new ImageIcon(AssetManager.class.getResource("../assets/HUD_PlayerColorBacking.png")).getImage();

	}
}
