package mule;

import gears.GBase;
import gears.GSprite;

import java.awt.Graphics2D;

/**
 * 
 * @author Zack
 *
 */
public class HUD_Timer extends GSprite {
	private double timeRemaining;
	private double initialTime;
	private boolean finished;
	private GSprite panelConnector;
	private HUD_ResourcePanel panel;
	private GSprite colorBacking;
	private GSprite raceBacking;
	public HUD_Timer(double x, double y, double timeRemaining, int raceID, int color) {
		super(x, y, null);
		loadGraphic(AssetManager.HUD_timer, true, 40, 40);
		setCurIndex(0);

		this.timeRemaining = timeRemaining;
		this.initialTime = timeRemaining;
		finished = false;
		
		panelConnector = new GSprite(x, y, null);
		panelConnector.loadGraphic(AssetManager.hud_timer_connector, true, 17, 36);
		
		panel = new HUD_ResourcePanel(x, y);
		
		colorBacking = new GSprite(x, y, null);
		colorBacking.loadGraphic(AssetManager.hud_backing_color, false, 40, 40);
		colorBacking.setCurIndex(color);
		
		raceBacking = new GSprite(x, y, null);
		raceBacking.loadGraphic(AssetManager.hud_backing_race, false, 40, 40);
		raceBacking.setCurIndex(raceID);
	}

	@Override
	public void update(){
		if(_curIndex == 24){
			setCurIndex(25);
			active = false;
		}
		super.update();
		if(!finished){
			timeRemaining -= GBase.elapsedSeconds;
			setCurIndex((int)Math.round((1-(timeRemaining/initialTime))*25));
		} if(_curIndex == 24){
			finished = true;
		}
		if(finished)
			GBase.log("Time's up!");
		
		panelConnector.update();
		panel.update();
		raceBacking.update();
		colorBacking.update();
	}
	
	@Override
	public void render(Graphics2D g){
		panelConnector.render(g);
		panel.render(g);
		colorBacking.render(g);
		raceBacking.render(g);
		super.render(g);
	}

	public void reset(double time){
		this.timeRemaining = time;
		this.initialTime = time;
		finished = false;
		active = true;
	}

	public void reset(){
		reset(initialTime);
	}

	public boolean isFinished(){
		return finished;
	}

	public double getRemainingTime(){
		return timeRemaining;
	}
	
	public void setRemainingTime(double time){
		timeRemaining = time;
	}
	
	public void setFinished(boolean b){
		finished=true;
	}
	
	public void setOrientation(int orientation){
		this.orientation = orientation;
		//Other things.
		panelConnector.setCurIndex(orientation);
		panelConnector.x = x + (23-((orientation & 0x1)*23));
		panelConnector.y = y + ((orientation & 0x2)>>1)*4;
		
		//ResourcePanel positioning: either x + width or x-panel.width.
		panel.x = x + (((orientation & 0x1)>0) ? -panel.width+1 : width-1);
		panel.y = y + ((orientation & 0x2)>>1)*4;
		
		//Re-adjust the profilings.
		colorBacking.x = x + (((orientation & 0x1)>0) ? 5 : 0);
		raceBacking.x = x + (((orientation & 0x1)>0) ? 5 : 0);
		colorBacking.y = y + (((orientation & 0x2)>0) ? 5 : 0);
		raceBacking.y = y + (((orientation & 0x2)>0) ? 5 : 0);
	}
	
	public void updateResourcePanel(int money, int[] resources){
		panel.updateResources(money, resources);
	}
}
