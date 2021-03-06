/*
 * ConsecutiveClusteringPanel.java
 *
 * Created on February 2, 2002, 8:50 PM
 */

package react.menu;
import react.common.TopReactionMenu;
import blurock.utilities.SetUpClassAttrFile;
import blurock.core.RunAlgorithm;
import blurock.utilities.MenuList;
import react.utilities.StandardListDialog;
import react.utilities.StandardListPanel;
/**
 *
 * @author  reaction
 * @version 
 */
public class ConsecutiveClusteringPanel extends javax.swing.JPanel {
    TopReactionMenu Top;
    /** Creates new form ConsecutiveClusteringPanel */
    public ConsecutiveClusteringPanel(TopReactionMenu top) {
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
        startButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        minNumberOfElements = new javax.swing.JSlider();
        jPanel4 = new javax.swing.JPanel();
        maxGap = new javax.swing.JSlider();
        jPanel5 = new javax.swing.JPanel();
        dependentVariable = new javax.swing.JTextField();
        variableSelect = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        findRegions = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setBorder(new javax.swing.border.TitledBorder("Phase Initialization"));
        jPanel1.setMinimumSize(new java.awt.Dimension(200, 94));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 94));
        startButton.setText("Start");
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startButtonMouseClicked(evt);
            }
        });

        jPanel1.add(startButton);

        saveButton.setText("Save");
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });

        jPanel1.add(saveButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel1, gridBagConstraints);

        jPanel3.setBorder(new javax.swing.border.TitledBorder("Minimum Number of Elements"));
        minNumberOfElements.setMajorTickSpacing(5);
        minNumberOfElements.setMaximum(20);
        minNumberOfElements.setMinorTickSpacing(1);
        minNumberOfElements.setPaintLabels(true);
        minNumberOfElements.setPaintTicks(true);
        minNumberOfElements.setSnapToTicks(true);
        minNumberOfElements.setValue(5);
        jPanel3.add(minNumberOfElements);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel3, gridBagConstraints);

        maxGap.setMajorTickSpacing(10);
        maxGap.setMaximum(20);
        maxGap.setMinorTickSpacing(1);
        maxGap.setPaintLabels(true);
        maxGap.setPaintTicks(true);
        maxGap.setValue(3);
        maxGap.setBorder(new javax.swing.border.TitledBorder("Maximum Gap"));
        jPanel4.add(maxGap);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        jPanel5.setBorder(new javax.swing.border.TitledBorder("Dependent Variable"));
        dependentVariable.setText("z_physical");
        jPanel5.add(dependentVariable);

        variableSelect.setText("Select");
        variableSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                variableSelectMouseClicked(evt);
            }
        });

        jPanel5.add(variableSelect);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel5, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Find Regions"));
        jPanel2.setMinimumSize(new java.awt.Dimension(200, 47));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 47));
        findRegions.setText("Regions");
        findRegions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                findRegionsMouseClicked(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(findRegions, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel2, gridBagConstraints);

    }//GEN-END:initComponents

    private void variableSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_variableSelectMouseClicked
        MenuList mlist = Top.InstanceCommon.getInstanceAttributeList(true);
        String[] parameterList = mlist.getNameList();
        StandardListDialog plist = new StandardListDialog(parameterList);
        plist.show();
        String[] names = plist.listPanel.selectedItemsToString(true);
        dependentVariable.setText(new String(names[0]));
    }//GEN-LAST:event_variableSelectMouseClicked

  private void findRegionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_findRegionsMouseClicked
      SetUpClassAttrFile ofile = 
        new SetUpClassAttrFile(Top,"Regions","Calculation of Regions");
      ofile.printString("RootName","Region");
      ofile.printString("ClusterObject","CobwebClusterTree");
      int MinimumNumberOfElements = minNumberOfElements.getValue();
      int MaximumGap = maxGap.getValue();
      ofile.printInteger("MinimumNumberOfElements",MinimumNumberOfElements);
      ofile.printInteger("MaximumGap",MaximumGap);
      String SortParameter = dependentVariable.getText();
      ofile.printString("SortParameter",SortParameter);
      ofile.read(true);
      
      RunAlgorithm run1 = new RunAlgorithm(Top,"ClusterSetsAlg",true);
      run1.run();
      run1.showResults();

      RunAlgorithm run2 = new RunAlgorithm(Top,"SortInstances",true);
      run2.run();
      run2.showResults();
      
      RunAlgorithm run3 = new RunAlgorithm(Top,"FindConsecutive",true);
      run3.run();
      run3.showResults();
  }//GEN-LAST:event_findRegionsMouseClicked

  private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
      Top.stopSystemProcess();
  }//GEN-LAST:event_saveButtonMouseClicked

  private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMouseClicked
    Top.history.setToFlame();
    Top.startSystemProcess();

  }//GEN-LAST:event_startButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider minNumberOfElements;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSlider maxGap;
    private javax.swing.JTextField dependentVariable;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton startButton;
    private javax.swing.JButton findRegions;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton variableSelect;
    // End of variables declaration//GEN-END:variables

}
