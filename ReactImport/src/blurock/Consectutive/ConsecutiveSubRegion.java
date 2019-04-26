/*
 * ConsecutiveSubRegion.java
 *
 * Created on November 7, 2003, 9:23 AM
 */

package blurock.Consectutive;
import blurock.core.*;
import java.io.IOException;
/**
 *
 * @author  reaction
 */
public class ConsecutiveSubRegion {
    
    private String Top;
    
    public double topValue;
    
    private String Bottom;
    
    public double bottomValue;
    
    public int NumberOfElements;
    public int Gaps;
    public int Level = 0;
    
    public String nodeInfoName;

    public int start;
    public int last; 
    public int levelpos; 
    public int leveltop;
    public int levelbottom;
 
    /** Creates a new instance of ConsecutiveSubRegion */
    public ConsecutiveSubRegion(String top, String bottom, int elements, int gaps, int level) {
        Top = top;
        Bottom = bottom;
        NumberOfElements = elements;
        Gaps = gaps;
        Level = level;
        topValue = elementNameToDouble(Top);
        bottomValue = elementNameToDouble(Bottom);

        nodeInfoName = "[ " + bottomValue +  " , " +  topValue + "]: " + NumberOfElements + " Elements";
    }
    double elementNameToDouble(String name) {
        double value = Double.parseDouble(name.substring(4,name.length()-2));
        return value;
    }
    void calculateLine(float lineStart, float levelStart, float lineWidth, float levelWidth, float bot, float width, int level) {
        start = Math.round(lineStart + (((float) bottomValue - bot)/width)*lineWidth);
        last = Math.round(lineStart + (((float) topValue - bot)/width)*lineWidth);
        levelpos = Math.round(levelStart + level*levelWidth);
        leveltop = Math.round(levelpos - levelWidth/8);
        levelbottom = Math.round(levelpos + levelWidth/8);
    }
    public void Write(RWManager io) throws IOException {
                io.printLine(Level + ": " + nodeInfoName);
    }

}
