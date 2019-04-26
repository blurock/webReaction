/*
 * RWXMLOptions.java
 *
 * Created on June 24, 2001, 8:32 PM
 */

package xml;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;
import java.io.*;
import blurock.coreobjects.DataSetOfObjectsClass;
import blurock.core.RWManager;

/**
 *
 * @author  reaction
 * @version 
 */
public class RWXMLOptions extends javax.swing.JPanel {
    XMLOptions xmlOptions;
    /** Creates new form RWXMLOptions */
    public RWXMLOptions(XMLOptions opts) {
        xmlOptions = opts;
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        readButton = new javax.swing.JButton();
        writeButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        fileName = new javax.swing.JTextField();
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));
        
        readButton.setLabel("Read Defaults");
          readButton.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  readButtonMouseClicked(evt);
              }
          }
          );
          jPanel1.add(readButton);
          
          
        writeButton.setLabel("Write Defaults");
          writeButton.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  writeButtonMouseClicked(evt);
              }
          }
          );
          jPanel1.add(writeButton);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel1, gridBagConstraints1);
        
        
        jPanel2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        jPanel2.setBorder(new javax.swing.border.TitledBorder("Default File"));
        
        fileName.setText("./XMLDefaults.xml");
          gridBagConstraints2 = new java.awt.GridBagConstraints();
          gridBagConstraints2.gridwidth = 0;
          gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
          jPanel2.add(fileName, gridBagConstraints2);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel2, gridBagConstraints1);
        
    }//GEN-END:initComponents

  private void writeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_writeButtonMouseClicked
// Add your handling code here:
      writeDefaults();
  }//GEN-LAST:event_writeButtonMouseClicked

  private void readButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readButtonMouseClicked
// Add your handling code here:
      DataSetOfObjectsClass classes = 
        new DataSetOfObjectsClass(1,"SetOfObjects","Set of Objects Class");
      RWManager rw = new RWManager(classes);
      String name = fileName.getText();
      try {
        Document doc = rw.parseXMLFile(name);
        
        Node modulenode = (Node) doc;
        System.out.println("getModuleNode() 1:  " + modulenode.getNodeName());
        modulenode = modulenode.getFirstChild();
        xmlOptions.read(modulenode);
        
      } catch(IOException io) {
          System.err.println(io);
      }
  }//GEN-LAST:event_readButtonMouseClicked

  void writeDefaults() {
      try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        Document doc = db.newDocument();
      
        Node topnode = xmlOptions.write(doc);
        doc.appendChild(topnode);
        DOMSource src = new DOMSource(doc);
        String name = fileName.getText();
        StreamResult result = new StreamResult(new FileOutputStream(name));
        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer trans = tfactory.newTransformer();
        trans.transform(src,result);
      } catch(ParserConfigurationException ex) {
        System.err.println("Parser Configuration Exception");
      } catch(TransformerException tex) {
          System.err.println("Transform Exception");
      } catch(IOException fex) {
          System.err.println("File not Found");
      }
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel jPanel1;
  private javax.swing.JButton readButton;
  private javax.swing.JButton writeButton;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JTextField fileName;
  // End of variables declaration//GEN-END:variables

}