/*
 * DefinedRegion.java
 *
 * Created on November 11, 2003, 7:52 AM
 */

package blurock.Consectutive;
import blurock.core.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 */
public class DefinedRegion implements java.util.Comparator {
    
    public double Bottom;
    
    public double Top;
    
    public String Name;
    public int subRegion;
    public int Level;
    
    /** Creates a new instance of DefinedRegion */
    public DefinedRegion(String name,int sub,double top,double bottom, int level)  {
        Name = name;
        Top = top;
        Bottom = bottom;
        subRegion = sub;
        Level = level;
    }
    public DefinedRegion(DefinedRegion region) {
        Bottom = region.Bottom;
        Top        = region.Top;
        Name       = region.Name;
        subRegion  = region.subRegion;
        Level      = region.Level;
    }
    public void Write(RWManager io) throws IOException {
        io.printLine(Level + ": " + Name + " [" + Bottom + "," + Top + "]");
    }
    
    public int compare(Object obj, Object obj1) {
      int result = 0;
      DefinedRegion r1 = (DefinedRegion) obj;
      DefinedRegion r2 = (DefinedRegion) obj1;
      
      if(r1.Bottom > r2.Bottom) 
          result = 1;
      else if(r1.Bottom < r2.Bottom)
          result = -1;
           
      return result;        
    }
    public String toString() {
        return new String(Level + ": " + Name + " [" + Bottom + "," + Top + "]");
    }
}
