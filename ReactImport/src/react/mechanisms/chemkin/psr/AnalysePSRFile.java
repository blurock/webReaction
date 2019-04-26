/*
 * AnalysePSRFile.java
 *
 * Created on October 22, 2006, 10:47 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package react.mechanisms.chemkin.psr;

import java.util.StringTokenizer;
import utilities.FileToString;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import utilities.ReadFileToString;

/**
 *
 * @author reaction
 */
public class AnalysePSRFile {
    
    String startKeyword = "TWOPNT:  FINAL SOLUTION:";
    String temperature  = "TEMPERATURE";
    String header       = "EXIT MOLE FRACTIONS";

    Vector namesV         = new Vector();
    Vector molefractionsV = new Vector();
    
    String[] namesS = null;
    Double[] molefractionsD = null;
    public double Temperature;
    
    /** Creates a new instance of AnalysePSRFile */
    public AnalysePSRFile(File file) throws IOException {
        ReadFileToString str = new ReadFileToString();
        str.read(file);
        StringTokenizer tok = new StringTokenizer(str.outputString,"\n");
        boolean notfound = true;
        int count = 4;
        while(notfound && tok.hasMoreTokens()) {
            String line = tok.nextToken();
            if(line.indexOf(startKeyword) >= 0) 
                count--;
            if(count == 0)
                notfound = false;
        }
        if(!notfound) {
            Temperature = getTemperature(tok);
            getMoleFractionHeader(tok);
            getMoleFractions(tok);
            int n = namesV.size();
            namesS = new String[n];
            molefractionsD = new Double[n];
            for(int i=0;i<n;i++) {
                namesS[i] = (String) namesV.elementAt(i);
                molefractionsD[i] = (Double) molefractionsV.elementAt(i);
            }
        } else {
            throw new IOException("PSR file incomplete: Begin not found in \n " + file.toString() );
        }
        
    }
    public String[] getNames() {
        return namesS;
    }
    public Vector getValues() {
        Vector molfs = new Vector(molefractionsD.length);
        for(int i=0;i<molefractionsD.length;i++) {
            molfs.add(i,molefractionsD[i]);
        }
        return molfs;
    }
    void getMoleFractions(StringTokenizer tok) throws IOException {
        boolean notdone = true;
        double temp = 0.0;
        String line = tok.nextToken();
        int count = 0;
        while(notdone) {
            count = 3;
            while(count < line.length()) {
                    StringTokenizer linetok = new StringTokenizer(line.substring(count,count+21)," =");
                    if(linetok.countTokens() >= 2) {
                    String name    = linetok.nextToken();
                    String valueS  = linetok.nextToken();
                    try {
                        Double valueD   = new Double(valueS);
                        double value    = valueD.doubleValue();
                        namesV.add(name);
                        molefractionsV.add(valueD);
                        count += 27;
                    } catch(NumberFormatException ex) {
                        throw new IOException("Error in Molefraction Value: " + valueS);
                    }
                } else {
                    throw new IOException("Error in Molefractions: " + line);
                }
            }
            line = tok.nextToken();
            int index = line.indexOf('=');
            if(index < 0 )
                notdone = false;
        }
    }
    void getMoleFractionHeader(StringTokenizer tok) throws IOException {
        boolean notdone = true;
        int count = 0;
        String name;
        while(notdone && count < 10) {
            name = tok.nextToken();
            int index = name.indexOf(header);
            if(index >= 0) {
                notdone = false;
            }
        }
    }
    double getTemperature(StringTokenizer tok) throws IOException {
        boolean notdone = true;
        double temp = 0.0;
        String name;
        int count = 0;
        while(notdone && count < 10) {
            name = tok.nextToken();
            int index = name.indexOf(temperature);
            if(index >= 0) {
                int i = index + 29;
                String numberS = name.substring(i, i+10);
                try {
                    Double numberD = new Double(numberS);
                    temp = numberD.doubleValue();
                    notdone = false;
                } catch(NumberFormatException ex) {
                    throw new IOException("TEMPERATURE double field not found");
                }
            }
        }
        if(notdone) 
            throw new IOException("TEMPERATURE field not found");
        return temp;
    }
}
