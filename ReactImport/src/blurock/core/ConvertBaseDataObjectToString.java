/*
 * ConvertBaseDataObjectToString.java
 *
 * Created on June 25, 2006, 12:29 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package blurock.core;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.io.IOException;
import blurock.coreobjects.DataSetOfObjectsClass;
import blurock.coreobjects.BaseDataObject;
/**
 *
 * @author reaction
 */
public class ConvertBaseDataObjectToString {
    String output;
    DataSetOfObjectsClass Classes;
    /** Creates a new instance of ConvertBaseDataObjectToString */
    public ConvertBaseDataObjectToString(DataSetOfObjectsClass classes) {
        Classes = classes;
    }
    public String convert(BaseDataObject obj) throws IOException {
            StringWriter swrt = new StringWriter();
            PrintWriter pwrt = new PrintWriter(swrt);
            RWManager io = new RWManager(Classes);
            io.openOutputFile(pwrt);
            obj.Write(io);
            output = swrt.getBuffer().toString();
        return output;
    }
}
