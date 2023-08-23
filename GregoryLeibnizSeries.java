import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class GregoryLeibnizSeries {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private GregoryLeibnizSeries() {
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

        double pie = 0.0;
        int n;
        double stable1 = 1.0;
        int exponent = 0;
        int i = 1;
        out.println("How many terms?");
        n = in.nextInteger();

        while (i <= n) {
            double gregSeries = 4.0 * Math.pow(stable1, exponent) / i;

            if (exponent % 2 == 0) {
                pie += gregSeries;
            } else {
                pie -= gregSeries;
            }

            i += 2;
            ++exponent;

        }
        out.println("Sum: " + pie);

        in.close();
        out.close();
    }
}
