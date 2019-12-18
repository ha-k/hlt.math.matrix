// FILE. . . . . d:/hak/hlt/src/hlt/math/matrix/sources/StandardAlgebra.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hak-Laptop
// STARTED ON. . Tue Dec  3 17:40:58 2019

package hlt.math.matrix;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.matrix</tt>
 * documentation listing</a>
 */

/**
 * This is a concrete subclass of <tt>NumberAlgebra</tt> redefining its
 * operations as those of standard arithmetic to be final methods:<p/>
 * <ul>
 * <li/><tt>zero()          </tt>&#8797;<tt>  0.0</tt>
 * <li/><tt>one()           </tt>&#8797;<tt>  1.0</tt>
 * <li/><tt>sum(x,y)        </tt>&#8797;<tt>  x+y</tt>
 * <li/><tt>product(x,y)    </tt>&#8797;<tt>  \*</tt>
 * <li/><tt>negation(x)     </tt>&#8797;<tt>  -x</tt>
 * <li/><tt>difference(x,y) </tt>&#8797;<tt>  x-y</tt>
 * </ul>
 *
 * @see         NumberAlgebra
 * @see         Matrix
 *
 * @version     Last modified on Fri Dec 13 10:01:27 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyy   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */
public class StandardAlgebra extends NumberAlgebra
{
  /**
   * Return the <tt>zero</tt> of this algebra as <tt>0.0</tt>.  It
   * should always verify <tt>sum(x,zero())</tt> =
   * <tt>sum(zero(),x)</tt> = <tt>x</tt>, for any <tt>double</tt>
   * <tt>x</tt>.
   */
  public final double zero ()
  {
    return 0.0;
  }

  /**
   * Return the <tt>one</tt> of this algebra as <tt>1.0</tt>.  It should
   * always verify <tt>product(x,one())</tt> = <tt>product(one(),x)</tt>
   * = <tt>x</tt>, for any <tt>double</tt>
   */
  public final double one ()
  {
    return 1.0;
  }

  /**
   * Return the <tt>sum</tt> of the arguments as <tt>+</tt>.
   */
  public final double sum (double x, double y)
  {
    return x + y;	
  }

  /**
   * Return the <tt>product</tt> of the arguments as <tt>&times;</tt>.
   */
  public final double product (double x, double y)
  {
    return x * y;	
  }

  /**
   * Return the <tt>difference</tt> of the arguments as binary <tt>-</tt>.
   */
  public final double difference (double x, double y)
  {
    return x - y;	
  }

  /**
   * Return the <tt>negation</tt> of the argument as unary <tt>-</tt>.
   */
  public final double negation (double x)
  {
    return -x;
  }

  /**
   * Define a <tt>String</tt> form for this <tt>StandardAlgebra</tt>. It is
   * final so it cannot be redefined in any subclass.
   */
  public final String toString ()
  {
    return "Standard Algebra";
  }

}
