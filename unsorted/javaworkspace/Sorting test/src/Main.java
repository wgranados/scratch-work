import java.util.ArrayList;
import java.util.Collections;


public class Main {
	public static void main(String[]args){
		ArrayList< Pair<Integer,Integer> >list = new ArrayList< Pair<Integer,Integer>>();
		list.add(new Pair<Integer,Integer>(1,2));
		list.add(new Pair<Integer,Integer>(2,1));
		Collections.sort(list,Collections.reverseOrder());
		for(int i = 0;i < list.size();i++){
			System.out.printf("[%d,%d]\n",list.get(i).first,list.get(i).second);
		}
	}
}
