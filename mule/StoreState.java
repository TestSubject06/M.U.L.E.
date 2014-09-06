package mule;

import gears.GBase;
import gears.GInput;
import gears.GSprite;
import gears.GState;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * *NOTE: all terms from perspective of player.
 * Example: buyingPrices is the price for player to buy.
 * M8 requirements to handle all store logic
 * @author Garrett
 *
 */
public class StoreState extends GState {
	private GSprite background;
	private GSprite buttonSelector;

	private Player player;
	private HUD_Timer hudTimer;
	
	//An array of inventory for rN= 0 food, 1 energy, 2 ore, 3 crystite
	public static int[] storeResources;
	int[] buyingPrices; // cost to purchase
	static int[] sellingPrices; // money gained from the sale of resource
	boolean selectorIsOnBuy;
	private int resourceID;
	public static int numMules = 18;

	/**
	 * Constructor
	 * @param hudTimer 
	 * @param Player
	 */
	public StoreState(Player player, HUD_Timer hudTimer){
		this.player = player;
		this.hudTimer = hudTimer;
		passDraw = false;
	}

	@Override
	public void create(){
		super.create();
		// food, energy, ore, crystite 
		buyingPrices = new int[] {50, 50, 75, 75};
		sellingPrices = new int[] {30, 30, 30, 30};
		storeResources = new int[] {8, 8, 8, 8};
		calculatePrices();

		selectorIsOnBuy = true;
		background = new GSprite(0,0, AssetManager.storeBackground);
		add(background);

		buttonSelector = new GSprite(177, 119, null);
		buttonSelector.loadGraphic(AssetManager.storeButtonSelector, true, 67, 33);
		buttonSelector.addAnimation("default", new int[]{1, 2}, 2, true);
		buttonSelector.play("default");
		add(buttonSelector);

		resourceID = 0;


		add(hudTimer);
		this.calculatePrices();
	}

	@Override
	public void update(){
		this.calculatePrices();
		super.update();
		if(GBase.input.justReleased(GInput.ESC)){
			System.exit(0);
		}
		// if Q is pressed, exit store
		if(GBase.input.justReleased(GInput.Q) || hudTimer.isFinished() ){
			GBase.stateManager.passBack("Information");
			GBase.stateManager.popState();
		}		

		/*
		 * Here is the logic to handle selecting each button. Features include discrete movement between buy and sell
		 * and returning to a previous button away form the "leave" button 
		 */
		if(GBase.input.justPressed(GInput.UpArrow)){
			if (buttonSelector.y == 328 && selectorIsOnBuy == true){
				//go back to BUY crystite from "leave" button
				buttonSelector.x = 177;
				buttonSelector.y = 266;
				resourceID = 3;
			}
			else if (buttonSelector.y == 328 && selectorIsOnBuy == false){
				// go back to SELL crystite from "leave" button
				buttonSelector.x = 369;
				buttonSelector.y = 266;
				resourceID = 3;
			}
			else if (buttonSelector.y > 119) {
				buttonSelector.y -= 49;
				resourceID --;
			}
		}

		if(GBase.input.justPressed(GInput.DownArrow)){
			if (buttonSelector.y < 266){
				buttonSelector.y += 49;
				resourceID ++;
			}
			else if (buttonSelector.y == 266){
				// go to "leave" button from buy or sell 
				buttonSelector.x = 273;
				buttonSelector.y = 328;
				resourceID = -1;
			}
		}
		if(GBase.input.justPressed(GInput.LeftArrow)){
			// move to BUY
			if (selectorIsOnBuy == false && buttonSelector.y != 328){
				buttonSelector.x -= 192;
				selectorIsOnBuy = true;
			}
			else if (buttonSelector.y == 328){
				// currently on leave, go to BUY
				buttonSelector.x = 177;
				buttonSelector.y = 266;
				selectorIsOnBuy = true;
				resourceID = 4;
			}
		}
		if(GBase.input.justPressed(GInput.RightArrow)){
			//move to SELL
			if (selectorIsOnBuy == true && buttonSelector.y != 328){
				buttonSelector.x += 192;
				selectorIsOnBuy = false;
			}
			else if (buttonSelector.y == 328){
				// currently on leave, go to sell
				buttonSelector.x = 369;
				buttonSelector.y = 266;
				selectorIsOnBuy = false;
				resourceID = 4;
			}
		}

		if(GBase.input.justPressed(GInput.X)){
			switch(resourceID){
			case -1:
				GBase.stateManager.passBack("Information");
				GBase.stateManager.popState();
				break;
			case 0:
				if(selectorIsOnBuy) buyResource(resourceID);
				else sellResource(resourceID);
				break;
			case 1:
				if(selectorIsOnBuy) buyResource(resourceID);
				else sellResource(resourceID);
				break;
			case 2:
				if(selectorIsOnBuy) buyResource(resourceID);
				else sellResource(resourceID);
				break;
			case 3:
				if(selectorIsOnBuy) buyResource(resourceID);
				else sellResource(resourceID);
				break;

			}
		}
		hudTimer.updateResourcePanel(player.money, player.resources);
	}

