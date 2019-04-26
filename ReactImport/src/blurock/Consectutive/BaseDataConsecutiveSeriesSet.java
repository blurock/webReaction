/*
 * BaseDataConsecutiveSeriesSet.java
 *
 * Created on November 5, 2003, 5:55 PM
 */

package blurock.Consectutive;
import java.util.*;
import blurock.core.*;
import blurock.coreobjects.*;
import java.io.IOException;
import java.text.DecimalFormat;
import graph.*;

/**
 *
 * @author  reaction
 */
public class BaseDataConsecutiveSeriesSet extends blurock.coreobjects.BaseDataSetOfObjects {
    
    public String topNode;
    public double bottomValue = 0.0;
    public double topValue = 0.0;
    int maxLevel = 5;
    public DefinedRegionLevel[] Levels = new DefinedRegionLevel[maxLevel];
    
    public DrawGraph Graph = new DrawGraph();
    /** Creates a new instance of BaseDataConsecutiveSeriesSet */
    public BaseDataConsecutiveSeriesSet() {
        Name = "ConsectutiveSeriesSet";
        Type = "ConsectutiveSeriesSet";
        Identification = 1;
    }
    
    public void Read(RWManager io) throws IOException {
        ReadAsOutput(io);
   }

   public void ReadAsOutput(RWManager io) throws IOException {
       while(!io.readNextLine().startsWith("Name(30803)"));
       
       String line = io.readNextLine();
       line = io.readNextLine();
       
       topNode = parseNodeName(line);
       System.out.println("Top: " + topNode);
       while(!line.startsWith("End:")) {
        System.out.println(line);
        String name = parseNodeName(line);
        
        System.out.println("NodeName: " + name);
        BaseDataConsecutiveSeries series = new BaseDataConsecutiveSeries();
        series.Name = name;
        System.out.println("Series: " + name);
        series.ReadAsOutput(name,io);
        
        String shortname = series.getSimpleNodeName();
        series.Name = shortname;
        System.out.println("Shortname: " + shortname);
        AddObject(series);
        DrawGraphNode dgnode = new DrawGraphNode(shortname);
        Graph.addNode(dgnode);
        System.out.println("Next: " + name);
        
        line = io.readNextLine();
        }
        try {
            System.out.println("topeNode: " + topNode);
            BaseDataConsecutiveSeries top = (BaseDataConsecutiveSeries) findObject(topNode);
            topValue = top.SubRegions[0].topValue;
            bottomValue = top.SubRegions[0].bottomValue;
         } catch (ObjectNotFoundException ex) {
            System.out.println("Top Node not found");
        }
        Object[] sets = setAsArray();
        for(int i=0;i<sets.length;i++) {
            BaseDataConsecutiveSeries series = (BaseDataConsecutiveSeries) sets[i];
            if(!series.Name.equals(topNode))
                addBondToGraph(series);
        }
   }
   
   String parseNodeName(String line) {
       StringTokenizer tok = new StringTokenizer(line);
       tok.nextToken();
       String name = tok.nextToken();
       return name;
   }
   public void ReadAsASCII(RWManager io) throws IOException {
        io.checkToken("Consecutive");
        io.checkToken("Sets:");
        String name = io.readElement();
        topNode = name; // first node is assumed to be the top
        boolean nottop = false;
        while(!(name.equals("END"))) {
            BaseDataConsecutiveSeries series = new BaseDataConsecutiveSeries();
            System.out.println("Series: " + name);
            series.ReadAsASCII(name,io);
            String shortname = series.getSimpleNodeName();
            series.Name = shortname;
            System.out.println("Shortname: " + shortname);
            AddObject(series);
            DrawGraphNode dgnode = new DrawGraphNode(shortname);
            Graph.addNode(dgnode);
            name = io.readElement();
            System.out.println("Next: " + name);
        }
        try {
            System.out.println("topeNode: " + topNode);
            BaseDataConsecutiveSeries top = (BaseDataConsecutiveSeries) findObject(topNode);
            topValue = top.SubRegions[0].topValue;
            bottomValue = top.SubRegions[0].bottomValue;
         } catch (ObjectNotFoundException ex) {
            System.out.println("Top Node not found");
        }
        Object[] sets = setAsArray();
        for(int i=0;i<sets.length;i++) {
            BaseDataConsecutiveSeries series = (BaseDataConsecutiveSeries) sets[i];
            if(!series.Name.equals(topNode))
                addBondToGraph(series);
        }
   }
    
