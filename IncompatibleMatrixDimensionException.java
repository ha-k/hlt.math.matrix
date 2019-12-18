// FILE. . . . . d:/hak/hlt/src/hlt/math/matrix/sources/IncompatibleMatrixDimensionException.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hak-Laptop
// STARTED ON. . Mon Nov 18 15:58:59 2019

/**
 * @version     Last modified on Thu Dec 12 11:12:44 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

package hlt.math.matrix;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.matrix</tt>
 * documentation listing</a>
 */

/**
 * This is the class of runtime exceptions specifically caused by a
 * matrix dimension mismatch between two pairs of numbers of rows and
 * columns.
 * 
 * @see         Matrix
 */
public class IncompatibleMatrixDimensionException extends RuntimeException
{
  /**
   * Contructs a new <tt>IncompatibleMatrixDimensionException</tt> with
   * the given parameters.
   */
  public IncompatibleMatrixDimensionException (int rows, int cols, int otherRows, int otherCols)
  {
    super("Incompatible matrix dimensions: ("+
	  rows+","+cols+") =/= ("+
	  otherRows+","+otherCols+")");
  }
}
