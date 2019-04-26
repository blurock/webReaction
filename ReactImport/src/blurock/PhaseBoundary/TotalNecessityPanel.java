/*
 * TotalNecessityPanel.java
 *
 * Created on October 4, 2004, 5:14 PM
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

/**
 *
 * @author  reaction
 */
public class TotalNecessityPanel extends javax.swing.JPanel {
    JTable phaseBoundaryTable;
    JTable boundaryNecessarySpeciesTable;
    public SetOfMaximaInRange[] NecessarySpeciesInRuns;
    SetOfMaximaInRange[] totalMaximaInRange;
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
    
    /** Creates new form TotalNecessityPanel */
    public TotalNecessityPanel(JTable phaseBoundaryt,JTable NecessarySpeciest) {
        phaseBoundaryTable = phaseBoundaryt;
        boundaryNecessarySpeciesTable = NecessarySpeciest;
        initComponents();
    }
    void computeNecessaryBoundaryTable() {
        combinePhases();
        computeTotals();
        setUpNecessaryTables();
    }
    void combinePhases() {
       DefaultTableModel tmodel = (DefaultTableModel) boundaryNecessarySpeciesTable.getModel();
       DefaultTableModel pmodel = (DefaultTableModel) phaseBoundaryTable.getModel();
       DefaultTableModel totmodel = (DefaultTableModel) totalNecessarySpeciesTable.getModel();
       
       int numphases = computeNumberOfSubmechs();
       int numBoundaries = NecessarySpeciesInRuns.length;
       DisplaySpecies = new DisplayNecessarySpecies[numphases];
       totalMaximaInRange = new SetOfMaximaInRange[numphases];
       for(int phase = 0;phase < numphases;phase++) {
         
         for(int i=0;i<numBoundaries;i++) {
             Integer range = (Integer) pmodel.getValueAt(i,1);
             if(range.intValue() == phase) {
                 SetOfMaximaInRange maxima = NecessarySpeciesInRuns[i];
                  if(totalMaximaInRange[phase] == null) {
                      totalMaximaInRange[phase] = new SetOfMaximaInRange(maxima);
                  } else {
                      try {
                        totalMaximaInRange[phase].merge(maxima);
                      } catch(Exception io) {
                          ErrorFrame fr = new ErrorFrame(io.toString());
                      }
                  }
             }
         }
       }
    }
    void computeTotals() {
       DefaultTableModel totmodel = (DefaultTableModel) totalNecessarySpeciesTable.getModel();
       int numphases = totalMaximaInRange.length;
       for(int phase = 0;phase < numphases;phase++) {
           System.out.println("Phase: " + phase);
           double slide = -4.0;
           double slide10 = Math.pow(10.0,slide);
           Double limitD = new Double(slide10);
           double limit = limitD.doubleValue();
           Boolean showit = new Boolean(false);
           System.out.println("Display Phase: " + phase);
           Integer phaseI = new Integer(phase);
         //if(totalMaximaInRange[phase] != null) {
                SetOfMaximaInRange maxima = totalMaximaInRange[phase];
                //Double limitD = new Double(-(limitSlider.getValue()/10));
                //Double limitD = new Double(-3.0);
                //double slide = -(limitSlider.getValue()/10);
                //double limit = limitD.doubleValue();
                if(maxima != null) {
                    Integer numSpecies = new Integer(maxima.numberAboveLimit(limit));
                    Integer numInRange = new Integer(maxima.numberInRange());
                    totmodel.addRow(new Object[] {phaseI,numInRange,numSpecies,showit});
                } else {
                    Integer numSpecies = new Integer(0);
                    Integer numInRange = new Integer(0);
                    totmodel.addRow(new Object[] {phaseI,numInRange,numSpecies,showit});                    
                }
               DisplaySpecies[phase] = new DisplayNecessarySpecies(limit,totalMaximaInRange[phase],
                                     POSMdirField.getText(),phase);
                DisplaySpecies[phase].hide();   
                /*
           } else {
                 DisplaySpecies[phase] = new DisplayNecessarySpecies(limit,totalMaximaInRange[phase],
                                     POSMdirField.getText(),phase);
                DisplaySpecies[phase].hide();              
           }
                 **/
       }
    }
    void setUpNecessaryTables() {
        int rowCount = totalNecessarySpeciesTable.getRowCount();
        ListSelectionModel rowSM = totalNecessarySpeciesTable.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(e);
                if (e.getValueIsAdjusting()) return;

                ListSelectionModel lsm =
                    (ListSelectionModel)e.getSource();
                if (lsm.isSelectionEmpty()) {
                    
                } else {
                    int selectedRow = lsm.getMinSelectionIndex();
                    DefaultTableModel tmodel = (DefaultTableModel) totalNecessarySpeciesTable.getModel();
                    
                    Boolean ans = (Boolean) tmodel.getValueAt(selectedRow,3);
                    boolean setvalue = !ans.booleanValue();
                    tmodel.setValueAt(new Boolean(setvalue),selectedRow,3);
                    System.out.println("Display " + selectedRow + "\t " + setvalue);
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
        jPanel1 = new javax.swing.JPanel();
        computeTotalNecessity = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        baseDirSelectButton = new javax.swing.JButton();
        POSMdirField = new javax.swing.JTextField();
        dirsetupButton = new javax.swing.JButton();
        totalNecessarySpeciesTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(3, 1));

        computeTotalNecessity.setText("Compute Total Species");
        computeTotalNecessity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                computeTotalNecessityMouseClicked(evt);
            }
        });

        jPanel1.add(computeTotalNecessity);

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

        dirsetupButton.setText("Set up Directories");
        dirsetupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dirsetupButtonMouseClicked(evt);
            }
        });

        jPanel1.add(dirsetupButton);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        totalNecessarySpeciesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Submechanism", "Total", "Reduced", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalNecessarySpeciesTable.setRowSelectionAllowed(false);
        add(totalNecessarySpeciesTable, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

    private void computeTotalNecessityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_computeTotalNecessityMouseClicked
        computeNecessaryBoundaryTable();
    }//GEN-LAST:event_computeTotalNecessityMouseClicked

    private void baseDirSelectButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baseDirSelectButtonMouseClicked
      if(POSMchooser == null) {
         POSMchooser = new JFileChooser();
         POSMchooser.setFileFilter(POSMchooserFilter);
      }
      POSMchooser.showOpenDialog(this);
      File chosenDir  = POSMchooser.getSelectedFile();
      POSMdirField.setText(chosenDir.toString());

    }//GEN-LAST:event_baseDirSelectButtonMouseClicked

    private void dirsetupButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dirsetupButtonMouseClicked
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
       
    }//GEN-LAST:event_dirsetupButtonMouseClicked
    int computeNumberOfSubmechs() {
       DefaultTableModel tmodel = (DefaultTableModel) boundaryNecessarySpeciesTable.getModel();
       int numphases = tmodel.getRowCount();
       int topphase = 0;
       for(int i=0;i<numphases;i++) {
           Integer phaseI = (Integer) tmodel.getValueAt(i,0);
           if(phaseI.intValue() > topphase) 
                topphase = phaseI.intValue();
       }
       return topphase+1;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField POSMdirField;
    private javax.swing.JButton baseDirSelectButton;
    private javax.swing.JButton computeTotalNecessity;
    private javax.swing.JButton dirsetupButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTable totalNecessarySpeciesTable;
    // End of variables declaration//GEN-END:variables
    
}
