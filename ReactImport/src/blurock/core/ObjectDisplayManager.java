/*
 * ObjectDisplayManager.java
 *
 * Created on February 22, 2001, 10:03 AM
 */

package blurock.core;
import blurock.coreobjects.DataSetOfObjectsClass;

/**
 *
 * @author  reaction
 * @version 
 */
public class ObjectDisplayManager extends java.lang.Object {

    public boolean displayOnly = true;
    public DataSetOfObjectsClass dataClasses;
    
    /** Creates new ObjectDisplayManager */
    public ObjectDisplayManager(DataSetOfObjectsClass classes) {
        dataClasses = classes;
    }

}
