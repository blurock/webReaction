/*  FILE     DrawDirectedNode.java
**  PACKAGE     REACTION    
**  AUTHOR   Edward S. Blurock
**
**  CONTENT
**
**  REFERENCES
**
**  COPYRIGHT (C) 1995  REACTION Project / Edward S. Blurock 
*/
package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.*;
import graph.DrawGraphNode;
 
/** DrawDirectedNode . . . . . . . . . . . . . . . .  The directed graph node information
 *
 * This has the node information:
 * <ul>
 * <li> XLevel,YLevel: The coordinates
 * </ul>
 * @author Edward S. Blurock
 */
public class DrawDirectedNode extends DrawGraphNode
{
    /**
     *   The x and y levels in panel
     */
    int XLevel,YLevel;

    /** DrawDirectedNode() . . . . . . . . . . . . . . . . . . empty constructor
     *
     */
    public DrawDirectedNode()
    {
	super();
    }
    /** DrawDirectedNode(name) . . . . . . . . . . . . . . constructor with name
     *
     */
    public DrawDirectedNode(String name)
    {
	super(name);
    }
    /** setXLevel(x)  . . . . . . . . . . . . . . . . set x level coordinate
     *     <ul>
     *       <li> x: The x level coordinate
     *     </ul>
     * @param x The x level coordinate
 */
    public void setXLevel(int x)
    {
	XLevel = x;
    }
    /** setYLevel(y)  . . . . . . . . . . . . . . . . set y level coordinate
     *     <ul>
     *       <li> y: The y level coordinate
     *     </ul>
     * @param y
 */
    public void setYLevel(int y)
    {
	YLevel = y;
    }
    /** x = getXLevel()  . . . . . . . . . . . . . . . . get x level coordinate
     *      <ul>
     *        <li> x: The x level coordinate
     *      </ul>
     */
    public int getXLevel()
    {
	return XLevel;
    }
    /** y = getYLevel()  . . . . . . . . . . . . . . . . get y level coordinate
     *      <ul>
     *        <li> y: The y level coordinate
     *      </ul>
     */
    public int getYLevel()
    {
	return YLevel;
    }
    /** setCoordsFromLevel(xoffset,yoffset,xrel,yrel)  get y level coordinate
     *     <ul>
     *       <li> xoffset,yoffset: The x and y offset for absolute coordinates
     *       <li> xrel,yrel: The relative 
     *     </ul>
     * @param xoffset
     * @param yoffset
     * @param xrel
     * @param yrel
 */
    public void setCoordsFromLevel(int xoffset,int yoffset,int xrel,int yrel)
    {
	int x = xrel * getXLevel();
	int y = yrel * getYLevel();
	setXCoordinate(x,xoffset);
	setYCoordinate(y,yoffset);
    }
    /** void drawNode(g)  . . . . . . . . . . . . . . . . . . . draw the node
     *    <ul>
     *    <li> g: The graphics objects
     *     </ul>
     */
    public void drawNode(Graphics g)
    {
	super.drawNode(g);
    }
}
