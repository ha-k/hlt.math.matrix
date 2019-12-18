// FILE. . . . . d:/hak/hlt/src/hlt/math/matrix/sources/Matrix.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hak-Laptop
// STARTED ON. . Fri Nov 15 13:09:37 2019

/**
 * @version     Last modified on Sun Dec 15 12:09:10 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

package hlt.math.matrix;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.matrix</tt>
 * documentation listing</a>
 */

import hlt.language.tools.Misc;
// import hlt.math.fuzzy.StandardFuzzyAlgebra;

/**
 * This defines the class <tt>Matrix</tt> that implements basic
 * two-dimensional linear algebra in the form of a collection of
 * operations defined on a <tt>Matrix</tt> class represented as a 2D
 * array of <tt>double</tt>s.  <p/>
 *
 * <span style="font-family:arial,helvetica;">
 * <a name="contents"><b>Contents</b></a> <small>(<a href="000StartHere.html"><tt>package hlt.math.matrix</tt>
 * documentation listing</a>)</small> 
 * <ul>
 * <li><a href="#constructors">Object Constructors</a></li>
 * <p/>
 * <li><a href="#components">Object Components</a></li>
 *     <ul>
 *     <li><a href="#access">Component-Access Methods</a></li>
 *     <li><a href="#setget">Component-Setter/Getter Methods</a></li>
 *     </ul>
 * <p/>
 * <li><a href="#objectmethods">Object Methods</a></li>
 *     <ul>
 *     <li><a href="#properties">Object Boolean Properties</a></li>
 *     <li><a href="#square">Square-Matrix Specific Methods</a></li>
 *     <li><a href="#vectors">Vector-Specific Methods</a></li>
 *     <li><a href="#io">I/O Object Methods</a></li>
 *     </ul>
 * <p/>
 * <li><a href="#classmethods">Static Methods</a></li>
 *     <ul>
 *     <li><a href="#useful">Useful Static Matrix Methods</a></li>
 *     <li><a href="#checks">Consistency Checking</a></li>
 *     <li><a href="#printing">Printing Control</a></li>
 *     <li><a href="#precision">Precision Control</a></li>
 *     <li><a href="#random">Random-Value Generation Control</a></li>
 *     <li><a href="#miscio">Miscellaneous static I/O tools</a></li>
 *     </ul>
 * </ul>
 * </span>
 *
 * @see         NumberAlgebra
 */
public class Matrix
{
  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="constructors" href="#contents">Object Constructors</a>
   * </span></h2>
   */

  /**
   * Construct a vacuous <tt>Matrix</tt> using the default entry algebra
   * (<tt>StandardAlgebra</tt>).
   */
  public Matrix ()
  {
    super();
  }

  /**
   * Construct a vacuous <tt>Matrix</tt> setting the number algebra to
   * the given <tt>NumberAlgebra</tt>.
   */
  protected Matrix (NumberAlgebra algebra)
  {
    NumberAlgebra.setCurrentAlgebra(algebra);
  }

  /**
   * Construct a <tt>rows</tt>-by-<tt>cols</tt> <tt>Matrix</tt> of
   * <tt>0.0</tt>'s.
   */
  public Matrix (int rows, int cols)
  {
    if (rows <= 0 || cols <= 0)
      throw new RuntimeException("Illegal matrix dimensions: ("+
				 rows+","+cols+")");
    this.rows = rows;
    this.cols = cols;
    data = new double[rows][cols];
  }

  /**
   * Construct a square <tt>Matrix</tt> of order <tt>order</tt> with all
   * entries <tt>0.0</tt>'s.
   */
  public Matrix (int order)
  {
    if (order <= 0)
      throw new RuntimeException("Illegal square matrix order: ("+
				 order+")");
    this.rows = order;
    this.cols = order;
    data = new double[order][order];
  }

  /**
   * Construct a new <tt>Matrix</tt> copying the given <tt>data</tt> 
   * array.
   */
  public Matrix (double[][] data)
  {
    this(data,false);
  }

  /**
   * Construct a new <tt>Matrix</tt> sharing the given <tt>data</tt>
   * array if <tt>inPlace</tt> is <tt>true</tt>, and copying it
   * otherwise.
   */
  public Matrix (double[][] data, boolean inPlace)
  {
    if (inPlace)
      setData(data);
    else
      {
	rows = data.length;
	cols = data[0].length;
	this.data = copyData(data);
      }
  }

  /**
   * Construct a new <tt>Matrix</tt> from the data array of the given
   * <tt>Matrix</tt>.
   */
  protected Matrix (Matrix M)
  {
    this(M.data);
  }

  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="components" href="#contents">Object Components</a>
   * </span></h2>
   */
  
  /**
   * The number of rows of this <tt>Matrix</tt>.
   */
  protected int rows;
  
  /**
   * The number of columns of this <tt>Matrix</tt>.
   */
  protected int cols;

  /**
   * This is the <tt>rows</tt>-by-<tt>cols</tt> array containing the
   * entries of this <tt>Matrix</tt>.
   */
  protected double[][] data;

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="access" href="#contents">Component-Access Methods</a>
   * </span></h4>
   */
  
  /**
   * Return the <tt>rank</tt> of this <tt>Matrix</tt>. It is always the
   * same as rows, but used only for square-matrix methods
   */
  public final int rank ()
  {
    return rows;
  }
  
  /**
   * Return the <tt>order</tt> of this <tt>Matrix</tt>. It is always the
   * same as rows, but used only for square-matrix methods
   */
  public final int order ()
  {
    return rows;
  }
  
  /**
   * Return the number of rows of this <tt>Matrix</tt>.
   */
  public final int rows ()
  {
    return rows;
  }
  
  /**
   * Return the number of columns of this <tt>Matrix</tt>.
   */
  public final int cols ()
  {
    return cols;
  }

  /**
   * Return the <tt>data</tt> array of this <tt>Matrix</tt>.
   */
  public final double[][] data ()
  {
    return data;
  }
  
  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="setget" href="#contents">Component-Setter/Getter Methods</a>
   * </span></h4>
   */

  /**
   * Set the (<tt>i</tt>,<tt>j</tt>) entry of this <tt>Matrix</tt> to the
   * given <tt>value</tt> and return the previous value that was
   * there. Throws a <tt>RuntimeException</tt> for indices out of bounds.
   * <b>N.B.:</b> Matrix indices are counted from <tt>1</tt>.
   */
  public double set (int i, int j, double value)
  {
    // check for legal entry and adjust i and j to start from 0 in data in
    // the code following this
    checkLegalEntry(i--,j--);

    return safeDataUpdate(i,j,truncate(value));
  }

