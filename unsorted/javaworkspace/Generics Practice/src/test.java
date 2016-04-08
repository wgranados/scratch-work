import java.util.ArrayList;

public class test {
	public static void main(String[]args){
		ArrayList<Integer> in = new ArrayList<>();
		
		for(int i = 1; i <= 100;i++)
			in.add(i);
		
		System.out.printf("%d\n",Algorithm.specialIdentifierChecker(in, new Checker()));
	
		
	}
}