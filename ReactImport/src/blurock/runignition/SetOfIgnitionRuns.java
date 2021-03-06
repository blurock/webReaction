/*
 * SetOfIgnitionRuns.java
 *
 * Created on June 8, 2006, 11:09 AM
 */

package blurock.runignition;

import blurock.core.ObjectNotFoundException;
import blurock.core.RWManager;
import blurock.core.RWManagerString;
import blurock.iterator.BaseDataIterationSet;
import blurock.iterator.DataIterationSetClass;
import blurock.iterator.BaseDataObjectIterator;
import blurock.iterator.DataObjectIteratorClass;
import blurock.coreobjects.DataSetOfObjectsClass;
import blurock.coreobjects.BaseDataSetOfObjects;
import blurock.runignition.BaseDataIgnitionConditions;
import blurock.runignition.DataIgnitionConditionsClass;
import blurock.utilities.SetUpClassAttrFile;
import java.awt.BorderLayout;
import java.io.StringWriter;
import java.io.PrintWriter;
import react.common.TopReactionMenu;
import utilities.ErrorFrame;
import java.io.IOException;
import java.io.FileNotFoundException;
import utilities.FileFrame;
import blurock.utilities.SetUpClassAttrFile;
import blurock.core.RWManager;
import blurock.Reference.ReferencePanel;
import blurock.Reference.RxnDataLiteratureReference;
import java.io.StringWriter;
import blurock.coreobjects.BaseDataReal;
import blurock.runignition.BaseDataIgnitionConditions;

/**mat
 *
 * @author  reaction
 */
