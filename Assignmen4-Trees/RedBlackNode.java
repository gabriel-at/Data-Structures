package binarytrees;

/*
 * Node for red-black trees which contains info of type T
 */

enum Color 
{ 
    RED, BLACK; 
} 

public class RedBlackNode<T extends Comparable<T>> extends BSTNode<T> {
    protected Color color;
    protected int blackHeight;

    /* Constructor to build new nodes.  Nodes are created as red and contain the given info. */
    public RedBlackNode( T info ){
        //All nodes start with no connections 
        super( info );

        //All nodes start as red
        this.color = Color.RED;
        this.blackHeight = 0;
    }

    /* Checks if this Tree is correctly formatted.  Prints when errors occur if print parameter is true.  Returns the total number errors. */
    @Override
    public long checkNode( boolean print ){
        long errors     = 0;
        int  leftBlack  = 0;
        int  rightBlack = 0;

        RedBlackNode<T> lchild = this.getLeft();
        RedBlackNode<T> rchild = this.getRight();
        if( lchild != null ) {
            leftBlack = lchild.blackHeight;
        }
        if( rchild != null ) {
            rightBlack = rchild.blackHeight;
        }

        if( leftBlack != rightBlack ) {
            if( print )
                System.out.println( "RB ERROR - left and right nodes have different black height.  Left count = " + leftBlack + " Right count = " + rightBlack );
            errors++;
        }

        if( (lchild != null && this.color == Color.RED && lchild.color == Color.RED) || (rchild != null && this.color == Color.RED && rchild.color == Color.RED) ) {
            if( print )
                System.out.println( "RB ERROR - red node with red child.");
            errors++;
        }

        if( this.color == Color.BLACK )
            this.blackHeight = Math.max(leftBlack,rightBlack) + 1;
        
        return errors+super.checkNode( print ); //super.checkNode tests the BST properties then recursively calls checkNode on the left and right child
    }

    public String toString() 
    { 
        if( this.color == Color.BLACK )
            return this.info + " BLACK"; 
        return this.info + " RED"; 
    } 

    /* Returns the left/right/parent/sibling of this node */
    public RedBlackNode<T> getLeft( )
    {
        return (RedBlackNode<T>)super.getLeft();
    }
    public RedBlackNode<T> getRight( )
    {
        return (RedBlackNode<T>)super.getRight();
    }
    public RedBlackNode<T> getParent( )
    {
        return (RedBlackNode<T>)super.getParent();
    }
    public RedBlackNode<T> getSibling( )
    {
        return (RedBlackNode<T>)super.getSibling();
    }

    /* Sets the left/right child of this node to the given node */
    public void setLeft( RedBlackNode<T> node )
    {
        this.pLeft = node;
    }
    public void setRight( RedBlackNode<T> node )
    {
        this.pRight = node;
    }
}
