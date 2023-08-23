import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Asks user if they want to calculate square root, "y" means proceed, else
 * quit. If "y", then prompt user for a value for "x" and error. Works when x =
 * 0. Uses the Newton Iteration method for finding the square root.
 *
 * @author: Defang Ndematebm
 *
 */
public final class Newton4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within the relative error
     * determined by the user.
     *
     * @param x
     *            positive number to compute square root of
     * @param epsilon
     *            maximum percentage error
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {
        double r = x;
        double ep = epsilon; //varies based on user input
        double squareRootEstimation = x;

        /**
         * Basically, we get x from the user, this x is plugged into r, of (r
         * +x/r)/2 calculate result of that. If relative error of result is too
         * much compared to our new x, do it again.
         */
        while ((Math.abs(Math.pow(r, 2) - x) / x) > ep) {
            squareRootEstimation = (r + x / r) / 2;
            r = squareRootEstimation;
        }
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

        squareRoot = sqrt(x, epsilon);
        out.println("Sqaure root of " + x + " using Newton Iteration: "
                + squareRoot);

        //close input & output streams
        in.close();
        out.close();
    }
}
