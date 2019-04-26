/*
 * BaseDataSetOfEquivalentSets.java
 *
 * Created on June 6, 2005, 6:06 PM
 */

package blurock.EquivalenceClasses;
import java.io.IOException;

import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.BaseDataSetOfObjects;
import blurock.coreobjects.DataSetOfObjectsClass;
import blurock.coreobjects.BaseDataKeyWords;
import blurock.coreobjects.DBaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.core.ObjectDisplayManager;
import blurock.core.RWManagerBase;
import blurock.core.ObjectNotFoundException;
/**
 *
 * @author  reaction
 */
public class BaseDataSetOfEquivalentSets extends BaseDataSetOfObjects {
    
    /** Creates a new instance of BaseDataSetOfEquivalentSets */
    public BaseDataSetOfEquivalentSets() {
    }
    public BaseDataSetOfEquivalentSets(String name, int id) {
        super(name,id);
    }
    public BaseDataObject Clone() {
        BaseDataSetOfEquivalentSets obj = new BaseDataSetOfEquivalentSets(Name,Identification);
        obj.CopyClone(this);
       return obj;         
    }
    
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
        Name = obj.Name;
        Identification = obj.Identification;
        Type = obj.Type;
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataSetOfEquivalentSets(man,this,cls);
    }
    public void Read(RWManagerBase io) throws IOException {
        String name = this.Type;
        System.out.println("Read type: " + name);
        try {
         DataSetOfObjectsClass classes = io.dataClasses;
         DataSetOfEquivalentSetsClass setofsetsclass = (DataSetOfEquivalentSetsClass) classes.findClass(name);
         DataEquivalentSetClass setclass = setofsetsclass.EquivalentSetClass;
         name = io.readElement();
         while(!name.equals("END")) {
             BaseDataEquivalentSet set = (BaseDataEquivalentSet) setclass.BaseDataObjectExample();
             set.Name = name;
             set.Read(io);
             name = io.readElement();
             AddObject(set);
         }
        } catch(ObjectNotFoundException ex) {
            throw new IOException("Error in Reading SetOfEquivalentSets: set not found: " + name);
        }
    }
    
    public void Write(RWManagerBase io) throws IOException {
        Object[] set = setAsArray();
        for(int i=0;i<set.length;i++) {
            BaseDataEquivalentSet equivset = (BaseDataEquivalentSet) set[i];
            io.printLine(equivset.Name);
            equivset.Write(io);
            io.printLine(" ");
        }
         io.printLine("END");
   }
   

}
