/*
 * BaseDataConsectutiveSeries.java
 *
 * Created on November 5, 2003, 3:15 PM
 */

package blurock.Consectutive;
import java.util.*;
import blurock.core.*;
import blurock.coreobjects.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
 *
 * @author  reaction
 */
public class BaseDataConsecutiveSeries extends blurock.coreobjects.BaseDataSetOfObjects {
    
    public int NumberOfLevels;
    
    public String[] Levels;
    
    public int NumberOfSubRegions = 0;
    public ConsecutiveSubRegion[] SubRegions = new ConsecutiveSubRegion[5];
    /** Creates a new instance of BaseDataConsectutiveSeries */
    public BaseDataConsecutiveSeries() {
        Name = "ConsecutiveSeries";
        Type = "ConsecutiveSeries";
        Identification = 1;
    }
    
    public void Read(RWManager io) throws IOException {
        Name = io.readElement();
        ReadAsOutput(Name,io);       
    }
    public DefinedRegionLevel regionLevelFromSeries() {
        System.out.println("regionLevelFromSeries");
        DefinedRegionLevel level = new DefinedRegionLevel();
        for(int i=0;i<SubRegions.length;i++) {
            if(SubRegions[i] != null) {
                System.out.println("bottom=" + SubRegions[i].bottomValue + "  top= " + SubRegions[i].topValue );
                DefinedRegion region = new DefinedRegion(Name,i,
                        SubRegions[i].topValue,SubRegions[i].bottomValue,NumberOfLevels);
                level.AddObject(region);
            }
        }
        System.out.println(level.divisionsAsString());
        return level;
    }
    public void ReadAsOutput(String name, RWManager io) throws IOException  {
        System.out.println("ReadAsOutput");
        System.out.println(io.readNextLine());
        System.out.println(io.readNextLine());
        String line = io.readNextLine();
        System.out.println("Number of Elements Line:" + line);
        if(!line.startsWith("Number of Elements"))
            throw new IOException("Expecting Node information: got " + line);
        String numS = line.substring(35);
        System.out.println("Number of Elements" + numS);
        int elements = 0;
        try {
          elements = Integer.parseInt(numS);
        } catch(NumberFormatException exp) {
            throw new IOException("Expected an integer value got '" + name + "'");
        }
        String bottom = io.readNextLine();
        String top = io.readNextLine();
        int gaps = readGapsInOutput(io);

        ConsecutiveSubRegion subregion = new ConsecutiveSubRegion(top,bottom,elements,gaps,NumberOfLevels); 
        int sub = parseNodeName();
        System.out.println("Add SubRegion: "+ sub);
        SubRegions[sub-1] = subregion;
        if(sub>NumberOfSubRegions)
            NumberOfSubRegions = sub;
     }
    int readGapsInOutput(RWManager io) throws IOException {
        int count = 0;
        String line = io.readNextLineAbsolute();
        line = io.readNextLineAbsolute();
        while(line.length() > 0) {
            StringTokenizer tok = new StringTokenizer(line);
            while(tok.hasMoreTokens()) {
                String gap = tok.nextToken();
                count++;
            }
            line = io.readNextLineAbsolute();
        }
        return count;
    }
    public void ReadAsASCII(RWManager io) throws IOException {
        Name = io.readElement();
        ReadAsASCII(Name,io);
    }
    public void ReadAsASCII(String name, RWManager io) throws IOException {
        Name = name;
        io.checkToken("Top:");
        String top = io.readElement();
        io.checkToken("Bottom:");
        String bottom = io.readElement();
        io.checkToken("Elements:");
        int elements = io.readInteger();
        io.checkToken("Gaps:");
        int gaps = io.readInteger();
        ConsecutiveSubRegion subregion = new ConsecutiveSubRegion(top,bottom,elements,gaps,NumberOfLevels);
        int sub = parseNodeName();
        System.out.println("Add SubRegion: "+ sub);
        SubRegions[sub-1] = subregion;
        if(sub>NumberOfSubRegions)
            NumberOfSubRegions = sub;
    }
    public void Write(RWManager io) throws IOException {
        for(int i=0;i<SubRegions.length;i++) {
            if(SubRegions[i] != null)
                io.printLine(NumberOfLevels + ": " + SubRegions[i].nodeInfoName);
        }
    }

    int parseNodeName() {
        StringTokenizer tokdash = new StringTokenizer(Name,"-");
        String name = tokdash.nextToken();
        String elenumS = "001";
        if(tokdash.hasMoreElements())
            elenumS = tokdash.nextToken();
         StringTokenizer tok = new StringTokenizer(name,"#");
         NumberOfLevels = 0;
         Levels = new String[tok.countTokens()];
         while(tok.hasMoreElements()) {
             Levels[NumberOfLevels] = (String) tok.nextElement();
             NumberOfLevels++;
         }
         int elenum = 0;
         try {
            elenum = Integer.parseInt(elenumS);
         } catch(NumberFormatException ex) {
                System.out.println("SubRegion Number Illegal: "+ elenumS);
            }
         return elenum;
    }
    public String getSimpleNodeName() {
        return Levels[NumberOfLevels - 1];
    }
    public String getSimpleParentName() {
        if(NumberOfLevels > 1)
            return Levels[NumberOfLevels - 2];
        else
            return Levels[NumberOfLevels - 1];
    }
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataConsecutiveSeries(man,this,cls);
    }

}
