/*
 * DecisionTreeProgramOut.java
 *
 * Created on September 22, 2004, 12:10 PM
 */

package blurock.DecisionTree;
import java.util.Hashtable;
import java.util.ArrayList;
import blurock.DecisionTree.*;
import blurock.logic.predicates.BaseDataPredicate;
import blurock.logic.predicates.BaseDataPredicateAsNonFuzzyPyramid;
import blurock.DecisionTree.BaseDataDecisionTreeNodeStats;
import blurock.logic.description.*;
import blurock.core.ObjectNotFoundException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Hashtable;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import javax.swing.JTable;
import utilities.ErrorFrame;
import utilities.FileFrame;
import blurock.core.RWManager;
import utilities.OutputFrame;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Dimension;
import javax.swing.table.JTableHeader;
import ignition.XandGNChooser;
import ignition.XMatrix;
import ignition.SetOfMaximaInRange;
import ignition.DisplayNecessarySpecies;
import blurock.PhaseBoundary.PhaseBoundaryPanel;
import blurock.PhaseBoundary.NecessarySpeciesPanel;
import blurock.PhaseBoundary.TotalNecessityPanel;
/**
 *
 * @author  reaction
 */
public class DecisionTreeProgramOut extends javax.swing.JPanel {
    XandGNChooser filter = new XandGNChooser("X","GN");
    File defaultDir = new File(".");
    FileFrame chooser = null;
    XMatrix XMatrixRuns[];
    public TotalNecessityPanel totNecPanel = null;
    String THERMOUT = new String("THERMOUT81.txt");
    String MECHOUT  = new String("MECOUT_801.txt");
    public JTable speciesTable = new JTable();
    BaseDataDecisionTree DecisionTree;
    public Hashtable ParameterTranslations;
    Hashtable predicates = new Hashtable();
    ArrayList parameters = new ArrayList();
    public Hashtable PredicateTranslations;
    public Vector SubMechanismInfo;
    public SubMechanismData FullMechanismData;
    boolean mechanismReadIn = false;
    DecisionTreeFORTRAN decFortran;
    DecisionTreeAsJAVA  decJava;
    /** Creates new form DecisionTreeProgramOut */
    public DecisionTreeProgramOut(BaseDataDecisionTree tree) {
        DecisionTree = tree;
        initComponents();
        javax.swing.table.TableColumn column = fileTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(300);
        column = fileTable.getColumnModel().getColumn(1);
        column.setPreferredWidth(300);
        column = fileTable.getColumnModel().getColumn(2);
        column.setPreferredWidth(50);

        JTableHeader header =   fileTable.getTableHeader();
        rawDataPanel.add(header,java.awt.BorderLayout.SOUTH);

        
        listOfPredicates();
        listOfParameters();
        parameterTable(false);
        ParameterTranslations = new Hashtable();
        updateParameterTranslations();
        predicateTable(false);
        PredicateTranslations = new Hashtable();
        updatePredicateTranslations();
        goalTable();
    }
    void update() {
        System.out.println("Update");
        updateParameterTranslations();
        updatePredicateTable();
        updatePredicateTranslations();
    }
    void updatePredicateTable() {
      DefaultTableModel tmodel = (DefaultTableModel) predicateTable.getModel();
      Object[] preds = predicates.values().toArray();
      int rowcount = tmodel.getRowCount();
      for(int i=0;i<rowcount;i++) {
            BaseDataPredicate pred = (BaseDataPredicate) preds[i];
            try {
                BaseDataPredicateAsNonFuzzyPyramid p = 
                    new BaseDataPredicateAsNonFuzzyPyramid(pred,ParameterTranslations);
                tmodel.setValueAt(p.toString(),i,2);
            } catch (ClassCastException ex) {
                System.err.println("Type not supported yet: " + pred.Type);
            }
        }       
    }
    void updatePredicateTranslations() {
      DefaultTableModel tmodel = (DefaultTableModel) predicateTable.getModel();
      int rowcount = tmodel.getRowCount();
      for(int i=0;i<rowcount;i++) {
      String predname   = (String) tmodel.getValueAt(i,0);
      String predtrans  = (String) tmodel.getValueAt(i,2);
      PredicateTranslations.put(predname,predtrans);
      }
    }
    void updateParameterTranslations() {
      DefaultTableModel tmodel = (DefaultTableModel) parameterTable.getModel();
      int rowcount = tmodel.getRowCount();
      for(int i=0;i<rowcount;i++) {
          String name = (String) tmodel.getValueAt(i,0);
          String translate = (String) tmodel.getValueAt(i,1);
          ParameterTranslations.put(name,translate);
      }
    }
    
