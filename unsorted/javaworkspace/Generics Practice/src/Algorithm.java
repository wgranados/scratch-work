import java.util.ArrayList;


interface characteristicIdentifier{
	boolean primeChecker(Integer i);
	boolean oddChecker(Integer i);
	boolean palindromeChecker(Integer i);
}

public final class Algorithm {
	public static int specialIdentifierChecker(ArrayList<Integer>arr, comparisonOperator<Integer> p){
		int cnt = 0;
		for(int  i = 0; i < arr.size();i++)
			if(p.primeChecker(arr.get(i)))
				cnt++;
				
		return cnt;		
	}
}

interface comparisonOperator<T>{
	boolean primeChecker(T i);
	boolean oddChecker(T i);
	boolean palindromeChecker(T i);
}
final class Checker implements comparisonOperator<Integer>{
	public boolean primeChecker(Integer i){
		for(int j = 2; j < Math.sqrt(i);j++)
			if(i%j == 0)return false;
		return true;
	}
	public boolean oddChecker(Integer i){
		return i%2 == 0;
	}
	public boolean palindromeChecker(Integer i ){
		int num = i, rev = 0, dig = 0;
		while (num > 0){
		     dig = num % 10;
		     rev = rev * 10 + dig;
		     num /= 10;
		 }
		 return rev == i ? true:false;
	}
}
