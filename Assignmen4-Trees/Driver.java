import java.util.ArrayList;
import java.util.Random;

import binarytrees.*; 

public class Driver {
    //Constants for BST tree testing
    public static final boolean PRINT_BST_TREE = false; //Enable this to see the final tree after all inserts are performed
    public static final boolean VERBOSE_TREE_ERRORS = false; //Enable this for specific errors to print as your tree is checked
    public static final int ERROR_TREE_SIZE = 20; //Number of values being inserted into the tree during error checking
    public static final int RUNTIME_TREE_SIZE = 100000; //Number of values being inserted into the tree during run time checking
    public static final int ERROR_TREE_REPEATS = 1000; //Number of times to repeat error checking
    public static final int RUNTIME_TREE_REPEATS = 1; //Number of times to repeat run time checking
    public static final String TREE_TYPE = "AVL"; //String representing the type of tree being tested ("BST", "AVL", "RedBlack")

    //Constants for Huffman tree testing
    public static final String HUFFMAN_STRING = "aabacccadadadadda"; //String to be encoded by the Huffman tree alg
    public static final boolean PRINT_HUFFMAN_TREE = false; //Print the Huffman tree

    //Constants for Segment tree testing
    public static final int[] START_POINTS = {  0, -7, -7, -10, -10, -2, -2,  5,  5, 43,  9,  9, 14, 14, -2, 43, -2, 26 };
    public static final int[] END_POINTS   = { 25, 25, -2,  -2,   5,  5, 26, 26, 52, 52, 46, 25, 25, 18, 18, 46, 38, 38 };
    public static final int[] QUERY_POINTS  = { -10, -7, -2, 0, 5, 9, 14, 18, 25, 26, 38, 43, 46, 52 };
    public static final boolean PRINT_SEGMENT_TREE = false; //Print the Segment tree

    public static void main(String[] args) {
        //Example we worked in class:
        //testTreeInteger( 20 );

        assignment4( );
    }

    public static void testTreeInteger( int numInserts ) {
        int i = 0;
        Integer data;
        Random random = new Random();

        //Create Red-Black Tree that contains Integers
        BSTree<Integer> t = new BSTree<Integer>(); 

        // Time the insert function
        for (i = 1; i < numInserts; i++)
        {
            data = random.nextInt(numInserts); /* generate number from 0 to numInserts-1 */
            System.out.println("Insert #" + i + " inserting " + data);
            if( !t.contains(data) ){
                t.insert( data );
                t.drawTree( );
            }
            else
                System.out.println("Ignoring duplicate: "+data);
            System.out.println();
        }
        BSTNode<Integer> root = t.getRoot();
        System.out.println( );
        System.out.println( "# of values >= 7 in our tree : " + countGT(root,7) );
        System.out.println( );

        while( root!=null ){
            t.setRoot( removeLeaves(root) );
            root = t.getRoot();
            t.drawTree( );
            System.out.println( );
        }
    }

    /* counts values in this subtree >= info */
    public static int countGT( BSTNode<Integer> root, int info ){
        int currentCount = 0;

        if( root==null )
            return 0;

        if( root.getInfo().compareTo( info ) != -1 ) /* compare current node's info to the parameter */
            currentCount = 1;

        currentCount += countGT( root.getLeft(), info );  /* get the left count recursively */
        currentCount += countGT( root.getRight(), info );  /* get the right count recursively */

        return currentCount;
    } 

    /* removes the leaves in the tree rooted at the given node */
    public static BSTNode<Integer> removeLeaves( BSTNode<Integer> root ){

        if( root==null )
            return null;

        if( root.getLeft()==null && root.getRight()==null )
            return null; //prev node should point to null instead of root 

        root.setLeft(  removeLeaves(root.getLeft())  ); 
        root.setRight( removeLeaves(root.getRight()) ); 

        return root;
    } 

