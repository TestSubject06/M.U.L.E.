/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mule;


import gears.GBase;
import gears.GInput;
import gears.GState;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;
/**
 *
 * @author Garrett Mallory, Jory Folker
 * @version Milestone 7
 * @date 10/4/13
 */
public class GameState extends GState{
	
	
	private Image raceImages;
	private MuleSprite mule;
	private Map map;	
	private HUD_Timer[] hudTimers;
	private LandSelector[] landSelectors;
	private Player[] playerArray;
	private int round; // round 0 = land selection, round 1-12 = game play
	private int roundPhase; // 0 = land purchase, 1 = game player for each person, 2 = land auction
	private int playerTurn; //0-3 for each player;
	private int numPlayers;
	private int grantsRemaining;
	int[] playerScoreArray;
	private int[] turnOrder;
	private int[] usedRaces, usedColors;
	private double fullTime, halfTime, noTime;
	
	private boolean landPurchase = false;
	private boolean selectionPhase;
	
	private boolean loadedGame = false;
	
	/**
	 * @Constructor.
	 * Does nothing as most logic is held in the create method.
	 * Default Constructor give sets the maximum time as 50 seconds

	 * Time is allotted in terms of seconds
	 */
	public GameState(double fullTime, double halfTime, double noTime, int numPlayers, int[] usedRaces, int[] usedColors){
		this.fullTime = fullTime;
		this.halfTime = halfTime;
		this.noTime = noTime;
		this.numPlayers = numPlayers;
		this.usedRaces = usedRaces;
		this.usedColors = usedColors;
	}
	
	
	public GameState(boolean loadGame){
		this.loadedGame = loadGame;
	}
	

	/**
	 * Determines if a piece of land is already owned and if not, land is sold to player
	 * @param int playerID, int cost, MuleTile selection
	 * @return true if purchased, false if already owned
	 */
	public boolean landPurchase(int playerID, int cost, MuleTile selection){
		//If the land is unowned and player has at least 500 money, then purchase
		if((selection.owner == -1)&&(playerArray[playerID].money>=cost||playerArray[playerID].landGrantRemaining>0)){
			selection.setOwner(playerID, playerArray[playerID].color);
			playerArray[playerID].money -= cost;
			if(playerArray[playerID].money<500) playerArray[playerID].landGrantRemaining--;
			playerArray[playerID].landCount++;
			GBase.log("P1 Money: " + playerArray[playerID].money);
			return true;
		}
		else{
			GBase.log("Invalid Purchase");
			return false;
		}
	}

	/*
	 * Updates players' scores in term of net worth (money+ number of land own)
	 * @author Jory Folker
	 * UPDATED: Garrett Mallory
	 */
	public void calculateScores(){
		for(int i = 0; i<playerScoreArray.length; i++){
			playerScoreArray[i]= playerArray[i].money+playerArray[i].resources[0]+
					playerArray[i].resources[1]+playerArray[i].resources[2]+
							playerArray[i].resources[3]+playerArray[i].landCount+500;
		}
	}
	


	/**
	 * Calculate who turn order based on score in ascending order
	 * @param
	 * @return int[] turnOrder
	 * @author Jory Folker
	 */
	public void turnOrder(){
		//Basically just bubble sort to find the ranking
		for(int i=0; i<numPlayers-1; i++){
			for(int j=0; j<i; j++){
				if(playerScoreArray[turnOrder[j]]>playerScoreArray[turnOrder[j+1]]){
					int temp=turnOrder[j+1];
					turnOrder[j+1]=turnOrder[j];
					turnOrder[j]=temp;
				}
			}
		}
		SaveHelper.turnOrder = turnOrder;
	}

	/**
	 * Computes the time in seconds allotted to a player for his turn
	 * @param Player p, int roundNumber
	 * @return double
	 * @author Jory Folker
	 */
	public double turnLength(Player p, int roundNumber){
		int foodRequirement = 3+(roundNumber/4);
		//If player has no food
		if(p.resources[0]<=0)
			return noTime;
		//If player has food but less than requirement
		else if(p.resources[0]<foodRequirement)
			return halfTime;
		//If player has 
		else
			return fullTime;
	}
	
