
public class Node<T extends Comparable<T>> {
	T value;
	Node<T> left, right;
	public Node(T value){
		this.value = value;	
	}	
}
