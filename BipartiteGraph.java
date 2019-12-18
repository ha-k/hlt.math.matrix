// FILE. . . . . d:/hak/hlt/src/hlt/math/matrix/sources/BipartiteGraph.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hak-Laptop
// STARTED ON. . Fri Nov 22 14:01:20 2019

/**
 * @version     Last modified on Thu Dec 12 11:15:12 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

package hlt.math.matrix;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.matrix</tt>
 * documentation listing</a>
 */

import java.util.Iterator;

import hlt.language.util.ArrayList;
import hlt.language.util.IntArrayList;
import hlt.language.util.IntIterator;

/**
 * This is a class representing a weighted bipartite graph with edges between
 * two sets of nodes of equal cardinality: left nodes and right nodes. It is
 * a subclass of <tt>Matrix</tt>, so using it can rely on its
 * <tt>Matrix</tt> and <tt>Matrix</tt> structure where its nodes
 * correspond to rows and columns and its edges correspond to non-zero
 * entries in its <tt>data</tt> array. More precisely, a left node is a row
 * and a right node is a column, and an edge exists between a left node and a
 * right node iff the corresponding entry in the data array is non-zero <i>at
 * edge-creation time</i>. Indeed, a <tt>BipartiteGraph</tt> is always
 * generated from its <i>defined</i> <tt>data</tt> array component. Hence, at
 * creation time, all its edges have a non-zero weight equal to the
 * corresponding entry in the <tt>data</tt> array. After it is created, a
 * <tt>BipartiteGraph</tt>'s graph structure does not change, but the weights
 * on its edges can change.  After creation time, modifying such an edge's
 * weight to any value (including zero) will modify the corresponding matrix
 * entry in the <tt>data</tt> array, and conversely, modifying any matrix
 * entry in the <tt>data</tt> array corresponding to an existing edge will
 * modify the weight on the edge. <b>N.B.:</b> Since a
 * <tt>BipartiteGraph</tt>'s graph structure is immutable after it is
 * created, setting an edge's weight to zero will not delete the edge.  This
 * is convenient for implementing many weight-based computation algorithms
 * (<i>e.g.</i> finding a matching, an augmenting path, <i>etc.</i>, ...).
 * 
 * <p/>
 * 
 * <a name="contents"><span style="font-family:arial,helvetica;">
 * <b>Contents</b> <small>(<a
 * href="000StartHere.html"><tt>package hlt.math.matrix</tt>
 * documentation listing</a>)</small> </span></a>
 *
 * <ul>
 *
 * <p/><li><a href="#constructors"><b><span style="font-family:arial,helvetica;">
 * Object Constructors
 * </span></b></a></li>
 *
 * <p/><li><a href="#components"><b><span style="font-family:arial,helvetica;">
 * Object Components
 * </span></b></a></li>
 *
 * <p/><li><a href="#methods"><b><span style="font-family:arial,helvetica;">
 * Object Methods
 * </span></b></a></li>
 *
 * <p/>
 *
 *    <ul>
 *
 *     <li><a href="#nodesedges"><b><span style="font-family:arial,helvetica;">
 *          Node/Edge Methods
 *          </span></b></a></li>
 *
 *     <li><a href="#io"><b><span style="font-family:arial,helvetica;">
 *          I/O Methods
 *          </span></b></a></li>
 *
 *     <li><a href="#matchings"><b><span style="font-family:arial,helvetica;">
 *          Methods for Matchings and Paths
 *          </span></b></a></li>
 *
 *     <li><a href="#private"><b><span style="font-family:arial,helvetica;">
 *          Local Private Methods
 *          </span></b></a></li>
 *
 *    </ul>
 *
 * <p/><li><a href="#static"><b><span style="font-family:arial,helvetica;">
 * Class Methods
 * </span></b></a></li>
 *
 * <p/>
 *
 *    <ul>
 *
 *    <li><a href="#scomponents"><b><span style="font-family:arial,helvetica;">
 *          Class Components
 *          </span></b></a></li>
 *
 *    <li><a href="#smethods"><b><span style="font-family:arial,helvetica;">
 *          Class Methods
 *          </span></b></a></li>
 *
 *    </ul>
 *
 * <p/><li><a href="#classes"><b><span style="font-family:arial,helvetica;">
 *          Local Classes
 *          </span></b></a></li>
 *
 * <p/>
 *
 *    <ul>
 *
 *    <li><a href="#edge"><b><span style="font-family:arial,helvetica;">
 *          Edge Class
 *          </span></b></a></li>
 *
 *    <li><a href="#matching"><b><span style="font-family:arial,helvetica;">
 *          Matching Class
 *          </span></b></a></li>
 *
 *    </ul>
 * </ul>
 *
 * @see         Matrix
 * @see         Matrix
 */
