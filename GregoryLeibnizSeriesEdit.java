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
public final class GregoryLeibnizSeriesEdit {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private GregoryLeibnizSeriesEdit() {
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
        double prePie = 0.0;
        int n;
        double gregSeries = 0.0;
        double stable1 = 1.0;
        double difference;
        double epsilon = 0.0001;
        int exponent = 0;
        int counter = 1;
        int i = 1;
        out.println("How many terms?");
        n = in.nextInteger();

        while (counter <= n) {
            gregSeries = 4.0 * (Math.pow(stable1, exponent) / i);
            prePie = pie;

            if (exponent % 2 == 0) {
                pie += gregSeries;
                out.println("Pos pie exp value " + exponent);
                out.println("Pos pie " + pie);
            } else {
                pie -= gregSeries;
                out.println("neg pie exp value " + exponent);
                out.println("Neg pie " + pie);
            }

            difference = Math.abs(prePie - pie);
            if (difference < epsilon) {
                break;
            }

            ++counter;
            i += 2;
            ++exponent;

        }
        out.println("Sum: " + pie);

        in.close();
        out.close();
    }
}
