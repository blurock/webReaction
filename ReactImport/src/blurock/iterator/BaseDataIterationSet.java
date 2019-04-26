/*
 * BaseDataBean.java
 *
 * Created on April 11, 2006, 10:32 AM
 */

package blurock.iterator;
import blurock.core.RWManagerBase;
import blurock.core.RWManagerBase;
import java.io.IOException;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.coreobjects.BaseDataSetOfObjects;
import blurock.core.ObjectNotFoundException;
import java.beans.*;
import java.io.Serializable;

/**
 * Object Class:
 * @author reaction
 */
public class BaseDataIterationSet extends BaseDataSetOfObjects implements Serializable {
    
    private PropertyChangeSupport propertySupport;
    
    /**
     * The standard empty constructor
     */
    public BaseDataIterationSet() {
        propertySupport = new PropertyChangeSupport(this);
        Type = "IterationSet";
    }
    public BaseDataIterationSet(String name, int id) {
        super(name,id);
        propertySupport = new PropertyChangeSupport(this);
        Type = "IterationSet";
    }
    
    
    /**
     * Add a change listener to the class
     * @param listener The listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    /**
     * Add a listener for the removal of a property
     * @param listener the listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    /**
     * Clone a copy of the class and its properties
     * @return The Cloned object
     */
    public BaseDataObject Clone() {
        BaseDataIterationSet obj = new BaseDataIterationSet(Name,Identification);
        obj.CopyClone(this);
        return obj;
    }
    
    /**
     * Copy the properties of the object into the current object
     * @param obj The object to copy the properties from
     */
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
    }
    
    /**
     * Get the class for the displaying of the object class
     * @param man The display object manager
     * @param cls The Class of this object
     * @return The display class
     */
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DbaseDataIterationSet(man,this,cls);
    }
    /**
     * Standard read (ASCII) of the class
     * @param io The io information on how the object should be read
     * @throws java.io.IOException If the reading goes wrong, then an exception is thrown, especially
     * if the wrong property is not found during the read.
     */
    public void Read(RWManagerBase io) throws IOException {
        io.checkToken("IterationSet:");
        try {
        DataIterationSetClass setclass = (DataIterationSetClass) io.dataClasses.findClass(this.Type);
        
        String next = io.readElement();
        while(next.compareTo("Iterator:") == 0) {
            next = io.readElement();
            DataObjectClass objcls = io.dataClasses.findClass(next);
            BaseDataObject obj = objcls.BaseDataObjectExample();
            obj.Name = io.readElement();
            obj.Read(io);
            AddObject(obj);
            next = io.readElement();
        }
        } catch(ObjectNotFoundException ex) {
            throw new IOException(ex.toString());
        }
    }
    
    /**
     * The standard writing of the object (complementary to Read)
     * @param io The io information for the writing of the object
     * @throws java.io.IOException If the writing goes wrong, then an exception is thrown.
     */
    public void Write(RWManagerBase io) throws IOException {
        Object[] iterators = super.setAsArray();
        io.printLine("IterationSet:");
        for(int i=0;i < iterators.length;i++) {
            BaseDataObjectIterator iter = (BaseDataObjectIterator) iterators[i];
            io.printLine("Iterator:  " + iter.Type + "  " + iter.Name);
            iter.WriteAsTriplet(io);
        }
        io.printLine("END");
    }
}