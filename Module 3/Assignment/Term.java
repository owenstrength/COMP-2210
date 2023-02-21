import java.util.Comparator;

/**
 * Autocomplete term representing a (query, weight) pair.
 * 
 */
public class Term implements Comparable<Term> {

    public String queryF = "";
    public Long weightF = null;

    /**
     * Initialize a term with the given query and weight.
     * This method throws a NullPointerException if query is null,
     * and an IllegalArgumentException if weight is negative.
     */
    public Term(String query, long weight) {
        if (weight < 0) {
            throw new IllegalArgumentException();
        }
        if (query == null) {
            throw new NullPointerException();
        }

        queryF = query;
        weightF = weight;

    }

    /**
     * Compares the two terms in descending order of weight.
     */
    public static Comparator<Term> byDescendingWeightOrder() {
        Comparator<Term> descendingWeightOrder = new Comparator<Term>() {
            public int compare(Term t1, Term t2) {
                return t2.weightF.compareTo(t1.weightF);
            }
        };
        return descendingWeightOrder;
    }

    /**
     * Compares the two terms in ascending lexicographic order of query,
     * but using only the first length characters of query. This method
     * throws an IllegalArgumentException if length is less than or equal
     * to zero.
     */
    public static Comparator<Term> byPrefixOrder(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException();
        }

        Comparator<Term> PrefixOrder = new Comparator<Term>() {
            public int compare(Term t1, Term t2) {
                String t1Trim = t1.queryF;
                String t2Trim = t2.queryF;
                if (length < t1.queryF.length()) {
                    t1Trim = t1Trim.substring(0, length);
                }

                if (length < t2.queryF.length()) {
                    t2Trim = t2Trim.substring(0, length);
                }

                return t1Trim.compareToIgnoreCase(t2Trim);
            }
        };
        return PrefixOrder;
    }

    /**
     * Compares this term with the other term in ascending lexicographic order
     * of query.
     */
    @Override
    public int compareTo(Term other) {
        return this.queryF.compareToIgnoreCase(other.queryF);
    }

    /**
     * Returns a string representation of this term in the following format:
     * query followed by a tab followed by weight
     */
    @Override
    public String toString() {
        return queryF + "\t" + weightF.toString();
    }

}
