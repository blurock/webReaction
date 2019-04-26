/*
 * SimpleOperationPanel.java
 *
 * Created on March 4, 2001, 8:18 AM
 */

package blurock.opandalgs.algorithm;

import blurock.coreobjects.ClassNamePairs;
import blurock.coreobjects.BaseDataKeyWords;
/**
 *
 * @author  reaction
 * @version 
 */
public class SimpleAlgorithmPanel extends javax.swing.JPanel {
    BaseDataAlgorithmSummary Summary = null;
    
    
    /** Creates new form SimpleOperationPanel */
    public SimpleAlgorithmPanel(String algname, String subclass) {
        initComponents ();
        algorithmName.setText(algname);
        subClassName.setText(subclass);
    }
    public SimpleAlgorithmPanel(BaseDataAlgorithmSummary obj) {
        subClassName.setText("Object");
        setAlgoprithmName();
        //setAlgorithm();
        //setRunKeys();
        //setParametersAndResults();
    }
    
    public void setAlgoprithmName() {
        algorithmName.setText(Summary.Name);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        algorithmNamePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        algorithmName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        subClassName = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        algorithmNamePanel.setLayout(new java.awt.GridLayout(1, 2));

        jLabel1.setText("AlgorithmName");
        algorithmNamePanel.add(jLabel1);

        algorithmName.setText("name");
        algorithmNamePanel.add(algorithmName);

        jLabel2.setText("Super Class");
        algorithmNamePanel.add(jLabel2);

        subClassName.setText("AlgorithmRun");
        algorithmNamePanel.add(subClassName);

        add(algorithmNamePanel, java.awt.BorderLayout.NORTH);

    }
    // </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField algorithmName;
    private javax.swing.JPanel algorithmNamePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField subClassName;
    // End of variables declaration//GEN-END:variables

}