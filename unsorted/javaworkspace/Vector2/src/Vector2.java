
public class Vector2<N extends Number> {
	private double x,y,magnitude;
	/** Creates a new vector with the given x-component and y-component
	 * @param x x-component value
	 * @param y y-component value 
	 * */
	public Vector2(N x, N y){
		this.x = x.doubleValue();
		this.y = y.doubleValue();
		// the magnitude of the vector is specified as the square root of the x-component squared plus the y-component squared
		this.magnitude =  Math.sqrt(this.x*this.x+this.y*this.y);
	}
	
	/**Returns the magnitude of the vector*/
	public double magnitude(){
		return this.magnitude;
	}
	/**Adds the vector specified to the current vector
	 * @param v vector*/
	public Vector2<Double> add(Vector2<Double> v){
		Vector2<Double> newVector = new Vector2<>(this.x+v.x,this.y+v.y);
		return newVector;
	}
	/**Adds the negative of the vector specified to the current vector
	 * @param v vector*/
	public Vector2<Double> sub(Vector2<Double> v){
		Vector2<Double> newVector = new Vector2<>(this.x+(-v.x),this.y+(-v.y));
		return newVector;
	}
	/**Multiplies the current vector by the scale specified
	 * @param s scalar*/
	public Vector2<Double> mul(N s){
		Vector2<Double> newVector = new Vector2<>(this.x*s.doubleValue(),this.y*s.doubleValue());
		return newVector;
	}
	/**Returns the dot product of the current vector with the specified vector
	 * @param v vector*/
	public double dot(Vector2<Double> v){
		return this.x*v.x + this.y*v.y;
	}
	/**Returns a summary of the current vector*/
	public String toString(){
		return magnitude + " [ " + this.x + " , " + this.y + " ]";
	}
}

class test{
	public static void main(String[]args){
		Vector2<Double> v1 = new Vector2<Double>(5.0,5.0);
		Vector2<Double> v2 = new Vector2<Double>(5.0,5.0);
		System.out.println(v1.add(v2));
	}
}
