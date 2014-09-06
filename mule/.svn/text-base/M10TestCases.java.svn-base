package mule;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author Jory Folker, Zach Tarvit, Garret Mallory, Daniel Kuo, Roxanne Jijina
 *
 */
public class M10TestCases {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Testing turnLength() in GameState.java
	 * @author Jory Folker
	 */
	@Test
	public void jory_turnLength(){
		GameState state = new GameState(50.0, 30.0, 5.0, 0, new int[]{0}, new int[]{0});
		Player player = new Player(0, 0, null, 0, 0);
		player.resources[0]=0; //give player no food
		//Player should always get "no time" (5sec) if no food
		for(int i=1; i<=12; i++)
			assertTrue((state.turnLength(player, i))==5.0);
		
		player.resources[0]=2;
		assertTrue((state.turnLength(player, 1))==30.0);
		assertTrue((state.turnLength(player, 4))==30.0);
		assertTrue((state.turnLength(player, 8))==30.0);
		assertTrue((state.turnLength(player, 12))==30.0);
		
		player.resources[0]=10;
		assertTrue((state.turnLength(player, 1))==50.0);
		assertTrue((state.turnLength(player, 4))==50.0);
		assertTrue((state.turnLength(player, 8))==50.0);
		assertTrue((state.turnLength(player, 12))==50.0);
	}
	
	
	
	/**
	 * Testing sellResouce() in StoreState.java
	 * @author Roxanne
	 */
	@Test
	public void Roxanne_SellResource(){
		Player player = new Player(10, 10, AssetManager.playerSprites, 0, 0);
		int money = player.money;
//		int food = player.resources[0];
//		int energy = player.resources[1];
//		int ore = player.resources[2];
//		int crystite = player.resources[3];
		HUD_Timer timer = new HUD_Timer(0, 0, 60, 0, 0);
		StoreState store = new StoreState(player, timer);
		StoreState.storeResources = new int[4];
		StoreState.sellingPrices = new int[4];
		store.buyingPrices = new int[4];
		player.resources[0] = 1;
		player.resources[1] = 1;
		player.resources[2] = 1;
		player.resources[3] = 2;		
		for(int i=0; i<4; i++){
			StoreState.storeResources[i]=8;
		}
		for(int i=0; i<4; i++){
			StoreState.sellingPrices[i]=30;
		}
		for(int i=0; i<StoreState.storeResources.length; i++){
			assertEquals(StoreState.storeResources[i], 8);
		}

		int price = StoreState.sellingPrices[0];
		store.sellResource(0); //sell food
		for(int i=0; i<StoreState.storeResources.length; i++){
			if(i==0){
				assertEquals(StoreState.storeResources[i],9);
			}else{
				assertEquals(StoreState.storeResources[i],8);
			}
		}
		//player has no food left
		assertEquals(player.resources[0],0);
		assertEquals(player.money, money += price);

		
		//try to sell food again
		store.sellResource(0);
		assertEquals(player.resources[0],0);
		assertEquals(StoreState.storeResources[0],9);
		assertEquals(player.money, money);
				
		store.sellResource(1); //sell energy
		for(int i=0; i<StoreState.storeResources.length; i++){
			if(i==0 || i==1){
				assertEquals(StoreState.storeResources[i],9);
			}else{
				assertEquals(StoreState.storeResources[i],8);
			}
		}
		store.sellResource(2); //sell ore
		for(int i=0; i<StoreState.storeResources.length; i++){
			if(i==3){
				assertEquals(StoreState.storeResources[i],8);
			}else{
				//System.out.println(i+" " +StoreState.storeResources[i]);
				assertEquals(StoreState.storeResources[i],9);

			}
		}
		store.sellResource(3); //sell crystite
		for(int i=0; i<StoreState.storeResources.length; i++){				
				assertEquals(StoreState.storeResources[i],9);
		}
		
	}// End Rox's method
	
	
	@Test
	public void Garrett_addArrays(){
		int [] a1 = {1};
		int [] a2 = {2};
		
		int [] a3 = {2, 2};
		int [] a4 = {3, 3};
		
		int [] a5 = {1, 2, 3, 4, 5, 6};
		int [] a6 = {1, 2, 3, 4, 5, 6};
		
		
		Map aMap = new Map();
		int[] ans1 = aMap.addArrays(a1, a2);
		assertEquals(ans1.length, 1);
		assertEquals(ans1[0], 3);
		
		int[] ans2 = aMap.addArrays(a3, a4);
		assertEquals(ans2.length, 2);
		assertEquals(ans2[0], 5);
		assertEquals(ans2[1], 5);
		
		int[] ans3 = aMap.addArrays(a5, a6);
		assertEquals(ans3.length, 6);
		assertEquals(ans3[0], 2);
		assertEquals(ans3[1], 4);
		assertEquals(ans3[2], 6);
		assertEquals(ans3[3], 8);
		assertEquals(ans3[4], 10);
		assertEquals(ans3[5], 12);
		
		int[] ans4 = aMap.addArrays(a3, a4);
		int[] ans5 = aMap.addArrays(a4, a3);
		for (int i = 0; i< ans4.length; i++)
			assertEquals(ans4[i], ans5[i]);
	}// end Garrett's method


