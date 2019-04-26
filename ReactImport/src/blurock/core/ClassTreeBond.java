/*
 * ClassTreeBond.java
 *
 * Created on March 9, 2001, 10:37 AM
 */

package blurock.core;

/**
 *
 * @author  reaction
 * @version 
 */
public class ClassTreeBond extends java.lang.Object {

    ClassTree classTree;
    ClassTreeNode parentNode;
    ClassTreeNode sonNode;

    ClassTreeBond(ClassTree classtree, String parent, String son)
    {
	System.out.println("Class Tree Bond: '" + parent + "'" + parent.length() + "'" + son + "' " + son.length());
	if(!(parent.equals("Identify")) && 
	   !(parent.startsWith(son) && son.length() == parent.length())) {
	    classTree = classtree;
	    parentNode = classTree.findNode(parent);
	    sonNode = classTree.findNode(son);
	    System.out.println("Class Tree Bond: '" + parentNode.nodeName + "' '" + sonNode.nodeName + "' ");
	    classTree.addBond(parentNode,sonNode);
	}
    }

}
