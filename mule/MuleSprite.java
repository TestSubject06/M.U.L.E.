package mule;

import gears.GObject;
import gears.GPoint;
import gears.GSprite;

public class MuleSprite extends GSprite {
	private GObject target;
	private GPoint acceleration;
	private GPoint velocity;
	private GPoint drag;
	private GPoint maxVelocity;
	public boolean isInstalled;
	
	public MuleSprite(double x, double y){
		super(x, y, null);
		//Load mule sprite
		loadGraphic(AssetManager.mule, true, 55, 35);
		//Add anims
		addAnimation("default", new int[]{0}, 0, false);
		addAnimation("die", new int[]{1, 2, 3, 4}, 15, false);
		acceleration = new GPoint(0,0);
		velocity = new GPoint(0,0);
		drag = new GPoint(.9,.9);
		maxVelocity = new GPoint(3,3);
		isInstalled = false;
		play("default");
	}
	
	public void setFollowTarget(GObject target){
		this.target = target;
	}
	
	@Override
	public void update(){
		super.update();
		
		if(target != null){
			if(target.x+target.width/2 < x-25){
				acceleration.x = -0.3;
			}else if(target.x+target.width/2 > x+width+25){
				acceleration.x = 0.3;
			}else{
				acceleration.x = 0;
			}
			if(target.y+target.height/2 < y-25){
				acceleration.y = -0.3;
			}else if(target.y+target.height/2 > y+height+25){
				acceleration.y = 0.3;
			}else{
				acceleration.y = 0;
			}
		}
		if(curAnim.name != "die"){
			velocity.x *= drag.x;
			velocity.y *= drag.y;
			velocity.x += acceleration.x;
			velocity.y += acceleration.y;
			x += velocity.x;
			y += velocity.y;
		}else if(curAnim.name == "die"){
			if(finished){
				exists = false;
			}
		}
	}
}
