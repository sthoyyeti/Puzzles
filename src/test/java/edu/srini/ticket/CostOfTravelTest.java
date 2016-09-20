package edu.srini.ticket;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class CostOfTravelTest {
	CostOfTravelV2 cot;
	
	enum TravelCost { 
		CASE1(Arrays.asList(), 0), 
		CASE2(Arrays.asList(1), 2),
		CASE3(Arrays.asList(1,7,13,19,25,30), 12),
		CASE4(Arrays.asList(1,7,8,10,12,19,25,30), 15), 
		CASE5(Arrays.asList(1,3,5,7,9,11,13,15,17,19,21,23,25,27,29), 23),
		CASE6(Arrays.asList(1,3,6,9,12,15,18,21,24,27,30), 22),
		CASE7(Arrays.asList(1,4,8,12,16,20,24,28), 16),
		CASE8(Arrays.asList(1,5,10,15,20,25,30), 14),
		CASE9(Arrays.asList(1,2,3,4,5,6,7,15,16,17,18,25,26,27,28,29), 21),
		CASE10(Arrays.asList(1,2,3,4,5,6,7,11,15,16,17,18,25,26,27,28,29), 23),
		CASE11(Arrays.asList(1,2,3,4,5,6,7,11,12,15,16,17,18,25,26,27,28,29), 23);
		
		List<Integer> days;
		int cost;
		
		TravelCost(List<Integer> days, int cost){
			this.days = days;
			this.cost = cost;
		}
		List<Integer> getDays(){
			return days;
		}
		int getCost(){
			return cost;
		}
	}
	
	@Before
	public void prepare(){
		cot = new CostOfTravelV2();
	}
	@Test
	public void testTravelForEmptyDays(){
		int calculateCost = cot.calculateMonthlyTicketCost(TravelCost.CASE1.getDays());
		Assert.assertEquals(TravelCost.CASE1.getCost(), calculateCost);
	}

	@Test
	public void testTravelFor1Day(){
		int calculateCost = cot.calculateMonthlyTicketCost(TravelCost.CASE2.getDays());
		Assert.assertEquals(TravelCost.CASE2.getCost(), calculateCost);
	}
	@Test
	public void testTravelForWeekStartAndEnd(){
		int calculateCost = cot.calculateMonthlyTicketCost(TravelCost.CASE3.getDays());
		Assert.assertEquals(TravelCost.CASE3.getCost(), calculateCost);
	}
	@Test
	public void testTravelFor4DaysSpreadAcrossTwoWeeks(){
		int calculateCost = cot.calculateMonthlyTicketCost(TravelCost.CASE4.getDays());
		Assert.assertEquals(TravelCost.CASE4.getCost(), calculateCost);
	}
	@Test
	public void testTravelForAllOddDays(){
		int calculateCost = cot.calculateMonthlyTicketCost(TravelCost.CASE5.getDays());
		Assert.assertEquals(TravelCost.CASE5.getCost(), calculateCost);
	}
	@Test
	public void testTravelForMultipleOf3(){
		int calculateCost = cot.calculateMonthlyTicketCost(TravelCost.CASE6.getDays());
		Assert.assertEquals(TravelCost.CASE6.getCost(), calculateCost);
	}
	@Test
	public void testTravelForMultipleOf4(){
		int calculateCost = cot.calculateMonthlyTicketCost(TravelCost.CASE7.getDays());
		Assert.assertEquals(TravelCost.CASE7.getCost(), calculateCost);
	}
	@Test
	public void testTravelForMultipleOf5(){
		int calculateCost = cot.calculateMonthlyTicketCost(TravelCost.CASE8.getDays());
		Assert.assertEquals(TravelCost.CASE8.getCost(), calculateCost);
	}
	@Test
	public void testTravelForThreeWeeks(){
		int calculateCost = cot.calculateMonthlyTicketCost(TravelCost.CASE9.getDays());
		Assert.assertEquals(TravelCost.CASE9.getCost(), calculateCost);
	}
	@Test
	public void testTravelForThreeWeeksOneDay(){
		int calculateCost = cot.calculateMonthlyTicketCost(TravelCost.CASE10.getDays());
		Assert.assertEquals(TravelCost.CASE10.getCost(), calculateCost);
	}
	@Test
	public void testTravelForThreeWeeksTwoDays(){
		int calculateCost = cot.calculateMonthlyTicketCost(TravelCost.CASE11.getDays());
		Assert.assertEquals(TravelCost.CASE11.getCost(), calculateCost);
	}
}
