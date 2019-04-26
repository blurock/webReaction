/*
 * DataBeanClass.java
 *
 * Created on April 11, 2006, 4:30 PM
 */

package blurock.coreobjects;

import java.beans.*;
import java.io.Serializable;
import blurock.DirectedTreeObjects.BaseDataTreeNode;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.core.RWManagerBase;
import java.io.IOException;

/**
 * Class Description:
 * @author reaction
 */
public class DataStringClass extends DataObjectClass implements Serializable {
    
    private PropertyChangeSupport propertySupport;
    
    /**
     * The Empty constructor (default values for Type, etc.)
     */
    public DataStringClass() {
        propertySupport = new PropertyChangeSupport(this);
        this.Type = "String";
        this.Identification = 1;
        this.Name = "String";
    }
    /**
     * Standard constructor for building a new data object of this type.
     * @param id The class identification number
     *
     * @param type The class name
     *
     * @param descr A one line description of the class
     */
    public DataStringClass(int id, String type, String descr) {
        super(id,type,descr);
    }
    
    /**
     * Add a change listener
     * @param listener The listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    /**
     * Add a removal listener
     * @param listener The listener to add
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
    /**
     * Create an object example of this class type.
     * @return An example of the object of this type.
     */
    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataString();
        obj.Type = this.Type;
        return obj;
    }
    
    /**
     * Create a cloned copy of this class type
     * @return The cloned class
     */
    public DataObjectClass Clone() {
        DataStringClass cls =   new DataStringClass(Identification,Name,Description);
        cls.CopyClone(this);
        return cls;
    }
    
    /**
     * Copy the contents of an class into this one
     * @param cls The class to copy from
     */
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
    }
    
    /**
     * Standard read operation.
     * @param io The io information for the read
     *
     * @throws java.io.IOException If the read is not successful, an exception is thrown (usually means the formatting is not correct).
     */
    public void Read(RWManagerBase io) throws IOException {
    }
    
    /**
     * Standard write operation
     * @param io The io information determining how object is to be written.
     * @throws java.io.IOException If the write produces an error, an exception is thrown.
     */
    public void Write(RWManagerBase io) throws IOException {
    }
    
}
