/** Name: William Granados
 *  Date: 13/11/14
 *  Purpose: Creates a queue data structure with a fixed capacity
 *  		 Overall complexity of O(1) for push and pop functions
 * */

public class CircularQueue implements Queue {
	
	
	private int head, tail;
	private int size;
	private Object[] contents;

	/** Creates a circular queue of size N
	 * @N maximum size of queue */
	public CircularQueue(int N) {
		contents = new Object[N];
		tail = contents.length - 1;
	}

	/** Appends an object to end of queue size permitting
	 * @value object to be appended on end of queue*/
	public void enqueue(Object value){
		// the position of the tail is incremented to become one based
		// then the remainder is taken to get its relative position
		tail = (tail+1) % contents.length;
		contents[tail] = (Object)value;
		size++;
	}
	/** Returns an object from the front of the queue then pops it off the queue, size permitting.
	 *  @return object object at front of queue
	 *  @return null if queue is empty*/
	public Object dequeue(){
		Object result = contents[head];
		head = (head+1)%contents.length;
		size--;
		return result;
	}
	/** Return the element from the front of the queue, size permitting
	 *  @return object object at the front of the queue
	 *  @return null if queue is empty */
	public Object head(){
		return contents[head];
	}

	/** Returns the number of elements present in the queue
	 *  @return size number of elements in queue*/
	public int size(){
		return size;
	}
}
