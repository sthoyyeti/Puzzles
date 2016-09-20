package edu.srini.ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author sri9cob
 * The cost calculation goes like following:
 * 1. For each index calculate the WEEK scope count
 * 2. get maximum of such week scope count and remove those days from list.
 * 3. if such count > 3 then increase cost by 7
 * 3.1 otherwise remove all days from list and increse cost by days * 2
 * 4. if total cost > 23 then go for monthly ticket. 
 *
 */
public class CostOfTravelV2 {
	List<Integer> daysInSequence;
	public static void main(String[] args) {

		List<Integer> days = new LinkedList<Integer>();
		//days.addAll(Arrays.asList(1, 2, 3, 5, 6, 7, 11, 15, 17));
		//days.addAll(Arrays.asList(1, 15, 17 ,19, 21));
		//days.addAll(Arrays.asList(1,7,8,10,12,19,25,30));
		// days.addAll(Arrays.asList(1, 2, 3, 5, 6, 7, 8,9,10,11,12,13,14, 15,
		// 17, 19, 23, 27, 29, 30)); 
		days.addAll(Arrays.asList(1,3,6,9,12,15,18,21,24,27,30));
		System.out.println(new CostOfTravelV2().calculateMonthlyTicketCost(days));
	}

	public int calculateMonthlyTicketCost(List<Integer> days) {
		daysInSequence = new ArrayList<>(days.size());
		int cost = calculateCostFor(new LinkedList<Integer>(days));
		return cost > 23 ? 23 : cost;
	}

	private int calculateCostFor(List<Integer> days) {

		daysInSequence.clear();
		if (days.size() == 0)
			return 0;

		for (int i = 0; i < days.size(); i++) {
			int curDay = days.get(i);
			int curWeekMax = curDay + 6;
			daysInSequence.add(1);

			for (int j = i + 1; j < days.size() && days.get(j) <= curWeekMax; j++) {
				daysInSequence.set(i, daysInSequence.get(i) + 1);
			}
		}
		Integer maxSeqCount = Collections.max(daysInSequence);

		// if max is duplicated also fine.. the idea is to consume and reduce
		// the days.
		int startIndex = daysInSequence.indexOf(maxSeqCount);

		int cost = maxSeqCount > 3 ? consumeWeek(days, startIndex, maxSeqCount) : consumeRemainingDays(days);

		
		return cost + calculateCostFor(days);
	}

	private int consumeRemainingDays(List<Integer> days) {
		int count = days.size();
		days.clear();
		return count * 2;
	}

	private int consumeWeek(List<Integer> days, int start, int count) {
		for (int i = 1; i <= count; i++) {
			days.remove(start);
		}
		return count > 3 ? 7 : 2 * count;
	}

}
