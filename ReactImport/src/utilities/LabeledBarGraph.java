/*
 * LabeledBarGraph.java
 *
 * Created on October 21, 2002, 11:25 AM
 */

package utilities;

/**
 *
 * @author  reaction
 */
public class LabeledBarGraph extends javax.swing.JPanel {
    
    /** Creates new form LabeledBarGraph */
    public LabeledBarGraph() {
        initComponents();
        set("Sample",60);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        name = new javax.swing.JLabel();
        bar = new javax.swing.JProgressBar();

        setLayout(new java.awt.GridBagLayout());

        name.setText("name");
        name.setMaximumSize(new java.awt.Dimension(100, 16));
        name.setMinimumSize(new java.awt.Dimension(100, 16));
        name.setPreferredSize(new java.awt.Dimension(100, 16));
        add(name, new java.awt.GridBagConstraints());

        bar.setBackground(new java.awt.Color(255, 255, 255));
        bar.setForeground(new java.awt.Color(255, 0, 51));
        bar.setValue(60);
        bar.setMinimumSize(new java.awt.Dimension(300, 14));
        bar.setPreferredSize(new java.awt.Dimension(300, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(bar, gridBagConstraints);

    }//GEN-END:initComponents

    public void set(String label, double percentage) {
        name.setText(label);
        if(percentage < 0.0) 
            percentage = 0.0;
        if(percentage > 100.0) 
            percentage = 100.0;

        Double s = new Double(percentage);
        int value  = Math.round(s.floatValue()); 
        bar.setValue(value);
    }    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel name;
    private javax.swing.JProgressBar bar;
    // End of variables declaration//GEN-END:variables
    
}
