/*
 * SetOfConditionParametersAsDoubles.java
 *
 * Created on September 29, 2004, 3:36 PM
 */

package blurock.SeparateUnderCondition;
import ignition.XMatrix;
/**
 *
 * @author  reaction
 */
public class SetOfConditionParametersAsDoubles extends blurock.SeparateUnderCondition.SetOfCondtionParameters {
    XMatrix MatrixX;
    /** Creates a new instance of SetOfConditionParametersAsDoubles */
    public SetOfConditionParametersAsDoubles() {
    }
    public SetOfConditionParametersAsDoubles(XMatrix x) {
        MatrixX = x;
        /*
        double[] rowD = new double[x.NumberOfParameters];
        double[][] mat = x.MatrixValues;
        for(int row = 0;row < x.NumberOfPoints;row++) {
            for(int col = 0;col<x.NumberOfParameters;col++) {
                rowD[col] = mat[row][col];
            }
            ConditionParametersAsDoubles parms = new ConditionParametersAsDoubles(rowD);
            addParameterSet(parms);
            System.out.println(rowD[38] + ", " + rowD[56] + ", "+ rowD[76] + ", ("+ size() + ") ");
            ConditionParametersAsDoubles p = (ConditionParametersAsDoubles) this.getIthParameterSet(row);
            System.out.println(p.toString());
            if(row > 4) {
            ConditionParametersAsDoubles p2 = (ConditionParametersAsDoubles) this.getIthParameterSet(4);
            System.out.println(p2.toString());
            }
         */
        }
    public ConditionParameters getIthParameterSet(int i) {
        double[] rowD = new double[MatrixX.NumberOfParameters-1];
        for(int col = 1;col<MatrixX.NumberOfParameters;col++) {
             rowD[col-1] = MatrixX.MatrixValues[i][col];
        }
        ConditionParametersAsDoubles parms = new ConditionParametersAsDoubles(rowD);
        return (ConditionParameters) parms;
    }
    public int size() {
        return MatrixX.NumberOfPoints;
    }
    
}
