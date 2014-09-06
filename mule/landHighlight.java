package mule;

import java.awt.Image;

import gears.GSprite;

/**
 * Places a boarder around land that has been grant/purchased
 * @author Zack
 *
 */
public class landHighlight extends GSprite {

	/**
	 * Constructor 
	 * @param x
	 * @param y
	 */
	public landHighlight(double x, double y) {
		super(x, y, null);
		loadGraphic(AssetManager.landHighlights, false, 71, 96);
		
	}

}
