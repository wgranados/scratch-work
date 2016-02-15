import java.util.*;

public class StoreSG {
	public static void main(String[]args){
		ArrayList<GoodsSGA> inventory = new ArrayList<GoodsSGA>();
		inventory.add( new FoodSG("Deluxe pizza", 5.00, 500, 500));
		inventory.add( new BookSG("Inferno", 5.00, 20, "Dan Brown"));
		inventory.add( new ToySG("Rubiks cube",5.00,20, 5));
		inventory.add( new ToiletrySG("Toothpaste",2.00, 15, "Colgate"));
		
		for(int i = 0; i < inventory.size();i++){
			System.out.println(inventory.get(i).toString());
			System.out.printf("After Tax: %.2f\n\n",  inventory.get(i).calculateTax());
		}
		
	}
}
