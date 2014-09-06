package mule;

import gears.GTileMap;

/**
 * Contains logic of the plain on the map
 * @author Zack
 *
 */
public class Plains extends MuleTile {

	/**
	 * Constructor
	 * @param tilemap
	 * @param index
	 * @param width
	 * @param height
	 * @param visible
	 * @param collision
	 */
	public Plains(GTileMap tilemap, int index, int width, int height,
			boolean visible, boolean collision) {
		super(tilemap, index, width, height, visible, collision);
		//Order: Food, Energy, Ore, Crystite
		production = new int[]{2, 3, 1, (int)Math.round(Math.random())};
	}

}
