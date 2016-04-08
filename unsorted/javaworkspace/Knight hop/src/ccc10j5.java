import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class ccc10j5 {
	static boolean[][] v = new boolean[8][8];
	
	public static void main(String[]args){
		Scanner s = new Scanner(System.in);
		int r1 = s.nextInt(),c1 = s.nextInt();
		int r2 = s.nextInt(),c2 = s.nextInt();
		int ans = 0;
		r1--;r2--;c1--;c2--;
		Queue< state >q = new LinkedList< state >();
		q.add(new state(r1,c1,0));
		while(!q.isEmpty()){
			int r = q.peek().r;
			int c = q.peek().c;
			int d = q.peek().d;
			q.poll();
			if(r < 0 || c < 0 || r >= 8 || c >= 8 ||v[r][c])continue;
			v[r][c] = true;
			if(r == r2 && c == c2){
				ans = d;
				break;
			}
			q.add(new state(r-2,c-1,d+1));
			q.add(new state(r-2,c+1,d+1));
			q.add(new state(r+2,c-1,d+1));
			q.add(new state(r+2,c+1,d+1));
			q.add(new state(r-1,c+2,d+1));
			q.add(new state(r+1,c+2,d+1));
			q.add(new state(r-1,c-2,d+1));
			q.add(new state(r+1,c-2,d+1));
			
		}
		System.out.println(ans);
	}
}

class state{
	public int r,c,d;
	public state(int r,int c,int d){
		this.r = r;
		this.c = c;
		this.d = d;
	}
}