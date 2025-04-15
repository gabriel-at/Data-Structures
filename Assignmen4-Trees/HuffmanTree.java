package binarytrees;

import java.lang.Character;
import java.util.*;

public class HuffmanTree extends Tree {
    private String text;
    private String compressedText; /* uses strings for simplicity but would want to use bits in an actual implementation */

    /* creates a Huffman coding Tree from the input array and returns a pointer to it */
    public HuffmanTree( String text )
    {
        //Set the root to NULL using constructor of Tree
        super();
        this.text = text;

        //Calculate frequency for each characters
        HashMap<Character, Integer> charFreq = new HashMap<Character, Integer>();
        for ( char ch: text.toCharArray() ) {
            int cnt = 0;
            if( charFreq.containsKey(ch) )
                cnt = charFreq.get(ch);
            charFreq.put(ch, cnt+1); 
        }

        //Add the characters to a priority queue based on frequency
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
        for(Map.Entry<Character, Integer> entry : charFreq.entrySet()) {
            Character key = entry.getKey();
            Integer freq = entry.getValue();
            pq.add( new HuffmanNode(key.toString(),freq) );
        }

        //Merge nodes of the Huffman Tree until the PQ contains only one node
        HuffmanNode curL = pq.remove();
        while( !pq.isEmpty() ) {
            HuffmanNode curR = pq.remove();

            HuffmanNode mergedNode = new HuffmanNode( curL.getCharacters()+curR.getCharacters(), curL.getFrequency()+curR.getFrequency() );
            mergedNode.setLeft( curL );
            mergedNode.setRight( curR );
            pq.add( mergedNode );

            curL = pq.remove();
        }
        this.setRoot( curL );

        this.compressedText = "";
        for ( char ch: text.toCharArray() ) 
            this.compressedText += this.charToHuffmanString( ch );
    }

    /* Returns the root node of this tree */
    public HuffmanNode getRoot()
    {
        return (HuffmanNode)this.root;
    }

    /* Sets the root node of this tree to the node */
    public void setRoot( HuffmanNode node )
    {
        super.setRoot( node );
    }

    /* Get original input String provided to the construtor */
    public String getOriginalText( )
    {
        return text;
    }

    /* Get Huffman code for the input String computed by the construtor */
    public String getHuffmanCoding( )
    {
        return compressedText;
    }

    /* finds the Huffman code for the char c in the  */
    public String charToHuffmanString( char c )
    {
        HuffmanNode root = this.getRoot();
        return root.charToHuffmanString( c );
    }
    
    public String toString() 
    { 
        return text + " encodded as " + compressedText; 
    } 

}
