/*
 * BaseDataBean.java
 *
 * Created on April 11, 2006, 10:32 AM
 */

package blurock.ThermoProps;
import blurock.core.RWManagerBase;
import blurock.core.RWManagerBase;
import java.io.IOException;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;

import java.beans.*;
import java.io.Serializable;

/**
 * Holds the additive and multiplicative factor for a conversion
 * @author reaction
 */
public class RxnDataConversionFactors extends BaseDataObject implements Serializable {
    
    private PropertyChangeSupport propertySupport;
    
    /**
     * The constructor
     */
    public RxnDataConversionFactors() {
        propertySupport = new PropertyChangeSupport(this);
        Type = "ConversionFactors";
    }
    
    
    /**
     * listen for add property change
     * @param listener the listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    /**
     * Change Listener
     * @param listener The listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    /**
     * Clone a copy
     * @return A cloned copy
     */
    public BaseDataObject Clone() {
        BaseDataObject obj = new BaseDataObject(Name,Identification);
        obj.CopyClone(this);
        return obj;
    }
    
    /**
     * Fill in the values from the clone
     * @param obj The object from which the data is found
     */
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
        RxnDataConversionFactors f = (RxnDataConversionFactors) obj;
        f.setAdditiveFactor(additiveFactor);
        f.setMultiplicativeFactor(multiplicativeFactor);
    }
    
    /**
     * Get the GUI display object
     * @param man The display manager
     * @param cls The class information
     * @return The display class
     */
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataObject(man,this,cls);
    }
    /**
     * Standard read the object
     * @param io The io information
     * @throws java.io.IOException If the read goes wrong
     */
    public void Read(RWManagerBase io) throws IOException {
        String element = "";
        try {
            additiveFactor = io.readDouble();
            multiplicativeFactor = io.readDouble();
        } catch(NumberFormatException exp) {
            throw new IOException("Expected an Double: '" + element + "'");
        }

    }
    
    /**
     * Standard write routine
     * @param io The io information
     * @throws java.io.IOException If the write goes wrong
     */
    public void Write(RWManagerBase io) throws IOException {
        super.Write(io);
        Double aD = new Double(additiveFactor);
        Double mD = new Double(multiplicativeFactor);
        String line = aD.toString() + "  " + mD.toString();
        io.printLine(line);
    }

    /**
     * Holds value of property additiveFactor.
     */
    private double additiveFactor;

    /**
     * Getter for property additiveFactor.
     * @return Value of property additiveFactor.
     */
    public double getAdditiveFactor() {

        return this.additiveFactor;
    }

    /**
     * Setter for property additiveFactor.
     * @param additiveFactor New value of property additiveFactor.
     */
    public void setAdditiveFactor(double additiveFactor) {

        this.additiveFactor = additiveFactor;
    }

    /**
     * Holds value of property multiplicativeFactor.
     */
    private double multiplicativeFactor;

    /**
     * Getter for property multiplicativeFactor.
     * @return Value of property multiplicativeFactor.
     */
    public double getMultiplicativeFactor() {

        return this.multiplicativeFactor;
    }

    /**
     * Setter for property multiplicativeFactor.
     * @param multiplicativeFactor New value of property multiplicativeFactor.
     */
    public void setMultiplicativeFactor(double multiplicativeFactor) {

        this.multiplicativeFactor = multiplicativeFactor;
    }

}
