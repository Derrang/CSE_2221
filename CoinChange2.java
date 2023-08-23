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
public final class CoinChange2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange2() {
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

        //For dollar, half-dollar, quarter, dime, nickel, penny
        int[] coinValues = { 100, 50, 25, 10, 5, 1 };
        int[] coinCounter = new int[6];
        // String[] coinNames = {}; use later

        /**
         * Somehow.. use only one loop that utilizes arrays So we have
         * userCents, we keep subtracting the largest array so coinValues[0]
         * first, and add to coinCounter[0] , then if userCents - coinValues[0]
         * is less than 0, then go on to coinValues[1] and start counting on
         * coinCounter[1]
         */

        /*
         * Put your main program code here
         */
        out.println("How many cents you got boi? ");
        userCents = in.nextInteger();
//keep subtracting 100 while not dropping bellow 0
        while (userCents > 0) {
            if (userCents >= coinValues[0]) {
                userCents = userCents - coinValues[0];
                ++coinCounter[0];
            } else if (userCents >= coinValues[1]) {
                userCents = userCents - coinValues[1];
                ++coinCounter[1];
            } else if (userCents >= coinValues[2]) {
                userCents = userCents - coinValues[2];
                ++coinCounter[2];
            } else if (userCents >= coinValues[3]) {
                userCents = userCents - coinValues[3];
                ++coinCounter[3];
            } else if (userCents >= coinValues[4]) {
                userCents = userCents - coinValues[4];
                ++coinCounter[4];
            } else if (userCents >= coinValues[5]) {
                userCents = userCents - coinValues[5];
                ++coinCounter[5];
            }
        }
        out.println("Dollar(s): " + coinCounter[0]);
        out.println("Half-Dollar(s): " + coinCounter[1]);
        out.println("Quarter(s): " + coinCounter[2]);
        out.println("Dime(s): " + coinCounter[3]);
        out.println("Nickel(s): " + coinCounter[4]);
        out.println("Pennies/Penny: " + coinCounter[5]);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