    //Tests your code for assignment 4
    public static void assignment4( ) {
        System.out.println("---------------------");
        System.out.println("TEST "+TREE_TYPE+" TREE: ");
        System.out.println("---------------------\n");
        testBSTrees( TREE_TYPE, true, generateArray( ERROR_TREE_SIZE, false ) );
        testBSTrees( TREE_TYPE, false, generateArray( RUNTIME_TREE_SIZE, false ) );
        System.out.println();

        /* Not required - We'll do this in class */
        /*
        System.out.println("---------------------");
        System.out.println("TEST HUFFMAN TREE: ");
        System.out.println("---------------------\n");
        HuffmanTree t = new HuffmanTree( HUFFMAN_STRING );
        if(PRINT_HUFFMAN_TREE)
            t.drawTree();
        System.out.println( t );
        System.out.println("\n");
        */

        /* Not required - We'll do this in class */
        /*
        System.out.println("---------------------");
        System.out.println("TEST SEGMENT TREE: ");
        System.out.println("---------------------\n");
        ArrayList<Pair<Integer,Integer>> lineSegments = new ArrayList<Pair<Integer,Integer>>( ); 
        for(int i=0; i<START_POINTS.length; i++ )
            lineSegments.add( new Pair<Integer,Integer>(START_POINTS[i],END_POINTS[i]) );

        SegmentTree s = new SegmentTree( lineSegments );
        if( PRINT_SEGMENT_TREE )
            s.drawTree();

        if( s.getRoot()!=null )
            for(int i=0; i<QUERY_POINTS.length; i++ )
                System.out.println("Line Stab Query for " + QUERY_POINTS[i] + " returns " + s.lineStabQuery( QUERY_POINTS[i] ) );
        else
            System.out.println( "ERROR - Root of Segment Tree is null" );
        System.out.println( );        
        */
    }

    public static ArrayList<Integer> generateArray( int size, boolean random ) {
        Random r = new Random();

        ArrayList<Integer> values = new ArrayList<Integer>();

        //Generate array of values to insert into the tree
        for( int i=0; i<size; i++ ) {
            int next;
            if( random )
                next = r.nextInt(size*10); //generate a random number from 0 to 10*size-1
            else
                next = i; //Use the next sequential number
            values.add( next );
        }

        return values;
    }
    

    public static void permuteArray( ArrayList<Integer> array ) {
        Random r = new Random();
        int size = array.size();

        //Generate array of values to insert into the tree
        for( int i=0; i<size; i++ ) {
            int swapLocation = r.nextInt(size); //generate a random number from 0 to size-1
            int temp = array.get(i);
            array.set( i, array.get(swapLocation) );
            array.set( swapLocation, temp );
        }
    }

    public static void testBSTrees( String type, boolean check, ArrayList<Integer> values ) {
        BSTree<Integer> t;
        Random r = new Random();
        long errorCount = 0;
        int repeats;
        long start, end;
        
        System.out.print("Insering " + values.size() + " into a "+type+" tree ");
        if( check ){
            System.out.println(" WITH error checking. (error checking adds O(n) time per insert)");
            repeats = ERROR_TREE_REPEATS;        
        }
        else{
            System.out.println("WITHOUT error checking.");
            repeats = RUNTIME_TREE_REPEATS;
        }

        try{
            // Time the insert function
            start = System.currentTimeMillis();
            for( int i=0; i<repeats && errorCount==0; i++ ){ //Test Student code until it fails or it encounters an error, 
                if( type.equals("BST") )
                    t = new BSTree<Integer>();
                else if( type.equals("AVL") )
                    t = new AVLTree<Integer>();
                else //if( type.equals("RedBlack") )
                    t = new RedBlackTree<Integer>();
                
                errorCount += testBSTreeInsert( t, values, check );
                if( check )
                    permuteArray( values );  //Randomize the order of these elements
            }
            end = System.currentTimeMillis();
         
            if( PRINT_BST_TREE && check ) //Set PRINT_BST_TREE to true to print your final tree
                t.drawTree( );     
            
            System.out.printf("Time to insert %d numbers: %.5f seconds.\n", values.size(), ((end - start) / 1000.0)/repeats);
            if( errorCount==0 && check )
                System.out.println( "No errors detected!  Well done!" );
            else if( check )
                System.out.println( errorCount + " errors were detected." );
        }
        catch(StackOverflowError e){
            System.out.println( "StackOverflowError encountered when inserting "+values.size()+" values." );
        }
        System.out.println( );
    }

    public static <T extends Comparable<T>> long testBSTreeInsert( BSTree<T> t, ArrayList<T> values, boolean check ) {
        int i = 1;
        long errorCount = 0;
        long errorStep  = 0; //Error count for this specific step

        for ( T data : values )
        {
            t.insert( data );
            if( check ) {
                errorStep = t.checkTreeStructure( VERBOSE_TREE_ERRORS );
                if( errorStep!=0){
                    System.out.println("Insert #" + i + " inserting " + data);
                    t.drawTree( );
                    System.out.println();
                }
                errorCount += errorStep;
            }
            i++;
        }
        return errorCount;
    }

    @Deprecated
    //Function for creating Strings from an int key value with digits number of symbols
    static String createName( int key, int digits )
    {
        int i;
        String str = "";
        for (i = digits; i > 0 ; i--)
        {
            str += (char) ('0' + key%10);
            key = key / 10;
        }
        return str;
    }

}
