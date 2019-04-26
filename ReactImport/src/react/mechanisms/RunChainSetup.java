/*
 * RunChainSetup.java
 *
 * Created on February 2, 2001, 12:19 AM
 */

package react.mechanisms;
import react.mechanisms.*;
import react.common.*;
import react.molecules.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class RunChainSetup extends javax.swing.JDialog {
    /** A return status code - returned if Cancel button has been pressed */
    public static final int RET_CANCEL = 0;
    /** A return status code - returned if OK button has been pressed */
    public static final int RET_OK = 1;

    /** Creates new form Test */
    public RunChainSetup(java.awt.Frame parent,boolean modal) {
        super (parent, modal);
        initComponents ();
        pack ();
    }
    public RunChainSetup(TopReactionMenu top, String title, int maxsize) {
        this.setModal(true);
        Title = title;
        Top = top;
        maxSize = maxsize;
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        mechanismName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mainMolecule = new javax.swing.JTextField();
        selectMainMolecule = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        listOfMolecules = new ManageListOfMolecules(Top,Title,maxSize);
        
        jPanel2 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 40));
        
        jPanel4.setLayout(new java.awt.GridLayout(1, 2));
          jPanel4.setPreferredSize(new java.awt.Dimension(400, 16));
          
          jLabel2.setText("Mechanism Name");
            jPanel4.add(jLabel2);
            
            
          mechanismName.setPreferredSize(new java.awt.Dimension(200, 16));
            mechanismName.setText("Test            ");
            jPanel4.add(mechanismName);
            
            gridBagConstraints2 = new java.awt.GridBagConstraints();
          gridBagConstraints2.gridwidth = 0;
          gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
          jPanel1.add(jPanel4, gridBagConstraints2);
          
          
        jPanel3.setLayout(new java.awt.GridLayout(1, 3));
          
          jLabel1.setText("Main Molecule");
            jPanel3.add(jLabel1);
            
            
          mainMolecule.setPreferredSize(new java.awt.Dimension(200, 16));
            mainMolecule.setText("molecule       ");
            jPanel3.add(mainMolecule);
            
            
          selectMainMolecule.setLabel("Selected Molecule");
            selectMainMolecule.setText("Get Molecule");
            selectMainMolecule.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    selectMainMolecule(evt);
                }
            }
            );
            jPanel3.add(selectMainMolecule);
            
            gridBagConstraints2 = new java.awt.GridBagConstraints();
          gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
          jPanel1.add(jPanel3, gridBagConstraints2);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanel1, gridBagConstraints1);
        
        
        
        listOfMolecules.setLayout(new java.awt.BorderLayout());
          jPanel5.add(listOfMolecules);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanel5, gridBagConstraints1);
        
        
        jPanel2.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        okButton.setText("jButton1");
          okButton.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  okButtonActionPerformed(evt);
              }
          }
          );
          gridBagConstraints3 = new java.awt.GridBagConstraints();
          gridBagConstraints3.gridwidth = -1;
          jPanel2.add(okButton, gridBagConstraints3);
          
          
        cancelButton.setText("jButton2");
          cancelButton.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  cancelButtonActionPerformed(evt);
              }
          }
          );
          gridBagConstraints3 = new java.awt.GridBagConstraints();
          gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
          jPanel2.add(cancelButton, gridBagConstraints3);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        getContentPane().add(jPanel2, gridBagConstraints1);
        
    }//GEN-END:initComponents

  private void selectMainMolecule(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMainMolecule
// Add your handling code here:
      String mols[] = listOfMolecules.moleculeList.getElements();
      if(mols.length > 0) {
          mainMolecule.setText(mols[0]);
      }
  }//GEN-LAST:event_selectMainMolecule

    private void okButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        doClose (RET_OK);    
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose (RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /** Closes the dialog */
    private void doClose (int retStatus) {
        returnStatus = retStatus;
        setVisible (false);
        dispose ();
    }

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        new RunChainSetup (new javax.swing.JFrame (), true).show ();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JTextField mechanismName;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JTextField mainMolecule;
    private javax.swing.JButton selectMainMolecule;
    private javax.swing.JPanel jPanel5;
    public react.molecules.ManageListOfMolecules listOfMolecules;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton okButton;
    private javax.swing.JButton cancelButton;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;

    public TopReactionMenu Top;
    
    public String Title;
    
    public int maxSize;
    
}