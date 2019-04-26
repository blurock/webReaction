/*
 * StandardListListModel.java
 *
 * Created on January 19, 2001, 6:28 PM
 */

package react.utilities;

/** This is the list model for the StandardListPanel
 * @author Edward S. Blurock
 * @version 2000.0
 */
public class StandardListListModel extends javax.swing.AbstractListModel {

    private String[] nameList = new String[10];
    
    /** Creates new StandardListListModel */
    public StandardListListModel() {
        nameList = new String[10];
    }

/** The standard constructor
 * @param names <I>The names in the list</I>
 */    
    public StandardListListModel(java.lang.String[] names) {
        nameList = names;
    }
    
/** The name at the given index
 * @param index <I>Where in the list of names</I>
 * @return <I>The name at the index</>
 */    
    public Object getElementAt(int index) {
            return nameList[index];
}
    
/** The number of names is the list
 * @return <I>The number of elements</>
 */
public int getSize() {
        if(nameList != null)
            return nameList.length;
        else
            return 0;
}

}
