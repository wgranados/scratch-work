
public class main {
	public static void main(String[]args){
		String A = "pizza", B = "piz";
		System.out.println(editDistance(A,B));
	}
	/**Returns whether true if the ith character in string
	 * A is the same as the jth character in string B.
	 * @param A first string
	 * @param B second string
	 * @param i ith character in A
	 * @param j jth character in B*/
	public static int diff(String A, String B,int i,int j){
		return A.charAt(i) == B.charAt(j) ? 0:1;
	}
	/**Returns the edit-distance between two strings
	 * Time complexity is O(NM) where N is the length of
	 * first string and M is the length of the second string
	 * @param A first string
	 * @param B second string  */
	public static int editDistance(String A,String B){
		int N = A.length(), M = B.length(),inf = 1000000;
		int [][]dp = new int[N][M];
		for(int i = 0;i < N;i++)
			for(int j = 0;j < M;j++)
				dp[i][j] = inf;
		for(int i = 0;i < N;i++){
			for(int j = 0;j < M;j++){
				if(i == 0)
					dp[i][j] = j;
				else if(j == 0)
					dp[i][j] = i;
				else
					dp[i][j] = Math.min(dp[i-1][j-1]+diff(A,B,i-1,j-1), Math.min(dp[i-1][j]+1,dp[i][j-1]+1) );
			}
		}
		return dp[N-1][M-1];
	}
}
