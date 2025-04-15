package binarytrees;

public class Pair < S extends Comparable < S > , T extends Comparable < T >> {
    S low;
    T high;

    /* creates a Pair from the given x, y values */
    public Pair(S x, T y) {
        this.low = x;
        this.high = y;
    }

    /*
     * Compares the x in the given Pair to the x in this Pair
     * Returns -1 if this Pair comes first
     * Returns 0 if these pairs have equal x values
     * Returns 1 if the given Pair comes first
     */
    public int compareTo(Pair < S, T > node) {
        if (this.low.compareTo(node.low) == 0)
            return this.high.compareTo(node.high);
        return this.low.compareTo(node.low);
    }

    public boolean equals(Pair < S, T > node) {
        return this.compareTo(node) == 0;
    }

    @Override
    @SuppressWarnings("unchecked") //Suppresses warning for cast to Pair<S,T>
    public boolean equals(Object aThat) {
        if (this == aThat) //Shortcut the future comparisons if the locations in memory are the same
            return true;
        if (!(aThat instanceof Pair))
            return false;
        Pair < S, T > that = (Pair < S, T > ) aThat;
        return this.equals(that); //Use above equals method
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 7;
        hash = prime * hash + ((low == null) ? 0 : low.hashCode());
        hash = prime * hash + ((high == null) ? 0 : high.hashCode());
        return hash; /* generate a good hash code for this Pair using prime numbers */
    }

    /* Returns a String representing this Pair */
    public String toString() {
        return "(" + low + ", " + high + ")";
    }

}