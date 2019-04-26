/*
 * SingleFlowFile.java
 *
 * Created on October 21, 2002, 5:47 PM
 */

package react.mechanisms.flow;
import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author  reaction
 */
public class SingleFlowFile extends utilities.ReadFileToString {
    
    SingleFlow[] flows = null;
    String species = null;
    /** Creates a new instance of SingleFlowFile */
    public SingleFlowFile(String name, String dir, boolean consumption) throws IOException {
        species = name;
        String fname;
        if(consumption)
            fname = new String(name + ".con");
        else 
            fname = new String(name + ".for");
        String nfname = convertFileName(fname);
        File file = new File(dir,nfname);
        System.out.println("File: " + file);
        read(file);
        parseString();
    }
    private String convertFileName(String name) {
        StringBuffer buf = new StringBuffer();
        for(int i=0; i< name.length();i++) {
            if(name.charAt(i) == '*')
                buf.append("\\*");
            else
                buf.append(name.charAt(i));
        }
        return buf.toString();
    }
    public String getSpeciesName() {
        return species;
    }
    public SingleFlow[] getFlow() {
        return flows;
    }
    private void parseString() throws IOException {
        try {
            String line;
            if(outputString != null) {
                StringTokenizer tok = new StringTokenizer(outputString,"\n");
                int num = tok.countTokens();
                flows = new SingleFlow[num];
                for(int i=0;i<num;i++) {
                    line = tok.nextToken();
                    StringTokenizer linetok = new StringTokenizer(line,"\t");
                    String name = linetok.nextToken().trim();
                    String absS = linetok.nextToken().trim();
                    String relS = linetok.nextToken().trim();
                    double absV = Double.parseDouble(absS);
                    double relV = Double.parseDouble(relS) * 100.0;
                    flows[i] = new SingleFlow(species,name,absV,relV);
                }
            }
            } catch(NumberFormatException io) {
                throw new IOException("Error in reading " + species + " flow file\n" + io);
            } catch(IndexOutOfBoundsException io) {
                throw new IOException("Error in reading " + species + " flow file\n" + io);
            } catch(NoSuchElementException io) {
                throw new IOException("Error in reading " + species + " flow file\n" + io);
            }
   
    }
}