  /**
   * Set the (<tt>row</tt>,<tt>col</tt>) entry of this <tt>Matrix</tt>
   * <tt>data</tt> array to the given <tt>value</tt> and return the
   * previous value that was there. <b>N.B.</b> This counts <tt>row</tt>
   * and <tt>col</tt> from <tt>0</tt> and assumes indexing is safe
   * (<i>e.g.</i>, after <tt>checkLegalEntry</tt>).
   */
  protected final double safeDataUpdate (int row, int col, double value)
  {
    double old = data[row][col];
    data[row][col] = value;
    return old;
  }

  /**
   * This is an in-place pointwise update that modifies each entry of
   * this matrix to the value of the corresponding entry in the given
   * matrix.
   */
  public Matrix update (Matrix M)
  {
    if (rows != M.rows || cols != M.cols)
      throw new RuntimeException("Incompatible matrix dimensions: <"
				 +rows+","+cols+"> =/= <"+M.rows+","+M.cols+">");

    for (int i = 0; i < rows; i++)
      for (int j = 0; j < cols; j++)
	data[i][j] = M.data[i][j];

    return this;
  }

  /**
   * Return the value of in this <tt>Matrix</tt> are entry indices
   * (<tt>i</tt>,<tt>j</tt>). Throws a <tt>RuntimeException</tt> for
   * indices out of bounds.  <b>N.B.:</b> Matrix indices are counted from
   * <tt>1</tt>.
   */
  public final double get (int i, int j)
  {
    // check for legal entry and adjust i and j to start from 0 in data in
    // the code following this
    checkLegalEntry(i--,j--);
    return data[i][j];
  }

  /**
   * Modifies this matrix to have the given two-dimensional data array
   * of <tt>double</tt>s and corresponding numbers of rows and columns.
   * If the array <tt>null</tt> a runtime exception is thrown.
   */
  public Matrix setData (double[][] data)
  {
    if (data == null)
      throw new RuntimeException
	("Attempt to initialize a matrix with a null data array");

    rows = data.length;
    cols = data[0].length;
    this.data = data;
    return this;
  }

  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="objectmethods" href="#contents">Object Methods</a>
   * </span></h2>
   */

  /**
   * Return the sum of its <tt>double</tt> arguments as their
   * <tt>sum</tt> according to the current number algebra in effect.
   */
  static public double sum (double x, double y)
  {
    return NumberAlgebra.getCurrentAlgebra().sum(x,y);
  }

  /**
   * Return the product of its <tt>double</tt> arguments as their
   * <tt>product</tt> according to the current number algebra in effect.
   */
  static public double product (double x, double y)
  {
    return NumberAlgebra.getCurrentAlgebra().product(x,y);
  }

  /**
   * Return the difference of its <tt>double</tt> arguments as their
   * <tt>difference</tt> according to the current number algebra in
   * effect.
   */
  static public double difference (double x, double y)
  {
    return NumberAlgebra.getCurrentAlgebra().difference(x,y);
  }

  /**
   * Return the negation of its <tt>double</tt> argument as its
   * <tt>negation</tt> according to the current number algebra in
   * effect.
   */
  static public double negation (double x)
  {
    return NumberAlgebra.getCurrentAlgebra().negation(x);
  }

  /**
   * Return a new <tt>Matrix</tt> equal to <tt>this+M</tt>.
   */
  public Matrix plus (Matrix M)
  {
    return new Matrix().setData(dataPlus(data,M.data));
  }

  protected final double[][] dataPlus (double[][] A, double[][] B)
  {
    int rows = A.length;
    int cols = A[0].length;
    verifyCompatibleDimension(rows,cols,B.length,B[0].length);

    double[][] newData = new double[rows][cols];

    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	newData[row][col] = sum(A[row][col],
				B[row][col]);
    
    return newData;
  }

  /**
   * Modify in place the entries of <tt>this</tt> to those of
   * <tt>this</tt> plus <tt>M</tt> and return <tt>this</tt>.
   */
  public Matrix i_plus (Matrix M)
  {
    verifyCompatibleDimension(M.rows,M.cols,rows,cols);

    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	data[row][col] = sum(data[row][col],
			     M.data[row][col]);

    return this;
  }

  /**
   * Return a new <tt>Matrix</tt> equal to <tt>this</tt> times
   * <tt>M</tt> (does not modify <tt>this</tt>).
   */
  public Matrix times (Matrix M)
  {
    if (cols != M.rows)
      throw new RuntimeException("Cannot multiply a "+rows+"x"+cols+
				 " matrix by a "+M.rows+"x"+M.cols+" matrix");

    Matrix result = new Matrix(rows,M.cols);

    for (int row = 0; row < rows; row++)
      for (int col = 0; col < M.cols; col++)
	{
	  double entry = 0.0;
	  for (int k = 0; k < cols; k++)
	    entry = sum(entry,
			product(data[row][k],
				M.data[k][col]));

	  // N.B: since multiplying two truncated entries may exceed the
	  // truncation limit, the resulting matrix entry must be truncated
	  result.data[row][col] = truncate(entry);
	}

    return result;
  }

  /**
   * Modify in place the entries of <tt>this</tt> to those of
   * <tt>this</tt> times <tt>M</tt> and return <tt>this</tt>.
   */
  public Matrix i_times (Matrix M)
  {
    return setData(this.times(M).data);
  }

  /**
   * Return a new <tt>Matrix</tt> equal to <tt>this-M</tt>.
   */
  public Matrix minus (Matrix M)
  {
    verifyCompatibleDimension(M.rows,M.cols,rows,cols);

    Matrix result = new Matrix(rows,cols);
    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	result.data[row][col] = difference(data[row][col],
					   M.data[row][col]);

    return result;
  }

  /**
   * Modify this <tt>Matrix</tt> to <tt>this-M</tt> and return
   * <tt>this</tt>
   */
  public Matrix i_minus (Matrix M)
  {
    verifyCompatibleDimension(M.rows,M.cols,rows,cols);

    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	data[row][col] = difference(data[row][col],
				    M.data[row][col]);

    return this;
  }

  /**
   * Return a new <tt>Matrix</tt> equal to <tt>-this</tt>.
   */
  public Matrix minus ()
  {
    Matrix result = new Matrix(rows,cols);

    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	result.data[row][col] = negation(data[row][col]);

    return result;
  }

