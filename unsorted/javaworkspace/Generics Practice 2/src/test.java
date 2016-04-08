import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class test {
	public static void main(String[]args){
		Library<Media> lib = new Library<Media>();
		lib.insert(new Book("Justin Li","Justin Li"));
		lib.insert(new Book("Justin Li 2 The return of Justin","Justin Li"));
		lib.insert(new Book("Justin Li 3 The book of Justin","Justin Li"));
		lib.insert(new Video("Justin Li: The movie",2007));
		lib.insert(new Newspaper("Justin Li insider","Justin Li announces new book!Incredible!"));
		System.out.println(lib.toString());
	}
}

class Library<T>{
	List<T> arr = new ArrayList<T>();
	public Library(){
		
	}
	public void insert(T obj){
		arr.add(obj);
	}
	public T getIndex(int index){
		return arr.get(index);
	}
	public String toString(){
		String toReturn = "";
		for(T elem:arr)
			toReturn+=elem+"\n";
		return toReturn;
	}
	public int size(){
		return arr.size();
	}
}
class Media{
	String name;
	public Media(String name){
		this.name = name;
	}
}

class Book extends Media{
	String author;
	public Book(String name, String author) {
		super(name);
		this.author = author;
	}
	public String toString(){
		return "[" + this.name + " , " + this.author + "]";
	}
}
class Video extends Media{
	int year;
	public Video(String name, int year) {
		super(name);
		this.year = year;
	}
	public String toString(){
		return "[" + this.name + " , " + this.year + "]";
	}
}
class Newspaper extends Media{
	String headline;
	public Newspaper(String name, String headline) {
		super(name);
		this.headline = headline;
	}
	public String toString(){
		return "[" + this.name + " , " + this.headline + "]";
	}
}