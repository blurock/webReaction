/*
 * ClassInfoPanel.java
 *
 * Created on May 13, 2001, 9:40 AM
 */

package xml.structure;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author  reaction
 * @version 
 */
public class ClassInfoPanel extends javax.swing.JPanel {
    BaseDataClassInfo flagInformation;
    boolean enableUpdate = false;
    
    /** Creates new form ClassInfoPanel */
    JCheckBox[] checkBoxes;
    public ClassInfoPanel(BaseDataClassInfo info) {
        flagInformation = info;
        setup(info.language,info.flagDescriptions,info.functionFlags);
    }
    void setup(String lang, String[] names, boolean[] flags) {
        initComponents ();
        checkBoxes = new JCheckBox[names.length];
        GridBagConstraints gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;

        for(int count=0;count < names.length;count++) {
        checkBoxes[count] = new javax.swing.JCheckBox();
        checkBoxes[count].setText(names[count]);
        checkBoxPanel.add(checkBoxes[count], gridBagConstraints2);
        checkBoxes[count].setSelected(flags[count]);
        }
        enableUpdate = true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel2 = new javax.swing.JPanel();
        infoTitle = new javax.swing.JLabel();
        checkBoxPanel = new javax.swing.JPanel();
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        }
        );
        
        
        infoTitle.setText("Language " + flagInformation.language + " Flags");
          jPanel2.add(infoTitle);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel2, gridBagConstraints1);
        
        
        checkBoxPanel.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        checkBoxPanel.setBorder(new javax.swing.border.TitledBorder("Flags"));
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(checkBoxPanel, gridBagConstraints1);
        
    }//GEN-END:initComponents

  private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
// Add your handling code here:
      updateData();
  }//GEN-LAST:event_formMouseExited

  void updateData() {
      if(enableUpdate) {
      for(int i=0; i< checkBoxes.length;i++) {
          flagInformation.functionFlags[i] = checkBoxes[i].isSelected();
      }
      }
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel jPanel2;
  private javax.swing.JLabel infoTitle;
  private javax.swing.JPanel checkBoxPanel;
  // End of variables declaration//GEN-END:variables

}
