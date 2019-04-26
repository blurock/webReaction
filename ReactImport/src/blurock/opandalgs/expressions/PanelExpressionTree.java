/*
 * PanelExpressionTree.java
 *
 * Created on February 28, 2001, 4:39 PM
 */

package blurock.opandalgs.expressions;
import javax.swing.*;
import utilities.BaseFrame;
/**
 *
 * @author  reaction
 * @version 
 */
public class PanelExpressionTree extends javax.swing.JPanel {
    JPanel topInfo;
    String className;

    /** Creates new form PanelExpressionTree */
    public PanelExpressionTree(String name, JPanel top, String exp) {
        initComponents ();
        topInfo = top;
        className = name;
        nameButton.setText(name);
        expressionText.setText(exp);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        nameButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        expressionText = new javax.swing.JTextField();
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        nameButton.setText("jButton1");
        nameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameButtonMouseClicked(evt);
            }
        }
        );
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(nameButton, gridBagConstraints1);
        
        
        jPanel2.setBorder(new javax.swing.border.TitledBorder("Expression"));
        
        expressionText.setText("                                         ");
          jPanel2.add(expressionText);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel2, gridBagConstraints1);
        
    }//GEN-END:initComponents

  private void nameButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameButtonMouseClicked
// Add your handling code here:
      BaseFrame f = new BaseFrame(topInfo,className,"Class Info","");
      f.show();

  }//GEN-LAST:event_nameButtonMouseClicked


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton nameButton;
  private javax.swing.JPanel jPanel2;
  public javax.swing.JTextField expressionText;
  // End of variables declaration//GEN-END:variables

}