/*
 * DataBeanClass.java
 *
 * Created on April 11, 2006, 4:30 PM
 */

package blurock.runignition;

import java.beans.*;
import java.io.Serializable;
import blurock.DirectedTreeObjects.BaseDataTreeNode;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.core.RWManagerBase;
import java.io.IOException;

import blurock.coreobjects.DataRealClass;
import blurock.coreobjects.DataSetOfObjectsClass;

/**
 * Class Description:
 * @author reaction
 */
public class DataIgnitionConditionsClass extends DataObjectClass implements Serializable {
    
    private PropertyChangeSupport propertySupport;
 
    //not implemented yet
    String LiteratureRefS;
    String UnitConvS;

    
    public DataSetOfObjectsClass SpeciesClass = null;
    public DataRealClass SpeciesValueClass = null;
    public DataRealClass TemperatureClass = null;
    public DataRealClass PressureClass = null;
    public DataRealClass DeltaTClass = null;
    
    /**
     * The Empty constructor (default values for Type, etc.)
     */
    public DataIgnitionConditionsClass() {
        propertySupport = new PropertyChangeSupport(this);
        this.Type = "IgnitionConditions";
        this.SubClass = "Object";
        this.Identification = 1;
    }
    /**
     * Standard constructor for building a new data object of this type.
     * @param id The class identification number
     *
     * @param type The class name
     *
     * @param descr A one line description of the class
     */
    public DataIgnitionConditionsClass(int id, String type, String descr) {
        super(id,type,descr);
   }
    public DataIgnitionConditionsClass(int id, String type, String descr,
            DataSetOfObjectsClass speciesClass,
            DataRealClass numericClass) {
        super(id,type,descr);
        SubClass = "IgnitionConditions";
        SpeciesClass = speciesClass;
        SpeciesValueClass = numericClass;
        TemperatureClass = numericClass;
        PressureClass = numericClass;
        DeltaTClass = numericClass;
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
        BaseDataObject obj = (BaseDataObject) new BaseDataIgnitionConditions();
        obj.Type = this.Type;
        return obj;
    }
    
    /**
     * Create a cloned copy of this class type
     * @return The cloned class
     */
    public DataObjectClass Clone() {
        DataObjectClass cls =   new DataIgnitionConditionsClass(Identification,Name,Description);
        cls.CopyClone(this);
        return cls;
    }
    
    /**
     * Copy the contents of an class into this one
     * @param cls The class to copy from
     */
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
        if(SpeciesClass != null)
            SpeciesClass.CopyClone(cls);
        if(SpeciesValueClass != null)
            SpeciesValueClass.CopyClone(cls);
        if(TemperatureClass!= null)
            TemperatureClass.CopyClone(cls);
        if(PressureClass!= null)
            PressureClass.CopyClone(cls);
        if(DeltaTClass!= null)
            DeltaTClass.CopyClone(cls);
     }
    
    /**
     * Standard read operation.
     * @param io The io information for the read
     *
     * @throws java.io.IOException If the read is not successful, an exception is thrown (usually means the formatting is not correct).
     */
    public void Read(RWManagerBase io) throws IOException {
        LiteratureRefS = io.readElement();
        UnitConvS = io.readElement();
        io.checkToken("StandardUnits:");
        String next = io.readElement();
        while(next.compareTo("END") != 0) 
            next = io.readElement();
        io.checkToken("PropertyConversions:");
        next = io.readElement();
        while(next.compareTo("END") != 0) 
            next = io.readElement();
        
        SpeciesClass = (DataSetOfObjectsClass) io.getClassFromNextElement();
        SpeciesValueClass = (DataRealClass) io.getClassFromNextElement();
        TemperatureClass = (DataRealClass) io.getClassFromNextElement();
        PressureClass = (DataRealClass) io.getClassFromNextElement();
        DeltaTClass = (DataRealClass) io.getClassFromNextElement();
    }
    
    /**
     * Standard write operation
     * @param io The io information determining how object is to be written.
     * @throws java.io.IOException If the write produces an error, an exception is thrown.
     */
    public void Write(RWManagerBase io) throws IOException {
        // This is a hack, because the lterature class and the sub class have not been defined properly
        StringBuffer condclsS = new StringBuffer();
        condclsS.append("LiteratureReference StandardUnitConversions\n");
        condclsS.append("StandardUnits:\n");
        condclsS.append("%% ------------------------\n");
        condclsS.append("END\n");
        condclsS.append("PropertyConversions:\n");
        condclsS.append("END\n");
        io.printLine(condclsS.toString());
        
        io.printString(" " + SpeciesClass.Name);
        io.printString(" " + SpeciesValueClass.Name);
        io.printString(" " + TemperatureClass.Name);
        io.printString(" " + PressureClass.Name);
        io.printString(" " + DeltaTClass.Name);
        io.printLine("");
    }
    
}
