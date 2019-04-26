/*
 * DBaseDataInstanceDoubleVector.java
 *
 * Created on June 8, 2005, 2:29 PM
 */

package blurock.InstanceObjects;
import blurock.core.ObjectNotFoundException;
import blurock.core.ObjectAsTreeNode;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 *
 * @author  reaction
 */
public class DBaseDataInstanceDoubleVector extends DBaseDataObject {
    
    /** Creates a new instance of DBaseDataInstanceDoubleVector */
    public DBaseDataInstanceDoubleVector(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode name = new ObjectAsTreeNode(this);
        topnode.add(name);
        BaseDataInstanceDoubleVector vec = (BaseDataInstanceDoubleVector) this.Object;
        double[] dvec = vec.vector;
        for(int i=0;i<dvec.length;i++) {
            String nodeS = String.valueOf(dvec[i]);
            ObjectAsTreeNode node = new ObjectAsTreeNode(nodeS);
            name.add(node);
        }
        return name;
    } 
    public JPanel objectAsPanel() {
        BaseDataInstanceDoubleVector vec = (BaseDataInstanceDoubleVector) this.Object;
        VectorPanel panel = new VectorPanel(vec);
        return (JPanel) panel;
    }

}
