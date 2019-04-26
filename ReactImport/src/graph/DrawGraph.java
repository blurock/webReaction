/*  FILE     DrawGraph.java
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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.lang.*;
import graph.DrawGraphNode;
import java.util.Hashtable;

/** DrawGraph
 *  <ul>
 *  <li> XOffSet, YOffSet: The offset of the graph coordinates
 *  <li> XRelative,YRelative: The relative multiplicative factor
 *  </ul>
 */
public class DrawGraph extends Canvas
implements MouseListener {
    DrawGraphNode pick = null;
    boolean pickfixed;
    public boolean EdgeAdjust = true;
    public double edgeLength = 50.0;
    double baseMove = edgeLength/2.0;
    public int xMax = 500;
    public int yMax = 500;
    public int xOffSet = 0;
    public int yOffSet = 0;
    public int yIncrement = 50;
    public int yInitial = 50;
    public int xTotalMax = 2000;
    /** The relative multiplicative factors
     */
    int XRelative = 50;
    int YRelative = 50;
    
    /** The bonds and nodes of the graph
     */
    public Vector Nodes = new Vector();
    public Vector Bonds = new Vector();
    /** The default color of the graph
     */
    Color DefaultNodeColor = Color.orange;
    Color DefaultBondColor = Color.black;
    /** The default type of the graph
     */
    /** The default node font
     */
    FontMetrics DefaultNodeFont;
    /** The default graph, node and bond types
     */
    int DefaultGraphType = 0;
    int DefaultNodeType = 0;
    int DefaultBondType = 0;
    public boolean treeGraph = false;
    public int countMove = 0;
    String GraphCommand = new String();
    String BestChosenNode;
    String BestChosenParent;
    String BestChosenSon;
    /** DrawGraph() . . . . . . . . . . . . . . . . . . . . empty constructor
     */
    public DrawGraph() {
    }
       public void clearGraph() {
           Bonds = new Vector();
           Nodes = new Vector();
    }

    /** setDefaultNodeFont(font) . . . . . . . . . . . . . . . . .  set name of node
     *       <ul>
     *         <li>  font: The font of the node
     *       </ul>
     */
    public void setDefaultNodeFont(FontMetrics font) {
        DefaultNodeFont = font;
    }
    /** font = getDefaultNodeFont()  . . . . . . . . . . . . . . . . .  get name
     *       <ul>
     *         <li>  font: The font of the node string
     *       </ul>
     */
    public FontMetrics getdDefaultNodeFont() {
        return DefaultNodeFont;
    }
    /** setDefaultNodeColor(color) . . . . . . . . . . . . . . . . set color of node
     *       <ul>
     *         <li>  color: The color of the node
     *       </ul>
     */
    public void setDefaultNodeColor(Color color) {
        DefaultNodeColor = color;
    }
    /** color = getDefaultNodeColor()  . . . . . . . . . . . . . . get color of node
     *       <ul>
     *         <li>  color: The color of the node
     *       </ul>
     */
    public Color getDefaultNodeColor() {
        return DefaultNodeColor;
    }
    /** setDefaultNodeColor(color) . . . . . . . . . . . . . . . . set color of node
     *       <ul>
     *         <li>  color: The color of the node
     *       </ul>
     */
    public void setDefaultBondColor(Color color) {
        DefaultBondColor = color;
    }
    /** color = getDefaultBondColor()  . . . . . . . . . . . . . . get color of node
     *       <ul>
     *         <li>  color: The color of the node
     *       </ul>
     */
    public Color getDefaultBondColor() {
        return DefaultBondColor;
    }
    /** setDefaultNodeType(type)
     *    <ul>
     *      <li> type: The type of the node:
     *    </ul>
     */
    public void setDefaultNodeType(int type) {
        DefaultNodeType = type;
    }
    /** type = getDefaultNodeType()
     *    <ul>
     *     <li> type: The type of the node:
     *    </ul>
     */
    public int getDefaultNodeType() {
        return DefaultNodeType;
    }
    /** setDefaultBondType(type)
     *    <ul>
     *     <li> type: The type of the node:
     *    </ul>
     */
    public void setDefaultBondType(int type) {
        DefaultBondType = type;
    }
    /** type = getDefaultBondType()
     *    <ul>
     *     <li> type: The type of the node:
     *    </ul>
     */
    public int getDefaultBondType() {
        return DefaultBondType;
    }
    /** setDefaultGraphType(type)
     *    <ul>
     *     <li> type: The type of the node:
     *    </ul>
     */
    public void setDefaultGraphType(int type) {
        DefaultGraphType = type;
    }
    /** type = getDefaultGraphType()
     *    <ul>
     *     <li> type: The type of the node:
     *    </ul>
     */
    public int getDefaultGraphType() {
        return DefaultGraphType;
    }
    /** addNode(node) . . . . . . . . . . . . . . .  add node to set of nodes
     * <ul>
     * <li>  node: The node to add.  the color, font and type are set to the default settings of the graph
     * </ul>
     */
    public void addNode(DrawGraphNode node) {
        node.setNodeColor(DefaultNodeColor);
        node.setNodeFont(DefaultNodeFont);
        node.setNodeType(DefaultNodeType);
        Nodes.addElement(node);
    }
    /** addBond(node1,node2)  . . . . . . . . . . .  add node to set of nodes
     * <ul>
     *   <li> node1: The names of the nodes in the bond.
     *   <li> node2: The names of the nodes in the bond.
     * </ul>
     */
    public DrawGraphBond addBond(String node1, String node2) {
        DrawGraphBond bond = new DrawGraphBond();
        int n1 = getNodeIndex(node1);
        int n2 = getNodeIndex(node2);
        if(n1 >= 0 && n2 >= 0 ) {
            bond = new DrawGraphBond(DefaultBondType,
            (DrawGraphNode) Nodes.elementAt(n1),
            (DrawGraphNode) Nodes.elementAt(n2));
            Bonds.addElement(bond);
        }
        else {
            System.out.println("Nodes not found:   Node 1: '" + node1 + "'  Node 2: '" + node2 + "'");
        }
        return bond;
    }
    public void treeGraphPositions(DrawGraphNode pick, int maxWidth,
                        int initialy, int yinc) {
        Hashtable used = new Hashtable();
        Hashtable usedrxns = new Hashtable();
        Vector current = new Vector();
        current.add(pick.getNameTag());
        used.put(pick.getNameTag(),pick.getNameTag());
        int ylevel = initialy;
        while(current.size() > 0) {
            int dist = maxWidth/(current.size()+1);
            for(int cnt=0;cnt<current.size();cnt++) {
                String name = (String) current.elementAt(cnt);
                int n = getNode(name);
                DrawGraphNode p = (DrawGraphNode) Nodes.elementAt(n);
                 p.setYCoordinate(ylevel,yOffSet);
                p.setXCoordinate((cnt+1)*dist,xOffSet);
            }
            Vector next = new Vector();
            for(int cnt=0;cnt<current.size();cnt++) {
                String p = (String) current.elementAt(cnt);
                Vector sons = getSons(p);
                for(int soncnt = 0;soncnt<sons.size();soncnt++) {
                    String name = (String) sons.elementAt(soncnt);
                    if(!used.contains(name)) {
                        used.put(name,name);
                        next.add(name);
                    }
                }
            }
            ylevel = ylevel + yinc;
            current = next;
        }
        ylevel = initialy;
        int xlevel = maxWidth + ylevel;
        for(int n=0;n<Nodes.size();n++) {
            DrawGraphNode p = (DrawGraphNode) Nodes.elementAt(n);
            if(!used.contains(p.getNameTag())) {
                p.setXCoordinate(xlevel,xOffSet);
                p.setYCoordinate(ylevel,yOffSet);
                ylevel += yinc;
            }
        }
    }
    /** index = getNodeIndex(name)
     * <ul>
     *  <li> name: The name of the node to find
     * </ul>
     */
    public int getNodeIndex(String name) {
        boolean notfound = true;
        int found = -1;
        for(int i=0;i < Nodes.size() && notfound; i++) {
            if(name.equals(((DrawGraphNode) Nodes.elementAt(i)).getNameTag())) {
                notfound = false;
                found = i;
            }
        }
        return found;
    }
    /** bond = getNode(name)
     * <ul>
     *   <li> name: The name of the bond to find
     * </ul>
     */
    public int getNode(String name) {
        boolean notfound = true;
        int found = -1;
        for(int i=0;i < Nodes.size() && notfound; i++) {
            if(name.equals(((DrawGraphNode) Nodes.elementAt(i)).getNameTag())) {
                notfound = false;
                found = i;
            }
        }
        return found;
    }
    public String getNodeNameFromIndex(int inode) {
        return ((DrawGraphNode) Nodes.elementAt(inode)).getNameTag();
    }
    /** drawGraph(g)  . . . . . draw the current positions of nodes and bonds
     */
    public void drawGraph(Graphics g) {
        for(int ibond=0;ibond < Bonds.size(); ibond++) {
            DrawGraphBond bond = (DrawGraphBond) Bonds.elementAt(ibond);
                int i1 = getNode(bond.Son);
                int i2 = getNode(bond.Parent);
                DrawGraphNode node1 = (DrawGraphNode) Nodes.elementAt(i1);
                DrawGraphNode node2 = (DrawGraphNode) Nodes.elementAt(i2);
                if(node1.drawNode && node2.drawNode) {
                    //System.out.println("Bond: " + node1.getNameTag() + "," + node2.getNameTag());
                    bond.drawBond(g);
                }
        }
        for(int inode=0;inode < Nodes.size(); inode++) {
            DrawGraphNode node = (DrawGraphNode) Nodes.elementAt(inode);
            node.drawNode(g);
        }
    }
    public void normalNodePositions() {
        double xhigh = -100000;
        double xlow = 100000;
        double yhigh = -100000;
        double ylow = 100000;
        
        for(int inode=0;inode < Nodes.size(); inode++) {
            DrawGraphNode node = (DrawGraphNode) Nodes.elementAt(inode);
            double x = node.getXCoordinate();
            double y = node.getYCoordinate();
            if(x > xhigh) xhigh = x;
            if(y > yhigh) yhigh = y;
            if(x < xlow) xlow = x;
            if(y < ylow) ylow = y;
        }
        double factor = 250.0;
        double xoffset = (xlow + xhigh)/2.0;
        double yoffset = (ylow + yhigh)/2.0;
        double xfactor = factor;
        double yfactor = factor;
        if(Math.abs(xhigh-xlow) >.001)
            xfactor = factor/(2.0*(xhigh - xlow));
        if(Math.abs(yhigh-ylow) >.001)
            yfactor = factor/(2.0*(yhigh - ylow));
        for(int inode=0;inode < Nodes.size(); inode++) {
            DrawGraphNode node = (DrawGraphNode) Nodes.elementAt(inode);
            double x = node.getXCoordinate();
            double y = node.getYCoordinate();
            Double xx = new Double((x-xoffset)*xfactor + 100.0);
            Double yy = new Double((y-yoffset)*yfactor + 100.0);
            node.setXCoordinate(xx.intValue(),xOffSet);
            node.setYCoordinate(yy.intValue(),yOffSet);
        }
        
    }
    /** drawGraphCoordinates()
     *
     * public void drawGraphCoordinates()
     * {
     *
     * for(int inode=0;inode < Nodes.size(); inode++)
     * {
     * DrawGraphNode n = (DrawGraphNode) Nodes.elementAt(inode);
     * }
     * }
     */
    public synchronized void relax() {
        Dimension d = getPreferredSize();
        for(int i=0;i<50;i++) {
            if(EdgeAdjust)
                adjustEdges();
            checkNodeDistances();
            moveNodes(d);
        }
        repaint();
    }
    
    double randomChange(double base) {
        return 2.0*Math.random()*base - base;
    }
    
    
    public void centerXFactor(double xCenter) {
        double accum = 0.0;
        for(int i = 0; i< Nodes.size();i++) {
            DrawGraphNode n1 = (DrawGraphNode) Nodes.elementAt(i);
            accum += n1.XCoordinate;
        }
        double nd = (double) Nodes.size();
        accum /= nd;
        
        double diff = xCenter - accum;
        for(int i = 0; i< Nodes.size();i++) {
            DrawGraphNode n1 = (DrawGraphNode) Nodes.elementAt(i);
            n1.dx += diff;
        }
    }
    public void centerYFactor(double yCenter) {
        double accum = 0.0;
        for(int i = 0; i< Nodes.size();i++) {
            DrawGraphNode n1 = (DrawGraphNode) Nodes.elementAt(i);
            accum += n1.YCoordinate;
        }
        double nd = (double) Nodes.size();
        accum /= nd;
        
        double diff = yCenter - accum;
        for(int i = 0; i< Nodes.size();i++) {
            DrawGraphNode n1 = (DrawGraphNode) Nodes.elementAt(i);
            n1.dy += diff;
        }
    }
    
    public void checkNodeDistances() {
        for (int i = 0 ; i < Nodes.size() ; i++) {
            DrawGraphNode n1 = (DrawGraphNode) Nodes.elementAt(i);
            double dx = 0;
            double dy = 0;
            for (int j = 0 ; j < i ; j++) {
                if(j != i) {
                    DrawGraphNode n2 = (DrawGraphNode) Nodes.elementAt(j);
                    double vx = n1.XCoordinate - n2.XCoordinate;
                    double vy = n1.YCoordinate - n2.YCoordinate;
                    double len = vx * vx + vy * vy;
                    double slen = Math.sqrt(len);
                    if(slen < edgeLength*0.8) {
                        double fx = ((vx / slen))*baseMove;
                        double fy = ((vy / slen))*baseMove;
                        if(slen < edgeLength*.1) {
                            fx = randomChange(baseMove);
                            fy = randomChange(baseMove);
                        }
                        dx += fx;
                        dy += fy;
                    }
                }
            }
            
            double dlen = dx * dx + dy * dy;
            
            if (dlen > 0) {
                //dlen = Math.sqrt(dlen);
                dlen = 1.0;
                n1.dx += dx / dlen;
                n1.dy += dy / dlen;
            }
            
        }
    }
    
    public void moveNodes(Dimension d) {
        //jiggleRandomNode();
        for (int i = 0 ; i < Nodes.size() ; i++) {
            DrawGraphNode n = (DrawGraphNode) Nodes.elementAt(i);
            if (!n.fixed) {
                n.XCoordinate += Math.max(-40, Math.min(40, n.dx));
                if(!treeGraph)
                    n.YCoordinate += Math.max(-30, Math.min(30, n.dy));
                if (n.XCoordinate < 30) {
                    n.XCoordinate = 30;
                }
                else if (n.XCoordinate > d.width-30) {
                    n.XCoordinate = d.width-30;
                }
                if (n.YCoordinate < 30) {
                    n.YCoordinate = 30;
                }
                else if (n.YCoordinate > d.height-30) {
                    n.YCoordinate = d.height-30;
                }
            }
            n.dx /= 2;
            n.dy /= 2;
        }
        centerXFactor(d.width/2.0);
        if(!treeGraph)
            centerYFactor(d.height/2.0);
    }
    
    public void adjustEdges() {
        for (int i = 0 ; i < Bonds.size() ; i++) {
            DrawGraphBond e = (DrawGraphBond) Bonds.elementAt(i);
            DrawGraphNode n1 = (DrawGraphNode) Nodes.elementAt(getNode(e.getParent()));
            DrawGraphNode n2 = (DrawGraphNode) Nodes.elementAt(getNode(e.getSon()));
            double vx = n1.XCoordinate - n2.XCoordinate;
            double vy = n1.YCoordinate - n2.YCoordinate;
            double len = Math.sqrt(vx * vx + vy * vy);
            if(len > edgeLength*1.5) {
                
                n1.XCoordinate  -= vx/10.0;
                n2.XCoordinate  += vx/10.0;
                if(!treeGraph) {
                    n1.YCoordinate -= vy/10.0;
                    n2.YCoordinate += vy/10.0;
                }
            }
        }
    }
    public double bondLength(DrawGraphBond e) {
        DrawGraphNode n1 = (DrawGraphNode) Nodes.elementAt(getNode(e.getParent()));
        DrawGraphNode n2 = (DrawGraphNode) Nodes.elementAt(getNode(e.getSon()));
        double vx = n1.XCoordinate - n2.XCoordinate;
        double vy = n1.YCoordinate - n2.YCoordinate;
        double len = Math.sqrt(vx * vx + vy * vy);
        return len;
    }
    public boolean MoveEdge(int ia) {
        boolean inter = false;
        double di = Math.random() * Bonds.size();
        int i = (int) di;
        DrawGraphBond b = (DrawGraphBond) Bonds.elementAt(i);
        DrawGraphBond a = (DrawGraphBond) Bonds.elementAt(ia);
        if(i == ia)
            inter = false;
        else
            inter = Intersect(a,b);
        return inter;
    }
    public boolean Intersect(DrawGraphBond a, DrawGraphBond b) {
        DrawGraphNode a1 = (DrawGraphNode) Nodes.elementAt(getNode(a.getParent()));
        DrawGraphNode a2 = (DrawGraphNode) Nodes.elementAt(getNode(a.getSon()));
        DrawGraphNode b1 = (DrawGraphNode) Nodes.elementAt(getNode(b.getParent()));
        DrawGraphNode b2 = (DrawGraphNode) Nodes.elementAt(getNode(b.getSon()));
        
        
        return Intersect(a1,a2,b1,b2);
    }
    public boolean Intersect(DrawGraphNode a1, DrawGraphNode a2, DrawGraphNode b1, DrawGraphNode b2) {
        boolean inter;
        double dxa = a2.XCoordinate - a1.XCoordinate;
        double dya = a2.YCoordinate - a1.YCoordinate;
        double dxb = b2.XCoordinate - b1.XCoordinate;
        double dyb = b2.YCoordinate - b1.YCoordinate;
        
        double xdiff = a1.XCoordinate - b1.XCoordinate;
        double ydiff = a1.YCoordinate - b1.YCoordinate;
        double det = dxa*dyb - dxb*dya;
        double pb = -dya*xdiff + dxa*ydiff;
        double pa = -dyb*xdiff + dxb*ydiff;
        if(det != 0.0) {
            pa = pa/det;
            pb = pb /det;
        }
        if(pa > 0.01 && pa < 0.99 && pb > 0.01 && pb < .99) {
            inter = true;
            a1.dx = dxa*(0.1);
            a1.dy = dya*(0.1);
            countMove++;
            if(countMove > 100) {
                Dimension d = new Dimension(100,100);
                shakeNodes(d);
                countMove = 0;
            }
        }
        else {
            inter = false;
        }
        return inter;
    }
    public void shakeNodes(Dimension d) {
        for (int i = 0 ; i < Nodes.size() ; i++) {
            DrawGraphNode n = (DrawGraphNode) Nodes.elementAt(i);
            if (!n.fixed) {
                n.XCoordinate += 80*Math.random() - 40;
                if(!treeGraph)
                    n.YCoordinate += 80*Math.random() - 40;
            }
        }
    }
    
    /** recomputeConnectionCoords()  given the nodes, recompute bond postions
     */
    public void recomputeConnectionCoords() {
        for(int ibond=0;ibond < Bonds.size(); ibond++) {
            DrawGraphBond bond = (DrawGraphBond) Bonds.elementAt(ibond);
            int par = getNode(bond.getParent());
            int son = getNode(bond.getSon());
            ((DrawGraphBond) Bonds.elementAt(ibond)).calculateBondCoordinate((DrawGraphNode) Nodes.elementAt(par),
            (DrawGraphNode) Nodes.elementAt(son));
        }
    }
    /** names = getSons(parent) . . . . . . . . . . . . . . . . . .  get sons
     * <ul>
     *   <li> parent: The name of the parent node
     *   <li> names: The names of the sons of the parent
     * </ul>
     */
    public Vector getSons(String parent) {
        Vector sons = new Vector();
        for(int b = 0; b < Bonds.size() ; b++) {
          DrawGraphBond bond = (DrawGraphBond) Bonds.elementAt(b);
                  if(bond.getParent().equals(parent)) {
                    sons.addElement(bond.getSon());
                }
        }
        return sons;
    }
     public void paint(Graphics g) {
        DefaultNodeFont = g.getFontMetrics();
        
        computeReports();
        setSize(xMax,yMax);
        recomputeConnectionCoords();
        drawGraph(g);
        Color bg = getBackground();
        //Draw a fancy frame around the applet.
        g.setColor(bg);
        g.draw3DRect(0, 0, xMax - 1, yMax - 1, true);
        g.draw3DRect(3, 3, xMax - 7, yMax - 7, false);
    }
    
    /**
     * Determines the value of the parameters for drawing.
     */
    private void computeReports() {
        XRelative = 100;
        if(Nodes.size() == 0)
            YRelative = 100;
        else
            YRelative = (yMax - 2*yOffSet)/ Nodes.size();
    }
    
    /**
     * Process the click on the graph surface.
     * <P>
     * <P> Computes the coordinates of the click.
     * <P> Computes the portions of the command string
     * <P> that will be returned after a click.
     */
    public void getCmdCoord(MouseEvent evnt) {
        
        int xClick = evnt.getX();
        int yClick = evnt.getY();
        
        int bestbond = getBondFromCoordinates(xClick,yClick);
        int bestnode = getNodeFromCoordinates(xClick,yClick);
        
        
        DrawGraphNode node = (DrawGraphNode) Nodes.elementAt(bestnode);
        DrawGraphBond bond = (DrawGraphBond) Bonds.elementAt(bestbond);
        BestChosenNode = node.getNameTag();
        BestChosenParent = bond.getParent();
        BestChosenSon = bond.getSon();
        
        if(getDistanceToBond(bestbond,xClick,yClick) > getDistanceToNode(bestnode,xClick,yClick) ) {
            GraphCommand = " " + BestChosenNode;
        }
        else {
            GraphCommand = " " + BestChosenParent + " " +  BestChosenSon;
        }
        
    }
    public int getDistanceToBond(int ibond, int xClick, int yClick) {
        DrawGraphBond bond = (DrawGraphBond) Bonds.elementAt(ibond);
        return bond.distSquaredToCoordinate(xClick,yClick);
    }
    
    public int getDistanceToNode(int inode, int xClick, int yClick) {
        DrawGraphNode node = (DrawGraphNode) Nodes.elementAt(inode);
        return node.distSquaredToCoordinate(xClick,yClick);
    }
    
    public int getBondFromCoordinates(int xClick, int yClick) {
        int bestbond = 0;
        int mindistbond = xMax*xMax+yMax*yMax;
        for(int ibond=0;ibond < Bonds.size(); ibond++) {
            DrawGraphBond bond = (DrawGraphBond) Bonds.elementAt(ibond);
            if(mindistbond > bond.distSquaredToCoordinate(xClick,yClick)) {
                bestbond = ibond;
                mindistbond = bond.distSquaredToCoordinate(xClick,yClick);
            }
        }
        return bestbond;
    }
    
    public int getNodeFromCoordinates(int xClick, int yClick) {
        String bnode = new String();
        int mindistnode = xMax*xMax+yMax*yMax;
        int bestnode = 0;
        for(int inode=0;inode < Nodes.size(); inode++) {
            DrawGraphNode node = (DrawGraphNode) Nodes.elementAt(inode);
            if(mindistnode > node.distSquaredToCoordinate(xClick,yClick)) {
                bnode = node.getNameTag();
                bestnode = inode;
                mindistnode = node.distSquaredToCoordinate(xClick,yClick);
            }
        }
        return bestnode;
    }
    
    public String getGraphCommand() {
        return GraphCommand;
    }
    
    public String getBestChosenNode() {
        return BestChosenNode;
    }
    
    public String getBestChosenParent() {
        return BestChosenParent;
    }
    
    public String getBestChosenSon() {
        return BestChosenSon;
    }
    
    // finalize: final cleanup
    protected void finalize() throws Throwable {
        super.finalize();
    }
    
    /** setXRelative(rel)
     *    <ul>
     *     <li> rel: The coordinate rel
     *    </ul>
     */
    public void setXRelative(int rel) {
        XRelative = rel;
    }
    /** rel = getXRelative()
     *    <ul>
     *     <li> rel: The coordinate rel
     *    </ul>
     */
    public int getXRelative() {
        return XRelative;
    }
    /** setYRelative(rel)
     *    <ul>
     *     <li> rel: The coordinate rel
     *    </ul>
     */
    public void setYRelative(int rel) {
        YRelative = rel;
    }
    /** rel = getYRelative()
     *    <ul>
     *     <li> rel: The coordinate rel
     *    </ul>
     */
    public int getYRelative() {
        return YRelative;
    }
    /** num = getNumberOfNodes()
     *    <ul>
     *     <li> num: The number of nodes in the graph
     *    </ul>
     */
    public int getNumberOfNodes() {
        return Nodes.size();
    }
    public Dimension getPreferredSize() {
        return new Dimension(xMax,yMax);
    }
    /**
     * mousePressed, mouseClicked, mouseEntered, mouseExited, mouseReleased -
     * methods that define reactions for the mouse operations.
     */
    public void mousePressed(MouseEvent evnt) {
        int x = evnt.getX();
        int y = evnt.getY();
        int nindex = getNodeFromCoordinates(x,y);
        pick = (DrawGraphNode) Nodes.elementAt(nindex);
        
        pickfixed = pick.fixed;
        pick.fixed = true;
        pick.XCoordinate = x;
        pick.YCoordinate = y;
        repaint();
    }
    public void mouseDragged(MouseEvent evnt) {
        if(pick == null)
            mousePressed(evnt);
        int x = evnt.getX();
        int y = evnt.getY();
        pick.XCoordinate = x;
        pick.YCoordinate = y;
        repaint();
    }
    
    public void mouseClicked(MouseEvent evnt) {getCmdCoord(evnt);}
    public void mouseEntered(MouseEvent evnt){}
    public void mouseExited(MouseEvent evnt){}
    public void mouseReleased(MouseEvent evnt){}
    
}
