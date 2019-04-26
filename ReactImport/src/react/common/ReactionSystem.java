/*
 * JFrame.java
 *
 * Created on January 31, 2001, 5:52 AM
 */

package react.common;
import react.common.ReactProperties;
/**
 *
 * @author  reaction
 * @version 
 */
public class ReactionSystem extends javax.swing.JFrame {

    /** Creates new form JFrame */
    public ReactionSystem() {
        initComponents ();
        
            setUp(ReactProperties.getProperty("react.home"),
                  ReactProperties.getProperty("analysis.home"),
                  ReactProperties.getProperty("reaction.home"));
       pack ();
    }
    public ReactionSystem(String rootDir) {
        initComponents ();
        setUp(rootDir,rootDir,rootDir);
       pack ();
    }

    void setUp(String rootDir, String analysisDir, String reactionDir) {
        String bindir = analysisDir + "/bin/";
        frameTextDirectory.setDefaultValue("/text/");
        baseInfoDirectory.setDefaultValue("/usr/local/Software/Interface");
        frameTextDirectory.setValueName("Text Information Directory");
        reactionExecutable.setDefaultValue(bindir + "Reaction.exe");
        reactionExecutable.setValueName("The Reaction Executable");
        analysisRunExecutable.setDefaultValue(bindir + "Analysis.exe");
        analysisRunExecutable.setValueName("The Analysis Executable");
        reactionRunExecutable.setDefaultValue(bindir + "Reaction.exe");
        reactionRunExecutable.setValueName("The Reaction Executable");
        flameRunExecutable.setDefaultValue(bindir + "Flame.exe");
        flameRunExecutable.setValueName("The Flame Code Executable");
        demoDirectory.setDefaultValue(rootDir + "/Interface/demo/");
        demoDirectory.setValueName("Demo Directory");
        
        submechanismsDir.setDefaultValue(rootDir + "/data/mechs/submechanisms");
        submechanismsDir.setValueName("Submechanism Directory");
        rxnpatsDir.setDefaultValue(rootDir + "/data/rxn/rxnpats");
        rxnpatsDir.setValueName("Reaction Pattern Directory");
        substructuresDir.setDefaultValue(rootDir + "/data/mol/subs");
        substructuresDir.setValueName("Substructures Directory");
        reactionMechanismDir.setDefaultValue(reactionDir);
        reactionMechanismDir.setValueName("Reaction Mechanism Directory");
        moleculesDir.setDefaultValue(reactionDir + "/data/mol/");
        moleculesDir.setValueName("Top Molecules Directory");
        
        readMol.setDefaultValue("ReadMol");
        readMol.setValueName("Command to read in molecule structures");
        
        chemkinClassFile.setDefaultValue("MoleculeChemkinClass.inp");
        chemkinClassFile.setValueName("The standard molecule chemkin class");
        C0HO.setDefaultValue("C0CO");
        C0HO.setValueName("Standard molecules with 0 carbons");
        C1HO.setDefaultValue("C1CO");
        C1HO.setValueName("Standard molecules with 1 carbons");
        C2HO.setDefaultValue("C2CO");
        C2HO.setValueName("Standard molecules with 2 carbons");
        C3HO.setDefaultValue("C3CO");
        C3HO.setValueName("Standard molecules with 3 carbons");
        C4HO.setDefaultValue("C4CO");
        C4HO.setValueName("Standard molecules with 4 carbons");
        
        
        
        initBaseAlg.setDefaultValue("generic/BaseAlgorithm");
        initBaseAlg.setValueName("Base Initialize Algorithm");
        initMoleculeAlg.setDefaultValue("mol/inputs/MoleculeAlgorithm");
        initMoleculeAlg.setValueName("Molecule Initialize Algorithm");
        initReactionAlg.setDefaultValue("rxn/inputs/RxnAlgorithm");
        initReactionAlg.setValueName("Reaction Initialize Algorithm");
        initMechanismAlg.setDefaultValue("mech/inputs/MechanismAlgorithm");
        initMechanismAlg.setValueName("Mechanism Initialize Algorithm");
        initMolecule.setDefaultValue("mol/inputs/Molecule");
        initMolecule.setValueName("Standard Molecule Objects");
        initReaction.setDefaultValue("rxn/inputs/Reaction");
        initReaction.setValueName("Standard Reaction Objects");
        initMechanism.setDefaultValue("mech/inputs/Mechanism");
        initMechanism.setValueName("Standard Mechanism Objects");
        initChemkin.setDefaultValue("mol/inputs/MoleculeChemkin");
        initChemkin.setValueName("Standard Chemkin Objects");
        initEquilibrium.setDefaultValue("mol/inputs/Equilibrium");
        initEquilibrium.setValueName("Standard Equilibrium Calculation Setup");
        initMolDat.setDefaultValue("StandardMOLDAT");
        initMolDat.setValueName("Standard MOLDAT Information");
        initTransfer.setDefaultValue("StandardC1C4Transfer");
        initTransfer.setValueName("Standard Initial transfer of C1-C4 Information");
        
        moleculeDBInit.setDefaultValue("mol/inputs/MolDbase");
        moleculeDBInit.setValueName("Reaction Initialize Molecule Database");
        reactionDBInit.setDefaultValue("rxn/inputs/RxnDbase");
        reactionDBInit.setValueName("Reaction Initialize Reaction Database");
        mechanismDBInit.setDefaultValue("mech/inputs/MechDbase");
        mechanismDBInit.setValueName("Reaction Initialize Mechanism Database");
        
        
        initializeDirectory.setDefaultValue(reactionDir + "/data/");
        initializeDirectory.setValueName("Initialization Directory");
        standardAlgorithms.setDefaultValue("initialize/StandardAlgorithms");
        standardAlgorithms.setValueName("Standard Algorithms");
        standardExpressions.setDefaultValue("initialize/StandardExpressions");
        standardExpressions.setValueName("Standard Expression Classes");
        standardGraph.setDefaultValue("initialize/StandardGraph");
        standardGraph.setValueName("Standard Graph Classes");
        standardRules.setDefaultValue("initialize/StandardRules");
        standardRules.setValueName("Standard Rule Classes");
        standardLogic.setDefaultValue("initialize/StandardLogic");
        standardLogic.setValueName("Standard Logic Classes");
        
        matrixData.setDefaultValue("Flame");
        matrixData.setValueName("Read in a Matrix of Data from the ignition program");
        
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jTabbedPane1 = new javax.swing.JTabbedPane();
        InfoDirs = new javax.swing.JPanel();
        frameTextDirectory = new react.common.StandardName();
        demoDirectory = new react.common.StandardName();
        submechanismDir = new react.common.StandardName();
        baseInfoDirectory = new react.common.StandardName();
        executables = new javax.swing.JPanel();
        reactionExecutable = new react.common.StandardName();
        analysisRunExecutable = new react.common.StandardName();
        reactionRunExecutable = new react.common.StandardName();
        flameRunExecutable = new react.common.StandardName();
        initial = new javax.swing.JPanel();
        initializeDirectory = new react.common.StandardName();
        standardAlgorithms = new react.common.StandardName();
        standardLogic = new react.common.StandardName();
        standardExpressions = new react.common.StandardName();
        standardGraph = new react.common.StandardName();
        standardRules = new react.common.StandardName();
        rxnDataDirs = new javax.swing.JPanel();
        submechanismsDir = new react.common.StandardName();
        substructuresDir = new react.common.StandardName();
        rxnpatsDir = new react.common.StandardName();
        reactionMechanismDir = new react.common.StandardName();
        moleculesDir = new react.common.StandardName();
        RxnInit = new javax.swing.JPanel();
        initMolecule = new react.common.StandardName();
        initReaction = new react.common.StandardName();
        initMechanism = new react.common.StandardName();
        readMol = new react.common.StandardName();
        C1C4 = new javax.swing.JPanel();
        C0HO = new react.common.StandardName();
        C1HO = new react.common.StandardName();
        C2HO = new react.common.StandardName();
        C3HO = new react.common.StandardName();
        C4HO = new react.common.StandardName();
        AlgInit = new javax.swing.JPanel();
        initBaseAlg = new react.common.StandardName();
        initMoleculeAlg = new react.common.StandardName();
        initReactionAlg = new react.common.StandardName();
        initMechanismAlg = new react.common.StandardName();
        RxnDataInit = new javax.swing.JPanel();
        initEquilibrium = new react.common.StandardName();
        initChemkin = new react.common.StandardName();
        chemkinClassFile = new react.common.StandardName();
        initMolDat = new react.common.StandardName();
        initTransfer = new react.common.StandardName();
        ReactionCommands = new javax.swing.JPanel();
        matrixData = new react.common.StandardName();
        reactionDataBasePanel = new javax.swing.JPanel();
        moleculeDBInit = new react.common.StandardName();
        reactionDBInit = new react.common.StandardName();
        mechanismDBInit = new react.common.StandardName();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(600, 200));
        InfoDirs.setLayout(new java.awt.GridLayout(5, 1, 0, 1));

