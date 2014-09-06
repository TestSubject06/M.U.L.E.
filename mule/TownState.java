package mule;

import gears.GBase;
import gears.GInput;
import gears.GSprite;
import gears.GState;

import java.awt.Graphics2D;
import java.util.Random;
/**
 * TownState contains the logic for the town.
 * It's a subclass of GState.
 * It can create the town state, increment or decrement the number of mules.
 * * It also allows the player to purchase a mule from the mule pen.
 * @author Daniel, Jory
 * @version M9
 * 
 */

public class TownState extends GState{
	
	private GSprite background;
	private MuleSprite[] mules;
	private Player player;
	// how do we keep track the amount of mule?
	private int amountOfMule;
	private int round;
	private Random ran;
	private int muleIndex = 0;
	
	// for the position of the mule in the mule pen
	// bottom left: x 504
	//              y 392
	// bottom right: x 562
	// 				 y 390
	private int nextMuleX = 500;
	private int nextMuleY = 500;
	private HUD_Timer hudTimer;
	
	// right now know that it will at least take in a Player object
	// Player will deal with player. 
	// also need to create amountOfMule Mules in the mule pen.
	/**
	 * Constructs the town state by taking in the player
	 * @param player
	 */
	public TownState(Player player, HUD_Timer hudTimer, int round){
		super();
		this.player = player;
		this.hudTimer = hudTimer;
		this.round = round;
		ran = new Random();
		passDraw = false;
	}
	
	/**
	 * Creates the town state graphically using GSprite
	 */
	@Override
	public void create(){
		super.create();
		background = new GSprite(0, 0, AssetManager.townInside);
		add(background);
		add(player);
		add(hudTimer);
		// center player
		player.x = 284;
		player.y = 192;
		
		//Build the mule stack
		//We want two stacks of mules
		//in this format:
		//|  3  |  |  4  |
		//|  1  |  |  2  |
		//Start low, build high.
		//This builds the stack, but they need to be added in reverse order.
		mules = new MuleSprite[18];
		for(int i = 17; i >= 0; i--){
			//X value is an i%2?right:left;
			//Y value is floor(i/10);
			mules[i] = new MuleSprite((i%2>0)?504:562, Math.floor(i/2)*15+300);
			GBase.log("Mule Added");
		}
		for(int i = 0; i < 18; i++){
			if(i < (18 - StoreState.numMules)){
				muleIndex = i+1;
				mules[i].exists = false;
			}
			add(mules[i]);
		}
		if(player.muleType>0){
			mules[0].exists = true;
			mules[0].setFollowTarget(player);
			mules[0].x = player.x-20;
			mules[0].y = player.y+player.height/2;
		}
	}
	
