
public class Node<T extends Comparable<T>> {
	
	private T value;
	private Node<T>parent;
	private Node<T> leftChild, rightChild;
	
	public Node(T value){
		this.setValue(value);
		
	}

	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}

	public Node<T> getParent() {
		return parent;
	}
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public Node<T> getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(Node<T> leftChild) {
		this.leftChild = leftChild;
	}

	public Node<T> getRightChild() {
		return rightChild;
	}
	public void setRightChild(Node<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	
	
}
