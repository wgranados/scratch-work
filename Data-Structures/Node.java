/** Name: William Granados
 *  Date: 09/11/14
 *  Purpose: Creates a generic node
 * */
public class Node< T extends Comparable<T> > {
	
	public T value;
	public Node<T>before;
	public Node<T>next;
	
	/**Creates a node with the corresponding value, before and after nodes are automatically set to null.
	 * @value value to be assigned*/
	public Node(T value){
		this.value = value;
		this.before = null;
		this.next = null;
	}

	/**Returns a string representation of nodes value*/
	public String toString(){
		return this.value.toString();
	}
}
