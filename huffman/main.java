public class main {
    // this is just for testing
    public static void main(String[] args) {
        huffman huff = new huffman("AAAAAABBCCDDEEFFFFF");

        System.out.println("Original: " + huff.inputString);
        System.out.println("Encoded String: " + huff.encodedString);
        System.out.println("Conversion Table: " + huff.conversion);
        System.out.println("Letter Freqs: " + huff.frequencies);
    }
}
