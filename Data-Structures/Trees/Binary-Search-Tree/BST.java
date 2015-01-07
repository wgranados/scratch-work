import java.util.ArrayList;
import java.util.Queue;


public class BST<T extends Comparable<T>> {
	
	private Node<T>root;
	
	public void insert(T value){
		Node<T>val = new Node<T>(value);
		Node<T>current = root;
		// we are initializing the tree
		if(current == null)
			this.root = val;
		else{
			// we keep on comparing until we find a value that is null
			while(true){
				// less than or equal to current node
				if(val.getValue().compareTo(current.getValue()) <= 0){
					if(current.getLeftChild() != null)
						current = current.getLeftChild();
					else{
						current.setLeftChild(val);
						val.setParent(current);
						break;
					}
				}
				// greater than current node
				else{
					if(current.getRightChild() != null)
						current = current.getRightChild();
					else{
						current.setRightChild(val);
						val.setParent(current);
						break;
					}
				}
			}
		}
	}
	
	public String toString(){
		@SuppressWarnings("unchecked")
		Queue<Node<T>> q = (Queue<Node<T>>) new ArrayList<Node<T>>();
		String toadd = "";
		q.add(this.root);
		while(!q.isEmpty()){
			Node<T>current = q.poll();
			toadd += " " + current.toString();
			if(current.getLeftChild() != null)
				q.add(current.getLeftChild());
			if(current.getRightChild() != null)
				q.add(current.getRightChild());
		}
		return toadd;
	}
}
