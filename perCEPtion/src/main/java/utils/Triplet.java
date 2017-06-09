package utils;

import java.io.Serializable;

public class Triplet<A, B, C> implements Serializable {

    private final A first;
    private final B second;
    private final C third;

    public Triplet(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
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
        int hThrid = 0;
        if(third != null) {
            hThrid = third.hashCode();
        }
        return (hFirst + hSecond + hThrid) * hSecond + hFirst + hThrid;
    }

    public boolean equals(Object other) {
        if (other instanceof Triplet) {
            Triplet otherTriplet = (Triplet) other;
            return (
                    (this.first == otherTriplet.first ||
                            (this.first != null && otherTriplet.first != null
                            && this.first.equals(otherTriplet.first)))
                    && (this.second == otherTriplet.second
                            || (this.second != null && otherTriplet.second != null
                            && this.second.equals(otherTriplet.second)))
                    && (this.third == otherTriplet.third
                            || (this.third != null && otherTriplet.third != null
                            && this.third.equals(otherTriplet.third)))
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

    public C getThird() {
        return third;
    }



}
