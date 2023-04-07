import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface.
 *
 * @author Owen Strength (ods0005@auburn.edu)
 */
public class Doublets implements WordLadderGame {

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
    /////////////////////////////////////////////////////////////////////////////
    // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
    // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS //
    // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES //
    // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash //
    // table with chaining).
    /////////////////////////////////////////////////////////////////////////////

    private TreeSet<String> lexicon;

    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public Doublets(InputStream in) {
        try {
            lexicon = null;
            Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();
                lexicon.add(str.toUpperCase());
                s.nextLine();
            }
            in.close();
        } catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }

    /**
     * Returns the total number of words in the current lexicon.
     *
     * @return number of words in the lexicon
     */
    public int getWordCount() {
        return 0;
    }

    /**
     * Checks to see if the given string is a word.
     *
     * @param str the string to check
     * @return true if str is a word, false otherwise
     */
    public boolean isWord(String str) {
        return false;
    }

    /**
     * Returns the Hamming distance between two strings, str1 and str2. The
     * Hamming distance between two strings of equal length is defined as the
     * number of positions at which the corresponding symbols are different. The
     * Hamming distance is undefined if the strings have different length, and
     * this method returns -1 in that case. See the following link for
     * reference: https://en.wikipedia.org/wiki/Hamming_distance
     *
     * @param str1 the first string
     * @param str2 the second string
     * @return the Hamming distance between str1 and str2 if they are the
     *         same length, -1 otherwise
     */
    public int getHammingDistance(String str1, String str2) {
        return 0;
    }

    /**
     * Returns all the words that have a Hamming distance of one relative to the
     * given word.
     *
     * @param word the given word
     * @return the neighbors of the given word
     */
    public List<String> getNeighbors(String word) {
        return null;
    }

    /**
     * Checks to see if the given sequence of strings is a valid word ladder.
     *
     * @param sequence the given sequence of strings
     * @return true if the given sequence is a valid word ladder,
     *         false otherwise
     */
    public boolean isWordLadder(List<String> sequence) {
        return false;
    }

    /**
     * Returns a minimum-length word ladder from start to end. If multiple
     * minimum-length word ladders exist, no guarantee is made regarding which
     * one is returned. If no word ladder exists, this method returns an empty
     * list.
     *
     * Breadth-first search must be used in all implementing classes.
     *
     * @param start the starting word
     * @param end   the ending word
     * @return a minimum length word ladder from start to end
     */
    public List<String> getMinLadder(String start, String end) {
        return null;
    }

}
