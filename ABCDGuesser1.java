import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * This program utilizes the de Jager formula. First take any random positive
 * real number for u, and positive real numbers greater than 1 for w, x, y and z
 * a, b, c, and d is one of the 17 numbers {-5, -4, -3, -2, -1, -1/2, -1/3,
 * -1/4, 0, 1/4, 1/3, 1/2, 1, 2, 3, 4, 5},
 *
 * w^a times x^b times y^c times z^d. The program goes through different a's,
 * b's, c's, and d's and prints the result of (w^a)(x^b)(y^c)(z^d) that has the
 * lowest relative error. The theory asserts that u can be approximated within a
 * fraction of 1% relative error.
 *
 * @author Defang Ndematebem
 *
 */
public final class ABCDGuesser1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        String uInputFromUser;
        uInputFromUser = in.nextLine();
        boolean bFalse = false;
        while (FormatChecker.canParseDouble(uInputFromUser) == bFalse
                || Double.parseDouble(uInputFromUser) <= 0) {
            out.println("Give a value for u that is a POSITIVE REAL number");
            uInputFromUser = in.nextLine();
        }
        return Double.parseDouble(uInputFromUser);
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        String wxyzInputFromUser;
        wxyzInputFromUser = in.nextLine();
        boolean bFalse = false;
        while (FormatChecker.canParseDouble(wxyzInputFromUser) == bFalse
                || Double.parseDouble(wxyzInputFromUser) <= 1) {
            out.println(
                    "Give a value that is a POSITIVE REAL number GREATER THAN 1");
            wxyzInputFromUser = in.nextLine();
        }
        return Double.parseDouble(wxyzInputFromUser);
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

        //Mostly to get rid of magic numbers, but makes it easy to remove
        //or add numbers to be computed
        String numberOfVariables = "wxyz";
        //w is element 0, x element 1, y element 2, z element 3
        double[] wxyz = new double[numberOfVariables.length()];
        double[] bestExponent = new double[numberOfVariables.length()];

        //u is a positive not 0, user number to approximate to
        double u;
        //exponent values for w, x , y, and z to be taken to the power of
        final double[] abcdPOWERS = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3,
                -1 / 4, 0, 1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };

        //Essentially 17^4 which = 83521
        // int possibleGuesses = (int) Math.pow(abcdPOWERS.length, wxyz.length);

        int[] counterWXYZ = new int[numberOfVariables.length()];

        double estimate = 0.0;
        double percentError = 0.0;
        double updatedPercentError = 100; //not sure if value matters

        out.println("Give a value for u that is a positive real number");
        u = getPositiveDouble(in, out);

        out.println("Give a positive real number not equal to 1.0 for w");
        wxyz[0] = getPositiveDoubleNotOne(in, out);
        out.println("Give a positive real number not equal to 1.0 for x");
        wxyz[1] = getPositiveDoubleNotOne(in, out);
        out.println("Give a positive real number not equal to 1.0 for y");
        wxyz[2] = getPositiveDoubleNotOne(in, out);
        out.println("Give a positive real number not equal to 1.0 for z");
        wxyz[3] = getPositiveDoubleNotOne(in, out);

        while (counterWXYZ[0] < abcdPOWERS.length) {
            while (counterWXYZ[1] < abcdPOWERS.length) {
                while (counterWXYZ[2] < abcdPOWERS.length) {
                    while (counterWXYZ[3] < abcdPOWERS.length) {
                        estimate = Math.pow(wxyz[0], abcdPOWERS[counterWXYZ[0]])
                                * Math.pow(wxyz[1], abcdPOWERS[counterWXYZ[1]])
                                * Math.pow(wxyz[2], abcdPOWERS[counterWXYZ[2]])
                                * Math.pow(wxyz[3], abcdPOWERS[counterWXYZ[3]]);

                        percentError = (Math.abs(estimate - u) / u) * 100;
                        if (percentError < 1) {
                            if (percentError < updatedPercentError) {
                                bestExponent[0] = counterWXYZ[0];
                                bestExponent[1] = counterWXYZ[1];
                                bestExponent[2] = counterWXYZ[2];
                                bestExponent[3] = counterWXYZ[3];
                                updatedPercentError = percentError;
                            }
                        }
                        counterWXYZ[3]++;
                    }
                    counterWXYZ[3] = 0;
                    counterWXYZ[2]++;
                }
                counterWXYZ[2] = 0;
                counterWXYZ[1]++;
            }
            counterWXYZ[1] = 0;
            counterWXYZ[0]++;
        }

        estimate = Math.pow(wxyz[0], abcdPOWERS[(int) bestExponent[0]])
                * Math.pow(wxyz[1], abcdPOWERS[(int) bestExponent[1]])
                * Math.pow(wxyz[2], abcdPOWERS[(int) bestExponent[2]])
                * Math.pow(wxyz[3], abcdPOWERS[(int) bestExponent[3]]);

        out.println(
                "To within a percent error of " + updatedPercentError + "%");
        out.println("and the following exponent values: ");

        out.println("Exponent for " + wxyz[0] + " = "
                + abcdPOWERS[(int) bestExponent[0]]);
        out.println("Exponent for " + wxyz[1] + " = "
                + abcdPOWERS[(int) bestExponent[1]]);
        out.println("Exponent for " + wxyz[2] + " = "
                + abcdPOWERS[(int) bestExponent[2]]);
        out.println("Exponent for " + wxyz[3] + " = "
                + abcdPOWERS[(int) bestExponent[3]]);

        out.println("Approximation utilizing the de Jager formula for " + u
                + " is equivalent to about " + estimate);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