	public void randomEvent(Player actingPlayer, int rank){
		Random randy = new Random();
		int eventNo=randy.nextInt(8);
		int multiplier = ((round/4)+1)*25;
		int temp;
		if(eventNo==1){
			GBase.log("YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS. ");
			actingPlayer.resources[0]+=3;
			actingPlayer.resources[1]+=2;
		}
		if(eventNo==2){
			GBase.log("A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE. ");
			actingPlayer.resources[2]+=2;
		}
		if(eventNo==3){
			temp=8*multiplier;
			GBase.log("THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $" + temp);
			actingPlayer.money+=temp;
		}
		if(eventNo==4){
			temp=2*multiplier;
			GBase.log("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $" + temp);
			actingPlayer.money+=temp;
		}
		if(eventNo==5){
			if(rank>0){
				temp=4*multiplier;
				GBase.log("FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $" + temp);
				actingPlayer.money-=temp;
			}
		}
		if(eventNo==6){
			if(rank>0){
				GBase.log("MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.");
				actingPlayer.resources[0] = (actingPlayer.resources[0]/2);
			}
		}
		if(eventNo==7){
			if(rank>0){
				temp=6*multiplier;
				GBase.log("YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU " + temp + " TO CLEAN IT UP. ");
				actingPlayer.money-=temp;
			}
		}
		
		if(actingPlayer.money<0)
			actingPlayer.money=0;
	}


	/**
	 * Runs the logic for selecting land during land grants
	 * @author Zach
	 */
	public void landSelectionPhase(){
		if(!landSelectors[roundPhase].exists){
			landSelectors[roundPhase].exists = true;
		}
		if(GBase.input.justPressed(GInput.LeftArrow) && landSelectors[roundPhase].x != 0){
			landSelectors[roundPhase].x -= 71;
		}
		if(GBase.input.justPressed(GInput.RightArrow) && landSelectors[roundPhase].x <= 560){
			landSelectors[roundPhase].x += 71;
		}
		if(GBase.input.justPressed(GInput.UpArrow) && landSelectors[roundPhase].y != 0){
			landSelectors[roundPhase].y -= 96;
		}
		if(GBase.input.justPressed(GInput.DownArrow) && landSelectors[roundPhase].y <= 380){
			landSelectors[roundPhase].y += 96;
		}
		// if time runs out and the selector is still on the town, end that term as it.
		if((landSelectors[roundPhase].x == 284 && landSelectors[roundPhase].y == 192) && hudTimers[roundPhase].isFinished()){
			GBase.log("landgrant remaining: " + playerArray[roundPhase].landGrantRemaining);
			playerArray[roundPhase].landGrantRemaining++;
			GBase.log("landgrant remaining: " + playerArray[roundPhase].landGrantRemaining);
			landSelectors[roundPhase].exists = false;
			hudTimers[roundPhase].active = false;
			hudTimers[roundPhase].setCurIndex(25);
			roundPhase++;
			if(roundPhase > numPlayers-1){
				roundPhase = 0;
				grantsRemaining -= 1;
				if(grantsRemaining <= 0){
					round=1;
					selectionPhase = false;
					GBase.log("Round " + round + ": START!");
				}
			}
			hudTimers[roundPhase].reset();
		}
		if((GBase.input.justPressed(GInput.X)||hudTimers[roundPhase].isFinished()) && !(landSelectors[roundPhase].x == 284 && landSelectors[roundPhase].y == 192)){
			int x = (int)(landSelectors[roundPhase].x);
			int y = (int)(landSelectors[roundPhase].y);	
			if(landPurchase(roundPhase, grantsRemaining > 0 ? 0 : 500, map.muleTiles[(x/71)+((y/96)*9)])){
				landSelectors[roundPhase].exists = false;
				hudTimers[roundPhase].active = false;
				hudTimers[roundPhase].setCurIndex(25);
				roundPhase++;
				if(roundPhase > numPlayers-1){
					roundPhase = 0;
					grantsRemaining -= 1;
					if(grantsRemaining <= 0){
						round=1;
						selectionPhase = false;
						GBase.log("Round " + round + ": START!");
						SaveHelper.roundNumber = round;
					}
				}
			}
			hudTimers[roundPhase].reset();
		}
	}
	
