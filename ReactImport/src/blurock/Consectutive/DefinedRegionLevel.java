/*
 * DefinedRegionLevel.java
 *
 * Created on November 11, 2003, 7:55 AM
 */

package blurock.Consectutive;
import java.util.Hashtable;
import java.util.Arrays;
import java.util.Collection;
/**
 *
 * @author  reaction
 */
public class DefinedRegionLevel implements java.util.Comparator {
    
    private Hashtable LevelSet;
    
    /** Creates a new instance of DefinedRegionLevel */
    public DefinedRegionLevel() {
        LevelSet = new Hashtable();
    }
    
    public String AddObject(DefinedRegion obj) {
        LevelSet.put(obj.Name,obj);
        return obj.Name;
}
    public int getSize() {
        return LevelSet.size();
    }
    public Object[] levelsAsArray() {
        return LevelSet.values().toArray();
    }
    public String divisionsAsString() {
        StringBuffer buf = new StringBuffer();
        Object[] set = LevelSet.values().toArray();
        Arrays.sort(set,this);
        for(int i=0;i<set.length;i++) {
            DefinedRegion region = (DefinedRegion) set[i];
            buf.append( region.Name + " [" + region.Bottom + "," + region.Top + ")\n");
        }
        return buf.toString();
    }
    public DefinedRegion findDefinedLevel(String name) {
        return (DefinedRegion) LevelSet.get(name);
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
    public String[] listOfNodes() {
        Collection r = LevelSet.values();
        Object[] set = r.toArray();
        String[] names = new String[set.length];
        for(int i=0;i<set.length;i++) {
            DefinedRegion region = (DefinedRegion) set[i];
            names[i] = region.Name;
        }
        return names;
    }
    public DefinedRegion regionWithName(String name) {
        Collection r = LevelSet.values();
        Object[] set = r.toArray();
      DefinedRegion found = null;
      boolean notfound = true;
      for(int i=0;i<set.length && notfound;i++) {
        DefinedRegion region = (DefinedRegion) set[i];
        if(name.equals(region.Name)) {
            notfound = false;
            found = region;
            }
        }
    return found;
    }
    public void mergeLevels(DefinedRegionLevel another) {
        int num = another.getSize();
        Object[] a = another.LevelSet.values().toArray();
       for(int i=0;i<num;i++) {
            AddObject((DefinedRegion) a[i]);
        }
    }
}
