package binarytrees;

public class AVLNode<T extends Comparable<T>> extends BSTNode<T> {

    /* Constructor to build new nodes.  Nodes are created as red and contain the given info. */
    public AVLNode( T info ){
        //Set the parent, left, and right connections to NULL using constructor of BSTNode
        super( info );
    }

    /* Checks if this Tree is correctly formatted.  Prints when errors occur.  Returns the number errors detected. */
    @Override
    public long checkNode( boolean print ){
        long errorCount=0;
        AVLNode<T> lchild = this.getLeft();
        AVLNode<T> rchild = this.getRight();
        int leftHeight  = AVLNode.getHeight(lchild);
        int rightHeight = AVLNode.getHeight(rchild);

        if( Math.abs(leftHeight - rightHeight)>1 ) {
            errorCount++;
            if(print)
                System.out.println( "AVL ERROR - "+lchild+" has height = "+leftHeight+" but "+rchild+" has height = "+rightHeight );
        }

        return errorCount+super.checkNode( print ); //super.checkNode tests the BST properties then recursively calls checkNode on the left and right child
    }

    public String toString() 
    {
        return this.info.toString(); 
    } 

    /* Returns the left/right/parent/sibling of this node */
    public AVLNode<T> getLeft( )
    {
        return (AVLNode<T>)super.getLeft();
    }
    public AVLNode<T> getRight( )
    {
        return (AVLNode<T>)super.getRight();
    }
    public AVLNode<T> getParent( )
    {
        return (AVLNode<T>)super.getParent();
    }
    public AVLNode<T> getSibling( )
    {
        return (AVLNode<T>)super.getSibling();
    }

    /* Sets the left/right child of this node to the given node */
    public void setLeft( AVLNode<T> node )
    {
        super.setLeft( node );
    }
    public void setRight( AVLNode<T> node )
    {
        super.setRight( node );
    }

    /* Returns the taller child node */
    @SuppressWarnings("unchecked")
    public AVLNode<T> getTallerChild() {
        return (AVLNode<T>)super.getTallerChild();      
    }


}
