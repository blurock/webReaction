/*
 * ClusteringPanel.java
 *
 * Created on January 25, 2002, 11:18 AM
 */

package react.menu;
import utilities.*;
import blurock.utilities.*;
import react.common.TopReactionMenu;
import java.io.File;
/**
 *
 * @author  reaction
 * @version 
 */
public class InitializeRegionClusteringPanel extends javax.swing.JPanel {

    TopReactionMenu Top;
    String runName;
    FileFrame fileFrame;
    RunCommand run;
    /** Creates new form ClusteringPanel */
    public InitializeRegionClusteringPanel(TopReactionMenu top) {
        Top = top;
        initComponents ();
        fileFrame = new FileFrame();
        fileFrame.setupButton("Data",".","txt");
        run = new RunCommand(Top);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        historyName = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        dataFileField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Read = new javax.swing.JButton();
        Instances = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        attributeSelectButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        selectInstancesButton = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        minValueText = new javax.swing.JTextField();
        percentageSlider = new javax.swing.JSlider();
        maxValueText = new javax.swing.JTextField();
        widthSlider = new javax.swing.JSlider();
        makePredicatesButton = new javax.swing.JButton();
        jPanel51 = new javax.swing.JPanel();
        dependentVariable = new javax.swing.JTextField();
        variableSelect = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        saveData = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(new javax.swing.border.TitledBorder("Initial Setup"));
        jButton1.setText("Initialize");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButton1, gridBagConstraints);

        historyName.setText("test");
        historyName.setToolTipText("The name of the run");
        historyName.setMinimumSize(new java.awt.Dimension(30, 16));
        historyName.setPreferredSize(new java.awt.Dimension(50, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.weightx = 3.0;
        jPanel1.add(historyName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Set Up Data"));
        dataFileField.setText("SpeciesData.txt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel2.add(dataFileField, gridBagConstraints);

        browseButton.setText("Browse");
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseButtonMouseClicked(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        jPanel2.add(browseButton, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        Read.setText("Read");
        Read.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReadMouseClicked(evt);
            }
        });

        jPanel3.add(Read);

        Instances.setText("Instances");
        Instances.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InstancesMouseClicked(evt);
            }
        });

        jPanel3.add(Instances);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        jPanel2.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel2, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridLayout(1, 1, 1, 0));

        jPanel11.setBorder(new javax.swing.border.TitledBorder("Select Attributes"));
        attributeSelectButton.setText("Select Attributes");
        attributeSelectButton.setToolTipText("Select out the attributes for forming the predicates");
        jPanel11.add(attributeSelectButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel11, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridLayout(1, 1));

        jPanel5.setBorder(new javax.swing.border.TitledBorder("Instances"));
        selectInstancesButton.setText("Select Instances");
        selectInstancesButton.setToolTipText("Select Test and Train Instances");
        jPanel5.add(selectInstancesButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel5, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        jPanel21.setBorder(new javax.swing.border.TitledBorder("General Predicate Settings"));
        jPanel21.setMinimumSize(new java.awt.Dimension(350, 40));
        minValueText.setText("0.0");
        minValueText.setMinimumSize(new java.awt.Dimension(50, 16));
        minValueText.setPreferredSize(new java.awt.Dimension(50, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel21.add(minValueText, gridBagConstraints);

        percentageSlider.setMajorTickSpacing(10);
        percentageSlider.setMinorTickSpacing(5);
        percentageSlider.setPaintTicks(true);
        percentageSlider.setSnapToTicks(true);
        percentageSlider.setToolTipText("Set the center of the fuzzy predicate");
        percentageSlider.setValue(10);
        percentageSlider.setName("Center");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel21.add(percentageSlider, gridBagConstraints);

        maxValueText.setText("1.00");
        maxValueText.setMinimumSize(new java.awt.Dimension(50, 16));
        maxValueText.setPreferredSize(new java.awt.Dimension(50, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 2;
        jPanel21.add(maxValueText, gridBagConstraints);

        widthSlider.setMajorTickSpacing(10);
        widthSlider.setMinorTickSpacing(5);
        widthSlider.setPaintTicks(true);
        widthSlider.setSnapToTicks(true);
        widthSlider.setToolTipText("Set Width of Fuzzy Predicate (relative to center)");
        widthSlider.setValue(0);
        widthSlider.setName("Width");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        jPanel21.add(widthSlider, gridBagConstraints);

        makePredicatesButton.setText("Setup");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel21.add(makePredicatesButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel21, gridBagConstraints);

        jPanel51.setLayout(new java.awt.GridLayout(1, 2));

        jPanel51.setBorder(new javax.swing.border.TitledBorder("Dependent Variable"));
        dependentVariable.setText("z_physical");
        jPanel51.add(dependentVariable);

        variableSelect.setText("Select");
        jPanel51.add(variableSelect);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel51, gridBagConstraints);

        jPanel4.setBorder(new javax.swing.border.TitledBorder("Save Data"));
        saveData.setText("Save");
        saveData.setToolTipText("Save the results of this phase of the calculation");
        saveData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveDataMouseClicked(evt);
            }
        });

        jPanel4.add(saveData);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel4, gridBagConstraints);

    }//GEN-END:initComponents

  private void saveDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveDataMouseClicked
      Top.stopSystemProcess();
  }//GEN-LAST:event_saveDataMouseClicked

  private void InstancesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InstancesMouseClicked
      try {
        String command = new String("MakeInstanceFromMatrix MatrixObject");
        run.run(command);
        OutputFrame frame2 = new OutputFrame(run.outputString);
        frame2.show();
      } catch(Exception ex) {
          ex.printStackTrace(System.out);
      }
  }//GEN-LAST:event_InstancesMouseClicked

  private void ReadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReadMouseClicked
      try {
        String command = new String(Top.SystemInfo.matrixData.getValue() + " " + dataFileField.getText() + " 0.000001");
        run.run(command);
        OutputFrame frame = new OutputFrame(run.outputString);
        frame.show();
      } catch(Exception ex) {
          ex.printStackTrace(System.out);
      }
  }//GEN-LAST:event_ReadMouseClicked

  private void browseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseButtonMouseClicked
      fileFrame.getFile();
      File chosenFile = fileFrame.chosenFile;
      dataFileField.setText(chosenFile.getPath());
  }//GEN-LAST:event_browseButtonMouseClicked

  private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        initializeClusteringRun();
  }//GEN-LAST:event_jButton1MouseClicked
  private void initializeClusteringRun() {
        Top.history.setToFlame();
        Top.history.setHistoryName(historyName.getText());
        runName = Top.SystemInfo.flameRunExecutable.getValue();
        Top.InitializeSystem.basicInitialization();
        Top.InitializeSystem.algorithmsInitialization();
        Top.InitializeSystem.logicInitialization();
        //Top.InitializeSystem.expressionInitialization();
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField dataFileField;
    private javax.swing.JTextField historyName;
    private javax.swing.JSlider percentageSlider;
    private javax.swing.JTextField dependentVariable;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jButton1;
    private javax.swing.JSlider widthSlider;
    private javax.swing.JTextField minValueText;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField maxValueText;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JButton saveData;
    private javax.swing.JButton makePredicatesButton;
    private javax.swing.JButton browseButton;
    private javax.swing.JButton Instances;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton selectInstancesButton;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JButton attributeSelectButton;
    private javax.swing.JButton variableSelect;
    private javax.swing.JButton Read;
    // End of variables declaration//GEN-END:variables

}
