/*
 * TopTreeInfoPanel.java
 *
 * Created on March 4, 2001, 7:36 AM
 */

package blurock.core;
    import react.common.*;
    import react.utilities.*;
    import utilities.FrameSet;
    import react.menu.*;
    import react.mechanisms.*;
    import utilities.BaseFrame;
    import java.awt.Dimension;
    import javax.swing.*;
    import javax.swing.tree.*;
    import java.awt.event.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class TopTreeInfoPanel extends javax.swing.JPanel {
        Dimension panelSize;
        public DefaultMutableTreeNode topNode;
        public FrameSet frameSet;
        
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

  
    /** Creates new form TopTreeInfoPanel */
    public TopTreeInfoPanel(String name, FrameSet fr, String textdir,
                        Dimension psize) {
        panelSize = psize;
        frameSet = fr;
        topNode = new DefaultMutableTreeNode(name);
        initComponents ();
        informationPanel.setup(textdir);
        frameHierarchy.addMouseListener(ml);
    }
    public void initializeTreeHierarchy(DefaultMutableTreeNode topnode) {
    }
      public void addNode(BaseFrame frame, DefaultMutableTreeNode node) {
          frameSet.putFrame(frame);
            DefaultMutableTreeNode n = new DefaultMutableTreeNode(frame.frameName);
            node.add(n);
      }
      public DefaultMutableTreeNode addSimpleNode(String name, DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode son = 
                new DefaultMutableTreeNode(name);
            parent.add(son);
            return son;
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
        private void initComponents() {//GEN-BEGIN:initComponents
            frameHierarchy = new JTree(topNode);
            informationPanel = new utilities.InformationPanel();
            setLayout(new java.awt.GridLayout(1, 2));
            
            frameHierarchy.setBorder(new javax.swing.border.TitledBorder("Hierarchy"));
            
            add(frameHierarchy);
            
            
            
            add(informationPanel);
            
        }//GEN-END:initComponents


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JTree frameHierarchy;
        private utilities.InformationPanel informationPanel;
        // End of variables declaration//GEN-END:variables

}
