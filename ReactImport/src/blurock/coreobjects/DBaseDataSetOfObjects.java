/*
 * DBaseDataSetOfObjects.java
 *
 * Created on February 22, 2001, 5:40 PM
 */

package blurock.coreobjects;
import blurock.core.*;
import utilities.ErrorFrame;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataSetOfObjects extends blurock.coreobjects.DBaseDataObject {

    /** Creates new DBaseDataSetOfObjects */
    public DBaseDataSetOfObjects(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);
        BaseDataSetOfObjects objects = (BaseDataSetOfObjects) Object;
        DataSetOfObjectsClass classes = (DataSetOfObjectsClass) OClass;
        Object[] set = objects.setAsArray();
        for(int i=0;i<set.length;i++) {
            BaseDataObject obj = (BaseDataObject) set[i];
            try {
                System.out.println("SetOfObjects: ObjectAsTreeNode objectAsSubTree: " + obj.Type);
                DataObjectClass cls = displayManager.dataClasses.findClass(obj.Type);
                DBaseDataObject dobj = obj.getDisplayObject(displayManager,cls);
                dobj.objectAsSubTree(objtop);
            } catch (ObjectNotFoundException exp) {
                ErrorFrame fr = new ErrorFrame("Fairly Sever Error: \n Class not Found: '" +
                                    obj.Type + "'");
                fr.show();
            }
        }
        return objtop;
    }
    public JPanel objectAsPanel() {
        ObjectAsTreeNode topnode = new ObjectAsTreeNode(Object.Name);
        PanelSetOfObjects pset = new PanelSetOfObjects(Object.Name,
                            super.objectAsPanel(),
                            objectAsSubTree(topnode));
        return pset;
    }
    
}
