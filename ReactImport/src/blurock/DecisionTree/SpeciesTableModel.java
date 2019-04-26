/*
 * SpeciesTableModel.java
 *
 * Created on September 25, 2004, 8:59 PM
 */

package blurock.DecisionTree;
import java.util.Vector;
/**
 *
 * @author  reaction
 */
public class SpeciesTableModel extends blurock.utilities.StandardTableModel {
    
    /** Creates a new instance of SpeciesTableModel */
    public SpeciesTableModel(Object[][] d, String[] c) {
        super(d,c);
    }
    
    /** */
    protected void fillRows(Vector vRow) {
    }
    
    /** */
    protected void setColumnsLength(int maxLen) {
    }
    public boolean isCellEditable(int row, int col) {
        boolean ans = true;
        if(col == 0) ans = false;
        return ans;
    };
    
}
