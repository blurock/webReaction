/*
 * CombineSubMechanismMenu.java
 *
 * Created on February 2, 2001, 9:06 PM
 */

package react.mechanisms;
import java.io.*;
import utilities.*;
import react.common.*;
import react.menu.*;
import java.awt.Dimension;


/**
 *
 * @author  reaction
 * @version 
 */
public class CombineSubMechanismMenu extends javax.swing.JPanel {

    /** Creates new form CombineSubMechanismMenu */
    public CombineSubMechanismMenu() {
        initComponents ();
    }
    public CombineSubMechanismMenu(TopReactionMenu top, String title,
                                   int sz,Dimension psize) {
        Top = top;
        Title = title;
        maxSize = sz;
        //setPreferredSize(psize);
        initComponents ();
    }
    public void setup(TopReactionMenu top, Dimension psize) {
        Top = top;
        Title = new String("Combine Mechanisms");
        maxSize = 20;
        setPreferredSize(psize);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel3 = new javax.swing.JPanel();
        runCombine = new javax.swing.JButton();
        combineNameL = new javax.swing.JLabel();
        combineName = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        mechanismList = new ManageListOfMechanisms(Top,Title,20);
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel3.setLayout(new java.awt.GridLayout(1, 3));
        
        runCombine.setToolTipText("Call to Combine the set of Mechanisms");
          runCombine.setText("Combine");
          runCombine.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  runCombine(evt);
              }
          }
          );
          jPanel3.add(runCombine);
          
          
        combineNameL.setText("Combined Name");
          jPanel3.add(combineNameL);
          
          
        combineName.setText("Combined");
          jPanel3.add(combineName);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridwidth = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel3, gridBagConstraints1);
        
        
        
        jPanel1.add(mechanismList);
          
          
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jPanel1, gridBagConstraints1);
        
    }//GEN-END:initComponents

  private void runCombine(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runCombine
// Add your handling code here:
      String name = combineName.getText();
      String[] mechnames = mechanismList.mechanismList.getElements();
      String filename = name + ".lst";
      File fstr = new File(filename);
      String fname = fstr.getAbsolutePath();
        try {
            PrintWriter ostr = 
                new PrintWriter(new FileOutputStream(fstr));
          
            for(int i=0;i<mechnames.length;i++) {
                ostr.println(mechnames[i]);
            }
            ostr.close();
            String command = Top.Scripts.runCombineMechanism.getValue() + 
                    " " + name + 
                    " " + name;
            System.out.println("Command: " + command);
            Top.tLink.start(command);
            Top.tLink.execute(" ");
            Top.tLink.stop();
        } catch(IOException ios) {
            ErrorFrame fr = new ErrorFrame("Error in Combined File Writing");
            fr.show();
        }
  }//GEN-LAST:event_runCombine


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel jPanel3;
  private javax.swing.JButton runCombine;
  private javax.swing.JLabel combineNameL;
  private javax.swing.JTextField combineName;
  private javax.swing.JPanel jPanel1;
  private react.mechanisms.ManageListOfMechanisms mechanismList;
  // End of variables declaration//GEN-END:variables

  public TopReactionMenu Top;  

  public String Title;
  
  public int maxSize;
  
}
