/*
 * XMLDefaultDirectories.java
 *
 * Created on February 20, 2001, 1:48 PM
 */

package xml;
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
public class XMLDefaultDirectories extends javax.swing.JPanel {

    /** Creates new form XMLDefaultDirectories */
    public XMLDefaultDirectories() {
        initComponents ();
        TopSystemDirectory.setDefaultValue("/home/reaction/cvs/AnalysisII/Stable");
        TopSystemDirectory.setValueName("Top Directory of System");
        currentDevDirectory.setDefaultValue("/home/reaction/cvs/AnalysisII/Develop");
        currentDevDirectory.setValueName("Top Directory of development system");
        dataDirectory.setDefaultValue("/home/reaction/cvs/AnalysisII/Stable/data");
        dataDirectory.setValueName("The default data directory");
        xmlDirectory.setDefaultValue("/home/reaction/cvs/AnalysisStable/xml");
        xmlDirectory.setValueName("The top XML development directory");
        originalDevelopmentDir.setDefaultValue("/usr/local/Software/Analysis/Develop");
        originalDevelopmentDir.setValueName("The original development directory");
        
    }
    public void read(Node topnode)  throws IOException {
    for(Node child = topnode.getFirstChild();child != null;child = child.getNextSibling()) {
        if(child.getNodeType() == Node.ELEMENT_NODE) {
            String name = child.getNodeName();
            System.out.println("XMLDefaultDirectories: " + name);
            if(name.equals("TopSystemDirectory"))
                TopSystemDirectory.setDefaultValue(getAttribute(child,
                        "/usr/local/Software/Analysis/AnalysisII"));
            else if(name.equals("currentDevDirectory"))
                currentDevDirectory.setDefaultValue(getAttribute(child,
                        "/usr/local/Software/Analysis/DevII"));
            else if(name.equals("dataDirectory"))
                dataDirectory.setDefaultValue(getAttribute(child,
                        "/usr/local/Software/Analysis/data"));
            else if(name.equals("xmlDirectory"))
                xmlDirectory.setDefaultValue(getAttribute(child,
                        "/usr/local/Software/Analysis/xml"));
            else if(name.equals("originalDevelopmentDir"))
                originalDevelopmentDir.setDefaultValue(getAttribute(child,
                        "/usr/local/Software/Analysis/Develop"));
        }
    }
    }
    String getAttribute(Node node,String defaultname) {
      NamedNodeMap nodemap = node.getAttributes();
      String name = null;
      int length = nodemap.getLength();
      if(length > 0) {
          Attr attr = (Attr) nodemap.item(0);
          name = attr.getValue();
          System.out.println("Attribute Set: " + name);
      } else {
          System.out.println("Default Name used: " + defaultname);
          name = defaultname;
      }
      return name;
    }
    
    public Node write(Document doc) {
        Element topnode = doc.createElement("XMLDefaultDirectories");
        writeAttribute(doc,topnode,"TopSystemDirectory",TopSystemDirectory.getValue());
        writeAttribute(doc,topnode,"currentDevDirectory",currentDevDirectory.getValue());
        writeAttribute(doc,topnode,"dataDirectory",dataDirectory.getValue());
        writeAttribute(doc,topnode,"xmlDirectory",xmlDirectory.getValue());
        writeAttribute(doc,topnode,"originalDevelopmentDir",originalDevelopmentDir.getValue());
        return topnode;
    }    
    void writeAttribute(Document doc, Node topnode,String name, String attr) {
        Element element = doc.createElement(name);
        Attr attrnode = doc.createAttribute("value");
        attrnode.setValue(attr);
        element.setAttributeNode(attrnode);
        topnode.appendChild(element);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        TopSystemDirectory = new react.common.StandardName();
        currentDevDirectory = new react.common.StandardName();
        dataDirectory = new react.common.StandardName();
        xmlDirectory = new react.common.StandardName();
        originalDevelopmentDir = new react.common.StandardName();
        setLayout(new java.awt.BorderLayout());
        
        
        jPanel1.setLayout(new java.awt.GridLayout(5, 1));
          jPanel1.setPreferredSize(new java.awt.Dimension(300, 250));
          jPanel1.setMinimumSize(new java.awt.Dimension(300, 250));
          
          TopSystemDirectory.setLayout(new java.awt.GridLayout(2, 3));
            TopSystemDirectory.setToolTipText("Top System Directory of standard libs and include, etc.");
            jPanel1.add(TopSystemDirectory);
            
            
          currentDevDirectory.setLayout(new java.awt.GridLayout(2, 3));
            currentDevDirectory.setToolTipText("Directory holding packages under development");
            jPanel1.add(currentDevDirectory);
            
            
          dataDirectory.setLayout(new java.awt.GridLayout(2, 3));
            dataDirectory.setToolTipText("Default Data Directory");
            jPanel1.add(dataDirectory);
            
            
          xmlDirectory.setLayout(new java.awt.GridLayout(2, 3));
            jPanel1.add(xmlDirectory);
            
            
          originalDevelopmentDir.setLayout(new java.awt.GridLayout(2, 3));
            jPanel1.add(originalDevelopmentDir);
            
            jTabbedPane1.addTab("Top", null, jPanel1, "Standard top directories needed");
          
          
        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jPanel1;
    public react.common.StandardName TopSystemDirectory;
    public react.common.StandardName currentDevDirectory;
    public react.common.StandardName dataDirectory;
    public react.common.StandardName xmlDirectory;
    public react.common.StandardName originalDevelopmentDir;
    // End of variables declaration//GEN-END:variables

}
