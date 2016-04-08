class Diamond implements Comparable<Diamond>
{
  private String stockNumber;
  
  private double carot;       /* size of the diamond */
  private String clarity;  /* clarity grade of the diamond */
  private char color;      /* color grade D-Z */
  private String cut;      /* name of the cut */
  
  private String rnk[] = {"FL", "IF", "VVS1", "VVS2", "VS1", "VS2", "SI1", "SI2", "I1", "I2", "I3"};
  
  public Diamond( String s, double car, String clar, char col, String ct ){
    stockNumber = s; 
    carot = car; 
    clarity = clar;
    color = col;
    cut = ct;  
  }
  public String getStock(){ 
	  return stockNumber; 
  }
  public double getCarot(){
	  return carot;
  }
  public String getClarity(){
	  return clarity; 
  }
  public char   getColor(){
	  return color;
  }
  public String getCut(){ 
	  return cut;
  }
  
  public String toString(){ 
	  return "Carot: " + carot + ", Clarity: " + clarity + ", color: " + color + ", cut: " + cut;
  }
  
  
  public int linearSearch(String arr[], String key){
	  for(int i = 0; i < arr.length;i++)
		  if(key.equals(arr[i]))return i;
	  return -arr.length;
  }
  public int compareTo( Diamond other ) {
	   int compare1 = -((int)getCarot()-(int)other.getCarot());
	   int compare2 = -(linearSearch(rnk,getClarity()) - linearSearch(rnk,other.getClarity()));
	   int compare3 = -(getColor()-other.getColor());
	   return (compare1 != 0) ? compare1: ( (compare2 != 0) ? compare2:compare3);
  }
  
}