/** Name: William Granados
 *  Date: 11/12/14
 *  Purpose: Implementation of radix sort for integers
 * */

import java.util.ArrayList;


public class Test {
	public static void main(String[]args){
		int arr[] = {-5,2,210,1,-50000,25,354,1,2};
		radix_sort(arr,10000,true);
	}
	/**Implements Radix sort on the given array
	 * Complexity is O(N)*/
	public static void radix_sort(int[] list, int maxPowerOf10, boolean trace){
		int N = list.length;
		ArrayList<CircularQueue> queue = new ArrayList<CircularQueue>();
		
		// Creates a list of queues where the 0th queue represents
		// -9, 9th queue represents 0, and 18th queue represents +9
		for(int i = 0; i < 20;i++)
			queue.add(new CircularQueue(N));
		
		for(int p = 1; p <= maxPowerOf10;p*=10){
			for(int i = 0; i < N;i++){
				// negative numbers
				if(list[i] < 0){
					if(Math.abs(list[i]) % p == list[i]){
						// 9 is defined as the 0 index
						queue.get(9).enqueue(list[i]);
					}
					else{
						int index = 0,elem = Math.abs(list[i]),pp = p;
						while(pp > 1){
							elem/=10;
							pp/=10;
						}
						// take the difference so that when we sort it the queues are in order
						// from -9 - 9
						index = 9-elem%10;
						queue.get(index).enqueue((Integer)list[i]);
					}
				}
				// positive numbers
				else{
					if(list[i] % p == list[i]){
						// 9 is defined as the 0 index
						queue.get(9).enqueue(list[i]);
					}
					else{
						int index = 0,elem = list[i],pp = p;
						while(pp > 1){
							elem/=10;
							pp/=10;
						}
						// numbers from 1-9 are defined as indexes from 10-19
						index = 9+elem%10;
						queue.get(index).enqueue((Integer)list[i]);
					}
				}
			}
			// assign the values to the original array
			int index = 0;
			for(int i = 0; i < 20;i++){
				while(queue.get(i).size() != 0){
					list[index++] = (Integer) queue.get(i).dequeue();
				}
			}
			if(trace){
				for(int i = 0; i < N;i++){
					System.out.printf("%d ",list[i]);
				}System.out.println();
			}
		}
		
	}
}

