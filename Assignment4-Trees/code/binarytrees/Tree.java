package binarytrees;

public class Tree {
    protected TNode root;

    /* creates a new empty Tree with a null root */
    public Tree()
    {
        this.root = null;
    }

    /* Prints the tree starting at the root of this tree. */
    public void drawTree( ){
        TNode root = this.getRoot();
        TNode.drawTree( root, 0, 'R' );
    }

    /* Get the height of the tree */
    public int getHeight( ){
        if( root == null )
            return 0;
        return TNode.getHeight(root);
    }

    /* Updates the height of this node and those above this one */
    public static void updateHeight( TNode root ){
        while( root != null ){
            int leftcnt  = TNode.getHeight(root.getLeft());
            int rightcnt = TNode.getHeight(root.getRight());
            root.height  = Math.max(leftcnt,rightcnt) + 1;

            //Recursive call to go up the tree
            root = root.getParent();
        }
    }

    /* Performs a left rotation at node oldRoot */
    void leftRotate(TNode oldRoot)
    {
        if( oldRoot==null || oldRoot.getRight()==null )
            throw new NullPointerException("ERROR - Attempt to rotate using null pointers"); 

        TNode newRoot = oldRoot.getRight();
        TNode oldRootParent = oldRoot.getParent();

        if( oldRootParent != null)
        {
            if ( oldRootParent.getRight() == oldRoot )
                oldRootParent.setRight( newRoot );
            else
                oldRootParent.setLeft( newRoot );
        }
        else{
            this.root = newRoot;
            newRoot.pParent = null;
        }
        oldRoot.setRight( newRoot.getLeft() );
        newRoot.setLeft( oldRoot );
        Tree.updateHeight( oldRoot ); //Update the heights of the nodes above the old root in this tree
    }

    /* Performs a right rotation at node oldRoot */
    void rightRotate(TNode oldRoot)
    {
        if( oldRoot==null || oldRoot.getLeft()==null )
            throw new NullPointerException("ERROR - Attempt to rotate using null pointers"); 

        TNode newRoot = oldRoot.getLeft();
        TNode oldRootParent = oldRoot.getParent();

        if( oldRootParent != null)
        {
            if ( oldRootParent.getLeft() == oldRoot )
                oldRootParent.setLeft( newRoot );
            else
                oldRootParent.setRight( newRoot );
        }
        else{
            this.root = newRoot;
            newRoot.pParent = null;
        }
        oldRoot.setLeft( newRoot.getRight() );
        newRoot.setRight( oldRoot );
        Tree.updateHeight( oldRoot ); //Update the heights of the nodes above the old root in this tree
    } 

    /* Returns the root node of this tree */
    public TNode getRoot()
    {
        return this.root;
    }

    /* Updates the root node of this tree */
    public void setRoot( TNode root )
    {
        this.root = root;
    }
}