	public void landPurchase(){
		if(!landSelectors[turnOrder[playerTurn]].exists){
			landSelectors[turnOrder[playerTurn]].exists = true;
		}
		if(GBase.input.justPressed(GInput.LeftArrow)){
			landSelectors[turnOrder[playerTurn]].x -= 71;
		}
		if(GBase.input.justPressed(GInput.RightArrow)){
			landSelectors[turnOrder[playerTurn]].x += 71;
		}
		if(GBase.input.justPressed(GInput.UpArrow)){
			landSelectors[turnOrder[playerTurn]].y -= 96;
		}
		if(GBase.input.justPressed(GInput.DownArrow)){
			landSelectors[turnOrder[playerTurn]].y += 96;
		}
		if(GBase.input.justPressed(GInput.X)||hudTimers[turnOrder[playerTurn]].isFinished()){
			int x = (int)(landSelectors[turnOrder[playerTurn]].x);
			int y = (int)(landSelectors[turnOrder[playerTurn]].y);	
			if(landPurchase(turnOrder[playerTurn], 500, map.muleTiles[(x/71)+((y/96)*9)])){
				landSelectors[turnOrder[playerTurn]].exists = false;
				playerArray[turnOrder[playerTurn]].exists = true;
				GBase.stateManager.addState(new TownState(playerArray[turnOrder[playerTurn]], hudTimers[turnOrder[playerTurn]], round));
				landPurchase = false;
			}
		}
	}
	
	/**
	 * Executes the procedure for a round which consists of turns for each player in calculated order
	 * After the last player has his turn, turn order is updated (and consequently, so are scores)
	 * 		and the game moves on to the next round
	 * @author Jory Folker
	 */
	public void round(){
		if(playerTurn>numPlayers-1){
			GBase.log("End of Round " + round);
			round++;
			GBase.log("Round " + round + ": START!;");
			playerTurn=0;
			this.calculateScores();
			this.turnOrder();
			// handle production for all tiles for all players -> finds new production and adds to current amount
			int[][] resourceMatrix= map.calcProduction();
			
			for(int i = 0; i < numPlayers; i ++){
				playerArray[i].resources = map.addArrays(playerArray[i].resources, resourceMatrix[i]);
			}
			SaveHelper.roundNumber = round;
			//add mules to store
			int mulesToAdd = numPlayers+1;
			while(StoreState.storeResources[2]>=1 && StoreState.numMules<18 && mulesToAdd>0){
				StoreState.numMules++;
				StoreState.storeResources[2]--;
				mulesToAdd--;
			}
		}
		SaveHelper.turnNumber = playerTurn;
		Player actingPlayer = playerArray[turnOrder[playerTurn]];
		this.turn(actingPlayer);
	}
	
