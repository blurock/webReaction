/*
 * MechanismGenerateAndCombineMenu.java
 *
 * Created on March 25, 2000, 11:11 PM
 */
package react.mechanisms;
import react.common.*;
import react.utilities.StandardListDialog;
import blurock.files.*;
import java.util.StringTokenizer;
import utilities.OutputFrame;
import utilities.ErrorFrame;
import java.io.*;
import utilities.FileToString;

/**
 *
 * @author  reaction
 * @version 
 */
public class MechanismGenerateAndCombineMenu extends javax.swing.JPanel {

    TopReactionMenu Top;
    /** Creates new form MechanismGenerateAndCombineMenu */
    public MechanismGenerateAndCombineMenu(TopReactionMenu top) {
        Top = top;
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel9 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        selectMolecules = new javax.swing.JButton();
        selectSubmechanisms = new javax.swing.JButton();
        mechanismSelectButton = new javax.swing.JButton();
        readMolecules = new javax.swing.JButton();
        readSubmechanisms = new javax.swing.JButton();
        selectCombined = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        runAll = new javax.swing.JRadioButton();
        runCombined = new javax.swing.JButton();
        combineButton = new javax.swing.JButton();
        setOfMolecules = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        combineMechanismName = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        moleculeSet = new react.utilities.StandardListPanel();
        subMechanismSet = new react.utilities.StandardListPanel();
        fullSetOfSubMechanisms = new react.utilities.StandardListPanel();
        jPanel3 = new javax.swing.JPanel();
        subMechanismsToRun = new react.utilities.StandardListPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.GridLayout(2, 1));

        jPanel5.setLayout(new java.awt.GridLayout(2, 3));

        selectMolecules.setText("Select Molecules");
        selectMolecules.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMoleculesMouseClicked(evt);
            }
        });

        jPanel5.add(selectMolecules);

        selectSubmechanisms.setText("Select Submechanisms");
        selectSubmechanisms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectSubmechanismsMouseClicked(evt);
            }
        });

        jPanel5.add(selectSubmechanisms);

        mechanismSelectButton.setText("Select Out Combined");
        mechanismSelectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mechanismSelectButtonMouseClicked(evt);
            }
        });

        jPanel5.add(mechanismSelectButton);

        readMolecules.setText("Read Molecules");
        readMolecules.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                readMoleculesMouseClicked(evt);
            }
        });

        jPanel5.add(readMolecules);

        readSubmechanisms.setText("Read SubMechanisms");
        readSubmechanisms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                readSubmechanismsMouseClicked(evt);
            }
        });

        jPanel5.add(readSubmechanisms);

        selectCombined.setText("Select Combined");
        selectCombined.setToolTipText("Select out specific combined submechanisms");
        selectCombined.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectCombinedMouseClicked(evt);
            }
        });

        jPanel5.add(selectCombined);

        jPanel9.add(jPanel5);

        jPanel4.setLayout(new java.awt.GridLayout(2, 1));

        jPanel4.setBorder(new javax.swing.border.TitledBorder("Run Combined Information"));
        jPanel7.setLayout(new java.awt.GridLayout(1, 3));

        runAll.setSelected(true);
        runAll.setText("Use All");
        runAll.setToolTipText("Check if all combined mechanisms should be calculated");
        jPanel7.add(runAll);

        runCombined.setText("Generate Combined");
        runCombined.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                runCombinedMouseClicked(evt);
            }
        });

        jPanel7.add(runCombined);

        combineButton.setText("Combine");
        combineButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combineButtonMouseClicked(evt);
            }
        });

        jPanel7.add(combineButton);

        jPanel4.add(jPanel7);

        setOfMolecules.setText("oxygen,oxygen-radical,hydrogen-radical,methyl-radical,hydroxyl-radical,peroxyl-radical,methylperoxy-radical,methoxy-radical,hydrogen-peroxide,ethyl-radical,ethenyl-radical");
        setOfMolecules.setToolTipText("Set of molecules to include with mechanism generation");
        setOfMolecules.setMinimumSize(new java.awt.Dimension(200, 20));
        setOfMolecules.setPreferredSize(new java.awt.Dimension(200, 20));
        setOfMolecules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setOfMoleculesActionPerformed(evt);
            }
        });

        jPanel4.add(setOfMolecules);

        jPanel9.add(jPanel4);

        add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.GridLayout(1, 2));

        jLabel1.setText("Combined Name");
        jPanel8.add(jLabel1);

        combineMechanismName.setText("Test-Combined");
        combineMechanismName.setToolTipText("The name of the generated combined mechanism");
        jPanel8.add(combineMechanismName);

        add(jPanel8, java.awt.BorderLayout.SOUTH);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(200, 400));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(440, 400));
        jPanel6.setLayout(new java.awt.GridLayout(3, 1));

        jPanel6.setMinimumSize(new java.awt.Dimension(430, 1300));
        moleculeSet.setLayout(new java.awt.FlowLayout());

        moleculeSet.setBorder(new javax.swing.border.TitledBorder("Molecules"));
        moleculeSet.setMinimumSize(new java.awt.Dimension(400, 200));
        moleculeSet.setPreferredSize(new java.awt.Dimension(400, 200));
        jPanel6.add(moleculeSet);

        subMechanismSet.setLayout(new java.awt.FlowLayout());

        subMechanismSet.setBorder(new javax.swing.border.TitledBorder("Submechanisms"));
        subMechanismSet.setMinimumSize(new java.awt.Dimension(400, 200));
        subMechanismSet.setPreferredSize(new java.awt.Dimension(400, 200));
        jPanel6.add(subMechanismSet);

        fullSetOfSubMechanisms.setLayout(new java.awt.FlowLayout());

        fullSetOfSubMechanisms.setBorder(new javax.swing.border.TitledBorder("Submechanisms To Generate"));
        fullSetOfSubMechanisms.setMinimumSize(new java.awt.Dimension(400, 200));
        fullSetOfSubMechanisms.setPreferredSize(new java.awt.Dimension(400, 200));
        jPanel6.add(fullSetOfSubMechanisms);

        jScrollPane1.setViewportView(jPanel6);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        subMechanismsToRun.setBorder(new javax.swing.border.TitledBorder("Mechanisms To Generate"));
        jPanel3.add(subMechanismsToRun, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        add(jPanel1, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

    private void setOfMoleculesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setOfMoleculesActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_setOfMoleculesActionPerformed

  private void readMoleculesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readMoleculesMouseClicked
    FileToString f = new FileToString("Submechanism List",".","lst");
    StringTokenizer strtoken = new StringTokenizer(f.outputString);
    String[] molnames = new String[strtoken.countTokens()];
    int count = 0;
    while(strtoken.hasMoreTokens()) {
        String name = strtoken.nextToken();
        molnames[count] = name;
        count++;
    }
    String[] reducedlist = new String[count];
    for(int i=0;i<count;i++) {
        reducedlist[i] = molnames[i];
    }
    moleculeSet.setData(reducedlist);
  }//GEN-LAST:event_readMoleculesMouseClicked

  private void readSubmechanismsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readSubmechanismsMouseClicked
    FileToString f = new FileToString("Submechanism List",".","lst");
    StringTokenizer strtoken = new StringTokenizer(f.outputString);
    String[] molnames = new String[strtoken.countTokens()];
    int count = 0;
    while(strtoken.hasMoreTokens()) {
        String name = strtoken.nextToken();
        molnames[count] = name;
        count++;
    }
    String[] reducedlist = new String[count];
    for(int i=0;i<count;i++) {
        reducedlist[i] = molnames[i];
    }
    subMechanismSet.setData(reducedlist);
  }//GEN-LAST:event_readSubmechanismsMouseClicked

  private void combineButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combineButtonMouseClicked
