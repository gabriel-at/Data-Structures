package binarytrees;
import java.util.*;

public class SegmentTree extends Tree {
    protected ArrayList<Pair<Integer,Integer>> lineSegments;

    /* creates a Segment Tree from the input array of 1d line segments */
    public SegmentTree( ArrayList<Pair<Integer,Integer>> lineSegments )
    {
        this.lineSegments = lineSegments;

        //get set of unique points
        HashSet<SegmentNode> points = new HashSet<SegmentNode>();
        for( Pair<Integer,Integer> p: lineSegments ){
            points.add( new SegmentNode( p.low,p.low ) );
            points.add( new SegmentNode( p.high,p.high ) );
        }

        ArrayList<SegmentNode> sortedPoints = new ArrayList<SegmentNode>( points );
        Collections.sort(sortedPoints);
        buildBalancedTreeFromArray( new LinkedList<SegmentNode>(sortedPoints) );

        for( Pair<Integer,Integer> p: lineSegments ){
            SegmentNode.insertLineSegment( this.getRoot(), p );
        }
    }

    /* Builds a balanced tree from a given sorted array */
    public void buildBalancedTreeFromArray( Queue<SegmentNode> queue )
    {
        Queue<SegmentNode> queueNext = new LinkedList<SegmentNode>();

        while( queue.size()>=2 ){
            SegmentNode left = queue.remove();
            SegmentNode right = queue.remove();
            SegmentNode parent = new SegmentNode( left.range.low, right.range.high );
            parent.setLeft(left);
            parent.setRight(right);
            queueNext.add(parent);

            if( queue.size()<2 ){
                queueNext.addAll( queue );
                queue = queueNext;
                queueNext = new LinkedList<SegmentNode>();
            }
        } 
        root = queue.remove();
    }

    /* Returns the number of line segments that intersect the query point  */
    public int lineStabQuery( int point ){
        return SegmentNode.lineStabQuery( this.getRoot(), point );
    }

    /* Returns the root node of this tree */
    @Override
    public SegmentNode getRoot()
    {
        return (SegmentNode)this.root;
    }

}
