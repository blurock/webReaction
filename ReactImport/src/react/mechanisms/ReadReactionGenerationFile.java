/*
 * ReadReactionGenerationFile.java
 *
 * Created on January 22, 2001, 7:03 PM
 */

package react.mechanisms;
import utilities.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class ReadReactionGenerationFile extends ReadInFile {

    public ReactMechanismGeneration generationSteps;
    /** Creates new ReadReactionGenerationFile */
    public ReadReactionGenerationFile() {
    }

    public boolean processOutput(java.lang.String o) {
        generationSteps = new ReactMechanismGeneration();
        return generationSteps.parse(o);
  }

}
