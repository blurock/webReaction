/*
 * ReadPSRFile.java
 *
 * Created on March 14, 2006, 10:44 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ignition.psr;
import java.io.*;
import java.util.Comparator;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;
/**
 *
 * @author reaction
 */
public class ReadPSRFile extends utilities.ReadFileToString 
                                implements Comparator {
    public double temperature = 0.0;
    public double pressure = 0.0;
    public String filename = new String();
    public String units = "";
    public Hashtable values = null;
    /** Creates a new instance of ReadPSRFile */
    public ReadPSRFile(File file) throws IOException {
     values = new Hashtable();
     filename = file.getParent();
     read(file);
     StringTokenizer lines = new StringTokenizer(outputString,"\n");
     units = lines.nextToken();
     String line = lines.nextToken();
     line.trim();
     System.out.println("Line: " + line);
     System.out.println(line.indexOf("End"));
     while(line.indexOf("End") < 0) {
         StringTokenizer linetok = new StringTokenizer(line,"=");
         if(linetok.countTokens() != 2) {
             throw new IOException("Illegal PSR line (no equals sign): '" + line + "'");
         }
         String species = linetok.nextToken();
         String value = linetok.nextToken();
         try {
            Double dvalue = new Double(value);
             values.put(species,dvalue);
         } catch(NumberFormatException ex) {
             throw new IOException("Illegal PSR line (number format exception): '" + line + "'");
         }
         line = lines.nextToken();
     }
     // The dashed lines
     System.out.println(lines.nextToken());
     
     try {
        // the temperature field
        String tempS = lines.nextToken();
        if(tempS.startsWith("Temperature")) {
            String tS = tempS.substring(17).trim();
            String ntS = tS.replace('D', 'E');
            System.out.println(ntS);
            Double dtemp = new Double(ntS);
            temperature = dtemp.doubleValue();
        } else {
             throw new IOException("Illegal PSR line (no temperature field): '" + line + "'");
        }
        System.out.println(lines.nextToken());
        String pressureS = lines.nextToken();
        if(pressureS.startsWith("Pressure")) {
            String pS = pressureS.substring(17);
            String npS = pS.replace('D', 'E');
            Double dpressure = new Double(npS);
            pressure = dpressure.doubleValue();
        }else {
             throw new IOException("Illegal PSR line (no pressure field): '" + line + "'");
        }
     } catch(NumberFormatException ex) {
         throw new IOException("Illegal PSR line (number format exception in temperature)");
     }
    }
    public int compare(Object obj1, Object obj2) {
        ReadPSRFile o1 = (ReadPSRFile) obj1;
        ReadPSRFile o2 = (ReadPSRFile) obj2;
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
        ReadPSRFile o = (ReadPSRFile) obj1;
        
        boolean ans = false;
        if(o != null) {
            if(o.temperature == temperature) 
                ans = true;
        } else {
            ans = false;
        }
        return ans;
    }
    public Double[] fillInValues(String[] names) {
        Double[] row = new Double[names.length];
        for(int i=0;i<names.length;i++) {
            String name = names[i];
            Double value = (Double) values.get(name);
            if(value != null) {
                row[i] = value;
            } else {
                row[i] = new Double(0.0);
            }
        }
        return row;
    }
    String[] getNames() {
        Enumeration keys = values.keys();
        int size = values.size();
        String[] names = new String[size];
        for(int i=0;i<size;i++) {
            String key = (String) keys.nextElement();
            names[i] = key;
        }
        return names;
    }
}
