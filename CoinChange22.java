import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Take user input for change in cents. Output change with dollars, half-dollar,
 * quarters, dimes, nickels, and pennies. Use largest units when possible. A
 * friend helped me simplify this one
 *
 * @author Defang Ndematebem
 *
 */
public final class CoinChange22 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange22() {
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

        // For dollar, half-dollar, quarter, dime, nickel, penny
        int[] coinValues = { 100, 50, 25, 10, 5, 1 };
        int[] coinCounter = new int[6];
        String[] coinNames = { "Dollar(s)", "Half-Dollar(s)", "Quarter(s)",
                "Dime(s)", "Nickel(s)", "Penny/Pennies" };

        out.println("How many cents do you have? ");
        userCents = in.nextInteger();

        int i = 0; // Index for iterating through coinValues array

        while (userCents > 0 && i < coinValues.length) {
            if (userCents >= coinValues[i]) {
                coinCounter[i] = userCents / coinValues[i]; // integers will round down if division isn't clean
                userCents = userCents % coinValues[i]; //stores remainder of userCents after dividing max times
            }
            i++;
        }

        // Output the number of coins of each denomination
        for (int j = 0; j < coinNames.length; j++) {
            out.println(coinNames[j] + ": " + coinCounter[j]);
        }

        in.close();
        out.close();
    }

}