// Add your handling code here:

      String[] combined = subMechanismsToRun.selectedItemsToString(true);
      String dir = Top.SystemInfo.submechanismsDir.getValue();
      try {
          String filename = combineMechanismName.getText() + ".lst";

        PrintWriter comb = new PrintWriter(new FileOutputStream(filename));
        for(int i=0;i<combined.length;i++) {
          StringTokenizer tokens = new StringTokenizer(combined[i],"#");
          String mol = tokens.nextToken();
          String mech = tokens.nextToken();
          comb.println(mol + "-" + mech);
        }          
        comb.close();
          String combcommand = 
                Top.Scripts.runCombineMechanism.getValue() + " " + 
                combineMechanismName.getText() + " " + 
                combineMechanismName.getText();
          String commandOutput = Top.tLink.singleCommand(combcommand);
          //String commandOutput = Top.tLink.singleCommand("");
          OutputFrame fr = new OutputFrame(commandOutput);
          fr.show();
          Top.tLink.stop();
          
        String prtcommand = 
                Top.Scripts.printOutMechanism.getValue() + " " + 
                combineMechanismName.getText() + " " + 
                combineMechanismName.getText();
          System.out.println("Command: " + prtcommand);
          String commandOutput1 = Top.tLink.singleCommand(prtcommand);
          //String commandOutput1 = Top.tLink.execute("");
          OutputFrame fr1 = new OutputFrame(commandOutput1);
          fr1.show();
          Top.tLink.stop();
      } catch (FileNotFoundException ex) {
          ErrorFrame fr = new ErrorFrame("Couldn't Open Output File");
      }
           
  }//GEN-LAST:event_combineButtonMouseClicked

  private void selectCombinedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectCombinedMouseClicked
// Add your handling code here:
     String[] sel = fullSetOfSubMechanisms.selectedItemsToString(true);
      subMechanismsToRun.setData(sel);

  }//GEN-LAST:event_selectCombinedMouseClicked

  private void runCombinedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runCombinedMouseClicked
