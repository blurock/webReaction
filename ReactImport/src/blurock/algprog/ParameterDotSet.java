/*
 * ParameterDotSet.java
 *
 * Created on March 14, 2001, 10:11 PM
 */

package blurock.algprog;
import java.util.Hashtable;
/**
 *
 * @author  reaction
 * @version 
 */
public class ParameterDotSet extends javax.swing.JPanel {
    
    Hashtable parameterSet = new Hashtable();
    
    /** Creates new form ParameterDotSet */
    public ParameterDotSet() {
    }
    public ParameterDotSet(String[] names) {
        initComponents ();
        if(names.length == 1) 
            setDotSet(names[0]);
        else if(names.length == 2)
            setDotSet(names[0],names[1]);
    }
    public void setDotSet(String name) {
        ParameterDot dot = new ParameterDot(name);
        position3.add(dot);
        parameterSet.put(name,dot);
        
    }
    public void setDotSet(String name1, String name2) {
        ParameterDot dot1 = new ParameterDot(name1);
        ParameterDot dot2 = new ParameterDot(name2);
        position2.add(dot1);
        parameterSet.put(name1,dot1);
        position4.add(dot2);
        parameterSet.put(name2,dot2);
        
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        position1 = new javax.swing.JPanel();
        parameterDot1 = new blurock.algprog.ParameterDot();
        position2 = new javax.swing.JPanel();
        parameterDot2 = new blurock.algprog.ParameterDot();
        position3 = new javax.swing.JPanel();
        parameterDot3 = new blurock.algprog.ParameterDot();
        position4 = new javax.swing.JPanel();
        parameterDot4 = new blurock.algprog.ParameterDot();
        position5 = new javax.swing.JPanel();
        parameterDot5 = new blurock.algprog.ParameterDot();
        setLayout(new javax.swing.BoxLayout(this, 0));
        
        jPanel1.setLayout(new java.awt.GridLayout(1, 5));
        jPanel1.setBackground(java.awt.Color.black);
        
        position1.setBackground(java.awt.Color.white);
          
          parameterDot1.setLayout(new java.awt.BorderLayout());
            position1.add(parameterDot1);
            
            jPanel1.add(position1);
          
          
        position2.setBackground(java.awt.Color.white);
          
          parameterDot2.setLayout(new java.awt.BorderLayout());
            position2.add(parameterDot2);
            
            jPanel1.add(position2);
          
          
        position3.setBackground(java.awt.Color.white);
          
          parameterDot3.setLayout(new java.awt.BorderLayout());
            position3.add(parameterDot3);
            
            jPanel1.add(position3);
          
          
        position4.setBackground(java.awt.Color.white);
          
          parameterDot4.setLayout(new java.awt.BorderLayout());
            position4.add(parameterDot4);
            
            jPanel1.add(position4);
          
          
        position5.setBackground(java.awt.Color.white);
          
          parameterDot5.setLayout(new java.awt.BorderLayout());
            position5.add(parameterDot5);
            
            jPanel1.add(position5);
          
          
        add(jPanel1);
        
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel position1;
    private blurock.algprog.ParameterDot parameterDot1;
    private javax.swing.JPanel position2;
    private blurock.algprog.ParameterDot parameterDot2;
    private javax.swing.JPanel position3;
    private blurock.algprog.ParameterDot parameterDot3;
    private javax.swing.JPanel position4;
    private blurock.algprog.ParameterDot parameterDot4;
    private javax.swing.JPanel position5;
    private blurock.algprog.ParameterDot parameterDot5;
    // End of variables declaration//GEN-END:variables

}
