/*
 * RunSystemLineCommand.java
 *
 * Created on March 11, 2001, 6:27 PM
 */

package blurock.core;
import react.common.TopReactionMenu;
import utilities.OutputFrame;
import link.*;

/**
 * This is a panel to run a command under ANALYSIS++.  This can be
 * used as a GUI (JPanel) or standalone to simplify running a job.
 * The basic sequence is:  setCommand(¨command); and then run();
 * with the output in commandOutput;
 * @author Edward S. Blurock
 */
public class RunSystemLineCommand extends javax.swing.JPanel {
    TopReactionMenu Top;
    String CommandName;
    String levelModS;
    boolean Operate;
    /**
     * This is the result of running the command.
     * It is null if a command has not been run or if
     * the command is unsuccessful.
     */
    public String commandOutput;
    boolean run = true;
   /**
     * Creates new form RunSystemLineCommand
     * @param top The Top menu global information
     */
    public RunSystemLineCommand(TopReactionMenu top) {
        initComponents ();
        Top = top;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel4 = new javax.swing.JPanel();
        saveTypePanel = new javax.swing.JPanel();
        experimentRun = new javax.swing.JRadioButton();
        changeRun = new javax.swing.JRadioButton();
        operateRun = new javax.swing.JRadioButton();
        commandPanel = new javax.swing.JPanel();
        command = new javax.swing.JTextField();
        buttonPanel = new javax.swing.JPanel();
        runCommand = new javax.swing.JButton();
        start = new javax.swing.JButton();
        textPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputText = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridLayout(3, 1));

        saveTypePanel.setLayout(new java.awt.GridLayout(1, 0));

        saveTypePanel.setBorder(new javax.swing.border.TitledBorder(null, "Command Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        experimentRun.setToolTipText("Do not change the history information, passive run");
        experimentRun.setSelected(true);
        experimentRun.setText("Experiment");
        saveTypePanel.add(experimentRun);

        changeRun.setToolTipText("Change the current history level, but don't update the level");
        changeRun.setText("Change");
        saveTypePanel.add(changeRun);

        operateRun.setToolTipText("Store the results of the run in the next level (and increment the level)");
        operateRun.setText("Operate");
        saveTypePanel.add(operateRun);

        jPanel4.add(saveTypePanel);

        commandPanel.setLayout(new java.awt.GridLayout(1, 0));

        commandPanel.setBorder(new javax.swing.border.TitledBorder(null, "Command", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        command.setPreferredSize(new java.awt.Dimension(200, 16));
        command.setToolTipText("The command to run");
        command.setText("Print Class");
        commandPanel.add(command);

        jPanel4.add(commandPanel);

        buttonPanel.setLayout(new java.awt.GridLayout(1, 2));

        buttonPanel.setBorder(new javax.swing.border.TitledBorder(null, "Execute Command", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        runCommand.setToolTipText("Run the command and put the output in another frame");
        runCommand.setText("Run Command");
        runCommand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                runCommandMouseClicked(evt);
            }
        });

        buttonPanel.add(runCommand);

        start.setText("Start System");
        start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startMouseClicked(evt);
            }
        });

        buttonPanel.add(start);

        jPanel4.add(buttonPanel);

        add(jPanel4, java.awt.BorderLayout.NORTH);

        textPanel.setLayout(new java.awt.BorderLayout());

        textPanel.setBorder(new javax.swing.border.TitledBorder(null, "Command Output", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        jScrollPane1.setViewportView(outputText);

        textPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(textPanel, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

  private void startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startMouseClicked
    initialStart();
  }//GEN-LAST:event_startMouseClicked

  private void runCommandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runCommandMouseClicked

      commandOutput = run();
      outputText.setText(commandOutput);
  }//GEN-LAST:event_runCommandMouseClicked
  private void setOperateType() {
      boolean operate = false;
      String operateName = "Experiment";
      if(changeRun.isSelected()) {
          operateName = "Change";
      } else if(operateRun.isSelected()) {
          operate = true;
          operateName = "Operate";
      }
  }
  private void initialStart() {
 	String command = 
	    Top.SystemInfo.reactionExecutable.getValue() + " xxx Initial " + 
	    Top.history.getHistoryName() + " 0";

        System.out.println("Run Command: '" + command + "'");
        Top.reactLink = new ReactionLink("Reaction::");
	Top.reactLink.start(command);
	Top.history.setHistoryLevel(0);
        Top.startReaction = true;     
  }
  /**
   * This sets the command (and the field in the GUI)
   * to the command to be executed under ANALYSIS++.
   * @param commandS The command string to be executed under ANALYSIS++
   */
  public void setCommand(String commandS) {
      command.setText(commandS);
  }
  /**
   * The current command (from the GUI).
   * @return The current command string
   */
  public String getCommand() {
      return command.getText();
  }
  /**
   * This sets the text of the Text Area in the GUI
   */
  public void setText() {
      outputText.setText(commandOutput);
  }
  /**
   * This runs the current ANALYSIS++ command.
   * The output is put into commandOutput and the TextArea of the GUI
   * @return This is true if the command was successful
   */
  public String run() {
        String outputS = null;
        if(run) {
              Top.initializeSystemIfNeeded();
              outputS = Top.reactLink.execute(getCommand());
        }
        return outputS;
    }
    /**
     * This outputs the command output to a new GUI Frame
     */
    public void showResultsToFrame() {
        OutputFrame fr = new OutputFrame(commandOutput);
        fr.show();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JRadioButton changeRun;
    private javax.swing.JTextField command;
    private javax.swing.JPanel commandPanel;
    private javax.swing.JRadioButton experimentRun;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton operateRun;
    /**
     * The GUI text area with the command output
     */
    public javax.swing.JTextArea outputText;
    private javax.swing.JButton runCommand;
    private javax.swing.JPanel saveTypePanel;
    private javax.swing.JButton start;
    private javax.swing.JPanel textPanel;
    // End of variables declaration//GEN-END:variables

}
