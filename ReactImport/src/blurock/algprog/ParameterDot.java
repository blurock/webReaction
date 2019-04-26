/*
 * ParameterDot.java
 *
 * Created on March 14, 2001, 9:52 PM
 */

package blurock.algprog;

/**
 *
 * @author  reaction
 * @version 
 */
public class ParameterDot extends javax.swing.JPanel {

    public String parameterName;
    
    public boolean enabledParameter;
    
    /** Creates new form ParameterDot */
    public ParameterDot() {
        enabledParameter = false;
        initComponents ();
    }

    public ParameterDot(java.lang.String name) {
        enabledParameter = false;
        parameterName = name;
        initComponents ();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jButton1 = new javax.swing.JButton();
        setLayout(new javax.swing.BoxLayout(this, 0));
        
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/images/BlueDot.jpg")));
        jButton1.setPreferredSize(new java.awt.Dimension(15, 15));
        jButton1.setToolTipText(parameterName);
        jButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/data/images/RedDot.jpg")));
        jButton1.setSelected(enabledParameter);
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.setMinimumSize(new java.awt.Dimension(5, 5));
        
        add(jButton1);
        
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables

}