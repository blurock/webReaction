/*
 * BaseDataCobwebClusterDescriptorPoint.java
 *
 * Created on October 14, 2003, 11:38 AM
 */

package blurock.CobwebCluster;
import java.util.*;
import blurock.core.*;
import blurock.coreobjects.*;
import java.io.IOException;
import java.text.DecimalFormat;
/**
 *
 * @author  reaction
 */
public class BaseDataCobwebClusterDescriptorPoint extends BaseDataObject {
    
    public double descriptorValue = 0;
    
    public double differenceValue = 0;
    String parameter;
    String partition;
    String op;
    String value;
    public String parameterDescription;
    /** Creates a new instance of BaseDataCobwebClusterDescriptorPoint */
    public BaseDataCobwebClusterDescriptorPoint() {
        Name = "CobwebClusterDescriptorPoint";
        Type = "CobwebClusterDescriptorPoint";
        Identification = 1;
    }
    public BaseDataCobwebClusterDescriptorPoint(String name, double descr, double diff) {
        Name = name;
        Type = "CobwebClusterDescriptorPoint";
        Identification = 1;
        descriptorValue = descr;
        differenceValue = diff;
        StringTokenizer tok = new StringTokenizer(Name,".");
        parameter = tok.nextToken();
        partition = tok.nextToken();
        String otoken = tok.nextToken();
        if(otoken.equals("LT"))
            op = new String("<");
        else if(otoken.equals("GT"))
            op = new String(">");
        else
            op = otoken;
        if(tok.countTokens() > 2) 
            value = tok.nextToken() + "." + tok.nextToken();
        else 
            value = tok.nextToken();
        String n  = parameter + " " + op + " " + value;
        String blanks = new String("                                                  ");
        parameterDescription = n + blanks.substring(1,50 - Name.length());
    }
      public void Read(RWManager io) throws IOException {
         }
 
    public void Write(RWManagerBase io) throws IOException {
        DecimalFormat dft = new DecimalFormat("   #####.###   ");
         String line = parameterDescription + "   " + dft.format(differenceValue) +  dft.format(descriptorValue);
        io.printLine(line);
    }
 
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataCobwebClusterDescriptorPoint(man,this,cls);
    }
   
}
