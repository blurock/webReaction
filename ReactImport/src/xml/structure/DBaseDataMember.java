/*
 * DBaseDataMember.java
 *
 * Created on May 10, 2001, 11:30 AM
 */

package xml.structure;
import blurock.core.*;
    import javax.swing.*;
    import javax.swing.tree.*;
import blurock.coreobjects.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataMember extends blurock.coreobjects.DBaseDataObject {

    /** Creates new DBaseDataMember */
    public DBaseDataMember(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
        BaseDataMember mem = (BaseDataMember) Object;
        System.out.println("  DBaseDataMember(): Object.MemberName = '" + mem.MemberName + "'");
    }

    public JPanel objectAsPanel() {
        //JPanel top = super.objectAsPanel();
        BaseDataMember mem = (BaseDataMember) Object;
        System.out.println("  objectAsPanel(): Object.MemberName = '" + mem.MemberName + "'");
        MemberPanel mempanel = new MemberPanel(mem);
        //TopObjectPanel toppanel = new TopObjectPanel("Object",top,mempanel);
        return mempanel;
     }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return new ObjectAsTreeNode(Object.Name);
    }
    
}
