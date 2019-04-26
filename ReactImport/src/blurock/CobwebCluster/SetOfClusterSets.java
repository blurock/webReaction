/*
 * SetOfClusterSets.java
 *
 * Created on September 3, 2004, 11:02 AM
 */

package blurock.CobwebCluster;
import java.util.ArrayList;
import blurock.core.RWManagerBase;
import blurock.core.RWManager;
import java.io.*;
import java.util.StringTokenizer;
import java.lang.Integer;
import blurock.coreobjects.DataSetOfObjectsClass;
import java.util.Vector;
import blurock.Consectutive.DefinedRegion;
import blurock.CobwebCluster.RegionsOfNode;
import blurock.CobwebCluster.SetOfRegionsOfLevel;

 /*
 * @author  reaction
 */
/** This class holds the set of <B>ClusterNodeDataSet</B> classes
 *
 * This manages the entire set of points, regions, cluster sets, etc.
 *
 * This class is also used to reorganize the data into different viewpoints:
 *
 */
public class SetOfClusterSets {
    ArrayList Set = new ArrayList();
    /** Creates a new instance of SetOfClusterSets by reading it
     * into from a file
     * @param name The name of the file from which to read the cluster data
     * @param cls The set of registered classes
     * @throws IOException If there is an error in reading, this class
     * throws an exception
     */
    public SetOfClusterSets(String name, DataSetOfObjectsClass cls)  throws IOException {
        
        RWManager reader = new RWManager(cls);
        reader.openManager(name,true);
        read(reader);
    }
     /** This reads from an string/file source
      * @param reader The source of the ASCII data
      * @throws IOException An exception is thrown if there is an error
      */    
    public SetOfClusterSets(RWManagerBase reader) throws IOException {
        read(reader);
    }
    /** This source of the set of <B>ClusterNodeData</B> points is transfered
     * to the class
     * @param datapoints The set of points to transfer to class
     */    
    public SetOfClusterSets(ArrayList datapoints) {
        for(int i=0;i<datapoints.size();i++) {
            ClusterNodeData data = (ClusterNodeData) datapoints.get(i);
            addToSet(data);
        }
    }
    /** The top level ASCII read procedure
     * @param reader The ASCII source
     * @throws IOException an exception is thrown if error
     */    
    private void read(RWManagerBase reader) throws IOException {
        while(!reader.readNextLine().startsWith("ClusterLevelValues:"));

        String line = reader.readNextLine();
        ClusterNodeData data;
        line = reader.readNextLine();
        while(line.startsWith("Instance:")) {
            data = parseNodeLine(line);
            System.out.println(data.toString());
            addToSet(data);
            line = reader.readNextLine();
             }
    }
    /** Parses a single line to a <B>ClusterNodeData</B>
     * @param line The line to parse
     * @throws IOException if error
     * @return The parsed node information
     */    
    private ClusterNodeData parseNodeLine(String line) throws IOException {
        ClusterNodeData data = new ClusterNodeData();
        StringTokenizer linetok = new StringTokenizer(line);
        String instkey = linetok.nextToken();
        String conditionsS = null;
        String levelS = null;
        String nodeS = null;
        boolean result = true;
        if(instkey.startsWith("Instance:")) {
            conditionsS = linetok.nextToken();
            String valuekeyS = linetok.nextToken();
            if(valuekeyS.startsWith("Value:")) {
                levelS = linetok.nextToken();
                String equalS = linetok.nextToken();
                if(equalS.startsWith("=")) {
                    nodeS = linetok.nextToken();
                } else result = false;
            } else result = false;
        } else result = false;
        if(result) {
            parseConditions(data,conditionsS);
            parseLevel(data,levelS);
            data.Name = nodeS;
        } else {
            data = null;
        }
         return data;
    }
    /** Isolates the conditions
     * @param data The class to fill in the conditions to
     * @param line The ASCII string to parse
     * @throws IOException if unexpectted keys
     */    
    private void parseConditions(ClusterNodeData data,String line) throws IOException {
        StringTokenizer tok = new StringTokenizer(line,":");
        if(tok.hasMoreTokens()) {
            String name = tok.nextToken();
            StringTokenizer nametok = new StringTokenizer(name,"-");
            data.Name = nametok.nextToken();
            int n = nametok.countTokens();
            System.out.println("Conditions: " + data.Name + ": " + n);
            data.values = new double[n+2];
            for(int i=0;i<n;i++) {
                try {
                    data.values[i+1] = Double.valueOf(nametok.nextToken());
                } catch(NumberFormatException ex)  {
                    
                }
            }
            data.values[n+1] = 0;
            data.levelIndex = n+1;
        }
        if(tok.hasMoreTokens()) {
            
            double regionD;
            String value = tok.nextToken();
            int pos = value.indexOf("[");
            if(pos > 0) {
                try {
                    String regionS = value.substring(pos+1, value.length()-2);
                    regionD = Double.valueOf(regionS);
                    data.values[0] = regionD;
                } catch(NumberFormatException ex) {
                    System.out.println("Not a region: (" + pos + ") " + value );
                    data.values[0] = 0.0;
                }
            } else {
                System.out.println("Region not found:" + value);
            }
        }
    }
    /** Parses the level information
     * @param data The class to fill in
     * @param levelS The level information to parse
     * @throws IOException if an error
     */    
    private void parseLevel(ClusterNodeData data, String levelS) throws  IOException {
        try {
            String l = levelS.substring(22);
            int level = Integer.parseInt(l);
            data.values[data.levelIndex] = level;
            System.out.println("Level: " + data.Name + ":" + level);
        } catch(NumberFormatException num) {
            throw new IOException("Number Exception (Level Parse): " + levelS);
        }
    }

