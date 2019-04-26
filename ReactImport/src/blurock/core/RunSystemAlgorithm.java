/*
 * RunSystemAlgorithm.java
 *
 * Created on March 12, 2001, 11:50 AM
 */

package blurock.core;
import react.common.TopReactionMenu;
import utilities.OutputFrame;
import blurock.utilities.*;
import react.utilities.StandardListDialog;

/**
 * The class runs an algorithm (the 'RunAlgorithm' command in ANALYSIS++)
 * @author Edward S. Blurock
 */
public class RunSystemAlgorithm extends javax.swing.JPanel {
    public TopReactionMenu Top;
    /**
     * The result of running a command. null if no command has been run.
     */
    public String commandOutput = null;
    /**
     * Creates new form RunSystemAlgorithm
     * @param top The global menu information
     */
    public RunSystemAlgorithm(TopReactionMenu top) {
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

        chooseAlgPanel = new javax.swing.JPanel();
        chooseAlgortithm = new javax.swing.JButton();
        algorithmToRun = new javax.swing.JTextField();
        outputDisplayPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputText = new javax.swing.JTextArea();
        runAlgPanel = new javax.swing.JPanel();
        runAlgorithm = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        chooseAlgPanel.setLayout(new java.awt.GridLayout(1, 2));

        chooseAlgortithm.setToolTipText("Choose from a list of available algorithms");
        chooseAlgortithm.setText("Choose");
        chooseAlgortithm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseAlgortithmMouseClicked(evt);
            }
        });

        chooseAlgPanel.add(chooseAlgortithm);

        algorithmToRun.setText("AlgorithmName");
        chooseAlgPanel.add(algorithmToRun);

        add(chooseAlgPanel, java.awt.BorderLayout.NORTH);

        outputDisplayPanel.setLayout(new java.awt.BorderLayout());

        outputDisplayPanel.setPreferredSize(new java.awt.Dimension(400, 200));
        outputDisplayPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        outputDisplayPanel.add(outputText, java.awt.BorderLayout.NORTH);

        add(outputDisplayPanel, java.awt.BorderLayout.CENTER);

        runAlgPanel.setLayout(new java.awt.GridLayout());

        runAlgorithm.setToolTipText("Run the algorithm and the result appears in an extra frame");
        runAlgorithm.setText("Run Algorithm");
        runAlgorithm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                runAlgorithmMouseClicked(evt);
            }
        });

        runAlgPanel.add(runAlgorithm);

        add(runAlgPanel, java.awt.BorderLayout.SOUTH);

    }//GEN-END:initComponents

  private void runAlgorithmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runAlgorithmMouseClicked
// Add your handling code here:
      //RunAlgorithm runit = new RunAlgorithm(Top,algorithmToRun.getText(),true);
      run();
      outputText.setText(commandOutput);
      //OutputFrame fr = new OutputFrame(commandOutput);
      //fr.show();
  }//GEN-LAST:event_runAlgorithmMouseClicked

  private void chooseAlgortithmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseAlgortithmMouseClicked
// Add your handling code here:
      MenuList attrs = Top.InstanceCommon.getAttributeList(false);
      String[] namelist = attrs.getNameList();
      for(int i= 0;i<namelist.length;i++)
          System.out.println(namelist[i]);
      StandardListDialog fr = new StandardListDialog(namelist);
      fr.show();
      String[] names = fr.listPanel.selectedItemsToString(false);
      if(names.length > 0) {
          algorithmToRun.setText(names[0]);
      }
  }//GEN-LAST:event_chooseAlgortithmMouseClicked
  /**
   * Set the algorithm name.
   * @param algname The algorithm name to run.
   */
  public void setAlgorithm(String algname) {
    algorithmToRun.setText(algname);
  }
  /**
   * Retrieve the algorithm name
   * @return The algorithm name to run
   */
  public String getAlgorithmName() {
      return algorithmToRun.getText();
  }
  /**
   * This makes to call to ANALYSIS++ to run the algorithm
   */
  public void run() {
      String command = setCommand();
      System.out.println("Run Command: '" + command + "'");
      commandOutput = Top.reactLink.execute(command);
      outputText.setText(commandOutput);
    }
  /**
   * This creates the complete algorithm command with name and history.
   * @return The complete command line to be executed by ANALYSIS++.
   */
  public String setCommand() {
      int level = Top.history.getDebugLevel();
      Integer levelI = new Integer(level);
      
 	String command = 
	    "RunAlgorithm " +
	    getAlgorithmName() + " " + 
	    levelI.toString();
         return command;
  }
    /**
     * This creates an external frame to display the results of running the command.
     */
    public void showResultsInFrame() {
        if(commandOutput != null) {
            OutputFrame fr = new OutputFrame(commandOutput);
            fr.show();
        }
    }
    public String getOutput() {
        return outputText.getText();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JTextField algorithmToRun;
    private javax.swing.JPanel chooseAlgPanel;
    private javax.swing.JButton chooseAlgortithm;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel outputDisplayPanel;
    protected javax.swing.JTextArea outputText;
    private javax.swing.JPanel runAlgPanel;
    private javax.swing.JButton runAlgorithm;
    // End of variables declaration//GEN-END:variables

}
