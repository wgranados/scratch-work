public class Employee implements Comparable<Employee>
{
  private String firstName;
  private String lastName;
   
  private int birthYear;
  public Employee(String f,String l,int year){
    firstName = f; lastName = l; birthYear = year;
  }

  public String getFirstName(){
	  return firstName; 
  }
  public String getLastName(){
	  return lastName;
  }
  public int getBirthYear(){
	  return birthYear;
  }
  
  public String toString(){
	  return "First name: " + firstName + ", Last name: " + lastName + ", Birth year: " + birthYear;
  }
  
  public int compareTo( Employee other ) {
	 int compare1 = getLastName().compareTo(other.getLastName());
	 int compare2 = getFirstName().compareTo(other.getFirstName());
	 int compare3 = getBirthYear() - other.getBirthYear();
	 return (compare1 != 0) ? compare1:( (compare2 != 0) ? compare2:compare3); 
  }
  
}