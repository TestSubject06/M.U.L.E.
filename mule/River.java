package mule;

import gears.GTileMap;

/**
* River contains River resource values
* @author Zack
*/
public class River extends MuleTile {
	
	/**
	 * Creates a new River
	 * @param tilemap
	 * @param index
	 * @param width
	 * @param height
	 * @param visible
	 * @param collision
	 */
	public River(GTileMap tilemap, int index, int width, int height, boolean visible, boolean collision) {
		super(tilemap, index, width, height, visible, collision);
		//Order: Food, Energy, Ore, Crystite
		production = new int[]{4, 2, 0, 0};
	}
	
}
