
public class Test {
	public static void main(String[]args){
		BST<Integer>bst = new BST<Integer>();
		bst.insert(50);
		bst.insert(30);
		bst.insert(70);
		bst.insert(20);
		bst.insert(40);
		bst.insert(60);
		bst.insert(80);
		bst.insert(32);
		bst.insert(65);
		bst.insert(75);
		bst.insert(85);
		bst.insert(34);
		bst.insert(36);
		//bst.insert(81);
		//bst.preOrder(bst.root);
		//System.out.println();
		bst.delete(50);
		bst.preOrder(bst.root);
		System.out.println();
	}
}
