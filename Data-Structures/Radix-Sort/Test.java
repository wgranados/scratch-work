/** Name: William Granados
 *  Date: 11/12/14
 *  Purpose: Implementation of radix sort for integers
 * */

import java.util.ArrayList;


public class Test {
	public static void main(String[]args){
		int arr[] = {10,-10,0};
		radixSortIntegers(arr,10,true);
	}
	/**Implements Radix sort on the given array that consists of integers
	 * Complexity is O(N)*/
	public static void radixSortIntegers(int[] list, int maxPowerOf10, boolean trace){
		int N = list.length, pass = 0;
		ArrayList<CircularQueue> queue = new ArrayList<CircularQueue>();
		
		// Creates a list of queues where the 0th queue represents
		// -9, 9th queue represents 0, and 18th queue represents +9
		for(int i = 0; i < 20;i++)
			queue.add(new CircularQueue(N));
		
		if(trace){
			System.out.printf("Starting Pass %d\n",pass++);
			for(int i = 0; i < N;i++){
				System.out.printf("%6d ",i);
			}System.out.println();
			for(int i = 0; i < N;i++){
				System.out.printf("%6d ",list[i]);
			}System.out.println();
			System.out.println();
		}
		for(int p = 1; p <= maxPowerOf10 && N != 0;p*=10){
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
			// prints out elements contained within the list
			if(trace){
				System.out.printf("Pass %d\n",pass++);
				for(int i = 0; i < N;i++){
					System.out.printf("%6d ",i);
				}System.out.println();
				for(int i = 0; i < N;i++){
					System.out.printf("%6d ",list[i]);
				}System.out.println();
				System.out.println();
			}
		}
		if(trace){
			System.out.printf("Final Pass %d\n",--pass);
			for(int i = 0; i < N;i++){
				System.out.printf("%6d ",i);
			}System.out.println();
			for(int i = 0; i < N;i++){
				System.out.printf("%6d ",list[i]);
			}System.out.println();
			System.out.println();
		}
	}
}

