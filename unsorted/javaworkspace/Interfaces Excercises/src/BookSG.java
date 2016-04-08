
public class BookSG extends GoodsSGA implements Taxable{

	private String author;
	public BookSG(String des, double pr, int quant, String auth) {
		super(des, pr, quant);
		this.setAuthor(auth);
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
	
	public String getAuthor(){
		return this.author;
	}
	public void setAuthor(String newAuthor){
		this.author = newAuthor;
	}
	
	@Override
	public double calculateTax() {
		return this.getPrice()*taxRate;
	}

}
