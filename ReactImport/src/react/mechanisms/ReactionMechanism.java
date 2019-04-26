/*
 * ReactionMechanism.java
 *
 * Created on February 3, 2001, 6:40 PM
 */

package react.mechanisms;
import java.util.Vector;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import utilities.ErrorFrame;
import java.util.Hashtable;
import java.io.*;
import react.molecules.*;
/**
 *
 * @author  reaction
 * @version 
 */
public class ReactionMechanism extends Object {
    Vector rxnClasses;
    public Hashtable Molecules = new Hashtable();
    public String seedMolecule;
    /** Creates new ReactionMechanism */
    public ReactionMechanism() {
    }
    
    public void readFromFile(File chosenFile) throws IOException {
        readMolecules(chosenFile);
        FileReader reader = new FileReader(chosenFile);
        StringBuffer tLine         = new StringBuffer();
        int bt;
        try {
            bt = reader.read();
            while(bt != -1) {
                    tLine.append((char) bt);
              bt = reader.read();
                if(bt == '\\')
                        bt = processConcat(reader);
                if(bt == '%')
                        bt = processComment(reader);
            }
        } catch (IOException io) { bt = -1; }
        String output = tLine.toString();
        System.out.println(output);
        parse(output);
    }
      public void readMolecules(File mechfile) {
      String name = mechfile.getName();
      String rootname = name.substring(0,name.length()-4);
      String sdfS = rootname + "sdf";
      File sdfFile = new File(mechfile.getParent(),sdfS);
      try {
        java.io.FileReader rd = new FileReader(sdfFile);
        java.io.BufferedReader inp = new java.io.BufferedReader(rd);
        boolean noseed = true;
        while(true) {
            ReactMoleculeFromSDF mol = new ReactMoleculeFromSDF();
            mol.parseMolecule(inp);
            if(noseed) {
                seedMolecule = mol.MoleculeName;
                noseed = false;
                System.out.println("Read in Molecule Seed: " + seedMolecule);
            }
            Molecules.put(mol.MoleculeName,mol);
        }
        } catch(FileNotFoundException io) {
            System.out.println("Molecule File not Found" + sdfFile);
        } catch(IOException io) {
            System.out.println("Done Reading Molecule File");
        }
      
  }
  int processConcat(FileReader reader) {
      int n;
      try {
        n = reader.read();
        if(n == '\n')
            n = reader.read();
      } catch(IOException io) {
          n = -1;
      }
      return n;
  }
  
  int processComment(FileReader reader) {
      int n;
      try {
        n = reader.read();
        while(n != '\n') {
            n = reader.read();
            }
        } catch(IOException io) { n = '\n';}
        return n;
  }

    public boolean parse(String name) {
        boolean success = true;
        rxnClasses = new Vector();
        StringTokenizer elements = new StringTokenizer(name,"\n");
        String classcoeffs = elements.nextToken();
        if(classcoeffs.startsWith("CLASSCOEFFICIENTS")) {
            try {
                boolean notdone = true;
                while(notdone && success) {
                    String line = elements.nextToken();
                    System.out.println("Class Coeffs: " + line);
                    if(line.startsWith("END"))
                        notdone = false;
                    else {
                        System.out.println("ReactionMechanism: create RxnClass");
                        ReactMechanismRxnClass rxnclass = new ReactMechanismRxnClass();
                        System.out.println("ReactionMechanism: parse RxnClass");
                        success = rxnclass.parseCoeffs(line);
                        rxnClasses.add(rxnclass);
                    }
                }
            } catch(Exception ios) {
                System.out.println("Error in parsing ReactionMechanism Coefficients");
            }
            try {
                String classequivs = elements.nextToken();
                String equivsend = elements.nextToken();
                if(classequivs.startsWith("CLASSEQUIV") &&
                    equivsend.startsWith("END")) {
                    for(int i=0;i<rxnClasses.size() && success;i++) {
                        String line = elements.nextToken();
                        System.out.println("Reactions: " + line);
                        ReactMechanismRxnClass rxnclass = 
                            (ReactMechanismRxnClass) rxnClasses.elementAt(i);
                        success = rxnclass.parse(elements);
                    }
                } else {
                    ErrorFrame fr = new ErrorFrame("Expecting:\n"
                         + "CLASSEQUIVALENT got " + classequivs
                         + "END got" + equivsend);
                    fr.show();
                    success = false;
                }
            } catch (NoSuchElementException ios) {
                ErrorFrame fr = new ErrorFrame("Error in parsing Mechanism - Reactions");
                fr.show();
            }
        } else {
            System.out.println("CLASSCOEFFICIENTS not found");
        }
        return success;
    }
    public String write() {
        StringBuffer str = new StringBuffer();
        str.append("CLASSCOEFFICIENTS\n");
        for(int i=0;i<rxnClasses.size();i++) {
           ReactMechanismRxnClass rxnclass = 
                  (ReactMechanismRxnClass) rxnClasses.elementAt(i);
           str.append(rxnclass.writeCoeffs());
        }
        str.append("END\n");
        str.append("CLASSEQUIVALENT\n");
        str.append("END\n");
        for(int i=0;i<rxnClasses.size();i++) {
           ReactMechanismRxnClass rxnclass = 
                  (ReactMechanismRxnClass) rxnClasses.elementAt(i);
           str.append(rxnclass.write());
        }
        //str.append("END\n");
        return str.toString();
    }        
}