	/**
	 * Execute the procedure for a player's turn
	 * The turn ends if the timer runs out or the player presses "E" to End the turn
	 * If the player presses X on the square containing the town, the player will enter the town
	 * If the player walks out of the town, the player returns to the map
	 * @author Jory Folker
	 * @param actingPlayer
	 */
	public void turn(Player actingPlayer){
		if(!actingPlayer.exists){
			actingPlayer.exists = true;
			actingPlayer.setPosition(284, 192);
			if(playerTurn < numPlayers){
				hudTimers[turnOrder[playerTurn]].reset(turnLength(actingPlayer, round));
				hudTimers[turnOrder[playerTurn]].active = true;
			}
		}
		
		//Enter Town.
		if(GBase.input.justPressed(GInput.X)){
			if((actingPlayer.x + actingPlayer.width/2) > 284 && (actingPlayer.x + actingPlayer.width/2) < (284+71) && (actingPlayer.y + actingPlayer.height/2) > 192 && (actingPlayer.y + actingPlayer.height/2) < (192+96)){
				GBase.stateManager.addState(new TownState(actingPlayer, hudTimers[turnOrder[playerTurn]], round));
			}
		}

		//Place Mule.
		if(GBase.input.justPressed(GInput.X) && actingPlayer.muleType != 0 && map.getTileAtCoords(actingPlayer.x+actingPlayer.width/2, actingPlayer.y+actingPlayer.height/2).owner == playerTurn &&
				map.getTileAtCoords(actingPlayer.x+actingPlayer.width/2, actingPlayer.y+actingPlayer.height/2).mule == -1){
			
			map.getTileAtCoords(actingPlayer.x+actingPlayer.width/2, actingPlayer.y+actingPlayer.height/2).setMule(actingPlayer.muleType-1);
			mule.exists = false;
			actingPlayer.muleType = 0;
		}else{
			if(GBase.input.justPressed(GInput.X) && !((actingPlayer.x + actingPlayer.width/2) > 284 && (actingPlayer.x + actingPlayer.width/2) < (284+71) && 
					(actingPlayer.y + actingPlayer.height/2) > 192 && (actingPlayer.y + actingPlayer.height/2) < (192+96))){
				mule.play("die");
				actingPlayer.muleType = 0;
			}
		}
		
		//If the player runs out of time, or chooses to voluntarily end his turn, the game moves on
		if(hudTimers[turnOrder[playerTurn]].isFinished()||GBase.input.justPressed(GInput.E)){
			switch(turnOrder[playerTurn]){
				case 0:
					SaveHelper.player1Resource = playerArray[turnOrder[playerTurn]].resources;
					SaveHelper.p1M = playerArray[turnOrder[playerTurn]].money;
				break;
				case 1:
					SaveHelper.player2Resource = playerArray[turnOrder[playerTurn]].resources;
					SaveHelper.p2M = playerArray[turnOrder[playerTurn]].money;
					break;
				case 2:
					SaveHelper.player3Resource = playerArray[turnOrder[playerTurn]].resources;
					SaveHelper.p3M = playerArray[turnOrder[playerTurn]].money;
					break;
				case 3:
					SaveHelper.player4Resource = playerArray[turnOrder[playerTurn]].resources;
					SaveHelper.p4M = playerArray[turnOrder[playerTurn]].money;
					break;
			}
			SaveHelper.mapInfo = map.updateSaveArray();
			SaveHelper.storeResources = StoreState.storeResources;
			SaveHelper.numMules = StoreState.numMules;
			
			GBase.log("Player " + turnOrder[playerTurn] + ", your turn is over!");
			hudTimers[turnOrder[playerTurn]].active = false;
			hudTimers[turnOrder[playerTurn]].setCurIndex(25);
			playerTurn++;
			actingPlayer.exists = false;
			
			//Reset the timer for the next guy
			if(playerTurn<numPlayers-1){
				Random randy = new Random();
				if(randy.nextInt(100)<27){
					this.randomEvent(playerArray[turnOrder[playerTurn]], playerTurn);
				}
			}
			else{
				Random randy = new Random();
				if(randy.nextInt(100)<27){
					this.randomEvent(playerArray[turnOrder[0]], playerTurn);
				}
			}
		}
		// if player is going out of bounds
		if(actingPlayer.x <= 0 - actingPlayer.width/2)
			actingPlayer.x = 0 - actingPlayer.width/2;
		if(actingPlayer.x > GBase.gameWidth - actingPlayer.width/2)
			actingPlayer.x = GBase.gameWidth - actingPlayer.width/2;
		if(actingPlayer.y < 0 - actingPlayer.height/2)
			actingPlayer.y = 0 - actingPlayer.height/2;	
		if(actingPlayer.y > GBase.gameHeight - actingPlayer.height/2)
			actingPlayer.y = GBase.gameHeight - actingPlayer.height/2;
	}
	
