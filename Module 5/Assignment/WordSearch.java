import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.event.ListDataEvent;

public class WordSearch implements WordSearchGame {

    private TreeSet<String> lexicon;
    private String[][] board;
    private int size;

    private SortedSet<String> allWords;

    public WordSearch() {
        lexicon = null;
        size = 4;
        board = new String[size][size];
    }

    /**
     * Loads the lexicon into a data structure for later use.
     * 
     * @param fileName A string containing the name of the file to be opened.
     * @throws IllegalArgumentException if fileName is null
     * @throws IllegalArgumentException if fileName cannot be opened.
     */
    public void loadLexicon(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException();
        }

        try {
            lexicon = new TreeSet<String>();
            Scanner file = new Scanner(new File(fileName));
            while (file.hasNext()) {
                lexicon.add(file.next().toUpperCase());
            }
            file.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Stores the incoming array of Strings in a data structure that will make
     * it convenient to find words.
     * 
     * @param letterArray This array of length N^2 stores the contents of the
     *                    game board in row-major order. Thus, index 0 stores the
     *                    contents of board
     *                    position (0,0) and index length-1 stores the contents of
     *                    board position
     *                    (N-1,N-1). Note that the board must be square and that the
     *                    strings inside
     *                    may be longer than one character.
     * @throws IllegalArgumentException if letterArray is null, or is not
     *                                  square.
     */
    public void setBoard(String[] letterArray) {
        if (letterArray == null) {
            throw new IllegalArgumentException();
        }

        double n = Math.sqrt(Double.valueOf(letterArray.length));
        if (n % 1 > 0.0001) {
            throw new IllegalArgumentException();
        }

        board = new String[(int) n][(int) n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = letterArray[cnt++];
            }
        }
        size = (int) n;
    }

    /**
     * Creates a String representation of the board, suitable for printing to
     * standard out. Note that this method can always be called since
     * implementing classes should have a default board.
     */
    public String getBoard() {
        String boardStr = "";
        for (int i = 0; i < size; i++) {
            boardStr += "\n| ";
            for (int j = 0; j < size; j++) {
                boardStr += board[i][j] + " ";
            }
            boardStr += "|";
        }
        return boardStr;
    }

    /**
     * Retrieves all scorable words on the game board, according to the stated game
     * rules.
     * 
     * @param minimumWordLength The minimum allowed length (i.e., number of
     *                          characters) for any word found on the board.
     * @return java.util.SortedSet which contains all the words of minimum length
     *         found on the game board and in the lexicon.
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException    if loadLexicon has not been called.
     */
    public SortedSet<String> getAllScorableWords(int minimumWordLength) {
        // words must be at least min length
        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }

        allWords = new TreeSet<String>();
        LinkedList<Integer> temp = new LinkedList<Integer>();
        // look at board for all words
        for (int i = 0; i < (size * size); i++) {
            temp.add(i);
            String tempStr = toWord(temp);
            if (isValidWord(tempStr) && tempStr.length() >= minimumWordLength) {
                allWords.add(tempStr);
            }
            if (isValidPrefix(tempStr)) {
                search(temp, minimumWordLength);
            }
            temp.clear();

        }

