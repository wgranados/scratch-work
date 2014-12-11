/** A queue with fixed capacity and (except for creation)
 * constant-time operations */
public class CircularQueue implements Queue {

	/** The number of elements in me. */
	private int size;
	/** The index of the head and tail of the queue */
	private int head, tail;
	/** The items in me, stored in contents[head .. tail],
	 * with wrap around */
	private Object[] contents;

	/** A CircularQueue with capacity of n elements */
	public CircularQueue(int n) {
		contents = new Object[n];
		tail = contents.length - 1;
	}

	/** Append o to me. */
	public void enqueue(Object o){
		tail = (tail+1) % contents.length;
		contents[tail] = (Object)o;
		size++;
	}

	/** Remove and return my front element.
	 * Requires size() != 0 */
	public Object dequeue(){
		Object result = contents[head];
		head = (head+1)%contents.length;
		size--;
		return result;
	}

	/** Return my front element
	 * Requires: size() != 0 */
	public Object head(){
		return contents[head];
	}

	/** Return the number of elements in me. */
	public int size(){
		return size;
	}
}
