package mule;

import gears.GTileMap;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 * Contains logic to create a map.
 * @author Zack
 *
 */
public class Map extends GTileMap {

	MuleTile[] muleTiles;
	/**
	 * Constructor. Extends entirely from GTileMap
	 */
	public Map() {
		super();
	}

	@Override
	public void loadMap(String mapData, Image tileGraphic, int tileWidth, int tileHeight, int drawIndex, int collideIndex){
		super.loadMap(mapData, tileGraphic, tileWidth, tileHeight, drawIndex, collideIndex);
		muleTiles = new MuleTile[totalTiles];
		for(int i = 0; i < muleTiles.length; i++){
			//We need to make specific tiles based on the tilemap
			switch(_data.get(i)){
			case 1:
				muleTiles[i] = new Mountain1(this, i, 71, 96, true, false);
				break;
			case 2:
				muleTiles[i] = new Mountain2(this, i, 71, 96, true, false);
				break;
			case 3:
				muleTiles[i] = new Mountain3(this, i, 71, 96, true, false);
				break;
			case 4:
				muleTiles[i] = new River(this, i, 71, 96, true, false);
				break;
			case 5:
				muleTiles[i] = new Plains(this, i, 71, 96, true, false);
				break;
			default:
				muleTiles[i] = new MuleTile(this, i, 71, 96, false, false);
				break;	
			}
		}
	}
	
	public void loadFromHugeArray(int[] hugeArray, Image tileGraphic, int tileWidth, int tileHeight, int drawIndex, int collideIndex){
		//the data is as follows: 0, 3, 6, 9, 12...
		String mapData = "";
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 9; j++){
				mapData += hugeArray[((i*(9*3))+(j*3))] + ",";
			}
			//Delete that last ',' and replace it with a '\n'
			mapData = mapData.substring(0, mapData.length()-1);
			mapData += "\n";
		}
		//Delete that last \n
		mapData = mapData.substring(0, mapData.length()-1);
		super.loadMap(mapData, tileGraphic, tileWidth, tileHeight, drawIndex, collideIndex);
		muleTiles = new MuleTile[totalTiles];
		int[] playerColors = new int[]{SaveHelper.p1C, SaveHelper.p2C, SaveHelper.p3C, SaveHelper.p4C};
		for(int i = 0; i < muleTiles.length; i++){
			//We need to make specific tiles based on the tilemap
			switch(_data.get(i)){
				case 1:
					muleTiles[i] = new Mountain1(this, i, 71, 96, true, false);
					break;
				case 2:
					muleTiles[i] = new Mountain2(this, i, 71, 96, true, false);
					break;
				case 3:
					muleTiles[i] = new Mountain3(this, i, 71, 96, true, false);
					break;
				case 4:
					muleTiles[i] = new River(this, i, 71, 96, true, false);
					break;
				case 5:
					muleTiles[i] = new Plains(this, i, 71, 96, true, false);
					break;
				default:
					muleTiles[i] = new MuleTile(this, i, 71, 96, false, false);
					break;	
			}
			//tile mules follow the format 1, 4, 7, 10...
			//tile owners follow the format 2, 5, 8, 11...
			if(hugeArray[i*3+2] > -1){
				muleTiles[i].setOwner(hugeArray[i*3+2], playerColors[hugeArray[i*3+2]]);
				muleTiles[i].setMule(hugeArray[i*3+1]);
			}
		}
	}

	/**
	 * Calculates production for each tile for each player
	 * @return int[][]
	 */
	public int[][] calcProduction(){
		//Two dimensional array - [player#][resource#]
		int[][] ret = new int[4][4];
		for(int i = 0; i<muleTiles.length; i++){
			if(muleTiles[i].owner != -1){
				ret[muleTiles[i].owner] = addArrays(muleTiles[i].calcProduction(), ret[muleTiles[i].owner]);
			}
		}
		return ret;
	}
	
	public int[] updateSaveArray(){
		int[] ret = new int[(5*9*3)];
		for(int i = 0; i<muleTiles.length; i++){
			ret[(i*3)] = _data.get(i);
			ret[(i*3)+1] = muleTiles[i].mule;
			ret[(i*3)+2] = muleTiles[i].owner;
		}
		return ret;
	}

	/**
	 * Requires the arrays be of the same length.
	 * @param a1 the first array
	 * @param a2 the second array
	 * @return returns a1 + a2 as the sum of the elements
	 */
	public int[] addArrays(int[] a1, int[] a2){
		int[] a = new int[a1.length];
		for(int i = 0; i<a1.length; i++){
			a[i] = a1[i] + a2[i];
		}
		return a;
	}

	@Override
	public void render(Graphics2D g){
		super.render(g);
		for(int i = 0; i<muleTiles.length; i++){
			muleTiles[i].render(g);
		}
	}
	
	public MuleTile getTileAtCoords(double x, double y){
		int tileX = (int)Math.floor(x/71);
		int tileY = (int)Math.floor(y/96);
		return muleTiles[tileY*9 + tileX];
	}

}
