// FILE. . . . . d:/hak/hlt/src/hlt/math/matrix/sources/NumberAlgebra.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hak-Laptop
// STARTED ON. . Tue Dec  3 17:40:58 2019

package hlt.math.matrix;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.matrix</tt>
 * documentation listing</a>
 */

import java.util.HashMap;
import java.util.Iterator;

/**
 * This is an abstract class that can be subclassed by any class
 * defining two dual operations <tt>sum</tt> and <tt>product</tt> on a
 * pair of <tt>double</tt> values, as well as two nullary
 * <tt>zero()</tt> and <tt>one()</tt> operations, a unary operation
 * <tt>negation</tt>, and a binary <tt>difference</tt> operation, all
 * returning a <tt>double</tt>.<p/>

 * It provides default implementations for all six as the six standard
 * Java built-in floating-point arithmetic operations <tt>+</tt> and
 * <tt>\*</tt>, the <tt>double</tt> constants <tt>0.0</tt> and
 * <tt>1.0</tt>, and unary and binary <tt>-</tt>; <i>i.e.</i>,
 * <tt>sum(x,y)</tt> = <tt>x+y</tt>, <tt>product(x,y)</tt> =
 * <tt>x\*y</tt>, <tt>zero()</tt> = <tt>0.0</tt>, <tt>one()</tt> =
 * <tt>1.0</tt>, <tt>negation(x)</tt> = <tt>-x</tt>, and
 * <tt>difference(x,y)</tt> = <tt>x-y</tt>. These default methods are
 * not final. So, unless redefined to other methods in a subclass, they
 * are inherited.<p/>
 *
 * To understand how the algebra classes are organized in this package and
 * could be used by other packages, see <a
 * href="MatrixPackageDescription.html#algebras">this detailed
 * description</a>.
 *
 * @see         MatrixPackageDescription
 * @see         StandardAlgebra
 * @see         MaxMinAlgebra
 * @see         Matrix
 *
 * @version     Last modified on Wed Dec 18 07:39:41 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyy   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */
abstract public class NumberAlgebra
{
  /* ************************************************************************ */
  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="constructors" href="#contents">Constructors</a>
   * </span></h2>
   */

  /**
   * Constructs a new <tt>NumberAlgebra</tt> with the default operations. It
   * is not public.
   */
  protected NumberAlgebra ()
  {
  }
  
  /**
   * This defines the default <tt>zero()</tt> of this <tt>NumberAlgebra</tt> as
   * <tt>0.0</tt>.  It can be redefined in a subclass. It should always verify
   * <tt>sum(x,zero())</tt> = <tt>sum(zero(),x)</tt> = <tt>x</tt>, for any
   * <tt>double</tt> <tt>x</tt>.
   */
  public double zero ()
  {
    return 0.0;
  }

  /**
   * This defines the default <tt>one()</tt> of this <tt>NumberAlgebra</tt> as
   * <tt>1.0</tt>. It can be redefined in a subclass.  It should always verify
   * <tt>product(x,one())</tt> = <tt>product(one(),x)</tt> = <tt>x</tt>, for
   * any <tt>double</tt>.
   */
  public double one ()
  {
    return 1.0;
  }

  /**
   * This defines the default <tt>sum</tt> of this <tt>NumberAlgebra</tt>. It
   * can be redefined in a subclass. It should always verify
   * <tt>sum(x,zero())</tt> = <tt>sum(zero(),x)</tt> = <tt>x</tt>, for any
   * <tt>double</tt> <tt>x</tt>.
   */
  public double sum (double x, double y)
  {
    return x + y;	
  }

  /**
   * This defines the default <tt>product</tt> of this <tt>NumberAlgebra</tt> as
   * <tt>\*</tt>. It can be redefined in a subclass.  It should always verify
   * <tt>product(x,one())</tt> = <tt>product(one(),x)</tt> = <tt>x</tt>, for
   * any <tt>double</tt>.
   */
  public double product (double x, double y)
  {
    return x * y;	
  }

  /**
   * This defines the default <tt>difference</tt> of this
   * <tt>NumberAlgebra</tt> as binary <tt>-</tt>. It can be redefined in a
   * subclass.  It should always verify <tt>difference(zero(),x)</tt> =
   * <tt>negation(x)</tt>, for any <tt>double</tt>.
   */
  public double difference (double x, double y)
  {
    return x - y;	
  }

  /**
   * This defines the default <tt>negation</tt> of this <tt>NumberAlgebra</tt>
   * as binary <tt>-</tt>. It can be redefined in a subclass.  It should
   * always verify <tt>difference(zero(),x)</tt> = <tt>negation(x)</tt>, for
   * any <tt>double</tt>.
   */
  public double negation (double x)
  {
    return -x;
  }

