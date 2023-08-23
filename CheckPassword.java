import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Defang Ndematebem
 *
 */
public final class CheckPassword {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CheckPassword() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param passwordCandidate
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String passwordCandidate,
            SimpleWriter out) {
        SimpleReader in = new SimpleReader1L();
        int satisfiesAtLeast3 = 0; //when this be

        //check if password is at least 8 characters long
        while (passwordCandidate.length() < 8) {
            out.println("ERROR: Password is less than 8 characters,"
                    + " more characters are needed: ");
            passwordCandidate = in.nextLine();
        }

        if (containsUpperCaseLetter(passwordCandidate) == true) {
            ++satisfiesAtLeast3;
        } else {
            out.println("Lacking at least an uppercase letter...");
        }
        if (containsLowerCaseLetter(passwordCandidate) == true) {
            ++satisfiesAtLeast3;
        } else {
            out.println("Lacking at least a lowercase letter...");
        }
        if (containsDigit(passwordCandidate) == true) {
            ++satisfiesAtLeast3;
        } else {
            out.println("Lacking at least a digit...");
        }
        if (containsSpecialCharacter(passwordCandidate) == true) {
            ++satisfiesAtLeast3;
        } else {
            out.println("Lacking a at least a special character...");
        }

        if (satisfiesAtLeast3 < 3) {
            out.println("Password failed to meet the "
                    + "aformentioned conditions, try again...");
            out.println("Give a String as a password: ");
            String userPassword = in.nextLine();
            checkPassword(userPassword, out);
        } else {
            out.println("Your password is good to go!");
        }
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String str) {
        int indexNum = 0;
        int lengthOfPassword = str.length() - 1; //this is so it matches index
        boolean yesThereIsAnUpperCase = false;
//stuff gets funny here...
//Character.isUpperCase returns a boolean based on character
// charAt(indexNum) indexes the character of the String at that moment
        while (indexNum <= lengthOfPassword) {
            if (Character.isUpperCase(str.charAt(indexNum)) == true) {
                yesThereIsAnUpperCase = true;
            }
            ++indexNum;
        }
        return yesThereIsAnUpperCase;
    }

//index 5 is really unit 6 of password, so if index 7 is unit 8 of password length
    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String str) {
        int indexNum = 0;
        int lengthOfPassword = str.length() - 1; //this is so it matches index
        boolean yesThereIsAnLowerCase = false;
//stuff gets funny here...
//Character.isUpperCase returns a boolean based on character
// charAt(indexNum) indexes the character of the String at that moment
        while (indexNum <= lengthOfPassword) {
            if (Character.isLowerCase(str.charAt(indexNum)) == true) {
                yesThereIsAnLowerCase = true;
            }
            ++indexNum;
        }
        return yesThereIsAnLowerCase;
    }

    /**
     * Checks if the given String contains an digit.
     *
     * @param str
     *            the String to check
     * @return true if str contains an digit, false otherwise
     */
    private static boolean containsDigit(String str) {
        int indexNum = 0;
        int lengthOfPassword = str.length() - 1; //this is so it matches index
        boolean yesThereIsAnDigit = false;
//stuff gets funny here...
//Character.isUpperCase returns a boolean based on character
// charAt(indexNum) indexes the character of the String at that moment
        while (indexNum <= lengthOfPassword) {
            if (Character.isDigit(str.charAt(indexNum)) == true) {
                yesThereIsAnDigit = true;
            }
            ++indexNum;
        }
        return yesThereIsAnDigit;
    }

    private static boolean containsSpecialCharacter(String str) {
        String specialChar = "!@#$%^&*()_-+={}[]:;,.?";
        int indexForChar = 0;
        int indexNum = 0;
        int lengthOfSpecials = specialChar.length() - 1;
        int lengthOfPassword = str.length() - 1;
        boolean yesThereIsSpecialChar = false;
//This will loop through whole password
//However, at each element, it will go through the entire password, and
//if one happens to be a char, this method will return true
        while (indexNum <= lengthOfPassword) {
            while (indexForChar < lengthOfSpecials) {
                if (specialChar.charAt(indexForChar) == str.charAt(indexNum)) {
                    yesThereIsSpecialChar = true;
                }
                ++indexForChar;
            }
            ++indexNum;
        }
        return yesThereIsSpecialChar;
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

        String userPassword;

        out.println("Give a String as a password: ");
        userPassword = in.nextLine();

        checkPassword(userPassword, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