// Add your handling code here:
      String[] combined = subMechanismsToRun.selectedItemsToString(true);
      String dir = Top.SystemInfo.submechanismsDir.getValue();
      try {
        String filename = combineMechanismName.getText() + ".lst";
        PrintWriter comb = new PrintWriter(new FileOutputStream(filename));
        for(int i=0;i<combined.length;i++) {
          StringTokenizer tokens = new StringTokenizer(combined[i],"#");
          String mol = tokens.nextToken();
          String mech = tokens.nextToken();
          String molid = Top.Common.MoleculeList.getIDFromName(mol);
          System.out.println("runCombinedMouseClicked: '" + mol + "': " + molid);
          String command = Top.Scripts.runChain.getValue() + " " +
                    dir + "/" + mech + " " +
                    molid + "," + setOfMolecules.getText() + " " +
                    mol + "-" + mech;
         // System.out.println("Molecule: " + mol + "' = " + 
                //Top.Common.MoleculeList.getIDFromName(mol));
          System.out.println(command);
          String commandOutput = Top.tLink.singleCommand(command);
          //String commandOutput = Top.tLink.execute("");
          OutputFrame fr = new OutputFrame(commandOutput);
          fr.show();
          Top.tLink.stop();
        }
        comb.close();
          
        
      } catch (FileNotFoundException ex) {
          ErrorFrame fr = new ErrorFrame("Couldn't Open Output File");
      }
  }//GEN-LAST:event_runCombinedMouseClicked

  private void combineMechanismButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combineMechanismButtonMouseClicked
// Add your handling code here:
      String[] full = fullSetOfSubMechanisms.selectedItemsToString(true);
      String[] sel = new String[full.length];
      int cnt = 0;
      for(int j=0; j<full.length;j++) {
         if(!Top.Common.ReactionMechanismList.isInList(full[j])) {
                sel[cnt] = full[j];
                cnt++;
        }
      }
      String[] reduced = new String[cnt];
      for(int j=0; j<cnt;j++)
          reduced[j] = sel[j];
      
      subMechanismsToRun.setData(reduced);
  }//GEN-LAST:event_combineMechanismButtonMouseClicked

  private void mechanismSelectButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mechanismSelectButtonMouseClicked
      String[] mols = moleculeSet.selectedItemsToString(true);
      String[] mechs = subMechanismSet.selectedItemsToString(true);
      int num = mols.length * mechs.length;
      String[] next = new String[num];
      String[] notinlist = new String[num];
      int count = 0;
      int cnt = 0;
      for(int i=0; i<mols.length;i++) {
          System.out.println("Molecule: '" + mols[i] + "' = " + 
                Top.Common.MoleculeList.getIDFromName(mols[i]));
          for(int j=0; j<mechs.length;j++) {
              next[count] = mols[i] + "#" + mechs[j];
              if(runAll.isSelected() || 
                    !Top.Common.ReactionMechanismList.isInList(next[count])) {
                notinlist[cnt] = next[count];
                cnt++;
              }
              count++;
          }
      }
      fullSetOfSubMechanisms.setData(next);

      String[] torun = new String[cnt];
      for(int i=0; i<cnt;i++)
          torun[i] = notinlist[i];
      subMechanismsToRun.setData(torun);
      
      
  }//GEN-LAST:event_mechanismSelectButtonMouseClicked

  private void selectSubmechanismsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectSubmechanismsMouseClicked
// Add your handling code here:
      String dir = Top.SystemInfo.submechanismsDir.getValue();
      ListOfFiles fls = new ListOfFiles(dir);
      fls.findFileWithExtension("lsr");
      StandardListDialog mechs = new StandardListDialog(fls.listOfNames);
      mechs.show();
      if(mechs.getReturnStatus () == mechs.RET_OK) {
        String[] selected = mechs.listPanel.selectedItemsToString(false);
        subMechanismSet.setData(selected);
      }
  }//GEN-LAST:event_selectSubmechanismsMouseClicked

  private void selectMoleculesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMoleculesMouseClicked
// Add your handling code here:
      String[] fullmols = Top.Common.MoleculeList.getNameList();
      StandardListDialog mols = new StandardListDialog(fullmols);
      mols.show();
      if(mols.getReturnStatus () == mols.RET_OK) {
        String[] selected = mols.listPanel.selectedItemsToString(false);
        moleculeSet.setData(selected);
      }
      
  }//GEN-LAST:event_selectMoleculesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton combineButton;
    private javax.swing.JTextField combineMechanismName;
    private react.utilities.StandardListPanel fullSetOfSubMechanisms;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mechanismSelectButton;
    private react.utilities.StandardListPanel moleculeSet;
    private javax.swing.JButton readMolecules;
    private javax.swing.JButton readSubmechanisms;
    private javax.swing.JRadioButton runAll;
    private javax.swing.JButton runCombined;
    private javax.swing.JButton selectCombined;
    private javax.swing.JButton selectMolecules;
    private javax.swing.JButton selectSubmechanisms;
    private javax.swing.JTextField setOfMolecules;
    private react.utilities.StandardListPanel subMechanismSet;
    private react.utilities.StandardListPanel subMechanismsToRun;
    // End of variables declaration//GEN-END:variables

}
