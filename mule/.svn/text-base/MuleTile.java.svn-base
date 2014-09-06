package mule;

import gears.GSprite;
import gears.GTile;
import gears.GTileMap;

import java.awt.Graphics2D;

/**
 * 
 * Handels some logic for tiles on the map
 * @author Zack
 *
 */
public class MuleTile extends GTile {

	public int owner;
	public int mule;
	public int[] production;
	public landHighlight highlight;
	public GSprite icon;
	public boolean flicker;
	
	/**
	 * Constructor
	 * @param tilemap
	 * @param index
	 * @param width
	 * @param height
	 * @param visible
	 * @param collision
	 */
	public MuleTile(GTileMap tilemap, int index, int width, int height, boolean visible, boolean collision) {
		super(tilemap, index, width, height, visible, collision);
		owner = -1; //-1 means no one owns this tile
		mule = -1; //-1 means no mule exists on this tile
		production = new int[4];
		highlight = new landHighlight((index%9)*71,  (index/9)*96);
		highlight.visible = false;
		icon = new GSprite((index%9)*71,  (index/9)*96+80, null);
		icon.loadGraphic(AssetManager.tileMuleIcons, false, 16, 16);
		icon.exists = false;
	}

	/**
	 * 
	 * @return int[]
	 */
	public int[] calcProduction(){
		if(mule >= 0){
			int[] i = new int[4];
			i[mule] = production[mule];
			return i;
		}
		return new int[4];
	}
	
	@Override
	public void update(){
		super.update();
		if(icon.exists){
			icon.update();
		}
	}

	@Override
	public void render(Graphics2D g){
		if(highlight.visible)
			highlight.render(g);
		if(flicker)
			highlight.visible = !highlight.visible;
		if(icon.exists){
			icon.render(g);
		}
	}

	/**
	 * Allows a tile to be owned by a player
	 * @param owner
	 * @param color
	 */
	public void setOwner(int owner, int color){
		this.owner = owner;
		highlight.setCurIndex(color);
		highlight.visible = true;
	}
	
	public void setMule(int mule){
		this.mule = mule;
		icon.setCurIndex(mule);
		icon.exists = true;
	}

}
