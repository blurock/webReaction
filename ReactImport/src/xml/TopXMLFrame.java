/*
 * TopXMLFrame.java
 *
 * Created on February 20, 2001, 12:02 PM
 */
package xml;
import utilities.FrameSet;
import utilities.BaseFrame;
import blurock.core.*;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.*;
import xml.structure.BuildClassXML;

/**
 *
 * @author  reaction
 * @version 
 */
public class TopXMLFrame extends javax.swing.JFrame {
        Dimension panelSize;
        DefaultMutableTreeNode topNode = new DefaultMutableTreeNode("Code Generation");

        public MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = frameHierarchy.getRowForLocation(e.getX(),e.getY());
                TreePath path = frameHierarchy.getPathForLocation(e.getX(),e.getY());
                if(path != null) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) 
                    path.getLastPathComponent();
                    String name = (String) node.getUserObject();
                    if(selRow != -1) {
                        boolean isleaf = frameHierarchy.getModel().isLeaf(node);
                        if(isleaf) {
                            System.out.println("Is a Leaf Node -->");
                        }
                        if(e.getClickCount() == 1) {
                            if(isleaf)
                                setSelected(name);
                        } else if(e.getClickCount() == 2) {
                            if(isleaf)
                                setFrame(name);
                            }
                    }
                }
            }
        };
        private void addNode(BaseFrame frame, DefaultMutableTreeNode node) {
            DefaultMutableTreeNode n = new DefaultMutableTreeNode(frame.frameName);
            node.add(n);
      }
        public void setSelected(String name) {
            System.out.println("NodeClicked:" + name);
            BaseFrame frame = frameSet.getFrame(name);
            informationPanel.setInformationFromFile(frame.textExplanation);
        }
        public void setFrame(String name) {
            System.out.println("Open Frame: " + name);
            BaseFrame frame = frameSet.getFrame(name);
            frame.setVisible(true);
        }

    /** Creates new form TopXMLFrame */
    public TopXMLFrame() {
        panelSize = new Dimension(400,400);
        initComponents ();
        informationPanel.setup("/usr/local/Software/Analysis/xml/texts/");
        initializeTopLevelSystemFrames(topNode);
        initializeOptionFrames(topNode);
        moduleOperationFrames(topNode);
        frameHierarchy.addMouseListener(ml);
        frameHierarchy.updateUI();
        pack();
    }
    private void initializeOptionFrames(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode root = 
                    new DefaultMutableTreeNode("Options");
            parent.add(root);
           
            RWXMLOptions xmlopt = new RWXMLOptions(xmlOptions);
            
            BaseFrame wr = new BaseFrame(xmlopt,
                                    "Read/Write Option Parameters",
                                    "Reading and Writing Option Parameters",
                                    "xml/default/rwoptions.txt");
            frameSet.putFrame(wr);
            addNode(wr,root);
            
            BaseFrame r = new BaseFrame(xmlOptions.defaultDirectories,
                                    "Default Directories",
                                    "The Default Directories",
                                    "xml/default/directories.txt");
            frameSet.putFrame(r);
            addNode(r,root);

            BaseFrame s = new BaseFrame(xmlOptions.standardScripts,
                                    "Standard Scripts",
                                    "The names of the scripts used by the XML system",
                                    "xml/default/scripts.txt");
            frameSet.putFrame(s);
            addNode(s,root);

        }
    private void initializeTopLevelSystemFrames(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode root = 
                    new DefaultMutableTreeNode("Root System Setup");
            parent.add(root);
           
            InitializeTotalSystem init = new InitializeTotalSystem(xmlOptions);
            
            BaseFrame i = new BaseFrame(init,
                                    "Initialize System",
                                    "Initialize from scratch the entire system",
                                    "xml/setup/sysinit.txt");
            frameSet.putFrame(i);
            addNode(i,root);

            CreatePackageEnvironment pack = new CreatePackageEnvironment(xmlOptions);
            
            BaseFrame p = new BaseFrame(pack,
                                    "Create New Package Environment",
                                    "Initialize a new Package Environment",
                                    "xml/setup/package.txt");
            frameSet.putFrame(p);
            addNode(p,root);

            InitializePackageModule mod = new InitializePackageModule(xmlOptions);
            
            BaseFrame m = new BaseFrame(mod,
                                    "Create New Module",
                                    "Initialize a new Module",
                                    "xml/setup/module.txt");
            frameSet.putFrame(m);
            addNode(m,root);

        }
    private void moduleOperationFrames(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode root = 
                    new DefaultMutableTreeNode("Module Operations");
            parent.add(root);
            
            FillInModule fill = new FillInModule(xmlOptions);
            BaseFrame f = new BaseFrame(fill,
                                    "Initial Setup of Module Files",
                                    "Create Base Files for Module",
                                    "xml/module/fill.txt");
            frameSet.putFrame(f);
            addNode(f,root);
            
            ExternalFormats extform = new ExternalFormats(xmlOptions);
            BaseFrame e = new BaseFrame(extform,
                                        "External Formats",
                                        "Generate Further Codes from Specification",
                                        "xml/module/external.txt");
            frameSet.putFrame(e);
            addNode(e,root);
            
            ManipulateModule manip = new ManipulateModule(xmlOptions);
            BaseFrame m = new BaseFrame(manip,
                                    "Manage Module",
                                    "Compile and Manipulate the module",
                                    "xml/module/manage.txt");
            frameSet.putFrame(m);
            addNode(m,root);
            BuildClassXML bd = new BuildClassXML(xmlOptions);
            BaseFrame mb = new BaseFrame(bd,
                                         "Build Modules Classes",
                                         "Build Classes for a Module",
                                         "xml/module/build.txt");
            frameSet.putFrame(mb);
            addNode(mb,root);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        frameHierarchy = new JTree(topNode);
        informationPanel = new utilities.InformationPanel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel1.add(frameHierarchy);

        jPanel1.add(informationPanel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit (0);
    }//GEN-LAST:event_exitForm

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        new TopXMLFrame ().show ();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilities.InformationPanel informationPanel;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree frameHierarchy;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public FrameSet frameSet = new FrameSet();    

    public XMLOptions xmlOptions = new XMLOptions();
    
}
