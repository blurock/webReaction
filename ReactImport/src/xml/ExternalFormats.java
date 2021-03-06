/*
 * ExternalFormats.java
 *
 * Created on July 2, 2001, 2:01 PM
 */

package xml;

/**
 *
 * @author  reaction
 * @version 
 */
public class ExternalFormats extends javax.swing.JPanel {
    XMLOptions xmlOptions;
    
    /** Creates new form ExternalFormats */
    public ExternalFormats(XMLOptions xmlopt) {
        xmlOptions = xmlopt;
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        modulePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        environmentName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        moduleName = new javax.swing.JTextField();
        fileLocationPanel = new javax.swing.JPanel();
        localFiles = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        formatPanel = new javax.swing.JPanel();
        javaButton = new javax.swing.JButton();
        idlButton = new javax.swing.JButton();
        mathematicaButton = new javax.swing.JButton();
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        modulePanel.setLayout(new java.awt.GridLayout(2, 2));
        modulePanel.setBorder(new javax.swing.border.TitledBorder("Module"));
        
        jLabel1.setText("Environment");
          modulePanel.add(jLabel1);
          
          
        environmentName.setText("CoreObjects");
          modulePanel.add(environmentName);
          
          
        jLabel2.setText("Module");
          modulePanel.add(jLabel2);
          
          
        moduleName.setText("CoreDataObjects");
          modulePanel.add(moduleName);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(modulePanel, gridBagConstraints1);
        
        
        fileLocationPanel.setLayout(new java.awt.GridLayout(2, 1));
        fileLocationPanel.setBorder(new javax.swing.border.TitledBorder("File Location"));
        
        localFiles.setSelected(true);
          localFiles.setText("Input Standard");
          fileLocationPanel.add(localFiles);
          
          
        jCheckBox2.setSelected(true);
          jCheckBox2.setText("Output Standard");
          fileLocationPanel.add(jCheckBox2);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(fileLocationPanel, gridBagConstraints1);
        
        
        formatPanel.setLayout(new java.awt.GridLayout(5, 1));
        formatPanel.setBorder(new javax.swing.border.TitledBorder("External Formats"));
        
        javaButton.setText("JAVA");
          javaButton.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  javaButtonMouseClicked(evt);
              }
          }
          );
          formatPanel.add(javaButton);
          
          
        idlButton.setText("IDL");
          idlButton.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  idlButtonMouseClicked(evt);
              }
          }
          );
          formatPanel.add(idlButton);
          
          
        mathematicaButton.setText("Mathematica");
          mathematicaButton.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  mathematicaButtonMouseClicked(evt);
              }
          }
          );
          formatPanel.add(mathematicaButton);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(formatPanel, gridBagConstraints1);
        
    }//GEN-END:initComponents

  private void mathematicaButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mathematicaButtonMouseClicked
// Add your handling code here:
  }//GEN-LAST:event_mathematicaButtonMouseClicked

  private void idlButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idlButtonMouseClicked
// Add your handling code here:
  }//GEN-LAST:event_idlButtonMouseClicked

  private void javaButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_javaButtonMouseClicked
// Add your handling code here:
  }//GEN-LAST:event_javaButtonMouseClicked


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel modulePanel;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JTextField environmentName;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JTextField moduleName;
  private javax.swing.JPanel fileLocationPanel;
  private javax.swing.JCheckBox localFiles;
  private javax.swing.JCheckBox jCheckBox2;
  private javax.swing.JPanel formatPanel;
  private javax.swing.JButton javaButton;
  private javax.swing.JButton idlButton;
  private javax.swing.JButton mathematicaButton;
  // End of variables declaration//GEN-END:variables

}