    private void addBondToGraph(BaseDataConsecutiveSeries series) {
        String shortname = series.getSimpleNodeName();
        String shortparent = series.getSimpleParentName();
        Graph.addBond(shortparent,shortname);
    }
    public void Write(RWManager io) throws IOException {
        Object[] regionsets = setAsArray();
        Object[] regions = collectSubRegions(regionsets);
        java.util.Arrays.sort(regions,new DefinedRegionLevel());
        //for(int level=0;level<maxLevel;level++) {
            for(int i=0;i<regions.length;i++) {
                DefinedRegion r = (DefinedRegion) regions[i];
                int l = 1;
                while(l < r.Level) {
                    io.printString(": ");
                    l++;
                }
                r.Write(io);
            }
    io.printLine("");
    for(int lev=1;lev<20;lev++) {
        int levm1 = lev - 1;
        io.printLine("Level " + levm1 + " Divisions");
        int currentlevel = -1;
        int levelcount[] = new int[lev+1];
        for(int i=0;i<regions.length;i++) {
            DefinedRegion r = (DefinedRegion) regions[i];
            if(r.Level < lev) {
                if(r.Level > currentlevel) {
                    currentlevel = r.Level;
                    //levelcount[currentlevel] = 1;
                } else if(r.Level <= currentlevel) {
                    currentlevel = r.Level;
                    Double b = new Double(r.Bottom);
                    io.printLine(b.toString());
                }
            }
        }
    }
    }
    
    Object[] collectSubRegions(Object[] regionsets) {
           java.util.Vector vec = new java.util.Vector();
           for(int i=0;i<regionsets.length;i++) {
                BaseDataConsecutiveSeries r = (BaseDataConsecutiveSeries) regionsets[i];
                for(int j=0;j<r.SubRegions.length;j++) {
                if(r.SubRegions[j] != null) {
                    DefinedRegion dr = new DefinedRegion(r.Name,0,
                             r.SubRegions[j].topValue,r.SubRegions[j].bottomValue,
                             r.NumberOfLevels-1);
                    vec.add(dr);
                }
           }
        }
           return vec.toArray();
    }
    public String LevelsToString() {
        StringBuffer buf= new StringBuffer();
        for(int i=0;i<Levels.length;i++) {
            System.out.println(i);
            if(Levels[i] != null) {
                buf.append("Level: " + i + "\n");
                String s = Levels[i].divisionsAsString();
                buf.append(s);
                buf.append("\n");
            }
        }
        return buf.toString();
    }
    public void findLevelRegions() {
        try {
           int level = 0;
           BaseDataConsecutiveSeries series = (BaseDataConsecutiveSeries) findObject(topNode);
           DefinedRegionLevel l = series.regionLevelFromSeries();
           Levels[0] = l;
           findNextLevel(l,1);
        } catch(ObjectNotFoundException ex) {
            System.out.println(ex);
        }
    }
    private void findNextLevel(DefinedRegionLevel levels, int levelnum) throws ObjectNotFoundException {
        System.out.println("findNextLevel " + levelnum + "  " + levels.getSize());
        Object[] ls = levels.levelsAsArray();
        DefinedRegionLevel nextlevel = new DefinedRegionLevel();
        boolean more = false;
        for(int i=0;i<ls.length;i++) {
            System.out.println("DefinedRegion: " + i);
            DefinedRegion lregion = (DefinedRegion) ls[i];
            System.out.println("Name " + lregion.Name);
            BaseDataConsecutiveSeries series = (BaseDataConsecutiveSeries) findObject(lregion.Name);            
            
            Vector sons = Graph.getSons(lregion.Name);
            System.out.println(sons.size());
            if(sons.size() > 0) {
                for(int j=0;j<sons.size();j++) {
                    System.out.println(sons.get(j));
                    String name = (String) sons.get(j);
                    System.out.println("findNextLevel: son loop " + j + ": " + name);
                    BaseDataConsecutiveSeries sn = (BaseDataConsecutiveSeries) findObject(name);
            
                    DefinedRegionLevel sonlevel = sn.regionLevelFromSeries();
                    nextlevel.mergeLevels(sonlevel);
                }
            } else {
                nextlevel.AddObject(lregion);
            }
        }
        try {
            Levels[levelnum]= nextlevel;
        } catch ( java.lang.ArrayIndexOutOfBoundsException ex) {
            System.out.println("Array:  " +levelnum);
            throw new ObjectNotFoundException();
        }
        if(nextlevel.getSize() > 1)
            findNextLevel(nextlevel,levelnum+1);
    }
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man, DataObjectClass cls) {
        return new DBaseDataConsecutiveSeriesSet(man,this,cls);
    }    
}
