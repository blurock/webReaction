/*
 * CobwebClusterMainPanel.java
 *
 * Created on November 10, 2003, 12:02 PM
 */

package blurock.CobwebCluster;
import utilities.*;
import blurock.core.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.BorderLayout;
/**
 *
 * @author  reaction
 */
public class CobwebClusterMainPanel extends javax.swing.JPanel {
    BaseDataCobwebCluster cobwebCluster;
    DataCobwebClusterClass cobwebClusterClass;
    FileFrame fileFrame = new FileFrame("Cluster Information",".","out");
    RWManagerBase io;
    DBaseDataCobwebCluster dset;
     /** Creates new form CobwebClusterMainPanel */
    public CobwebClusterMainPanel() {
        initComponents();
    }
    public CobwebClusterMainPanel(BaseDataCobwebCluster cluster, DataCobwebClusterClass cobwebclass, 
                              ObjectDisplayManager man, RWManagerBase rw) {
        io = rw;
        cobwebCluster = cluster;
        cobwebClusterClass = cobwebclass;
        dset = new DBaseDataCobwebCluster(man,cluster,cobwebclass);
        ObjectAsTreeNode top = new ObjectAsTreeNode(cobwebCluster.Name);
        dset.objectAsSubTree(top);
        TopTreePanel tree = new TopTreePanel(top);
        
        initComponents();
        TreePanel.add(tree,BorderLayout.CENTER);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        writeButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        TreePanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        printLevelSlider = new javax.swing.JSlider();
        jPanel3 = new javax.swing.JPanel();
        numElementSlider = new javax.swing.JSlider();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        writeButton.setText("Write");
        writeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                writeButtonMouseClicked(evt);
            }
        });

        jPanel1.add(writeButton);

        jButton2.setText("Redraw");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jPanel1.add(jButton2);

        add(jPanel1, java.awt.BorderLayout.SOUTH);

        TreePanel.setLayout(new java.awt.BorderLayout());

        TreePanel.setToolTipText("");
        TreePanel.setMinimumSize(new java.awt.Dimension(200, 200));
        TreePanel.setPreferredSize(new java.awt.Dimension(300, 200));
        add(TreePanel, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel21.setLayout(new java.awt.BorderLayout());

        jPanel21.setBorder(new javax.swing.border.TitledBorder("Cluster Level"));
        printLevelSlider.setMajorTickSpacing(1);
        printLevelSlider.setMaximum(10);
        printLevelSlider.setPaintLabels(true);
        printLevelSlider.setPaintTicks(true);
        printLevelSlider.setSnapToTicks(true);
        printLevelSlider.setToolTipText("The maximum cluster level to printout (top node is one\n)");
        printLevelSlider.setValue(3);
        jPanel21.add(printLevelSlider, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel21, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(new javax.swing.border.TitledBorder("Minimum Number of Elements"));
        numElementSlider.setMajorTickSpacing(5);
        numElementSlider.setMaximum(20);
        numElementSlider.setMinimum(1);
        numElementSlider.setMinorTickSpacing(1);
        numElementSlider.setPaintLabels(true);
        numElementSlider.setPaintTicks(true);
        numElementSlider.setSnapToTicks(true);
        numElementSlider.setToolTipText("The minimum number of elements in the cluster");
        numElementSlider.setValue(5);
        numElementSlider.setMaximumSize(new java.awt.Dimension(32767, 50));
        numElementSlider.setMinimumSize(new java.awt.Dimension(200, 41));
        numElementSlider.setPreferredSize(new java.awt.Dimension(200, 45));
        jPanel3.add(numElementSlider, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.SOUTH);

        add(jPanel2, java.awt.BorderLayout.NORTH);

    }//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
       cobwebCluster.maxLevel = printLevelSlider.getValue();
       cobwebCluster.minimumElementsInNode = numElementSlider.getValue();
        ObjectAsTreeNode top = new ObjectAsTreeNode(cobwebCluster.Name);
        dset.objectAsSubTree(top);
        
        TopTreePanel tree = new TopTreePanel(top);
        TreePanel.removeAll();
        TreePanel.add(tree,BorderLayout.CENTER);
        TreePanel.updateUI();

    }//GEN-LAST:event_jButton2MouseClicked

    private void writeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_writeButtonMouseClicked
       cobwebCluster.maxLevel = printLevelSlider.getValue();
       cobwebCluster.minimumElementsInNode = numElementSlider.getValue();
       
       fileFrame.getFile();
       File f = fileFrame.chosenFile;
       try {
            io.openOutputFile(f.getAbsolutePath());
       } catch(FileNotFoundException ex) {
            ErrorFrame fr = new ErrorFrame("Problem with writing File: \n" + f.toString());
            fr.show();
       }
       try {
            cobwebCluster.Write(io);
            io.closeOutputFile();
       } catch(IOException ex) {
            ErrorFrame fr = new ErrorFrame("Problem with writing File: \n" + f.toString());
            fr.show();
       }
    }//GEN-LAST:event_writeButtonMouseClicked
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel TreePanel;
    private javax.swing.JButton writeButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider printLevelSlider;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSlider numElementSlider;
    // End of variables declaration//GEN-END:variables
    
}
