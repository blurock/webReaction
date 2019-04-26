/*
 * XYMatrix.java
 *
 * Created on den 12 oktober 2005, 18:09
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
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

public class XYMatrix extends ReadFileToString{
    File MatrixFile;

    public int NumberOfParameters;
    public int NumberOfPoints;
    
    StringTokenizer lines;
    String[] ParameterNames;
    public double MatrixValues[][];
    
    Hashtable IndexOfName;
    
    /** Creates a new instance of XMatrix */
    public XYMatrix(File file) throws IOException {
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

    void readMatrix() throws IOException {
        NumberOfPoints = lines.countTokens();
        MatrixValues = new double[NumberOfPoints][NumberOfParameters];
        for(int i = 0; i< NumberOfPoints; i++) {
            String rowS = lines.nextToken();
            StringTokenizer rowT = new StringTokenizer(rowS,"\t ");
            if(rowT.countTokens() != NumberOfParameters) 
                throw new IOException("Error reading Matrix (inconsistent rows):  " + MatrixFile.toString());
            for(int j=0;j<NumberOfParameters;j++) {// skip the first column
                String value = rowT.nextToken();
                try {
                    double num = Double.parseDouble(value);
                    MatrixValues[i][j] = num;
                } catch(NumberFormatException numex) {
                    //System.err.println("Error reading Matrix (not a number):  " + value + " in " + MatrixFile.toString() );
                    MatrixValues[i][j] = 0.0;
                }
            }
        }
    }




}    