	@Test
	public void Zachary_Production(){
		Map map =  new Map();
		map.loadMap("5,5,1,5,4,5,3,5,5\n"
				  + "5,1,5,5,4,5,5,5,3\n"
				  + "3,5,5,5,6,5,5,5,1\n"
				  + "5,2,5,5,4,5,2,5,5\n"
				  + "5,5,2,5,4,5,5,5,2", new ImageIcon(AssetManager.class.getResource("../assets/Mule_Tiles.png")).getImage(), 71, 96, 1, 1);
		assertEquals(map.calcProduction(), new int[4][4]);
		
		//Check across player 1
		
		map.muleTiles[0].owner = 0;
		map.muleTiles[0].mule = 0;
		assertEquals(map.calcProduction()[0][0], 2);
		
		map.muleTiles[0].owner = 0;
		map.muleTiles[0].mule = 1;
		assertEquals(map.calcProduction()[0][1], 3);
		
		map.muleTiles[0].owner = 0;
		map.muleTiles[0].mule = 2;
		assertEquals(map.calcProduction()[0][2], 1);
		
		map.muleTiles[0].owner = 0;
		map.muleTiles[0].mule = 3;
		assertEquals(map.calcProduction()[0][3], map.muleTiles[0].production[3]);
		
		map.muleTiles[0].owner = 0;
		map.muleTiles[0].mule = 0;
		map.muleTiles[1].owner = 0;
		map.muleTiles[1].mule = 1;
		map.muleTiles[3].owner = 0;
		map.muleTiles[3].mule = 2;
		map.muleTiles[5].owner = 0;
		map.muleTiles[5].mule = 3;
		assertArrayEquals(map.calcProduction()[0], new int[]{2, 3, 1, map.muleTiles[5].production[3]});
		
		//Check across player 2
		map.muleTiles[0].owner = 1;
		map.muleTiles[0].mule = 0;
		assertEquals(map.calcProduction()[1][0], 2);
		
		map.muleTiles[0].owner = 1;
		map.muleTiles[0].mule = 1;
		assertEquals(map.calcProduction()[1][1], 3);
		
		map.muleTiles[0].owner = 1;
		map.muleTiles[0].mule = 2;
		assertEquals(map.calcProduction()[1][2], 1);
		
		map.muleTiles[0].owner = 1;
		map.muleTiles[0].mule = 3;
		assertEquals(map.calcProduction()[1][3], map.muleTiles[0].production[3]);
		
		map.muleTiles[0].owner = 1;
		map.muleTiles[0].mule = 0;
		map.muleTiles[1].owner = 1;
		map.muleTiles[1].mule = 1;
		map.muleTiles[3].owner = 1;
		map.muleTiles[3].mule = 2;
		map.muleTiles[5].owner = 1;
		map.muleTiles[5].mule = 3;
		assertArrayEquals(map.calcProduction()[1], new int[]{2, 3, 1, map.muleTiles[5].production[3]});
		
		//Player 3
		map.muleTiles[0].owner = 2;
		map.muleTiles[0].mule = 0;
		assertEquals(map.calcProduction()[2][0], 2);
		
		map.muleTiles[0].owner = 2;
		map.muleTiles[0].mule = 1;
		assertEquals(map.calcProduction()[2][1], 3);
		
		map.muleTiles[0].owner = 2;
		map.muleTiles[0].mule = 2;
		assertEquals(map.calcProduction()[2][2], 1);
		
		map.muleTiles[0].owner = 2;
		map.muleTiles[0].mule = 3;
		assertEquals(map.calcProduction()[2][3], map.muleTiles[0].production[3]);
		
		map.muleTiles[0].owner = 2;
		map.muleTiles[0].mule = 0;
		map.muleTiles[1].owner = 2;
		map.muleTiles[1].mule = 1;
		map.muleTiles[3].owner = 2;
		map.muleTiles[3].mule = 2;
		map.muleTiles[5].owner = 2;
		map.muleTiles[5].mule = 3;
		assertArrayEquals(map.calcProduction()[2], new int[]{2, 3, 1, map.muleTiles[5].production[3]});
		
		//Player 4
		map.muleTiles[0].owner = 3;
		map.muleTiles[0].mule = 0;
		assertEquals(map.calcProduction()[3][0], 2);
		
		map.muleTiles[0].owner = 3;
		map.muleTiles[0].mule = 1;
		assertEquals(map.calcProduction()[3][1], 3);
		
		map.muleTiles[0].owner = 3;
		map.muleTiles[0].mule = 2;
		assertEquals(map.calcProduction()[3][2], 1);
		
		map.muleTiles[0].owner = 3;
		map.muleTiles[0].mule = 3;
		assertEquals(map.calcProduction()[3][3], map.muleTiles[0].production[3]);
		
		map.muleTiles[0].owner = 3;
		map.muleTiles[0].mule = 0;
		map.muleTiles[1].owner = 3;
		map.muleTiles[1].mule = 1;
		map.muleTiles[3].owner = 3;
		map.muleTiles[3].mule = 2;
		map.muleTiles[5].owner = 3;
		map.muleTiles[5].mule = 3;
		assertArrayEquals(map.calcProduction()[3], new int[]{2, 3, 1, map.muleTiles[5].production[3]});
		
	}
	
}
