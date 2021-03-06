/*
 * ChemkinPSRAnalysis.java
 *
 * Created on October 22, 2006, 9:34 AM
 */

package react.mechanisms.chemkin.psr;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import utilities.ErrorFrame;
import utilities.FileFrame;
import utilities.StringToFile;

/**
 *
 * @author  reaction
 */
public class ChemkinPSRAnalysis extends javax.swing.JPanel {
    String defaultDirectory;
    int nTemperatures = 0;
    int nSpecies      = 0;
    Vector temperatureSets;
    String[] specieNames;
    String[] headerNames;
    Vector temperatures;
    class PSRFilter extends FileFilter {
        
        PSRFilter() {}
        public boolean accept(File f) {
            
            String name = f.toString();
            boolean ans1 = name.endsWith(".out");
            boolean ans2 = name.startsWith("psr") || name.startsWith("PSR");
            boolean ans3 = f.isDirectory();
            boolean ans = ans1 || ans2 || ans3;
            //System.out.println(ans + ":   " + f.toString());
            return ans;
        }
        public String getDescription() {
            return "PSR file (starts with psr and ends with out)";
        }
    }
    PSRFilter filter = new PSRFilter();
    /** Creates new form ChemkinPSRAnalysis */
    public ChemkinPSRAnalysis(String dir) {
        defaultDirectory = dir;
        
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        psrTabbedPane = new javax.swing.JTabbedPane();
        fileChoosePane = new javax.swing.JPanel();
        fileChooseScroll = new javax.swing.JScrollPane();
        fileChooseTable = new javax.swing.JTable();
        fileControlPanel = new javax.swing.JPanel();
        chooseFileButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        clearFilesButton = new javax.swing.JButton();
        analysisPanel = new javax.swing.JPanel();
        analysisScroll = new javax.swing.JScrollPane();
        molefractionTable = new javax.swing.JTable();
        analysisButtonPanel = new javax.swing.JPanel();
        analysisButton = new javax.swing.JButton();
        outputPanel = new javax.swing.JPanel();
        analysisOutputButtonPanel = new javax.swing.JPanel();
        outputButton = new javax.swing.JButton();
        clearAnalysisTableButton = new javax.swing.JButton();
        stylePanel = new javax.swing.JPanel();
        withHeaderCheck = new javax.swing.JCheckBox();
        comparePanel = new javax.swing.JPanel();
        compareTablePanel = new javax.swing.JPanel();
        compareTableScroll = new javax.swing.JScrollPane();
        compareTable = new javax.swing.JTable();
        compareInputPanel = new javax.swing.JPanel();
        compareValuesButton = new javax.swing.JButton();
        compareParameterPanel = new javax.swing.JPanel();
        compareRatioLabel = new javax.swing.JLabel();
        compareRatioField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        compareCutoffLabel = new javax.swing.JLabel();
        compareCutoff = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        compareOutputButton = new javax.swing.JButton();
        clearCompareTableButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        fileChoosePane.setLayout(new java.awt.BorderLayout());

        fileChooseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "File Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        fileChooseScroll.setViewportView(fileChooseTable);

        fileChoosePane.add(fileChooseScroll, java.awt.BorderLayout.CENTER);

        fileControlPanel.setLayout(new java.awt.BorderLayout());

        chooseFileButton.setText("Choose PSR Output Files");
        chooseFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseFileButtonMouseClicked(evt);
            }
        });

        fileControlPanel.add(chooseFileButton, java.awt.BorderLayout.CENTER);

        fileChoosePane.add(fileControlPanel, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.GridLayout());

        clearFilesButton.setText("Clear Table");
        clearFilesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearFilesButtonMouseClicked(evt);
            }
        });

        jPanel2.add(clearFilesButton);

        fileChoosePane.add(jPanel2, java.awt.BorderLayout.SOUTH);

        psrTabbedPane.addTab("Files", fileChoosePane);

        analysisPanel.setLayout(new java.awt.BorderLayout());

        molefractionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Species"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        analysisScroll.setViewportView(molefractionTable);

        analysisPanel.add(analysisScroll, java.awt.BorderLayout.CENTER);

        analysisButtonPanel.setLayout(new java.awt.BorderLayout());

        analysisButton.setText("Read in PSR Information");
        analysisButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                analysisButtonMouseClicked(evt);
            }
        });

        analysisButtonPanel.add(analysisButton, java.awt.BorderLayout.CENTER);

        analysisPanel.add(analysisButtonPanel, java.awt.BorderLayout.NORTH);

        outputPanel.setLayout(new java.awt.GridLayout(2, 1));

        analysisOutputButtonPanel.setLayout(new java.awt.GridLayout(1, 2));

        outputButton.setText("Output Selected Species");
        outputButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                outputButtonMouseClicked(evt);
            }
        });

        analysisOutputButtonPanel.add(outputButton);

        clearAnalysisTableButton.setText("Clear Table");
        clearAnalysisTableButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearAnalysisTableButtonMouseClicked(evt);
            }
        });

        analysisOutputButtonPanel.add(clearAnalysisTableButton);

        outputPanel.add(analysisOutputButtonPanel);

        stylePanel.setLayout(new java.awt.GridLayout(1, 1));

        withHeaderCheck.setText("Include Header");
        withHeaderCheck.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        withHeaderCheck.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        stylePanel.add(withHeaderCheck);

        outputPanel.add(stylePanel);

        analysisPanel.add(outputPanel, java.awt.BorderLayout.SOUTH);

        psrTabbedPane.addTab("Analysis", analysisPanel);

        comparePanel.setLayout(new java.awt.BorderLayout());

        compareTablePanel.setLayout(new java.awt.BorderLayout());

        compareTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Species", "Mechanism 1", "Mechanism 2", "Ratio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        compareTableScroll.setViewportView(compareTable);

        compareTablePanel.add(compareTableScroll, java.awt.BorderLayout.CENTER);

        comparePanel.add(compareTablePanel, java.awt.BorderLayout.CENTER);

        compareInputPanel.setLayout(new java.awt.GridLayout(3, 1));

        compareValuesButton.setText("Compare First Two Columns");
        compareValuesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                compareValuesButtonMouseClicked(evt);
            }
        });

        compareInputPanel.add(compareValuesButton);

        compareParameterPanel.setLayout(new java.awt.GridLayout(1, 2));

        compareRatioLabel.setText("Minimal Ratio");
        compareParameterPanel.add(compareRatioLabel);

        compareRatioField.setText("5.0");
        compareParameterPanel.add(compareRatioField);

        compareInputPanel.add(compareParameterPanel);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        compareCutoffLabel.setText("Compare Cutoff");
        jPanel1.add(compareCutoffLabel);

        compareCutoff.setText("1.0e-08");
        jPanel1.add(compareCutoff);

        compareInputPanel.add(jPanel1);

        comparePanel.add(compareInputPanel, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        compareOutputButton.setText("Output Table");
        compareOutputButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                compareOutputButtonMouseClicked(evt);
            }
        });

        jPanel3.add(compareOutputButton);

        clearCompareTableButton.setText("Clear Table");
        clearCompareTableButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearCompareTableButtonMouseClicked(evt);
            }
        });

        jPanel3.add(clearCompareTableButton);

        comparePanel.add(jPanel3, java.awt.BorderLayout.SOUTH);

        psrTabbedPane.addTab("Compare", comparePanel);

        add(psrTabbedPane, java.awt.BorderLayout.CENTER);

    }
    // </editor-fold>//GEN-END:initComponents

    private void compareOutputButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compareOutputButtonMouseClicked
        FileFrame fr  = new FileFrame("PSR Compare File",defaultDirectory,"out");
        DefaultTableModel model = (DefaultTableModel) compareTable.getModel(); 
        DefaultTableModel fmodel = (DefaultTableModel) fileChooseTable.getModel(); 
        if(fr.getFile()) {
            StringBuffer buf = new StringBuffer();
            int rowcount = model.getRowCount();
            buf.append("Comparison of Files:\n");
            buf.append(fmodel.getValueAt(0, 0));
            buf.append("\n");
            buf.append(fmodel.getValueAt(1, 0));
            buf.append("Cutoff      : " + compareCutoff.getText());
            buf.append("\nRatio Cutoff: " + compareRatioField.getText());
            buf.append("\n");
            for(int i=0;i<rowcount;i++) {
                buf.append(model.getValueAt(i,0).toString());
                buf.append("   \t");
                buf.append(model.getValueAt(i,1).toString());
                buf.append("   \t");
                buf.append(model.getValueAt(i, 2).toString());
                buf.append("   \t");
                buf.append(model.getValueAt(i,3).toString());
                buf.append("\n");
            }
            StringToFile fout = new StringToFile();
            try {
                fout.makeFile(fr.chosenFile.toString(),buf.toString());
            } catch(IOException ex) {
                
            }
        }
    }//GEN-LAST:event_compareOutputButtonMouseClicked

    private void clearCompareTableButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearCompareTableButtonMouseClicked
        DefaultTableModel model = (DefaultTableModel) compareTable.getModel();
        while(model.getRowCount() > 0) {
            model.removeRow(model.getRowCount()-1);
        }
    }//GEN-LAST:event_clearCompareTableButtonMouseClicked

    private void clearAnalysisTableButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearAnalysisTableButtonMouseClicked
        DefaultTableModel model = (DefaultTableModel) molefractionTable.getModel();
        while(model.getRowCount() > 0) {
            model.removeRow(model.getRowCount()-1);
        }
    }//GEN-LAST:event_clearAnalysisTableButtonMouseClicked

    private void clearFilesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearFilesButtonMouseClicked
        DefaultTableModel model = (DefaultTableModel) fileChooseTable.getModel();
        while(model.getRowCount() > 0) {
            model.removeRow(model.getRowCount()-1);
        }
        
    }//GEN-LAST:event_clearFilesButtonMouseClicked

    private void compareValuesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compareValuesButtonMouseClicked
        DefaultTableModel model = (DefaultTableModel) molefractionTable.getModel();
        DefaultTableModel comparemodel = (DefaultTableModel) compareTable.getModel();
        try {
            Double cutoffD = new Double(compareCutoff.getText());
            Double ratioD = new Double(compareRatioField.getText());
            double cutoff = cutoffD.doubleValue();
            double ratio = ratioD.doubleValue();
            int rowcount = model.getRowCount();
            for(int i=0;i<rowcount;i++) {
                Double v1 = new Double((String) model.getValueAt(i, 1));
                Double v2 = new Double((String) model.getValueAt(i, 2));
                if((v1.doubleValue() > cutoff) || (v2.doubleValue() > cutoff)) {
                    double computedratio = v1.doubleValue()/v2.doubleValue();
                    double ratioinverse = 1.0/computedratio;
                    if((computedratio > ratio) || (ratioinverse > ratio)) {
                        String name = (String) model.getValueAt(i,0);
                        Double rD = new Double(computedratio);
                        Object[] rowdata = {name, v1,v2,rD};
                        comparemodel.addRow(rowdata);
                    }
                }
            }
        } catch(NumberFormatException ex) {
            ErrorFrame fr = new ErrorFrame("Ratio or Cutoff Value not valid");
            fr.setVisible(true);
        }
        updateUI();
    }//GEN-LAST:event_compareValuesButtonMouseClicked

    private void outputButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outputButtonMouseClicked
        FileFrame fr  = new FileFrame("PSR Output File",defaultDirectory,"out");
        DefaultTableModel model = (DefaultTableModel) molefractionTable.getModel(); 
        if(fr.getFile()) {
            StringBuffer buf = new StringBuffer();

            int[] indicies = molefractionTable.getSelectedRows();
            for(int j=0;j<model.getColumnCount();j++) {
                if(withHeaderCheck.isSelected())
                    buf.append(headerNames[j] + "\t");
                for(int i=0;i<indicies.length;i++) {
                    int row = indicies[i];
                    String element = (String) model.getValueAt(row, j);
                    buf.append(element);
                    buf.append("\t");
                }
                buf.append("\n");
            }
            StringToFile fout = new StringToFile();
            try {
                fout.makeFile(fr.chosenFile.toString(),buf.toString());
            } catch(IOException ex) {
                
            }
        }
 
    }//GEN-LAST:event_outputButtonMouseClicked

    private void analysisButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_analysisButtonMouseClicked
        try {
            readFiles();
            Object[][] matvalues = setupMatrix();
            setUpTable(headerNames, matvalues);
        } catch(IOException ex) {
            ErrorFrame fr = new ErrorFrame("Error in setting up Table:\n" + ex.toString());
            fr.setVisible(true);
        }
    }//GEN-LAST:event_analysisButtonMouseClicked

    private void chooseFileButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseFileButtonMouseClicked
       addFilesToTable();      
    }//GEN-LAST:event_chooseFileButtonMouseClicked
    public void readFiles() throws IOException {
        DefaultTableModel model = (DefaultTableModel) fileChooseTable.getModel();
        int nrows = model.getRowCount();
        temperatures = new Vector();
        temperatureSets = new Vector(nrows);
        for(int i=0;i<nrows;i++) {
            String nameS = (String) model.getValueAt(i, 0);
            File nameF = new File(nameS);
            AnalysePSRFile psr = new AnalysePSRFile(nameF);
            String[] names = psr.getNames();
            if(i==0) {
                specieNames = names;
                nSpecies = names.length;
                nTemperatures = 1;
            } else {
                if(names.length != nSpecies)
                    throw new IOException("Number of Species didn't match: " + nameS);
                nTemperatures++;
            }
            
            Vector values = psr.getValues();
            temperatureSets.add(values);
            temperatures.add(psr.Temperature);
        }
    }
    Object[][] setupMatrix() {
        Object[][] mat = new Object[nSpecies][nTemperatures+1];
        headerNames = new String[nTemperatures + 1];
        headerNames[0] = new String("Species");
        for(int i=0;i<nTemperatures;i++) {
            Double t = (Double) temperatures.elementAt(i);
            headerNames[i+1] = t.toString();
            Vector values = (Vector) temperatureSets.elementAt(i);
            for(int j=0;j<nSpecies;j++) {
                mat[j][i+1] = values.elementAt(j).toString();
            }
        }
        for(int j=0;j<nSpecies;j++) {
              mat[j][0] = specieNames[j];
        }

        return mat;
    }
    void setUpTable(String[] headernames, 
                    Object[][] matvalues) {
       molefractionTable.setModel(
               new PSRTableModel(matvalues,headernames));
       DefaultTableModel model = (DefaultTableModel) molefractionTable.getModel(); 
       updateUI();
    }
    public void addFilesToTable() {
        DefaultTableModel model = (DefaultTableModel) fileChooseTable.getModel();        
        JFileChooser f = new JFileChooser(defaultDirectory);
        f.setMultiSelectionEnabled(true);
        boolean ans = true;
	f.addChoosableFileFilter(filter);
	int returnVal = f.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File[] files  = f.getSelectedFiles();
            if(files.length > 0)
                defaultDirectory = files[0].getParent();
            for(int i=0;i<files.length;i++) {
                System.out.println("File Chosen: " + files[i].toString());
                Object[] objs = {files[i].toString()};
                model.addRow(objs);
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analysisButton;
    private javax.swing.JPanel analysisButtonPanel;
    private javax.swing.JPanel analysisOutputButtonPanel;
    private javax.swing.JPanel analysisPanel;
    private javax.swing.JScrollPane analysisScroll;
    private javax.swing.JButton chooseFileButton;
    private javax.swing.JButton clearAnalysisTableButton;
    private javax.swing.JButton clearCompareTableButton;
    private javax.swing.JButton clearFilesButton;
    private javax.swing.JTextField compareCutoff;
    private javax.swing.JLabel compareCutoffLabel;
    private javax.swing.JPanel compareInputPanel;
    private javax.swing.JButton compareOutputButton;
    private javax.swing.JPanel comparePanel;
    private javax.swing.JPanel compareParameterPanel;
    private javax.swing.JTextField compareRatioField;
    private javax.swing.JLabel compareRatioLabel;
    private javax.swing.JTable compareTable;
    private javax.swing.JPanel compareTablePanel;
    private javax.swing.JScrollPane compareTableScroll;
    private javax.swing.JButton compareValuesButton;
    private javax.swing.JPanel fileChoosePane;
    private javax.swing.JScrollPane fileChooseScroll;
    private javax.swing.JTable fileChooseTable;
    private javax.swing.JPanel fileControlPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTable molefractionTable;
    private javax.swing.JButton outputButton;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JTabbedPane psrTabbedPane;
    private javax.swing.JPanel stylePanel;
    private javax.swing.JCheckBox withHeaderCheck;
    // End of variables declaration//GEN-END:variables
    
}
