package edu.srini.board;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShortPathFinderTest {

	ShortPathFinder spf;
	
	enum Board {
				
		CASE1(new String[]{"1", "E"}, "Missing S or E"),
		CASE2(new String[]{"S","1"}, "Missing S or E"),
		CASE3(new String[]{"S","-1","E"}, "1"),
		CASE4(new String[]{"S","1","E"}, "1"),
		CASE5(new String[]{"S","4","3","2","1","1","E"}, "1"),
		CASE6(new String[]{"S","4","3","2","5","1","2","E","4","-2"}, "3"),
		CASE7("S 1 R 4 3 4 3 -5 2 -4 E".split(" "), "3"),
		CASE8("S 1 1 4 3 4 3 -5 2 -4 E".split(" "), "Not possible to reach E from S"),
		CASE9("S R R R R R R -6 -5 -4 -3 -2 1 E".split(" "), "3")
		;
		String[] s;
		String result;
		Board(String[] str, String result){
			s = str;
			this.result = result;
		}
		String getResult(){
			return result;
		}
		String[] getBoard(){
			return s;
		}
		
	};
	
	@Before
	public void prepare(){
		spf = new ShortPathFinder();
	}
	@Test
	public void testWithMissingS(){
		String result = spf.findShortPathS2E(Board.CASE1.getBoard());
		Assert.assertEquals(Board.CASE1.getResult(), result);
	}
	
	@Test
	public void testWithMissingE(){
		String result = spf.findShortPathS2E(Board.CASE2.getBoard());
		Assert.assertEquals(Board.CASE2.getResult(), result);
	}
	
	@Test
	public void testWithSimpleInfinteLoop(){
		String result = spf.findShortPathS2E(Board.CASE3.getBoard());
		Assert.assertEquals(Board.CASE3.getResult(), result);
	}
	
	@Test
	public void testWithReachInOneStep(){
		String result = spf.findShortPathS2E(Board.CASE4.getBoard());
		Assert.assertEquals(Board.CASE4.getResult(), result);
	}
	
	@Test
	public void testWithReachInOneStepFromManyPossiblities(){
		String result = spf.findShortPathS2E(Board.CASE5.getBoard());
		Assert.assertEquals(Board.CASE5.getResult(), result);
	}
	
	@Test
	public void testWithReachWithNegativeNumber(){
		String result = spf.findShortPathS2E(Board.CASE6.getBoard());
		Assert.assertEquals(Board.CASE6.getResult(), result);
	}
	@Test
	public void testWithSingleRAndMultipleInfiniteLoops(){
		String result = spf.findShortPathS2E(Board.CASE7.getBoard());
		Assert.assertEquals(Board.CASE7.getResult(), result);
	}
	@Test
	public void testWithNotPossibleToReach(){
		String result = spf.findShortPathS2E(Board.CASE8.getBoard());
		Assert.assertEquals(Board.CASE8.getResult(), result);
	}
	@Test
	public void testWithManyRsAndNegativeNumbers(){
		String result = spf.findShortPathS2E(Board.CASE9.getBoard());
		Assert.assertEquals(Board.CASE9.getResult(), result);
	}

}
