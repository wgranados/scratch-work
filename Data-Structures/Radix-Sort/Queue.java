
public interface Queue {
	/**Append object to me*/
	void enqueue(Object o);
	
	/**Return my front element
	 * Requires.size() != 0*/
	Object head();
	Object deque();
	int size();
}
