import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Takes a user given URL of anXML RSS 2.0 feed and converts it into an HTML
 * file as output.
 *
 * @author Defang Ndematebem
 *
 */
public final class RSSReader {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private RSSReader() {
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
     * Displays the opening tags of the printed HTML file. The following
     * elements should be printed:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tbody>
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires the root of channel is a <channel> tag and out.is_open
     * @ensures out.content will equal #out.content, the HTML "opening" tags
     */
    private static void printHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        String headTitle;
        String description;

        if (getChildElement(channel, "title") != -1) {
            XMLTree titleElement = channel
                    .child(getChildElement(channel, "title"));
            if (titleElement.numberOfChildren() > 0) {
                headTitle = titleElement.child(0).label();
            } else {
                headTitle = "Empty Title";
            }
        } else {
            headTitle = "Empty Title";
        }

        if (getChildElement(channel, "description") != -1) {
            XMLTree descriptionElement = channel
                    .child(getChildElement(channel, "description"));
            if (descriptionElement.numberOfChildren() > 0) {
                description = descriptionElement.child(0).label();
            } else {
                description = "No description";
            }
        } else {
            description = "No description";
        }

        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + headTitle + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(
                "<h1><a href="
                        + channel.child(getChildElement(channel, "link"))
                                .child(0).label()
                        + ">" + headTitle + "</a></h1>");
        out.println("<p>" + description + "</p>");
        out.println("<table border=\"1\">");
        out.println("<tbody>");
        out.println("<tr>");
        out.println("<th>Date</th>");
        out.println("<th>Source</th>");
        out.println("<th>News</th>");
        out.println("</tr>");

        int counter = 0;
        int numOfChildren = channel.numberOfChildren();

        while (counter < numOfChildren) {
            if (channel.child(counter).label().equals("item")) {
                XMLTree item = channel.child(counter);
                processItem(item, out);
            }
            counter++;
        }
    }

    /**
     * Prints the closing tags in the generated HTML file. The following
     * elements will be generated:
     *
     * </tbody>
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content, the HTML "closing" tags
     */
    private static void printFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("</tbody>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     *
     * Currently processes one news item and outputs the title, or the
     * description if the title is not present, and the link (if available) with
     * appropriate labels. Surrounds the printed contents of the item with the
     * following tag:
     * <tr>
     * </tr>
     *
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
        int counterForDesc = 0;
        int numOfChildren = item.numberOfChildren();
        String pubDate = "";
        String linkText = "";
        String descText = "";
        String sourceText = "";
        String sourceURL = "";
        boolean isThereTitleText = false;
        boolean isThereSource = false;
        boolean isThereSourceText = false;
        boolean isThereDate = false;
        boolean isThereDesc = false;
        boolean isThereLink = false;

        out.println("<tr>");
        int counterLink = 0;
        while (counterLink < numOfChildren) {
            if (item.child(counterLink).label().equals("link")) {
                XMLTree link = item.child(counterLink);
                linkText = link.child(0).label();
                isThereLink = true;
            }
            counterLink++;
        }

        int counterDate = 0;
        while (counterDate < numOfChildren) {
            if (item.child(counterDate).label().equals("pubDate")) {
                XMLTree date = item.child(counterDate);
                pubDate = date.child(0).label();
                out.println("<td>" + pubDate + "</td>");
                isThereDate = true;
            }
            counterDate++;
        }
        if (!isThereDate) {
            out.println("<td>pubDate tag had no data</td>");
        }

        int counterSource = 0;
        while (counterSource < numOfChildren) {
            if (item.child(counterSource).label().equals("source")) {
                XMLTree source = item.child(counterSource);
                sourceURL = source.attributeValue("url");
                isThereSource = true;
                /*
                 * runs if there is a source available, then checks for text
                 */
                int counterSourceText = 0;
                while (counterSourceText < source.numberOfChildren()
                        && isThereSource) {
                    XMLTree childOfSource = source.child(counterSourceText);
                    sourceText = childOfSource.label();
                    if (!childOfSource.isTag() && isThereSource) {
                        out.println("<td><a href=" + sourceURL + ">"
                                + sourceText + "</a></td>");
                        isThereSourceText = true;
                    }
                    /*
                     * in this case still display the link as a source does
                     * exist, there is just no text describing the source
                     */
                    if (!isThereSourceText) {
                        out.println("<td><a href=" + sourceURL + ">"
                                + "link found, but no source text"
                                + "</a></td>");
                    }
                    counterSourceText++;
                }
            }
            counterSource++;
        }
        if (!isThereSource) {
            out.println("<td>No source available</td>");
        }

        while (counter < numOfChildren) {
            //chunk of code bellow finds title, and prints the content of the
            //title tags child,
            if (item.child(counter).label().equals("title")) {
                XMLTree title = item.child(counter);
                while (counterIsThereTitle < title.numberOfChildren()) {
                    XMLTree childOfTitle = title.child(counterIsThereTitle);
                    if (!childOfTitle.isTag() && !isThereLink) {
                        out.println("<td>" + childOfTitle.label() + "<td>");
                        isThereTitleText = true;
                    }
                    if (!childOfTitle.isTag() && isThereLink) {
                        out.println("<td><a href=" + linkText + ">"
                                + childOfTitle.label() + "</a></td>");
                        isThereTitleText = true;
                    }
                    counterIsThereTitle++;
                }
            }
            counter++;
        }

        /*
         * If a title not found, search within the given item for a description
         */
        int counterForDescText = 0;
        if (!isThereTitleText) {
            //numOfChildren refers to children within the item
            while (counterForDesc < numOfChildren) {
                //if label at that child is description, proceed into if
                //if not, just go on and look at the next child of item
                if (item.child(counterForDesc).label().equals("description")) {
                    XMLTree description = item.child(counterForDesc);
                    int descNumOfChildren = description.numberOfChildren();
                    //if it isn't a tag, then it's text and thus not blank
                    while (counterForDescText < descNumOfChildren) {
                        if (!description.child(counterForDescText).isTag()
                                && !isThereLink) {
                            descText = description.child(counterForDescText)
                                    .label();
                            out.println("<td>" + descText + "</td>");
                            isThereDesc = true;
                        }

                        //if item had a child link, then link description text
                        if (!description.child(counterForDescText).isTag()
                                && isThereLink) {
                            descText = description.child(counterForDescText)
                                    .label();
                            out.println("<td><a href=" + linkText + ">"
                                    + descText + "</a></td>");
                            isThereDesc = true;
                        }
                        ++counterForDescText;
                    }
                }
                counterForDesc++;
            }
            if (!isThereDesc) {
                // Title AND description were empty
                out.println("<td>No title available</td>");
            }
        }
        out.println("</tr>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Open I/O streams.
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Give a file name, for example: output.html
         */
        out.print("Please give a name for the output file: ");
        String fileName = in.nextLine();

        SimpleWriter printToFile = new SimpleWriter1L(fileName);

        /*
         * Input the source URL. For example: https://news.yahoo.com/rss/
         */
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();

        XMLTree xml = new XMLTree1(url);
        xml.display();

        /*
         * Extract <channel> element.
         */
        XMLTree channel = xml.child(getChildElement(xml, "channel"));

        // Check if the XML input is a valid RSS 2.0 feed
        if (xml.label().equals("rss") && xml.hasAttribute("version")
                && xml.attributeValue("version").equals("2.0")) {

            // Print header and footer
            printHeader(channel, printToFile);
            printFooter(printToFile);
        } else {
            out.println("Invalid RSS 2.0 feed.");
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
