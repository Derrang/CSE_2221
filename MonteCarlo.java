import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
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

        /*
         * Declare counters and initialize them
         */
        int ptsInInterval = 0, ptsInSubinterval = 0;
        /*
         * Create pseudo-random number generator
         */
        Random rnd = new Random1L();
        Random rnd2 = new Random1L();
        /*
         * Generate points and count how many fall in [0.0,1) radius
         */
        while (ptsInInterval < n) {
            /*
             * Generate pseudo-random numbers in [0.0,2.0) [0.0,2.0) interval
             */
            double x = 2 * rnd.nextDouble();
            double y = 2 * rnd2.nextDouble();
            /*
             * Increment total number of generated points
             */
            double radius = Math
                    .pow((Math.pow(x, 2) - 1) + (Math.pow(y, 2) - 1), .5);

            ptsInInterval++;
            /*
             * Check if point is in the circle (area of pi) interval and
             * increment counter if it is
             */
            if (radius < 1) {
                ptsInSubinterval++;
            }
        }
        /*
         * Estimate percentage of points generated in [0.0,2.0)square area along
         * x and y fall within the radius of the circle
         */
        double area = (ptsInInterval - ptsInSubinterval);
        output.println("Estimate of area: " + (area / ptsInInterval));
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }
}
