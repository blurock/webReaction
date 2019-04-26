/*
 * ReadIgnitionOutput.java
 *
 * Created on May 7, 2003, 11:57 AM
 */

package ignition.times;
import utilities.ErrorFrame;

import java.io.*;
import java.util.Comparator;
import java.text.DecimalFormat;
/**
 *
 * @author  reaction
 */
public class ReadIgnitionOutput extends utilities.ReadFileToString 
                                implements Comparator {
    String tempString              = "Temperature";
    int tempIndex           = 0;
    public double temperature      = 0.0;
    String  OHString        = "t([OH] Max)";
    int OHIndex               = 0;
    public double OHValue            = 0.0;
    String  CO2String       = "t([CO2*] Max)";
    int CO2Index            = 0;
    public double CO2Value         = 0.0;
     String  pString        = "t(p_PrimeMax)";
    int pIndex              = 0;
    public double pValue           = 0.0;
     String  tString        = "t(t_PrimeMax)";
    int tIndex              = 0;
    public double tValue           = 0.0;
     String kamString       = "t(F Kamenetzki)";
    int kamIndex            = 0;
    public double kamValue         = 0.0;
    String pattern = "#####0.0000000000";
    
    IgnitionTimesOptions options;
    
    /** Creates a new instance of ReadIgnitionOutput */
    public ReadIgnitionOutput(File file,IgnitionTimesOptions opt) throws NumberFormatException {
        read(file);
        options = opt;
        System.out.println("Index: 1 ");
        tempIndex     = outputString.indexOf(options.temperature.getMatchingString());
                                            
        System.out.println("Index: 2 " + tempIndex);
        OHIndex       = outputString.indexOf(options.ohvalue.getMatchingString());
        System.out.println("Index: 3 " + OHIndex);
        CO2Index     = outputString.indexOf(options.co2value.getMatchingString());
        System.out.println("Index: 4 " + CO2Index);
        pIndex        = outputString.indexOf(options.pressure.getMatchingString());
        System.out.println("Index: 5 " + pIndex);
        tIndex        = outputString.indexOf(options.tprime.getMatchingString());
        System.out.println("Index: 6 " + tIndex);
        kamIndex      = outputString.indexOf(options.kamenetzki.getMatchingString());
        System.out.println("Index: 7 " + kamIndex);
        
            temperature = getTemperature(tempIndex,options.temperature);
            System.out.println("Index: 8 ");
            System.out.println("temperature       = " + temperature);
        
            OHValue    = IgnTime(OHIndex,options.ohvalue);
            System.out.println("t([OH]_max)       = " + OHValue);

            CO2Value   = IgnTime(CO2Index,options.co2value);
            System.out.println("t([CO2*]_max)     = " + CO2Value);

            pValue     = IgnTime(pIndex,options.pressure);
            System.out.println("t(p_prime_max)    = " + pValue);

            tValue     = IgnTime(tIndex,options.tprime);
            System.out.println("t(t_prime_max)    = " + tValue);

            kamValue   = IgnTime(kamIndex,options.kamenetzki);
            System.out.println("t(F Kamenetzki)   = " + kamValue);

   }
    private double IgnTime(int index, IsolateValue isolate) throws NumberFormatException {
        double value = 0.0;
        int offset = isolate.getOffset();
        int length = isolate.getLength();
        int begIndex = index + offset;
        int endIndex = begIndex + length;
            System.out.println("offset: " + offset);
            System.out.println("length: " + length);
            System.out.println("begIndex: " + begIndex);
            System.out.println("endIndex: " + endIndex);

        try { 
            String orig = outputString.substring(begIndex,endIndex);
            String substring = orig.replace('D','E');
            System.out.println("Index: Time " + substring);
            value = Double.parseDouble(substring);
            value = value * 1000.0;
        } catch(NumberFormatException ex) {
            ErrorFrame fr = new ErrorFrame("Error in reading " + isolate.getName() +
            "String to match ='" + isolate.getMatchingString() + "' " +
            "  offset = " + isolate.getOffset() + " Field Length = " + isolate.getLength());
            fr.setVisible(true);
            throw new NumberFormatException("Error in reading " + isolate.getName());
        } catch(java.lang.ArrayIndexOutOfBoundsException ex) {
            throw new NumberFormatException("Index out of bounds\n");
        }
        
        return value;
    }
    private double getTemperature(int index,IsolateValue isolate) {
        int offset = isolate.getOffset();
        int length = isolate.getLength();
        int begIndex = index + offset;
        int endIndex = begIndex + length;
        String substring = outputString.substring(begIndex,endIndex);
                System.out.println("Index: Temperature " + substring);

        double value = Double.parseDouble(substring);
        return value;
    }
    public String stringStandardLine() {
        StringBuffer buf = new StringBuffer();
        appendDouble(temperature,buf);
        appendDouble(1000.0/temperature,buf);
        appendDouble(kamValue,buf);
        appendDouble(OHValue,buf);
        appendDouble(CO2Value,buf);
        appendDouble(pValue,buf);
        appendDouble(tValue,buf);
        buf.append("\n");
        return buf.toString();
    }
    private void appendDouble(double value, StringBuffer buf) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        System.out.println("append: " + value + ", " + output + ", " + pattern);
        buf.append(output);
        buf.append("\t");
    }
    public int compare(Object obj1, Object obj2) {
        ReadIgnitionOutput o1 = (ReadIgnitionOutput) obj1;
        ReadIgnitionOutput o2 = (ReadIgnitionOutput) obj2;
        int ans = 0;
        if(o1 != null || o2 != null)  {
        if(o1.temperature > o2.temperature) {
            ans = 1;
        } else if(o1.temperature < o2.temperature) {
            ans = -1;
        }
    } else {
            ans = 0;
    }
        return ans;
    }
    public boolean equals(Object obj1) {
        ReadIgnitionOutput o = (ReadIgnitionOutput) obj1;
        
        boolean ans = false;
        if(o != null) {
            if(o.temperature == temperature) 
                ans = true;
        } else {
            ans = false;
        }
        return ans;
    }
    public String toString() {
        return stringStandardLine();
    }
}
