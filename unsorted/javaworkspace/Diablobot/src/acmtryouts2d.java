import java.util.Scanner;

public class acmtryouts2d {
	public static void main(String[]args){
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		String in = s.nextLine();
		for(int i = 0;i < N;++i){
			in = s.nextLine().toLowerCase();
			String wu[] = in.split(" ");
			if(wu[0].length() >= 2 && wu[0].charAt(wu[0].length()-2) == '\'' && wu[0].charAt(wu[0].length()-1) == 's')
			   System.out.println("Set");
			else if(wu[0].equals("damaged"))
			   System.out.println("Normal");
			else if(wu.length > 2 && wu.length <= 4 && wu[wu.length-2].equals("of"))
			   System.out.println("Magic");
			else if(wu.length == 2 && wu[0].equals("of"))
			   System.out.println("Rare");
			else if(wu.length == 2)
			   System.out.println("Not sure, take anyways");
			else
			   System.out.println("Normal");
		}
	}
}
