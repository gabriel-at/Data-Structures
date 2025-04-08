package huffmanEncode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

class Node implements Comparable<Node>{
    String c;
    int freq;
    Node left;
    Node right;

    Node(String c, int freq, Node left, Node right){
        this.c = c;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public Node(int freq, Node left, Node right) {
        this.c = null; // Non-leaf nodes don't store a character
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node other){
        return this.freq - other.freq;
    }
}

public class huffman{
    public String inputString;
    public Map<String, String> conversion;
    public String encodedString = "";
    public HashMap<String, Integer> frequencies = new HashMap<>();

    public huffman(String input){
        this.inputString = input;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        getFreqs(input, pq);

        Node root = buildTree(pq);

        HashMap<String, String> codes = new HashMap<>();
        genCodes(root, "", codes);

        this.conversion = codes;

        for(int i = 0; i < input.length(); i++){
            encodedString += codes.get(input.substring(i, i+1));
        }

    }

    private void getFreqs(String input, PriorityQueue<Node> pq){
        // HashMap<String, Integer> frequencies = new HashMap<>();
        ArrayList<String> keys = new ArrayList<>();

        for(int i = 0; i < input.split("").length; i++){
            String s = input.split("")[i];
            try{
                frequencies.put(s, frequencies.get(s)+1);
            } catch(Exception e){
                frequencies.put(s, 1);
                keys.add(s);
            }
    
        }

        for(int i = 0; i < keys.size(); i++){
            pq.add(new Node(keys.get(i), frequencies.get(keys.get(i)), null, null));
        }
    }

    // returns root
    private Node buildTree(PriorityQueue<Node> pq){
        while(pq.size() > 1){
            Node left = pq.poll();
            Node right = pq.poll();

            Node merge = new Node(left.freq + right.freq, left, right);

            pq.add(merge);
        }
        return pq.poll();
    }

    private void genCodes(Node node, String currentCode, Map<String, String> codes){
        if(node == null) return;

        if(node.c != null){
            codes.put(node.c, currentCode);
        }

        genCodes(node.left, currentCode + "0", codes);
        genCodes(node.right, currentCode + "1", codes);
    }
    
    public void printCodePairs() {
        for (Entry<String, String> entry : conversion.entrySet()) {
        	System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
