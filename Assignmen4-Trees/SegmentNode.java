package binarytrees;

public class SegmentNode extends TNode implements Comparable<SegmentNode> {
    protected Pair<Integer,Integer> range; /* the range of this node */
    protected int count; /* number of line segments that cover this node's range but not its parent's range */

    /* Constructor to build new nodes.  Nodes are created with all pointers as null and with the specified range */
    public SegmentNode( Pair<Integer,Integer> range ){
        //Set the parent, left, and right connections to NULL using constructor of TNode
        super( );

        this.range = range;
    }

    public SegmentNode( Integer x ,Integer y ){
        //Set the parent, left, and right connections to NULL using constructor of TNode
        super( );

        this.range = new Pair<Integer,Integer>(x,y);
    }

    /*
     * Compares the data in the given node to the data in this node
     * Returns -1 if this node comes first
     * Returns 0 if these nodes have equal keys
     * Returns 1 if the given node comes first
     */
    public int compareTo( SegmentNode node ){
        return range.compareTo( node.range );
    }

    public boolean equals( SegmentNode node ){
        return this.compareTo( node ) == 0;
    }

    @Override 
    public boolean equals(Object aThat) {
        if (this == aThat) //Shortcut the future comparisons if the locations in memory are the same
            return true;
        if (!(aThat instanceof SegmentNode))
            return false;
        SegmentNode that = (SegmentNode)aThat;
        return this.equals( that ); //Use above equals method
    }

    @Override
    public int hashCode() {
        return range.hashCode();
    }


    /* Returns the left child of this node */
    public SegmentNode getLeft( )
    {
        return (SegmentNode)super.getLeft();
    }

    /* Returns the right child of this node */
    public SegmentNode getRight( )
    {
        return (SegmentNode)super.getRight();
    }

    /* Sets the left child of this node to the given node */
    public void setLeft( SegmentNode node )
    {
        super.setLeft( node );
    }

    /* Sets the right child of this node to the given node */
    public void setRight( SegmentNode node )
    {
        super.setRight( node );
    }

    /* TODO
     * Inserts the given line segment into the segment tree rooted at node  */
    protected static void insertLineSegment( SegmentNode node, Pair<Integer,Integer> lineSegment ){
        //...
    }

    /* TODO
     * Returns the number of line segments that intersect the query point in the tree rooted at node  */
    protected static int lineStabQuery( SegmentNode node, int point ){
        //...
        return -1; //Replace with your return statement
    }

    public String toString() 
    { 
        return this.range.toString() + " count = " + count;
    } 
}