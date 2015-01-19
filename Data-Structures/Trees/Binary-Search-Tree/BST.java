/** Name: William Granados
 *  Date: 18/01/15
 *  Purpose: Creates a generic bst
 * */
class BST<T extends Comparable<T> > {
	
	public Node<T> root;
	
	/**Inserts a node into the binary search tree. Does this through iteration.
	 * @param node node to be inserted*/
	public void insert(Node<T>node){
		// we are creating a tree
		if(this.root == null)
			this.root = node;
		else{
			Node<T>current = this.root;
			// we keep on comparing until we find a value that is null
			while(true){
				if(node.value.compareTo(current.value) <= 0){ // less than or equal to current node
					if(current.left != null)
						current = current.left;
					else{
						current.left = node;
						break;
					}
				}
				else{ // greater than current node
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
	/**Inserts a value into the binary search tree. Does this through iteration.
	 * @param value value to be inserted*/
	public void insert(T value){
		// we are initializing the tree
		if(this.root == null)
			this.root = new Node<T>(value);
		else{
			Node<T>current = root;
			// we keep on comparing until we find a value that is null
			while(true){
				// less than or equal to current node
				if(value.compareTo(current.value) <= 0){
					if(current.left != null)
						current = current.left;
					else{
						current.left = new Node<T>(value);
						break;
					}
				}
				// greater than current node
				else{
					if(current.right != null)
						current = current.right;
					else{
						current.right = new Node<T>(value);
						break;
					}
				}
			}
		}
	}
	@SuppressWarnings("unused")
	private Node<T> insert(Node<T>n, T value){
		Node<T>current = this.root;
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
		// if we've added a value return true, other return the node that was given
		return (current.left.value == node.value || current.right.value == node.value) ? current:n;
	}
	/**Deletes a value in the binary search tree. Does this through iteration.
	 * @param value value to be deleted*/
	public void delete(T value){
		
		if(this.root.value.compareTo(value) == 0){
			if(this.root.left == null && this.root.right == null)
				this.root = null;
			else{
				if(this.root.right != null){
					Node<T>replacement = this.min(this.root.right);
					Node<T>parent = this.root.right;
					while(parent.left != null && parent.left.left != null)
						parent = parent.left;
					if(parent.left == null){// same as replacement
						replacement.left = this.root.left;
						this.root = replacement;
					}else{
						parent.left = parent.left.right;
						replacement.right = parent;
						replacement.left = this.root.left;
						this.root = replacement;
					}
				}
			}
			
		}else{
			// searching for the parent node
			Node<T>current = this.root;
			while(current != null && current.value.compareTo(value) != 0){
				if(current.left != null && current.left.value.compareTo(value) == 0 || current.right != null && current.right.value.compareTo(value) == 0 ){
					break;
				}
				else{
					if(value.compareTo(current.value) < 0)
						current = current.left;
					else
						current = current.right;
				}
			}
			// value not found in bst
			if(current == null){
				System.out.println("not found");
				return;
			}
			else{
				// if value is in left sub-tree
				if(current.left != null && current.left.value.compareTo(value) == 0){
					// deleting a leaf
					if(current.left.left == null && current.left.right == null){
						current.left = null;
					}
					// one child - left child
					else if(current.left.left != null && current.left.right == null){
						current.left = current.left.left;
					}
					// one child - right child
					else if(current.left.right != null && current.left.left == null){
						current.left = current.left.right;
					}
					else{ // two children
						// find parent node to smallest node in right sub-tree
						Node<T>current2 = current.left.right;
						Node<T>replacement = this.min(current.left.right);
						while(current2 != null && current2.left.left != null){
							current2 = current.left;
						}
						if(current2.left != null ){
							current2.left = current2.left.right;
							replacement.right = current2;
							replacement.left = current.left.left;
							current.left = replacement;
						}else{
							current2.left = current.left.left;
							current.left = current2;
						}
					}
					
				}else{ // otherwise it's in the right sub-tree
					// deleting a leaf
					if(current.right.left == null && current.right.right == null){
						current.right = null;
					}
					// one child - left child
					else if(current.right.left != null && current.right.right == null){
						current.right = current.right.left;
					}
					// one child - right child
					else if(current.right.right != null && current.right.left == null){
						current.right = current.right.right;
					}
					else{ // two children
						// find parent node to smallest node in right sub-tree
						Node<T>current2 = current.right.right;
						Node<T>replacement = this.min(current.right.right);
						while(current2.left != null && current2.left.left != null){
							current2 = current.left;
						}
						if(current2.left != null){
							current2.left = current2.left.right;
							replacement.right = current2;
							replacement.left = current.right.left;
							current.right = replacement;
						}else{
							current2.left = current.right.left;
							current.right = current2;
						}
						
					}
				}
				
			}
		}
		
	}
	/**Checks if a given value is within the binary search tree
	 * @param value value to be searched for*/
	public boolean contains(T value){
		Node<T>current = this.root;
		while(true){
			if(value.compareTo(current.value) < 0){
				if(current.left != null)
					current = current.left;
				else
					break;
			}
			else if(value.compareTo(current.value) == 0)
				return true;
			else if(value.compareTo(current.value) > 0){
				if(current.right != null)
					current = current.right;
				else
					break;
			}
		}
		return false;
	}
	@SuppressWarnings("unused")
	private boolean contains(Node<T> n, T value){
		Node<T>current = this.root;
		while(n.value.compareTo(current.value) != 0){
			if(n.value.compareTo(current.value) < 0)
				current = current.left;
			else
				current = current.right;
		}
		while(true){
			if(value.compareTo(current.value) < 0){
				if(current.left != null)
					current = current.left;
				else
					break;
			}
			else if(value.compareTo(current.value) == 0)
				return true;
			else if(value.compareTo(current.value) > 0){
				if(current.right != null)
					current = current.right;
				else
					break;
			}
		}
		return false;
	}
	
	/**Finds the minimum value in a subtree
	 * @param n root of subtree*/
	public Node<T> min(Node<T>n){
		Node<T>current = this.root;
		while(n.value.compareTo(current.value) != 0){
			if(n.value.compareTo(current.value) < 0)
				current = current.left;
			else
				current = current.right;
		}
		while(current.left != null){
			current = current.left;
		}
		return current;
	}
	/**Finds the maximum value in a subtree
	 * @param n root of subtree*/
	public Node<T> max(Node<T>n){
		Node<T>current = this.root;
		while(n.value.compareTo(current.value) != 0){
			if(n.value.compareTo(current.value) < 0)
				current = current.left;
			else
				current = current.right;
		}
		while(current.right != null){
			current = current.right;
		}
		return current;
	}
	
	/**Prints the pre-order traversal of a subtree
	 * @param current head of subtree*/
	public void preOrder(Node<T>current){
		System.out.print(current.value + " ");
		if(current.left != null)
			preOrder(current.left);
		if(current.right != null)
			preOrder(current.right);
	}
	/**Prints the in-order traversal of a subtree
	 * @param current head of subtree*/
	public void inOrder(Node<T>current){
		if(current.left != null)
			inOrder(current.left);
		System.out.print(current.value + " ");
		if(current.right != null)
			inOrder(current.right);
	}
	/**Prints the post-order traversal of a subtree
	 * @param current head of subtree*/
	public void postOrder(Node<T>current){
		if(current.left != null)
			postOrder(current.left);
		if(current.right != null)
			postOrder(current.right);
		System.out.println(current.value + " ");
	}
	
}
