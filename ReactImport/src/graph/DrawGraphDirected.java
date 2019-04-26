/*  FILE     DrawGraphDirected.java
**  PACKAGE     ANALYSIS    
**  AUTHOR   Edward S. Blurock
**
**  CONTENT
**
**  REFERENCES
**
**  COPYRIGHT (C) 1995  ANALYSIS Project / Edward S. Blurock 
*/
package graph;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Vector;
import java.lang.*;
import graph.DrawGraphNode;
 
/** DrawGraphDirected
 *  <ul>
 *   <li> RootNode: The root node name of the directed graph
 *  </ul>
 */
public class DrawGraphDirected extends DrawGraph
				       implements MouseListener
{
    /** The name of the root node
     */
    String RootNode;
    /** The maximum level in the x and y directions
	(used for determining the relative positions)
     */
    int XLevelMax = 0;
    int YLevelMax = 0;

    /** DirectedGraphDirected() empty constructor
     */
    public DrawGraphDirected()
    {
    }
    public void drawGraphCoordinates()
    {
	yMax = getNumberOfNodes() * 5*DefaultNodeFont.getHeight()/3;
	if(yMax < 500)
	    yMax = 500;
	drawGraphFromNodeCoordinates(getRootNode(),0,0);
	int xrel = (xMax - 3*xOffSet)/(XLevelMax);
	int yrel = (yMax - 2*yOffSet)/(YLevelMax);
	setXRelative(xrel);
	setYRelative(yrel);
	drawGraphFromNodeCoordinates(getRootNode(),0,0);
    }
    public int drawGraphFromNodeCoordinates(String node,int levelx,int levely)
    {
	int index = getNodeIndex(node);
	((DrawDirectedNode) Nodes.elementAt(index)).setXLevel(levelx);
	((DrawDirectedNode) Nodes.elementAt(index)).setYLevel(levely);
	((DrawDirectedNode) Nodes.elementAt(index)).setCoordsFromLevel(xOffSet,yOffSet,
								       XRelative,YRelative);
	if(XLevelMax < levelx)
	    XLevelMax = levelx;
	if(YLevelMax < levely)
	    YLevelMax = levely;

	Vector names = getSons(node);
	
	int newlevelx = levelx + 1;
	int newlevely = levely + 1;
	for(int i=0; i < names.size(); i++)
	    {
		String next = (String) names.elementAt(i);
		newlevely = drawGraphFromNodeCoordinates(next,newlevelx,newlevely);
	    }
	return newlevely;
    }
    /** node = getRootNode()
     * <ul>
     *  <li> node: The root node name
     * </ul>
     */
    public String getRootNode()
    {
	return RootNode;
    }
    /** node = getRootNode()
     * <ul>
     *  <li> node: The root node name
     * </ul>
     */
    public void setRootNode(String node)
    {
	RootNode = node;
    }
    
    public void mouseClicked(java.awt.event.MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(java.awt.event.MouseEvent mouseEvent) {
    }
    
    public void mouseExited(java.awt.event.MouseEvent mouseEvent) {
    }
    
    public void mousePressed(java.awt.event.MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(java.awt.event.MouseEvent mouseEvent) {
    }
    
}
