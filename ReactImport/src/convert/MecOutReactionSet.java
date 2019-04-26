/*
 * MecOutReactionSet.java
 *
 * Created on December 16, 2002, 5:29 PM
 */

package convert;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;
/**
 *
 * @author  reaction
 */
public class MecOutReactionSet extends ArrayList {
    int count;
    public SpeciesList speciesList = null;
    /** Creates a new instance of MecOutReactionSet */
    public MecOutReactionSet(SpeciesList splist) {
        speciesList = splist;
    }
    public void read(StringTokenizer tok, Object[] species) throws IOException {
        try {
          while(true) {
              System.out.println("Reaction Count:" + count);
                MecOutReaction rxn = new MecOutReaction(count,speciesList);
                rxn.read(tok,species);
                add(count,rxn);
                count++;
          }
        } catch(DoneReadingException ex) {
            System.out.print("Done Reading MECOUT");
        }
    }
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for(int i=0;i<size();i++) {
            MecOutReaction rxn = (MecOutReaction) get(i);
            buf.append(rxn.toString());
        }
        return buf.toString();
    }
}
