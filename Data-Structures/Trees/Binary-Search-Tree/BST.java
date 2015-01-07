/** Name: William Granados
 *  Date: 09/11/14
 *  Purpose: Creates a generic bst
 * */
public class Tree<T extends Comparable<T> > {
	
	public Node<T> root;
	public int height;
	
	public Tree(){}
	
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