    void listOfPredicates() {
        Object[] objs = DecisionTree.Tree.setOfNodes();
        System.out.println("listOfPredicates()");
        for(int i=0;i<objs.length;i++) {
            BaseDataDecisionTreeNodeStats node = (BaseDataDecisionTreeNodeStats) objs[i];
            BaseDataPredicate pred = getPredicate(node);
            if(pred != null) {
                if(!predicates.containsKey(node.Name)) {
                    predicates.put(node.Name,pred);
                    System.out.println(node.Name + " Predicate: " + pred.Name);
                }
            } else {
                //System.out.println("no elements: " + node.Name);
            }
        }
    }
    BaseDataPredicate getPredicate(BaseDataDecisionTreeNodeStats node) {
       BaseDataConjunction conj = node.Conjunction;
       BaseDataDescription descr = conj.setOfLogicOperations();
       BaseDataPredicate pred = null;
        String name = node.Name;
        int i = name.indexOf("#");
        String predname = name.substring(i+1);
       try {
        pred = (BaseDataPredicate) descr.findObject(predname);
       } catch(ObjectNotFoundException ex) {
           System.out.println("predicate not found, using last: " + predname);
           pred = (BaseDataPredicate) descr.lastObject();
       }
        return pred;
    }
    void listOfParameters() {
        Object[] preds = predicates.values().toArray();
        System.out.println("listOfNames()" + preds.length);
        
        for(int i=0;i<preds.length;i++) {
            BaseDataPredicate pred = (BaseDataPredicate) preds[i];
            String name = pred.Parameter;
            System.out.println("Predicate: " + pred.Name + "Parameter: " + name);
            if(!parameters.contains(name)) {
                parameters.add(name);
                System.out.println(name);
            }
        }
    }
    void predicateTable(boolean update) {
      DefaultTableModel tmodel = (DefaultTableModel) predicateTable.getModel();
        Object[] preds = predicates.values().toArray();
        for(int i=0;i<preds.length;i++) {
            BaseDataPredicate pred = (BaseDataPredicate) preds[i];
            try {
                BaseDataPredicateAsNonFuzzyPyramid p = 
                    new BaseDataPredicateAsNonFuzzyPyramid(pred,ParameterTranslations);
                if(update)
                    tmodel.setValueAt(p.toString(),i,2);
                else
                    tmodel.addRow(new Object[] {pred.Name,new Boolean(p.step),p.toString()});
                    
            } catch (ClassCastException ex) {
                System.err.println("Type not supported yet: " + pred.Type);
            }
        }        
        //int r2 = tmodel.getRowCount();
        //tmodel.fireTableRowsInserted(0,r2);
    }
    void parameterTable(boolean update) {
      DefaultTableModel tmodel = (DefaultTableModel) parameterTable.getModel();
      Object[] objs = parameters.toArray();
      for(int i=0;i<objs.length;i++) {
          String name = (String) objs[i];
          if(mechanismReadIn) {
              SpeciesTableModel smodel = (SpeciesTableModel) speciesTable.getModel();
              int pos = FullMechanismData.getSpeciesPosition(name);
              if(pos >= 0) {
                  int subscript = pos;
                  if(fortranOUTCheck.isSelected()) {
                      subscript = subscript + 1;
                  }
                Integer nameI = (Integer) new Integer(subscript);
                name = nameI.toString();
              } else {
                  System.err.println("Species not found:" + name + ":");
                  System.err.println(FullMechanismData.MoleculeNames.size());
                  System.err.println(FullMechanismData.MoleculeNames.indexOf(name));
                  for(int k=0;k<FullMechanismData.MoleculeNames.size();k++) {
                      System.err.println(FullMechanismData.MoleculeNames.get(k) + ":"+ name + ":" );
                  }
              }
          }
          String concname = "Concentration[" + name + "]";
          if(fortranOUTCheck.isSelected()) {
              concname = "Concentration(" + name + ")";              
          }
          if(update) 
              tmodel.setValueAt(concname,i,1);
          else
            tmodel.addRow(new Object[] {name,concname});
      }
        //int r2 = tmodel.getRowCount();
        //tmodel.fireTableRowsInserted(0,r2);
    }
    void goalTable() {
        try {
            DefaultTableModel tmodel = (DefaultTableModel) goalTable.getModel();
            BaseDataDecisionTreeNodeStats node = (BaseDataDecisionTreeNodeStats)
            DecisionTree.Tree.findObject("Root");
            for(int i=0;i<node.goalPartitionCount;i++) {
                Integer countI = new Integer(i);
                String dir = new String("mech" + countI.toString());
                tmodel.addRow(new Object[] {countI,countI.toString(),dir,new Integer(0),new Integer(0)});
            }
            Integer countI = new Integer(node.goalPartitionCount);
            String dir = new String("Full");
            tmodel.addRow(new Object[] {countI,new String(dir),dir,new Integer(0),new Integer(0)});
        }  catch(ObjectNotFoundException ex) {
        }
    }
    public int determineNodeGoal(BaseDataDecisionTreeNodeStats node) {
        double top = node.GoalStats[0];
        int goal = 0;
        for(int i=1;i<node.goalPartitionCount;i++) {
            double g = node.GoalStats[i];
            if(g > top) {
                top = node.GoalStats[i];
                goal = i;
            }
        }
        return goal;
    }
    void readMechanismInformation()  throws IOException {
        DefaultTableModel tmodel = (DefaultTableModel) goalTable.getModel();
        SubMechanismInfo = new Vector();
        FullMechanismData = new SubMechanismData();
        fillInSpeciesData();
        mechanismReadIn = true;
    }
    void fillInSpeciesData()  throws IOException {
        readFullMechanismSpecies();
        readSubMechanismSpecies();
        readFullMechanismReactions();
        readSubMechanismReactions();
        makeSpeciesTable();
    }
    void readFullMechanismSpecies() throws IOException {
        File fullDir = new File(TopPOSMDirectoryField.getText(),"Full");
        File fullMechanismTHERMOFile = new File(fullDir,THERMOUT);
        RWManager rwfullThermo = new RWManager();
        rwfullThermo.openManager(fullMechanismTHERMOFile.toString(),true);
        FullMechanismData.readSpeciesData(rwfullThermo);
    }
    void readFullMechanismReactions() throws IOException {
        File fullDir = new File(TopPOSMDirectoryField.getText(),"Full");
        File fullMechanismMECOUTFile = new File(fullDir,MECHOUT);
        RWManager rwfullmech = new RWManager();
        rwfullmech.openManager(fullMechanismMECOUTFile.toString(),true);
        FullMechanismData.readReactionData(rwfullmech);
    }
    void readSubMechanismSpecies() throws IOException {
        DefaultTableModel tmodel = (DefaultTableModel) goalTable.getModel();
        int numsubs = tmodel.getRowCount() - 1;
        for(int i=0;i< numsubs;i++) {
            SubMechanismData submech =  new SubMechanismData();
            SubMechanismInfo.add(submech);
            Integer iI = new Integer(i);
            String mechdirname = (String) tmodel.getValueAt(i,2);
            File subDir = new File(TopPOSMDirectoryField.getText(),mechdirname);
            File subTHERMO = new File(subDir,THERMOUT);
            System.out.println("================= " + subTHERMO.toString());
            RWManager rwsubThermo = new RWManager();
            rwsubThermo.openManager(subTHERMO.toString(),true);
            
            submech.readSpeciesData(rwsubThermo);
        }
    }
    void readSubMechanismReactions() throws IOException {
        DefaultTableModel tmodel = (DefaultTableModel) goalTable.getModel();
        int numsubs = tmodel.getRowCount() - 1;
        for(int i=0;i< numsubs;i++) {
            SubMechanismData submech =  (SubMechanismData) SubMechanismInfo.elementAt(i);
            Integer iI = new Integer(i);
            String mechdirname = (String) tmodel.getValueAt(i,2);
            File subDir = new File(TopPOSMDirectoryField.getText(),mechdirname);
            File subMECH = new File(subDir,MECHOUT);
            System.out.println("================= " + subMECH.toString());
            RWManager rwsubmech = new RWManager();
            rwsubmech.openManager(subMECH.toString(),true);
            submech.readReactionData(rwsubmech);
        }
    }
    void makeSpeciesTable() {
        DefaultTableModel tmodel = (DefaultTableModel) goalTable.getModel();
        int numsubmechs = SubMechanismInfo.size();
        int totalspecies = FullMechanismData.numberOfSpecies();
        Object[][] data = new Object[totalspecies][numsubmechs+1];
        for(int i=0;i<totalspecies;i++){
            String name = FullMechanismData.getSpeciesI(i);
            data[i][0] = name;
            for(int j=0;j<numsubmechs;j++) {
                SubMechanismData submech = (SubMechanismData) SubMechanismInfo.get(j);
                if(submech.speciesPresent(name)) {
                    submech.getSpeciesPosition(name);
                    data[i][j+1] = new Integer(submech.getSpeciesPosition(name));
                } else {
                    data[i][j+1] = new Integer(-1);
                }
            }
        }
        System.out.println(numsubmechs);
        String[] cols = new String[numsubmechs+1];
        cols[0] = new String("Species");
        for(int j=0;j<numsubmechs;j++) {
            SubMechanismData submech = (SubMechanismData) SubMechanismInfo.get(j);
            tmodel.setValueAt(new Integer(submech.numberOfSpecies()),j,3);
            tmodel.setValueAt(new Integer(submech.numberOfReactions()),j,4);
            String mechdirname = (String) tmodel.getValueAt(j,2);
            cols[j+1] = mechdirname;
        }
        System.out.println(numsubmechs);
        tmodel.setValueAt(new Integer(FullMechanismData.numberOfSpecies()),numsubmechs,3);
        tmodel.setValueAt(new Integer(FullMechanismData.numberOfReactions()),numsubmechs,4);
        SpeciesTableModel specmod = new SpeciesTableModel(data,cols);
        speciesTable.setModel(specmod);
        speciesScrollPanel.setViewportView(speciesTable);
        //speciesScrollPanel.getC (speciesTable);
        //speciesPanel.add(speciesTable,java.awt.BorderLayout.CENTER);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jTabbedPane1 = new javax.swing.JTabbedPane();
        treePanel = new javax.swing.JPanel();
        rawDataPanel = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        browseButton = new javax.swing.JButton();
        readMatrixButton = new javax.swing.JButton();
        fileTable = new javax.swing.JTable();
        phasePanel = new javax.swing.JPanel();
        necessaryPanel = new javax.swing.JPanel();
        totalNecessityPanel = new javax.swing.JPanel();
        goalPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        POSMDirButton = new javax.swing.JButton();
        TopPOSMDirectoryField = new javax.swing.JTextField();
        goalTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        readMechButton = new javax.swing.JButton();
        parameterPanel = new javax.swing.JPanel();
        updateParameterNamesButton = new javax.swing.JButton();
        parameterTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        fortranOUTCheck = new javax.swing.JCheckBox();
        predicatePanel = new javax.swing.JPanel();
        predicateTable = new javax.swing.JTable();
        speciesPanel = new javax.swing.JPanel();
        speciesScrollPanel = new javax.swing.JScrollPane();
        codePanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        writeButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        javaCheck = new javax.swing.JCheckBox();
        fortranCheck = new javax.swing.JCheckBox();
        asciiCheck = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        rootDirButton = new javax.swing.JButton();
        rootDirField = new javax.swing.JTextField();
        programOutPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        treePanel.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.addTab("Tree", treePanel);

        rawDataPanel.setLayout(new java.awt.BorderLayout());

        rawDataPanel.setBorder(new javax.swing.border.TitledBorder("Read"));
        jPanel51.setLayout(new java.awt.GridLayout(2, 0));

        browseButton.setText("Browse");
        browseButton.setToolTipText("Browse for files");
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseButtonMouseClicked(evt);
            }
        });

        jPanel51.add(browseButton);

        readMatrixButton.setText("Read");
        readMatrixButton.setToolTipText("Read in the file");
        readMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readMatrixButtonActionPerformed(evt);
            }
        });
        readMatrixButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                readMatrixButtonMouseClicked(evt);
            }
        });

        jPanel51.add(readMatrixButton);

        rawDataPanel.add(jPanel51, java.awt.BorderLayout.NORTH);

        fileTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mass Fractions", "Necessity", "Title 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        fileTable.setDragEnabled(true);
        fileTable.setEditingColumn(1);
        fileTable.setEditingRow(1);
        fileTable.setFocusCycleRoot(true);
        fileTable.setRowSelectionAllowed(false);
        rawDataPanel.add(fileTable, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Data", rawDataPanel);

        phasePanel.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.addTab("Phases", phasePanel);

        necessaryPanel.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.addTab("Necessary", necessaryPanel);

        totalNecessityPanel.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.addTab("Total", null, totalNecessityPanel, "Total Necessity over several ranges");

        goalPanel.setLayout(new java.awt.BorderLayout());

        goalPanel.setBorder(new javax.swing.border.TitledBorder("Goal Translation"));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        POSMDirButton.setText("POSM Directory");
        POSMDirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                POSMDirButtonActionPerformed(evt);
            }
        });

        jPanel5.add(POSMDirButton);

        TopPOSMDirectoryField.setText("POSM");
        TopPOSMDirectoryField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TopPOSMDirectoryFieldActionPerformed(evt);
            }
        });

        jPanel5.add(TopPOSMDirectoryField);

        goalPanel.add(jPanel5, java.awt.BorderLayout.NORTH);

        goalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        goalPanel.add(goalTable, java.awt.BorderLayout.CENTER);

        jPanel6.setLayout(new java.awt.GridLayout(1, 1));

        readMechButton.setText("Read Mechanism Data");
        readMechButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                readMechButtonMouseClicked(evt);
            }
        });

        jPanel6.add(readMechButton);

        goalPanel.add(jPanel6, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("Goal", goalPanel);

        parameterPanel.setLayout(new java.awt.BorderLayout());

        parameterPanel.setBorder(new javax.swing.border.TitledBorder("Parameter Translation"));
        updateParameterNamesButton.setText("Update From Species Information");
        updateParameterNamesButton.setToolTipText("IOf the mechanism has been read in, then the indicies can be used");
        updateParameterNamesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateParameterNamesButtonMouseClicked(evt);
            }
        });

        parameterPanel.add(updateParameterNamesButton, java.awt.BorderLayout.NORTH);

        parameterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Title 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        parameterTable.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                parameterTableInputMethodTextChanged(evt);
            }
        });

        parameterPanel.add(parameterTable, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridLayout());

        fortranOUTCheck.setSelected(true);
        fortranOUTCheck.setText(" FORTRAN Output");
        jPanel1.add(fortranOUTCheck);

        parameterPanel.add(jPanel1, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("Parameters", parameterPanel);

        predicatePanel.setLayout(new java.awt.BorderLayout());

        predicatePanel.setBorder(new javax.swing.border.TitledBorder("Predicate Translation"));
        predicateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Usage", "Translation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        predicateTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                predicateTableMouseClicked(evt);
            }
        });

        predicatePanel.add(predicateTable, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Predicates", predicatePanel);

        speciesPanel.setLayout(new java.awt.BorderLayout());

        speciesPanel.add(speciesScrollPanel, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Species", speciesPanel);

        codePanel.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(3, 1));

        jPanel2.setBorder(new javax.swing.border.TitledBorder("Output Panel"));
        writeButton.setText("Write");
        writeButton.setToolTipText("Write out decision tree to the directory named 'chemistry' in the root directory");
        writeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                writeButtonMouseClicked(evt);
            }
        });

        jPanel2.add(writeButton);

        jPanel3.setLayout(new java.awt.GridLayout(1, 3));

        javaCheck.setText("JAVA");
        jPanel3.add(javaCheck);

        fortranCheck.setSelected(true);
        fortranCheck.setText("FORTRAN");
        jPanel3.add(fortranCheck);

        asciiCheck.setText("ASCII");
        jPanel3.add(asciiCheck);

        jPanel2.add(jPanel3);

        jPanel7.setLayout(new java.awt.GridLayout(1, 2));

        rootDirButton.setText("Root Directory");
        rootDirButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rootDirButtonMouseClicked(evt);
            }
        });

        jPanel7.add(rootDirButton);

        rootDirField.setText("POSM");
        rootDirField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rootDirFieldActionPerformed(evt);
            }
        });

        jPanel7.add(rootDirField);

        jPanel2.add(jPanel7);

        codePanel.add(jPanel2, java.awt.BorderLayout.NORTH);

        programOutPanel.setLayout(new java.awt.BorderLayout());

        codePanel.add(programOutPanel, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Code", codePanel);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);

    }
    // </editor-fold>//GEN-END:initComponents

    private void POSMDirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_POSMDirButtonActionPerformed
       FileFrame fr = new FileFrame();
       fr.defaultDirectory = chooser.defaultDirectory;
       boolean ans = fr.chooseDirectory();
       if(ans) {
           TopPOSMDirectoryField.setText(fr.defaultDirectory);
       }
    }//GEN-LAST:event_POSMDirButtonActionPerformed

    private void rootDirFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rootDirFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rootDirFieldActionPerformed

    private void rootDirButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rootDirButtonMouseClicked
        rootDirField.setText(chooser.defaultDirectory);
    }//GEN-LAST:event_rootDirButtonMouseClicked

    private void readMatrixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readMatrixButtonActionPerformed
        
    }//GEN-LAST:event_readMatrixButtonActionPerformed

    private void readMatrixButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readMatrixButtonMouseClicked
       try {
            DefaultTableModel tmodel = (DefaultTableModel) fileTable.getModel();
            int numRows = tmodel.getRowCount();
            
            XMatrixRuns = new XMatrix[numRows];
            for(int i=0;i< numRows; i++) {
                File Xname = (File) tmodel.getValueAt(i,0);
                System.out.println(Xname);
                XMatrixRuns[i] = new XMatrix(Xname);
            }
       } catch(IOException ex) {
           new ErrorFrame(ex.toString()).show();
       }
    PhaseBoundaryPanel phasep = new PhaseBoundaryPanel(fileTable, XMatrixRuns);
    phasep.computeBoundaries();
    phasePanel.add(phasep,java.awt.BorderLayout.CENTER);
    NecessarySpeciesPanel necPanel = new NecessarySpeciesPanel(fileTable,phasep.phaseBoundaryTable,this);
    necessaryPanel.add(necPanel,java.awt.BorderLayout.CENTER);
    totNecPanel = new TotalNecessityPanel(phasep.phaseBoundaryTable,
                                          necPanel.boundaryNecessarySpeciesTable);
    totalNecessityPanel.add(totNecPanel,java.awt.BorderLayout.CENTER);
    }//GEN-LAST:event_readMatrixButtonMouseClicked

    private void browseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseButtonMouseClicked
        DefaultTableModel tmodel = (DefaultTableModel) fileTable.getModel();
        if(chooser == null) {
            chooser = new FileFrame("Select Concentration File",".",filter);
        }
        try {
            File[] files = chooser.getChoosenFiles();
            for(int f=0;f<files.length;f++) {
                File Xname = files[f];;
                System.out.println(Xname);
                int r1 = tmodel.getRowCount();
                String[] GNnames = getGNNames(Xname);

                for(int i=0;i<GNnames.length;i++) {
                    System.out.println(GNnames[i]);
                    tmodel.addRow(new Object[] {Xname,GNnames[i], new Boolean(true)});
                }
             int r2 = tmodel.getRowCount();
              tmodel.fireTableRowsInserted(r1,r2);
            }
        } catch(IOException io) {
            System.out.println("No files selected");
        }
    }//GEN-LAST:event_browseButtonMouseClicked

    String[] getGNNames(File XFile) {
        Vector namesV = new Vector();
        String Xname = XFile.getName();
           int l = Xname.length();
             String specs = Xname.substring(5,l-4);
            System.out.println(specs);
            File parent = XFile.getAbsoluteFile().getParentFile();
            String[] namelist = parent.list();
            for(int i=0; i< namelist.length; i++) {
                if(namelist[i].startsWith("GN") && 
                  namelist[i].endsWith(".txt")  &&
                  namelist[i].indexOf(specs) > 0) {
                      namesV.add(namelist[i]);
                }
            }
            String[] namesS = new String[namesV.size()];
            for(int i=0;i<namesV.size();i++) {
                namesS[i] = (String) namesV.elementAt(i);
            }
        return namesS;
    }

    private void updateParameterNamesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateParameterNamesButtonMouseClicked
        if(mechanismReadIn) {
        parameterTable(true);
        updateParameterTranslations();
        predicateTable(true);
        updatePredicateTranslations();
        } else {
            OutputFrame fr = new OutputFrame("Mechanism data not read in yet, no action");
            fr.show();
        }
    }//GEN-LAST:event_updateParameterNamesButtonMouseClicked

    private void writeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_writeButtonMouseClicked
        decFortran = new DecisionTreeFORTRAN(this);
        decJava = new DecisionTreeAsJAVA(this);
        if(javaCheck.isSelected()) {
            OutputFrame jfr = new OutputFrame(decJava.toString());
            jfr.show();
        }
        if(fortranCheck.isSelected()) {
            String basedir = new String(rootDirField.getText());
            File dirF = new File(basedir,"chemistry");
            String dir = dirF.toString();
            File mechdetailsF = new File(dir,new String("LundMechanismDetails.f90"));
            File statemodelF = new File(dir,new String("LundStateFunctionModule.f90"));
            File kineticmodelF = new File(dir,new String("LundKineticModel.f90"));
            File phasereductionF = new File(dir,new String("PhaseReductionModel.f90"));
            decFortran.stringImportance(basedir);
            try {
                String lundstateS = decFortran.stringLundStateFunction();
                FileWriter lundstateStr = new FileWriter(statemodelF);
                PrintWriter lundstateP = new PrintWriter(lundstateStr);
                lundstateP.print(lundstateS);
                lundstateP.close();
                
                String mechdetailsS = decFortran.stringMechanismDetails();
                FileWriter mechdetailsW = new FileWriter(mechdetailsF);
                PrintWriter mechdetailsP = new PrintWriter(mechdetailsW);
                mechdetailsP.print(mechdetailsS);
                mechdetailsP.close();
                
                String kineticmodelS = decFortran.stringLundKineticModule();
                FileWriter kineticmodelW = new FileWriter(kineticmodelF);
                PrintWriter kineticmodelP = new PrintWriter(kineticmodelW);
                kineticmodelP.print(kineticmodelS);
                kineticmodelP.close();
                
                String phasereductionS = decFortran.stringPhaseReductionModule();
                FileWriter phasereductionW = new FileWriter(phasereductionF);
                PrintWriter phasereductionP = new PrintWriter(phasereductionW);
                phasereductionP.print(phasereductionS);
                phasereductionP.close();
                
            } catch(FileNotFoundException io) {
                ErrorFrame fr = new ErrorFrame("FORTRAN file could not be written:\n" + io);
            } catch(IOException io) {
                ErrorFrame fr = new ErrorFrame("FORTRAN file could not be written:\n" + io);
            }
            
        }
        if(asciiCheck.isSelected()) {
            OutputFrame ffr = new OutputFrame(decFortran.toString());
            ffr.show();
        }

    }//GEN-LAST:event_writeButtonMouseClicked

    private void readMechButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readMechButtonMouseClicked
      try {
            readMechanismInformation();
      } catch(IOException ex) {
          ErrorFrame fr = new ErrorFrame(ex.toString());
          fr.show();
      }

    }//GEN-LAST:event_readMechButtonMouseClicked

    private void TopPOSMDirectoryFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TopPOSMDirectoryFieldActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_TopPOSMDirectoryFieldActionPerformed

    private void predicateTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_predicateTableMouseClicked
        update();
    }//GEN-LAST:event_predicateTableMouseClicked

    private void parameterTableInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_parameterTableInputMethodTextChanged
       update();
    }//GEN-LAST:event_parameterTableInputMethodTextChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton POSMDirButton;
    private javax.swing.JTextField TopPOSMDirectoryField;
    private javax.swing.JCheckBox asciiCheck;
    public javax.swing.JButton browseButton;
    private javax.swing.JPanel codePanel;
    private javax.swing.JTable fileTable;
    private javax.swing.JCheckBox fortranCheck;
    private javax.swing.JCheckBox fortranOUTCheck;
    private javax.swing.JPanel goalPanel;
    public javax.swing.JTable goalTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox javaCheck;
    public javax.swing.JPanel necessaryPanel;
    private javax.swing.JPanel parameterPanel;
    private javax.swing.JTable parameterTable;
    private javax.swing.JPanel phasePanel;
    private javax.swing.JPanel predicatePanel;
    private javax.swing.JTable predicateTable;
    private javax.swing.JPanel programOutPanel;
    private javax.swing.JPanel rawDataPanel;
    private javax.swing.JButton readMatrixButton;
    private javax.swing.JButton readMechButton;
    private javax.swing.JButton rootDirButton;
    private javax.swing.JTextField rootDirField;
    private javax.swing.JPanel speciesPanel;
    private javax.swing.JScrollPane speciesScrollPanel;
    private javax.swing.JPanel totalNecessityPanel;
    public javax.swing.JPanel treePanel;
    private javax.swing.JButton updateParameterNamesButton;
    private javax.swing.JButton writeButton;
    // End of variables declaration//GEN-END:variables
    
}
