/*
 * BaseDataPredicateAsFuzzy.java
 *
 * Created on September 24, 2004, 11:46 AM
 */

package blurock.logic.predicates;
import java.lang.ClassCastException;
import java.util.Hashtable;

/**
 *
 * @author  reaction
 */
public class BaseDataPredicateAsNonFuzzy extends blurock.logic.predicates.BaseDataPredicate {
    public Hashtable NameTranslations;
    /** Creates a new instance of BaseDataPredicateAsFuzzy */
    public BaseDataPredicateAsNonFuzzy(BaseDataPredicate pred, Hashtable names) throws ClassCastException {
        super(pred);
        NameTranslations = names;
    }
    public BaseDataPredicateAsNonFuzzy(BaseDataPredicateAsNonFuzzy pred) {
        super(pred);
    }
    public String toString() {
        return null;
    }
}
