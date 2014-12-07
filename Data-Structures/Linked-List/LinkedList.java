/** Name: William Granados
 *  Date: 07/12/14
 *  Purpose: Creates a generic linked list
 * */
public class LinkedList< T extends Comparable<T> >{
	
	private Node<T> head;
	private Node<T> tail;
	public int size;
	
	//----------------------------CONSTRUCTORS--------------------------------\\
	
	/**Creates an empty linked list*/
	public LinkedList(){
		this.head = null;
		this.tail = null;
	}
	/**Creates a linked list where the head and the tail are the same node
	 * @referenceNode head and tail of linked list*/
	public LinkedList(Node<T>referenceNode){
		this.head = referenceNode;
		this.tail = referenceNode;
		this.head.next = null;
		this.size = 1;
	}
	/**Creates a new linked list with a defined head and tail
	 * @head head of linked list
	 * @tail tail of linked list*/
	public LinkedList(Node<T>head, Node<T>tail){
		this.head = head;
		this.size = 0;
		Node<T>current = this.head;
		while(current!=tail && current != null){
			this.size++;
			current = current.next;
		}
		this.tail = current;
		this.tail.next = null;
	}
	
	//----------------------------GETTERS & SETTERS----------------------------\\
	
	/**Returns the head of the linked list
	 * @return head of list*/
	public Node<T> getHead(){
		return this.head;
	}
	/**Alters the head of the linked list
	 * @head new head value*/
	public void setHead(Node<T>head){
		// if the linked list is of size 1 then we know don't have to worry about assigning pointers to its non-existent proceeding elements
		if(this.size == 1){
			this.head = head;
			this.tail = head;
		}
		else{
			head.next = this.head.next;
			this.head.next.before = head;
			this.head = head;
		}
	}
	/**Returns the tail of the linked list
	 * @return tail of list*/
	public Node<T> getTail(){
		return this.tail;
	}
	/**Sets tail of the linked list
	 * @tail tail of list*/
	public void setTail(Node<T>tail){
		// if the linked list is of size 1 then we know don't have to worry about assigning pointers to its non-existent proceeding elements
		if(this.size == 1){
			this.head = tail;
			this.tail = tail;
		}
		else{
			this.tail.before = tail;
			this.tail = tail;
		}
	}

	//------------------------------MUTATOR METHODS-----------------------------\\
	
	/**Appends element at the end of the linked list
	 * @value element to be appended*/
	public void add(Node<T>value){
		if(this.head != null){
			value.before = this.tail;
			value.next = null;
			this.tail.next = value;
			this.tail = value;
			this.size++;
		}
		else{
			this.head = value;
			this.tail = value;
			this.size++;	
		}
	}
	/**Inserts element at the specified index in linked list
	 * @index 0-based index of linked list
	 * @value element to be inserted*/
	public void add(int index, Node<T>value){
		if(index == 0){
			this.addHead(value);
			return;
		}
		else{
			Node<T>current = this.head;
			for(int i = 0;i < this.size;i++){
				if(i == index){
					value.before = current.before;
					value.next = current;
					current.before.next = value;
					current.before = value;
					this.size++;
					return;
				}
				current = current.next;
			}
		}
	}
	/**Inserts element at the beginning of the linked list
	 * @value element to be inserted*/
	public void addHead(Node<T>value){
		if(this.head == null){
			this.head = value;
			this.tail = value;
			this.size++;
			return;
		}
		else{
			value.next = this.head;
			this.head.before = value;
			this.head = value;
			this.size++;
		}
	}
	/**Inserts element at the end of the linked list
	 * @value element to be inserted*/
	public void addTail(Node<T>value){
		this.tail.next = value;
		value.before = this.tail;
		this.tail = value;
		this.size++;
	}
	/**Deletes node at specified index
	 * @index specified index*/
	public void delete(int index){
		Node<T>current = this.head;
		if(this.head != null && this.tail != null){
			if(index == 0){
				if(this.size == 1){
					this.head = null;
					this.tail = null;
				}
				else{
					this.head = head.next;
					this.head.before = null;
				}
				this.size--;
			}
			else if(index == this.size-1){
				if(this.size == 1){
					this.head = null;
					this.tail = null;
				}
				else{
					this.tail = this.tail.before;
					this.tail.next = null;
				}
				this.size--;
			}else{
				for(int i = 0; i < this.size;i++){
					if(index == i){
						current.before.next = current.next;
						current.next.before = current.before;
						this.size--;
						return;
					}
					current = current.next;
				}
			}
		}
	}
	/**Deletes all occurrences of the specified value
	 * @value specified value to be deleted*/
	public void deleteOccurences(Node<T>value){
		if(this.head != null && this.tail != null){
			// check for values that definitely do not have their next or before values as null, thus avoiding null pointer exceptions
			Node<T>current = this.head.next;
			for(int i = 1; i < this.size-1;i++){
				if(current.value.compareTo(value.value) == 0){
					current.before.next = current.next;
					current.next.before = current.before;
					this.size--;
					i--;// we decrement the iterator to avoid skipping an element
				}
				current = current.next;
			}
			// deletes the edge cases 
			if(this.head.value.compareTo(value.value) == 0){
				this.delete(0);
			}
			if(this.tail.value.compareTo(value.value) == 0){
				this.delete(this.size-1);
			}
		}
	}
	/**Insert node in a sorted list in its correct place
	 * @value node to be inserted */
	public void insertSorted(Node<T>value){
		// if the current value is less than the head we are implicitly adding a head to a sorted linked list
		if(value.value.compareTo(this.head.value) < 0){
			this.addHead(value);
			return;
		}
		else{
			Node<T>current = this.head;
			for(int i = 0; i < this.size-1;i++){
				if(current.value.compareTo(value.value) <= 0 && current.next.value.compareTo(value.value) > 0){
					current.next.before = value;
					value.next = current.next;		
					current.next = value;
					value.before = current;
					this.size++;
					return;
				}
				current = current.next;
			}
			// otherwise insert node at end if greater than all nodes in list
			this.add(value);
		}
	}
	
