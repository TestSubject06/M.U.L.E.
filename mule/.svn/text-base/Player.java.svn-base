/**
 * 
 * 
 * @date created: 10/18/2013
 * @version Milestone 6
 * 
 */

package mule;
import gears.GBase;
import gears.GInput;
import gears.GPoint;
import gears.GSprite;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 * Contains logic for the user's player
 * @author Garrett
 *
 */
public class Player extends GSprite {

	//0:food 1:energy 2:ore 3:Chrystite
	public int[] resources = new int[4]; // each player has resources. Starts at 0,0,0,0
	public int money;
	public int landCount;
	private int raceID;
	public int color;
	public int muleType;	
	public GPoint mulePos;
	public int landGrantRemaining;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param raceImages
	 * @param raceIDInput
	 * @param colorID
	 */
	public Player(double x, double y, Image raceImages, int raceIDInput, int colorID){
		super(x, y, raceImages);
		
		// raceID indicates the row that the images lies on in the raceImage matrix
		// 5 is the length of the row (or number of pictures in each row). This is used to select a row from the matrix
		this.raceID = raceIDInput*5;
		muleType = 0; //no mule
		landGrantRemaining = 0;
		try{
		GBase.watch("Position: ", "pos", this);
		} 
		catch(NullPointerException n){}
		
		//0:food 1:energy 2:ore 3:Chrystite
		resources[0]=10;
		resources[1]=15;
		resources[2]=20;
		resources[3]=25;
		money=1000;
		landCount=0;
		this.color = colorID;
		
		//If race is human
		if(raceID==7){
			money=600;
		}
		//If race is flapper	
		if(raceID==5){
			money=1600;
		}

		//left walk = frame 0, 1
		//up = frame 2, 4
		//down = frame 3, 4
		//idle = frame 4
		// images currently set to 71 x 96 pixel height
		this.loadGraphic(raceImages, true, 71, 96);

		this.addAnimation("walk", new int[]{0+ raceID, 1+ raceID, 0+ raceID, 1+ raceID}, 7, false);
		this.addAnimation("up",   new int[]{2+ raceID, 4+ raceID, 2+ raceID, 4+ raceID}, 7, false);
		this.addAnimation("down", new int[]{3+ raceID, 4+ raceID, 3+ raceID, 4+ raceID}, 7, false);
		this.addAnimation("idle", new int[]{4+ raceID}, 0, false);

		this.play("idle");

	}
	
	/*
	 * Sets the position of the player
	 * @param int x, int y
	 */
	public void setPosition(int px, int py){
	 	x=px;
		y=py;
	}

	@Override
	public void update(){
		super.update();
		if(GBase.input.isDown(GInput.DownArrow)){
			// limited to 96*4
			y = y+3;
			play("down");
		}
		if(GBase.input.isDown(GInput.UpArrow)){
			y = y-3;
			play("up");
		}
		if(GBase.input.isDown(GInput.RightArrow)){
			// limited to 71*8
			x = x + 3;
			play("walk");
			orientation = FLIP_HORZ;
		}
		if(GBase.input.isDown(GInput.LeftArrow)){
			x = x - 3;
			play("walk");
			orientation = 0;
		}

		//GBase.log("x: " + x + " y: " + y);
	}

	@Override
	public void render(Graphics2D g){
		super.render(g);

	}

}

