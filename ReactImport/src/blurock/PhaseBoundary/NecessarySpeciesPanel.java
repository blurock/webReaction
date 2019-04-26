
/*
 * NecessarySpeciesPanel.java
 *
 * Created on September 30, 2004, 2:43 PM
 */
package blurock.PhaseBoundary;

import java.io.IOException;
import java.io.File;
import utilities.ErrorFrame;
import utilities.FileFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import ignition.XMatrix;
import ignition.PointRange;
import ignition.SetOfMaximaInRange;
import ignition.DisplayNecessarySpecies;
import utilities.traverseDirectory;
import blurock.DecisionTree.DecisionTreeProgramOut;
/**
 *
 * @author  reaction
 */
public class NecessarySpeciesPanel extends javax.swing.JPanel {
    XMatrix mat;
    JTable fileTable;
    JTable phaseBoundaryTable;
    SetOfMaximaInRange[] NecessarySpeciesInRuns;
    DisplayNecessarySpecies[] DisplaySpecies;
    String fullMechRoot = "Full";
    class directoryPOSM extends javax.swing.filechooser.FileFilter {
        public directoryPOSM() {
        }
        public String getDescription() {
            return new String("POSM Directory");
        }
        public boolean accept(java.io.File file) {
            boolean ans = true;
	    if(file.isDirectory()) 
		ans = true;
            else
                ans = false;
            return ans;
        }
    }
    directoryPOSM POSMchooserFilter = new directoryPOSM();
    JFileChooser POSMchooser = null;
    DecisionTreeProgramOut topPanel;
    
