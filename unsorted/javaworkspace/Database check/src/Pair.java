

public class Pair<F extends Comparable<F>,S extends Comparable<S>> implements Comparable<Pair<F,S>>{
	public F first;
	public S second;
	
	public Pair(F first, S second){
		this.first = first;
		this.second = second;
	}
	@Override
	public int compareTo(Pair<F,S> arg0) {
		int fCompare = this.first.compareTo(arg0.first);
		int sCompare = this.second.compareTo(arg0.second);
		return fCompare == 0 ? (sCompare):fCompare;
	}
}