  /**
   * Modify this <tt>Matrix</tt> to <tt>-M</tt> and return
   * <tt>this</tt>
   */
  public Matrix i_minus ()
  {
    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	data[row][col] = negation(data[row][col]);

    return this;
  }

  /**
   * Return a new <tt>Matrix</tt> each entry of which is equal the
   * corresponding entry in <tt>this</tt> multiplied by <tt>scale</tt>.
   */
  public Matrix scale (double factor)
  {
    Matrix result = new Matrix(rows,cols);

    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	  // N.B: since multiplying a truncated entry by a double may
	  // exceed the truncation limit, the resulting matrix entry must
	  // be truncated
	result.data[row][col] = truncate(factor*data[row][col]);

    return result;
  }

  /**
   * Modify this <tt>Matrix</tt> to <tt>factor*this</tt> and return
   * <tt>this</tt>
   */
  public Matrix i_scale (double factor)
  {
    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	  // N.B: since multiplying a truncated entry by a double may
	  // exceed the truncation limit, the resulting matrix entry must
	  // be truncated
	data[row][col] = truncate(factor*data[row][col]);

    return this;
  }

  /**
   * Return <tt>true</tt> iff <tt>this</tt> matrix and </tt>M</tt> are
   * entry-wise equal.
   */
  public final boolean equal (Matrix M)
  {
    verifyCompatibleDimension(M.rows,M.cols,rows,cols);

    return equalData(data,M.data);
  }

  /**
   * Swap rows <tt>row</tt> and <tt>col</tt> of <tt>this</tt>
   * <tt>Matrix</tt>.
   */
  public void swapRows (int row, int col)
  {
    double[] tmp = data[row];
    data[row] = data[col];
    data[col] = tmp;
  }

  /**
   * Swap columns <tt>row</tt> and <tt>col</tt> of <tt>this</tt>
   * <tt>Matrix</tt>.
   */
  public void swapCols (int row, int col)
  {
    double tmp;

    for (int k = 0; k < data.length; k++)
      {
	tmp = data[k][row];
	data[k][row] = data[k][col];
	data[k][col] = tmp;
      }
  }

  /**
   * Return a new <tt>Matrix</tt> that is the transpose of this one.
   */
  public Matrix transpose ()
  {
    return new Matrix().setData(dataTranspose(data));
  }

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="properties" href="#contents">Object Boolean Properties</a>
   * </span></h4>
   */


  /**
   * Return <tt>true</tt> if this <tt>Matrix</tt> is pointwise-equal to
   * the given one.
   */
  public boolean equals (Matrix B)
  {
    Matrix A = this;    

    if (A == B)
      return true;

    if (A.data == B.data)
      return true;

    if (B.rows != A.rows || B.cols != A.cols)
      throw new RuntimeException("Incompatible matrix dimensions: <"
				 +A.rows+","+A.cols+"> =/= <"+B.rows+","+B.cols+">");


    for (int i = 0; i < rows; i++)
      for (int j = 0; j < cols; j++)
	if (A.data[i][j] != B.data[i][j])
	  return false;

    return true;
  }

  /**
   * Return <tt>true</tt> iff this matrix has no data array.
   */
  public final boolean isVacuous ()
  {
    return data == null;
  }

  /**
   * Return <tt>true</tt> iff <tt>this</tt> <tt>Matrix</tt> is
   * degenerate (<i>i.e.</i>, one of its rows or columns has only
   * <tt>0.0</tt>).
   */
  public boolean isDegenerate ()
  {
    return (degenerateRow() != -1) || (degenerateCol() != -1);
  }
  
  /**
   * Return the row index of a row that contains only <tt>0.0</tt> if
   * there exists one; <tt>-1</tt> otherwise.
   */
  public int degenerateRow ()
  {
    for (int row = 0; row < rows; row++)
      {
	int col = 0;
	boolean rowIsAllZero = true;
	
	while (rowIsAllZero && col < cols)
	  {
	    rowIsAllZero = (data[row][col] == 0.0);
	    col++;
	  }

	if (rowIsAllZero)
	  return row;
      }	

    return -1;
  }
  
  /**
   * Return the column index of a column that contains only
   * <tt>0.0</tt> if there exists one; <tt>-1</tt> otherwise.
   */
  public int degenerateCol ()
  {
    for (int col = 0; col < cols; col++)
      {
	int row = 0;
	boolean colIsAllZero = true;
	
	while (colIsAllZero && row < rows)
	  {
	    colIsAllZero = (data[row][col] == 0.0);
	    row++;
	  }

	if (colIsAllZero)
	  return col;
      }	

    return -1;
  }
  
  /**
   * Return <tt>true</tt> iff all entries are <tt>0.0</tt>.
   */
  public boolean isZeroMatrix ()
  {
    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	if (data[row][col] != 0.0)
	  return false;

    return true;
  }

  /* ************************************************************************ */

  /**
   * Return <tt>true</tt> iff this is a row-stochastic matrix;
   * <i>i.e.</i>, all the rows are in <tt>[0.0,1.0]</tt> and their
   * components add up to <tt>1.0</tt>.
   */
  public final boolean isRowStochastic ()
  {
    return isRowStochastic(data);
  }

  /**
   * Modifies this non-negative <tt>matrix</tt> in place so that all its
   * rows have non-negative entries that add up to <tt>1</tt>. It does
   * so by normalizing each entry by dividing it by the sum of all the
   * entries on its row.
   */
  public final void makeRowStochastic ()
  {
    for (int row = 0; row < rows; row++)
      normalizeRow(row);
  }

  /**
   * Return <tt>true</tt> iff this is a column-stochastic matrix;
   * <i>i.e.</i>, all the columns are in <tt>[0.0,1.0]</tt> and their
   * components add up to <tt>1.0</tt>.
   */
  public final boolean isColumnStochastic ()
  {
    return isColumnStochastic(data);
  }

  /**
   * Return <tt>true</tt> iff this is a doubly-stochastic matrix;
   * <i>i.e.</i>, that it all the rows and all the columns have only
   * entries in <tt>[0.0,1.0]</tt> that add up to <tt>1.0</tt> in each
   * dimension.
   */
  public final boolean isDoublyStochastic ()
  {
    return isDoublyStochastic(data);
  }

