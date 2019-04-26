/*
 * StandardName.java
 *
 * Created on January 20, 2001, 11:19 AM
 */

package react.common;

/**
 *
 * @author  reaction
 * @version 
 */
public class StandardName extends javax.swing.JPanel {

    /** Creates new form StandardName */
    public StandardName() {
        initComponents ();
    }

    public StandardName(java.lang.String name,java.lang.String defaultValue) {
        initComponents();
        setDefaultValue(defaultValue);
        setValueName(name);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        setLayout(new java.awt.GridLayout(1, 2));
        setPreferredSize(new java.awt.Dimension(120, 20));
        
        
        add(jLabel1);
        
        
        jTextField1.setPreferredSize(new java.awt.Dimension(69, 20));
        jTextField1.setText("jTextField1");
        
        add(jTextField1);
        
    }//GEN-END:initComponents

    public void setDefaultValue(java.lang.String value) {
        jTextField1.setText(value);
    }    

    public void setValueName(java.lang.String name) {
        jLabel1.setText(name);
    }
    
    public String getValue() {
        return jTextField1.getText();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}