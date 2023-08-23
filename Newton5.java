import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Asks user if they want to calculate square root, "y" means proceed, else
 * quit. If "y", then prompt user for a value for "x" and error. Uses the Newton
 * Iteration method for finding the square root. Works when x = 0. Additionally
 * only does as many iterations as the user desires through "k".
 *
 * @author: Defang Ndematebm
 *
 */
public final class Newton5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton5() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     * Runs only k amount of times as determined by the user.
     *
     * @param x
     *            positive number to compute square root of
     * @param epsilon
     *            maximum percentage error
     * @param k
     *            how many times to run the iteration
     * @return estimate of square root
     */
    private static double sqrt(double x, int k, double epsilon) {
        double r = x;
        SimpleWriter out = new SimpleWriter1L();
        int kth = k;
        int counter = 1;
        double ep = epsilon; //varies based on user input
        double squareRootEstimation = x;

        /**
         * Basically, we get x from the user, this x is plugged into r then run
         * (r +x/r)/2 to calculate square root. If relative error of square root
         * is too much compared to our new x, do it again. Additionally, method
         * only runs for as many iterations as the user desires, or until the
         * desired error has been achieved.
         */
        while (kth > 0) {
            squareRootEstimation = (r + x / r) / 2;
            out.println("Iteration " + counter + ": " + squareRootEstimation);
            r = squareRootEstimation;
            kth--;
            counter++;
            if (Math.abs(Math.pow(r, 2) - x) / x < ep) {
                break;
            }
        }
        out.close();
        return squareRootEstimation;
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
        double x;
        int k;
        double squareRoot;
        double epsilon;

        out.println("Give a value for x: ");
        x = in.nextDouble();

        if (x < 0) {
            out.println("Program terminated... ");
            in.close();
            out.close();
            return;
        }
        out.println("Give an maximum error percentage in decimal form: ");
        epsilon = in.nextDouble();

        out.println("Give an integer k-th root greater than or equal to 2: ");
        k = in.nextInteger();

        if (k < 2) {
            while (k < 2) {
                out.println("ERROR: Give a k INTEGER that is"
                        + " GREATER than OR EQUAL to 2... ");
                k = in.nextInteger();
            }
        }

        squareRoot = sqrt(x, k, epsilon);
        out.println("Root of " + x + " using Newton Iteration " + k
                + " (or less depending on maximum error)" + " times: "
                + squareRoot);

        //close input & output streams
        in.close();
        out.close();
    }
}
