package utils;

import java.io.Serializable;

public class Pair<A, B> implements Serializable {

    private final A first;
    private final B second;

    public Pair(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }

    public int hashCode() {
        int hFirst = 0;
        if(first != null) {
            hFirst = first.hashCode();
        }
        int hSecond = 0;
        if(second != null) {
            hSecond = second.hashCode();
        }
        return (hFirst + hSecond) * hSecond + hFirst;
    }

    public boolean equals(Object other) {
        if (other instanceof Pair) {
            Pair otherPair = (Pair) other;
            return (
                    (this.first == otherPair.first ||
                            (this.first != null && otherPair.first != null
                            && this.first.equals(otherPair.first)))
                    && (this.second == otherPair.second
                            || (this.second != null && otherPair.second != null
                            && this.second.equals(otherPair.second)))
            );
        }
        return false;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

}