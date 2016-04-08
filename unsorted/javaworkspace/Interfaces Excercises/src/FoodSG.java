
public class FoodSG extends GoodsSGA implements Taxable{
	
	private double calories;
	
	public FoodSG(String des, double pr, int quant, double cal) {
		super(des, pr, quant);
		this.setCalories(cal);
	}
		

	public String getDescription(){
		return super.getDescription();
	}
	public void setDescription(String newDescription){
		super.setDescription(newDescription);
	}
	
	public double getPrice(){
		return super.getPrice();
	}
	public void setPrice(double newPrice){
		super.setPrice(newPrice);
	}
	
	public int getQuantity(){
		return super.getQuantity();
	}
	public void setQuantity(int newQuantity){
		super.setQuantity(newQuantity);
	}
	
	public double getCalories(){
		return this.calories;
	}
	public void setCalories(double calories){
		this.calories = calories;
	}


	
	@Override
	public double calculateTax() {
		return this.getPrice() * taxRate;
	}

}
