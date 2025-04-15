package binarytrees;

public class TNode {
    protected TNode pLeft;
    protected TNode pRight;
    protected TNode pParent;
    protected int height;

    /* Constructor to build new TNodes.  All of the pointers are initially null. */
    public TNode( ){
        //All nodes start with no connections 
        this.pParent = null;
        this.pLeft   = null;
        this.pRight  = null;
        this.height = 1;
    }

    /* Returns the height of the given node */
    public static int getHeight( TNode root )
    {
        if( root==null )
            return 0;
        return root.getHeight();
    }

    /* Returns the height of this node */
    private int getHeight( )
    {
        return this.height;
    }

    /* Returns the taller child node */
    public TNode getTallerChild() {
        if( TNode.getHeight(pLeft)>TNode.getHeight(pRight) )
            return pLeft;
        else
            return pRight;         
    }

    /* Prints the tree starting at this node.  The int is to track the current level of the tree we're on. */
    public static void drawTree( TNode node, int level, char c ){
        if( node == null )
            return ;

        drawTree( node.getRight(), level+1, '/' );

        for( int i=0; i<level; i++ ){
            System.out.printf( "        " );
        }
        if( node.getRight()!=null || node.getLeft()!=null )
            System.out.printf( "%c-------+--", c );
        else
            System.out.printf( "%c----------", c );
        System.out.println( node );

        drawTree( node.getLeft(), level+1, '\\' );
    }

    public String toString() 
    { 
        return ""+this.height; 
    } 

    /* Returns the left/right/parent/sibling of this node */
    public TNode getLeft( )
    {
        return pLeft;
    }
    public TNode getRight( )
    {
        return pRight;
    }
    public TNode getParent( )
    {
        return pParent;
    }
    public TNode getSibling( )
    {
        if( this.pParent == null )
            return null;

        else if( this.pParent.pLeft == this )
            return this.pParent.pRight;

        else if( this.pParent.pRight == this )
            return this.pParent.pLeft;

        return null;
    }

    /* Sets the left/right child of this node to node */
    public void setLeft( TNode node )
    {
        this.pLeft = node;
        if( node!=null )
            node.pParent = this;
    }
    public void setRight( TNode node )
    {
        this.pRight = node;
        if( node!=null )
            node.pParent = this;
    }
}