public class BipartiteGraph extends Matrix
{
  /**
   * <h3 align="center"><span style="font-family:arial,helvetica;">
   * <a name="constructors" href="#contents">Object Constructors</a>
   * </span></h3>
   */

  /**
   * Construct a <tt>BipartiteGraph</tt> with the given <tt>data</tt>
   * array. This ensures that the data array is defined and square (violation
   * of either condition causes a runtime exception to be thrown).
   */
  public BipartiteGraph (double[][] data)
  {
    setData(data);
    initializeGraphComponents();
  }

  /**
   * <h3 align="center"><span style="font-family:arial,helvetica;">
   * <a name="components" href="#contents">Object Components</a>
   * </span></h3>
   *
   * In addition to the components it inherits form its <a
   * href="./Matrix.html"><tt>Matrix</tt></a> superclass, a
   * <tt>BipartiteGraph</tt> has one specific additional component which
   * is a <tt>boolean[][]</tt> array. It also keeps handy information in
   * node-indexed arrays.
   */

  /**
   * This is a square array of <tt>boolean</tt>s having the same sign pattern
   * as the data array at graph creation time.  It serves as a mask on the
   * <tt>data</tt> array of this <tt>BipartiteGraph</tt> to identify which
   * entries in its <tt>data</tt> array are its edges. Hence, it is immutable
   * once created.
   */
  private boolean[][] isEdge;

  /**
   * This is an array of <tt>IntArrayList</tt>s containing the lists of
   * successors of left nodes.
   */
  private IntArrayList[] succLists;

  /**
   * This is an array of right node indices, each of which is the first
   * successor of a left node, or <tt>-1</tt> if a left node has no
   * successor.
   */
  private int[] firstSuccs;

  /**
   * This is an array of right node indices, each of which is the last
   * successor of a left node, or <tt>-1</tt> if a left node has no
   * successor.
   */
  private int[] lastSuccs;

  /**
   * This is an array of <tt>IntArrayList</tt>s containing the lists of
   * predecessors of right nodes.
   */
  private IntArrayList[] predLists;

  /**
   * This is an array of left node indices, each of which is the first
   * predecessor of a right node, or <tt>-1</tt> if a right node has no
   * predecessor.
   */
  private int[] firstPreds;

  /**
   * This is an array of left node indices, each of which is the last
   * predecessor of a right node, or <tt>-1</tt> if a right node has no
   * predecessor.
   */
  private int[] lastPreds;

  /**
  * Contains the number of successors of each left node.
  */
  private int[] numOfSuccs;

  /**
  * Contains the number of predecessors of each right node.
  */
  private int[] numOfPreds;

  /**
   * <h3 align="center"><span style="font-family:arial,helvetica;">
   * <a name="methods" href="#contents">Object Methods</a>
   * </span></h3>
   */

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="nodesedges" href="#contents">Node/Edge Methods</a>
   * </span></h4>
   */

  /**
   * Return <tt>true</tt> iff there is an edge in this
   * <tt>BipartiteGraph</tt> between <tt>left</tt> node and <tt>right</tt>
   * node.
   */
  public final boolean isEdge (int left, int right)
  {
    return isEdge[left][right];
  }

  /**
   * Return <tt>true</tt> iff there is an edge in this
   * <tt>BipartiteGraph</tt> between <tt>right</tt> node and <tt>left</tt>
   * node.
   */
  public final boolean isDualEdge (int right, int left)
  {
    return isEdge[left][right];
  }

