/*  FILE     DrawGraphNode.java
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
import java.lang.*;
import java.awt.*;
 
/** DrawGraphNode . . . . . . . . . . . . . . . .  The graph node information
**
**  This has the node information:
**  <ul> 
**  <li> x,y: The coordinates
**  <li> NameTag: The node string lable
**  <li> type: The type of the node
**  <li> color: The color of the node
**  </ul>
**
*/
public class DrawGraphNode extends Object
{
    /**
     *  nameTag: The name of the node
     */
    String NameTag;
    /**
     *   The x and y coordinates  in panel
     */
    int XCoordinate = 0;
    int YCoordinate = 0;
    public double dx,dy;
    public boolean fixed = false;
    public boolean drawNode = true;

    /**
     *   The properties of the node
     */
    Color NodeColor = Color.black;
    FontMetrics NodeFont;
    int NodeSize = 20;
    int NodeType = 100;
    public int Len = 40;
    /** DrawGraphNode() . . . . . . . . . . . . . . . . . . . . . constructor
     */
    public DrawGraphNode()
    {
	NameTag = new String();
    }
    /** DrawGraphNode(name) . . . . . . . . . . . . . . . .  create with name
     *       <ul>
     *         <li>  name: The name of the node
     *       </ul>
     */
    public DrawGraphNode(String name)
    {
	NameTag = name;
    }
    /** setNameTag(name) . . . . . . . . . . . . . . . . .  set name of node
     *       <ul>
     *         <li>  name: The name of the node
     *       </ul>
     */
    public void setNameTag(String name)
    {
	NameTag = name;
    }
    /** name = getNameTag(name)  . . . . . . . . . . . . . . . . .  get name
     *       <ul>
     *         <li>  name: The name of the node
     *       </ul>
     */
    public String getNameTag()
    {
	return NameTag;
    }
    /** setNodeFont(font) . . . . . . . . . . . . . . . . .  set name of node
     *       <ul>
     *         <li>  font: The font of the node
     *       </ul>
     */
    public void setNodeFont(FontMetrics font)
    {
	NodeFont = font;
    }
    /** font = getNodeFont()  . . . . . . . . . . . . . . . . .  get name
     *       <ul>
     *         <li>  font: The font of the node string
     *       </ul>
     */
    public FontMetrics getNodeFont()
    {
	return NodeFont;
    }
    /** setXCoordinate(x) . . . . . . . . . . . . . . . . .  set x coordinate
     *       <ul>
     *         <li>  x: The x node coordinate 
     *       </ul>
     */
    public void setXCoordinate(int x, int offSet)
    {
	XCoordinate = x - offSet;
    }
    /** setYCoordinate(y) . . . . . . . . . . . . . . . . .  set y coordinate
     *       <ul>
     *         <li>  y: The y node coordinate 
     *       </ul>
     */
    public void setYCoordinate(int y, int offSet)
    {
	YCoordinate = y - offSet;
    }
    /** x = getXCoordinate()  . . . . . . . . . . . . . . .  get x coordinate
     *       <ul>
     *         <li>  x: The x node coordinate 
     *       </ul>
     */
    public int getXCoordinate()
    {
	return XCoordinate;
    }
    /** y = getYCoordinate()  . . . . . . . . . . . . . . .  get y coordinate
     *       <ul>
     *         <li>  y: The y node coordinate 
     *       </ul>
     */
    public int getYCoordinate()
    {
	return YCoordinate;
    }
    /** setNodeColor(color) . . . . . . . . . . . . . . . . set color of node
     *       <ul>
     *         <li>  color: The color of the node 
     *       </ul>
     */
    public void setNodeColor(Color color)
    {
	NodeColor = color;
    }
    /** color = getNodeColor()  . . . . . . . . . . . . . . get color of node
     *       <ul>
     *         <li>  color: The color of the node 
     *       </ul>
     */
    public Color getNodeColor()
    {
	return NodeColor;
    }
    /** setNodeSize(size) . . . . . . . . . . . . . . .  The size of the node
     *    <ul>
     *      <li> size: The size of the node to be drawn. If zero, then the height is the text height.
     *    </ul>
     */
    public void setNodeSize(int size)
    {
	NodeSize = size;
    }
    /** getNodeSize(size) . . . . . . . . . . . . . . .  The size of the node
     *    <ul>
     *      <li> size: The size of the node to be drawn. If zero, then the height is the text height.
     *    </ul>
     */
    public int getNodeSize()
    {
	return NodeSize;
    }
    /** setNodeType(type)
     *    <ul>
     *     <li> type: The type of the node:
     *                 <ul>
     *                   <li>100: as box 
     *                   <li>200: as oval
     *                 </ul>
     *    </ul>
     */
    public void setNodeType(int type)
    {
	NodeType = type;
    }
    /** type = getNodeType()
     *    <ul>
     *     <li> type: The type of the node:
     *    </ul>
     */
    public int getNodeType()
    {
	return NodeType;
    }
    /** void drawNode(g)  . . . . . . . . . . . . . . . . . . . draw the node
     *    <ul>
     *    <li> g: The graphics objects
     *     </ul>
     */
    public void drawNode(Graphics g)
    {
	    int x = XCoordinate;
	    int y = YCoordinate;
	    g.setColor(NodeColor);
	    NodeFont = g.getFontMetrics();

	    int w = NodeFont.stringWidth(NameTag) + 10;
	    int h;

	        if(NodeSize == 0) {
	     h = NodeFont.getHeight();
	      } else {
	        h = NodeSize;
	      }
	    if(NodeType == 200)
	      g.fillOval(x-h/2,y-h/2,h,h);
	    else
	      g.fillRect(x - w/2, y - h / 2, w, h);
	    g.setColor(Color.black);
             if(NodeType == 200) {
	      g.drawOval(x-h/2,y-h/2,h-1,h-1);
              g.drawString(NameTag, x - (w-10)/2, (y - h/2) + 3*NodeFont.getAscent()/2);
            } else {
	      g.draw3DRect(x - w/2, y - h / 2, w-1, h-1,true);
	      g.drawString(NameTag, x - (w-10)/2, (y - h/2) + 3*NodeFont.getAscent()/2);
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
}
