/*
 * PanelSetOfObjects.java
 *
 * Created on March 1, 2001, 1:29 PM
 */

package blurock.coreobjects;
import blurock.core.*;
import utilities.BaseFrame;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class PanelSetOfObjects extends javax.swing.JPanel {
    JPanel topInfo;
    String className;
    /** Creates new form PanelSetOfObjects */
    public PanelSetOfObjects(String name, JPanel top, ObjectAsTreeNode set) {
        initComponents ();
        topInfo = top;
        className = name;
        nameButton.setText(name);
        TopTreePanel tree = new TopTreePanel(set);
        setPanel.add(tree);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        nameButton = new javax.swing.JButton();
        setPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        nameButton.setText("jButton1");
        nameButton.setToolTipText("Class Information of SetOfObject");
        nameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameButtonMouseClicked(evt);
            }
        });

        add(nameButton, java.awt.BorderLayout.NORTH);

        setPanel.setLayout(new java.awt.BorderLayout());

        setPanel.setBorder(new javax.swing.border.TitledBorder("Parameters"));
        add(setPanel, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

  private void nameButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameButtonMouseClicked
// Add your handling code here:
      BaseFrame f = new BaseFrame(topInfo,className,"Class Info","");
      f.show();
  }//GEN-LAST:event_nameButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel setPanel;
    private javax.swing.JButton nameButton;
    // End of variables declaration//GEN-END:variables

}