  /**
   * Return <tt>true</tt> iff the given <tt>data</tt> array this is a
   * row-stochastic matrix; <i>i.e.</i>, all the rows are in
   * <tt>[0.0,1.0]</tt> and their components add up to <tt>1.0</tt>.
   */
  protected final boolean isRowStochastic (double[][] data)
  {
    int rows = data.length;
    int cols = data[0].length;

    for (int row = 0; row < rows; row++)
      {
	double rowSum = 0;
	
	for (int col = 0; col < cols; col++)
	  {
	    if (data[row][col] < 0.0 || data[row][col] > 1.0)
	      return false; // not within [0.0,1.0]

	    rowSum = sum(rowSum,
			 data[row][col]);
	  }

	if (truncate(rowSum) != 1.0)
	  return false;
      }

    // ok - we're good
    return true;	  
  }

  /**
   * Return <tt>true</tt> iff the given <tt>data</tt> array is
   * column-stochastic; <i>i.e.</i>, all the columns are in
   * <tt>[0.0,1.0]</tt> and their components add up to <tt>1.0</tt>.
   */
  protected final boolean isColumnStochastic (double[][] data)
  {
    int rows = data.length;
    int cols = data[0].length;

    for (int col = 0; col < cols; col++)
      {
	double colSum = 0;
	
	for (int row = 0; row < rows; row++)
	  {
	    if (data[row][col] < 0.0 || data[row][col] > 1.0)
	      return false; // not within [0.0,1.0]

	    colSum = sum(colSum,
			 data[row][col]);
	  }

	if (truncate(colSum) != 1.0)
	  return false;
      }

    // ok - we're good
    return true;	  
  }

  /**
   * Return <tt>true</tt> iff this is a doubly-stochastic data array;
   * <i>i.e.</i>, that it all the rows and all the columns have only
   * entries in <tt>[0.0,1.0]</tt> that add up to <tt>1.0</tt> in each
   * dimension.
   */
  protected final boolean isDoublyStochastic (double[][] data)
  {
    int rows = data.length;
    int cols = data[0].length;

    // as we compute for each row the sum of its entries we also
    // accumulate the sum on entries for each column and store it in
    // this array until we reach the last row
    double[] colSums = new double[rows];
    
    for (int row = 0; row < rows; row++)
      {
	double rowSum = 0;
	
	for (int col = 0; col < cols; col++)
	  {
	    if (data[row][col] < 0.0 || data[row][col] > 1.0)
	      return false; // not within [0.0,1.0]

	    rowSum = sum(rowSum,
			 data[row][col]);

	    colSums[row] = sum(colSums[row],
			       data[row][col]);
	  }

	if (rowSum != 1.0)
	  return false;
      }

    // if we're here, we need to check each column sums
    for (int row = 0; row < rows; row++)
      if (truncate(colSums[row]) != 1.0)
	return false;

    // ok - we're good
    return true;	  
  }

  /**
   * Modifies the <tt>row</tt>-th row of this <tt>Matrix</tt> by
   * normalizing each of its entries by dividing it by the sum of all
   * the entries on this row.  <b>N.B.: This does not works due to
   * rounding issue (see <tt>truncate</tt>).</b>
   */
  private final void normalizeRow (int row)
  {
    double rowSum = 0.0; // the sum of the entries on this row

    System.err.println();
    System.err.println("------------------------------------------------------------------------");
    System.err.println("Row sum ["+row+"]:");
    System.err.println("------------------------------------------------------------------------");
    System.err.println();

    for (int col = 0; col < cols; col++)
      {
	data[row][col] = truncate(data[row][col]);

	if (data[row][col] < 0.0)
	  throw new RuntimeException
	    ("Negative data array entry "+data[row][col]+" at indices "+
	     row+","+col+": can only make non-negative matrix row-stochastic.");

	System.err.print(" ");
	System.err.printf(floatFormatString(),data[row][col]);
	System.err.print(col<(cols-1)?"+":"");

	rowSum = sum(rowSum,
		     data[row][col]);
      }

    rowSum = truncate(rowSum);
    System.err.println(" ---> rowSum["+row+"] = "+rowSum);

    if (rowSum == 0.0)
      return; // then there is nothing to do

    // needed to sum the normalized row entries to offset rounding errors
    double normalizedRowSum = 0.0;
    double actualNormalizedRowSum = 0.0;

    for (int col = 0; col < cols; col++)
      {
	if (data[row][col] == rowSum)
	  { // this means that the row has only one non-zero at col
	    // so the normalized value at col is 1.00 (and all
	    // others still 0.0)
	    data[row][col] = normalizedRowSum = actualNormalizedRowSum = 1.0;
	    return;
	  }

	// dividing by the sum is safe since != 0.0:
	double normalizedEntry = truncate(data[row][col]/rowSum);

	System.err.print(" ");
	System.err.printf(floatFormatString(),normalizedEntry);
	System.err.print(col<(cols-1)?"+":"");

	if (col == lastNonZeroColInRow(row))
	  { // this is the last non-zero in this row: adjust to how
	    // close to 1.0: i.e., 1.0 - sum of all the previous non-0
	    // normalized entries!
	    data[row][col] = difference(1.0,
					actualNormalizedRowSum);
	    actualNormalizedRowSum = sum(actualNormalizedRowSum,
					 normalizedEntry);
	    normalizedRowSum = 1.0;
	  }
	else // otherwise, just normalize the entry:
	  {
	    actualNormalizedRowSum = sum(actualNormalizedRowSum,
					 normalizedEntry);
	    data[row][col] = normalizedEntry;
	  }
      }

    System.err.println(" ---> actualNormalizedRowSum["+row+"] = "+actualNormalizedRowSum);
    //    System.err.println(" ---> normalizedRowSum["+row+"] = "+normalizedRowSum);
    System.err.println();
    if (truncate(actualNormalizedRowSum) == 1.0)
      System.err.println("Actual normalized sum of row["+row+
			 "] is close enough to 1.0: so row["+row+
			 "] is deemed row-stochastic");
    else
      System.err.println("Actual normalized sum of row["+row+
			 "] is NOT close enough to 1.0: so row["+row+
			 "] is NOT row-stochastic");
    System.err.println("------------------------------------------------------------------------");
  }

  /**
   * Return the column index of the first non-zero entry in the given
   * <tt>row</tt>, or <tt>-1</tt> if there is none (<i>i.e.</i>, the row
   * contains only zero entries).
   */
  public final int firstNonZeroColInRow (int row)
  {
    int col = 0;

    while (col < cols && data[row][col] == 0.0)
      col++;    

    return (col == cols) ? -1 : col;
  }

  /**
   * Return the row index of the first non-zero entry in the given
   * <tt>col</tt>, or <tt>-1</tt> if there is none (<i>i.e.</i>, the
   * column contains only zero entries).
   */
  public final int firstNonZeroRowInColumn (int col)
  {
    int row = 0;

    while (row < rows && data[row][row] == 0.0)
      row++;    

    return (row == rows) ? -1 : row;
  }

