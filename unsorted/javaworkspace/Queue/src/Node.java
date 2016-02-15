
public class Node<T> {
	private T val;
	private T before,after;
	public Node(T val, T before, T after){
		this.setVal(val);
		this.setBefore(before);
		this.setAfter(after);
	}
	
	public T getVal() {
		return val;
	}
	public void setVal(T val) {
		this.val = val;
	}
	public T getBefore() {
		return before;
	}
	public void setBefore(T before) {
		this.before = before;
	}
	public T getAfter() {
		return after;
	}
	public void setAfter(T after) {
		this.after = after;
	}
	
}
