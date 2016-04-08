/** Name: William Granados
 *  Date: 05/01/15
 *  Purpose: Creates a generic tree
 * */
public class Node< T extends Comparable<T> > {
	public T value;
	public Node<T> left,right;
	public Node(T value){
		this.value = value;
	}

}
