/*
 * SystemCommands.java
 *
 * Created on February 15, 2001, 11:06 AM
 */

package blurock.common;
import react.common.StandardName;

/**
 *
 * @author  reaction
 * @version 
 */
public class SystemCommands extends javax.swing.JPanel {

    /** Creates new form SystemCommands */
    public SystemCommands() {
        initComponents ();
        InstanceList.setDefaultValue("WindowObject Instance");
        InstanceList.setValueName("List of Instance Names");
        AttributeList.setDefaultValue("WindowObject Attribute");
        AttributeList.setValueName("List of Attribute Names");
        InstanceAttributeList.setDefaultValue("WindowObject AttributeNames");
        InstanceAttributeList.setValueName("List of Attribute Names");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        InstanceList = new react.common.StandardName();
        AttributeList = new react.common.StandardName();
        InstanceAttributeList = new react.common.StandardName();
        calculateExpression = new react.common.StandardName();
        standardName2 = new react.common.StandardName();
        setLayout(new java.awt.GridLayout(5, 1));
        
        InstanceList.setLayout(new java.awt.GridLayout(2, 3));
        InstanceList.setName("Instance List");
        InstanceList.setToolTipText("The Command to get the instance list");
        
        add(InstanceList);
        
        
        AttributeList.setLayout(new java.awt.GridLayout(2, 3));
        AttributeList.setName("AttributeList");
        AttributeList.setToolTipText("Command to get attribute list");
        
        add(AttributeList);
        
        
        InstanceAttributeList.setLayout(new java.awt.GridLayout(2, 3));
        InstanceAttributeList.setName("InstanceAttributeList");
        InstanceAttributeList.setToolTipText("Command to retrieve list of instance attributes (depends on instance selection)");
        
        add(InstanceAttributeList);
        
        
        calculateExpression.setLayout(new java.awt.GridLayout(2, 3));
        
        add(calculateExpression);
        
        
        standardName2.setLayout(new java.awt.GridLayout(2, 3));
        
        add(standardName2);
        
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public react.common.StandardName InstanceList;
    public react.common.StandardName AttributeList;
    private react.common.StandardName InstanceAttributeList;
    private react.common.StandardName calculateExpression;
    private react.common.StandardName standardName2;
    // End of variables declaration//GEN-END:variables

}