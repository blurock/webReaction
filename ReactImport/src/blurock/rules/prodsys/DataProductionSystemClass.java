/*
 * DataProductionSystemClass.java
 *
 * Created on February 28, 2001, 12:04 PM
 */

package blurock.rules.prodsys;
import blurock.rules.actions.*;
import blurock.opandalgs.parameterized.*;
import blurock.coreobjects.*;
import blurock.core.*;
import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataProductionSystemClass extends blurock.opandalgs.parameterized.DataParameterizedFunctionClass {

    public DataSetOfRulesClass RulesClass;
    
    /** Creates new DataProductionSystemClass */
    public DataProductionSystemClass() {
    }
    public DataProductionSystemClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "ParameterizedFunction";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataProductionSystem();
        obj.Type = Name;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataProductionSystemClass cls = 
                new DataProductionSystemClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
        try {
            cls.RulesClass = (DataSetOfRulesClass) RulesClass.Clone();
        } catch(NullPointerException exp) {
            cls.RulesClass = null;
        }
       return cls;
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
        RulesClass = (DataSetOfRulesClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        try {
            io.printLine(RulesClass.Name);
        } catch (NullPointerException nexp) {
            throw new IOException("Production Rule Class not fully defined");
        }
    }
    
}