	/**
	 * Updates the town state
	 */
	public void update(){
		super.update();
		
		//If player walks out of town, return to map
		if(player.x + (player.width/2) < 0 || player.x + (player.width/2) > 640 || player.y+(player.height/2) < 0 || 
				player.y+(player.height/2) > 480){
			if(player.muleType > 0){
				GBase.stateManager.passBack(""+player.muleType);
			}else{
				GBase.stateManager.passBack("-1");
			}
			GBase.stateManager.popState();
		}
		
		//Abort Program
        if(GBase.input.justReleased(GInput.ESC)){
            System.exit(0);
        }
        
        //Assay office (land purchase)
        if(GBase.input.justPressed(GInput.X) && player.x + (player.width/2) > 23 && player.x +(player.width/2) < 140 && 
        		player.y+(player.height/2) > 313 && player.y +(player.height/2) < 430 && 
        		(player.money>=500||player.landGrantRemaining>0))
        	purchaseLand();
         
        //Gamble here
        if(GBase.input.justPressed(GInput.X) && player.x + (player.width/2) > 330 && player.x + (player.width/2) < 430 && 
        		player.y+(player.height/2) > 260 && player.y+(player.height/2) < 380){
        	
        	gamble();
        }
        //Enter store here
        if(GBase.input.justPressed(GInput.X) && player.x + (player.width/2) > 140 && player.x + (player.width/2) < 240 && player.y+(player.height/2) > 260 && player.y+(player.height/2) < 380){
        	GBase.stateManager.addState(new StoreState(player, hudTimer));
        }
        
        //Mule pen logic
        if(GBase.input.justPressed(GInput.X) && player.money >= 200 && player.x>24 && player.x<24+113 
				&& player.y>51 && player.y<51+123){//Chrystite mule
        	player.money-=200;
        	player.muleType = 4;
			mules[muleIndex].setFollowTarget(player);
        	GBase.log("Purchased Food Mule");
        	StoreState.numMules--;
        	
        }else if(GBase.input.justPressed(GInput.X) && player.money >= 175 && player.x>166 && player.x<166+113 
				&& player.y>51 && player.y<51+123){//Smithore mule
        	player.money-=175;
        	player.muleType = 3;
			mules[muleIndex].setFollowTarget(player);
        	GBase.log("Purchased Food Mule");
        	StoreState.numMules--;

		}else if(GBase.input.justPressed(GInput.X) && player.money >= 150 && player.x>360 && player.x<360+113 
				&& player.y>51 && player.y<51+123){//Energy mule
			player.money-=150;
			player.muleType = 2;
			mules[muleIndex].setFollowTarget(player);
        	GBase.log("Purchased Food Mule");
        	StoreState.numMules--;

		}else if(GBase.input.justPressed(GInput.X) && player.money >= 125 && player.x>504 && player.x<504+113 
				&& player.y>51 && player.y<51+123 && player.muleType == 0){//Food mule
			player.money-=125;
			player.muleType = 1;
			mules[muleIndex].setFollowTarget(player);
        	GBase.log("Purchased Food Mule");
        	StoreState.numMules--;
		}	
        
        
        //If time runs out...
        if(hudTimer.isFinished()){
        	GBase.stateManager.popState();
        }
        
        //Exit turn voluntarily
        if(GBase.input.justPressed(GInput.E)){
        	hudTimer.setRemainingTime(1.0);
        }
        
        hudTimer.updateResourcePanel(player.money, player.resources);
	}
	
	/**
	 * Rendering the town state
	 */
	public void render(Graphics2D g){
		super.render(g);
	}
	
	/**
	 * Gamble money, timer automatically runs out when you do so
	 */
	public void gamble(){
		GBase.log("money before gamble: " + player.money);
		
		int timeBonus=0;
		if(hudTimer.getRemainingTime()>0)
			timeBonus=50;
		if(hudTimer.getRemainingTime()>12)
			timeBonus=100;
		if(hudTimer.getRemainingTime()>25)
			timeBonus=150;
		if(hudTimer.getRemainingTime()>37)
			timeBonus=200;
		
		int roundBonus=(50*(round/4))+50;
		int moneyBonus=roundBonus+((int)(Math.random()*(timeBonus+1)));
		
		if(moneyBonus>250)
			moneyBonus=250;
		
		player.money+=moneyBonus;
		
		GBase.log("money after gamble: " + player.money);
		GBase.log("Money awarded from gambling: $" + moneyBonus);
		
		GBase.stateManager.popState();
		hudTimer.setRemainingTime(1.0);
	}
	
	/**
	 * Brings up the land selector to allow a player to purchase land at the beginning of their turn
	 */
	private void purchaseLand(){
		GBase.stateManager.passBack("land");
		GBase.stateManager.popState();
	}
	
	/**
	 * Increments the number of mules 
	 */
	public void addMule(){
		amountOfMule++;
		// toDo: animation
	}
	
	/**
	 * Decrements the number of mules
	 */
	public void deductMule(){
		amountOfMule--;
		// toDo: animation
		
	}
}