	@Override
	public void render(Graphics2D g){
		super.render(g);
		g.setColor(Color.white);
		
		//Store resources
		g.drawString(""+storeResources[0], 43, 143);
		g.drawString(""+storeResources[1], 43, 189);
		g.drawString(""+storeResources[2], 43, 237);
		g.drawString(""+storeResources[3], 43, 285);
		
		//player buying prices ( price to purchase resource)
		g.drawString(""+buyingPrices[0], 110, 143);
		g.drawString(""+buyingPrices[1], 110, 189);
		g.drawString(""+buyingPrices[2], 110, 237);
		g.drawString(""+buyingPrices[3], 110, 285);
		
		//Player resources
		g.drawString(""+player.resources[0], 464, 143);
		g.drawString(""+player.resources[1], 464, 189);
		g.drawString(""+player.resources[2], 464, 237);
		g.drawString(""+player.resources[3], 464, 285);
		
		//player selling prices ( profit from selling)
		g.drawString(""+sellingPrices[0], 530, 143);
		g.drawString(""+sellingPrices[1], 530, 189);
		g.drawString(""+sellingPrices[2], 530, 237);
		g.drawString(""+sellingPrices[3], 530, 285);
		
		//player money
		g.drawString(""+player.money, 333, 425);
	}


	/** All adders are belong to Store
	 * ALL ADDERS ARE FROM PERSPECTIVE OF PLAYER
	 Buying resources/mule checks for sufficient funds
	Buying resources/mule deducts funds from player
	Buying resources adds to storehouse
	Selling resources deducts resource from storehouse
	Selling adds money to player
	Player cannot sell more resources than they have
	Players can buy mule (one of three types)
	 */

	/**
	 * Buy resource
	 */
	public void buyResource(int resourceNumber){
		//Strings to print to screen to help us track buying in game
		String str = "";
		if(resourceNumber==0)
			str="food";
		if(resourceNumber==1)
			str="energy";
		if(resourceNumber==2)
			str="smithore";
		if(resourceNumber==3)
			str="crysthite";

		// rN= 0 food, 1 energy, 2 ore, 3 crystite, 4 mule
		if (player.money >= buyingPrices[resourceNumber] && storeResources[resourceNumber] > 0){
			storeResources[resourceNumber] --;
			player.resources[resourceNumber] ++;
			player.money -= buyingPrices[resourceNumber];
			GBase.log("Bought some " + str);
		}
		else if (player.money < buyingPrices[resourceNumber])
			GBase.log("You do not have enough money to purchase this item!");
		else if (storeResources[resourceNumber] <= 0)
			GBase.log("There is no more " + str +"!");
		calculatePrices();
	}

	public void sellResource(int resourceNumber){
		//Strings to print to screen to help us track selling in game
		String str = "";
		if(resourceNumber==0)
			str="food";
		if(resourceNumber==1)
			str="energy";
		if(resourceNumber==2)
			str="smithore";
		if(resourceNumber==3)
			str="crysthite";
		// rN= 0 food, 1 energy, 2 ore, 3 crystite, 4 mule
		if (player.resources[resourceNumber] > 0 ){
			storeResources[resourceNumber] ++;
			player.resources[resourceNumber] --;
			player.money += sellingPrices[resourceNumber];
			//GBase.log("Sold some " + str);
		}
		//else
			//GBase.log("You cannot sell more resources than you have!");
		calculatePrices();
	}
	/**
	 * *Resolved* Issues: you can not purchase mules from store. That is handled in town.
	 * No logic for button selector movement.
	 * no separate array for prices to buy and sell -> two different prices.
	 * Selling in bulk of 50 interferes with the resources gained from tiles which are on the order of 0-5.
	 * 
	 * Ideas used: Strings to print buying and selling
	 */

	/**
	 * Changing resource prices based on scarcity of resources for later phases of development
	 * The store will buy resources off of your hands in a cube root function
	 * And the store will sell resources to you based on an inverse cube root function
	 * @param
	 */
	public void calculatePrices(){
		//
		buyingPrices[0] = (int)(95 - ((storeResources[0]/45.0)>1? 1 : storeResources[0]/45.0)*94);
		sellingPrices[0] = (int)(5 + ((1-(storeResources[0]/30.0))<0? 0 : (1-(storeResources[0]/30.0)))*60);
		
		buyingPrices[1] = (int)(140 - ((storeResources[1]/45.0)>1? 1 : storeResources[1]/45.0)*140);
		sellingPrices[1] = (int)(5 + ((1-(storeResources[1]/30.0))<0? 0 : (1-(storeResources[1]/30.0)))*90);
		
		buyingPrices[2] = (int)(130 - ((storeResources[2]/45.0)>1? 1 : storeResources[2]/45.0)*130);
		sellingPrices[2] = (int)(5 + ((1-(storeResources[2]/30.0))<0? 0 : (1-(storeResources[2]/30.0)))*80);
		
		buyingPrices[3] = (int)(400 - ((storeResources[3]/45.0)>1? 1 : storeResources[3]/45.0)*400);
		sellingPrices[3] = (int)(5 + ((1-(storeResources[3]/30.0))<0? 0 : (1-(storeResources[3]/30.0)))*300);
		
		/*for(int i=0; i<storeResources.length; i++){
			if(buyingPrices[i]<=0)
				buyingPrices[i]=10;
			if(sellingPrices[i]<=0)
				sellingPrices[i]=5;
		}*/
		
	}

}
