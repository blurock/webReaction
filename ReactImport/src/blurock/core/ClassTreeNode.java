/*
 * ClassTreeNode.java
 *
 * Created on March 9, 2001, 10:35 AM
 */

package blurock.core;
import javax.swing.*;
import javax.swing.tree.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class ClassTreeNode extends javax.swing.tree.DefaultMutableTreeNode {

    private ClassTree classTree;
    
    public String nodeName;
    
    /** Creates new ClassTreeNode */
    public ClassTreeNode(ClassTree tree, String node) {
        super(node);
        classTree = tree;
        nodeName = node;
    }

}
