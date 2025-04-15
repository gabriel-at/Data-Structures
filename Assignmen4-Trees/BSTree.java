package binarytrees;

public class BSTree<T extends Comparable<T>> extends Tree {

    /* creates a new empty Tree and returns a pointer to it */
    public BSTree()
    {
        //Set the root to NULL using constructor of Tree
        super();
    }

    /* Returns the root node of this tree */
    @SuppressWarnings("unchecked") //Suppresses warning for this cast
    public BSTNode<T> getRoot()
    {
        return (BSTNode<T>)this.root;
    }

    /* Stores the passed node in the Tree */
    public void insert( BSTNode<T> newNode )
    {
        boolean flag = true;
        BSTNode<T> current = this.getRoot();

        if(current == null)
        {
            this.setRoot( newNode );
            flag = false;
        }

        // find the location to insert at
        while(flag)
        {
            if( newNode.compareTo( current )==0 )
            {
                return; // ignore insertion of duplicates
            }
            else if( newNode.compareTo( current )<0 )
            {
                if( current.getLeft() == null)
                {
                    current.setLeft( newNode );
                    flag = false;
                }
                else // still not at bottom of tree
                {
                    current = current.getLeft();
                }
            }
            else
            {
                if (current.getRight() == null)
                {
                    current.setRight( newNode );
                    flag = false;
                }
                else // still not at bottom of tree
                {
                    current = current.getRight();
                }
            }
        }
        Tree.updateHeight( newNode );
    }

    /* Creates a new node for the given info and inserts it into the Tree */
    public void insert( T info )
    {
        BSTNode<T> newNode = new BSTNode<T>( info );
        insert( newNode ); 
        rebalanceTree( newNode );    
    }

    /* Stores the passed info in the Tree */
    public boolean contains( T info )
    {
        BSTNode<T> current = this.getRoot();
        BSTNode<T> newNode = new BSTNode<T>( info );

        // descend the tree
        while( current != null )
        {
            if( newNode.compareTo( current )==0 )
            {
                return true;
            }
            else if( newNode.compareTo( current )<0 )
            {
                current = current.getLeft();
            }
            else
            {
                current = current.getRight();
            }
        }

        return false;
    }

    /* rebalanceTree
     * input: node to start fixing the tree from (climbing up towards the root)
     * output: none
     * 
     * Override this method to define your method of rebalancing
     */
    void rebalanceTree( BSTNode<T> x )
    {
        //BSTree does no rebalancing
    }

    public long checkTreeStructure(boolean print) {
        if( this.getRoot()==null )
            return 0; //no tree to check

        long errors = this.getRoot().checkNode( print );
        return errors;
    }
}
