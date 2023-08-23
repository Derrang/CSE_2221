import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeExploration {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires <pre>
     * [the label of the root of xt is a tag]  and
     * [xt has at least one child]  and  out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the label of the middle child
     *  of xt, whether the root of the middle child is a tag or text,
     *  and if it is a tag, the number of children of the middle child]
     * </pre>
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {
        int middleChild = 0;
        boolean itIsTag = true;
        out.println("Number of children in " + xt.label() + ": "
                + xt.numberOfChildren());

        if (xt.numberOfChildren() % 2 == 0) {
            middleChild = xt.numberOfChildren() / 2;
            out.println("Middle child of " + xt.label()
                    + " as root is either child " + middleChild
                    + " which is represented by "
                    + xt.child(middleChild).label() + " or child "
                    + (middleChild - 1) + " which is represented by "
                    + xt.child(middleChild - 1).label());

            if (xt.child(middleChild).isTag() == itIsTag) {
                out.println(xt.child(middleChild).label() + " label is a tag. "
                        + "Number of children in "
                        + xt.child(middleChild).label() + ": "
                        + xt.child(middleChild).numberOfChildren());
            } else if (xt.child(middleChild).isTag() != itIsTag) {
                out.println(xt.child(middleChild).label() + " label is text. ");
            }
            if (xt.child(middleChild - 1).isTag() == itIsTag) {
                out.println(xt.child(middleChild - 1).label()
                        + " label is a tag. " + "Number of children in "
                        + xt.child(middleChild - 1).label() + ": "
                        + xt.child(middleChild - 1).numberOfChildren());
            } else if (xt.child(middleChild - 1).isTag() != itIsTag) {
                out.println(
                        xt.child(middleChild - 1).label() + " label is text. ");
            }

        } else if (xt.numberOfChildren() % 2 != 0) {
            middleChild = xt.numberOfChildren() / 2;
            out.println("Middle child of " + xt.label() + " as root is child "
                    + middleChild + " which is represented by "
                    + xt.child(middleChild).label());

            if (xt.child(middleChild).isTag() == itIsTag) {
                out.println(xt.child(middleChild).label() + " label is a tag. "
                        + "Number of children in "
                        + xt.child(middleChild).label() + ": "
                        + xt.child(middleChild).numberOfChildren());
            } else if (xt.child(middleChild).isTag() != itIsTag) {
                out.println(xt.child(middleChild).label() + " label is text. ");
            }
        }
        out.println();
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

        //toString outputs textual display of XMLTree
        //also can output it to an output stream

        //display opens a new window, displays XML tree
        //similar to the XMLTree viewer application in last lab

        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                        + "extras/instructions/xmltree-model/columbus-weather.xml");
        xml.display();
        //out.println(xml.toString());

        //verifies if the root is a tag, and prints the name of that same root
        out.println(xml.isTag());
        out.println(xml.label());

        //THE CHILDREN

        XMLTree results = xml.child(0);
        out.println(results);

        XMLTree channel = results.child(0);
        out.println(channel);

        out.println("Number of children of the root XMLTree channel: "
                + channel.numberOfChildren());

        XMLTree title = channel.child(1);
        out.println(title);

        XMLTree titleText = title.child(0);
        out.println(titleText);

        XMLTree item = channel.child(12);

        //For challenge after 6,
        //just do out.println(title.child(0));
        //actually that ain't even right, do it again if time

        //THE ATTRIBUTES

        XMLTree astronomy = channel.child(10);
        out.println(astronomy);

        out.println(
                "Within yweather:astronomy, does its attribute contain 'sunset'? "
                        + astronomy.hasAttribute("sunset"));
        out.println(
                "Within yweather:astronomy, does its attribute contain 'midday'? "
                        + astronomy.hasAttribute("midday"));
        out.println();

        //One MORE CHALLENGE
        printMiddleNode(channel, out);
        printMiddleNode(item, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
