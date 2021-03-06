/*
 * InitializeSystem.java
 *
 * Created on March 9, 2001, 11:34 AM
 */

package blurock.core;
import react.common.TopReactionMenu;
import utilities.ErrorFrame;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class InitializeSystem extends javax.swing.JPanel {
    TopReactionMenu Top;
    /** Creates new form InitializeSystem */
    public InitializeSystem(TopReactionMenu top) {
        Top = top;
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        initializeSystem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        basicInitial = new javax.swing.JRadioButton();
        algorithmsInitial = new javax.swing.JRadioButton();
        logicInitial = new javax.swing.JRadioButton();
        expresssionInitial = new javax.swing.JRadioButton();
        graphInitial = new javax.swing.JRadioButton();
        rulesInitial = new javax.swing.JRadioButton();

        setLayout(new java.awt.BorderLayout());

        initializeSystem.setText("Initialize");
        initializeSystem.setToolTipText("Start the initialization procedure");
        initializeSystem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                initializeSystemMouseClicked(evt);
            }
        });

        jPanel1.add(initializeSystem);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.GridLayout(6, 1));

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Initialization Parameters"));
        basicInitial.setSelected(true);
        basicInitial.setText("Basic Initialization");
        basicInitial.setToolTipText("Perform the basic initialization");
        jPanel2.add(basicInitial);

        algorithmsInitial.setText("Standard Algorithms");
        jPanel2.add(algorithmsInitial);

        logicInitial.setText("Standard Logical Objects");
        jPanel2.add(logicInitial);

        expresssionInitial.setText("Standard Expressions");
        jPanel2.add(expresssionInitial);

        graphInitial.setText("Standard Graph Functions");
        jPanel2.add(graphInitial);

        rulesInitial.setText("Standard Rule Objects");
        jPanel2.add(rulesInitial);

        add(jPanel2, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents
    public void basicInitialization() {
	String command = 
	    Top.SystemInfo.reactionExecutable.getValue() + " xxx Initial " + 
	    Top.history.getHistoryName() + " 0";
	Top.reactLink.start(command);
	//String commandOutput = Top.tLink.execute(new String("\n"));
        //Top.tLink.execute("\n");
        //Top.tLink.execute("\n");
	//Top.tLink.stop();
	Top.history.setHistoryLevel(0);
        Top.startReaction = true;
        //ErrorFrame fr = new ErrorFrame(commandOutput);
        //fr.show();
    }
    public void algorithmsInitialization() {
          ReadFile fread = new ReadFile(Top,
                        buildClassFileName(Top.SystemInfo.standardAlgorithms.getValue()),
                        buildAttrFileName(Top.SystemInfo.standardAlgorithms.getValue()),
                        false);
         ErrorFrame fr = new ErrorFrame(fread.commandOutput);
         fr.show();
         RunCommand runit1 = new RunCommand(Top,"SetGoalClass TestGoalRun",false);
         //runit1.run();
         RunCommand runit2 = new RunCommand(Top,"SetAlgorithmClass TestGoalRun",false);
         //runit2.run();
    }
    public void logicInitialization() {
          ReadFile fread = new ReadFile(Top,
                        buildClassFileName(Top.SystemInfo.standardLogic.getValue()),
                        buildAttrFileName(Top.SystemInfo.standardLogic.getValue()),
                        false);
          readRegistered(Top.SystemInfo.standardLogic.getValue());
         ErrorFrame fr = new ErrorFrame(fread.commandOutput);
         fr.show();
    }
    public void expressionInitialization() {
          ReadFile fread = new ReadFile(Top,
                        buildClassFileName(Top.SystemInfo.standardExpressions.getValue()),
                        buildAttrFileName(Top.SystemInfo.standardExpressions.getValue()),
                        false);
          readRegistered(Top.SystemInfo.standardExpressions.getValue());
         ErrorFrame fr = new ErrorFrame(fread.commandOutput);
         fr.show();
    }
    public void graphInitialization() {
          ReadFile fread = new ReadFile(Top,
                        buildClassFileName(Top.SystemInfo.standardGraph.getValue()),
                        buildAttrFileName(Top.SystemInfo.standardGraph.getValue()),
                        false);
          //readRegistered(Top.SystemInfo.standardGraph.getValue());
         ErrorFrame fr = new ErrorFrame(fread.commandOutput);
         fr.show();
    }
    public void rulesInitialization() {
          ReadFile fread = new ReadFile(Top,
                        buildClassFileName(Top.SystemInfo.standardRules.getValue()),
                        buildAttrFileName(Top.SystemInfo.standardRules.getValue()),
                        false);
          readRegistered(Top.SystemInfo.standardRules.getValue());
         ErrorFrame fr = new ErrorFrame(fread.commandOutput);
         fr.show();
    }
  private void initializeSystemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_initializeSystemMouseClicked
      if(basicInitial.isSelected()) {
          basicInitialization();
      }
      if(algorithmsInitial.isSelected()) {
          algorithmsInitialization();
      }
      if(logicInitial.isSelected()) {
            logicInitialization();
      }
      if(expresssionInitial.isSelected()) {
       expressionInitialization();
      }
      if(graphInitial.isSelected()) {
          graphInitialization();
       }
       if(rulesInitial.isSelected()) {
           rulesInitialization();
       }
  }//GEN-LAST:event_initializeSystemMouseClicked
  void readRegistered(String base) {
        String clsFile = new String(Top.SystemInfo.initializeDirectory.getValue() +
                                        "registry/" + base + "Class.inp");
        try {
            Top.readWriteManager.openInputFile(clsFile);
            Top.registeredClasses.Read(Top.readWriteManager);
            Top.readWriteManager.closeInputFile();
        } catch(IOException exp) {
            ErrorFrame fr = new ErrorFrame("Problem with Reading File: \n" + 
                                            clsFile + "\n" + exp.toString());
            fr.show();
        }
  }
  String buildClassFileName(String base) {
      String dir = Top.SystemInfo.initializeDirectory.getValue();
      return dir + base + "Class.inp";
  }
  String buildAttrFileName(String base) {
      String dir = Top.SystemInfo.initializeDirectory.getValue();
      return dir + base + ".inp";
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JRadioButton rulesInitial;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JRadioButton algorithmsInitial;
    public javax.swing.JRadioButton basicInitial;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JRadioButton graphInitial;
    private javax.swing.JRadioButton logicInitial;
    private javax.swing.JButton initializeSystem;
    protected javax.swing.JRadioButton expresssionInitial;
    // End of variables declaration//GEN-END:variables

}
