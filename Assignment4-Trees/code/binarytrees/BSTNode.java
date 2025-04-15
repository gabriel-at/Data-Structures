package binarytrees;

public class BSTNode<T extends Comparable<T>> extends TNode {
    protected T info; /* my stored data */

    /* Constructor to build new nodes.  Nodes contain the given info. */
    public BSTNode( T info ){
        //Set the parent, left, and right connections to NULL using constructor of TNode
        super( );

        this.info = info;
        this.height = 1;
    }
    
    /* Checks if this Tree is correctly formatted.  Prints when errors occur.  Returns the number errors detected. */
    public long checkNode( boolean print ){
        long errorCount=0;
        BSTNode<T> lchild = this.getLeft();
        BSTNode<T> rchild = this.getRight();

        if( lchild != null ) {
            if( info.compareTo(lchild.info)<0 ) {
                errorCount++;
                if(print)
                    System.out.println( "BST ERROR - "+info+" has left child = "+lchild.info );
            }
            errorCount +=  lchild.checkNode( print );
        }
        if( rchild != null ) {
            if( info.compareTo(rchild.info)>0 ) {
                errorCount++;
                if(print)
                    System.out.println( "BST ERROR - "+info+" has right child = "+rchild.info );
            }
            errorCount += rchild.checkNode( print );
        }
        return errorCount;
    }

    /*
     * Compares the data in the given node to the data in this node
     * Returns -1 if this node comes first
     * Returns 0 if these nodes have equal keys
     * Returns 1 if the given node comes first
     */
    public int compareTo( BSTNode<T> node ){
        return info.compareTo( node.info );
    }

    public String toString() 
    { 
        return "" + this.info; 
    } 

    /* Gets the info of this node */
    public T getInfo( )
    {
        return this.info;
    }

    /* Returns the left/right/parent/sibling of this node */
    @SuppressWarnings("unchecked") //Suppresses warning for this cast
    public BSTNode<T> getLeft( )
    {
        return (BSTNode<T>)super.getLeft();
    }
    @SuppressWarnings("unchecked") //Suppresses warning for this cast
    public BSTNode<T> getRight( )
    {
        return (BSTNode<T>)super.getRight();
    }
    @SuppressWarnings("unchecked") //Suppresses warning for this cast
    public BSTNode<T> getParent( )
    {
        return (BSTNode<T>)super.getParent();
    }
    @SuppressWarnings("unchecked") //Suppresses warning for this cast
    public BSTNode<T> getSibling( )
    {
        return (BSTNode<T>)super.getSibling();
    }

    /* Sets the left/right child of this node to the given node */
    public void setLeft( BSTNode<T> node )
    {
        super.setLeft( node );
    }
    public void setRight( BSTNode<T> node )
    {
        super.setRight( node );
    }
}
