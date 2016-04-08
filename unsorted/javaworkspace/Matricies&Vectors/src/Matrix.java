/** Name: William Granados
 *  Date: 06/11/14
 *  Purpose: Creates a mathematical matrix with varying functions
 * */

public class Matrix{
	
	private int rows,columns;
	private Integer[][] M;
	
	/**Creates a matrix of size m x n with default value of 0 in its indices
	 * @m number of rows
	 * @n numbers of columns
	 **/
	public Matrix(int m, int n){
		this.rows = m;
		this.columns = n;
		this.M = new Integer[this.rows][this.columns];
		for(int r = 0; r < this.rows;r++){
			for(int c = 0; c < this.columns;c++){
				this.M[r][c] = 0;
			}
		}
	}
	/**Creates a new matrix that possess the same attributes of the integer array passed
	 * @M Integer array 
	 * */
	public Matrix(Integer[][] M){
		this.M = M;
		this.rows = M.length;
		this.columns = M[0].length;
		for(int r = 0; r < this.rows;r++){
			for(int c = 0; c < this.columns;c++){
				this.M[r][c] = M[r][c];
			}
		}
	}
	/**Returns the number of rows present in the current matrix*/
	public int getRows(){
		return this.rows;
	}
	/**Returns the number of columns present in the current matrix*/
	public int getColumns(){
		return this.columns;
	}
	/**Returns the value in the matrix respective to the indices specified
	 * @i ith row in matrix
	 * @j jth column in matrix
	 * */
	public Integer getIndex(int i, int j){
		return this.M[i][j];
	}
	/**Sets the respective index specified in the matrix
	 * @i ith row in matrix
	 * @j jth column in matrix
	 * @val value to be assigned
	 * */
	public void setIndex(int i, int j, Integer val){
		this.M[i][j] = val;
	}
	/**Adds to the respective index specified in the matrix
	 * @i ith row in matrix
	 * @j jth column in matrix
	 * @val value to be added
	 * */
	public void addIndex(int i, int j, Integer val){
		this.M[i][j] += val;
	}
	
	/**Returns a new matrix of size m x n that is the sum of the current matrix and specified matrix if and only if the dimensions of both matricies are the same, otherwise returns null
	 * @M2 matrix to be added to the current matrix*/
	public Matrix add(Matrix M2){
		if(M2.getRows() != this.rows || M2.getColumns() != this.columns)
			return null;
		Matrix M3 = new Matrix(this.rows,this.columns);
		// sets the M3[r][c] the sum of M[r][c]+M2[r][c] 
		for(int r = 0; r < this.rows;r++){
			for(int c = 0; c < this.columns;c++){
				M3.setIndex(r, c, this.M[r][c].intValue() + M2.getIndex(r, c));
			}
		}
		return M3;
	}
	/**Returns a new matrix of size m x n that is the differences of the current matrix and specified matrix if and only if the dimensions of both matricies are the same, otherwise returns null
	 * @M2 matrix to be subtracted to the current matrix*/
	public Matrix subtract(Matrix M2){
		if(M2.getRows() != this.rows || M2.getColumns() != this.columns)
			return null;
		Matrix M3 = new Matrix(this.rows,this.columns);
		for(int r = 0; r < this.rows;r++){
			for(int c = 0; c < this.columns;c++){
				M3.setIndex(r, c,this.M[r][c].intValue()  + (-M2.getIndex(r, c)));// you never subtract vectors, you simply be add the negative of a vector
			}
		}
		return M3;
	}
	/**Returns a new matrix of size m x n where each index is the product of the current index and specified scalar value
	 * @value scalar value to be used during multiplcation process
	 * */
	public Matrix scalarMult(int value){
		Matrix M3 = new Matrix(this.rows,this.columns);
		for(int r = 0; r < this.rows;r++){
			for(int c = 0;c < this.columns;c++){
				M3.setIndex(r,c, this.M[r][c]*value);
			}
		}
		return M3;
	}
	/**Returns a new matrix of size m x p if and only if the columns for the current matrix is the same as the rows for the correponding matrix
	 * @M2 matrix to be used during calculation */
	public Matrix times(Matrix M2){
		if(this.columns != M2.getRows())return null;
		Matrix M3 = new Matrix(this.rows,M2.getColumns());
		// dimensios are m x n x n x p = m x p
		for(int r = 0; r < this.rows;r++){
			for(int c = 0; c < M2.getColumns();c++){
				for(int k = 0; k < this.rows;k++){
					M3.addIndex(r, c, this.M[r][k].intValue()*M2.getIndex(k, c));
				}
			}
		}
		return M3;
	}
	/**Returns a new matrix of size mx1 where index is the dot product of the corresponding indicies in the matrix and vector 
	 * @v vector to be used during calculations*/
	public Matrix times(Vector2<Integer>v){
		if(this.rows != 2)return null;
		else{
			Integer[][] arr = {{(int)v.getXMagnitude()},{(int)v.getYMagnitude()}};
			Matrix M2 = new Matrix(arr);
			return times(M2);
		}
	}
	/**Returns a new matrix matrix that is the transposition of the current matrix*/
	public Matrix transpose(){
		Matrix M2 = new Matrix(this.columns,this.rows);
		for(int r = 0;r < this.rows;r++){
			for(int c = 0; c < this.columns;c++){
				M2.setIndex(c, r, this.M[r][c]);
			}
		}
		return M2;
	}
	/**Returns a new identity matrix of size n x n
	 * @n dimensions of identity matrix*/
	public Matrix identity(int n){
		Matrix M2 = new Matrix(n,n);
		for(int r = 0; r < n;r++){
			for(int c = 0; c < n;c++){
				if(r == c)
				M2.setIndex(r, c, 1);
			}
		}
		return M2;
	}
	/**Returns the string representation of the current matrix*/
	public String ToString(){
		String toReturn = "";
		for(int r = 0; r < this.rows;r++){
			for(int c = 0;c < this.columns;c++){
				toReturn += String.valueOf(this.M[r][c]) + " ";
			}
			toReturn += "\n";
		}
		return toReturn;
	}
}
