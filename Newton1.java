import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Asks user if they want to calculate square root, "y" means proceed, else
 * quit. If "y", then prompt user for a positive double. Error of the reported
 * square root should vary no more than 0.01%. Uses the Newton Iteration method
 * for finding the square root.
 *
 * @author: Defang Ndematebm
 *
 */
public final class Newton1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double r = x;
        final double setEPSILON = 0.0001;
        double squareRootEstimation = x;

        /**
         * Basically, we get x from the user, this x is plugged into r, of (r
         * +x/r)/2 calculate result of that. If relative error of result is too
         * much compared to our new x, do it again.
         */
        while ((Math.abs(Math.pow(r, 2) - x) / x) > setEPSILON) {
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
        String userDecision;
        double x;
        double squareRoot;
        out.println("Would you like to compute a square root? "
                + "type 'y' if yes, anything else if no: ");
        userDecision = in.nextLine();

        if (userDecision.equals("y")) {
            out.println("Give a positive double you would like"
                    + " to find the square root of: ");
            x = in.nextDouble();

            if (x <= 0) {
                while (x <= 0) {
                    out.println("ERROR: Give a POSITIVE DOUBLE... ");
                    x = in.nextDouble();
                }
            }
            squareRoot = sqrt(x);
            out.println("Square root of " + x + " using Newton Iteration: "
                    + squareRoot);
        }

        //closes input & output streams
        in.close();
        out.close();
    }

}
