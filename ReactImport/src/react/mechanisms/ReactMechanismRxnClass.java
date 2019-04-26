/*
 * ReactMechanismRxnClass.java
 *
 * Created on February 3, 2001, 6:44 PM
 */

package react.mechanisms;
import react.reactions.*;
import utilities.StringNumber;
import java.util.*;
import utilities.ErrorFrame;
/**
 *
 * @author  reaction
 * @version 
 */
public class ReactMechanismRxnClass extends Object {

    public String className;
    
    public Vector reactions;
    ReactReactionConstants forwardConstants;
    ReactReactionConstants reverseConstants;
    
    /** Creates new ReactMechanismRxnClass */
    public ReactMechanismRxnClass() {
    }
    public Vector reactionsWithMoleculeAsReactant(String molname) {
        Vector rxns = new Vector();
        for(int i=0;i<reactions.size();i++) {
            ReactMechanismRxn rxn = (ReactMechanismRxn) reactions.elementAt(i);
            if(rxn.moleculeAsReactant(molname)) {
                rxns.add(rxn);
            }
        }
        return rxns;
    }
    public Vector reactionsWithMoleculeAsProduct(String molname) {
        Vector rxns = new Vector();
        for(int i=0;i<reactions.size();i++) {
            ReactMechanismRxn rxn = (ReactMechanismRxn) reactions.elementAt(i);
            if(rxn.moleculeAsProduct(molname)) {
                rxns.add(rxn);
            }
        }
        return rxns;
    }
    public boolean parse(StringTokenizer elements) {
        boolean success = true;
        reactions = new Vector();
        try {
            //String clsname = elements.nextToken();
            //System.out.println("ClassName: " + clsname);
            boolean notdone = true;
            while(notdone) {
                String rxn = elements.nextToken();
                if(rxn.equals("END")) 
                    notdone = false;
                else {
                    ReactMechanismRxn r = new ReactMechanismRxn();
                    success = r.parse(rxn);
                    reactions.add(r);
                }
            }
        } catch (NoSuchElementException ios) {
            System.out.println("Error in parsing Reaction Tokens");
            success = false;
        } catch(Exception ios) {
            System.out.println("Error in parsing Reaction");
            success = false;
        }
        return success;
    }
    public boolean parseCoeffs(String line) {
        boolean success = true;
        System.out.println("parseCoeffs " + line);
        StringTokenizer tokens = new StringTokenizer(line);
        try {
        className = tokens.nextToken();
        String e = tokens.nextToken();
        StringNumber af = new StringNumber(tokens.nextToken());
        StringNumber nf = new StringNumber(tokens.nextToken());
        StringNumber ef = new StringNumber(tokens.nextToken());
        StringNumber ar = new StringNumber(tokens.nextToken());
        StringNumber nr = new StringNumber(tokens.nextToken());
        StringNumber er = new StringNumber(tokens.nextToken());
        forwardConstants = new ReactReactionConstants(
                        af.doubleValue(),
                        nf.doubleValue(),
                        ef.doubleValue(),
                        1.0,true);
        reverseConstants = new ReactReactionConstants(
                        ar.doubleValue(),
                        nr.doubleValue(),
                        er.doubleValue(),
                        1.0,false);
        } catch (NoSuchElementException ios) { 
            ErrorFrame fr = new ErrorFrame("Error in parsing Coefficients:\n" + line);
            System.out.println("Error in parsing Coefficients:\n" + line);
            fr.show();
            success = false;
        }
        return success;
    }
    public String writeCoeffs() {
        StringBuffer str = new StringBuffer();
        str.append(className + " = ");
        str.append(forwardConstants.aFactor + " ");
        str.append(forwardConstants.nFactor + " ");
        str.append(forwardConstants.eFactor + " ");
        str.append(reverseConstants.aFactor + " ");
        str.append(reverseConstants.nFactor + " ");
        str.append(reverseConstants.eFactor + "\n");
        return str.toString();
    }
    public String write() {
        StringBuffer str = new StringBuffer();
        str.append("REACTIONCLASS = " + className + "\n");
        for(int i=0;i<reactions.size();i++) {
            ReactMechanismRxn rxncls = (ReactMechanismRxn) reactions.elementAt(i);
            String line = rxncls.write();
            str.append(line);
        }
        str.append("END\n");
        return str.toString();
    }
    
}
