/*
 * DisplayFlow.java
 *
 * Created on October 24, 2002, 5:51 PM
 */

package react.mechanisms.flow;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;
import graph.*;
/**
 *
 * @author  reaction
 */
public class DisplayFlow extends javax.swing.JPanel {
    /** Creates new form DisplayFlow */
    GraphOptions options = null;
    public DisplayFlow() {
            initComponents();
     }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        setupButton = new javax.swing.JButton();
        graph = new react.mechanisms.flow.FlowGraph();

        setLayout(new java.awt.BorderLayout());

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        setupButton.setText("Set up Flow");
        setupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setupButtonMouseClicked(evt);
            }
        });

        jPanel1.add(setupButton);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        graph.setName("canvas1");
        add(graph, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

    private void setupButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setupButtonMouseClicked
        /*options = new GraphOptions(graph);
        JFrame fr = new JFrame();
        fr.setTitle("Flow Analysis Setup and Graph Options");
        fr.getContentPane().add(options);
        fr.pack();
        fr.show();
         **/
    }//GEN-LAST:event_setupButtonMouseClicked
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public react.mechanisms.flow.FlowGraph graph;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton setupButton;
    // End of variables declaration//GEN-END:variables
    
}
