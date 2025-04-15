package binarytrees;

public class RedBlackTree<T extends Comparable<T>> extends BSTree<T> {

    /* creates a new empty Tree and returns a pointer to it */
    public RedBlackTree()
    {
        //Set the root to NULL using constructor of Tree
        super();
    }
    
    /* Returns the root node of this tree */
    @SuppressWarnings("unchecked")
    public RedBlackNode<T> getRoot()
    {
        return (RedBlackNode<T>)this.root;
    }
    
    /* Creates a new node for the given info and inserts it into the Tree */
    @Override
    public void insert( T info )
    {
        RedBlackNode<T> newNode = new RedBlackNode<T>( info );
        insert( newNode ); 
        rebalanceTree( newNode );     
    }

    /* Checks if this Tree is correctly formatted.  Prints when errors occur. */
    @Override
    public long checkTreeStructure( boolean print ){
        if( this.getRoot()==null )
            return 0; //no tree to check

        long errors = this.getRoot().checkNode( print );

        if( this.getRoot().color == Color.RED ) {
            //System.out.println( "ERROR - root node is red" );
            errors++;
        }
        
        return errors;
    }

    /* (Not required for our class - but feel free to mess with it if you want)
     * rebalanceTree
     * input: node to start fixing the tree from (climbing up towards the root)
     * output: none
     * 
     * Modifies the tree to ensure it follows the 5 rules of red-black trees
     */
    void rebalanceTree( RedBlackNode<T> x )
    {

        /* Check if x is the root and recolor it if it's RED (you can return after fixing it) */

        // loop that rebalances up the tree until you reach the root
        /* while ( fill in your condition ) */
        {

            /* Check if parent is the root and recolor it if it's RED (you can return after fixing it) */

            //if this node and its parent are both red, do the following:

            //if case 1 - recolor parent and parent's sibling node

            //if case 2 & 3 - fix the zigzag/straight cases with rotations/recolors (you can return after fixing them)

            //move up the tree
        }
    }

}
