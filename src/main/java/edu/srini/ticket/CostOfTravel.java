package edu.srini.ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CostOfTravel {

	 int[] bucket = new int[7];
	
	public static void main(String[] args) {

		List<Integer> days = new ArrayList<Integer>();
		//days.addAll(Arrays.asList(1, 2, 3, 5, 6, 7, 11, 15, 17, 19, 23, 27, 29, 30));
		//days.addAll(Arrays.asList(1,  15, 17 ,19, 21));
		days.addAll(Arrays.asList(1));
		System.out.println(new CostOfTravel().calculateCost(days));

	}

	private  void resetBucket() {
		for (int i = 0; i< 7; i++)
		{
			bucket[i] = 0;
		}
	}

	public  int calculateCost(List<Integer> days) {
		boolean bucketStart = true;
		int mainCost = 0;
	
		for (int daysIndex = 0; daysIndex < days.size(); ){
			
			int nextTravel = 0;
			
			if(bucketStart){
			    bucket[0] = days.get(daysIndex);
			    bucketStart = false;
			}else{
				
				nextTravel = days.get(daysIndex) -  bucket[0];
				
				if(nextTravel <= 6){
					bucket[nextTravel] = days.get(daysIndex);
				}
			}
			if(nextTravel > 6 ){
				mainCost += calculateBucketCost(bucket);
				resetBucket();
				bucketStart = true;
				continue;
		    }
			daysIndex++;
		}
		mainCost += calculateBucketCost(bucket);
		return mainCost > 23 ? 23 : mainCost ;
	}

	private  int calculateBucketCost(int[] bucket2) {
		int count = (int) Arrays.stream(bucket2).filter(e -> e > 0).count();
		
		return  count > 3 ? 7 :  2 * count;
	}

}
