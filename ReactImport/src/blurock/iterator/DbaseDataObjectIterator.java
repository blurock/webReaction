/*
 * DbaseDataBean.java
 *
 * Created on April 11, 2006, 8:03 PM
 */

package blurock.iterator;

import java.beans.*;
import java.io.Serializable;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;

/**
 * Display Class:
 * @author reaction
 */
public class DbaseDataObjectIterator extends DBaseDataObject implements Serializable {
    
    private PropertyChangeSupport propertySupport;
    
    /**
     * The basic constructor of the display class
     * @param man The display manager class
     *
     * @param obj The object being displayed
     *
     * @param cls The class of the object
     */
    public DbaseDataObjectIterator(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
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
        BaseDataObjectIterator o = (BaseDataObjectIterator) Object;
        JPanel panel = super.objectAsPanel();
        return panel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        BaseDataObjectIterator o = (BaseDataObjectIterator) Object;
        ObjectAsTreeNode node = super.objectAsSubTree(topnode);
        return node;
    }
}
