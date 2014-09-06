package mule;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DanielsJUnit {
	
	/**
	 * @author Daniel Kuo
	 */
	@Test
	public void TestCharacterFilter(){

		// letter a, b, c, d, e, f, g will be allowed through
		// this part deals with no shift pressed
		CharacterFilter cf = new CharacterFilter("abcdefgABC.12345/", false);
		
		// a is allowed
		assertEquals(cf.filterCharacter(97), "a");
		// A B C are allowed
		assertEquals(cf.filterCharacter(65), "A");
		assertEquals(cf.filterCharacter(66), "B");
		assertEquals(cf.filterCharacter(67), "C");
		// period '.' is allowed
		assertEquals(cf.filterCharacter(46), ".");
		// 12345 are all allowed
		assertEquals(cf.filterCharacter(49), "1");
		assertEquals(cf.filterCharacter(50), "2");
		assertEquals(cf.filterCharacter(51), "3");
		assertEquals(cf.filterCharacter(52), "4");
		assertEquals(cf.filterCharacter(53), "5");
		// "/" is allowed
		assertEquals(cf.filterCharacter(47), "/");
		
		// I is filtered
		assertEquals(cf.filterCharacter(111), "");
		// G is filtered
		assertEquals(cf.filterCharacter(107), "");
		// , is not allowed
		assertEquals(cf.filterCharacter(44), "");
		// and etc
		
	
		// character !?ABCD will be allowed through
		//CharacterFilter cf2 = new CharacterFilter("!?ABCD", true);
		
		
	}
	
	
}
