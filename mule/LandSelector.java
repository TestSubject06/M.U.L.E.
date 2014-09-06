package mule;

import java.awt.Image;

import gears.GSprite;
/**
 * 
 * @author Zack
 *
 */
public class LandSelector extends GSprite {

	/**
	 * Constructor 
	 * @param x
	 * @param y
	 * @param playerColor
	 */
	public LandSelector(double x, double y, int playerColor) {
		super(x, y, null);
		loadGraphic(AssetManager.landSelector, true, 71, 96);
		addAnimation("default", new int[]{playerColor, playerColor+7}, 1, true);
		play("default");
	}	
}
