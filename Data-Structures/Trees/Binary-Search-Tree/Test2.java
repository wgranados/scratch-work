
public class Test2 {
	public static void main(String[]args){
		BST<Integer> bst = new BST<Integer>();
		bst.insert(5);
		bst.insert(7);
		bst.insert(6);
		bst.insert(24);
		bst.insert(54);
		bst.insert(31);
		bst.insert(1);
		bst.insert(4);
		bst.insert(24);
		bst.inOrder(bst.root);
		System.out.println(bst.max(bst.root).value);
	}
}

class BST<T extends Comparable<T> > {
	
	public Node<T> root;
	
	public void insert(Node<T>node){
		
		if(this.root == null){
			node = root;
			return;
		}
		else{
			Node<T>current = root;
			// we keep on comparing until we find a value that is null
			while(true){
				// less than or equal to current node
				if(node.value.compareTo(current.value) <= 0){
					if(current.left != null)
						current = current.left;
					else{
						current.left = node;
						break;
					}
				}
				// greater than current node
				else{
					if(current.right != null)
						current = current.right;
					else{
						current.right = node;
						break;
					}
				}
			}
		}
		
	}
	public void insert(T value){
		Node<T>val = new Node<T>(value);
		// we are initializing the tree
		if(this.root == null)
			this.root = val;
		else{
			Node<T>current = root;
			// we keep on comparing until we find a value that is null
			while(true){
				// less than or equal to current node
				if(val.value.compareTo(current.value) <= 0){
					if(current.left != null)
						current = current.left;
					else{
						current.left = val;
						break;
					}
				}
				// greater than current node
				else{
					if(current.right != null)
						current = current.right;
					else{
						current.right = val;
						break;
					}
				}
			}
		}
	}
	private Node<T> insert(Node<T>n, T value){
		Node<T>current = root;
		Node<T>node = new Node<T>(value);
		while(current.value.compareTo(n.value) != 0){
			if(current.value.compareTo(n.value) < 0)
				current = current.left;
			else
				current = current.right;
		}
		while(true){
			if(node.value.compareTo(current.value) <= 0){
				if(current.left != null)
					current = current.left;
				else{
					current.left = node;
					break;
				}
			}
			else{
				if(current.right != null)
					current = current.right;
				else{
					current.right = node;
					break;
				}
			}
		}
		return (current.left.value == node.value || current.right.value == node.value) ? current:n;
	}
	
	
	
	public boolean contains(T value){
		Node<T>current = root;
		Node<T>node = new Node<T>(value);
		while(true){
			if(node.value.compareTo(current.value) < 0){
				if(current.left != null)
					current = current.left;
				else
					break;
			}
			else if(node.value.compareTo(current.value) == 0)
				return true;
			else if(node.value.compareTo(current.value) > 0){
				if(current.right != null)
					current = current.right;
				else
					break;
			}
		}
		return false;
	}
	private boolean contains(Node<T> n, T value){
		Node<T>current = root;
		Node<T>node = new Node<T>(value);
		while(current.value.compareTo(n.value) != 0){
			if(current.value.compareTo(n.value) < 0)
				current = current.left;
			else
				current = current.right;
		}
		while(true){
			if(node.value.compareTo(current.value) < 0){
				if(current.left != null)
					current = current.left;
				else
					break;
			}
			else if(node.value.compareTo(current.value) == 0)
				return true;
			else if(node.value.compareTo(current.value) > 0){
				if(current.right != null)
					current = current.right;
				else
					break;
			}
		}
		return false;
	}
	
	public Node<T> min(Node<T>n){
		Node<T>current = root;
		while(current.value.compareTo(n.value) != 0){
			if(current.value.compareTo(n.value) < 0)
				current = current.left;
			else
				current = current.right;
		}
		while(current.left != null){
			current = current.left;
		}
		return current;
	}
	public Node<T> max(Node<T>n){
		Node<T>current = root;
		while(current.value.compareTo(n.value) != 0){
			if(current.value.compareTo(n.value) < 0)
				current = current.left;
			else
				current = current.right;
		}
		while(current.right != null){
			current = current.right;
		}
		return current;
	}

	public void preOrder(Node<T>current){
		System.out.println(current.value);
		if(current.left != null)
			preOrder(current.left);
		if(current.right != null)
			preOrder(current.right);
	}
	public void inOrder(Node<T>current){
		if(current.left != null)
			inOrder(current.left);
		System.out.println(current.value);
		if(current.right != null)
			inOrder(current.right);
	}
	public void postOrder(Node<T>current){
		if(current.left != null)
			postOrder(current.left);
		if(current.right != null)
			postOrder(current.right);
		System.out.println(current.value);
	}
	
}

class Node< T extends Comparable<T> > {
	public T value;
	public Node<T> left,right;
	public Node(T value){
		this.value = value;
	}

}


