/*
 * Class.java
 *
 * Created on February 17, 2004, 8:45 PM
 */

package react.mechanisms.chemkin;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;
/**
 *
 * @author  reaction
 */
public class ReadChemkinMechanism {
    
    public ArrayList Molecules = new ArrayList();
    public ArrayList Reactions = new ArrayList();
    public ArrayList Elements = new ArrayList();
    public ArrayList Thermo = new ArrayList();
    
    ChemkinString lines;
    String commentString = "%";
    MechanismReadOptions options;
    String forward    = ">";
    String reversible = "=";
    
    /** Creates a new instance of Class */
    public ReadChemkinMechanism(String input,MechanismReadOptions opt) {
        options = opt;
        forward    = (String) options.forwardCombo.getSelectedItem();
        reversible = (String) options.reversibleCombo.getSelectedItem();
        commentString = (String) options.commentCharCombo.getSelectedItem();
        lines = new ChemkinString(input,commentString);
    }
 
    public void parse() throws IOException {
       parseMechanism();
    }
    
    private void parseMechanism() throws IOException {
        if(options.includeElements.isSelected()) {
            parseElements();
        }
        if(options.includeSpecies.isSelected()) {
            parseMolecules();
        }
        if(options.includeThermo.isSelected()) {
            parseThermo();
        }
        if(options.includeReactions.isSelected()) {
            parseReactions();
        }
    }
    private void parseElements() throws IOException {
        String first = lines.nextToken();
        System.out.println(first);
        if(lines.tokenMatch(first,"ELEMENTS")) {
        String next = lines.nextToken();
            while(!lines.tokenMatch(next,"END")) {
                StringTokenizer tok = new StringTokenizer(next," ");
                while(tok.hasMoreElements()) {
                    String ele = tok.nextToken();
                    System.out.println(ele);
                    Elements.add(ele);
                }
                next = lines.nextToken();
             }
        } else {
            throw new IOException("Expected SPECIES, got " + first);
        }
        
    }
    private void parseThermo() throws IOException {
        String first = lines.nextToken();
        System.out.println(first);
        if(lines.tokenMatch(first,"THERMO")) {
        String temp = lines.nextToken();
        if(!temp.startsWith("END")) {
          System.out.println(temp);
          String next = lines.nextToken();
            while(!lines.tokenMatch(next,"END")) {
                System.out.println(next);
                    String l2 = lines.nextToken();
                    String l3 = lines.nextToken();
                    String l4 = lines.nextToken();
                    ThermoNASAPoly thm = new ThermoNASAPoly(lines,Molecules);
                    thm.parse(next,l2,l3,l4);
                    Thermo.add(thm);
                    
                next = lines.nextToken();
             }
        }
        } else {
            throw new IOException("Expected THERMO, got " + first);
        }
    }
        private void parseMolecules() throws IOException {
        String first = lines.nextToken();
        System.out.println(first);
        if(lines.tokenMatch(first,"SPECIES")) {
        String next = lines.nextToken();
            while(!lines.tokenMatch(next,"END")) {
                StringTokenizer tok = new StringTokenizer(next," ");
                while(tok.hasMoreElements()) {
                    String ele = tok.nextToken();
                    System.out.println(ele);
                    Molecules.add(ele);
                }
                next = lines.nextToken();
             }
        } else {
            throw new IOException("Expected SPECIES, got " + first);
        }
        
    }
        private void parseReactions() throws IOException{
        String first = lines.nextToken();
        System.out.println(first);
        boolean markDuplicate = false;
        if(lines.tokenMatch(first,"REACTIONS")) {
            boolean done = false;
            ChemkinReaction lastrxn = null;
            String next = lines.nextToken();
            while(!done) {
                if(next.toUpperCase().startsWith("DUPLICATE")) {
                    if(options.duplicateCheck.isSelected()) {
                        if(lastrxn != null)
                            lastrxn.markedAsDuplicate = true;
                        else 
                            throw new IOException("No corresponding Duplicate Reaction");
                    } else {
                        markDuplicate = true;
                    }
                    next = lines.nextToken();
                }
                System.out.println(next);
                if(next.toUpperCase().startsWith("END")) {
                    done = true;
                    System.out.println("END of Reactions");
                } else {
                   ChemkinReaction rxn = new ChemkinReaction(lines,Molecules,commentString);
                   next = rxn.parse(next,forward,reversible);
                   if(markDuplicate)
                       rxn.markedAsDuplicate = true;
                   markDuplicate = false;
                   lastrxn = rxn;
                }
            if(!done)
                next = lines.nextToken();
            }
        } else {
            throw new IOException("Expected REACTIONS, got " + first);
        }
        }
}
