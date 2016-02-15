import java.io.*;
import java.lang.*;
import java.util.*;

public class ccc12s2p5 {
	
	private static BufferedReader br;
	private static StringTokenizer st;
	private static PrintStream out;
	static ArrayList<Integer>percentages,tempPercentages;
	
	public static void main(String[]args) throws IOException{
		int N = nextInt();
		boolean OK = false;
		for(int i = 0,j = 0;i < N;++i){
			j = nextInt();
 
		}
		/*for(int i = 1;i <= 100;++i){
			 for(int j = 0; j <= i;++j)
	             tempPercentages.add((int)Math.round(j*100.0/i));
			 for(int j = 0;j < N;++j){
				 if(Collections.binarySearch(tempPercentages, percentages.get(j)) < 0)
					 break;
				 else if(j == N-1){
					 out.printf("%d\n",i);
					 OK = true;
				 }
				 if(OK)break;
				 tempPercentages.clear();
			 }
		}*/
		
	}
	//fast unsigned nextInt() from stdin (CANNOT read negative numbers)
	static int nextUInt() throws IOException {
		int v = 0, c;
		while ((c = System.in.read() - '0') >= 0 && c <= 9) v = v * 10 + c;
		return v;
	}
	//can also read negative numbers, but slightly slower than the above
	static int nextInt() throws IOException {
		int v = 0, c;
		boolean neg = false;
		while ((v = System.in.read() - '0') < 0 && v != -3 || v > 9);
		if (v == -3) {
			v = System.in.read() - '0';
			neg = true;
		}
		while ((c = System.in.read() - '0') >= 0 && c <= 9) v = v * 10 + c;
		return (neg ? -v : v);
	}
	static String nextLine() throws IOException {
		int c = System.in.read();
		StringBuffer ret = new StringBuffer();
		while (c != '\n' && c != '\r' && c != -1) {
			ret.append((char)c);
			c = System.in.read();
		}
		return ret.toString();
	}
}
