/*
 * AlgorithmBlock.java
 *
 * Created on March 14, 2001, 10:54 PM
 */

package blurock.algprog;
import blurock.coreobjects.*;
import java.util.Vector;
/**
 *
 * @author  reaction
 * @version 
 */
public class AlgorithmBlock extends javax.swing.JPanel {
    ParameterDotSet inDots;
    ParameterDotSet outDots;
    String[] ins = {"i1"};
    String[] outs = {"o1"};
    
    /** Creates new form AlgorithmBlock */
    public AlgorithmBlock() {
        initComponents ();
        algName.setText("Algorithm");
        setDots();
    }
    public AlgorithmBlock(String name, ClassNamePairs inp, ClassNamePairs outp) {
        initComponents ();
        ins = new String[inp.Names.size()];
        outs = new String[outp.Names.size()];
        algName.setText(name);
        for(int incnt = 0;incnt<ins.length;incnt++) 
            ins[incnt] = (String) inp.Names.elementAt(incnt);
        for(int outcnt = 0;outcnt<outs.length;outcnt++) 
            outs[outcnt] = (String) outp.Names.elementAt(outcnt);
        setDots();
    }
    private void setDots() {
        
        inDots = new ParameterDotSet(ins);
        outDots = new ParameterDotSet(outs);
        inputParameters.add(inDots);
        outputParameters.add(outDots);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        inputParameters = new javax.swing.JPanel();
        parameterDotSet1 = new blurock.algprog.ParameterDotSet();
        algorithmInfo = new javax.swing.JPanel();
        algName = new javax.swing.JTextField();
        outputParameters = new javax.swing.JPanel();
        parameterDotSet2 = new blurock.algprog.ParameterDotSet();
        setLayout(new java.awt.GridLayout(3, 1));
        setBackground(java.awt.Color.white);
        
        inputParameters.setLayout(new javax.swing.BoxLayout(inputParameters, 0));
        inputParameters.setBackground(java.awt.Color.white);
        
        inputParameters.add(parameterDotSet1);
          
          
        add(inputParameters);
        
        
        algorithmInfo.setLayout(new javax.swing.BoxLayout(algorithmInfo, 0));
        algorithmInfo.setBackground(java.awt.Color.white);
        
        algName.setFont(new java.awt.Font ("Utopia", 1, 12));
          algName.setText("jTextField1");
          algName.setMargin(new java.awt.Insets(1, 1, 1, 1));
          algorithmInfo.add(algName);
          
          
        add(algorithmInfo);
        
        
        outputParameters.setLayout(new javax.swing.BoxLayout(outputParameters, 0));
        outputParameters.setBackground(java.awt.Color.white);
        
        outputParameters.add(parameterDotSet2);
          
          
        add(outputParameters);
        
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel inputParameters;
    private blurock.algprog.ParameterDotSet parameterDotSet1;
    private javax.swing.JPanel algorithmInfo;
    private javax.swing.JTextField algName;
    private javax.swing.JPanel outputParameters;
    private blurock.algprog.ParameterDotSet parameterDotSet2;
    // End of variables declaration//GEN-END:variables

}