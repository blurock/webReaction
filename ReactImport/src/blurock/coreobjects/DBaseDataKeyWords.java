/*
 * DbaseDataBean.java
 *
 * Created on April 11, 2006, 8:03 PM
 */

package blurock.coreobjects;

import java.beans.*;
import java.io.Serializable;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Display Class:
 * @author reaction
 */
public class DBaseDataKeyWords extends DBaseDataObject implements Serializable {
    
    private PropertyChangeSupport propertySupport;
    
    /**
     * The basic constructor of the display class
     * @param man The display manager class
     *
     * @param obj The object being displayed
     *
     * @param cls The class of the object
     */
    public DBaseDataKeyWords(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
        propertySupport = new PropertyChangeSupport(this);
    }
    
    
    /**
     * Add a property change listener
     * @param listener The listener class
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    /**
     * Add a property change listener
     * @param listener The listener class
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    public JPanel objectAsPanel() {
        BaseDataKeyWords o = (BaseDataKeyWords) Object;
        KeyWordPanel keyP = new KeyWordPanel(o);
        return keyP;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        BaseDataKeyWords o = (BaseDataKeyWords) Object;
        ObjectAsTreeNode node = super.objectAsSubTree(topnode);
        for(int i=0;i<o.keyWords.length;i++) {
            DefaultMutableTreeNode n = new DefaultMutableTreeNode(o.keyWords[i]);
            node.add(n);
        }
        return node;
    }
    
}
