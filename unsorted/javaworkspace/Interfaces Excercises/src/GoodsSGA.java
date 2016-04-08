public abstract class GoodsSGA implements Taxable
{
  private String description;
  private double price;
  private int quantity;

  public GoodsSGA( String des, double pr, int quant ){
	this.setDescription(des);
	this.setPrice(pr);
	this.setQuantity(quant);
  }
  public abstract void test();
  
  String getDescription(){
	  return this.description;
  }
  void setDescription(String newDescription){
	  this.description = newDescription;
  }
  
  double getPrice(){
    return price;
  }
  void setPrice( double newPrice){
    this.price =  newPrice;
  }

  int getQuantity(){
    return quantity;
  }
  void setQuantity ( int newQuantity ){
    this.quantity =  newQuantity;
  }
       
  public String toString(){
    return "Item: " + description + "\nQuantity: " + quantity + "\nPrice: " + price ;
  }
}