  /**
   * Return the column index of the last non-zero entry in the given
   * <tt>row</tt>, or <tt>-1</tt> if there is none (<i>i.e.</i>, the row
   * contains only zero entries).
   */
  public final int lastNonZeroColInRow (int row)
  {
    int col = cols-1;

    while (col > 0 && data[row][col] == 0.0)
      col--;    

    return col;
  }

  /**
   * Return the row index of the last non-zero entry in the given
   * <tt>col</tt>, or <tt>-1</tt> if there is none (<i>i.e.</i>, the
   * column contains only zero entries).
   */
  public final int lastNonZeroRowInColumn (int col)
  {
    int row = rows-1;

    while (row > 0 && data[row][row] == 0.0)
      row--;    

    return row;
  }


  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="square" href="#contents">Square-Matrix Specific Methods</a></li></a>
   * </span></h4>
   * <p/>
   * Following are some methods concerning square matrices;
   * <i>i.e.</i>, or equal numbers of rows and columns.
   */

  /**
   * Return <tt>true</tt> iff <tt>this</tt> <tt>Matrix</tt> is square.
   */
  public final boolean isSquare ()
  {
    return (rows == cols);
  }
  
  /**
   * Return the trace of <tt>this</tt> (square) <tt>Matrix</tt>
   * (<i>i.e.</i>, the sum of all its diagonal entries).
   */
  public final double trace ()
  {
    if (!isSquare())
      throw new RuntimeException("Can only compute trace of a non-square matrix");

    double result = 0.0;
    for (int i = 0; i < rows; i++)
      result += data[i][i];

    return result;
  }
  
  /**
   * Return <tt>true</tt> iff this is a square diagonal matrix.
   */
  public final boolean isDiagonal ()
  {
    if (!isSquare())
      return(false);
    
    // need only sweep through the upper triangular part and check
    // whether either of each symmetric entries is non-zero
    for (int row = 0; row < rows-1; row++)
      for (int col = 1; col < cols; col++)
	if (data[row][col] != 0.0 || data[col][row] != 0.0)
	  return false;

    return true;
  }

  /**
   * If this is a square matrix, this returns <tt>this</tt> after
   * modifying its <tt>data</tt> array in place to its transpose.  If it
   * is not square, it throws a <tt>RuntimeException</tt>.
   */
  public Matrix i_transpose ()
  {
    if (!isSquare())
      throw new RuntimeException("Cannot transpose a non-square matrix in place!");

    // need only sweep through the upper triangular part and swap
    // symmetric non-equal entries (this is necessarily safe)
    for (int row = 0; row < rows-1; row++)
      for (int col = 1; col < cols; col++)
	if (data[col][row] != data[row][col])
	  data[col][row] = safeDataUpdate(row,col,data[col][row]);

    return this;
  }

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="vectors" href="#contents">Vector-Specific Methods</a></li></a>
   * </span></h4>
   * <p/>
   * Following are some methods concerning one-dimensional matrices;
   * <i>i.e.</i>, (row or column) vectors.
   */

  /**
   * Return <tt>true</tt> iff <tt>this</tt> is a one-dimensional
   * <tt>Matrix</tt>.
   */
  public boolean isVector ()
  {
    return isRowVector() || isColVector();
  }

  /**
   * Return <tt>true</tt> iff <tt>this</tt> is a one-row
   * <tt>Matrix</tt>.
   */
  public boolean isRowVector ()
  {
    return rows == 1;
  }

  /**
   * Return <tt>true</tt> iff <tt>this</tt> is a one-colum
   * <tt>Matrix</tt>.
   */
  public boolean isColVector ()
  {
    return cols == 1;
  }

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="io" href="#contents">I/O Object Methods</a>
   * </span></h4>
   */

  /**
   * Prints this matrix to standard output printing each <tt>double</tt>
   * entry formatted using the default<a href="#floatformat">
   * <tt>floatFormat</tt> format string</a>.
   */
  public void show ()
  {
    System.out.println();
    for (int row = 0; row < rows; row++)
      {
	for (int col = 0; col < cols; col++)
	  System.out.printf(floatFormatString(),data[row][col]);
	System.out.println();
      }
    System.out.println();
  }

  final protected void showData ()
  {
    showDataArray(data);
  }

  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="classmethods" href="#contents">Static Methods</a>
   * </span></h2>
   */

  /**
   * Return <tt>true</tt> iff <tt>this.data</tt> and </tt>M.data</tt> are
   * entry-wise equal.
   */
  static protected final boolean equalData (double[][] data1, double[][] data2)
  {
    for (int row = 0; row < data1.length; row++)
      for (int col = 0; col < data1[0].length; col++)
	if (data1[row][col] != data2[row][col])
	  return false;

    return true;
  }

  /**
   * Return a new <tt>double[][]</tt> that is the transpose of the given
   * array <tt>data</tt>.
   */
  static public final double[][] dataTranspose (double[][] data)
  {
    int rows = data.length;
    int cols = data[0].length;
    double[][] newData = new double[cols][rows];

    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	newData[col][row] = data[row][col];

    return newData;
  }

  static final protected void showDataArray (double[][] data)
  {
    for (int row = 0; row < data.length; row++)
      {
	for (int col = 0; col < data[0].length; col++)
	  System.out.printf(floatFormatString(),data[row][col]);
	System.out.println();
      }
  }

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="useful" href="#contents">Useful Static Matrix Methods</a>
   * </span></h4>
   * <p/>
   *
   * The following are various (static) useful methods handy when using
   * <tt>Matrix</tt>.
   */

  /**
   * Return a new square <tt>Matrix</tt> of order <tt>order</tt> with all
   * entries <tt>0.0</tt>'s.
   */
  public static Matrix newSquareMatrix (int order)
  {
    return new Matrix(order);
  }

  /**
   * Return <tt>true</tt> if the given entry is assimilitated to zero
   * according to the current set precision.
   */
  static public boolean isZeroEntry (double entry)
  {
    return Math.abs(entry) < currentPrecision();
  }

  /**
   * Return a new <tt>double[][]</tt> that is equal to the given array
   * <tt>data</tt>.
   */
  static public final double[][] copyData (double[][] data)
  {
    int rows = data.length;
    int cols = data[0].length;

    double[][] dataCopy = new double[rows][cols];	
    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	dataCopy[row][col] = data[row][col];
    return dataCopy;
  }

