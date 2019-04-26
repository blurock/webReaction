/*
 * BaseDataBean.java
 *
 * Created on April 11, 2006, 10:32 AM
 */

package blurock.iterator;
import blurock.core.RWManagerBase;
import blurock.core.RWManagerBase;
import blurock.coreobjects.ClassNamePairs;
import java.io.IOException;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.coreobjects.BaseDataSetOfObjects;
import blurock.coreobjects.DataSetOfObjectsClass;
import blurock.coreobjects.BaseDataKeyWords;
import blurock.coreobjects.BaseDataReal;
import blurock.coreobjects.ClassNamePairs;

import java.beans.*;
import java.io.Serializable;

/**
 * Object Class:
 * @author reaction
 */
public class BaseDataObjectIterator extends BaseDataSetOfObjects implements Serializable {
    
    private PropertyChangeSupport propertySupport;
    public BaseDataKeyWords ObjectNames;
    /**
     * The standard empty constructor
     */
    public BaseDataObjectIterator() {
        propertySupport = new PropertyChangeSupport(this);
        Type = "ObjectIterator";
    }
     public BaseDataObjectIterator(String name, int id) {
         super(name,id);
        propertySupport = new PropertyChangeSupport(this);
        Type = "ObjectIterator";
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
        BaseDataObjectIterator obj = new BaseDataObjectIterator(Name,Identification);
        obj.CopyClone(this);
        return obj;
    }
    
    /**
     * Copy the properties of the object into the current object
     * @param obj The object to copy the properties from
     */
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
        BaseDataObjectIterator iter = (BaseDataObjectIterator) obj;
        ObjectNames = iter.ObjectNames;
    }
    private void formObjectNames(DataSetOfObjectsClass classes) {
        BaseDataKeyWords keys = new BaseDataKeyWords();
        Object[] objs = setAsArray();
        keys.keyWords = new String[objs.length];
        for(int i=0;i<objs.length;i++) {
            BaseDataObject obj = (BaseDataObject) objs[i];
            keys.keyWords[i] = obj.Name;
        }
    }
    /**
     * Get the class for the displaying of the object class
     * @param man The display object manager
     * @param cls The Class of this object
     * @return The display class
     */
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataObject(man,this,cls);
    }
    /**
     * Standard read (ASCII) of the class
     * @param io The io information on how the object should be read
     * @throws java.io.IOException If the reading goes wrong, then an exception is thrown, especially
     * if the wrong property is not found during the read.
     */
    public void Read(RWManagerBase io) throws IOException {
        ReadAsTriplet(io);
        formObjectNames(io.dataClasses);
   }
    
    /**
     * The standard writing of the object (complementary to Read)
     * @param io The io information for the writing of the object
     * @throws java.io.IOException If the writing goes wrong, then an exception is thrown.
     */
    public void Write(RWManagerBase io) throws IOException {
        WriteAsTriplet(io);
    }
    public void fillFromDoubleVector(String[] parameters, double[] values) {
        for(int i=0;i<values.length;i++) {
            BaseDataReal r = new BaseDataReal();
            r.Name = parameters[i];
            r.realValue = values[i];
            this.AddObject(r);
       }
    }
    public DataSetOfObjectsClass fillFromDoubleMatrix(int id,String classname,
                                String setNames[], String[] elementNames, 
                                double[][] mat) {
        DataSetOfObjectsClass cls = new DataSetOfObjectsClass(id,classname,"IteratorObject as set of Reals");
        ClassNamePairs pairs = new ClassNamePairs();
        for(int j=0;j<elementNames.length;j++) {
            pairs.addPair(elementNames[j], "Real");
        }
        cls.classNamePairs = pairs;
        System.out.println("fillFromDoubleMatrix: " + cls.Name);
        for(int i=0;i<setNames.length;i++) {
            BaseDataSetOfObjects obj = new BaseDataSetOfObjects(setNames[i], i);
            obj.ioClassNamePairs = false;
            obj.Type = classname;
            for(int j=0;j<elementNames.length;j++) {
                BaseDataReal r = new BaseDataReal();
                r.realValue = mat[i][j];
                r.Name = elementNames[j];
                obj.AddObject(r);
            }
            AddObject(obj);
        }
        return cls;
    }
}