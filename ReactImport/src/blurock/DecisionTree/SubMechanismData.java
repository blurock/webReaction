/*
 * SubMechanismData.java
 *
 * Created on September 25, 2004, 6:34 PM
 */

package blurock.DecisionTree;
import blurock.core.RWManagerBase;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;
/**
 *
 * @author  reaction
 */
public class SubMechanismData {
    int numSpecies = 0;
    int numReactions = 0;
    public ArrayList MoleculeNames = new ArrayList();
    /** Creates a new instance of SubMechanismData */
    public SubMechanismData() {
    }
    public void readSpeciesData(RWManagerBase io) throws IOException {
        try {
            String line = io.readNextLine();
            StringTokenizer tok = new StringTokenizer(line);
            String numSpeciesS = tok.nextToken();
            numSpecies = Integer.parseInt(numSpeciesS);
            int count = 0;
            line = null;
            while(count < numSpecies) {
                if(line == null) {
                    line = io.readNextLine();
                    tok = new StringTokenizer(line);
                }
                String species = tok.nextToken();
                MoleculeNames.add(species);
                count++;
                if(tok.countTokens() == 0)
                    line = null;
            }
        } catch(NumberFormatException ex) {
            throw new IOException("Illegal Number of Species: " + ex);
        }
    }
    public void readReactionData(RWManagerBase io) throws IOException {
        try {
            String line = io.readNextLine();
            line = io.readNextLine();
            StringTokenizer tok = new StringTokenizer(line);
            tok.nextToken();
            String numS = tok.nextToken();
            Integer numR = new Integer(tok.nextToken());
            numReactions = numR.intValue();
            tok.nextToken();
        } catch(NumberFormatException ex) {
            throw new IOException("Illegal Number of Species: " + ex);
        }        
    }
    public boolean speciesPresent(String name) {
        int ind = MoleculeNames.indexOf(name);
        boolean ans = true;
        if(ind < 0) ans = false;
        return ans;
    }
    public int getSpeciesPosition(String name) {
        return MoleculeNames.indexOf(name);
    }
    public int numberOfSpecies() {
        return MoleculeNames.size();
    }
    public int numberOfReactions() {
        return numReactions;
    }
    public String getSpeciesI(int i) {
        return (String) MoleculeNames.get(i);
    }
}
