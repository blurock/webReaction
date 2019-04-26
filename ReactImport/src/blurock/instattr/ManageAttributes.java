/*
 * ManageAttributes.java
 *
 * Created on March 10, 2001, 8:17 AM
 */

package blurock.instattr;
import react.common.TopReactionMenu;
import blurock.core.*;
import blurock.utilities.MenuList;
import utilities.ErrorFrame;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class ManageAttributes extends javax.swing.JPanel {
    TopReactionMenu Top;
    /** Creates new form ManageAttributes */
    public ManageAttributes(TopReactionMenu top) {
        Top = top;
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        updateCommand = new javax.swing.JButton();
        viewAttribute = new javax.swing.JButton();
        attributeList = new javax.swing.JPanel();
        attributeListPanel = new react.utilities.StandardListPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        updateCommand.setText("Update");
        updateCommand.setToolTipText("Updates to the current set of Attributes");
        updateCommand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateCommandMouseClicked(evt);
            }
        });

        jPanel1.add(updateCommand);

        viewAttribute.setText("View");
        viewAttribute.setToolTipText("View the attribute in another frame");
        viewAttribute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewAttributeMouseClicked(evt);
            }
        });

        jPanel1.add(viewAttribute);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        attributeList.setLayout(new java.awt.BorderLayout());

        attributeList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attributeListMouseClicked(evt);
            }
        });

        attributeListPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attributeListPanelMouseClicked(evt);
            }
        });

        attributeList.add(attributeListPanel, java.awt.BorderLayout.CENTER);

        add(attributeList, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

  private void viewAttributeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewAttributeMouseClicked
// Add your handling code here:
      String[] attrs = attributeListPanel.selectedItemsToString(false);
      AttributeFrame fr = new AttributeFrame(Top);
      for(int i=0;i<attrs.length;i++) {
          /*
          String attrname = "WindowObject Attribute " + attrs[i];
          RunCommand runit = new RunCommand(Top,attrname,false);
          runit.run();
          ErrorFrame fr = new ErrorFrame(runit.commandOutput);
          fr.show();
           **/
          fr.inFrame(attrs[i]);
      }
  }//GEN-LAST:event_viewAttributeMouseClicked

  private void updateCommandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateCommandMouseClicked
// Add your handling code here:
      MenuList attr = Top.InstanceCommon.getAttributeList(true);
      String[] namelist = attr.getNameList();
      //for(int i= 0;i<namelist.length;i++)
          //System.out.println(namelist[i]);
      attributeListPanel.setData(namelist);
      
  }//GEN-LAST:event_updateCommandMouseClicked

  private void attributeListPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attributeListPanelMouseClicked
// Add your handling code here:
      System.out.println("Attribute List Panel Clicked");
  }//GEN-LAST:event_attributeListPanelMouseClicked

  private void attributeListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attributeListMouseClicked
// Add your handling code here:
      System.out.println("Attribute List Clicked");
      
  }//GEN-LAST:event_attributeListMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton viewAttribute;
    private javax.swing.JButton updateCommand;
    private javax.swing.JPanel jPanel1;
    private react.utilities.StandardListPanel attributeListPanel;
    private javax.swing.JPanel attributeList;
    // End of variables declaration//GEN-END:variables

}