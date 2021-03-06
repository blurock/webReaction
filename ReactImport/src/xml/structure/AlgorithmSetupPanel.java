/*
 * AlgorithmSetupPanel.java
 *
 * Created on July 3, 2001, 4:08 PM
 */

package xml.structure;
import react.utilities.SimpleManageListItems;
/**
 *
 * @author  reaction
 * @version 
 */
public class AlgorithmSetupPanel extends javax.swing.JPanel {
    BaseDataAlgorithmSetup algSetup; 
    boolean enableUpdate = false;
    /** Creates new form AlgorithmSetupPanel */
    public AlgorithmSetupPanel(BaseDataAlgorithmSetup setup) {
        algSetup = setup;
        System.out.println("Create Algorithm Information in Panel: I="
                + algSetup.inputObjects.length + "   O="
                + algSetup.outputObjects.length);
        initComponents ();
        
        inputVariables.setItems(algSetup.inputObjects);
        outputVariables.setItems(algSetup.outputObjects);
        enableUpdate = true;
        //update();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        inputVariables = new SimpleManageListItems("Input Objects",new String[0],0);
        
        outputVariables = new SimpleManageListItems("Output Objects",new String[0],0);
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));
        
        java.awt.GridBagConstraints gridBagConstraints2;
          inputVariables.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseEntered(java.awt.event.MouseEvent evt) {
                  update(evt);
              }
          }
          );
          jPanel1.add(inputVariables);
          
          
        java.awt.GridBagConstraints gridBagConstraints3;
          outputVariables.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseEntered(java.awt.event.MouseEvent evt) {
                  update(evt);
              }
          }
          );
          jPanel1.add(outputVariables);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        add(jPanel1, gridBagConstraints1);
        
    }//GEN-END:initComponents

  private void update(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update
// Add your handling code here:
      if(enableUpdate)
            update();
  }//GEN-LAST:event_update
  void update() {
      String[] names = algSetup.listOfNames();
      inputVariables.Source = names;
      outputVariables.Source = names;
      algSetup.inputObjects = inputVariables.getElements();
      algSetup.outputObjects = outputVariables.getElements();
        System.out.println("Update Algorithm Information: I="
                + algSetup.inputObjects.length + "   O="
                + algSetup.outputObjects.length);
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel jPanel1;
  private react.utilities.SimpleManageListItems inputVariables;
  private react.utilities.SimpleManageListItems outputVariables;
  // End of variables declaration//GEN-END:variables

}
