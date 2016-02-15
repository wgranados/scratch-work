
public class ToySG extends GoodsSGA implements Taxable{
	
	private int minimumAge;
	public ToySG(String des, double pr, int quant, int minAge) {
		super(des, pr, quant);
		this.setMinimumAge(minAge);
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
	
	public double getMinimumAge(){
		return this.minimumAge;
	}
	public void setMinimumAge(int newMinimumAge){
		this.minimumAge = newMinimumAge;
	}
	
	@Override
	public double calculateTax() {
		return this.getPrice()*taxRate;
	}
	
}
