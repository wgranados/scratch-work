/** Name: William Granados
 *  Date: 06/11/14
 *  Purpose: Creates a mathematical vector with varying functions
 * */

public class Vector2<N extends Number> {
	
	private double xMagnitude,yMagnitude,magnitude;
	private double x1, x2, y1, y2;
	
	/** Creates a new vector with the given x-component and y-component
	 * @param x1 x-coordinate of first point
	 * @param y1 y-coordinate of first point
	 * @param x2 x-coordinate of second point
	 * @param y2 y-coordinate of second point
	 * */
	public Vector2(N x1, N y1,N x2, N y2){
		this.x1 = x1.doubleValue();
		this.x2 = x2.doubleValue();
		this.y1 = y1.doubleValue();
		this.y2 = y2.doubleValue();
		// calculates the magnitude of the vector x and y component
		this.xMagnitude = x1.doubleValue() - x2.doubleValue();
		this.yMagnitude = y1.doubleValue() - y2.doubleValue();
		// the magnitude of the vector is specified as the square root of the x-component squared plus the y-component squared
		this.magnitude =  Math.sqrt(this.xMagnitude*this.xMagnitude+this.yMagnitude*this.yMagnitude);
	}
	/** Creates a new vector with the given x-component and y-component relative to the origin
	 * @param x x-component value
	 * @param y y-component value 
	 * */
	public Vector2(N x, N y){
		this.xMagnitude = x.doubleValue();
		this.yMagnitude = y.doubleValue();
		this.x1 = 0;
		this.x2 = x1 + x.doubleValue();
		this.y1 = 0;
		this.y2 = y1 + y.doubleValue();
		// the magnitude of the vector is specified as the square root of the x-component squared plus the y-component squared
		this.magnitude =  Math.sqrt(this.xMagnitude*this.xMagnitude+this.yMagnitude*this.yMagnitude);
	}
	
	/**Returns the magnitude of the vector*/
	public double vectorMagnitude(){
		return this.magnitude;
	}
	/**Returns the magnitude for the x-coordinate of the vector*/
	public double getXMagnitude(){
		return this.xMagnitude;
	}
	/**Returns the magnitude for the y-coordinate of the vector*/
	public double getYMagnitude(){
		return this.yMagnitude;
	}
	/**Returns the starting x coordinate at the beginning of the vector*/
	public double getX1Coordinate(){
		return this.x1;
	}
	/**Returns the ending x coordinate at the end of the vector*/
	public double getX2Coordinate(){
		return this.x2;
	}
	/**Returns the beginning y coordinate at the beginning of the vector*/
	public double getY1Coordinate(){
		return this.y1;
	}
	/**Returns the ending y coordinate at the end of the vector*/
	public double getY2Coordinate(){
		return this.y2;
	}
	/**Recalculates the head of the vector relative to the first point*/
	public void updateEndingCoordinates(){
		// the final coordinates are equal to the the the displacement
		// in their corresponding component.
		this.x2 =  this.x1 + this.xMagnitude;
		this.y2 =  this.y1 + this.yMagnitude;
	}
	
	/**Adds the vector specified to the current vector and returns a new vector with its starting position at the origin
	 * @param v vector*/
	public Vector2<Double> add(Vector2<N> v){
		Vector2<Double> newVector = new Vector2<>(this.xMagnitude+v.xMagnitude,this.yMagnitude+v.yMagnitude);
		newVector.updateEndingCoordinates();
		return newVector;
	}
	/**Adds the negative of the vector specified to the current vector and returns a new vector with its starting position at the origin
	 * @param v vector*/
	public Vector2<Double> sub(Vector2<N> v){
		Vector2<Double> newVector = new Vector2<>(this.xMagnitude+(-v.xMagnitude),this.yMagnitude+(-v.yMagnitude));
		newVector.updateEndingCoordinates();
		return newVector;
	}
	/**Multiplies the current vector by the scale specified and returns a new vector with its starting position at the origin
	 * @param s scalar*/
	public Vector2<Double> mul(N s){
		Vector2<Double> newVector = new Vector2<>(this.xMagnitude*s.doubleValue(),this.yMagnitude*s.doubleValue());
		newVector.updateEndingCoordinates();
		return newVector;
	}
	/**Returns the dot product of the current vector with the specified vector
	 * @param v vector*/
	public double dot(Vector2<N> v){
		return this.xMagnitude*v.xMagnitude + this.yMagnitude*v.yMagnitude;
	}
	/**Returns a summary of the current vector*/
	public String toString(){
		return "Magnitude:"+this.magnitude + " [ X:" + this.xMagnitude + " , Y:" + this.yMagnitude + " ]";
	}
}
