
/** Name: William Granados
 *  Date: 07/12/14
 *  Purpose: Creates a queue data structure
 * */
public class Queue<T extends Comparable<T>> extends LinkedList<T>{
	
	private int MAX_SIZE;
	private boolean CONDITIONAL_SIZE;
	
	/**Initializes a stack with no restraints on size*/
	public Queue(){
		super();
	}
	/**Initializes a stack with constraints on size
	 * @MAX_SIZE constraint on size*/
	public Queue(int MAX_SIZE){
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
	/**Returns the the node at the front of the queue
	 * @return node at front of queue
	 * @return null if queue is empty*/
	public Node<T> front(){
		return this.get(0);
	}
	/**Pops a node from the front of the queue.
	 * @return node at the front of the queue
	 * @return null if queue is empty*/
	public Node<T> pop(){
		Node<T>cur = this.getHead();
		this.delete(0);
		return cur;
	}
	/**Checks if the queue is empty*/
	public boolean isEmpty(){
		return this.size == 0 ? true:false;
	}
	/**Checks if the queue is full as specified by the MAX_SIZE variable*/
	public boolean isFull(){
		return (this.CONDITIONAL_SIZE) ? ((this.size == MAX_SIZE) ? true:false):false;	
	}
}
