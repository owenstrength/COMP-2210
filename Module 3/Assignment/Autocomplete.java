import java.util.Arrays;

/**
 * Autocomplete.
 */
public class Autocomplete {

    private Term[] terms;

    /**
     * Initializes a data structure from the given array of terms.
     * This method throws a NullPointerException if terms is null.
     */
    public Autocomplete(Term[] termsIn) {
        if (termsIn == null) {
            throw new NullPointerException();
        }

        terms = termsIn;

    }

    /**
     * Returns all terms that start with the given prefix, in descending order of
     * weight.
     * This method throws a NullPointerException if prefix is null.
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new NullPointerException();
        }

        int i = 0;
        Term[] preTerms = new Term[terms.length];
        for (Term t : terms) {
            if (t.queryF.startsWith(prefix)) {
                preTerms[i] = t;
                i++;
            }
        }

        Term[] preTermsFinal = new Term[i];
        for (int j = 0; j < i; j++) {
            preTermsFinal[j] = preTerms[j];
        }

        Arrays.sort(preTermsFinal, Term.byDescendingWeightOrder());

        return preTermsFinal;
    }

}