	@Override
	public void update() {
		super.update();

		if(GBase.input.justReleased(GInput.ESC)){
			GBase.stateManager.addState(new PauseState());
		} 
		//Land Selection
		if(selectionPhase){
			this.landSelectionPhase();
		}else if(landPurchase){
			landPurchase();
		}
		//Normal Rounds
		else if(round>=1 && round<=12){
			this.round();
			if(playerTurn < numPlayers)
				hudTimers[turnOrder[playerTurn]].updateResourcePanel(playerArray[turnOrder[playerTurn]].money, playerArray[turnOrder[playerTurn]].resources);
		}
		//End of game
		else{
			GBase.log("\nGAME OVER!");
			this.turnOrder();
			GBase.log("And the winner is: Player " + turnOrder[numPlayers-1]);
		}
	}


	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void create() {
		super.create();
		if(loadedGame){
			numPlayers = SaveHelper.numPlayers;
			
			playerScoreArray = new int[numPlayers];
			turnOrder = SaveHelper.turnOrder;
			playerArray = new Player[numPlayers];
			landSelectors = new LandSelector[numPlayers];
			round = SaveHelper.roundNumber;
			roundPhase = 0;
			raceImages = AssetManager.playerSprites;
			selectionPhase = false;
			grantsRemaining = 0;
			playerTurn = SaveHelper.turnNumber;
			map = new Map();
			map.loadFromHugeArray(SaveHelper.mapInfo, AssetManager.mapTiles, 71, 96, 1, 1);
			map.visible = true;
			add(map);
			
			usedColors = new int[]{SaveHelper.p1C, SaveHelper.p2C, SaveHelper.p3C, SaveHelper.p4C};
			usedRaces = new int[]{SaveHelper.p1R, SaveHelper.p2R, SaveHelper.p3R, SaveHelper.p4R};
			for(int i = 0; i<playerArray.length;i++){
				// town coordinates are 284 x, 192 y
				playerArray[i] = new Player(284, 192, raceImages, usedRaces[i], usedColors[i]);
				playerArray[i].exists = false;
				landSelectors[i] = new LandSelector(284, 192, usedColors[i]);
				landSelectors[i].exists = false;
				add(landSelectors[i]);
				add(playerArray[i]);
			}
			playerArray[0].money = SaveHelper.p1M;
			playerArray[0].resources = SaveHelper.player1Resource;
			playerArray[1].money = SaveHelper.p2M;
			playerArray[1].resources = SaveHelper.player2Resource;
			if(numPlayers >= 3) { playerArray[2].money = SaveHelper.p3M; playerArray[2].resources = SaveHelper.player3Resource; }
			if(numPlayers == 4) { playerArray[3].money = SaveHelper.p3M; playerArray[3].resources = SaveHelper.player4Resource; }
			
			StoreState.storeResources = SaveHelper.storeResources;
			
			mule = new MuleSprite(0,0);
			mule.exists = false;
			add(mule);
			
			fullTime = SaveHelper.roundTime;
			halfTime = SaveHelper.roundHalfTime;
			noTime = SaveHelper.roundNoTime;
			
			hudTimers = new HUD_Timer[numPlayers];
			for(int i = 0; i < numPlayers; i++){
				hudTimers[i] = new HUD_Timer(0+(i%2)*600, 0+(i/2)*440, fullTime, usedRaces[i], usedColors[i]);
				hudTimers[i].setOrientation(i);
				if(i!=turnOrder[playerTurn]){
					hudTimers[i].active = false;
					hudTimers[i].setCurIndex(25);
				}
				add(hudTimers[i]);
			}
		}else{
			playerScoreArray = new int[numPlayers];
			// make turn order length numPlayers and initialize to 0, 1, 2, 3
			turnOrder = new int[numPlayers];
			for (int i =0; i<turnOrder.length; i++){
				turnOrder[i] = i;
			}
			playerArray = new Player[numPlayers];
			landSelectors = new LandSelector[numPlayers];
			round = 0;
			roundPhase = 0;
			raceImages = AssetManager.playerSprites;
			selectionPhase = true;
			grantsRemaining = 2;
			playerTurn=0;
	
			map = new Map();
			// mountain1 = 1
			// mountain2 = 2
			// mountain3 = 3
			// river = 4
			// plain = 5
			// town is 6
			map.loadMap("5,5,1,5,4,5,3,5,5\n"
					  + "5,1,5,5,4,5,5,5,3\n"
					  + "3,5,5,5,6,5,5,5,1\n"
					  + "5,2,5,5,4,5,2,5,5\n"
					  + "5,5,2,5,4,5,5,5,2", AssetManager.mapTiles, 71, 96, 1, 1);
			map.visible = true;
			add(map);
	
			for(int i = 0; i<playerArray.length;i++){
				// town coordinates are 284 x, 192 y
				playerArray[i] = new Player(284, 192, raceImages, usedRaces[i], usedColors[i]);
				playerArray[i].exists = false;
				landSelectors[i] = new LandSelector(284, 192, usedColors[i]);
				landSelectors[i].exists = false;
				add(landSelectors[i]);
				add(playerArray[i]);
			}
			SaveHelper.numPlayers = numPlayers;
			SaveHelper.p1C = usedColors[0];
			SaveHelper.p2C = usedColors[1];
			SaveHelper.p3C = usedColors[2];
			SaveHelper.p4C = usedColors[3];
			SaveHelper.p1R = usedRaces[0];
			SaveHelper.p2R = usedRaces[1];
			SaveHelper.p3R = usedRaces[2];
			SaveHelper.p4R = usedRaces[3];
			SaveHelper.turnOrder = turnOrder;
			SaveHelper.player1Resource = new int[]{10, 15, 20, 25};
			SaveHelper.player2Resource = new int[]{10, 15, 20, 25};
			SaveHelper.player3Resource = new int[]{10, 15, 20, 25};
			SaveHelper.player4Resource = new int[]{10, 15, 20, 25};
			SaveHelper.p1M = 1000;
			SaveHelper.p2M = 1000;
			SaveHelper.p3M = 1000;
			SaveHelper.p4M = 1000;
			StoreState.storeResources = new int[]{15, 15, 15, 0};
			SaveHelper.storeResources = StoreState.storeResources;
			SaveHelper.mapInfo = map.updateSaveArray();
			SaveHelper.numMules = StoreState.numMules;
			SaveHelper.roundTime = (int)fullTime;
			SaveHelper.roundHalfTime = (int)halfTime;
			SaveHelper.roundNoTime = (int)noTime;
			
			
			mule = new MuleSprite(0,0);
			mule.exists = false;
			add(mule);
			
			
			hudTimers = new HUD_Timer[numPlayers];
			for(int i = 0; i < numPlayers; i++){
				hudTimers[i] = new HUD_Timer(0+(i%2)*600, 0+(i/2)*440, fullTime, usedRaces[i], usedColors[i]);
				hudTimers[i].setOrientation(i);
				if(i!=0){
					hudTimers[i].active = false;
					hudTimers[i].setCurIndex(25);
				}
				add(hudTimers[i]);
			}
		}
	}

	@Override
	public void passBack(String message){
		if(message != "land"){
			playerArray[playerTurn].x = 284;
			playerArray[playerTurn].y = 192;
			
			int muleType = Integer.parseInt(message);
			if(muleType > 0){
				mule.x = playerArray[playerTurn].x;
				mule.y = playerArray[playerTurn].y;
				mule.setFollowTarget(playerArray[playerTurn]);
				mule.exists = true;
				mule.play("default");
			}
		}else{
			playerArray[playerTurn].exists = false;
			mule.exists = false;
			landPurchase = true;
		}
	}
}
