/*
 * PSRFrame.java
 *
 * Created on March 16, 2006, 12:26 PM
 */

package ignition.psr;

import java.awt.BorderLayout;

/**
 *
 * @author  reaction
 */
public class PSRFrame extends javax.swing.JFrame {
    
    /** Creates new form PSRFrame */
    public PSRFrame() {
        initComponents();
        setSize(new java.awt.Dimension(600, 400));
        ReactPSR psr = new ReactPSR();
        jPanel1.add(psr,BorderLayout.CENTER);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(300, 300));
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PSRFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    
}
