
/** Name: William Granados
 *  Date: 19/11/14
 *  Purpose: testing purposes for linked list class
 * */
import java.util.Scanner;

public class Test {
	public static void main(String[]args){
		//linkedlist_check();
		//parenthesis_check();
		//reverse_input();
	}
	
	public static void parenthesis_check(){
		Stack<String>stack = new Stack<String>();
		Scanner sc = new Scanner(System.in);
		String in = "{}{[][][][][][][][][][][][][][][][][][][][]()()([])}";//sc.nextLine()
		boolean balanced = true;
		for(int i = 0; i < in.length();i++){
			if(in.charAt(i) == '{' || in.charAt(i) == '[' || in.charAt(i) == '('){
				stack.push(new Node<String>(in.substring(i, i+1)));
			}
			else{
				Node<String> val = stack.pop();
				String cur = in.substring(i, i+1);
				System.out.printf("%s : %s\n", (val == null) ? "null":val.value ,cur);
				//System.out.println(val.value + " " +  in.substring(i, i+1));
				if(val == null){
					balanced = false;
					break;
				}
				if(val.value == "[" && cur != "]"){
					balanced = false;
					break;
				}
				if(val.value == "(" && cur != ")"){
					balanced = false;
					break;
				}
				if(val.value == "{" && cur != "}"){
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
