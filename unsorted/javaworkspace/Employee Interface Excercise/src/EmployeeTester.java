import java.util.Arrays;

class EmployeeTester
{
  public static void main ( String[] args )
  {
    Employee[] workers = new Employee[12];
    
    workers[0] = new Employee( "Fred", "Adams",  1963);
    workers[1] = new Employee( "John", "Adams",  1959);
    workers[2] = new Employee( "Elmer", "Adams",  1976);
    workers[3] = new Employee( "Nancy", "Devon",  1963);
    workers[4] = new Employee( "Andrew", "Lewis",  1983);
    workers[5] = new Employee( "Douglas", "Page",  1981);
    workers[6] = new Employee( "Donald", "Wolder",  1963);
    workers[7] = new Employee( "Henry", "Wolder",  1972);
    workers[8] = new Employee( "Robert", "Wolder",  1959);
    workers[9] = new Employee( "Howard", "Cohen",  1933);
    workers[10] = new Employee( "Howard", "Cohen",  1958);
    workers[11] = new Employee( "Donald", "Rice",  1935);
     
    Arrays.sort( workers );
    
    for ( int j=0; j<workers.length; j++ )
      System.out.println( workers[j].toString() );
  }  

}