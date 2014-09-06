package mule;

import gears.GSprite;

import java.awt.Color;
import java.awt.Graphics2D;

public class HUD_ResourcePanel extends GSprite {
	private int[] resources;
	public HUD_ResourcePanel(double x, double y) {
		super(x, y, AssetManager.hud_resource_panel);
		resources = new int[]{18, 22, 45, 21, 1578};
	}
	
	@Override
	public void render(Graphics2D g){
		super.render(g);
		//Drawstring the resource pool.
		g.setColor(Color.black);
		g.drawString(""+resources[4], (int)x+3, (int)y+32);
		g.drawString(""+resources[0], (int)x+37, (int)y+32);
		g.drawString(""+resources[1], (int)x+56, (int)y+32);
		g.drawString(""+resources[2], (int)x+75, (int)y+32);
		g.drawString(""+resources[3], (int)x+94, (int)y+32);
	}
	
	public void updateResources(int money, int[] resources){
		this.resources[0] = resources[0];
		this.resources[1] = resources[1];
		this.resources[2] = resources[2];
		this.resources[3] = resources[3];
		this.resources[4] = money;
	}

}
