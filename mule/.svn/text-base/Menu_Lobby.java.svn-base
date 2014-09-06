package mule;

import gears.GBase;
import gears.GPoint;
import gears.GSprite;
import gears.GState;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.io.File;

/**
 * 
 * @author Zack
 *
 */
public class Menu_Lobby extends GState {
	private GSprite backGround;
	private GSprite diffTabs;
	//This will determine where our typing goes, later on.
	private String focus;
	private GPoint helperPoint;
	private GSprite blackoutShade;

	private Button startGame;
	private Button addBot;
	private Button LoadGame;
	private PlayerList playerlist;


    @Override
    public void create() {
        super.create();
        focus = "";
        helperPoint = new GPoint(0,0);
        
        backGround = new GSprite(0, 0, AssetManager.lobbyBackground);
        add(backGround);
        
        diffTabs = new GSprite(255, 37, null);
        diffTabs.loadGraphic(AssetManager.lobbyDifficulty, true, 199, 10);
        diffTabs.addAnimation("Beginner", new int[]{0}, 0, false);
        diffTabs.addAnimation("Normal", new int[]{1}, 0, false);
        diffTabs.addAnimation("Tourney", new int[]{2}, 0, false);
        diffTabs.addAnimation("Custom", new int[]{3}, 0, false);
        diffTabs.play("Beginner");
        add(diffTabs);
        
        blackoutShade = new GSprite(255, 47, null);
        blackoutShade.createImage(199, 168, Color.black);
        blackoutShade.alpha = 0.5f;
        add(blackoutShade);
        
        startGame = new Button(123, 188, AssetManager.startGameButton, new ButtonListener(){
        	public void buttonClicked(){
        		int numPlayers = playerlist.getNumberPlayers();
        		GBase.stateManager.newStack(new GameState(50, 30, 5, numPlayers, playerlist.usedRaces, playerlist.usedColors));
        	}
        });
        startGame.buttonActive = false;
        add(startGame);
        
        addBot = new Button(24, 188, AssetManager.lobbyAddBot, new ButtonListener(){
        	public void buttonClicked(){
        		playerlist.addPlayer(generateRandomName());
        		if(playerlist.getNumberPlayers() == 4){
        			addBot.buttonActive = false;
        		}
        	}
        });
        add(addBot);
        
        LoadGame = new Button(492, 162, AssetManager.lobbyLoadGame, new ButtonListener(){
        	public void buttonClicked(){
        		//load the game.
        		GBase.input.resetInput();
        		FileDialog chooser2 = new FileDialog(new Frame(), "Select save file to load", FileDialog.LOAD);
        		chooser2.setVisible(true);
        		if(chooser2.getFile() != null){
        			SaveHelper.loadFieldsFromFile(new File(chooser2.getFile()));
        			GBase.stateManager.newStack(new GameState(true));
        		}
        	}
        });
        add(LoadGame);
        
        playerlist = new PlayerList(0, 0, null);
        playerlist.addPlayer(ConfigurationManager.getInstance().getPlayerName());
        add(playerlist);
        
        GBase.registerInputListener(this);
    }


	@Override
	public void update() {
		super.update();
		if(GBase.input.mouseJustPressed()){
			helperPoint = GBase.input.getMouseLocation();
			//Check bounds for the tabs
			if(helperPoint.x >= diffTabs.x && helperPoint.x <= diffTabs.x + diffTabs.frameWidth && helperPoint.y >= diffTabs.y && helperPoint.y <= diffTabs.y + diffTabs.frameHeight){
				helperPoint.x -= diffTabs.x;
				GBase.log(""+helperPoint.x);
				//A normal formula is not a good idea here since the tabs are not the same length
				diffTabs.play("Beginner");
				blackoutShade.alpha = 0.5f;
				if(helperPoint.x > 53){
					diffTabs.play("Normal");
				}
				if(helperPoint.x > 100){
					diffTabs.play("Tourney");
				}
				if(helperPoint.x > 153){
					diffTabs.play("Custom");
					blackoutShade.alpha = 0.0f;
				}
			}
		}
		if(playerlist.getNumberPlayers() > 1){
			startGame.buttonActive = true;
		}else{
			startGame.buttonActive = false;
		}
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}


	/**
	 * Generates random names for the AI players
	 * @return String
	 */
	public String generateRandomName(){
		int name = (int) Math.round(Math.random()*17);
		switch(name){
		case 0:
			return "Bob_Bot";
		case 1:
			return "James_Bot";
		case 2:
			return "Marty_Bot";
		case 3:
			return "Fred_Bot";
		case 4:
			return "Hilfred_Bot";
		case 5:
			return "Howard_Bot";
		case 6:
			return "Gina_Bot";
		case 7:
			return "Nathan_Bot";
		case 8:
			return "Greg_Bot";
		case 9:
			return "Chat_Bot";
		case 10:
			return "Alice_Bot";
		case 11:
			return "Bethany_Bot";
		case 12:
			return "Captain Falcon";
		case 13:
			return "Gordon Ramsay";
		case 14:
			return "Gordon Freeman";
		case 15:
			return "Morgan_Bot";
		case 16:
			return "Summer_Bot";
		case 17:
			return "Brave_Bot";
		}
		return "";
	}

}

