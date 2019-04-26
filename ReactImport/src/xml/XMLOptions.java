/*
 * XMLOptions.java
 *
 * Created on February 20, 2001, 2:38 PM
 */

package xml;
import link.ReactLink;
import java.io.IOException;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author  reaction
 * @version 
 */
public class XMLOptions extends java.lang.Object {

    public XMLDefaultDirectories defaultDirectories = new XMLDefaultDirectories();
    
    public XMLScripts standardScripts = new XMLScripts();
    
    public ReactLink tLink = new ReactLink();
    
    /** Creates new XMLOptions */
    public XMLOptions() {
    }
    public void read(Node topnode) throws IOException {
    for(Node child = topnode.getFirstChild();child != null;child = child.getNextSibling()) {
        if(child.getNodeType() == Node.ELEMENT_NODE) {
            String name = child.getNodeName();
            System.out.println("XMLOptions: " + name);
            if(name.equals("XMLDefaultDirectories"))
                defaultDirectories.read(child);
            else if(name.equals("XMLScripts"))
                standardScripts.read(child);
        }
    }
    }
    public Node write(Document doc)  throws IOException {
        Element topnode = doc.createElement("XMLOptions");
        Node scr = defaultDirectories.write(doc);
        Node dir = standardScripts.write(doc);
        topnode.appendChild(scr);
        topnode.appendChild(dir);
        return topnode;
    }
}