  /**
   * Return the number of successors of the given <tt>left</tt> node.
   */
  public final int numOfSuccessors (int left)
  {
    return numOfSuccs[left];
  }

  /**
   * Return the number of predecessors of the given <tt>right</tt> node.
   */
  public final int numOfPredecessors (int right)
  {
    return numOfPreds[right];
  }

  /**
   * Return the right node index of the first successor of the given
   * <tt>left</tt> node, or <tt>-1</tt> if it has no successor.
   */
  public final int firstSuccessor (int left)
  {
    return firstSuccs[left];
  }

  /**
   * Return the right node index of the last successor of the given
   * <tt>left</tt> node, or <tt>-1</tt> if it has no successor.
   */
  public final int lastSuccessor (int left)
  {
    return lastSuccs[left];
  }

  /**
   * Return the left node index of the first predecessor of the given
   * <tt>right</tt> node, or <tt>-1</tt> if it has no predecessor.
   */
  public final int firstPredecessor (int right)
  {
    return firstPreds[right];
  }

  /**
   * Return the left node index of the last predecessor of the given
   * <tt>right</tt> node, or <tt>-1</tt> if it has no predecessor.
   */
  public final int lastPredecessor (int right)
  {
    return lastPreds[right];
  }

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="io" href="#contents">I/O Methods</a>
   * </span></h4>
   */

  /**
   * Print this <tt>BipartiteGraph</tt> as a matrix to standard output
   * printing each <tt>double</tt> entry formatted using the current <a
   * href="./Matrix.html#floatFormat"><tt>floatFormatString()</tt> value</a>.
   */
  public void show ()
  {
    jot("\t"); // tab space over the row numbers

    for (int col = 0; col < cols; col++)
      jot(centerColumnHeader(headerString(col+1))); // column number (counted from 1)

    ln(2);

    for (int row = 0; row < rows; row++)
      {
	jot(headerString(row+1)+"\t"); // row number (counted from 1)
	
	for (int col = 0; col < cols; col++)
	  if (isEdge(row,col))
	    System.out.printf(floatFormatString(),data[row][col]);
	  else
	    jot(noEdgeString());

	ln();
      }
  }

  /**
   * Print this <tt>BipartiteGraph</tt>'s components to standard output.
   */
  public void showComponents ()
  {
    int order = rows;

    sln("Showing the successors of each left node:");

    for (int left = 0; left < order; left++)
      say("\t"+(left+1)+": " + numOfSuccs[left]+" succs. = "+succSetString(left));

    ln();

    sln("Showing the predecessors of each right node:");

    for (int right = 0; right < order; right++)
      say("\t"+(right+1)+": " + numOfPreds[right]+" preds. = "+predSetString(right));
  }

  /**
   * Returns a <tt>String</tt> form of the successor set of the given
   * <tt>left</tt> node.
   */
  public final String succSetString (int left)
  {
    StringBuilder buf = new StringBuilder();

    buf.append('{');

    for (IntIterator it = succsIterator(left); it.hasNext();)
      {
	buf.append(Integer.toString(it.next()+1));
	if (it.hasNext())
	  buf.append(',');
      }

    return buf.append('}').toString();
  }

  /**
   * Returns a <tt>String</tt> form of the predecessor set of the given
   * <tt>right</tt> node.
   */
  public final String predSetString (int right)
  {
    StringBuilder buf = new StringBuilder();

    buf.append('{');

    for (IntIterator it = predsIterator(right); it.hasNext();)
      {
	buf.append(Integer.toString(it.next()+1));
	if (it.hasNext())
	  buf.append(',');
      }

    return buf.append('}').toString();
  }

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="matchings" href="#contents">Methods for Matchings and Paths</a>
   * </span></h4>
   */

