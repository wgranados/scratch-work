

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileHandler {
	
	final int CATEGORIES_SIZE = 10;
	List<List<String>> categories = new ArrayList<List<String>>();
	Scanner sc;
	
	public TextFileHandler(){
		for(int i = 0;i < this.CATEGORIES_SIZE;i++)// create the new categories
			this.categories.add(new ArrayList<String>());
		this.openCategoryFile("assets/Blue.txt", 	categories.get(0));
		this.openCategoryFile("assets/BTTSWD.txt", 	categories.get(1));
		this.openCategoryFile("assets/EWaste.txt", 	categories.get(2));
		this.openCategoryFile("assets/Green.txt", 	categories.get(3));
		this.openCategoryFile("assets/Grey.txt", 	categories.get(4));
		this.openCategoryFile("assets/HHW.txt", 	categories.get(5));
		this.openCategoryFile("assets/OW.txt", 		categories.get(6));
		this.openCategoryFile("assets/PW.txt", 		categories.get(7));
		this.openCategoryFile("assets/SM.txt", 		categories.get(8));
		this.openCategoryFile("assets/YW.txt", 		categories.get(9));
	}
	
	/**Opens a file and returns contents from it into an array list
	 * @param file file path for the file
	 * @param list list for information to be stored in*/
	public void openCategoryFile(String file,List<String>list){
		File categoryFile = new File(file);
		try{
			sc = new Scanner(categoryFile);
		}
		catch(Exception noFile) { 
		    System.out.println("No file found");
		}
		while(sc.hasNext()){
		   list.add(sc.nextLine());
		}
	}
	/**Returns the categories which contain the items*/
	public List<List<String>> getCategories(){
		return this.categories;
	}
}
