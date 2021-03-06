/*
 * BaseFrame.java
 *
 * Created on January 31, 2001, 1:41 PM
 */

package utilities;
import react.common.*;
import javax.swing.JPanel;
import java.awt.Dimension;


/**
 *
 * @author  reaction
 * @version 
 */
public class BaseFrame extends javax.swing.JFrame {

    public String frameName;
    
    public String textExplanation;
    
    public String oneLineExplanation;
    
    public TopReactionMenu Top;
    
    public JPanel panelObject;
    
    /** Creates new form BaseFrame */
    public BaseFrame() {
        initComponents ();
        pack ();
    }
    public BaseFrame(JPanel panel,
                        String name,
                        String tabtitle,
                        String text) {
        panelObject = panel;
        setup(panel,name,tabtitle,text);
        pack();
    }
    public BaseFrame(TopReactionMenu top, JPanel panel,
                        String name,
                        String tabtitle,
                        String text) {
        panelObject = panel;
        Top = top;
        setup(panel,name,tabtitle,text);
        pack();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        }
        );
    }//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit (0);
    }//GEN-LAST:event_exitForm

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        BaseFrame f = new BaseFrame ();
        f.show ();
    }

    public void setup(JPanel panel,
                        String name,
                        String tabtitle,
                        String text) {
        frameName = name;
        oneLineExplanation = tabtitle;
        textExplanation = text;
        getContentPane().add(panel);
        setVisible(false);
        pack();
    }    

    public void put(BaseFrame frame) {
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