  /**
   * A bipartite graph <tt>B</tt> of order <tt>n</tt> is a triple
   * (<tt>Lefts</tt>, <tt>Rights</tt>, <tt>Edges</tt>) where <tt>Lefts</tt>
   * and <tt>Right</tt> are two distinct sets of <i>nodes</i> of equal size
   * <tt>n</tt>, and <tt>Edges</tt> is a set of <i>edges</i>.
   *
   * <p/>
   *
   * A node is indexed by its position in <tt>{1,...,n}</tt> and is denoted
   * by its index.
   *
   * <p/>
   *
   * An <i>edge</i> <tt>(left,right)</tt> &isin; <tt>{1,...,n}</tt> &times;
   * <tt>{1,...,n}</tt> of this bipartite graph is a pair of left/right node
   * indices.
   *
   * <p/>
   *
   * A <i>matching</i> <tt>M</tt> of this bipartite graph is a set of pairs
   * of left/right node indices (for us, an <tt>ArrayList</tt> of
   * <tt>Edge</tt> objects) such that no two pairs share a node (<i>i.e.</i>,
   * the degree of all nodes in <tt>M</tt> is either <tt>0</tt> or <tt>1</tt>).
   *
   * <p/>
   *
   * A node of this bipartite graph will be said to be <i>used</i> by  a
   * matching <tt>M</tt> if it appears as the left or right node of an edge
   * in <tt>M</tt> (<i>i.e.</i>, if it has degree <tt>1</tt> in <tt>M</tt>).
   *
   * <p/>
   *
   * An <i>alternating path</i> of this bipartite graph with respect to a
   * matching <tt>M</tt> is a set of pairs of left/right node indices (for
   * us, an <tt>ArrayList</tt> of <tt>Edge</tt>s) such that no pair share a
   * node.
   *
   * <p/>
   *
   * A <i>path</i> of this bipartite graph is a sequence of such
   * <i>directed</i> <tt>Edge</tt>s starting at left node where the right
   * node of an edge is the left node of the following
   *
   * <p/>
   *
   *
   * Algorithm for finding a maximum bipartite-graph matching:
   *
   * <ul>
   *
   * <li>Start with empty matching <tt>M</tt>  </li>
   *
   * <li></li>
   *
   * <li></li>
   *
   * </ul>
   *
   */

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="private" href="#contents">Local Private Methods</a>
   * </span></h4>
   */

  /**
   * Return the right node that is the first successor of <tt>left</tt> node,
   * or <tt>-1</tt> if <tt>left</tt> has no successor.
   */
  private int getFirstSucc (int left)
  {
    if (succLists[left].isEmpty())
      return -1;

    return succLists[left].firstElement();
  }

  /**
   * Return the right node that is the last successor of <tt>left</tt> node,
   * or <tt>-1</tt> if <tt>left</tt> has no successor.
   */
  private int getLastSucc (int left)
  {
    if (succLists[left].isEmpty())
      return -1;

    return succLists[left].lastElement();
  }

  /**
   * Return the right node that is the first predecessor of <tt>right</tt>
   * node, or <tt>-1</tt> if <tt>right</tt> has no predecessor.
   */
  private int getFirstPred (int right)
  {
    if (predLists[right].isEmpty())
      return -1;

    return predLists[right].firstElement();
  }

  /**
   * Return the right node that is the last predecessor of <tt>right</tt>
   * node, or <tt>-1</tt> if <tt>right</tt> has no predecessor.
   */
  private int getLastPred (int right)
  {
    if (predLists[right].isEmpty())
      return -1;

    return predLists[right].lastElement();
  }

