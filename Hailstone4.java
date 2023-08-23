import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Take user input with any integer value greater than 0. If x even, x/2 If x is
 * odd, 3x + 1. This process is additive. HS4 specifically outputs max value
 * computed AND asks the user if they would like to proceed with computing the
 * HS series again
 *
 * @DefangNdematebem
 *
 */
public final class Hailstone4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone4() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer. Also should output the maximum value of the series
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int itsOne = 1;
        int numberOfTerms = 1;
        int highestValue = n;

        out.println("Term 1: " + n);

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

            if (n > highestValue) {
                highestValue = n;
            }
        }
        out.println("Number of terms: " + numberOfTerms);
        out.println("Highest value: " + highestValue);
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

        int x; //number the user would wish to computer
        String userChoice;

        out.println("Enter 'y' if you would like to compute a hailstone "
                + "series, anything else to quit");
        userChoice = in.nextLine();
        //ask user if they would like to do hailstone, quit if no
        //if yes, do hailstone, and once it finishes ask again if yes

        while (userChoice.equals("y")) {
            out.println("Give an positive integer to start with: ");
            x = in.nextInteger();
            generateSeries(x, out);
            out.println("\nEnter 'y' if you would like to compute a hailstone "
                    + "series, anything else to quit");
            userChoice = in.nextLine();
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
