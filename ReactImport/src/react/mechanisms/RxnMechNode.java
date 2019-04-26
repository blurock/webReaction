/*
 * RxnMechNode.java
 *
 * Created on January 14, 2002, 2:40 PM
 */

package react.mechanisms;
import react.common.TopReactionMenu;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class RxnMechNode extends javax.swing.tree.DefaultMutableTreeNode {

    public String Name;
    
    TopReactionMenu Top;
    
    String Title;
    
    public RxnMechNode(TopReactionMenu top, String name, String title) {
        super(name);
        Top = top;
        Name = name;
        Title = title;
    }

    public void showNode() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(Name);
        panel.add(label);
        setUpFrame(panel);
    }
    
    void setUpFrame(JPanel panel) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setName(Name);
        frame.pack();
    }    
}

