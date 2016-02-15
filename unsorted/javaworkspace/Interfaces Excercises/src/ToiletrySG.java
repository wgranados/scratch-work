
public class ToiletrySG extends GoodsSGA implements Taxable{
	
	private String brand;
	
	public ToiletrySG(String des, double pr, int quant, String brand) {
		super(des, pr, quant);
		
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
	
	public String getBrand(){
		return this.brand;
	}
	public void setBrand(String newBrand){
		this.brand = newBrand;
	}
	
	@Override
	public double calculateTax() {
		return this.getPrice()*taxRate;
	}
	
}
