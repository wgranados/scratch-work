
public class test {
	public static void main(String[]args){
		/*vector<Integer> arr = new vector<>(5,5);
		arr.push_back(6);
		arr.insert(0, 2);
		arr.insert(2, 1);
		System.out.println(arr);
		arr.erase(0, 1);
		System.out.println(arr);*/
		vector<Pair> arr = new vector<>(5, new Pair(5f,5f));
		arr.push_back( new Pair(5,5));
		System.out.println(arr);
	}
}

class Pair<L extends Comparable<L>,R extends Comparable<R> > {

	  private final L left;
	  private final R right;

	  public Pair(L left, R right) {
	    this.left = left;
	    this.right = right;
	  }

	  public L getLeft() { return left; }
	  public R getRight() { return right; }
	  @Override
	  public boolean equals(Object o) {
	    if (o == null) return false;
	    if (!(o instanceof Pair)) return false;
	    Pair pairo = (Pair) o;
	    return this.left.equals(pairo.getLeft()) &&
	           this.right.equals(pairo.getRight());
	  }

	}
class vector<T>{
	private T []arr;
	int size;
	public vector(){
		
	}
	@SuppressWarnings("unchecked")
	public vector(int size){
		this.size = size;
		this.arr = (T[]) new Object[size];
	}
	@SuppressWarnings("unchecked")
	public vector(int size, T value){
		this.size = size;
		this.arr = (T[]) new Object[size];
		for(int i = 0; i < size;++i){
			arr[i] = value;
		}
	}
	@SuppressWarnings("unchecked")
	public void push_back(T obj){
		this.size++;
		T []oldArr = this.arr;
		this.arr = (T[]) new Object[size];
		for(int i = 0; i < size-1;i++){
			arr[i] = oldArr[i];
		}
		arr[size-1] = obj;
	}
	public T getIndex(int i){
		if(i >= size)return null;
		return arr[i];
	}
	@SuppressWarnings("unchecked")
	public void insert(int it, T obj){
		this.size++;
		T []oldArr = this.arr;
		this.arr = (T[]) new Object[size];
		for(int i = 0; i < it;i++){
			arr[i] = oldArr[i];
		}
		arr[it] = obj;
		for(int i = it+1; i < size;i++){
			arr[i] = oldArr[i-1];
		}
	}
	@SuppressWarnings("unchecked")
	public void erase(int it, int len){
		T[] newArr = (T[]) new Object[size-len];
		int j = 0;
		for(int i = 0; i < size;i++){
			if(i >= it && i < it+len)continue;
			newArr[j++] = arr[i];
		}
		this.arr = newArr;
	}
	public String toString(){
		String toRet = "[ ";
		for(T elem:arr)
			toRet+= elem + ",";
		toRet = toRet.substring(0, toRet.length()-1) + " ]";
		return toRet;
	}
}
