/*  FILE     DrawGraphBond.java
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

import java.awt.Color;
import java.awt.Graphics;
import java.lang.*;
import graph.DrawGraphNode;
 

/** DrawGraphBond . . . . . . . . . . . The basic bond connection 
**
**  This has the node information:
**  <ul> 
**  <li> node1,node2: The names of the node being connected
**  <li> xCoordinate,yCoordinate: The coordinate of the bond
**  <li> type: How the bond is draw
**  </ul>
**
*/
public class DrawGraphBond extends Object
{
    int x1,y1,x2,y2;
    public int GRAPH_BOND_TYPE_DIRECT = 100;
    public int GRAPH_BOND_TYPE_ANGLES = 200;
    public int BondType = 1;
    /**  The names of the Nodes
     */
    String Parent,Son;
    /** The Coordinates of the nodes.  These will be used to determine
	if the node has been selected.
    */
    int XCoordinate,YCoordinate;
    /** The type of the bond
	<ul>
	<li> GRAPH_BOND_TYPE_DIRECTED: Direct Connection (The coordinate is the halfway point)
	<li> GRAPH_BOND_TYPE_DIRECTED_ANGLES: Right angle connection (The coordinate is at the junction)
	</ul>
    */
    int Type = 200;
    public int Len = 40;
    /** The color of the bond
     */
    Color BondColor;
    
    /** DrawGraphBond  . . . . . . . . . . . . . . . the empty constructor
     */
    public DrawGraphBond()
    {
    }
    /** DrawGraph(type,node1,node2) . . . . . . . . . . . . . . . constructor
     * <ul>
     *   <li> type: The type of the bond connection
     *   <li> node1,node2: The nodes to be connected
     * </ul>
     */
    public DrawGraphBond(int type, DrawGraphNode node1, DrawGraphNode node2)
    {
	Type = type;
	Parent = node1.getNameTag();
	Son = node2.getNameTag();
	calculateBondCoordinate(node1,node2);
    }
    /** setType(type) . . . . . . . . . . . . . .  set the node type directly
     * <ul>
     * <li> type: The type to set
     * </ul>
     */
    public void setType(int type)
    {
	Type = type;
    }
    /** type = getType() get the current type
     * <ul>
     * <li> type: The type to set
     * </ul>
     */
    public int getType()
    {
	return Type;
    }
    /** setBondColor(color) . . . . . . . . . . . . . . . . . . set the color
     *  <ul>
     *  <li> color: The color of the bond
     *  </ul>
     */
    public void setBondColor(Color color)
    {
	BondColor = color;
    }
    /** color = getBondColor()  . . . . . . . . . . . . . . . . get the color
     *  <ul>
     *  <li> color: The color of the bond
     *  </ul>
     */
    public Color getBondColor()
    {
	return BondColor;
    }
    /** calculateNodeCoordinate(node1,node2)  . . . . . . .  node coordinates
     * <ul>
     * <li> node1,node2: The nodes
     * </ul>
     */
    public void calculateBondCoordinate(DrawGraphNode node1, DrawGraphNode node2)
    {
	x1 = node1.getXCoordinate();
	y1 = node1.getYCoordinate();
	x2 = node2.getXCoordinate();
	y2 = node2.getYCoordinate();
	if(Type == GRAPH_BOND_TYPE_DIRECT)
	    {
		XCoordinate = (x1 + x2) / 2;
		YCoordinate = (y1 + y2) / 2;
	    }
	else
	    {
		XCoordinate = x1;
		YCoordinate = y2;
	    }
    }
    /** dist = distSquaredToCoordinate(x,y)
     *    <ul> 
     *    <li> x,y: The coordinate
     *    <li> dist: The distance squared
     *    </ul>
     */
    public int distSquaredToCoordinate(int x, int y)
    {
	int xdiff = x - XCoordinate;
	int ydiff = y - YCoordinate;
	return xdiff*xdiff + ydiff*ydiff;
    }
    public int bondLength() {
        int xdiff = x1 - x2;
        int ydiff = y1 - y2;
        double x = (double) xdiff*xdiff + ydiff*ydiff;
        float l = (float) Math.sqrt(x);
        return (int) Math.round(l);
    }
    /** drawBond(g) . . . . . . . . . . . . . . . . . . . . . . draw the bond
     * <ul>
     * <li> g: The graphics 
     * </ul>
     */
    public void drawBond(Graphics g)
    {
        g.setColor(BondColor);
	if(Type == GRAPH_BOND_TYPE_DIRECT)
	    {
                int length = bondLength();
                int xdirection = 0;
                int ydirection = 0;
                if(length != 0)
                    xdirection = (x2-x1)*3/length;
                if(length != 0)
                    ydirection = (y2-y1)*3/length;
                if(BondType == 1) 
		    g.drawLine(x1,y1,x2,y2);
                else if(BondType == 2) {
                    drawlineoffset(g,length,xdirection,ydirection,true);
                    drawlineoffset(g,length,xdirection,ydirection,false);
                } else if(BondType == 3) {
                    drawlineoffset(g,length,xdirection,ydirection,true);
                    g.drawLine(x1,y1,x2,y2);
                    drawlineoffset(g,length,xdirection,ydirection,false);
                } else 
                    g.drawLine(x1,y1,x2,y2);
	    }
	else
	    {
		g.drawLine(x1,y1,x1,y2);
		g.drawLine(x1,y2,x2,y2);
	    }
    }
    void drawlineoffset(Graphics g,int length,int xdirection,int ydirection,boolean dir) {
        int normal = -1;
        if(dir)
            normal = 1;
        if(xdirection < 2) xdirection = 2;
        if(ydirection < 2) ydirection = 2;
        int x1offset = x1 - ydirection*normal;
        int y1offset = y1 + xdirection*normal;
        int x2offset = x2 - ydirection*normal;
        int y2offset = y2 + xdirection*normal;
        g.drawLine(x1offset,y1offset,x2offset,y2offset);
    }
    /** parent = getParent()  . . . . . . . . . .  get the parent of the bond
     * <ul>
     *  <li> parent: the parent of the bond
     * </ul>
     */
    public String getParent()
    {
	return Parent;
    }
    /** son = getSon()  . . . . . . . . . .  get the son of the bond
     * <ul>
     *  <li> son: the son of the bond
     * </ul>
     */
    public String getSon()
    {
	return Son;
    }
}
