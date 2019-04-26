/*
 * ThermoNASAPoly.java
 *
 * Created on February 18, 2004, 10:22 PM
 */

package react.mechanisms.chemkin;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.StringTokenizer;
import java.io.IOException;

/**
 *
 * @author  reaction
 */
public class ThermoNASAPoly {
 
    ChemkinString lines = null;
    ArrayList molecules = null;

    public String name;
    
    public String atoms[] = new String[4];
    public int atomcnt[] = new int[4];
    public String phase;
    public double lowerT;
    public double upperT;
    public double middleT;
    
    public double[] lower = new double[7];
    public double[] upper = new double[7];
    
    /** Creates a new instance of ThermoNASAPoly */
    public ThermoNASAPoly() {
    }
    public ThermoNASAPoly(ChemkinString ls, ArrayList mols) {
       lines = ls;
       molecules = mols;
    }
    public void extractFormulaFromText(String formula) throws IOException {
        int count = 0;
        int index = 0;
        atoms = new String[4];
        atomcnt = new int[4];
        for(int i=0;i<4;i++) 
            atoms[i] = "";
        try {
            while(count < formula.length()) {
                System.out.println(count + ", " + formula);
                int nS = nextSymbol(formula, count);
                int nN = nextNumber(formula,nS+1);
                if(nS < count || nS == nN)
                    throw new IOException("Formula not correct: " + formula);
                Integer aI = new Integer(formula.substring(nS+1, nN+1));
                atomcnt[index] = aI.intValue();
                atoms[index] = formula.substring(count, nS+1);
                index++;
                count = nN +1;
            }
        } catch(NumberFormatException ex) {
            throw new IOException("Formula not correct: " + formula);
        }
    }
    boolean isNumber(char c) {
        return (c >= '1' && c <= '9') || c == '0';
    }
    int nextNumber(String text,int start) {
        int count = start;
        while(count < text.length() && isNumber(text.charAt(count))) {
            count++;
        }
        count--;
        return count;
    }
     int nextSymbol(String text,int start) {
        int count = start;
        while(count < text.length() && !isNumber(text.charAt(count))) {
            count++;
        }
        count--;
        return count;
    }
   
    public void parse(String l1, String l2, String l3, String l4) throws IOException {
        String namefield = l1.substring(0,23);
        StringTokenizer tok = new StringTokenizer(namefield," ");
        name = tok.nextToken();
        System.out.println(" Therm: " + name);
        atoms[0] = l1.substring(24,26).trim();
        atoms[1] = l1.substring(29,31).trim();
        atoms[2] = l1.substring(34,36).trim();
        atoms[3] = l1.substring(39,41).trim();
        
        try {
            atomcnt[0] = convertInt(l1.substring(26,29));
            atomcnt[1] = convertInt(l1.substring(31,34));
            atomcnt[2] = convertInt(l1.substring(36,39));
            atomcnt[3] = convertInt(l1.substring(41,44));
            
            phase = l1.substring(44,44);
            
            lowerT = parseDouble(l1.substring(45,54));
            upperT = parseDouble(l1.substring(55,64));
            middleT = parseDouble(l1.substring(65,74));
            upper[0] = parseDouble(l2.substring(0,14));
            upper[1] = parseDouble(l2.substring(15,29));
            upper[2] = parseDouble(l2.substring(30,44));
            upper[3] = parseDouble(l2.substring(45,59));
            upper[4] = parseDouble(l2.substring(60,74));
            upper[5] = parseDouble(l3.substring(0,14));
            upper[6] = parseDouble(l3.substring(15,29));
            lower[0] = parseDouble(l3.substring(30,44));
            lower[1] = parseDouble(l3.substring(45,59));
            lower[2] = parseDouble(l3.substring(60,74));
            lower[3] = parseDouble(l4.substring(0,14));
            lower[4] = parseDouble(l4.substring(15,29));
            lower[5] = parseDouble(l4.substring(30,44));
            lower[6] = parseDouble(l4.substring(45,59));
        } catch(NumberFormatException ex) {
            throw new IOException("Error in reading NASA polynomial: \n"
                    + ex.toString()
                    + "\n " + l1);
        }
    }
    int convertInt(String str) throws NumberFormatException {
        str = str.trim();
        int cnt = 0;
        if(str.length() != 0) {
            cnt = Integer.parseInt(str);
        }
        return cnt;
    }
    double parseDouble(String str) throws NumberFormatException {
        str = str.trim();
        double cnt = 0;
        if(str.length() != 0) {
            cnt = Double.parseDouble(str);
        }
        return cnt;
    }
    public String moleculeFormula() {
        StringBuffer formula = new StringBuffer();
        for(int i=0;i<4;i++) {
            if(atoms[i].length() > 0) {
                formula.append(atoms[i]);
                formula.append(atomcnt[i]);
            }
        }
        
        return formula.toString();
    }
    public String toString() {
        StringBuffer buf = new StringBuffer();
        Formatter f = new Formatter();
        buf.append(f.format("%-24s", name));
        
        for(int i=0;i<4;i++) {
            f = new Formatter();
            if(atoms[i].length() > 0)
                buf.append(f.format("%-3s%2d",atoms[i], atomcnt[i]));
            else 
                buf.append("     ");
        }
        buf.append("G");
        f = new Formatter();
        buf.append(f.format("%10.2f%10.2f%10.2f    1\n",lowerT,upperT,middleT));
        f = new Formatter();
        buf.append(f.format("%+15.8e%+15.8e%+15.8e%+15.8e%+15.8e    2\n", 
                upper[0], upper[1],upper[2],upper[3],upper[4]));
        f = new Formatter();
        buf.append(f.format("%+15.8e%+15.8e%+15.8e%+15.8e%+15.8e    3\n", 
                upper[5],upper[6],lower[0], lower[1], lower[2]));
        f = new Formatter();
        buf.append(f.format("%+15.8e%+15.8e%+15.8e%+15.8e                   4\n", 
                lower[3],lower[4],lower[5],lower[6]));
        return buf.toString();
    }
}
