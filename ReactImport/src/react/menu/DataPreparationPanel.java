/*
 * DataPreparationPanel.java
 *
 * Created on January 30, 2002, 4:19 PM
 */

package react.menu;
import react.common.TopReactionMenu;
import blurock.utilities.MenuList;
import react.utilities.StandardListDialog;
import react.utilities.StandardListPanel;
import blurock.utilities.SetUpClassAttrFile;
import blurock.core.RunAlgorithm;
import utilities.OutputFrame;
import blurock.instattr.SelectInstanceAttributes;
/**
 *
 * @author  reaction
 * @version 
 */
public class DataPreparationPanel extends javax.swing.JPanel {
    protected TopReactionMenu Top;
    protected String[] instanceNames = null;
    protected String[] parameterList = null;
    
    String distRootName = new String("RootName");
    String distName = new String("Distribution_");
    String normName = new String("Norm_");
    String instanceListName = new String("TotalInstanceNameList");
    String parameterListName = new String("AttributeNames");
    
    /**
     * Creates new form DataPreparationPanel 
     */
    public DataPreparationPanel(TopReactionMenu top) {
        Top = top;
        initComponents ();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        inputPanel = new javax.swing.JPanel();
        dependentVariablePanel = new javax.swing.JPanel();
        dependentSelect = new javax.swing.JButton();
        dependentVariable = new javax.swing.JTextField();
        derivativeSlider = new javax.swing.JSlider();
        zeroSlider = new javax.swing.JSlider();
        outputPanel = new javax.swing.JPanel();
        outputScroll = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();
        buttonPanel = new javax.swing.JPanel();
        derivativePanel = new javax.swing.JPanel();
        derivativeButton = new javax.swing.JButton();
        normailzePanel = new javax.swing.JPanel();
        distributionButton = new javax.swing.JButton();
        normalizeButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        inputPanel.setLayout(new java.awt.GridLayout(3, 1));

        dependentVariablePanel.setLayout(new java.awt.GridLayout(1, 2));

        dependentVariablePanel.setBorder(new javax.swing.border.TitledBorder("Dependent Variable"));
        dependentSelect.setText("Select");
        dependentSelect.setToolTipText("Select dependent variable from list");
        dependentSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dependentSelectMouseClicked(evt);
            }
        });

        dependentVariablePanel.add(dependentSelect);

        dependentVariable.setText("t[ms]");
        dependentVariable.setMinimumSize(new java.awt.Dimension(150, 16));
        dependentVariable.setPreferredSize(new java.awt.Dimension(150, 16));
        dependentVariablePanel.add(dependentVariable);

        inputPanel.add(dependentVariablePanel);

        derivativeSlider.setMajorTickSpacing(1);
        derivativeSlider.setMaximum(4);
        derivativeSlider.setMinimum(1);
        derivativeSlider.setPaintLabels(true);
        derivativeSlider.setPaintTicks(true);
        derivativeSlider.setSnapToTicks(true);
        derivativeSlider.setValue(1);
        derivativeSlider.setBorder(new javax.swing.border.TitledBorder("Derivative Order"));
        inputPanel.add(derivativeSlider);

        zeroSlider.setMajorTickSpacing(1);
        zeroSlider.setMaximum(15);
        zeroSlider.setMinimum(1);
        zeroSlider.setPaintLabels(true);
        zeroSlider.setPaintTicks(true);
        zeroSlider.setSnapToTicks(true);
        zeroSlider.setValue(8);
        zeroSlider.setBorder(new javax.swing.border.TitledBorder("Zero Definition   10**(-x)"));
        inputPanel.add(zeroSlider);

        add(inputPanel, java.awt.BorderLayout.NORTH);

        outputPanel.setLayout(new java.awt.BorderLayout());

        outputScroll.setViewportView(outputTextArea);

        outputPanel.add(outputScroll, java.awt.BorderLayout.CENTER);

        add(outputPanel, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.GridLayout(2, 1));

        derivativePanel.setLayout(new java.awt.GridLayout(1, 2));

        derivativePanel.setBorder(new javax.swing.border.TitledBorder("Parameter Derivatives"));
        derivativeButton.setText("Derivative");
        derivativeButton.setToolTipText("Take the set of derivatives of the variables");
        derivativeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                derivativeButtonMouseClicked(evt);
            }
        });

        derivativePanel.add(derivativeButton);

        buttonPanel.add(derivativePanel);

        normailzePanel.setLayout(new java.awt.GridLayout(1, 2));

        normailzePanel.setBorder(new javax.swing.border.TitledBorder("Normalize Parameters"));
        distributionButton.setText("Distribution");
        distributionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                distributionButtonMouseClicked(evt);
            }
        });

        normailzePanel.add(distributionButton);

        normalizeButton.setText("Normalize");
        normalizeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                normalizeButtonMouseClicked(evt);
            }
        });

        normailzePanel.add(normalizeButton);

        buttonPanel.add(normailzePanel);

        add(buttonPanel, java.awt.BorderLayout.SOUTH);

    }
    // </editor-fold>//GEN-END:initComponents

    private void dependentSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dependentSelectMouseClicked
        setupDependentVariable();
    }//GEN-LAST:event_dependentSelectMouseClicked

  private void derivativeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_derivativeButtonMouseClicked
    derivatives();
  }//GEN-LAST:event_derivativeButtonMouseClicked

  private void distributionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_distributionButtonMouseClicked
    parameterDistributions();
  }//GEN-LAST:event_distributionButtonMouseClicked

  private void normalizeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_normalizeButtonMouseClicked
    normalizeSetUpParameters();
    normalizeParameters();
  }//GEN-LAST:event_normalizeButtonMouseClicked
  protected void setupDependentVariable() {
        MenuList mlist = Top.InstanceCommon.getInstanceAttributeList(true);
        parameterList = mlist.getNameList();
        StandardListDialog plist = new StandardListDialog(parameterList);
        plist.setVisible(true);
        String[] names = plist.listPanel.selectedItemsToString(true);
        dependentVariable.setText(new String(names[0]));  
  }
  protected void startParameterSelection() {
      Top.selectInstanceAttributes.setVisible(true);
  }
  protected void setupAllInstances() {
    MenuList mlist = Top.InstanceCommon.getInstanceList(true);
    instanceNames = mlist.getNameList();
  }
  protected void setupAllParameters() {
    MenuList mlist = Top.InstanceCommon.getInstanceAttributeList(true);
    parameterList = mlist.getNameList();
  }
  protected void parameterDistributions() {
      SetUpClassAttrFile ofile = new SetUpClassAttrFile(Top,"Numeric","Data Preparation");
        SelectInstanceAttributes attrs = (SelectInstanceAttributes)
                Top.selectInstanceAttributes.panelObject;
      
      setupParameterDistributions(ofile,attrs);
      
      RunAlgorithm alg1 = new RunAlgorithm(Top,"MatrixSetUpAlg",true);
      alg1.run();
      StringBuffer buf = new StringBuffer();
      buf.append("\n===============================================\n");
      buf.append("Setup Matrix for Distribution\n");
      buf.append("===============================================\n");
      buf.append(alg1.commandOutput);
      RunAlgorithm alg2 = new RunAlgorithm(Top,"DistributionAlg",true);
      alg2.run();
      buf.append("\n===============================================\n");
      buf.append("Determine Distribution\n");
      buf.append("===============================================\n");
      buf.append(alg2.commandOutput);
      outputTextArea.append(buf.toString());
  }
  protected void setupParameterDistributions(SetUpClassAttrFile ofile,
                        SelectInstanceAttributes attrs) {
        parameterList = attrs.getSelectedAttributes();
        if(instanceNames == null)
          setupAllInstances();
      if(parameterList == null) 
          setupAllParameters();

      ofile.printString(distRootName,distName);
      ofile.printKeyWords(instanceListName,instanceNames);
      ofile.printKeyWords(parameterListName,parameterList);
      ofile.read(true);      
  }
  protected void normalizeSetUpParameters() {
        SelectInstanceAttributes attrs = (SelectInstanceAttributes)
                Top.selectInstanceAttributes.panelObject;
      SetUpClassAttrFile ofile = new SetUpClassAttrFile(Top,"NormalizeSetUp","Normalize Preparation");
       normalizeSetUpParameters(ofile,attrs);
 
      RunAlgorithm alg1 = new RunAlgorithm(Top,"NormalizeFromDistAlg",true);
      alg1.run();
      outputTextArea.append(alg1.commandOutput);
  }
  protected void normalizeSetUpParameters(SetUpClassAttrFile ofile, SelectInstanceAttributes attrs) {
      parameterList = attrs.getSelectedAttributes();

      ofile.addClassTypeAsString("SimpleNormalize","Normalize","A Simple Normalize","Real Real");
      String spec = new String("+    NumOpPlus\n-    NumOpMinus\n*    NumOpMultiply\n/    NumOpDivide\nEND");
      ofile.addClassTypeAsString("ExpressionTree", "NormalizeParameters",
                                 "Normalize Expressions", spec);
      ofile.addClassTypeAsString("SimpleNormalizeOperation","ParameterizedNormalize",
                                 "Parameterized Normalization","ParameterSet Normalize Real\nEND");

      String[] vars = new String[parameterList.length];
      for(int i=0; i<parameterList.length;i++) {
          vars[i] = new String(distName + parameterList[i]);
      }
      ofile.printKeyWords("Distributions",vars);
      ofile.printString("OperationRoot","NormOp");
      ofile.printString("OperationClass","ParameterizedNormalize");
      ofile.printString("ExpressionTreeClass","NormalizeParameters");
      ofile.read(true);

  }
  protected void normalizeParameters() {
      SetUpClassAttrFile ofile = new SetUpClassAttrFile(Top,"Normalize","Normalize Preparation");
      
      normalizeParametersPreparation(ofile);
      
      RunAlgorithm alg1 = new RunAlgorithm(Top,"ExpressionTreeAlg",true);
      alg1.run();
      outputTextArea.append("================================================================");
      outputTextArea.append("        Normalize Parameters");
      outputTextArea.append(alg1.commandOutput);
  }
  protected void normalizeParametersPreparation(SetUpClassAttrFile ofile) {          
      int length = parameterList.length*2 + 1;
      String[] vars = new String[length];
      String[] names = new String[parameterList.length];
      int cnt = 1;
      vars[0] = new String("Instance");
      String type = new String("NormalizeParameters");
      for(int i=0; i<parameterList.length;i++) {
          String name = new String(normName + parameterList[i]);
          String obj  = new String("( NormOp" + parameterList[i] + " " + parameterList[i] + " )");
          ofile.printObjectAsString(name,obj,type);
          vars[cnt++] = new String(normName + parameterList[i]);
          vars[cnt++] = new String(normName + parameterList[i]);
          names[i] = name;
      }
      ofile.printKeySet("VarsAndExps",vars);
      ofile.printKeyWords("AttributeList",names);
      ofile.read(true);

  }
  protected void derivatives() {
      setupParameterList();
      if(instanceNames == null)
          setupAllInstances();
      if(parameterList == null) 
          setupAllParameters();
      SetUpClassAttrFile ofile = new SetUpClassAttrFile(Top,"Derivatives","Derivative of Parameters");
      derivativesPreparation(ofile);
      derivativesAlgorithm();
  }
  protected void derivativesAlgorithm(){
      RunAlgorithm alg2 = new RunAlgorithm(Top,"NumericProperties",true);
      alg2.run();
      outputTextArea.append("================================================================");
      outputTextArea.append("        Compute Derivatives");
      outputTextArea.append(alg2.commandOutput);
  }
  protected void setupParameterList() {
     SelectInstanceAttributes attrs = (SelectInstanceAttributes)
                Top.selectInstanceAttributes.panelObject;
     parameterList = attrs.getSelectedAttributes();
  }
  protected void derivativesPreparation(SetUpClassAttrFile ofile) {
      String[] dep = new String[1];
      String[] plist = new String[parameterList.length+1];
      dep[0] = dependentVariable.getText();
      for(int i=0; i<parameterList.length;i++) {
            plist[i] = parameterList[i];
      }
      plist[parameterList.length] = dep[0];
      ofile.printKeyWords(instanceListName,instanceNames);
      ofile.printKeyWords(parameterListName,plist);
      ofile.printKeyWords("XParameters",dep);
      ofile.printKeyWords("YParameters",parameterList);
      ofile.printInteger("LowerPower",0);
      ofile.printInteger("UpperPower",7);
      ofile.printInteger("NumberOfDerivatives",derivativeSlider.getValue());
      ofile.printString("SortParameter", dep[0]);
      int zeropowerI = zeroSlider.getValue();
      double zeropowerD = (double) zeropowerI;
      double ten = 10.0;
      double zero = Math.pow(ten, -zeropowerD);
      ofile.printReal("NumericZeroValue",zero);
      ofile.read(true);
  }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton dependentSelect;
    protected javax.swing.JTextField dependentVariable;
    private javax.swing.JPanel dependentVariablePanel;
    private javax.swing.JButton derivativeButton;
    private javax.swing.JPanel derivativePanel;
    public javax.swing.JSlider derivativeSlider;
    private javax.swing.JButton distributionButton;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JPanel normailzePanel;
    private javax.swing.JButton normalizeButton;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JScrollPane outputScroll;
    protected javax.swing.JTextArea outputTextArea;
    public javax.swing.JSlider zeroSlider;
    // End of variables declaration//GEN-END:variables

}