        InfoDirs.add(frameTextDirectory);

        InfoDirs.add(demoDirectory);

        InfoDirs.add(submechanismDir);

        InfoDirs.add(baseInfoDirectory);

        jTabbedPane1.addTab("InfoDirs", null, InfoDirs, "");

        executables.setLayout(new java.awt.GridLayout(5, 1));

        reactionExecutable.setToolTipText("The Reaction Executable");
        executables.add(reactionExecutable);

        executables.add(analysisRunExecutable);

        executables.add(reactionRunExecutable);

        executables.add(flameRunExecutable);

        jTabbedPane1.addTab("Executables", null, executables, "The executables used by the system");

        initial.setLayout(new java.awt.GridLayout(6, 1));

        initial.setToolTipText("Initialization Files");
        initial.add(initializeDirectory);

        initial.add(standardAlgorithms);

        initial.add(standardLogic);

        initial.add(standardExpressions);

        initial.add(standardGraph);

        initial.add(standardRules);

        jTabbedPane1.addTab("Initialization", null, initial, "The file roots for all the initialization procedures");

        rxnDataDirs.setLayout(new java.awt.GridLayout(5, 1));

        rxnDataDirs.add(submechanismsDir);

        rxnDataDirs.add(substructuresDir);

        rxnDataDirs.add(rxnpatsDir);