  /**
   * Return a new identity <tt>Matrix</tt> of order <tt>order</tt>.
   */
  static public Matrix identity (int order)
  {
    double[][] id = new double[order][order];

    for(int i = 0; i< order; i++)
      id[i][i] = 1.0;
    
    return new Matrix(id);
  }

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="checks" href="#contents">Consistency Checking</a>
   * </span></h4>
   * <p/>
   *
   * The following are various (static) methods that are used in various
   * places to verify the consistency of various <tt>Matrix</tt>
   * parameters in a given context.
   */

  /**
   * Verifies the compatibility of the given dimensions and throws an
   * <tt>IncompatibleMatrixDimensionException</tt> if it is violated.
   */
  static final protected void verifyCompatibleDimension (int rows, int cols, int otherRows, int otherCols)
  {
    if (rows != otherRows || cols != otherCols)
      throw new IncompatibleMatrixDimensionException(rows,cols,otherRows,otherCols);
  }

  /**
   * Throws a <tt>RuntimeException</tt> if there is no such entry at
   * indices (<tt>i</tt>,<tt>j</tt>) in this <tt>Matrix</tt>. <b>N.B.:</b>
   * Matrix indices are counted from <tt>1</tt>.
   */
  final protected void checkLegalEntry (int i, int j)
  {
    checkLegalIndex(i,data.length);
    checkLegalIndex(j,data[0].length);
  }
  
  /**
   * Throws a <tt>RuntimeException</tt> if index <tt>i</tt> is not between
   * <tt>1</tt> and <tt>size</tt> inclusive. <b>N.B.:</b> Matrix indices are
   * counted from <tt>1</tt>.
   */
  static final protected void checkLegalIndex (int i, int size)
  {
    if (i < 1 || i > size)
      throw new RuntimeException
	("Matrix index "+i+" out of bounds [1,"+size+"]");    
  }

  /**
   * Throws a <tt>RuntimeException</tt> if any entry in the given
   * <tt>data</tt> array is not stochastic.
   */
  static final protected void checkForStochasticEntries (double[][] data)
  {
    for (int row = 0; row < data.length; row++)
      for (int col = 0; col < data[0].length; col++)
	if (data[row][col] < 0.0 || data[row][col] > 1.0)
	  throw new RuntimeException
	    ("Non-stochastic entry in data array at (row,col): ("+row+","+col+")");
  }  

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="printing" href="#contents">Printing Control</a>
   * </span></h4>
   * <p/>
   *
   * The following are various (static) paraphernalia for the control of
   * printing the entries of a <tt>Matrix</tt> object.
   */

  /**
   * <a name="printWidth"></a>Print width used to format
   * <tt>double</tt>s and <tt>double</tt>s using Java's <a
   * href="https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#syntax"><tt>System.out.printf</tt>
   * format syntax</a>. In order to control the format of entries printed
   * by the <tt>show()</tt> method, redefine this string to the wished
   * format string.
   */
  static private int printWidth = 9;

  /**
   * Return the <tt>printf</tt> width used for printing floating-point
   * numbers.  types.
   */
  static public final int printWidth ()
  {
    return printWidth;
  }

  /**
   * Sets the format string to the specified <tt>width</tt>, which will
   * be the total print width used for printing floating-point numbers
   * with <tt>printf</tt>. This cannot be less that <tt>3</tt> (if so, it
   * is set to <tt>3</tt>).
   */
  static public final int setPrintWidth (int width)
  {
    // %[width].[precision]f
    return printWidth = Math.max(3,width);
  }

  /**
   * Resets to <tt>9</tt> the total print width for floating-point
   * numbers used in <tt>printf</tt> format string.
   */
  static public final int resetPrintWidth ()
  {
    return setPrintWidth(9);
  }

  /**
   * Return the current <a name="floatformat">floating-point format
   * string</a> in effect for printing floating-point values using
   * <tt>printf</tt>.
   */
  public static String floatFormatString ()
  {
    return
      "%" +
      Integer.toString(printWidth) +
      "." +
      Integer.toString(currentSignificantDigits()) +
      "f";
  }

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="precision" href="#contents">Precision Control</a>
   * </span></h4>
   * <p/>
   *
   * The following are various (static) paraphernalia for the control of
   * <tt>double</tt> precision when dealing with random values.
   */

  /**
   * Return a <tt>double[rows][cols]</tt> with entries equal to random
   * values between <tt>0.0</tt> and <tt>1.0</tt> inclusive.
   */
  static public double[][] randomDataArray (int rows, int cols)
  {
    double[][] data = new double[rows][cols];

    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	data[row][col] = randomValue();

    return data;
  }

  /**
   * Return a <tt>double[rows][cols]</tt> with entries equal to random
   * values between <tt>0.0</tt> and <tt>scale</tt> inclusive.
   */
  static public double[][] randomDataArray (int rows, int cols, double scale)
  {
    double[][] data = new double[rows][cols];

    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
	data[row][col] = randomValue(scale);

    return data;
  }

  /**
   * <a name="bias">Toss a coin</a> and return either <tt>false</tt> or
   * <tt>true</tt>.  This can be biased by setting
   * <tt>COIN_TOSS_BIAS</tt> to any double between
   * <tt>[0.0,1.0]</tt>. The lower the bias makes coin tossing return
   * <tt>false</tt>; the higher, it makes it return <tt>true</tt>.  If
   * <tt>1</tt>, then coin-toss is always <tt>true</tt>.  If <tt>0</tt>,
   * then coin-toss is always <tt>false</tt>. The default is
   * <tt>COIN_TOSS_BIAS = 0.5</tt>.
   */
  static public final boolean headsOrTail ()
  {
    if (Math.random() < COIN_TOSS_BIAS)
      return true;
    return false;
  }

  /**
   * The default <tt>COIN_TOSS_BIAS</tt>.
   */
  static private double COIN_TOSS_BIAS = 0.5;

  /**
   * Return the current toss-coin bias (a <tt>double</tt> in
   * <tt>[0.0,1.0]</tt>).
   */
  static public final double bias ()
  {
    return COIN_TOSS_BIAS;
  }

  /**
   * Set the toss-coin bias to <tt>bias</tt>. The lower the bias makes
   * coin tossing return <tt>false</tt> more often; the higher, it makes
   * it return <tt>true</tt> more often.  If <tt>1</tt>, then coin-toss
   * is always <tt>true</tt>.  If <tt>0</tt>, then coin-toss is always
   * <tt>false</tt>.
   */
  static public final double setBias (double bias)
  {
    return COIN_TOSS_BIAS = Math.max(0,Math.min(1,bias));
  }