    /** Creates new form NecessarySpeciesPanel */
    public NecessarySpeciesPanel(JTable file, JTable phase, DecisionTreeProgramOut top) {
        initComponents();
        topPanel = top;
        fileTable = file;
        phaseBoundaryTable = phase;
        JTableHeader header =   boundaryNecessarySpeciesTable.getTableHeader();
        mainPanel.add(header,java.awt.BorderLayout.NORTH);
    }
    SetOfMaximaInRange computeNecessaryWithinBoundary(Double lower,Double upper,File fileF) {
        SetOfMaximaInRange MaximaSet = null;
            //try {
                //XMatrix mat = new XMatrix(fileF);
                PointRange rng = new PointRange();
                rng.LowerBoundary = lower.doubleValue();
                rng.UpperBoundary = upper.doubleValue();
                rng.NumberOfPoints = 0;
           
                String rangeName = new String("Index");
                MaximaSet = new SetOfMaximaInRange(mat,rangeName,rng);
           
                //MaximaSet.maximaOut(System.out);
            //} catch(IOException ex) {
            //System.err.println(ex);
            //}
            return MaximaSet;
    }
    void computeNecessaryBoundaryTable() {
       DefaultTableModel tmodel = (DefaultTableModel) boundaryNecessarySpeciesTable.getModel();
       DefaultTableModel pmodel = (DefaultTableModel) phaseBoundaryTable.getModel();
       int numBoundaries = NecessarySpeciesInRuns.length;
       DisplaySpecies = new DisplayNecessarySpecies[numBoundaries];
        int rcount = tmodel.getRowCount();
        for(int i=rcount-1;i >= 0 ;i--) {
            tmodel.removeRow(i);
        }       
       for(int i=0;i<numBoundaries;i++) {
           Integer range = (Integer) pmodel.getValueAt(i,1);
           SetOfMaximaInRange maxima = NecessarySpeciesInRuns[i];
           double slide = -(limitSlider.getValue()/10);
           double slide10 = Math.pow(10.0,slide);
           Double limitD = new Double(slide10);
           double limit = limitD.doubleValue();
           Integer numSpecies = new Integer(maxima.numberAboveLimit(limit));
           Integer numInRange = new Integer(maxima.numberInRange());
           Boolean showit = new Boolean(false);
           
           tmodel.addRow(new Object[] {range,numSpecies,numInRange,showit});
           Double lowerLimit = new Double(limit);
           DisplaySpecies[i] = new DisplayNecessarySpecies(limit,NecessarySpeciesInRuns[i],
                                     POSMdirField.getText(), range.intValue());
           DisplaySpecies[i].hide();
       }
        int rowCount = phaseBoundaryTable.getRowCount();
        tmodel.fireTableRowsInserted(rowCount,rowCount);
        ListSelectionModel rowSM = boundaryNecessarySpeciesTable.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(e);
                if (e.getValueIsAdjusting()) return;

                ListSelectionModel lsm =
                    (ListSelectionModel)e.getSource();
                if (lsm.isSelectionEmpty()) {
                    
                } else {
                    int selectedRow = lsm.getMinSelectionIndex();
                    DefaultTableModel tmodel = (DefaultTableModel) 
                        boundaryNecessarySpeciesTable.getModel();
                    
                    Boolean ans = (Boolean) tmodel.getValueAt(selectedRow,3);
                    System.out.println(ans);
                    boolean setvalue = !ans.booleanValue();
                    System.out.println("Display " + selectedRow + "\t " + setvalue);
                    tmodel.setValueAt(new Boolean(setvalue),selectedRow,3);
                    
                    if(setvalue) 
                        DisplaySpecies[selectedRow].show();
                    else
                        DisplaySpecies[selectedRow].hide();
                    }
             }});
        }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        mainPanel = new javax.swing.JPanel();
        boundaryNecessarySpeciesTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        baseDirSelectButton = new javax.swing.JButton();
        POSMdirField = new javax.swing.JTextField();
        setupdirButton = new javax.swing.JButton();
        computePhases = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        limitSlider = new javax.swing.JSlider();

        setLayout(new java.awt.BorderLayout());

        mainPanel.setLayout(new java.awt.BorderLayout());

        mainPanel.setBorder(new javax.swing.border.TitledBorder("Phases Reduced Species"));
        boundaryNecessarySpeciesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Range", "Number of Points", "Number of Species", "Necessary Species"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mainPanel.add(boundaryNecessarySpeciesTable, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridLayout(5, 1));

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        baseDirSelectButton.setText("POSM Base");
        baseDirSelectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                baseDirSelectButtonMouseClicked(evt);
            }
        });

        jPanel4.add(baseDirSelectButton);

        POSMdirField.setText("POSM");
        jPanel4.add(POSMdirField);

        jPanel1.add(jPanel4);

        setupdirButton.setText("Set up directories");
        setupdirButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setupdirButtonMouseClicked(evt);
            }
        });

        jPanel1.add(setupdirButton);

        computePhases.setText("Compute Necessary Species");
        computePhases.setMinimumSize(new java.awt.Dimension(600, 25));
        computePhases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                computePhasesMouseClicked(evt);
            }
        });

        jPanel1.add(computePhases);

        jPanel2.setLayout(new java.awt.GridLayout(3, 1));

        jPanel3.setLayout(new java.awt.GridLayout(1, 3));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("0.0");
        jPanel3.add(jLabel1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cutoff");
        jPanel3.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("-10.0");
        jPanel3.add(jLabel3);

        jPanel2.add(jPanel3);

        jPanel1.add(jPanel2);

        limitSlider.setMajorTickSpacing(10);
        limitSlider.setMinorTickSpacing(2);
        limitSlider.setPaintTicks(true);
        limitSlider.setSnapToTicks(true);
        limitSlider.setToolTipText("Necessary Species Cutoff");
        limitSlider.setValue(40);
        jPanel1.add(limitSlider);

        mainPanel.add(jPanel1, java.awt.BorderLayout.SOUTH);

        add(mainPanel, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

    private void setupdirButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setupdirButtonMouseClicked
        traverseDirectory dirstructure = new traverseDirectory();
        File topdir = new File(POSMdirField.getText());
        File fullmechdir = new File(topdir,fullMechRoot);
        if(!fullmechdir.exists()) {
            ErrorFrame fr = new ErrorFrame("Full Mechanism Directory not found: " + fullmechdir);
            fr.show();
        } else {
            int numsubmechs = computeNumberOfSubmechs();
            for(int i=0;i<numsubmechs;i++) {
                 Integer iI = new Integer(i);
                 String mech = new String("mech" + iI.toString());
                 File dir = new File(topdir,mech);
                  if(!dir.exists()) {
                      dir.mkdir();
                  }
                 System.out.println(fullmechdir);
                 System.out.println(dir);
                 try {
                    dirstructure.copyDirectoryStructure(fullmechdir,dir);
                 } catch(IOException io) {
                     ErrorFrame fr = new ErrorFrame(io.toString());
                     fr.show();
                 }
            }
        }
    }//GEN-LAST:event_setupdirButtonMouseClicked
    int computeNumberOfSubmechs() {
       DefaultTableModel tmodel = (DefaultTableModel) boundaryNecessarySpeciesTable.getModel();
       int numphases = tmodel.getRowCount();
       int topphase = 0;
       for(int i=0;i<numphases;i++) {
           Integer phaseI = (Integer) tmodel.getValueAt(i,1);
           if(phaseI.intValue() > topphase) 
                topphase = phaseI.intValue();
       }
       return topphase+1;
    }
    private void baseDirSelectButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baseDirSelectButtonMouseClicked
      if(POSMchooser == null) {
         POSMchooser = new JFileChooser();
         POSMchooser.setFileFilter(POSMchooserFilter);
      }
      POSMchooser.showOpenDialog(this);
      File chosenDir  = POSMchooser.getSelectedFile();
      POSMdirField.setText(chosenDir.toString());
    }//GEN-LAST:event_baseDirSelectButtonMouseClicked

    private void computePhasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_computePhasesMouseClicked
       DefaultTableModel tmodel = (DefaultTableModel) phaseBoundaryTable.getModel();
       DefaultTableModel fmodel = (DefaultTableModel) fileTable.getModel();
       int numBoundaries = tmodel.getRowCount();
       NecessarySpeciesInRuns = new SetOfMaximaInRange[numBoundaries];
       topPanel.totNecPanel.NecessarySpeciesInRuns = NecessarySpeciesInRuns;
        try {
        String GNname = (String) tmodel.getValueAt(0,0);
        File Xfile = (File) fmodel.getValueAt(0,0);
        String dir = Xfile.getParent();
        File fileF = new File(dir,GNname);
        mat = new XMatrix(fileF);
        System.out.println("Matrix: " + fileF.toString() + "    Parameters" + mat.NumberOfParameters);
        for(int b=0;b<numBoundaries;b++) {
            Double lower = (Double) tmodel.getValueAt(b,2);
            Double upper = (Double) tmodel.getValueAt(b,3);
            //String GNname = (String) tmodel.getValueAt(b,0);
            System.out.println(b + ":   Lower: " + lower + "  Upper: " + upper);
            System.out.println(fileF.toString());
            NecessarySpeciesInRuns[b] = computeNecessaryWithinBoundary(lower,upper,fileF);
        }
        
        computeNecessaryBoundaryTable();
            } catch(IOException ex) {
            System.err.println(ex);
            }

    }//GEN-LAST:event_computePhasesMouseClicked
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton baseDirSelectButton;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSlider limitSlider;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JTable boundaryNecessarySpeciesTable;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton computePhases;
    private javax.swing.JTextField POSMdirField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton setupdirButton;
    // End of variables declaration//GEN-END:variables
    
}
