/*
 * ClusterRegionsCanvas.java
 *
 * Created on November 7, 2003, 10:27 AM
 */

package blurock.Consectutive;
import java.awt.*;
/**
 *
 * @author  reaction
 */
public class ClusterRegionsCanvas extends java.awt.Canvas {
    BaseDataConsecutiveSeriesSet set;
    float canvasWidth = 600;
    float canvasHeight = 200;
    int cW = (int) canvasWidth;
    int cH = (int) canvasHeight;
    float levelWidth = 40;
    float levelStart = 20;
    float lineWidth = canvasWidth*9/10;
    float lineStart = canvasWidth/20;
   
    /** Creates a new instance of ClusterRegionsCanvas */
    public ClusterRegionsCanvas(BaseDataConsecutiveSeriesSet s) {
        set = s;
        setSize(cW,cH);
    }
    public void paint(Graphics g) {
        int xMax = Math.round(canvasWidth);
        int yMax = Math.round(canvasHeight);
        setSize(xMax,yMax);
        System.out.println("paint");
        System.out.print(g);
        drawCanvas(g);
        Color bg = getBackground();
        g.setColor(bg);
        g.draw3DRect(0, 0, xMax - 1, yMax - 1, true);
        g.draw3DRect(3, 3, xMax - 7, yMax - 7, false);
    }
    void drawCanvas(Graphics g) {
        g.setColor(Color.red);
        if(set.topValue - set.bottomValue > .0000000000001) {
            float bot = (float) set.bottomValue;
            float top = (float) set.topValue;
            float width = top - bot;
            Object[] arr = set.setAsArray();
            for(int i=0;i<arr.length;i++) {
                BaseDataConsecutiveSeries seriesset = (BaseDataConsecutiveSeries) arr[i];
                for(int j=0;j<seriesset.NumberOfSubRegions;j++) {
                    //System.out.println("paint: "+ seriesset.Name);
                    ConsecutiveSubRegion series = seriesset.SubRegions[j];
                    if(series != null) {
                        series.calculateLine(lineStart, levelStart, lineWidth, levelWidth, bot, width, seriesset.NumberOfLevels-1);
                       //System.out.println(series.start + "  "+ series.last + "  " + series.levelpos + "  "
                       //    + series.leveltop + "  " + series.levelbottom);
                        g.drawLine(series.start,series.levelpos,series.last,series.levelpos);
                        g.drawLine(series.start,series.leveltop,series.start,series.levelbottom);
                        g.drawLine(series.last,series.leveltop,series.last,series.levelbottom);
                    }
                }
            }
        }
    }
}