  /**
   * Reset the toss-coin bias to <tt>0.5</tt>.
   */
  static public final double resetBias ()
  {
    return COIN_TOSS_BIAS = 0.5;
  }

  /**
   * Return the result of truncating <tt>value</tt> to the value obtained
   * by setting to <tt>0</tt> all its digits past the position indicated by
   * <tt>currentSignificantDigits()</tt>.
   */
  static public final double truncate (double value)
  {
    return value; // NO OP UNTIL I FIGURE OUT HOW TO CONTROL THIS DAMN THING!
    // double scale = Math.pow(10,currentSignificantDigits());

    // return Math.floor(value*scale)/scale; // this means always <= value!
    // //    return round(Math.floor(value*scale)/scale); // see below
  }

  /**
   * Do the same kind of rounding that we do for values that are too close
   * (within current threshold <tt>&theta;</tt>) of <tt>0.0</tt> or
   * <tt>1.0</tt>, but to any <tt>value</tt> in
   * <tt>[&theta;,1-&theta;]</tt> closest (within current threshold
   * <tt>&theta;</tt>).

   * <i>e.g.</i>: if threshold <tt>&theta; = 0.01</tt> and <tt>digits =
   * 3</tt> (<i>i.e.</i>, <tt>precision = 0.001</tt>), the <tt>double</tt>
   * value <tt>0.123456</tt> is rounded downwards to <tt>0.123</tt> because:
   * ...
   * <p/><b>N.B.:</b> this not well defined unless we justify why
   * we round to the closest value at a precision equal to
   * <tt>10&times;precision</tt>!
   */
  static private final double round (double value)
  { // finish later; does nothing and return argument for now
    return value;
  }

  /**
   * Return the given array <tt>data</tt> after of truncating all its
   * entries by setting to <tt>0</tt> all its digits past the position
   * indicated by <tt>currentSignificantDigits()</tt>.
   */
  static public final double[][] truncate (double[][] data)
  {
    if (data == null)
      throw new RuntimeException("Vacuous data array");

    for (int row = 0; row < data.length; row++)
      for (int col = 0; col < data[0].length; col++)
	data[row][col] = truncate(data[row][col]);

    return data;
  }

  /**
   * The greatest number of significant decimal digits accepted for a
   * matrix entries. It is set by default to <tt>5</tt> decimal places.
   */
  static private int MAX_SIGNIFICANT_DIGITS = 5;

  /**
   * The method <tt>maxSignificantDigits()</tt> return the currently
   * effective number of significant decimal digits after the dot that are
   * taken into account.<p/>

   * <b>N.B.:</b> <tt>maxSignificantDigits()</tt> is the next integer
   * higher than <tt>-log<sub>10</sub>(<a
   * href="#currentPrecision">currentPrecision())</a></tt>; <i>i.e.</i>, in
   * Java:

   * <pre>
   * maxSignificantDigits() == -Math.ceil(Math.log10(currentPrecision()))</pre>

   * For example, if <tt>currentPrecision() = 0.0001</tt>, then
   * <tt>log<sub>10</sub>currentPrecision() = -4</tt>, <i>i.e.</i>,
   * <tt>maxSignificantDigits() = 4</tt>.<p/>

   * To keep all this inter-dependencies this consistent, the maximum
   * number of significant digits is the only constant that can be reset
   * and all others are computed from it.
   */
  static public final int maxSignificantDigits ()
  {
    return MAX_SIGNIFICANT_DIGITS;
  }

  /**
   * The method <tt>setMaxSignificantDigits()</tt> sets the maximum number
   * of significant decimal digits after the dot that are taken into
   * account to the maximum of <tt>1</tt> and <tt>digits</tt> and return
   * it.
   */
  static public final int setMaxSignificantDigits (int digits)
  {
    return MAX_SIGNIFICANT_DIGITS = Math.max(1,digits);
  }

  /**
   * How many significant decimal digits are currently taken into account
   * for matrix entries (all digits after this one are <tt>0</tt>). This
   * defaults to <tt>1</tt>.
   */
  static private int CURRENT_SIGNIFICANT_DIGITS = 1;

  /**
   * Return the current number of significant of decimal digits after the
   * dot that are taken into account.
   */
  public static int currentSignificantDigits ()
  {
    return CURRENT_SIGNIFICANT_DIGITS;
  }

  /**
   * The method <tt>setCurrentSignificantDigits(int)</tt> sets the current
   * number of significant decimal digits dot to the given <tt>int</tt> if
   * it is between <tt>1</tt> and <tt>maxSignificantDigits()</tt>, or
   * whichever of these that is closest otherwise, and return it.
   */
  static public final int setCurrentSignificantDigits (int digits)
  {
    return CURRENT_SIGNIFICANT_DIGITS = Math.min(Math.max(1,digits),maxSignificantDigits());
  }

  /**
   * The method <tt><a name="currentPrecision">currentPrecision()</a></tt>
   * return the precision currently in effect. <p/>

   * <b>N.B.:</b> It is always equal to
   * <tt>10<sup>-currentSignificantDigits()</sup></tt>; <i>i.e.</i>, in
   * Java, <tt>Math.pow(10,-d)</tt>.

   * For example, if <tt>currentSignificantDigits() = 3</tt>, then
   * <tt>currentPrecision() = 0.001</tt>.

   */
  static public final double currentPrecision ()
  {
    return Math.pow(10.0,-CURRENT_SIGNIFICANT_DIGITS);
  }

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="random" href="#contents">Random-Number Generation Control</a>
   * </span></h4>
   * <p/>
   *
   * The following are various (static) paraphernalia for the control of
   * how close to <tt>0.0</tt> or <tt>1.0</tt> some random values
   * generated by the <a href="#randomValue"><tt>randomValue()</tt></a>
   * method should be rounded downwards to <tt>0.0</tt> or upwards to
   * <tt>1.0</tt> according a coin toss. (See the methods that <a
   * href="#bias">control coin-toss bias</a>.)
   */

  /**
   * This is a value in <tt>[0.0,1.0]</tt> used as approximation threshold
   * to <tt>0.0</tt> if too close; defaults to <tt>0.0</tt>.
   */
  static private double THRESHOLD0 = 0.0;

  /**
   * Return the threshold for random rounding approximation to
   * <tt>0.0</tt> currently in effect.
   */
  static public final double threshold0 ()
  {
    return THRESHOLD0;
  }

  /**
   * This is a value in <tt>[0.0,1.0]</tt> used as approximation threshold
   * to <tt>1.0</tt> if too close; defaults to <tt>0.0</tt>.
   */
  static private double THRESHOLD1 = 0.0;

