/*
 * XMatrix.java
 *
 * Created on June 28, 2004, 3:18 PM
 */

package ignition;
import utilities.ReadFileToString;
import java.io.File;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import java.io.PrintStream;
import java.text.DecimalFormat;
/**
 *
 * @author  reaction
 */
public class XMatrix extends ReadFileToString {
    File MatrixFile;

    public int NumberOfParameters;
    public int NumberOfPoints;
    
    StringTokenizer lines;
    public String[] ParameterNames;
    public double MatrixValues[][];
    
    Hashtable IndexOfName;
    
    /** Creates a new instance of XMatrix */
    public XMatrix(File file) throws IOException {
        MatrixFile = file;
        read(file);
        //System.out.println(outputString);
        lines = new StringTokenizer(outputString,"\n");
        if(lines.countTokens() > 0) {
            readTitles();
            readMatrix();
        } else {
            throw new IOException("Error in reading Matrix\n");
        }
    }
    public XMatrix(int npoints,int nparameters,String[] names) {
        NumberOfParameters = nparameters;
        NumberOfPoints = npoints;
        MatrixValues = new double[NumberOfPoints][NumberOfParameters];
        ParameterNames = names;
    }
    
    public XMatrix(double[][]indata,String[] names) {
        NumberOfParameters = indata[0].length;
        NumberOfPoints = indata.length;
        MatrixValues = new double[NumberOfPoints][NumberOfParameters];
        MatrixValues = indata;
        ParameterNames = names;
    }    
    void readTitles() {
        String parametersS = lines.nextToken();
        StringTokenizer parametersT = new StringTokenizer(parametersS,"\t");
        NumberOfParameters = parametersT.countTokens();
        
        IndexOfName = new Hashtable(NumberOfParameters);
        
        ParameterNames = new String[NumberOfParameters];
        for(int i= 0; i< NumberOfParameters; i++) {
            String name = parametersT.nextToken();
            
            ParameterNames[i] = name.trim();
            IndexOfName.put(ParameterNames[i],new Integer(i));
       }
    }
    public int getIndex(String name) {
        Integer index = (Integer) IndexOfName.get(name.trim());
        int ind = 0;
        if(index != null) {
           ind = index.intValue();
        }
        return ind;
    }
    public double[] BuildReference(int index) {
        double[] reference = new double[NumberOfPoints];
        for(int i=0;i<NumberOfPoints;i++) {
            reference[i] = MatrixValues[i][index];
        }
        return reference;
    }
    void readMatrix() throws IOException {
        NumberOfPoints = lines.countTokens();
        MatrixValues = new double[NumberOfPoints][NumberOfParameters];
        for(int i = 0; i< NumberOfPoints; i++) {
            String rowS = lines.nextToken();
            StringTokenizer rowT = new StringTokenizer(rowS,"\t ");
            if(rowT.countTokens() != NumberOfParameters) 
                throw new IOException("Error reading Matrix (inconsistent rows):  " + MatrixFile.toString());
            for(int j=0;j<NumberOfParameters;j++) {
                String value = rowT.nextToken();
                try {
                    double num = Double.parseDouble(value);
                    MatrixValues[i][j] = num;
                } catch(NumberFormatException numex) {
                    System.err.println("Error reading Matrix (not a number):  " + value + " in " + MatrixFile.toString() );
                    MatrixValues[i][j] = 0.0;
                }
            }
        }
    }
    public void writeMatrix(PrintStream io, String nameprefix) {
        System.out.println(NumberOfParameters);
        System.out.println(NumberOfPoints);
        System.out.println(MatrixValues.length);
        System.out.println(MatrixValues[0].length);
        for(int i=0;i<ParameterNames.length;i++) {
            if(i!=0) {
                io.print("\t "); 
                io.print(nameprefix+ParameterNames[i]);
            } else {
                io.print(ParameterNames[i]);
            }
        }
        io.print("\n");
        for(int i=0;i<NumberOfPoints;i++) {
            for(int j=0;j<NumberOfParameters;j++) {
                if(j!=0) io.print("\t ");
                double num = MatrixValues[i][j];
                
                io.print(Double.toString(num));
                //io.print(displayNumber(num));
            }
            io.print("\n");
        }
    }

    
     String displayNumber(double num) {
      String pattern = new String("0.00000000E000");
      DecimalFormat myFormatter = new DecimalFormat(pattern);
      String output = myFormatter.format(num);
      System.out.println("displayNumber: " + output+" pattern "+pattern+" num "+num);
      return output;
     }


}