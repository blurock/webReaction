/*
 * ReactMechanismGeneration.java
 *
 * Created on January 22, 2001, 7:32 PM
 */

package react.mechanisms;
import react.common.*;
import java.util.StringTokenizer;

/**
 *
 * @author  reaction
 * @version 
 */
public class ReactMechanismGeneration extends Object {

    public TopReactionMenu Top;
    
    public String mechanismName = new String("MechanismGeneration");
    
    public ReactMechanismGenerationStep[] Steps;
    
    public String[] initialMolecules;
    
    
    /** Creates new ReactMechanismGeneration */
    public ReactMechanismGeneration() {
    }

    public boolean parse(String str) {
        StringTokenizer linetokens = new StringTokenizer(str,"\n");
        mechanismName = linetokens.nextToken();
        String molecules = linetokens.nextToken();
        StringTokenizer moltokens = new StringTokenizer(molecules,",");
        initialMolecules = new String[moltokens.countTokens()];
        int i = 0;
        while(moltokens.hasMoreTokens()) {
            initialMolecules[i] = moltokens.nextToken();
            i++;
        }
        int num = 0;
        Steps = new ReactMechanismGenerationStep[linetokens.countTokens()];
        boolean success = true;
        System.out.println("Begin: Parse Through Steps");
        while(linetokens.hasMoreTokens() && success) {
            Steps[num] = new ReactMechanismGenerationStep(Top);
            String next = linetokens.nextToken();
            success = Steps[num].parse(next);
            Steps[num].index = num;
            num++;
        }
        System.out.println("End:   Parse Through Steps");
        return success;
    }
        public String write() {
            String str = new String();
            String mechComment = new String("%% MechanismGeneration: " + mechanismName + "\n");
            str.concat(str);
            String name = new String(mechanismName + "\n");
            str.concat(name);
            String molcomment = new String("%%   Molecules\n");
            str.concat(molcomment);
            for(int i = 0; i < initialMolecules.length; i++) {
                String m = new String(initialMolecules[i]);
                str.concat(m);
                if(i != initialMolecules.length - 1) 
                        str.concat(",\\");
                str.concat("\n");
            }
            String stepcommon = new String("%% Mechanism Steps\n");
            str.concat(stepcommon);
            for(int j = 0; j < Steps.length; j++) {
                String m = Steps[j].write();
                str.concat(m);
                str.concat("\n");
            }
            return str;
        }
}
