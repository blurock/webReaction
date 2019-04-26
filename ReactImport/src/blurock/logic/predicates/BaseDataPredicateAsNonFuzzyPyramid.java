/*
 * BaseDataPredicateAsNonFuzzyPyramdi.java
 *
 * Created on September 24, 2004, 12:02 PM
 */

package blurock.logic.predicates;
import java.lang.ClassCastException;
import blurock.numeric.numops.BaseDataPyramidFunction;
import blurock.numeric.numops.BaseDataNumericPredicate;
import java.util.Hashtable;
/**
 *
 * @author  reaction
 */
public class BaseDataPredicateAsNonFuzzyPyramid extends blurock.logic.predicates.BaseDataPredicateAsNonFuzzy {

    BaseDataNumericPredicate numpred;
    BaseDataPyramidFunction pyr;
    public boolean gt = false;
    public boolean lt = false;
    public  boolean step = false;
    public boolean slope = false;
    double cutoff = 0.0;
    String Parameter = null;
    
    /** Creates a new instance of BaseDataPredicateAsNonFuzzyPyramdi */
    public BaseDataPredicateAsNonFuzzyPyramid(BaseDataPredicate pred, Hashtable names) throws ClassCastException {
        super(pred,names);
        
        numpred = (BaseDataNumericPredicate) pred.Operation;
        pyr = (BaseDataPyramidFunction) numpred.PredicateFunction;
        if(pyr.Xbegin == pyr.Xend) {
            if(pyr.InitialHeight > pyr.FinalHeight) 
                lt = true;
            else
                gt = true;
            step = true;
            cutoff = pyr.Xbegin;
            Hashtable p;
            Parameter = (String) NameTranslations.get(pred.Parameter);
        } else {
            if(pyr.Xbegin == pyr.Xpoint || pyr.Xend == pyr.Xpoint) {
                slope = true;
        }
    }
    }
    public String toString() {
        String out = null;
        if(step)
            out = asStringStep();
        else if(slope)
            out = asStringSlope();
        return out;
    }
    String asStringSlope() {
        StringBuffer buf = new StringBuffer();
        double slope = 1.0/(pyr.Xend - pyr.Xbegin);
        Double slopeD = new Double(slope);
        Double bD = new Double(pyr.Xbegin);
        buf.append("((");
        buf.append(Parameter);
        buf.append(" - ");
        buf.append(bD.toString());
        buf.append(")*");
        buf.append(slopeD.toString());
        buf.append(") > 0.5)");
        return buf.toString();
    }
    String asStringStep() {
        StringBuffer buf = new StringBuffer();
        buf.append(Parameter);
        if(lt)
            buf.append(" > ");
        else
            buf.append(" < ");
        Double d = new Double(cutoff);
        buf.append(d.toString());
        return buf.toString();
    }

}
