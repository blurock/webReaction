/*
 * BaseDataBean.java
 *
 * Created on April 11, 2006, 10:32 AM
 */

package blurock.opandalgs.algorithm;
import blurock.core.RWManagerBase;
import blurock.core.RWManagerBase;
import java.io.IOException;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.coreobjects.ClassNamePairs;
import blurock.coreobjects.BaseDataKeyWords;

import java.beans.*;
import java.io.Serializable;

/**
 * Object Class:
 * @author reaction
 */
public class BaseDataAlgorithmSummary extends BaseDataObject implements Serializable {
    public BaseDataKeyWords AlgorithmCategory = null;
    public BaseDataKeyWords AlgorithmKeys = null;
    public BaseDataKeyWords Keys = null;
    public ClassNamePairs Parameters = null;
    public BaseDataKeyWords Results = null;

    private PropertyChangeSupport propertySupport;
    
    /**
     * The standard empty constructor
     */
    public BaseDataAlgorithmSummary() {
        propertySupport = new PropertyChangeSupport(this);
        Type = "AlgorithmSummary";
    }
     public BaseDataAlgorithmSummary(String algname,
            String[] category,
            String[] algorithms,
            String[] keys,
            ClassNamePairs pairs,
            String[] results) {
        propertySupport = new PropertyChangeSupport(this);
        Type = "AlgorithmSummary";
        Name = algname;
        AlgorithmCategory = new BaseDataKeyWords(category);
        AlgorithmCategory.Name = "AlgorithmCategory";
        AlgorithmKeys = new BaseDataKeyWords(algorithms);
        AlgorithmKeys.Name = "AlgorithmKeys";
        Keys = new BaseDataKeyWords(keys);
        Keys.Name = "RunKeyWords";
        Parameters = pairs;
        Parameters.useKeyWord = false;
        Results = new BaseDataKeyWords(results);
        Results.Name = "OutputParameters";
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
        BaseDataObject obj = new BaseDataObject(Name,Identification);
        obj.CopyClone(this);
        return obj;
    }
    
    /**
     * Copy the properties of the object into the current object
     * @param obj The object to copy the properties from
     */
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
        BaseDataAlgorithmSummary o = (BaseDataAlgorithmSummary) obj;
        AlgorithmCategory = o.AlgorithmCategory;
        Keys              = o.Keys;
        AlgorithmKeys     = o.AlgorithmKeys;
        Parameters        = o.Parameters;
        Results           = o.Results;
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
        AlgorithmCategory = new BaseDataKeyWords();
        AlgorithmCategory.Read(io);
        Keys = new BaseDataKeyWords();
        Keys.Read(io);
        AlgorithmKeys.Read(io);
        AlgorithmKeys = new BaseDataKeyWords();
        Parameters = new ClassNamePairs(false);
        Parameters.Read(io);
        Results = new BaseDataKeyWords();
        Results.Read(io);
    }
    
    /**
     * The standard writing of the object (complementary to Read)
     * @param io The io information for the writing of the object
     * @throws java.io.IOException If the writing goes wrong, then an exception is thrown.
     */
    public void Write(RWManagerBase io) throws IOException {
        AlgorithmCategory.Write(io);
        Keys.Write(io);
        AlgorithmKeys.Write(io);
        Parameters.Write(io);
        Results.Write(io);
    }
}
