/*
 * XMLScripts.java
 *
 * Created on February 20, 2001, 1:40 PM
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
public class XMLScripts extends javax.swing.JPanel {

    /** Creates new form XMLScripts */
    public XMLScripts() {
        initComponents ();
        initRootSystem.setDefaultValue("InitializeRootSystem.sh");
        initRootSystem.setValueName("Initial Setup of System");
        initEnvironment.setDefaultValue("InitializePackage.sh");
        initEnvironment.setValueName("Initial Setup of a Package Environment");
        initModuleDir.setDefaultValue("InitializeModule.sh");
        initModuleDir.setValueName("Initial Directory Setup of a Module");
        initialModuleXSLParse.setDefaultValue("ParseToXML.sh");
        initialModuleXSLParse.setValueName("Parse Original Module");
        fillModule.setDefaultValue("ProcessModule.sh");
        fillModule.setValueName("Parse with XSL to fill in module information");
        manipulateModule.setDefaultValue("ManipulateModules.sh");
        manipulateModule.setValueName("Compile and manipulate module information");
        exampleSetup.setDefaultValue("SetupStandardExample.sh");
        exampleSetup.setValueName("Setup Standard Example in test directory");
    }
    public void read(Node topnode)  throws IOException {
    for(Node child = topnode.getFirstChild();child != null;child = child.getNextSibling()) {
        if(child.getNodeType() == Node.ELEMENT_NODE) {
            String name = child.getNodeName();
            System.out.println("XMLScripts: " + name);
            if(name.equals("initRootSystem."))
                initRootSystem.setDefaultValue(getAttribute(child,
                        "InitializeRootSystem.sh"));
            else if(name.equals("initEnvironment"))
                initEnvironment.setDefaultValue(getAttribute(child,
                        "InitializePackage.sh"));
            else if(name.equals("initModuleDir"))
                initModuleDir.setDefaultValue(getAttribute(child,
                        "InitializeModule.sh"));
            else if(name.equals("initialModuleXSLParse"))
                initialModuleXSLParse.setDefaultValue(getAttribute(child,
                        "ParseToXML.sh"));
            else if(name.equals("fillModule"))
                fillModule.setDefaultValue(getAttribute(child,
                        "ProcessModule.sh"));
            else if(name.equals("manipulateModule"))
                manipulateModule.setDefaultValue(getAttribute(child,
                        "ManipulateModules.sh"));
            else if(name.equals("exampleSetup"))
                manipulateModule.setDefaultValue(getAttribute(child,
                        "exampleSetup.sh"));
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
        Element topnode = doc.createElement("XMLScripts");
        writeAttribute(doc,topnode,"initRootSystem",initRootSystem.getValue());
        writeAttribute(doc,topnode,"initEnvironment",initEnvironment.getValue());
        writeAttribute(doc,topnode,"initModuleDir",initModuleDir.getValue());
        writeAttribute(doc,topnode,"initialModuleXSLParse",initialModuleXSLParse.getValue());
        writeAttribute(doc,topnode,"fillModule",fillModule.getValue());
        writeAttribute(doc,topnode,"manipulateModule",manipulateModule.getValue());
        writeAttribute(doc,topnode,"exampleSetup",exampleSetup.getValue());
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
        sysInit = new javax.swing.JPanel();
        initRootSystem = new react.common.StandardName();
        initEnvironment = new react.common.StandardName();
        initModuleDir = new react.common.StandardName();
        initialModuleXSLParse = new react.common.StandardName();
        jPanel1 = new javax.swing.JPanel();
        fillModule = new react.common.StandardName();
        manipulateModule = new react.common.StandardName();
        exampleSetup = new react.common.StandardName();
        setLayout(new java.awt.BorderLayout());
        
        
        sysInit.setLayout(new java.awt.GridLayout(5, 1));
          sysInit.setPreferredSize(new java.awt.Dimension(300, 250));
          sysInit.setMinimumSize(new java.awt.Dimension(300, 250));
          
          initRootSystem.setLayout(new java.awt.GridLayout(2, 3));
            initRootSystem.setToolTipText("Initialization Script for System");
            sysInit.add(initRootSystem);
            
            
          initEnvironment.setLayout(new java.awt.GridLayout(2, 3));
            sysInit.add(initEnvironment);
            
            
          initModuleDir.setLayout(new java.awt.GridLayout(2, 3));
            sysInit.add(initModuleDir);
            
            
          initialModuleXSLParse.setLayout(new java.awt.GridLayout(2, 3));
            sysInit.add(initialModuleXSLParse);
            
            jTabbedPane1.addTab("SysInit", null, sysInit, "Initialize Total System Directories from Scratch");
          
          
        jPanel1.setLayout(new java.awt.GridLayout(5, 1));
          jPanel1.setName("Modules");
          
          fillModule.setLayout(new java.awt.GridLayout(2, 3));
            jPanel1.add(fillModule);
            
            
          manipulateModule.setLayout(new java.awt.GridLayout(2, 3));
            jPanel1.add(manipulateModule);
            
            
          exampleSetup.setLayout(new java.awt.GridLayout(2, 3));
            jPanel1.add(exampleSetup);
            
            jTabbedPane1.addTab("Modules", null, jPanel1, "Scripts having to do with module manipulation");
          
          
        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel sysInit;
    public react.common.StandardName initRootSystem;
    public react.common.StandardName initEnvironment;
    public react.common.StandardName initModuleDir;
    public react.common.StandardName initialModuleXSLParse;
    private javax.swing.JPanel jPanel1;
    public react.common.StandardName fillModule;
    public react.common.StandardName manipulateModule;
    public react.common.StandardName exampleSetup;
    // End of variables declaration//GEN-END:variables

}