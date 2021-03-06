/*
 * BuildReaction.java
 *
 * Created on April 1, 2002, 7:45 AM
 */

package react.mechanisms;
import react.common.TopReactionMenu;
import blurock.instattr.GetAttributeParameter;
import blurock.coreobjects.BaseDataKeyWords;
import utilities.ErrorFrame;
import blurock.utilities.SetUpClassAttrFile;
import blurock.core.RunAlgorithm;
import blurock.core.RunCommand;
import blurock.utilities.SetUpClassAttrFile;

/**
 *
 * @author  reaction
 * @version 
 */
public class BuildLumpedMechanism extends javax.swing.JPanel {
TopReactionMenu Top;
    /** Creates new form BuildReaction */
    public BuildLumpedMechanism(TopReactionMenu top) {
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        updateButton = new javax.swing.JButton();
        reactionListField = new javax.swing.JTextField();
        parameterList = new react.utilities.StandardListPanel();
        jPanel3 = new javax.swing.JPanel();
        useReactantsRadio = new javax.swing.JRadioButton();
        useProductsRadio = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        rootNameField = new javax.swing.JTextField();
        lumpReactionsButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setBorder(new javax.swing.border.TitledBorder("LumpReactions"));
        updateButton.setLabel("Update");
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateButtonMouseClicked(evt);
            }
        });

        jPanel1.add(updateButton);

        reactionListField.setText("ReactionList");
        jPanel1.add(reactionListField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel1, gridBagConstraints);

        parameterList.setLayout(new java.awt.FlowLayout());

        parameterList.setBorder(new javax.swing.border.TitledBorder("Reaction List"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(parameterList, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setBorder(new javax.swing.border.TitledBorder("Molecule Lumping Criteria"));
        useReactantsRadio.setSelected(true);
        useReactantsRadio.setText("Reactants");
        jPanel3.add(useReactantsRadio);

        useProductsRadio.setSelected(true);
        useProductsRadio.setText("Products");
        jPanel3.add(useProductsRadio);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Lump"));
        rootNameField.setToolTipText("The root name of the variables created");
        rootNameField.setText("Test-Combined");
        jPanel2.add(rootNameField);

        lumpReactionsButton.setToolTipText("Lump the reactions");
        lumpReactionsButton.setText("Lump Reactions");
        lumpReactionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lumpReactionsButtonMouseClicked(evt);
            }
        });

        jPanel2.add(lumpReactionsButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel2, gridBagConstraints);

    }
    // </editor-fold>//GEN-END:initComponents

  private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
    GetAttributeParameter getparam = new GetAttributeParameter(Top);
    boolean success = getparam.getParameterAsString(reactionListField.getText());
    if(success) {
        if(getparam.attributeType.equals("KeyWords")) {
            BaseDataKeyWords keys = new BaseDataKeyWords(getparam.outputString);
            parameterList.setData(keys.keyWordAsStringArray());
            parameterList.updateUI();
        } else {
            ErrorFrame err = new ErrorFrame("Expected 'KeyWord' type, but got '" + getparam.attributeType + "'");
            err.show();
        }
    } else {
            ErrorFrame err = new ErrorFrame("Error in reading parameter '"+ reactionListField.getText() + "'");
            err.show();
    }
  }//GEN-LAST:event_updateButtonMouseClicked

  private void lumpReactionsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lumpReactionsButtonMouseClicked
    SetUpClassAttrFile inpfile = new SetUpClassAttrFile(Top,"LumpedReactions",
                "Determine Lumped Species from Reaction Classes");
    SetUpClassAttrFile inpfile0 = new SetUpClassAttrFile(Top,"LumpedReactionsKeys",
                "Determine Lumped Species from Reaction Classes");
    // RootName
    inpfile.printString("RootName",rootNameField.getText());
    // PredicateObject
    inpfile.printObjectAsString("PredicateObject",
                                "MemberOfKeyWord: Parameter Parameter",
                                "MemberOfKeyWord");
    // ParameterList
    String[] par = paramsForKeyWords();
    inpfile0.printKeyWords("Parameters",par);
    // ParameterList
    inpfile0.printKeyWords("ParameterList",par);
    // EquivalentSets
    String[] equivsets = new String[2];
    equivsets[0] = "ReactionsAsEquivalentSet";
    equivsets[1] = "ReactionEquivalentSets";
    inpfile.printKeyWords("EquivalentSets",equivsets);
    // ReactionsAsEquivalentSet
    BaseDataKeyWords keys = new BaseDataKeyWords(parameterList.selectedItemsToString(true));
    StringBuffer equivset = new StringBuffer();
    equivset.append("Top\n");
    equivset.append("EquivalentSet:\n");
    String lst = keys.asString();

    equivset.append(lst);
    equivset.append("%% Arbitrary Vector Equivalence\nEquivalence: Values: 1  1.0   Parameter END\n");
    equivset.append("%% Equivalence Criteria\nCutOffCriteria: 0.1\n");
    equivset.append("%% Only a single set\nEND\n");
    inpfile.printObjectAsString("ReactionsAsEquivalentSet",
            equivset.toString(),"NumericSetOfEquivalentSets");

    inpfile0.read(false);
    String transS = "TransferAttributes " +
                   reactionListField.getText() + " " +
                   Top.SystemParameters.totalInstanceList.getValue();
    RunCommand runit0 = new RunCommand(Top,transS,false);

    RunAlgorithm runit1 = new RunAlgorithm(Top,"AddClassKeyWords",false);
    runit1.run();
    //runit1.showResults();
    
    inpfile.read(false);
    
    RunAlgorithm runit2 = new RunAlgorithm(Top,"PredicatesFromKeyWordsAlg",false);
    runit2.run();
    //runit2.showResults();
    RunAlgorithm runit3 = new RunAlgorithm(Top,"DescriptionMatrixAlg",false);
    runit3.run();
    //runit3.showResults();
    RunAlgorithm runit4 = new RunAlgorithm(Top,"SimpleGroupAlg",false);
    runit4.run();
    runit4.showResults();
    String transS1 = "TransferAttributes ReactionEquivalentSets EquivalentSets";
    RunCommand runit6 = new RunCommand(Top,transS1,false);
    
    RunAlgorithm runit7 = new RunAlgorithm(Top,"SimpleCreateLumpedReactions",false);
    runit7.run();
    runit7.showResults();
  }//GEN-LAST:event_lumpReactionsButtonMouseClicked
  private String[] paramsForKeyWords() {
          int numpar = 1;
    if(useProductsRadio.isSelected() & useReactantsRadio.isSelected())
        numpar = 2;
    if(!useProductsRadio.isSelected() & !useReactantsRadio.isSelected())
        numpar = 0;
    int count = 0;
    String[] par = new String[numpar];
    if(useProductsRadio.isSelected()) {
        par[count] = "Products ";
        count++;
    }
    if(useReactantsRadio.isSelected()) {
        par[count] = "Reactants ";
        count++;
    }
    return par;
  }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton lumpReactionsButton;
    private react.utilities.StandardListPanel parameterList;
    private javax.swing.JTextField reactionListField;
    private javax.swing.JTextField rootNameField;
    private javax.swing.JButton updateButton;
    private javax.swing.JRadioButton useProductsRadio;
    private javax.swing.JRadioButton useReactantsRadio;
    // End of variables declaration//GEN-END:variables

}
