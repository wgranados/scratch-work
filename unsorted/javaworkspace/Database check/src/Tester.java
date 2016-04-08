import java.awt.List;


public class Tester {
	static Database database = new Database();
	public static void main(String[]args){
		String query = "bruh its burhan";
		if(database.exactQuery(query) != null){
			System.out.println(query + " : " + database.exactQuery(query));
		}else{
			// try best suggestions
			String a = "pizza", b = "burhan";
			if(database.search(a, b))
				System.out.println("found");
			else
				System.out.println("oh noes");
			
			//List suggestions = (List) database.suggestions(query);
			
		}
		
	}
}
