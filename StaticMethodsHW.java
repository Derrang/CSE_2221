import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo but using static methods for HW3
 *
 * @Defang Ndematebem
 *
 */
public final class StaticMethodsHW {

    /**
     * No argument constructor--private to prevent instantiation.
     */

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {
        /*
         * Create pseudo-random number generator
         */
        Random rnd = new Random1L();
        Random rnd2 = new Random1L();

        //basically returns number that falls in radius

        /*
         * Declare counters and initialize them
         */
        int ptsInInterval = 0, ptsInSubinterval = 0;

        /*
         * Generate points and count how many fall in [0.0,1) radius
         */
        while (ptsInInterval < n) {
            double x = 2 * rnd.nextDouble();
            double y = 2 * rnd2.nextDouble();
            /*
             * Increment total number of generated points
             */
            ptsInInterval++;
            /*
             * Calls pointIsInCircle method, read the method for more details
             */
            if (pointIsInCircle(x, y) == true) {
                ptsInSubinterval++;
            }
        }
        return ptsInSubinterval;
    }

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        double radius = Math
                .sqrt(Math.pow(xCoord - 1, 2) + Math.pow(yCoord - 1, 2));
        /*
         * Now with a value for radius, check if the point is within the circle
         * by being less than 1. If so, the return value will be true, else
         * false
         */
        return (radius < 1);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points fired: ");
        int n = input.nextInteger();

        double ptsInSubinterval = numberOfPointsInCircle(n);

        output.println("Points: " + n);
        output.println("Subinterval points: " + ptsInSubinterval);

        /*
         * Estimate percentage of points generated in [0.0,2.0)square area along
         * x and y fall within the radius of the circle
         */
        double area = 4 * (ptsInSubinterval / n);
        output.println("Estimate of area: " + area);
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }
}
