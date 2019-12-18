// FILE. . . . . d:/hak/hlt/src/hlt/math/matrix/sources/MaxMinAlgebra.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hak-Laptop
// STARTED ON. . Thu Dec  5 12:40:13 2019

package hlt.math.matrix;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.matrix</tt>
 * documentation listing</a>
 */

/**
 * This is a concrete subclass of <tt>NumberAlgebra</tt> defining its
 * operations as the algebra defining <tt>sum</tt> as <tt>Math.max</tt>,
 * <tt>product</tt> as <tt>Math.min</tt>, <tt>zero()</tt> as
 * <tt>NEGATIVE_INFINITY</tt>, and <tt>one()</tt> as
 * <tt>POSITIVE_INFINITY</tt>. The <tt>negation</tt> and
 * <tt>difference</tt> operations are undefined.
 *
 * @see         NumberAlgebra
 * @see         Matrix
 *
 * @version     Last modified on Fri Dec 13 13:12:50 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyy   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */
public class MaxMinAlgebra extends NumberAlgebra
{
  /**
   * Return the <tt>zero</tt> of this <tt>MaxMinAlgebra</tt> as
   * <tt>NEGATIVE_INFINITY</tt>.  It verifies <tt>sum(x,zero())</tt> =
   * <tt>sum(zero(),x)</tt> = <tt>x</tt>, for any <tt>double</tt>
   * <tt>x</tt>.
   */
  public double zero ()
  {
    return Double.NEGATIVE_INFINITY;
  }

  /**
   * Return the <tt>one</tt> of this algebra as
   * <tt>POSITIVE_INFINITY</tt>.  It verifies <tt>product(x,one())</tt>
   * = <tt>product(one(),x)</tt> = <tt>x</tt>, for any <tt>double</tt>
   * <tt>x</tt>.
   */
  public double one ()
  {
    return Double.POSITIVE_INFINITY;
  }

  /**
   * Return the <tt>sum</tt> of the arguments as <tt>Math.max</tt>.
   * Verifies <tt>sum(x,zero())</tt> = <tt>sum(zero(),x)</tt> =
   * <tt>x</tt>, for any <tt>double</tt> <tt>x</tt>:
   * <tt>max(x,NEGATIVE_INFINITY)</tt> =
   * <tt>max(NEGATIVE_INFINITY,x)</tt> = <tt>x</tt> for any
   * <tt>double</tt> <tt>x</tt>.
   */
  public final double sum (double x, double y)
  {
    return Math.max(x,y);	
  }

  /**
   * Return the <tt>product</tt> of the arguments as <tt>Math.min</tt>.
   * Verifies <tt>product(x,one())</tt> = <tt>product(one(),x)</tt> =
   * <tt>x</tt>, for any <tt>double</tt> <tt>x</tt>:
   * <tt>min(x,POSITIVE_INFINITY)</tt> =
   * <tt>min(POSITIVE_INFINITY,x)</tt> = <tt>x</tt> for any
   * <tt>double</tt> <tt>x</tt>.
   */
  public final double product (double x, double y)
  {
    return Math.min(x,y);	
  }

  /**
   * Throws a runtime exception to indicate that the <tt>negation</tt>
   * operation is undefined for a this <tt>MaxMinAlgebra</tt>. It can be
   * redefined to a well-defined operation in a subclass.
   */
  public double negation (double x)
  {
    throw new RuntimeException
      ("The negation operation is undefined for a "+this);
  }

  /**
   * Throws a runtime exception to indicate that the <tt>difference</tt>
   * operation is undefined for a this <tt>MaxMinAlgebra</tt>. It can be
   * redefined to a well-defined operation in a subclass.
   */
  public double difference (double x, double y)
  {
    throw new RuntimeException
      ("The difference operation is undefined for a "+this);
  }

  /**
   * Define a <tt>String</tt> form for this <tt>MaxMinAlgebra</tt>. It
   * must be redefined in any subclass.
   */
  public String toString ()
  {
    return "Max/Min Algebra";
  }

}
