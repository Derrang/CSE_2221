import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Take user input with any integer value greater than 0. If x even, x/2 If x is
 * odd, 3x + 1. This process is additive
 *
 * @DefangNdematebem
 *
 */
public final class Hailstone1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone1() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int itsOne = 1;
        int numberOfTerms = 1;

        while (n != itsOne) {

            if (n % 2 == 0) {
                n = n / 2;
                ++numberOfTerms;
                out.println("Term " + numberOfTerms + ": " + n);
            } else {
                n = 3 * n + 1;
                ++numberOfTerms;
                out.println("Term " + numberOfTerms + ": " + n);
            }
        }
        out.println("Number of terms: " + numberOfTerms);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        int x;
        out.println("Give an positive integer to start with: ");
        x = in.nextInteger();

        generateSeries(x, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
