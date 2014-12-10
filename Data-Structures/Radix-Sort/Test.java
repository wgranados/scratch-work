import java.util.ArrayList;


public class Test {
	public static void main(String[]args){
		int arr[] = {5,2,210,1,50000,25,354,1,2};
		radix_sort(arr,10000,true);
	}
	public static void radix_sort(int[] list, int maxPowerOf10, boolean trace){
		int N = list.length;
		ArrayList<CircularQueue> queue = new ArrayList<CircularQueue>();
		for(int i = 0; i < 10;i++)
			queue.add(new CircularQueue(N));
		
		for(int p = 1; p <= maxPowerOf10;p*=10){
			for(int i = 0; i < N;i++){
				if(list[i] % p == list[i]){
					queue.get(0).enqueue(list[i]);
				}
				else{
					int index = 0,elem = list[i],pp = p;
					while(pp > 1){
						elem/=10;
						pp/=10;
					}
					index = elem%10;
					queue.get(index).enqueue((Integer)list[i]);
				}
			}
			// assign the values to the original array
			int index = 0;
			for(int i = 0; i <= 9;i++){
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

