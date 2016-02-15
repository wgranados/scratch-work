/** Name: William Granados
 *  Date: 06/11/14
 *  Purpose: Testing methods in classes
 * */
public class Test {
	public static void main(String[]args){
		matrixTest();
		vector2Test();
	}
	/**To test methods in the matrix class*/
	public static void matrixTest(){
		Matrix M1,M2;
		// addition test
		Integer[][] arr = { {2,2},{2,2}};
		M1 = new Matrix(arr);
		M2 = new Matrix(arr);
		System.out.println(M1.add(M2).ToString());
		// subtraction test
		M1 = new Matrix(arr);
		M2 = new Matrix(arr);
		System.out.println(M1.subtract(M2).ToString());
		// scalar mult test
		System.out.println(M1.scalarMult(10).ToString());
		// matrix times test
		Integer[][] arr3 = { {1,2},{3,4}};
		Integer[][] arr4 = { {5,6,7},{8,9,10}};
		M1 = new Matrix(arr3);
		M2 = new Matrix(arr4);
		System.out.println(M1.times(M2).ToString());
		// vector times test
		Integer[][] arr5 = { {1,2},{3,4}};
		M1 = new Matrix(arr5);
		Vector2<Integer> v = new Vector2<Integer>(5,6);
		System.out.println(M1.times(v).ToString());
		// transpose teste
		Integer[][] arr6 = { {0,1},{1,0}};
		M1 = new Matrix(arr6);
		System.out.println(M1.transpose().ToString());
		// identity test
		System.out.println(M1.identity(5).ToString());
	}
	/**To test methods in the vector2 class*/
	public static void vector2Test(){
		// addition test
		Vector2<Integer> v = new Vector2<Integer>(0,1);
		Vector2<Integer> v2 = new Vector2<Integer>(1,0);
		System.out.println(v.add(v2).toString());
		// subtract test
		System.out.println(v.sub(v2).toString());
		// multiply test
		System.out.println(v.mul(5).toString());
		// dot product test
		System.out.println(v.dot(v2));
	}
}
