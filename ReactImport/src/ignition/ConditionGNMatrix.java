/*
 * ConditionGNMatrix.java
 *
 * Created on July 29, 2004, 10:29 PM
 */

package ignition;
import java.io.PrintStream;
/**
 *
 * @author  reaction
 */
public class ConditionGNMatrix {
    XMatrix Xmatrix;
    XMatrix GNmatrix;
    XMatrix CGNmatrix;
    int Nave = 0;
    /** Creates a new instance of ConditionGNMatrix */
    public ConditionGNMatrix(XMatrix X, XMatrix GN) {
        Xmatrix = X;
        GNmatrix = GN;   
    }
    public void conditionGNMatrix(double zro) {
        CGNmatrix = new XMatrix(GNmatrix.NumberOfPoints,GNmatrix.NumberOfParameters,GNmatrix.ParameterNames);
        for(int i=0;i<GNmatrix.NumberOfPoints;i++) {
            CGNmatrix.MatrixValues[i][0] = GNmatrix.MatrixValues[i][0];
            Xmatrix.MatrixValues[i][0] = GNmatrix.MatrixValues[i][0];
        }
        for(int j=1;j<GNmatrix.NumberOfParameters;j++) {
            for(int i=0;i<GNmatrix.NumberOfPoints;i++) {
                double ave = 0.0;
                int lower = i - Nave;
                if(lower < 0) lower = 0;
                int upper = i+Nave+1;
                if(upper > GNmatrix.NumberOfPoints) upper = GNmatrix.NumberOfPoints;
                double countD = 0.0;
                for(int k=lower;k<upper;k++) {
                    countD = countD + 1.0;
                    ave += GNmatrix.MatrixValues[k][j];
                 }
                double cave = ave/countD;
                if(cave < zro) cave = zro;
                if(Xmatrix.MatrixValues[i][j] < zro) {
                    cave = zro;
                }
                //double lave = Math.log(cave)/Math.log(10.0);
                CGNmatrix.MatrixValues[i][j] = cave;
            }
        }
    }
    public void writeGNMatrix(PrintStream io) {
        CGNmatrix.writeMatrix(io,"CN");
    }
    public void writeXMatrix(PrintStream io) {
        Xmatrix.writeMatrix(io,"X");
    }
}
