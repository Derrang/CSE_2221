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
 * Uses for loops instead of while loops.
 *
 * @author Defang Ndematebem
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
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
     * Displays the result of the formula.
     *
     * @param out
     *            the output stream
     * @param updatedPercentError
     *            the most recently saved percent error
     * @param abcdPOW
     *            the possible exponent powers
     * @param bestExponent
     *            the possible exponent powers
     * @param wxyz
     *            user given numbers to be taken to the power of abcdPOW
     * @param estimate
     *            the estimated value of u within the updatedPercentError
     * @param u
     *            the user given number to be estimated
     */
    private static void printResults(SimpleWriter out,
            double updatedPercentError, final double[] abcdPOW,
            double bestExponent[], double wxyz[], double estimate, double u) {
        estimate = Math.pow(wxyz[0], abcdPOW[(int) bestExponent[0]])
                * Math.pow(wxyz[1], abcdPOW[(int) bestExponent[1]])
                * Math.pow(wxyz[2], abcdPOW[(int) bestExponent[2]])
                * Math.pow(wxyz[3], abcdPOW[(int) bestExponent[3]]);

        out.println(); //for visual space
        out.println(
                "To within a percent error of " + updatedPercentError + "%");
        out.println("and the following exponent values: ");

        out.println("Exponent for " + wxyz[0] + " = "
                + abcdPOW[(int) bestExponent[0]]);
        out.println("Exponent for " + wxyz[1] + " = "
                + abcdPOW[(int) bestExponent[1]]);
        out.println("Exponent for " + wxyz[2] + " = "
                + abcdPOW[(int) bestExponent[2]]);
        out.println("Exponent for " + wxyz[3] + " = "
                + abcdPOW[(int) bestExponent[3]]);

        out.println("Approximation utilizing the de Jager formula for " + u
                + " is equivalent to about " + estimate);
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

        //w is element 0, x element 1, y element 2, z element 3
        String numberOfVariables = "wxyz";
        double[] wxyz = new double[numberOfVariables.length()];
        double[] bestExponent = new double[numberOfVariables.length()];

        //u is a positive not 0, user number to approximate to
        double u;
        //exponent values for w, x , y, and z to be taken to the power of
        final double[] abcdPOW = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3, -1 / 4,
                0, 1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };

        int[] countWXYZ = new int[numberOfVariables.length()];

        double estimate = 0.0;
        double percentError = 0.0;
        double updatedPercentError = 100;

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

        //For the sake of understanding the code latter I chose to not shorten
        //the variable names more than I have done here.
        for (countWXYZ[0] = 0; countWXYZ[0] < abcdPOW.length; countWXYZ[0]++) {
            for (countWXYZ[1] = 0; countWXYZ[1] < abcdPOW.length; countWXYZ[1]++) {
                for (countWXYZ[2] = 0; countWXYZ[2] < abcdPOW.length; countWXYZ[2]++) {
                    for (countWXYZ[3] = 0; countWXYZ[3] < abcdPOW.length; countWXYZ[3]++) {
                        estimate = Math.pow(wxyz[0], abcdPOW[countWXYZ[0]])
                                * Math.pow(wxyz[1], abcdPOW[countWXYZ[1]])
                                * Math.pow(wxyz[2], abcdPOW[countWXYZ[2]])
                                * Math.pow(wxyz[3], abcdPOW[countWXYZ[3]]);

                        percentError = (Math.abs(estimate - u) / u) * 100;
                        if (percentError < 1) {
                            if (percentError < updatedPercentError) {
                                bestExponent[0] = countWXYZ[0];
                                bestExponent[1] = countWXYZ[1];
                                bestExponent[2] = countWXYZ[2];
                                bestExponent[3] = countWXYZ[3];
                                updatedPercentError = percentError;
                            }
                        }
                    }
                }
            }
        }
        printResults(out, updatedPercentError, abcdPOW, bestExponent, wxyz,
                estimate, u);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
