/*
 * DataSetOfObjectsClass.java
 *
 * Created on February 22, 2001, 5:32 PM
 */

package blurock.coreobjects;
import java.util.Dictionary.*;
import java.util.*;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataSetOfObjectsClass extends blurock.coreobjects.DataObjectClass {

    private Hashtable elementClasses;
    public ClassNamePairs classNamePairs = null;
        String endString = "END";
        String dataType = "DataType:";

    /** Creates new DataSetOfObjectsClass */
    public DataSetOfObjectsClass() {
        elementClasses = new Hashtable();
        Type = "SetOfObjects";
        SubClass = "Object";
    }
    public DataSetOfObjectsClass(int id, String type, String descr) {
        super(id,type,descr);
        elementClasses = new Hashtable();
        SubClass = "SetOfObjects";
    }
    public DataObjectClass Clone() {
        DataSetOfObjectsClass cls = new DataSetOfObjectsClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
       return cls;
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataSetOfObjects();
        obj.Type = Name;
        return obj;
    }
    
    public void Read(RWManagerBase io) throws IOException {
        try {
        io.checkToken("ObjectClasses:");
        String next = io.readElement();
        while(!next.equals(endString)) {
            System.out.println("DataType: " + next);
            if(next.equals(dataType)) {
                next = io.readElement();
                System.out.println("This is the type: " + next);
                DataObjectClass cls = io.dataClasses.findClass(next);
                System.out.println(cls);
                DataObjectClass newcls = cls.Clone();
                newcls.Description = io.restOfLine();
                newcls.Name = io.readElement();
                newcls.Type = newcls.Name;
                System.out.println("The new name: " + newcls.Name);
                System.out.println(newcls);
                newcls.Read(io);
                newcls.derivedClass = true;
                AddClass(newcls);
                next = io.readElement();
            } else {
                throw new IOException("DataType keyword not found: " + next);
            }
        }
        System.out.println("Done reading classes");
        } catch(ObjectNotFoundException exp) {
            throw new IOException("Error in reading set of Objects:\n" + exp);
        }
        classNamePairs = new ClassNamePairs();
        classNamePairs.Read(io);
        System.out.println("Done reading class name pairs");
    }
    
    public void Write(RWManagerBase io) throws IOException {
        io.printLine("%%% ----------------------------------------------");
        io.printLine("ObjectClasses:");
        try {
            Object[] keys = elementClasses.keySet().toArray();
            for(int i=0; i<keys.length;i++) {
                String key = (String) keys[i];
                DataObjectClass cls = (DataObjectClass) findClass(key);
                io.printLine(dataType + " " + cls.Name + " " + cls.Description);
                cls.Write(io);
                io.printLine("");
            }
            io.printLine("END");
            io.printLine("%%% ----------------------------------------------");
            classNamePairs.Write(io);
        } catch(ObjectNotFoundException ex) {
            throw new IOException("Fatal System Error in DataSetOfObjects: " + ex.toString());
        }
        
    }
    
    public String AddClass(DataObjectClass cls) {
        elementClasses.put( cls.Name,cls);
        return cls.Name;
}
    
public DataObjectClass findClass(java.lang.String name) throws ObjectNotFoundException {
    DataObjectClass cls = null;
    if(elementClasses.containsKey(name))
        cls = (DataObjectClass) elementClasses.get(name);
    else
        throw new ObjectNotFoundException("Class not found: '" + name + "'");
    return cls;
}

public void removeClass(java.lang.String name) throws ObjectNotFoundException {
    elementClasses.remove(name);
}
public DataObjectClass[] setAsArray() {
    return (DataObjectClass[]) elementClasses.values().toArray();
}
}
