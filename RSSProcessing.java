import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * This program inputs an XML RSS (version 2.0) feed from a given URL and
 * outputs various elements of the feed to the console.
 *
 * @author Put your name here
 *
 */
public final class RSSProcessing {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSProcessing() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of the {@code XMLTree} matching the
     *         given tag or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of the {@code XMLTree} matching the
     *   given tag or -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";
        int counter = 0;
        int getChildElement = -1;
        int numOfChildren = xml.numberOfChildren();

        while (counter < numOfChildren
                && !xml.child(counter).label().equals(tag)) {
            counter++;
        }
        // If it happens to be more, getChildElement will equal -1
        //indicating the String doesn't exist
        if (counter < numOfChildren) {
            getChildElement = counter;
        }

        return getChildElement;

    }

    /**
     * Processes one news item and outputs the title, or the description if the
     * title is not present, and the link (if available) with appropriate
     * labels.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures out.content = #out.content * [the title (or description) and
     *          link]
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        int counter = 0;
        int counterIsThereTitle = 0;
        int counterDescContent;
        int counterLinkContent; //not needed but want to implement it anyways
        int counterForDesc = 0;
        int numOfChildren = item.numberOfChildren();
        boolean isThereTitleText = false;
        boolean isThereTitleTag = false;
        boolean isThereDesc = false;
        boolean isThereLink = false;
        boolean trueBoolean = true; //there is a chance I'll need this

        while (counter < numOfChildren) {
            //chunk of code bellow finds title, and prints the content of the
            //title tags child,
            if (item.child(counter).label().equals("title")) {
                XMLTree title = item.child(counter);
                while (counterIsThereTitle < title.numberOfChildren()) {

                    XMLTree titleItem = title.child(counterIsThereTitle);
                    if (!titleItem.isTag()) {
                        out.println("Title: " + titleItem.label());
                        isThereTitleText = true;
                    }
                    counterIsThereTitle++;
                }
                if (isThereTitleText != trueBoolean) {
                    out.println("Title tag was found, but String text wasn't");
                }

                isThereTitleTag = true;
            }
            counter++;
        }

        /*
         * If a title was not found, search within the given item for a
         * description
         */
        if (isThereTitleTag != trueBoolean) {
            while (counterForDesc < numOfChildren) {
                if (item.child(counterForDesc).label().equals("description")) {
                    XMLTree description = item.child(counterForDesc);
                    out.println("<title> tag was straight up not found");
                    out.println("Description: " + description.child(0).label());
                    isThereDesc = true;
                }
                counterForDesc++;
            }
            if (isThereDesc != trueBoolean) {
                out.println("<description> was not found");
            }
        }

        /*
         * So LETS SAY number of children under item is 2, child 1 = title
         *
         * when counter is 0, the label wont equal "title", counter is now 1
         *
         * NOW it will equal "title" and print the title at that counter
         *
         * counter++ still occurs, counter = 2
         */

        int counter2 = 0;
        int numOfChildren2 = item.numberOfChildren();
        while (counter2 < numOfChildren2) {
            if (item.child(counter2).label().equals("link")) {
                XMLTree link = item.child(counter2);
                out.println("Link: " + link.child(0).label());
                isThereLink = true;
            }
            counter2++;
        }
        if (isThereLink != trueBoolean) {
            out.println("<link> was not found");
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open I/O streams.
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Input the source URL. For example: https://news.yahoo.com/rss/
         */
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();

        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */
        XMLTree xml = new XMLTree1(url);
        xml.display();

        /*
         * Extract <channel> element.
         */
        XMLTree channel = xml.child(0);

        /*
         * output title, link, and description
         */
        int titleElement;
        int descElement;
        int linkElement;
        titleElement = getChildElement(channel, "title");
        descElement = getChildElement(channel, "description");
        linkElement = getChildElement(channel, "link");
        out.println();
        out.println(
                "The Title, Link, and Description of given RSS Feed bellow: ");
        out.println("Title: " + channel.child(titleElement).child(0).label());
        out.println(
                "Description: " + channel.child(descElement).child(0).label());
        out.println("Link: " + channel.child(linkElement).child(0).label());
        out.println();

        /*
         * for each item, output title (or description, if title is not
         * available) and link (if available)
         *
         * TODO: ADDITIONAL ACTIVITES - Modify processItem (including updating
         * the comments) so that, in addition to title (or description) and
         * link, it also outputs publication date (tag pubDate) and source (tag
         * source) with the source URL (attribute url of source tag). If any of
         * these elements are not present, output <element> not present (where
         * <element> is replaced by the name of the missing tag).
         */
        int counter = 0;
        int numOfChildren = channel.numberOfChildren();

        while (counter < numOfChildren) {
            if (channel.child(counter).label().equals("item")) {
                XMLTree item = channel.child(counter);
                processItem(item, out);
                out.println(); //space to improve readability in console
            }
            counter++;
        }

        /*
         * Close I/O streams.
         */
        in.close();
        out.close();
    }
}
