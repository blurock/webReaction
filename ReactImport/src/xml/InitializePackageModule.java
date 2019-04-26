/*
 * InitializePackageModule.java
 *
 * Created on February 21, 2001, 10:42 AM
 */

package xml;
import blurock.core.RunScriptWithOutput;

/**
 *
 * @author  reaction
 * @version 
 */
public class InitializePackageModule extends javax.swing.JPanel {

    XMLOptions xmlOptions;
    /** Creates new form InitializePackageModule */
    public InitializePackageModule(XMLOptions opt) {
        xmlOptions = opt;
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        packageName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        moduleName = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        initDirectory = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        parseModule = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        origDevelopDir = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        origModule = new javax.swing.JTextField();
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel1.setLayout(new java.awt.GridLayout(2, 2));
        jPanel1.setBorder(new javax.swing.border.TitledBorder("Module Definition"));
        
        jLabel1.setText("Package Name");
          jPanel1.add(jLabel1);
          
          
        packageName.setToolTipText("The name of an exsisting package environment");
          packageName.setText("CoreObjects");
          jPanel1.add(packageName);
          
          
        jLabel2.setText("Module Name");
          jPanel1.add(jLabel2);
          
          
        moduleName.setToolTipText("Name of the new module to be created or modified");
          moduleName.setText("CoreDataObjects");
          jPanel1.add(moduleName);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel1, gridBagConstraints1);
        
        
        jPanel2.setBorder(new javax.swing.border.TitledBorder("Initialize Directory Structure"));
        
        initDirectory.setToolTipText("The first operation must be to initialize the directory structure");
          initDirectory.setText("Initialize Directory Structure");
          initDirectory.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  initDirectoryMouseClicked(evt);
              }
          }
          );
          jPanel2.add(initDirectory);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel2, gridBagConstraints1);
        
        
        jPanel3.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        jPanel3.setBorder(new javax.swing.border.TitledBorder("Initial Definition Parse"));
        
        parseModule.setToolTipText("Parse the given module in the given directory structure");
          parseModule.setText("Parse");
          parseModule.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  parseModuleMouseClicked(evt);
              }
          }
          );
          gridBagConstraints2 = new java.awt.GridBagConstraints();
          gridBagConstraints2.gridwidth = 0;
          gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
          jPanel3.add(parseModule, gridBagConstraints2);
          
          
        jPanel4.setLayout(new java.awt.GridLayout(2, 2));
          
          jLabel4.setText("Source Directory Root");
            jLabel4.setToolTipText("The development directory root to find the orginal source to parse");
            jPanel4.add(jLabel4);
            
            
          origDevelopDir.setText(xmlOptions.defaultDirectories.originalDevelopmentDir.getValue());
            jPanel4.add(origDevelopDir);
            
            
          jLabel3.setText("Source Module");
            jLabel3.setToolTipText("The source module to parse");
            jPanel4.add(jLabel3);
            
            
          origModule.setText("CoreDataObjects");
            jPanel4.add(origModule);
            
            gridBagConstraints2 = new java.awt.GridBagConstraints();
          jPanel3.add(jPanel4, gridBagConstraints2);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel3, gridBagConstraints1);
        
    }//GEN-END:initComponents

  private void parseModuleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_parseModuleMouseClicked
// Add your handling code here:
      String command =  xmlOptions.defaultDirectories.xmlDirectory.getValue() +
                        "/scripts/" + 
                        xmlOptions.standardScripts.initialModuleXSLParse.getValue() + " " +
                        packageName.getText() + " " +
                        moduleName.getText() + " " +
                        origModule.getText() + " " +
                        xmlOptions.defaultDirectories.currentDevDirectory.getValue() + " " +
                        origDevelopDir.getText() + " " +
                        xmlOptions.defaultDirectories.xmlDirectory.getValue();
      RunScriptWithOutput scr = new RunScriptWithOutput(xmlOptions.tLink);
      scr.run(command,true);      
  }//GEN-LAST:event_parseModuleMouseClicked

  private void initDirectoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_initDirectoryMouseClicked
// Add your handling code here:
      String command =  xmlOptions.defaultDirectories.xmlDirectory.getValue() +
                        "/scripts/" + 
                        xmlOptions.standardScripts.initModuleDir.getValue() + " " +
                        packageName.getText() + " " +
                        moduleName.getText() + " " +
                        xmlOptions.defaultDirectories.currentDevDirectory.getValue() + " " +
                        xmlOptions.defaultDirectories.xmlDirectory.getValue();
      RunScriptWithOutput scr = new RunScriptWithOutput(xmlOptions.tLink);
      scr.run(command,true);      
  }//GEN-LAST:event_initDirectoryMouseClicked


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel jPanel1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JTextField packageName;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JTextField moduleName;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JButton initDirectory;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JButton parseModule;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JTextField origDevelopDir;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JTextField origModule;
  // End of variables declaration//GEN-END:variables

}