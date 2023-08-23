import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Take user input for change in cents. Output change with dollars, half-dollar,
 * quarters, dimes, nickels, and pennies. Use largest units when possible.
 *
 * @author Defang Ndematebem
 *
 */
public final class CoinChange1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange1() {
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
        int userCents;

        final int dOLLARCENTS = 100;
        int dollarCounter = 0;

        final int hALFDOLLARCENTS = 50;
        int halfDollarCounter = 0;

        final int qUARTERCENTS = 25;
        int quarterCounter = 0;

        final int dIMECENTS = 10;
        int dimeCounter = 0;

        final int nICKLECENTS = 5;
        int nickleCounter = 0;

        final int pENNYCENTS = 1;
        int pennyCounter = 0;

        /*
         * Put your main program code here
         */
        out.println("How many cents you got? ");
        userCents = in.nextInteger();
//keep subtracting 100 while not dropping bellow 0
        while (userCents >= dOLLARCENTS) {
            userCents = userCents - dOLLARCENTS;
            dollarCounter++;
        }
        while (userCents >= hALFDOLLARCENTS) {
            userCents = userCents - hALFDOLLARCENTS;
            halfDollarCounter++;
        }
        while (userCents >= qUARTERCENTS) {
            userCents = userCents - qUARTERCENTS;
            quarterCounter++;
        }
        while (userCents >= dIMECENTS) {
            userCents = userCents - dIMECENTS;
            dimeCounter++;
        }
        while (userCents >= nICKLECENTS) {
            userCents = userCents - nICKLECENTS;
            nickleCounter++;
        }
        while (userCents >= pENNYCENTS) {
            userCents = userCents - pENNYCENTS;
            pennyCounter++;
        }
        out.println("Dollar(s): " + dollarCounter);
        out.println("Half-Dollar(s): " + halfDollarCounter);
        out.println("Quarter(s): " + quarterCounter);
        out.println("Dime(s): " + dimeCounter);
        out.println("Nickle(s): " + nickleCounter);
        out.println("Pennies/Penny: " + pennyCounter);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
