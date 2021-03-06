/*
 * PredicatePanel.java
 *
 * Created on September 23, 2004, 2:46 PM
 */

package blurock.logic.predicates;
import javax.swing.JPanel;

/**
 *
 * @author  reaction
 */
public class PredicatePanel extends javax.swing.JPanel {
    BaseDataPredicate  predicate;
    /** Creates new form PredicatePanel */
    public PredicatePanel(BaseDataPredicate  pred, JPanel base, JPanel op) {
        predicate = pred;
        initComponents();
        baseInfoPanel.add(base,java.awt.BorderLayout.CENTER);
        functionPanel.add(op,java.awt.BorderLayout.CENTER);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        predicateInfoPanel = new javax.swing.JPanel();
        baseInfoPanel = new javax.swing.JPanel();
        parameterPanel = new javax.swing.JPanel();
        parameterLabel = new javax.swing.JLabel();
        parameterNameLabel = new javax.swing.JLabel();
        functionPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        predicateInfoPanel.setLayout(new java.awt.BorderLayout());

        baseInfoPanel.setLayout(new java.awt.BorderLayout());

        baseInfoPanel.setBorder(new javax.swing.border.TitledBorder("Predicate Type"));
        predicateInfoPanel.add(baseInfoPanel, java.awt.BorderLayout.CENTER);

        parameterPanel.setLayout(new java.awt.GridLayout(1, 2));

        parameterPanel.setBorder(new javax.swing.border.TitledBorder("Predicvate Parameters"));
        parameterLabel.setText("Parameter Name");
        parameterPanel.add(parameterLabel);

        parameterNameLabel.setText(predicate.Parameter);
        parameterPanel.add(parameterNameLabel);

        predicateInfoPanel.add(parameterPanel, java.awt.BorderLayout.SOUTH);

        add(predicateInfoPanel, java.awt.BorderLayout.NORTH);

        functionPanel.setLayout(new java.awt.BorderLayout());

        functionPanel.setBorder(new javax.swing.border.TitledBorder("Predicate Operation"));
        add(functionPanel, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel functionPanel;
    private javax.swing.JLabel parameterLabel;
    private javax.swing.JLabel parameterNameLabel;
    private javax.swing.JPanel parameterPanel;
    private javax.swing.JPanel predicateInfoPanel;
    public javax.swing.JPanel baseInfoPanel;
    // End of variables declaration//GEN-END:variables
    
}
