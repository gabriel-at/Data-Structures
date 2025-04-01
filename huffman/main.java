public class main {
    // this is just for testing
    public static void main(String[] args) {
        huffman huff = new huffman("Beans and cheese");

        System.out.println(huff.freq);
        System.out.println(huff.sorted);
    }
}