  /**
   * Return the default <tt>String</tt> form for this
   * <tt>NumberAlgebra</tt>. It must redefined in any subclass.
   */
  public String toString ()
  {
    return "Number Algebra";
  }

  /* ************************************************************************ */
  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="statics" href="#contents">
   * Static Class Components and Algebra Manipulation Methods</a>
   * </span></h2>
   *
   * This gathers all the paraphernalia necessary and/or useful for the
   * control of parameterization of matrix operations with respect to
   * redefinable arithmetic operations on a matrix <tt>double</tt> entries.
   */

  /**
   * A private holder for the canonical <tt>StandardAlgebra</tt> object.
   */
  static private StandardAlgebra standardAlgebra;    

  /**
   * Return the canonical <tt>StandardAlgebra</tt>.
   */
  static final public StandardAlgebra standardAlgebra ()
  {
    if (standardAlgebra == null)
      standardAlgebra = (StandardAlgebra)registeredAlgebra(new StandardAlgebra());

    return standardAlgebra;
  }

  /**
   * A private holder for the canonical <tt>MaxMinAlgebra</tt> object.
   */
  static private MaxMinAlgebra maxMinAlgebra;

  /**
   * Return the canonical <tt>MaxMinAlgebra</tt>.
   */
  static final public MaxMinAlgebra maxMinAlgebra ()
  {
    if (maxMinAlgebra == null)
      maxMinAlgebra = (MaxMinAlgebra)registeredAlgebra(new MaxMinAlgebra());

    return maxMinAlgebra;
  }

  /* ************************************************************************ */

  /**
   * This is the current <tt>NumberAlgebra</tt> in effect for all matrix
   * operations. It is set by default to the canonical <tt>StandardAlgebra</tt>.
   */
  static private NumberAlgebra currentAlgebra;

  /**
   * Return 
   */
  static final public NumberAlgebra currentAlgebra ()
  {
    if (currentAlgebra == null)
      currentAlgebra = standardAlgebra();

    return currentAlgebra;
  }

  /**
   * Set the current canonical <tt>NumberAlgebra</tt> on <tt>double</tt>
   * matrix entries in effect for all matrix operations to the (canonical
   * representative algebra of the) given <tt>NumberAlgebra</tt>.
   */
  static final public void setCurrentAlgebra (NumberAlgebra algebra)
  {
    currentAlgebra = registeredAlgebra(algebra);
  }

  /**
   * Return the current number algebra on <tt>double</tt> matrix entries in
   * effect for all matrix operations.
   */
  static final public NumberAlgebra getCurrentAlgebra ()
  {
    if (currentAlgebra == null)
      throw new RuntimeException
	("There is no NumberAlgebra currently set.");

    return currentAlgebra;
  }

  /**
   * Set the current number algebra in effect for all matrix operations to the
   * canonical <tt>StandardAlgebra</tt>.
   */
  static final public void setStandardAlgebra ()
  {
    currentAlgebra = standardAlgebra();
  }

  /**
   * Set the current number algebra in effect for all matrix operations to the
   * canonical <tt>MaxMinAlgebra</tt>.
   */
  static final public void setMaxMinAlgebra ()
  {
    currentAlgebra = maxMinAlgebra();
  }

  /* ************************************************************************ */
  /**
   * This is the repository of all registered concrete
   * <tt>NumberAlgebra</tt>s. It is used to provide a unique canonical algebra
   * object for a given algebra name. Thus, it associates a <tt>String</tt>
   * (the algebra name) to the unique <tt>NumberAlgebra</tt> it denotes.
   */
  static final private HashMap algebras = new HashMap();

  /**
   * Retrieve and return the unique canonical <tt>NumberAlgebra</tt> object
   * representative sharing the given algebra's name. If none exists, register
   * this algebra under its name (its <tt>toString()</tt> form), and return
   * it.
   */
  static final public NumberAlgebra registeredAlgebra (NumberAlgebra algebra)
  {
    String name = algebra.toString().intern();

    NumberAlgebra alg = (NumberAlgebra)algebras.get(name);

    if (alg != null)
      return alg;

    algebras.put(name,algebra);
    System.err.println("*** Registering new algebra: "+name);    

    return algebra;
  }

  static final public void showRegisteredAlgebras ()
  {
    System.out.println("\tCurrently registered number algebras:\n");    

    for (Iterator it = algebras.keySet().iterator(); it.hasNext();)
      System.out.println("\t"+it.next());

  System.out.println();
  }

}