	/**Clears all elements in the current linked list*/
	public void clear(){
		// I allow Java's garbage collector to do to the work for me
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	/**Returns a copy of current linked list
	 * @return copy of linked list*/
	public LinkedList<T> clone(){
		return new LinkedList<T>(this.head,this.tail);
	}
	
	//--------------------------------QUERY METHODS--------------------------------\\
	/**Returns a true of element is present in linked list
	 * @arg0 element to be checked for*/
	public boolean contains(Node<T>arg0){
		Node<T>current = this.head;
		while(current!=null){
			if(current.value.compareTo(arg0.value) == 0)
				return true;
			else
				current = current.next;
		}
		return false;
	}
	/**Checks whether the list is sorted or not, if the list is empty we assume it is sorted*/
	public boolean isSorted(){
		// if the list is empty we assume it's sorted
		if(this.head == null)
			return true;
		else{
			// if for any node the value of the node is greater that it's next the list is not sorted
			Node<T>current = this.head;
			for(int i = 0; i < this.size-1;i++){
				if(current.value.compareTo(current.next.value) > 0)
					return false;
			}
			return true;
		}
	}
	/**Returns element at specified index in linked list
	 * @return ith node in linked list*/
	public Node<T> get(int index){
		Node<T> current = this.head;
		for(int i = 0;i < this.size;i++){
			if(i == index)
				return current;
			current = current.next;
		}
		System.out.println("not found");
		return null;
	}
	/**Returns the index of the first occurrence of element in the linked list
	 * if element not found -1 is returned
	 * @value element to be searched for
	 * @return 0-based index of first occurrence*/
	public int indexOf(Node<T>value){
		Node<T> current = this.head;
		for(int i = 0;i < this.size;i++){
			if(current.value.compareTo(value.value) == 0)
				return i;
			current = current.next;
		}
		return -1;
	}
	/**Returns the index of the last occurrence of element in the linked list
	 * if element is not found -1 is returned
	 * @value element to be searched for
	 * @return 0-based index of first occurrence*/
	public int lastIndexOf(Node<T>value){
		Node<T> current = this.tail;
		for(int i = this.size-1;i >= 0;i--){
			if(current.value.compareTo(value.value) == 0)
				return i;
			current = current.before;
		}
		return -1;
	}
	
	/**Returns a string representation of the current linked list
	 * @return string representation of linked list*/
	public String toString(){
		Node<T> current = this.head;
		String toReturn = "[";
		for(int i = 0;i < this.size;i++){
			if(i != this.size-1)
				toReturn += current.value.toString() + ", " ;
			else
				toReturn += current.value.toString();
			current = current.next;
		}
		toReturn += "]";
		return toReturn;
	}
	
	
}
