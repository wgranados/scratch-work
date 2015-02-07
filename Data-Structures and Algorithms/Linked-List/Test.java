
/** Name: William Granados
 *  Date: 07/12/14
 *  Purpose: testing purposes for linked list class
 * */
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	public static void main(String[]args){
		//linkedlist_check();
		//parenthesis_check();
		//reverse_input();
		//queue_check();
		radix_sort();
		
	}
	/**Checks queue by using a breadth first search*/
	public static void queue_check(){
		Queue<Integer>q = new Queue<Integer>();
		for(int i = 0; i < 5;i++)
			q.push(new Node<Integer>(i));
		while(!q.isEmpty()){
			System.out.println(q.pop().value);
		}
	}
	/**Simple implementation of balanced parenthesis*/
	public static void parenthesis_check(){
		Stack<String>stack = new Stack<String>();
		//Scanner sc = new Scanner(System.in);
		String in = "{}{[][][][][][][][][][][][][][][][][][][][]()()([])}";//sc.nextLine()
		boolean balanced = true;
		for(int i = 0; i < in.length();i++){
			if(in.charAt(i) == '{' || in.charAt(i) == '[' || in.charAt(i) == '('){
				stack.push(new Node<String>(in.substring(i, i+1)));
			}
			else{
				// if the current parenthesis on the top of the stack is not the
				// corresponding parenthesis for the current parenthesis then the
				// string is not a balanced parenthesis.
				Node<String> val = stack.pop();
				String cur = in.substring(i, i+1);
				if(val == null){
					balanced = false;
					break;
				}
				if(val.value.compareTo("[") == 0 && cur.compareTo("]") != 0){
					balanced = false;
					break;
				}
				else if(val.value.compareTo("(") == 0 && cur.compareTo(")") != 0){
					balanced = false;
					break;
				}
				else if(val.value.compareTo("{") == 0 && cur.compareTo("}") != 0){
					balanced = false;
					break;
				}
			}
		}
		if(!stack.isEmpty())
			balanced = false;
		System.out.println(in);
		System.out.printf("This string is %s.\n", balanced ? "balanced":"not balanced");
	}
	/**Simple implementation of the lowest power radix sort*/
	public static void radix_sort(){
		int highest_pow_10 = 1, N = 8;
		int arr[] = {7125,21171,120,43859,73641,31975,51455,60433};
		// find the highest digit we have to go up to
		for(int i = 0; i < N;i++){
			if(arr[i]%highest_pow_10 != arr[i]){
				highest_pow_10*=10;
			}
		}
		ArrayList< Queue<Integer> > list = new ArrayList< Queue<Integer> >();
		for(int i = 0; i <= 10;i++)
			list.add(new Queue<Integer>());
		for(int p = 1; p <= highest_pow_10;p*=10){
			for(int i = 0;i < N;i++){
				if(arr[i]%p == arr[i]){
					list.get(0).add(new Node<Integer>(arr[i]));
				}
				else{
					// find the corresponding digit index for the digit without use of strings
					int index = 0,elem = arr[i],pp = p;
					while(pp > 1){
						elem/=10;
						pp/=10;
					}
					index = elem%10;
					list.get(index).add(new Node<Integer>(arr[i]));
				}
			}
			// assign the values to the original array
			int index = 0;
			for(int i = 0; i <= 9;i++){
				while(!list.get(i).isEmpty()){
					arr[index++] = (int)list.get(i).front().value;
					list.get(i).pop();
				}
			}
		}
		for(int i = 0; i < N;i++){
			System.out.println(arr[i]);
		}
	}
	public static void reverse_input(){
		Stack<Integer>stack = new Stack<Integer>();
		Scanner s = new Scanner(System.in);
		while(true){
			int in = s.nextInt();
			if(in == -1)
				break;
			else
				stack.push(new Node<Integer>(in));
		}
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
		s.close();
	}
	public static void linkedlist_check(){
		LinkedList<Integer> list = new LinkedList<Integer>();
		// test 1
		list.add(new Node<Integer>(1));
		list.add(new Node<Integer>(1));
		list.add(new Node<Integer>(1));
		list.add(new Node<Integer>(1));
		list.add(new Node<Integer>(1));
		list.add(new Node<Integer>(17));
		list.add(new Node<Integer>(81));
		list.add(new Node<Integer>(19));
		list.add(new Node<Integer>(12));
		list.add(new Node<Integer>(2));
		list.add(new Node<Integer>(1));
		list.add(new Node<Integer>(3));
		list.add(new Node<Integer>(4));
		list.add(new Node<Integer>(6));
		list.add(new Node<Integer>(8));
		list.add(new Node<Integer>(1));
		System.out.println(list);
		System.out.println(list);
		list.deleteOccurences(new Node<Integer>(1));
		System.out.println(list);
		list.clear();
		System.out.println(list);
		list.deleteOccurences(new Node<Integer>(5));
		// test 2
		for(int i = 0; i < 10; i++)
			list.add(new Node<Integer>(i));
		System.out.println(list);
		list.insertSorted(new Node<Integer>(1));
		System.out.println(list);
		list.insertSorted(new Node<Integer>(1));
		System.out.println(list);
		System.out.println(list.lastIndexOf(new Node<Integer>(1)));
		System.out.println(list.indexOf(new Node<Integer>(1)));
		list.setHead(new Node<Integer>(100));
		System.out.println(list);
		System.out.println(list.contains(new Node<Integer>(1)) ? "Is present":"Not Present");
		list.setHead(new Node<Integer>(1));
		System.out.println(list);
		list.deleteOccurences(new Node<Integer>(1));
		System.out.println(list);
	}

}
