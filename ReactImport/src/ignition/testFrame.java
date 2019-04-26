/*
 * testFrame.java
 *
 * Created on January 19, 2001, 11:36 PM
 */

package ignition;
import javax.swing.*;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import ignition.times.IgnitionTimes;

/**
 *
 * @author  reaction
 * @version 
 */
public class testFrame extends javax.swing.JFrame {
    IgnitionTimes times = new IgnitionTimes();
    /** Creates new form testFrame */
    public testFrame() {
        initComponents ();
        jPanel1.add(times, BorderLayout.CENTER);
       pack ();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit (0);
    }//GEN-LAST:event_exitForm

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        new testFrame ().show ();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