  /**
   * Initialize the <tt>isEdge</tt> array and successor/predecessor arrays of
   * this <tt>BipartiteGraph</tt> at creation time, setting an entry to
   * <tt>true</tt> iff the corresponding edge exists (<i>i.e.</i>, iff the
   * entry in the <tt>data</tt> array is non-zero at creation time).
   */
  private final void initializeGraphComponents ()
  {
    int order = rows;

    // create the isEdge array
    isEdge = new boolean[order][order];

    // create the arrays containing the numbers of successors/predecessors
    // for each left/right node.
    numOfSuccs = new int[order];
    numOfPreds = new int[order];

    // initialize isEdge, succLists, and numOfSuccs
    succLists = new IntArrayList[order]; // an array of lists of successor indices
    for (int left = 0; left < order; left++)
      {
	succLists[left] = new IntArrayList();
	for (int right = 0; right < order; right++)
	  {
	    if (isEdge[left][right] = (data[left][right] != 0.0))
	      {
		succLists[left].add(right);
		numOfSuccs[left]++;
	      }
	  }
      }

    // System.err.println(">>> Succ Lists:");
    // for (int left = 0; left < order; left++)
    //   {
    // 	System.err.print(">>>\t"+(left+1)+":");
    // 	for (IntIterator it = succsIterator(left); it.hasNext();)
    // 	  System.err.print(" "+(it.next()+1));
    // 	System.err.println();
    //   }

    // initialize predLists and numOfPreds
    predLists = new IntArrayList[order]; // an array of lists of predecessor indices
    for (int right = 0; right < order; right++)
      {
	predLists[right] = new IntArrayList();
	for (int left = 0; left < order; left++)
	  {
	    if (isEdge[left][right])
	      {
		predLists[right].add(left);
		numOfPreds[right]++;
	      }
	  }
      }

    // System.err.println(">>> Pred Lists:");
    // for (int right = 0; right < order; right++)
    //   {
    // 	System.err.print(">>>\t"+(right+1)+":");
    // 	for (IntIterator it = predsIterator(right); it.hasNext();)
    // 	  System.err.print(" "+(it.next()+1));
    // 	System.err.println();
    //   }

    // create and initialize the array containing the first-successor right
    // node of each left node
    firstSuccs = new int[order];
    for (int left = 0; left < order; left++)
      firstSuccs[left] = getFirstSucc(left);

    // System.err.print("firstSuccs:");
    // for (int i = 0; i < order; i++)
    //   System.err.print(" "+(firstSuccs[i]+1));
    // System.err.println();

    // create and initialize the array containing the last-successor right
    // node of each left node
    lastSuccs = new int[order];
    for (int left = 0; left < order; left++)
      lastSuccs[left] = getFirstSucc(left);

    // System.err.print("lastSuccs: ");
    // for (int i = 0; i < order; i++)
    //   System.err.print(" "+(lastSuccs[i]+1));
    // System.err.println();

    // create and initialize the array containing the first-predecessor left
    // node of each right node
    firstPreds = new int[order];
    for (int right = 0; right < order; right++)
      firstPreds[right] = getFirstPred(right);

    // System.err.print("firstPreds:");
    // for (int i = 0; i < order; i++)
    //   System.err.print(" "+(firstPreds[i]+1));
    // System.err.println();

    // create and initialize the array containing the last-predecessor left
    // node of each right node
    lastPreds = new int[order];
    for (int right = 0; right < order; right++)
      lastPreds[right] = getLastPred(right);

    // System.err.print("lastPreds: ");
    // for (int i = 0; i < order; i++)
    //   System.err.print(" "+(lastPreds[i]+1));
    // System.err.println();

    // showComponents();
  }

  /**
   * Return an <tt>IntIterator</tt> for the list of successors of
   * <tt>left</tt> node.
   */
  public IntIterator succsIterator (int left)
  {
    return succLists[left].iterator();
  }

  /**
   * Return an <tt>IntIterator</tt> for the list of predecessors of
   * <tt>right</tt> node.
   */
  public IntIterator predsIterator (int right)
  {
    return predLists[right].iterator();
  }

  /**
   * Return a <tt>String</tt> equal to the square-bracketed row/column
   * <tt>index</tt>.
   */
  private final String headerString (int index)
  {
    return "["+index+"]";
  }

  /**
   * Return a <tt>String</tt> to serve as well-centered column header
   * given a column-header <tt>String</tt> <tt>header</tt>, centering
   * the bracketed column index according to the current
   * <tt>printWidth()</tt>.
   */
  private final String centerColumnHeader (String header)
  {
    int headerWidth = header.length();
    int beforeWidth = (printWidth() - headerWidth) / 2 + 2;
    int afterWidth  = printWidth() - beforeWidth - headerWidth - 1;

    char spaceChar = ' ';

    StringBuilder centeredHeader = new StringBuilder(spaceChar);

    for (int i = 0; i < beforeWidth; i++)
      centeredHeader.append(spaceChar);

    centeredHeader.append(header);

    for (int i = 0; i < afterWidth; i++)
      centeredHeader.append(spaceChar);

    return centeredHeader.append(spaceChar).toString();
  }

