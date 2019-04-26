/*
 * BaseDataSetOfObjects.java
 *
 * Created on February 22, 2001, 5:15 PM
 */

package blurock.coreobjects;
import java.util.Dictionary.*;
import java.util.*;
import blurock.core.*;
import java.io.IOException;
import blurock.core.ObjectNotFoundException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataSetOfObjects extends blurock.coreobjects.BaseDataObject {

    private Hashtable elementSet;
    Vector Names = new Vector();
    public boolean ioAsTriplet = false;
    public boolean ioClassNamePairs = true;
    
    /** Creates new BaseDataSetOfObjects */
    public BaseDataSetOfObjects() {
        elementSet = new Hashtable();
        Name = "SetOfObjects";
        Type = "SetOfObjects";
        Identification = 1;
    }
    public BaseDataSetOfObjects(String name, int id) {
        super(name,id);
        elementSet = new Hashtable();
    }
    public void Read(RWManagerBase io) throws IOException {
       try {
             DataSetOfObjectsClass cls = (DataSetOfObjectsClass) io.dataClasses.findClass(this.Type);
              ClassNamePairs ps;
              if(cls.classNamePairs == null) {
                  ps = new ClassNamePairs();
                  ps.Read(io);
               } else {
                  ps = cls.classNamePairs;
               }
              Read(io,ps);
            } catch(ObjectNotFoundException ex) {
                throw new IOException("Could not find SetOfObjects class: " + this.Type);
            }
    }
    public void Read(RWManagerBase io, ClassNamePairs ps) throws IOException {
        String classname = "unknown";
     try {
         if(ioAsTriplet) {
             ReadAsTriplet(io);
         } else {
        for(int i=0;i<ps.Names.size();i++) {
               String name = (String) ps.Names.elementAt(i);
               classname = (String) ps.ClassNames.elementAt(i);
               DataObjectClass cls = io.dataClasses.findClass(classname);
               BaseDataObject obj = cls.BaseDataObjectExample();
               obj.Read(io);
               obj.Name = name;
               AddObject(obj);
        }
         }
     } catch(ObjectNotFoundException exp) {
         throw new IOException("Illegal Class: '" + classname + "'");
     }
    }
    public void ReadAsTriplet(RWManagerBase io) throws IOException {
        String classname = io.readElement();
       try {
        while(classname.compareTo("END") != 0) {
            DataObjectClass cls = io.dataClasses.findClass(classname);
            BaseDataObject obj = cls.BaseDataObjectExample();
            obj.Name = io.readElement();
            obj.Read(io);
            AddObject(obj);
            classname = io.readElement();
            }
        } catch(ObjectNotFoundException exp) {
         throw new IOException("Illegal Class: '" + classname + "'");
        }
  }
    public void Write(RWManagerBase io) throws IOException {
        if(ioAsTriplet) {
            WriteAsTriplet(io);
        } else {
        Object[] objs = setAsArray();
        if(ioClassNamePairs) {
            ClassNamePairs ps = buildClassNamePairs(objs);
            ps.Write(io);
        }
        for(int i=0;i<objs.length;i++) {
            BaseDataObject obj = (BaseDataObject) objs[i];
            obj.Write(io);
        }
        }
    }
    public void WriteAsTriplet(RWManagerBase io) throws IOException {
        Object[] objs = setAsArray();
        io.printLine("%%% ------------------------------------------------");
        io.printLine("%%%" + Name + ": ");
         for(int i=0;i<objs.length;i++) {
            BaseDataObject obj = (BaseDataObject) objs[i];
            io.printLine(obj.Type + " " + obj.Name);
            obj.Write(io);
            io.printLine("");
         }
        io.printLine("END");
        io.printLine("%%% ------------------------------------------------");
    }
    ClassNamePairs buildClassNamePairs(Object[] objs) {
        ClassNamePairs ps = new ClassNamePairs();
        for(int i=0;i<objs.length;i++) {
            BaseDataObject obj = (BaseDataObject) objs[i];
            ps.addPair(obj.Name,obj.Type);
        }
        return ps;
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataSetOfObjects(man,this,cls);
    }
    
    public String AddObject(BaseDataObject obj) {
        System.out.println("Add Element: " + obj.Name);
        elementSet.put(obj.Name,obj);
        Names.add(obj.Name);
        System.out.println(Names.size());
        return obj.Name;
}
    
public void removeObject(java.lang.String name) throws ObjectNotFoundException {
    elementSet.remove(name);
}

public BaseDataObject findObject(java.lang.String name) throws ObjectNotFoundException {
    BaseDataObject obj = (BaseDataObject) elementSet.get(name);
    if(obj == null)
        throw new ObjectNotFoundException();
    return obj;
}
public Object[] setAsArray() {
    Object[] arr = elementSet.values().toArray();
    Arrays.sort(arr, new BaseDataObject());
    return arr;
}
public Object lastObject() {
    int s = Names.size()-1;
    Object obj = null;
    if(s >= 0 ) {
        String name = (String) Names.elementAt(s);
        obj = elementSet.get(name);
    }
    return obj;
}
public Object firstObject() {
    Object obj = null;
    if(size() > 0) {
        String name = (String) Names.elementAt(0);
        obj = elementSet.get(name);
    }
    return obj;
}
public int size() {
    return elementSet.size();
}
}
