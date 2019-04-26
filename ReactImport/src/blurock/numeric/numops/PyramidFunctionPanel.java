/*
 * PyramidFunctionPanel.java
 *
 * Created on September 23, 2004, 3:59 PM
 */

package blurock.numeric.numops;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
/**
 *
 * @author  reaction
 */
public class PyramidFunctionPanel extends javax.swing.JPanel {
    BaseDataPyramidFunction PyramidFunction;
    
    int sizeX = 300;
    int sizeY = 200;
    int shift = 20;
    double percent = 0.1;
    
    int leftLowerEdgeX;
    int leftLowerEdgeY;
    int rightUpperEdgeX;
    int rightUpperEdgeY;

    int leftUpperEdgeX;
    int leftUpperEdgeY;
        
    int rightLowerEdgeX;
    int rightLowerEdgeY;
    
    double left;
    double right;
    double bottom;
    double top;
    
    /** Creates new form PyramidFunctionPanel */
    public PyramidFunctionPanel(BaseDataPyramidFunction pyr, JPanel suppanel) {
        PyramidFunction = pyr;
        basicDimensions();
        
        initComponents();
        Graphics draw = this.getGraphics();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAxis(g);
        if(PyramidFunction.Xbegin == PyramidFunction.Xend)
            drawStepFunction(g);
        else
            drawSlopeFunction(g);        
    }
    void basicDimensions() {
        leftLowerEdgeX = (int) Math.floor(sizeX*percent);
        leftLowerEdgeY = (int) Math.floor(sizeY*(1.0-percent));
        rightUpperEdgeX = (int) Math.floor(sizeX*(1.0-percent));
        rightUpperEdgeY = (int) Math.floor(sizeY*percent);

        leftUpperEdgeX = leftLowerEdgeX;
        leftUpperEdgeY = rightUpperEdgeY;
        
        rightLowerEdgeX = rightUpperEdgeX;
        rightLowerEdgeY = leftLowerEdgeY;
    }
    void drawAxis(Graphics draw) {
        bottom = 1.0;
        top    = 0.0;
        double b = PyramidFunction.Xbegin;
        double e = PyramidFunction.Xend;
        double diff = e-b;
        
        left = b - diff/2.0;
        right = e + diff/2.0;
        if(b == e) {
            left = 0.0;
            right = 2.0*b;
        } 
        Double bottomD = new Double(bottom);
        Double topD = new Double(top);
        Double leftD = new Double(left);
        Double rightD = new Double(right);
        
        draw.drawLine(leftLowerEdgeX,leftLowerEdgeY,rightLowerEdgeX,rightLowerEdgeY);
        draw.drawLine(leftLowerEdgeX,leftLowerEdgeY,leftUpperEdgeX,leftUpperEdgeY);
        draw.drawString(topD.toString(),leftLowerEdgeX-shift,leftLowerEdgeY);
        draw.drawString(bottomD.toString(),leftUpperEdgeX-shift,leftUpperEdgeY);
        draw.drawString(rightD.toString(),rightLowerEdgeX,rightLowerEdgeY+shift);
        draw.drawString(leftD.toString(),leftLowerEdgeX,leftLowerEdgeY+shift);
   }
    void drawStepFunction(Graphics draw) {
        draw.setColor(Color.red);
        
        int topY = rightUpperEdgeY;
        int bottomY = leftLowerEdgeY-1;
        if(PyramidFunction.InitialHeight == 0.0) {
            topY = leftLowerEdgeY-1;
            bottomY = rightUpperEdgeY;
        }
        
        int Xbegin1 = leftLowerEdgeX+1;
        int Ybegin1 = bottomY;
        int Xend1   = (leftLowerEdgeX + rightLowerEdgeX)/2 + 1;
        int Yend1   = bottomY;
        draw.drawLine(Xbegin1,Ybegin1,Xend1,Yend1);
        
        int Xbegin2 = Xend1;
        int Ybegin2 = bottomY;
        int Xend2   = Xend1;
        int Yend2   = topY;
        draw.drawLine(Xbegin2,Ybegin2,Xend2,Yend2);

        int Xbegin3 = Xend2;
        int Ybegin3 = topY;
        int Xend3   = rightUpperEdgeX;
        int Yend3   = topY;
        draw.drawLine(Xbegin3,Ybegin3,Xend3,Yend3);
        
        Double riseD = new Double(PyramidFunction.Xpoint);
        draw.drawString(riseD.toString(),Xbegin2-shift/2,leftLowerEdgeY+shift);
    }
    void drawSlopeFunction(Graphics draw) {
        draw.setColor(Color.red);
         double yMax = Math.max(PyramidFunction.InitialHeight,PyramidFunction.PointHeight);
        yMax = Math.max(yMax,PyramidFunction.FinalHeight);
        double yMin = Math.min(PyramidFunction.InitialHeight,PyramidFunction.PointHeight);
        yMin = Math.min(yMin,PyramidFunction.FinalHeight);
        double yDiff = yMax-yMin;
        
        double xfirst = PyramidFunction.Xbegin;
        double xlast  = PyramidFunction.Xend;
        double xDiff  = xlast - xfirst;
        xfirst -= xDiff/2.0;
        xlast += xDiff/2.0;
        xDiff = xlast - xfirst;
        
        int sx = (int) Math.floor(sizeX*(1.0-2.0*percent));
        int sy = (int) Math.floor(sizeY*(1.0-2.0*percent));
      int pInitialHeight = (int) Math.floor(((PyramidFunction.InitialHeight - yMin)/yDiff)*sy);
      pInitialHeight = leftLowerEdgeY - pInitialHeight;
      int pPointHeight = (int) Math.floor(((PyramidFunction.PointHeight - yMin)/yDiff)*sy);
      pPointHeight = leftLowerEdgeY - pPointHeight;
      int pFinalHeight = (int) Math.floor(((PyramidFunction.FinalHeight - yMin)/yDiff)*sy);
      pFinalHeight = leftLowerEdgeY - pFinalHeight;
      int pXbegin = (int) Math.floor(((PyramidFunction.Xbegin - xfirst)/xDiff)*sx)+ leftLowerEdgeX;
      int pXpoint = (int) Math.floor(((PyramidFunction.Xpoint - xfirst)/xDiff)*sx)+ leftLowerEdgeX;
      int pXend = (int) Math.floor(((PyramidFunction.Xend - xfirst)/xDiff)*sx)+ leftLowerEdgeX;
      
      draw.drawLine(pXbegin,pInitialHeight,pXpoint,pPointHeight);
      draw.drawLine(pXpoint,pPointHeight,pXend,pFinalHeight);
      draw.drawLine(leftLowerEdgeX,pInitialHeight,pXbegin,pInitialHeight);
      draw.drawLine(pXend,pFinalHeight,rightLowerEdgeX,pFinalHeight);        
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents

        setLayout(new java.awt.BorderLayout());

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(300, 300));
        setPreferredSize(new java.awt.Dimension(300, 300));
    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
