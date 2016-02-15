/** Name: William Granados
 *  Date: 06/11/14
 *  Purpose: Creates a mathematical matrix with varying functions
 * */

public interface Queue {
	
	/**Appends object to the end of a queue data structure*/
	void enqueue(Object o);
	/**Pops element at the front of a queue data structure then returns the element*/
	Object dequeue();
	/**Returns the element at the front of the queue, element is not popped of queue*/
	Object head();
	/**Returns the number of elements present in the queues*/
	int size();
	
}