public class SetOfIgnitionRuns extends javax.swing.JPanel {
        public CreateMatrixOfReals species;
        public CreateVectorOfReals temperature;
        public CreateVectorOfReals pressure;
        TopReactionMenu Top;
        public ReferencePanel reference = new ReferencePanel();
        public BaseDataIterationSet IterationSet;
        public RxnDataLiteratureReference LiteratureReference;
        String currentDir;
    /** Creates new form SetOfIgnitionRuns */
    public SetOfIgnitionRuns(String[] speciesNames, TopReactionMenu top) {
        Top = top;
        currentDir = new String(".");
        initComponents();
        String name = "Parameters";
        species = new CreateMatrixOfReals("Species",speciesNames);
        temperature = new CreateVectorOfReals("Temperature");
        pressure = new CreateVectorOfReals("Pressure");
        temperaturePanel.add(temperature,BorderLayout.CENTER);
        pressurePanel.add(pressure,BorderLayout.CENTER);
        speciesPanel.add(species,BorderLayout.CENTER);
        referencePanel.add(reference,BorderLayout.CENTER);
    }
    public void setSpecies(String[] l) {
        species.possibleParameters = l;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        parameterPanel = new javax.swing.JPanel();
        temperaturePanel = new javax.swing.JPanel();
        pressurePanel = new javax.swing.JPanel();
        speciesPanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        timeStepPanel = new javax.swing.JPanel();
        timeStepBjButton1utton = new javax.swing.JButton();
        timeStepField = new javax.swing.JTextField();
        referencePanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        parameterPanel.setLayout(new java.awt.GridLayout(2, 1));

        parameterPanel.setPreferredSize(new java.awt.Dimension(450, 175));
        temperaturePanel.setLayout(new java.awt.BorderLayout());

        temperaturePanel.setBorder(new javax.swing.border.TitledBorder("Temperature"));
        parameterPanel.add(temperaturePanel);

        pressurePanel.setLayout(new java.awt.BorderLayout());

        pressurePanel.setBorder(new javax.swing.border.TitledBorder("Pressure"));
        parameterPanel.add(pressurePanel);

        add(parameterPanel, java.awt.BorderLayout.NORTH);

        speciesPanel.setLayout(new java.awt.BorderLayout());

        speciesPanel.setBorder(new javax.swing.border.TitledBorder("Species Mole Fractions"));
        speciesPanel.setPreferredSize(new java.awt.Dimension(450, 250));
        add(speciesPanel, java.awt.BorderLayout.CENTER);

        bottomPanel.setLayout(new java.awt.BorderLayout());

        timeStepPanel.setLayout(new java.awt.GridLayout(1, 2));

        timeStepPanel.setBorder(new javax.swing.border.TitledBorder("Time Step Size"));
        timeStepBjButton1utton.setText("Time Step Size (secs)");
        timeStepPanel.add(timeStepBjButton1utton);

        timeStepField.setText("0.00001");
        timeStepPanel.add(timeStepField);

        bottomPanel.add(timeStepPanel, java.awt.BorderLayout.NORTH);

        referencePanel.setLayout(new java.awt.BorderLayout());

        referencePanel.setBorder(new javax.swing.border.TitledBorder("Literature Reference"));
        bottomPanel.add(referencePanel, java.awt.BorderLayout.CENTER);

        add(bottomPanel, java.awt.BorderLayout.SOUTH);

    }
    // </editor-fold>//GEN-END:initComponents
    public BaseDataIgnitionConditions sampleSetOfConditions(DataIgnitionConditionsClass cls) {
        BaseDataIgnitionConditions cond = (BaseDataIgnitionConditions) cls.BaseDataObjectExample();
        cond.Reference = new RxnDataLiteratureReference("Reference",0,
                            reference.sourceTextArea.getText(),
                            reference.authorField.getText(),
                            reference.titleField.getText());
        double[] t = temperature.getVectorValues();
        double[] p = pressure.getVectorValues();
        double dt = 0.000001;
        try {
            dt = Double.valueOf(timeStepField.getText());
        } catch(NumberFormatException ex) {
            ErrorFrame fr = new ErrorFrame("Error in time step value");
            fr.setVisible(true);
        }
        cond.Temperature = new BaseDataReal();
        if(t.length > 0)
            cond.Temperature.realValue = cond.Temperature.realValue = t[0];
        else
            cond.Temperature.realValue = cond.Temperature.realValue = 1000.0;
        cond.Pressure = new BaseDataReal();
        if(p.length > 0)
            cond.Pressure.realValue = p[0];
        else
            cond.Pressure.realValue = 100000.0;
        cond.DeltaT = new BaseDataReal();
        cond.DeltaT.realValue = dt;
        
        cond.Species = (BaseDataSetOfObjects) cls.SpeciesClass.BaseDataObjectExample();
        return cond;
    }
    public DataSetOfObjectsClass createIterationSet(String iterclassS, String rootname) throws IOException {
        try {
            LiteratureReference = reference.createLiteratureReference();
            DataIterationSetClass iterclass = (DataIterationSetClass) 
                        Top.registeredClasses.findClass(iterclassS);
            IterationSet = (BaseDataIterationSet) iterclass.BaseDataObjectExample();
            IterationSet.Name = rootname;
            createVectorIterationObject("Pressure",0, pressure);
            createVectorIterationObject("Temperature",1, temperature);
            DataSetOfObjectsClass cls = createMatrixIterationObject("Species",2,species);
            return cls;
        } catch(ObjectNotFoundException ex) {
            throw new IOException("IterationSet class not found: " + iterclassS);
        }
    }
    private void createVectorIterationObject(String name, int id, CreateVectorOfReals obj) {
        double[] values = obj.getVectorValues();
        String[] parameters = obj.getParameterNames();
        BaseDataObjectIterator iter = new BaseDataObjectIterator(name,id);
        iter.fillFromDoubleVector(parameters, values);  
        IterationSet.AddObject(iter);        
    }
    private DataSetOfObjectsClass createMatrixIterationObject(String name, int id, CreateMatrixOfReals obj) {
        double [][] mat = obj.getMatrixValues();
        String[] setnames = obj.getSetNames();
        String[] parameters = obj.getParameterNames();
        BaseDataObjectIterator iter = new BaseDataObjectIterator(name,id);
        iter.ioClassNamePairs = false;
        int classid = Top.registeredClasses.getNextICount();
        DataSetOfObjectsClass cls = iter.fillFromDoubleMatrix(classid, "SpeciesClass", setnames, parameters, mat); 
        IterationSet.AddObject(iter);
        return cls;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel parameterPanel;
    private javax.swing.JPanel pressurePanel;
    private javax.swing.JPanel referencePanel;
    private javax.swing.JPanel speciesPanel;
    private javax.swing.JPanel temperaturePanel;
    private javax.swing.JButton timeStepBjButton1utton;
    public javax.swing.JTextField timeStepField;
    private javax.swing.JPanel timeStepPanel;
    // End of variables declaration//GEN-END:variables
    
}
