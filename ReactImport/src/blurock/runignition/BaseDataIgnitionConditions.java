/*
 * BaseDataBean.java
 *
 * Created on April 11, 2006, 10:32 AM
 */

package blurock.runignition;
import blurock.core.RWManagerBase;
import blurock.core.RWManagerBase;
import java.io.IOException;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;

import blurock.coreobjects.BaseDataReal;
import blurock.coreobjects.BaseDataSetOfObjects;
import blurock.Reference.RxnDataLiteratureReference;
import blurock.core.ObjectNotFoundException;

import java.beans.*;
import java.io.Serializable;

/**
 * Object Class:
 * @author reaction
 */
public class BaseDataIgnitionConditions extends BaseDataObject implements Serializable {
    
    private PropertyChangeSupport propertySupport;
    //Not implemented yet
    public RxnDataLiteratureReference Reference = null;
    
    public BaseDataSetOfObjects Species;
    public BaseDataReal Temperature;
    public BaseDataReal Pressure;
    public BaseDataReal DeltaT;
   
    /**
     * The standard empty constructor
     */
    public BaseDataIgnitionConditions() {
        propertySupport = new PropertyChangeSupport(this);
        Type = "IgnitionConditions";
    }
    public BaseDataIgnitionConditions(String name, int id) {
        super(name,id);
        propertySupport = new PropertyChangeSupport(this);
        Type = "IgnitionConditions";
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
    /**name
     * Clone a copy of the class and its properties
     * @return The Cloned object
     */
    public BaseDataObject Clone() {
        BaseDataIgnitionConditions obj = new BaseDataIgnitionConditions(Name,Identification);
        obj.CopyClone(this);
        return obj;
    }
    
    /**
     * Copy the properties of the object into the current object
     * @param obj The object to copy the properties from
     */
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
    BaseDataIgnitionConditions cond = (BaseDataIgnitionConditions) obj;
    Reference.CopyClone(cond.Reference);
    Species.CopyClone(cond.Species);
    Temperature.CopyClone(cond.Temperature);
    Pressure.CopyClone(cond.Pressure);
    DeltaT.CopyClone(cond.DeltaT);
    }
    
    /**
     * Get the class for the displaying of the object class
     * @param man The display object manager
     * @param cls The Class of this object
     * @return The display class
     */
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataIgnitionConditions(man,this,cls);
    }
    /**
     * Standard read (ASCII) of the class
     * @param io The io information on how the object should be read
     * @throws java.io.IOException If the reading goes wrong, then an exception is thrown, especially
     * if the wrong property is not found during the read.
     */
    public void Read(RWManagerBase io) throws IOException {
        Name = io.readElement();
        try {
            System.out.println("IgnitionConditions Type:" + this.Type);
        DataIgnitionConditionsClass condclass = (DataIgnitionConditionsClass) io.dataClasses.findClass(this.Type);
        if(Reference == null)
            Reference = new RxnDataLiteratureReference();
        Reference.Read(io);
        io.checkToken("T:");
        Temperature = (BaseDataReal) condclass.TemperatureClass.BaseDataObjectExample();
        Temperature.Read(io);
        io.checkToken("P:");
        Pressure = (BaseDataReal) condclass.PressureClass.BaseDataObjectExample();
        Pressure.Read(io);
        io.checkToken("d:");
        DeltaT = (BaseDataReal) condclass.DeltaTClass.BaseDataObjectExample();
        DeltaT.Read(io);
        Species = (BaseDataSetOfObjects) condclass.SpeciesClass.BaseDataObjectExample();
        io.checkToken("Species:");
        Species.ReadAsTriplet(io);
        } catch(ObjectNotFoundException e) {
            throw new IOException("IgnitionConditions Class not found!!!!!!\n" + e);
        }
    }
    
    /**
     * The standard writing of the object (complementary to Read)
     * @param io The io information for the writing of the object
     * @throws java.io.IOException If the writing goes wrong, then an exception is thrown.
     */
    public void Write(RWManagerBase io) throws IOException {
        //  This is due to an error in reading Conditions (the name is expected)
        io.printLine(Name);
        Reference.Write(io);
        
        String tS = Double.toString(Temperature.realValue);
        String pS = Double.toString(Pressure.realValue);
        String dS = Double.toString(DeltaT.realValue);
        
        io.printLine("T: " + tS);
        io.printLine("P: " + pS);
        io.printLine("d: " + dS);
        io.printLine("Species:");
        Object[] objs = Species.setAsArray();
        for(int i=0;i<objs.length;i++) {
            BaseDataReal r = (BaseDataReal) objs[i];
            String sS = Double.toString(r.realValue);
            io.printLine(r.Name + " Real " + sS);
        }
        io.printLine("END");
        
    }
}