  /**
   * This is the character used for showing no edge; defaults to the space
   * char.
   */
  private char noEdgeChar = ' ';

  /**
   * Return a <tt>String</tt> of <tt>noEdgeChar</tt>s fitting the
   * floating-point print width to indicate that there is no edge.
   */
  private String noEdgeString ()
  {
    StringBuilder str = new StringBuilder();

    for (int i = 0; i < printWidth(); i++)
      str.append(noEdgeChar);

    return str.toString();
  }

  /**
   * <h3 align="center"><span style="font-family:arial,helvetica;">
   * <a name="static" href="#contents">Class Components and Methods</a>
   * </span></h3>
   */

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="scomponents" href="#contents">Class Components</a>
   * </span></h4>
   */

  /**
   * This <tt>BipartiteGraph</tt> list of edges.
   */
  static private ArrayList Edges;

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="smethods" href="#contents">Class Methods</a>
   * </span></h4>
   */


}// end BipartiteGraph class

/**
 * <h3 align="center"><span style="font-family:arial,helvetica;">
 * <a name="classes" href="#contents">Local Classes</a>
 * </span></h3>
 */

/**
 * <h4 align="center"><span style="font-family:arial,helvetica;">
 * <a name="edge" href="#contents">Edge Class</a>
 * </span></h4>
 */

/**
 * A local class representing a (<tt>left</tt>,<tt>right</tt>) edge between a
 * left node of index <tt>left</tt>, and a right node of index <tt>right</tt>.
 */
class Edge
{
  int left;
  int right;

  Edge (int left, int right)
  {
    this.left  = left;
    this.right = right;
  }

  boolean startsWith (int left)
  {
    return this.left == left;
  }

  boolean endsWith (int right)
  {
    return this.right == right;
  }

  boolean isFollowedBy (Edge next)
  {
    return right == next.left;
  }

  public boolean equal (Object other)
  {
    if (!(other instanceof Edge))
      return false;

    return (left == ((Edge)other).left) && (right == ((Edge)other).right);
  }

  public String toString ()
  {
    return "("+left+","+right+")";
  }

}// end Edge class

/**
 * <h4 align="center"><span style="font-family:arial,helvetica;">
 * <a name="matching" href="#contents">Matching Class</a>
 * </span></h4>
 */

/**
 * A local class representing a matching of a <tt>BipartiteGraph</tt>.
 */
class Matching
{
  ArrayList matching = new ArrayList();

  BipartiteGraph graph;

  Matching (BipartiteGraph graph)
  {
    this.graph = graph;
  }

  /**
   * Return <tt>true</tt> iff this <tt>Matching</tt> is maximum for its
   * <tt>BipartiteGraph</tt>.
   */
  boolean isMaximum ()
  {
    return matching.size() == graph.rows();
  }

  /**
   * Return <tt>true</tt> iff this <tt>Matching</tt> uses the given
   * <tt>edge</tt>.
   */
  boolean uses (Edge edge)
  {
    return uses(edge.left) || uses(edge.right);
  }

  /**
   * Return <tt>true</tt> iff this <tt>Matching</tt> uses the given
   * <tt>node</tt>.
   */
  boolean uses (int node)
  {
    return degree(node) == 1;
  }

  /**
   * Return the degree of the given <tt>node</tt> in this
   * <tt>Matching</tt>. This is the number of <tt>Edge</tt> objects in this
   * <tt>Matching</tt> for which the given <tt>node</tt> is a <tt>left</tt>
   * or <tt>right</tt>.
   */
  int degree (int node)
  {
    int degree = 0;
    
    for (Iterator it = matching.iterator(); it.hasNext();)
      {
	Edge edge = (Edge)it.next();
	if (edge.left == node || edge.right == node)
	  degree++;
      }

    return degree;
  }

  /**
   * 
   */
  Matching add (Edge edge)
  {
    
    return this;
  }

  public boolean equal (Object other)
  {
    if (!(other instanceof Matching))
      return false;

    // TO FINISH
    return true;
  }

  public String toString ()
  {
    StringBuilder buf = new StringBuilder();
    
    // TO FINISH
    return "{"+buf+"}";
  }

  
}// end Matching class