        return allWords;
    }

    /**
     * Takes an integer list and creates its corresponding word.
     *
     * @param listIn Integer list representing word
     * @return word in string form
     */
    public String toWord(LinkedList<Integer> listIn) {
        String word = "";
        for (int i : listIn) {
            word += new Position(i).getLetter();
        }
        return word;
    }

    /**
     * Recursive dfs search method with backtracking.
     *
     * @param wordPre The word being built and checked against the lexicon
     * @param min     Minimum word length
     * @return The updated word being built
     */
    private LinkedList<Integer> search(LinkedList<Integer> wordPre, int min) {
        Position[] neighbors = new Position(wordPre.getLast()).neighbors(wordPre);
        for (Position p : neighbors) {
            if (p == null) {
                break;
            }
            wordPre.add(p.getIndex());
            String wordStr = toWord(wordPre);
            if (isValidPrefix(wordStr)) {
                if (isValidWord(wordStr) && wordStr.length() >= min) {
                    allWords.add(wordStr);
                }
                search(wordPre, min);
            } else {
                wordPre.removeLast();
            }
        }

        wordPre.removeLast();
        return wordPre;
    }

    /**
     * Recursive dfs search method with backtracking.
     *
     * @param wordPre The word being built and checked against the lexicon
     * @param min     Minimum word length
     * @return The updated word being built
     */
    private LinkedList<Integer> searchForWord(LinkedList<Integer> wordPre, int index, String wordToCheck) {
        // if no current path. search for starting letter. if no current letter return
        // null
        // if there is a path then search for all of the neightbors if the the neighbor
        // is a
        // match then keep searching.
        // if the current path isn't a prefix for the word to find. break and backtrack.
        // (recurse)
        // repeat until either the word is found OR return null and word is not found.

        if (wordPre.size() > 0 && !wordToCheck.equals(toWord(wordPre))) {
            Position[] neighbors = new Position(wordPre.getLast()).neighbors(wordPre);
            for (Position p : neighbors) {
                if (p == null) {
                    break;
                }
                wordPre.add(p.getIndex());
                String wordStr = toWord(wordPre);
                if (wordToCheck.equals(wordStr)) {
                    break;
                }
                if (wordToCheck.startsWith(wordStr)) {
                    searchForWord(wordPre, p.getIndex(), wordToCheck);
                } else {
                    wordPre.removeLast();
                }
            }
        }

        if (wordPre.size() == 0) {
            while (index < size * size) {
                if (wordToCheck.startsWith(new Position(index).getLetter())) {
                    wordPre.add(index);
                    searchForWord(wordPre, index, wordToCheck);
                }
                index++;
            }
            return wordPre;
        }

        if (toWord(wordPre).equals(wordToCheck)) {
            return wordPre;
        } else {
            wordPre.removeLast();
            return wordPre;
        }
    }

    /**
     * Class for traversing board. Used in DFS function
     */
    private class Position {
        private int x;
        private int y;
        private int index;
        private String letter;

        /** Constructs a Position with coordinates (x,y). */
        Position(int indexIn) {
            this.index = indexIn;
            if (index == 0) {
                this.x = 0;
                this.y = 0;
            } else {
                this.x = index % size;
                this.y = index / size;
            }
            this.letter = board[y][x];
        }

        Position(int xIn, int yIn) {
            this.x = xIn;
            this.y = yIn;
            this.index = (y * size) + x;
            this.letter = board[y][x];
        }

        private boolean isValid(int iIn, int jIn) {
            return (iIn >= 0) && (iIn < size) && (jIn >= 0) && (jIn < size);
        }

        public Position[] neighbors(LinkedList<Integer> visited) {
            Position[] nbrs = new Position[8];
            int count = 0;

            for (int i = this.x - 1; i <= this.x + 1; i++) {
                for (int j = this.y - 1; j <= this.y + 1; j++) {
                    if (!((i == this.x) && (j == this.y))) {
                        if (isValid(i, j) && !visited.contains((j * size) + i)) {
                            Position p = new Position(i, j);
                            nbrs[count++] = p;
                        }
                    }
                }
            }
            return nbrs;

        }

        public String getLetter() {
            return letter;
        }

        public int getIndex() {
            return index;
        }

    }

    /**
     * Computes the cummulative score for the scorable words in the given set.
     * To be scorable, a word must (1) have at least the minimum number of
     * characters,
     * (2) be in the lexicon, and (3) be on the board. Each scorable word is
     * awarded one point for the minimum number of characters, and one point for
     * each character beyond the minimum number.
     *
     * @param words             The set of words that are to be scored.
     * @param minimumWordLength The minimum number of characters required per word
     * @return the cummulative score of all scorable words in the set
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException    if loadLexicon has not been called.
     */
    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }
        int score = 0;
        Iterator<String> iter = words.iterator();
        while (iter.hasNext()) {
            String curr = iter.next();
            if (curr.length() >= minimumWordLength && isValidWord(curr) && !isOnBoard(curr).isEmpty()) {
                score += 1 + (curr.length() - minimumWordLength);
            }
        }
        return score;
    }

    /**
     * Determines if the given word is in the lexicon.
     * 
     * @param wordToCheck The word to validate
     * @return true if wordToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException    if loadLexicon has not been called.
     */
    public boolean isValidWord(String wordToCheck) {
        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }
        return lexicon.contains(wordToCheck);
    }

    /**
     * Determines if there is at least one word in the lexicon with the
     * given prefix.
     * 
     * @param prefixToCheck The prefix to validate
     * @return true if prefixToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if prefixToCheck is null.
     * @throws IllegalStateException    if loadLexicon has not been called.
     */
    public boolean isValidPrefix(String prefixToCheck) {
        if (prefixToCheck == null) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }

        String temp = lexicon.ceiling(prefixToCheck);

        if (temp == null) {
            return false;
        }

        return temp.startsWith(prefixToCheck);
    }

    /**
     * Determines if the given word is in on the game board. If so, it returns
     * the path that makes up the word.
     * 
     * @param wordToCheck The word to validate
     * @return java.util.List containing java.lang.Integer objects with the path
     *         that makes up the word on the game board. If word is not on the game
     *         board, return an empty list. Positions on the board are numbered from
     *         zero
     *         top to bottom, left to right (i.e., in row-major order). Thus, on an
     *         NxN
     *         board, the upper left position is numbered 0 and the lower right
     *         position
     *         is numbered N^2 - 1.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException    if loadLexicon has not been called.
     */
    public List<Integer> isOnBoard(String wordToCheck) {
        // write a dfs function to search for word. must be able to backtrack
        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }
        // search for word
        LinkedList<Integer> pathList = new LinkedList<Integer>();
        List<Integer> finalPath = searchForWord(pathList, 0, wordToCheck);
        return finalPath;

    }

}
