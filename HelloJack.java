import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A simple program that prompts the user for their name and says Hello.
 *
 * @author Defang
 *
 */
public final class HelloJack {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private HelloJack() {
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
        /*
         * Put your main program code here
         */
        out.println("Dear user, could you type your name for me?");
        SimpleReader keyboardIn = new SimpleReader1L();
        String name = keyboardIn.nextLine();
        String welcome = "Hello, ";
        out.println(welcome + name + "!");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
        keyboardIn.close();
    }
}
