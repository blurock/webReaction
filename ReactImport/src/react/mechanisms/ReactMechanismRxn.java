/*
 * ReactMechanismRxn.java
 *
 * Created on February 3, 2001, 7:55 PM
 */

package react.mechanisms;
import java.util.*;
import utilities.ErrorFrame;

/**
 *
 * @author  reaction
 * @version 
 */
public class ReactMechanismRxn extends Object {

    public String[] reactantMolecules;
    
    public String[] productMolecules;
    
    public int productMultiplicity = 1;
    
    public int reactantMultiplicity = 1;
    
    public int numReactants = 0;
    
    public int numProducts = 0;
    
    public int maxMolecules = 10;
    
    /** Creates new ReactMechanismRxn */
    public ReactMechanismRxn() {
        reactantMolecules = new String[maxMolecules];
        productMolecules = new String[maxMolecules];
    }
    public boolean moleculeAsReactant(String molname) {
        int cnt = 0;
        boolean found = false;
        boolean notdone = reactantMolecules.length > 0;
        while(notdone) {
            if(reactantMolecules[cnt++].equals(molname)) {
                found = true;
                notdone = false;
            }
        }
        return found;
    }
    public boolean moleculeAsProduct(String molname) {
        int cnt = 0;
        boolean found = false;
        boolean notdone = productMolecules.length > 0;
        while(notdone) {
            if(productMolecules[cnt++].equals(molname)) {
                found = true;
                notdone = false;
            }
        }
        return found;
    }
    public boolean parse(java.lang.String line) {
        boolean success = true;
        StringTokenizer elements = new StringTokenizer(line);
        try {
            String mult = elements.nextToken();
            reactantMultiplicity = Integer.parseInt(mult);
            boolean notdone = true;
            while(notdone) {
                String name = elements.nextToken();
                String ptoken = elements.nextToken();
                
                 reactantMolecules[numReactants] = isolateName(name);
                 numReactants++;
                 if(ptoken.equals("=")) 
                       notdone = false; 
            }
            notdone = true;
            while(notdone) {
                 String name = elements.nextToken();
                 productMolecules[numProducts] = isolateName(name);
                 numProducts++;
                 if(!elements.hasMoreTokens())
                     notdone = false;
                 else 
                     name = elements.nextToken();
            }
        } catch (NoSuchElementException ios) {
            ErrorFrame fr = new ErrorFrame("Parse Error: " + line);
            success = false;
        }
        return success;
    }
    private String isolateName(String name) {
        return name.substring(1,name.length()-1);
    }
    public String writeAsLine() {
         StringBuffer str = new StringBuffer();
       for(int i = 0; i<numReactants;i++) {
            if(i>0)
                str.append("+");
            str.append(reactantMolecules[i]);
        }
        str.append("=");
        for(int j = 0; j<numProducts;j++) {
            if(j>0)
                str.append("+");
            str.append(productMolecules[j]);
        }
        return str.toString();
    }
    public String write() {
        StringBuffer str = new StringBuffer();
        str.append(Integer.toString(reactantMultiplicity) + " ");
        for(int i = 0; i<numReactants;i++) {
            if(i>0)
                str.append(" + ");
            str.append(" {" + reactantMolecules[i] + "} ");
        }
        str.append(" = ");
        for(int j = 0; j<numReactants;j++) {
            if(j>0)
                str.append(" + ");
            str.append(" {" + productMolecules[j] + "} ");
        }
        str.append("\n");
        return str.toString();
    }
    
}