  /**
   * Return the threshold for random rounding approximation to
   * <tt>1.0</tt> currently in effect.
   */
  static public final double threshold1 ()
  {
    return THRESHOLD1;
  }

  /**
   * Sets the current approximation threshold to <tt>0.0</tt> to the given
   * <tt>double</tt> in <tt>[0.0,1.0]</tt> used as approximation
   * threshold to round downwards to <tt>0.0</tt> if too close.
   */
  static public double setThreshold0 (double threshold)
  {
    return THRESHOLD0 = Math.min(Math.max(0.0,threshold),1.0);
  }  

  /**
   * Resets the current approximation threshold to <tt>0.0</tt> its
   * default value (<tt>0.0</tt>).
   */
  static public double resetThreshold0 ()
  {
    return THRESHOLD0 = 0.0;
  }  

  /**
   * Sets the current approximation threshold to <tt>1.0</tt> to the
   * given <tt>double</tt> in <tt>[0.0,1.0]</tt> used as approximation
   * threshold to round upwards to <tt>1.0</tt> if too close.
   */
  static public double setThreshold1 (double threshold)
  {
    return THRESHOLD1 = Math.min(Math.max(0.0,threshold),1.0);
  }

  /**
   * Resets the current approximation threshold to <tt>1.0</tt> to its
   * default value (<tt>0.0</tt>).
   */
  static public double resetThreshold1 ()
  {
    return THRESHOLD1 = 0.0;
  }  

  /**
   * Resets the current approximation thresholds to <tt>0.0</tt> and <tt>1</tt> to their
   * default value (<tt>0.0</tt>).
   */
  static public void resetThresholds ()
  {
    resetThreshold0();
    resetThreshold1();
  }  
  /**
   * The static method <a name="randomValue"><tt>randomValue()</tt></a>
   * return a random <tt>double</tt> picked in the <i>closed</i>
   * interval <tt>[0.0,1.0]</tt> (<i>i.e.</i>, including <tt>0.0</tt>
   * and <tt>1.0</tt>), modulo the currently effective number of decimal
   * digits and precision. The <tt>int</tt> <tt>digits</tt> (=
   * <tt>1</tt>, <tt>2</tt>, <i>etc,</i>...) is how many decimal digits
   * after the decimal point <tt>double</tt>s are approximated.
   */
  static protected double randomValue ()
  {
    // pick a random double in [0.0,1.0)
    double picked = truncate(Math.random());
    // the above return a double in [0.0,1.0): it does not include 1.0
    // or may me too small to be worth distinguishing from 0.0; so
    // we round it to either 0.0 or 1.0 if too close to either
    double threshold0 = threshold0();
    double threshold1 = threshold1();
    
    if (picked < threshold0)        /* when picked is too close to 0.0 according to threshold0 */
      return
        headsOrTail() ? picked :    /* toss a coin and return either what was picked or        */
	0.0;                        /* return 0.0 (round downwards)                            */

    if ((1-picked) < threshold1)    /* when picked is too close to 1.0 according to threshold1 */
      return
        headsOrTail() ? picked :    /* toss a coin and return either what was picked or        */
	1.0;                        /* return 1.0 (round upwards)                              */

    // otherwise return picked as is
    return picked;
  }

  /**
   * Show the random-number generation and doubleing-point precision
   * control parameters currently in effect.
   */
  static public void showParameters ()
  {
    ln();
    
    say("--------------------------------------------------------------------");
    say("Matrix class parameter settings:") ;
    say("--------------------------------------------------------------------");
    say("    current number algebra = "+NumberAlgebra.getCurrentAlgebra());
    jot("    significant number of digits = "+currentSignificantDigits());
    say(" (i.e., precision = "+currentPrecision()+")");
    jot("    floating-point print width = "+printWidth());
    say(" (i.e., format string = "+floatFormatString()+")");
    say("    random number generation parameters:");
    say("    \tthreshold to 0.0 = "+threshold0());
    say("    \tthreshold to 1.0 = "+threshold1());
    say("    \tcoin-toss bias   = "+bias());
    say("--------------------------------------------------------------------");
  }

  /**
   * The static method <tt>randomValue(double scale)</tt></a> return a
   * random <tt>double</tt> picked in the <i>closed</i> interval
   * <tt>[0.0,scale]</tt> if <tt>scale</tt> is positive, or
   * <tt>[scale,0.0]</tt> if <tt>scale</tt> is negative, modulo the
   * currently effective number of decimal digits and precision.
   */
  static private double randomValue (double scale)
  {
    return truncate(scale*randomValue());
  }

  /**
   * Create and return a random square matrix of order <tt>order</tt> with
   * entries in <tt>[0.0,1.0]</tt>.
   */
  public static Matrix randomSquare (int order)
  {
    return random(order,order);
  }

  /**
   * Create and return a random square matrix of order <tt>order</tt> with
   * entries in <tt>[0.0,1.0]</tt> scaled by a factor <tt>scale</tt>.
   */
  public static Matrix randomSquare (int order, double scale)
  {
    return random(order,order,scale);
  }

  /**
   * Create and return a random <tt>rows</tt>-by-<tt>cols</tt> matrix with
   * entries in <tt>[0.0,1.0]</tt>.
   */
  public static Matrix random (int rows, int cols)
  {
    return new Matrix().setData(randomDataArray(rows,cols));
  }

  /**
   * Create and return a random <tt>rows</tt>-by-<tt>cols</tt> matrix with
   * entries in <tt>[0.0,scale]</tt>.
   */
  public static Matrix random (int rows, int cols, double scale)
  {
    return new Matrix().setData(randomDataArray(rows,cols,scale));
  }

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="miscio" href="#contents">Miscellaneous Static I/O tools</a>
   * </span></h4>
   */

  /**
   * Print an empty line on the standard output.
   */
  static public void ln ()
  {
    Misc.ln();
  }

  /**
   * Print <tt>n</tt> empty lines on the standard output.
   */
  static public void ln (int n)
  {
    Misc.ln(n);
  }

  /**
   * Print a line not ending in a CR on the standard output.
   */
  static public void jot (String what)
  {
    Misc.jot(what);
  }

  /**
   * Print a line ending in a CR on the standard output.
   */
  static public void say (String what)
  {
    Misc.say(what);
  }

  /**
   * Print a line ending in a CR and skip a line on the standard output.
   */
  static public void sln (String what)
  {
    Misc.sln(what);
  }


}
