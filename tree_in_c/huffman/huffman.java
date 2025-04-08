import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


public class huffman { 
    private class hnode{
        String c;
        hnode parent;
        hnode left;
        hnode right;

        public hnode(String c){
            this.c = c;
            this.parent = null;
            this.left = null;
            this.right = null;
        }
    }

    HashMap<String, Integer> freq = new HashMap<>();

    ArrayList<String> sorted = new ArrayList<>();

    String phrase;

    public huffman(String text){
        this.phrase = text;

        // int pointer = 0;
        // boolean sorted = false;
        // String s : text.split("")
        for(int i = 0; i < text.split("").length; i++){
            String s = text.split("")[i];
            try{
                freq.put(s, freq.get(s)+1);
            } catch(Exception e){
                freq.put(s, 1);
                sorted.add(s);
            }

        }

        // sort "sorted" arraylist
        Collections.sort(sorted, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2){
                return freq.get(s1) - freq.get(s2);
            }
        });
        
    }

}