    /** Adds a data point to the proper set
     * @param data The data point to add
     * @return true if successful
     */    
    public boolean addToSet(ClusterNodeData data) {
        boolean notfound = true;
        int count = 0;
        ClusterNodeDataSet set = null;
        while(notfound && count < Set.size()) {
            set = (ClusterNodeDataSet) Set.get(count);
            notfound = !set.belongsToSet(data);
            if(notfound) count++;
        }
        if(notfound) {
            System.out.println("New Set: " + data.values[data.regionIndex]);
            set = new ClusterNodeDataSet();
            set.transferConditions(data);
            Set.add(count,set);
        }
        set.addToSet(data);
        return notfound;
    }
    /** This loops through all the sets and finds all the
     * cluster node regions.
     */    
    public void findRegions() {
        for(int i=0;i<Set.size();i++) {
            ClusterNodeDataSet set = (ClusterNodeDataSet) Set.get(i);
            set.findRegions();
        }
    }
    /** This finds all the sets from a given level
     * @param level The level to find
     * @return The set of all sets at this level
     */    
    private ArrayList findLevel(int level) {
       ArrayList levellist = new ArrayList();
       System.out.println("Search for Level: " + level + ": number of sets: " + Set.size() );
       double levelD = level;
       for(int i=0;i<Set.size();i++) {
           ClusterNodeDataSet set = (ClusterNodeDataSet) Set.get(i);
           System.out.println(set.toString());
           if(set.values != null) {
               System.out.println(set.values[set.levelIndex] + "?=" + levelD);
            if(set.values[set.levelIndex] == levelD) {
                System.out.println("Found level " + level + ": " + i);
               levellist.add(set);
            }
           }
       }
       return levellist;
    }
    /** This finds all the nodes of a given level
     *
     * From the list of condition sets of a given level,
     * this isolates the list of unique node names
     * @param level The level to find
     * @return The names of all the nodes at this level
     */    
    public String[] listOfNodesForLevel(int level) {
        ArrayList nameset = new ArrayList();
        ArrayList levellist = findLevel(level);
        for(int l=0;l<levellist.size();l++) {
            ClusterNodeDataSet set = (ClusterNodeDataSet) levellist.get(l);
            String[] lnames = set.listOfNodes();
            for(int i=0;i<lnames.length;i++) {
                if(!nameset.contains(lnames[i])) {
                    nameset.add(lnames[i]);
                }
            }
        }
        String[] names = new String[nameset.size()];
        for(int i=0;i<nameset.size();i++) {
            names[i] = (String) nameset.get(i);
        }
        return names;
    }
    /**
     * @param name
     * @param level
     * @return  */    
    private RegionsOfNode regionsOfNode(String name, int level) {
        return new RegionsOfNode(level, name, Set);
    }
    /** This organizes (throught the <B>SetOfRegionsOfLevel</B> class,
     * the nodes according to first level, and then node name.
     * @return The collected levels
     */    
    public SetOfRegionsOfLevel LevelRegions() {
        SetOfRegionsOfLevel set = new SetOfRegionsOfLevel();
        for(int i=0;i<20;i++) {
            RegionsOfLevel lset = RegionsOfLevel(i);
            if(lset != null)
                set.addLevelSet(lset);
        }
        return set;
    }
    /** This collects the regions of one level collected
     * accordng to node names
     * @param level The level to isolate regions
     * @return The set of regions having that level collected together by
     * node names (<B>RegionsOfLevel</B> as a collection of <B>RegionsOfNode</B>
     */    
    public RegionsOfLevel RegionsOfLevel(int level) {
        String[] nodes = listOfNodesForLevel(level);
        RegionsOfLevel levregions = null;
        if(nodes.length > 0) {
            ArrayList levelnodes = new ArrayList();
            for(int i=0;i<nodes.length;i++) {
                RegionsOfNode reg = regionsOfNode(nodes[i],level);
                levelnodes.add(reg);
            }
            levregions = new RegionsOfLevel(levelnodes,level);
        }
        return levregions;
    }
    /**
     * @return  */    
    public String toString() {
        StringBuffer buf = new StringBuffer();
        boolean notitle = true;
        for(int level=0; level < 10 ;level++) {
            ArrayList levellist = findLevel(level);
            Object[] levelarr = levellist.toArray();
            for(int i=0; i<levelarr.length;i++) {
                ClusterNodeDataSet set = (ClusterNodeDataSet) levelarr[i];
                 if(notitle)
                        buf.append("Level " + level + ":-------------------------------------------------------\n");
                  notitle = false;
                  String divs = set.toString();
                  buf.append(divs);
                }
            }
        SetOfRegionsOfLevel sets = LevelRegions();
        buf.append(sets.toString());
        return buf.toString();
    }
}
