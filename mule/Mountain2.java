package mule;

import gears.GTileMap;

/**
 * One of three types of mountain map tiles
 * @author Zack
 *
 */
public class Mountain2 extends MuleTile {

	/**
	 * Constructor
	 * @param tilemap
	 * @param index
	 * @param width
	 * @param height
	 * @param visible
	 * @param collision
	 */
	public Mountain2(GTileMap tilemap, int index, int width, int height,
			boolean visible, boolean collision) {
		super(tilemap, index, width, height, visible, collision);
		// TODO Auto-generated constructor stub
		//Order: Food, Energy, Ore, Crystite
		production = new int[]{1, 1, 3, 0};
	}

}
