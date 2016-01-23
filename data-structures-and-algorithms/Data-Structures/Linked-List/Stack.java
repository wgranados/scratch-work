
/** Name: William Granados
 *  Date: 07/12/14
 *  Purpose: Creates a stack data structure
 * */
public class Stack<T extends Comparable<T>> extends LinkedList<T>{
	
	private int MAX_SIZE;
	private boolean CONDITIONAL_SIZE;
	
	/**Initializes a stack with no restraints on size*/
	public Stack(){
		super();
	}
	/**Initializes a stack with constraints on size
	 * @MAX_SIZE constraint on size*/
	public Stack(int MAX_SIZE){
		super();
		this.MAX_SIZE = MAX_SIZE;
		this.CONDITIONAL_SIZE = true;
	}
	
	/**Pushes a node onto a stack if and only if it abides by the conditional size.
	 * @node value to be pushed*/
	public void push(Node<T>node){
		if(!this.CONDITIONAL_SIZE){
			this.add(node);
		}
		else{
			if(this.size + 1 <= this.MAX_SIZE){
				this.add(node);
			}
		}
	}
	
	/**Returns the node at the top of the stack
	 * @return node at top of stack
	 * @return null if stack is empty*/
	public Node<T>top(){
		return this.getTail();
	}
	
	/**Pops a node from the top of a stack.
	 * @return node at top stack
	 * @return null if stack is empty*/
	public Node<T> pop(){
		Node<T>cur = this.getTail();
		this.delete(this.size-1);
		return cur;
	}
	/**Checks if the stack is empty*/
	public boolean isEmpty(){
		return this.size == 0 ? true:false;
	}
	/**Checks if the stack is full as specified by the MAX_SIZE variable*/
	public boolean isFull(){
		return (this.CONDITIONAL_SIZE) ? ((this.size == MAX_SIZE) ? true:false):false;	
	}
}
