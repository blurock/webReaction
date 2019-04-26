/*
 * ReactionObjectInitialization.java
 *
 * Created on April 2, 2000, 9:08 AM
 */

package blurock.core;
import react.common.*;
import blurock.core.RunCommand;
import blurock.core.RunAlgorithm;
import blurock.core.*;
import utilities.ErrorFrame;

/**
 *
 * @author  reaction
 * @version 
 */
public class ReactionObjectInitialization extends javax.swing.JPanel {
    TopReactionMenu Top;
    
    /** Creates new form ReactionObjectInitialization */
    public ReactionObjectInitialization(TopReactionMenu top) {
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

        jPanel2 = new javax.swing.JPanel();
        startReaction = new javax.swing.JButton();
        saveReaction = new javax.swing.JButton();
        initializeButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        moleculesC0 = new javax.swing.JRadioButton();
        moleculesC1 = new javax.swing.JRadioButton();
        moleculesC2 = new javax.swing.JRadioButton();
        moleculesC3 = new javax.swing.JRadioButton();
        moleculesC4 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        equilibriumInit = new javax.swing.JRadioButton();
        storeInformation = new javax.swing.JRadioButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Phase of Calculation"));
        startReaction.setText("Start");
        startReaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startReactionMouseClicked(evt);
            }
        });

        jPanel2.add(startReaction);

        saveReaction.setText("Save");
        saveReaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveReactionMouseClicked(evt);
            }
        });

        jPanel2.add(saveReaction);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel2, gridBagConstraints);

        initializeButton.setToolTipText("Start the chemical object initialization");
        initializeButton.setText("Run Initialization");
        initializeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                initializeButtonMouseClicked(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(initializeButton, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridLayout(5, 1));

        jPanel1.setBorder(new javax.swing.border.TitledBorder("Molecule Objects"));
        moleculesC0.setToolTipText("Simple molecules with no carbons");
        moleculesC0.setSelected(true);
        moleculesC0.setText("C0 Standard Molecules");
        jPanel1.add(moleculesC0);

        moleculesC1.setToolTipText("Molecules with 1 carbon");
        moleculesC1.setSelected(true);
        moleculesC1.setText("C1 Standard Molecules");
        jPanel1.add(moleculesC1);

        moleculesC2.setToolTipText("Standard molecules with 2 carbons");
        moleculesC2.setSelected(true);
        moleculesC2.setText("C2 Standard Molecules");
        jPanel1.add(moleculesC2);

        moleculesC3.setToolTipText("Standard molecules with 3 carbons");
        moleculesC3.setSelected(true);
        moleculesC3.setText("C3 Standard Molecules");
        jPanel1.add(moleculesC3);

        moleculesC4.setToolTipText("Standard molecules with 4 carbons");
        moleculesC4.setSelected(true);
        moleculesC4.setText("C4 Standard Molecules");
        jPanel1.add(moleculesC4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel1, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridLayout(1, 1));

        jPanel4.setBorder(new javax.swing.border.TitledBorder("Additional Operations"));
        equilibriumInit.setToolTipText("Calculate Equilibrium Information");
        equilibriumInit.setSelected(true);
        equilibriumInit.setText("Equilibrium");
        jPanel4.add(equilibriumInit);

        storeInformation.setToolTipText("Store the read in information into database");
        storeInformation.setSelected(true);
        storeInformation.setText("Database Store");
        jPanel4.add(storeInformation);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel4, gridBagConstraints);

    }//GEN-END:initComponents

  private void saveReactionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveReactionMouseClicked
      Top.history.setToReaction();
      Top.stopSystemProcess();
  }//GEN-LAST:event_saveReactionMouseClicked

  private void startReactionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startReactionMouseClicked
      Top.startSystemProcess();
  }//GEN-LAST:event_startReactionMouseClicked

  private void initializeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_initializeButtonMouseClicked
      if(moleculesC0.isSelected()) {
          readMolecule(Top.SystemInfo.C0HO.getValue());
      }
      if(moleculesC1.isSelected()) {
          readMolecule(Top.SystemInfo.C1HO.getValue());
      }
      if(moleculesC2.isSelected()) {
          readMolecule(Top.SystemInfo.C2HO.getValue());
      }
      if(moleculesC3.isSelected()) {
          readMolecule(Top.SystemInfo.C3HO.getValue());
          readMolecule("C3H5O");
      }
      if(moleculesC4.isSelected()) {
          readMolecule(Top.SystemInfo.C4HO.getValue());
          readMolecule("C4ket");
      }
      
  }//GEN-LAST:event_initializeButtonMouseClicked

    void readMolecule(String base) {
        String dir = Top.SystemInfo.moleculesDir.getValue();
        String structname  = dir + "sets/" + base + "/structure.sdf";
        String dataname = dir + "sets/" + base + "/chemkin.inp";
        String moldatname = dir + "sets/" + base + "/moldat.inp";
        String moldatclass = dir + "inputs/" + Top.SystemInfo.chemkinClassFile.getValue();
         String dataclass = dir + "inputs/" + Top.SystemInfo.chemkinClassFile.getValue();
        String moveclass = dir + "inputs/MoleculeTransferClass.inp";
        String movit     = dir + "sets/" + base + "/MoleculeTransfer.inp";
        
        StringBuffer output = new StringBuffer();
        
        String command = "ReadMol Molecule " + structname;
        
        RunCommand runstruct = new RunCommand(Top,command,true);
        output.append(runstruct.commandOutput);
            
        ReadFile fread = new ReadFile(Top,
                        dataclass,dataname,
                        false);
        output.append(fread.commandOutput);
        
        ReadFile fread1 = new ReadFile(Top,
                        moveclass,movit,
                        false);
        output.append(fread1.commandOutput);

        RunAlgorithm runit = new RunAlgorithm(Top,
                        Top.SystemAlgorithms.dataBaseMove.getValue(),
                        false);
        runit.run();
        output.append(runit.commandOutput);
        
        if(equilibriumInit.isSelected()) {
            calculateEquilibrium(output);
        }
        if(storeInformation.isSelected()) { 
            RunCommand storeit = new RunCommand(Top,"Store Molecule Molecule InstanceNameList",false);
            //storeit.run();
            output.append(storeit.commandOutput);
        }
        
        ErrorFrame fr = new ErrorFrame(output.toString());
        fr.show();
    }
    
    void calculateEquilibrium(StringBuffer output) {
       String dir = Top.SystemInfo.initializeDirectory.getValue();
       String equilclass = dir + Top.SystemInfo.initEquilibrium.getValue() + "Class.inp";
       String equilname  = dir + Top.SystemInfo.initEquilibrium.getValue() + ".inp";
       ReadFile fread = new ReadFile(Top,
                        equilclass,equilname,
                        true);

       output.append(fread.commandOutput);
   
       RunAlgorithm runit = new RunAlgorithm(Top,
                        Top.SystemAlgorithms.expressionCalc.getValue(),
                        false);
       runit.run();
       output.append(runit.commandOutput);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton equilibriumInit;
    private javax.swing.JButton initializeButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton moleculesC0;
    private javax.swing.JRadioButton moleculesC1;
    private javax.swing.JRadioButton moleculesC2;
    private javax.swing.JRadioButton moleculesC3;
    private javax.swing.JRadioButton moleculesC4;
    private javax.swing.JButton saveReaction;
    private javax.swing.JButton startReaction;
    private javax.swing.JRadioButton storeInformation;
    // End of variables declaration//GEN-END:variables

}