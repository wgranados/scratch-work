/*
 * The game of Nim starts with a random number of stones between 15 and 30. Two players alternate turns and on each turn may take either 1, 2, or 3 stones from the pile. The player forced to take the last stone loses. Use object-oriented development to create a Nim2 application that allows the user to play Nim against the computer. The Nim2 application and its objects should:

        •        Generate the number of stones to begin with.

        •        Allow the player to go first.

        •        Use a random number generator to determine the number of stones the computer takes.

        •        Prevent the player and the computer from taking an illegal number of stones. For example, neither should be allowed to take three stones when there are only 1 or 2 left. 
 * 
 * */
import java.util.*;

public class Nim2 {
	
	public static void main(String[]args){
		Scanner s = new Scanner(System.in);
		Player computer = new Player();
		int stones_left = (new Random().nextInt(15) + 15);
		boolean player_turn = true;// true is user, false is computer
		
		System.out.printf("%d stones to starts with\n",stones_left);
		
		while(stones_left > 0){
			if(player_turn){
				int in = s.nextInt();
				if(in >= 1 && in <= 3){
					if(in <= stones_left){
						stones_left-=in;
						player_turn = false;
						
						System.out.printf("Player 1 takes %d stone(s).\n",in);
						if(stones_left == 0){
							System.out.println("Player 1 loses!");
							continue;
						}
					}
					else
						System.out.println("Incorrect input, please input a number that is less than or equal to the number of stones left.");
				}
				else
					System.out.println("Incorrect input, please take stones between the range of 1 to 3.");
			}
			else{
				player_turn = true;
				computer.update_stones(stones_left);
				stones_left -= computer.return_stones();
							
				System.out.printf("Player 2 takes %d stone(s).\n",computer.return_stones());
				if(stones_left == 0){
					System.out.println("Player 2 loses!");
					continue;
				}
			}
			System.out.printf("%d Stones left.\n",stones_left);
		}
		s.close();
	}
	
}

class Player{
	private int stones_taken;
	private Random rnd;
	
	public Player(){
		this.rnd = new Random();
		this.stones_taken = rnd.nextInt(3)+1;
	}
	public void update_stones(int max){
		this.stones_taken = (max > 3) ?  rnd.nextInt(3)+1 : rnd.nextInt(max)+1 ;
	}
	public int return_stones(){
		return this.stones_taken;
	}
	
}