        rxnDataDirs.add(reactionMechanismDir);

        rxnDataDirs.add(moleculesDir);

        jTabbedPane1.addTab("DataDirs", null, rxnDataDirs, "The data directories");

        RxnInit.setLayout(new java.awt.GridLayout(5, 1));

        RxnInit.add(initMolecule);

        RxnInit.add(initReaction);

        RxnInit.add(initMechanism);

        RxnInit.add(readMol);

        jTabbedPane1.addTab("RxnInit", null, RxnInit, "Initialization of Molecules, Reactions and Mechanisms");

        C1C4.setLayout(new java.awt.GridLayout(6, 1));

        C1C4.add(C0HO);

        C1C4.add(C1HO);

        C1C4.add(C2HO);

        C1C4.add(C3HO);

        C1C4.add(C4HO);

        jTabbedPane1.addTab("C1-C4", null, C1C4, "The initialization files of the standard REACTION objects");

        AlgInit.setLayout(new java.awt.GridLayout(5, 1));

        AlgInit.add(initBaseAlg);

        AlgInit.add(initMoleculeAlg);

        AlgInit.add(initReactionAlg);

        AlgInit.add(initMechanismAlg);

        jTabbedPane1.addTab("Algorithms", null, AlgInit, "Initialization of Reaction Algorithms");

        RxnDataInit.setLayout(new java.awt.GridLayout(5, 1));

        RxnDataInit.add(initEquilibrium);

        RxnDataInit.add(initChemkin);

        RxnDataInit.add(chemkinClassFile);

        RxnDataInit.add(initMolDat);

        RxnDataInit.add(initTransfer);

        jTabbedPane1.addTab("Reaction", null, RxnDataInit, "Initialization of Reaction Data");

        matrixData.setToolTipText("Reading in Matrix Data");
        matrixData.setName("Matrix Data");
        ReactionCommands.add(matrixData);

        jTabbedPane1.addTab("Commands", null, ReactionCommands, "Reaction Commands");

        reactionDataBasePanel.add(moleculeDBInit);

        reactionDataBasePanel.add(reactionDBInit);

        reactionDataBasePanel.add(mechanismDBInit);

        jTabbedPane1.addTab("DataBase", null, reactionDataBasePanel, "Database information for Reaction System");

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit (0);
    }//GEN-LAST:event_exitForm

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        new ReactionSystem ().show ();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AlgInit;
    public react.common.StandardName C0HO;
    private javax.swing.JPanel C1C4;
    public react.common.StandardName C1HO;
    public react.common.StandardName C2HO;
    public react.common.StandardName C3HO;
    public react.common.StandardName C4HO;
    private javax.swing.JPanel InfoDirs;
    private javax.swing.JPanel ReactionCommands;
    private javax.swing.JPanel RxnDataInit;
    private javax.swing.JPanel RxnInit;
    public react.common.StandardName analysisRunExecutable;
    public react.common.StandardName baseInfoDirectory;
    public react.common.StandardName chemkinClassFile;
    public react.common.StandardName demoDirectory;
    private javax.swing.JPanel executables;
    public react.common.StandardName flameRunExecutable;
    public react.common.StandardName frameTextDirectory;
    public react.common.StandardName initBaseAlg;
    public react.common.StandardName initChemkin;
    public react.common.StandardName initEquilibrium;
    public react.common.StandardName initMechanism;
    public react.common.StandardName initMechanismAlg;
    public react.common.StandardName initMolDat;
    public react.common.StandardName initMolecule;
    public react.common.StandardName initMoleculeAlg;
    public react.common.StandardName initReaction;
    public react.common.StandardName initReactionAlg;
    public react.common.StandardName initTransfer;
    private javax.swing.JPanel initial;
    public react.common.StandardName initializeDirectory;
    private javax.swing.JTabbedPane jTabbedPane1;
    public react.common.StandardName matrixData;
    public react.common.StandardName mechanismDBInit;
    public react.common.StandardName moleculeDBInit;
    public react.common.StandardName moleculesDir;
    public react.common.StandardName reactionDBInit;
    public javax.swing.JPanel reactionDataBasePanel;
    public react.common.StandardName reactionExecutable;
    public react.common.StandardName reactionMechanismDir;
    public react.common.StandardName reactionRunExecutable;
    public react.common.StandardName readMol;
    private javax.swing.JPanel rxnDataDirs;
    public react.common.StandardName rxnpatsDir;
    public react.common.StandardName standardAlgorithms;
    public react.common.StandardName standardExpressions;
    public react.common.StandardName standardGraph;
    public react.common.StandardName standardLogic;
    public react.common.StandardName standardRules;
    private react.common.StandardName submechanismDir;
    public react.common.StandardName submechanismsDir;
    public react.common.StandardName substructuresDir;
    // End of variables declaration//GEN-END:variables

}
