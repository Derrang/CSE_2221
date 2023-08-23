import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author D. Ndematebem
 */
public final class NSquaredPlusmSquared {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private NSquaredPlusmSquared() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        int n = 0;
        int m = 0;
        int nsqu = 0;
        int msqu = 0;
        int areaBound = 0;
        int sumN = 0;
        int sumM = 0;
        int sum = 0;

        out.println("Give n, must be 1 or greater: ");
        n = in.nextInteger();
        out.println("Give m, must be 1 or greater: ");
        m = in.nextInteger();
        out.println("Give an area bound, must be greater than n^2 and m^2 ");
        areaBound = in.nextInteger();

        nsqu = n * n;
        msqu = m * m;

        while (nsqu < areaBound) {
            nsqu = n * n;
            n++;
            sumN = nsqu;
            nsqu = n * n; /**
                           * computing again so that if the new n++ value
                           * squared is not less than areaBound, while loop
                           * won't go through again
                           */
        }

        while (msqu < areaBound) {
            msqu = m * m;
            m++;
            sumM = msqu;
            msqu = m * m;

        }

        sum = sumN + sumM;
        /**
         * Sum of n^2 + m^2 while each iteration of n^2 or m^2 itself is less
         * than areaBound
         */
        out.println("Sum: " + sum);

        in.close();
        out.close();
    }
}
