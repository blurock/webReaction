    /*
     * TopReactionFrame.java
     *
     * Created on January 21, 2001, 10:19 AM
     */

    package react.menu;

    import react.common.*;
    import react.utilities.*;
    import react.menu.*;
    import react.therm.*;
    import react.mechanisms.*;
    import react.molecules.*;
    import utilities.BaseFrame;
    import java.awt.Dimension;
    import javax.swing.*;
    import javax.swing.tree.*;
    import java.awt.event.*;

    import blurock.instattr.*;
    import blurock.opandalgs.register.*;
    import blurock.pop.genetic.*;
    import blurock.core.ClassTree;
    import blurock.core.InitializeSystem;
    import blurock.core.RunSystemLineCommand;
    import blurock.core.RunSystemAlgorithm;
    import blurock.core.InitializeReaction;
    import blurock.core.ReactionObjectInitialization;
    import blurock.core.LogoFrame;
    import blurock.core.RunCommand;
    import blurock.opandalgs.decisiontree.DecisionTreePanel;
    import ignition.ConditionMatrix;
    import ignition.ClusterRegionAlpgorithmParameters;
    import ignition.psr.ReactPSR;
    import react.mechanisms.flow.DisplayFlow;
    import ignition.times.IgnitionTimes;
    import react.common.ReactProperties;
    
    /**
     *
     * @author  reaction
     * @version 
     */
    public class TopReactionFrame extends javax.swing.JFrame {
        protected static java.util.Properties properties = new java.util.Properties();
        Dimension panelSize;
        DefaultMutableTreeNode topNode = new DefaultMutableTreeNode("Reaction");
        LogoFrame logo;
        public MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = frameHierarchy.getRowForLocation(e.getX(),e.getY());
                TreePath path = frameHierarchy.getPathForLocation(e.getX(),e.getY());
                if(path != null) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) 
                     path.getLastPathComponent();
                    String name = (String) node.getUserObject();
                    if(selRow != -1) {
                        //informationPanel.setup(Top.SystemInfo.baseInfoDirectory.getValue() + 
                                               //Top.SystemInfo.frameTextDirectory.getValue());
                        boolean isleaf = frameHierarchy.getModel().isLeaf(node);
                        if(isleaf) {
                            System.out.println("Is a Leaf Node -->");
                        }
                        if(e.getClickCount() == 1) {
                            if(isleaf)
                                setSelected(name);
                        } else if(e.getClickCount() == 2) {
                            if(isleaf)
                                setFrame(name);
                        }
                    }
                }
            }
        };

        /** Creates new form TopReactionFrame */
        public TopReactionFrame(String  baseInfoDirectory, String reactdir) {
            String base = baseInfoDirectory;
            System.out.println("Properities: " + System.getProperties());
        logo = new LogoFrame();
        logo.setVisible(true);
        logo.show();
            Top = new TopReactionMenu(logo.loadProgress,reactdir);
    
            panelSize = new Dimension(Top.Defaults.panelSizeX.getValue(),
                                      Top.Defaults.panelSizeY.getValue());
            logo.loadProgress.setValue(60);
            initializeOptionFrames(topNode);
            logo.loadProgress.setValue(65);
            initializeRunFrames(topNode);
            logo.loadProgress.setValue(75);
            initializeCatalogFrames(topNode);
            //logo.loadProgress.setValue(80);
            initializeInstanceFrames(topNode);
            logo.loadProgress.setValue(85);
            initializeOptimizationFrames(topNode);
            //logo.loadProgress.setValue(90);
            initializeReactionFrames(topNode);
            logo.loadProgress.setValue(90);
            initComponents ();
            logo.loadProgress.setValue(100);
            frameHierarchy.addMouseListener(ml);
            pack ();
            logo.loadProgress.setValue(100);
        logo.setVisible(false);
        //RunCommand runit = new RunCommand(Top,"Print Class",false);
        //runit.run();
        }
        private void initializeReactionFrames(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode rtop = 
                    new DefaultMutableTreeNode(new String("REACTION"));
            parent.add(rtop);
            DefaultMutableTreeNode init = 
                    new DefaultMutableTreeNode(new String("Initialization"));
            rtop.add(init);
            
            System.out.println("InitializeReaction");
            InitializeReaction rinit = new InitializeReaction(Top);
            BaseFrame fr1 = new BaseFrame(Top,rinit,
                                        "Reaction Initialization",
                                        "Initialize Standard REACTION Structures",
                                        "react/options/reactioninitial.txt");
            Top.frameSet.putFrame(fr1);
            addNode(fr1,init);

            System.out.println("ReactionObjectInitialization");
            ReactionObjectInitialization oinit = new ReactionObjectInitialization(Top);
            BaseFrame fr2 = new BaseFrame(Top,oinit,
                                        "Reaction Object Initialization",
                                        "Initialize Standard REACTION Objects",
                                        "react/options/reactionobject.txt");
            Top.frameSet.putFrame(fr2);
            addNode(fr2,init);

            System.out.println("initializeExamineFrames");
            initializeExamineFrames(rtop);
            System.out.println("initializeOperationFrames");
            initializeOperationFrames(rtop);
            initializeIgnitionFrames(rtop);
            System.out.println("Done with it.....");
        }
        private void initializeOptimizationFrames(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode opt = 
                    new DefaultMutableTreeNode(new String("Optimization"));
            parent.add(opt);
            RunGeneticOptimization gen = new RunGeneticOptimization(Top);
            BaseFrame fgen = new BaseFrame(Top,gen,
                                    "Run a genetic Optimization",
                                    "Input a file specified genetic optimization",
                                    "opandalgs/genetic/geneticrun.txt");
            Top.frameSet.putFrame(fgen);
            addNode(fgen,opt);
        }
        private void initializeRunFrames(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode run = 
                    new DefaultMutableTreeNode(new String("Run"));
            parent.add(run);
            RunSystemLineCommand command = new RunSystemLineCommand(Top);
            BaseFrame fcommand = new BaseFrame(Top,command,
                                    "Run a Command",
                                    "Run a specified Command",
                                    "run/commandrun.txt");
            Top.frameSet.putFrame(fcommand);
            addNode(fcommand,run);
            RunSystemAlgorithm alg = new RunSystemAlgorithm(Top);
            BaseFrame falg = new BaseFrame(Top,alg,
                                    "Run an Algorithm",
                                    "Run a specified Algorithm",
                                    "run/algorithmrun.txt");
            Top.frameSet.putFrame(falg);
            addNode(falg,run);
        }
        private void initializeCatalogFrames(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode cat = 
                    new DefaultMutableTreeNode(new String("Catalogs"));
            parent.add(cat);
            CatalogAlgorithms algs = new CatalogAlgorithms(Top);
            BaseFrame falgs = new BaseFrame(Top,algs,
                                    "Catalog of Algorithms",
                                    "An organized catalog of algorithms and their descriptions",
                                    "opandalgs/register/algcatalog.txt");
            Top.frameSet.putFrame(falgs);
            addNode(falgs,cat);
            CatalogOperations ops = new CatalogOperations(Top);
            BaseFrame fops = new BaseFrame(Top,ops,
                                    "Catalog of Operations",
                                    "An organized catalog of operations and descriptions",
                                    "opandalgs/register/opcatalog.txt");
            Top.frameSet.putFrame(fops);
            addNode(fops,cat);
        }
        private void initializeInstanceFrames(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode inst = 
                    new DefaultMutableTreeNode(new String("Instances/Attributes/Classes"));
            parent.add(inst);

            ReadClassInstAttr rclsatt = new ReadClassInstAttr(Top);
            BaseFrame r = new BaseFrame(Top,rclsatt,
                                    "Read Data Files",
                                    "Read in Attributes and Classes",
                                    "blurock/instances/read.txt");
            Top.frameSet.putFrame(r);
            addNode(r,inst);

            BaseFrame treef = new BaseFrame(Top,Top.classTree,
                                             "Class Hierarchy",
                                             "Show the Class Tree hierarchy",
                                             "blurock/instances/class.txt");
            Top.frameSet.putFrame(treef);
            addNode(treef,inst);

            ManageAttributes attrs = new ManageAttributes(Top);
            BaseFrame fattrs = new BaseFrame(Top,attrs,
                        "Manage Attributes",
                        "Manage Attributes",
                        "blurock/instances/attrs.txt");
            Top.frameSet.putFrame(fattrs);
            addNode(fattrs,inst);
            SelectInstanceAttributes att = new SelectInstanceAttributes(Top);
            BaseFrame attf = new BaseFrame(Top,att,
                                            "Select Instance Attributes",
                                            "Selecte Instance Attributes",
                                            "blurock/instances/attset.txt");
            Top.selectInstanceAttributes = attf;
            Top.frameSet.putFrame(attf);
            addNode(attf,inst);

            ExamineInstanceAttributes examattr = new ExamineInstanceAttributes(Top);
            BaseFrame fexam = new BaseFrame(Top,examattr,
                                            "Examine Instance Attributes",
                                            "Examine individual instance attributes",
                                            "blurock/instances/instattr.txt");
            Top.frameSet.putFrame(fexam);
            addNode(fexam,inst);
            InstanceSets iss = new InstanceSets(Top);
            BaseFrame is = new BaseFrame(Top,iss,
                                    "Set up Total/Test/Train Sets",
                                    "Set up the instance sets",
                                    "blurock/instances/instset.txt");
            Top.instanceSets = is;
            Top.frameSet.putFrame(is);
            addNode(is,inst);
            
        }        
        private void initializeOptionFrames(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode opts = 
                    new DefaultMutableTreeNode(new String("Options"));
            parent.add(opts);

            BaseFrame h = new BaseFrame(Top,Top.history,
                                    "History Level",
                                    "Manage the History Level",
                                    "react/options/history.txt");
            Top.frameSet.putFrame(h);
            addNode(h,opts);
            h.show();
            Top.InitializeSystem = new InitializeSystem(Top);
            BaseFrame fr = new BaseFrame(Top,Top.InitializeSystem,
                                        "System Initialization",
                                        "Initialize Standard System Structures",
                                        "react/options/initialization.txt");
            Top.frameSet.putFrame(fr);
            addNode(fr,opts);
            
            JPanel preactionsystem = (JPanel) Top.SystemInfo.getContentPane();
            BaseFrame inf = new BaseFrame(Top,preactionsystem,
                                        "System Information",
                                        "Default System Values",
                                        "react/options/systeminfo.txt");
            Top.frameSet.putFrame(inf);
            addNode(inf,opts);

}
        private void initializeOperationFrames(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode ops = 
                    new DefaultMutableTreeNode(new String("Operations"));
            parent.add(ops);
            ReactionRunSetup rxnrunsetup = new ReactionRunSetup(Top);
            BaseFrame run= new BaseFrame(Top,rxnrunsetup,
                                "SetupScripts",
                                "Setup Scripts for REACTION information",
                                "react/ops/rxnrunsetup.txt");
             Top.frameSet.putFrame(run);
             addNode(run,parent);
            initializeOpMechFrames(ops);
        }
        private void initializeIgnitionFrames(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode ignition = 
                    new DefaultMutableTreeNode(new String("Ignition"));
            parent.add(ignition);
            
            DisplayFlow flow = new DisplayFlow();
            BaseFrame flowf = new BaseFrame(Top,flow,
                                "Display Ignition Flow",
                                "Display Ignition Flow",
                                "react/ignition/flow.txt");
            Top.frameSet.putFrame(flowf);
            addNode(flowf,ignition);
            
            IgnitionTimes times = new IgnitionTimes();
            BaseFrame timesf = new BaseFrame(Top,times,
                                "Look at Ignition Run",
                                "Look at Ignition Run",
                                "react/ignition/times.txt");
            Top.frameSet.putFrame(timesf);
            addNode(timesf,ignition);
            
            ReactPSR psr = new ReactPSR();
            BaseFrame psrf = new BaseFrame(Top, psr, 
                                            "Extract PSR Values",
                                            "Extract PSR Values",
                                            "react/ignition/psr.txt");
            Top.frameSet.putFrame(psrf);
            addNode(psrf, ignition);
        }
        private void initializeOpMechFrames(DefaultMutableTreeNode parent) {
            DefaultMutableTreeNode runchain = 
                    new DefaultMutableTreeNode(new String("Generation"));
            parent.add(runchain);
            DefaultMutableTreeNode ops = 
                    new DefaultMutableTreeNode(new String("Operations"));
            parent.add(ops);
            DefaultMutableTreeNode regions = 
                    new DefaultMutableTreeNode(new String("Flame Regions"));
            parent.add(regions);
           
            CombineSubMechanismMenu combineMenu = 
                    new CombineSubMechanismMenu(Top,"Combine Mechanisms",20,panelSize);
            BaseFrame combine = new BaseFrame(Top,combineMenu,
                                    "Mechanism Combine",
                                    "Run Combine Mechanisms",
                                    "react/ops/runchain.txt");
            Top.frameSet.putFrame(combine);
            addNode(combine,ops);
            DefaultMutableTreeNode inpmech =
                    new DefaultMutableTreeNode("Input Mechanism");
            //parent.add(inpmech);
             MechanismGenerateAndCombineMenu mechgen = new MechanismGenerateAndCombineMenu(Top);
             BaseFrame mech = new BaseFrame(Top,mechgen,
                                "Combine Mechanism Set",
                                "Combine Mechanism Set",
                                "react/ops/combineset.txt");
             Top.frameSet.putFrame(mech);
             addNode(mech,runchain);
             
            ReadInMechanism readinmech = new ReadInMechanism(Top);
            BaseFrame freadinmech = new BaseFrame(Top,readinmech,
                                    "Read In Mechanism",
                                    "Read In Mechanism",
                                    "react/ops/readinmech.txt");
            Top.frameSet.putFrame(freadinmech);
            addNode(freadinmech,runchain);
            
            InputMechanism inpmechMenu = new InputMechanism(Top);
            BaseFrame inp = new BaseFrame(Top,inpmechMenu,
                                "Setup for Run",
                                "Setup for Run",
                                "react/ops/inpmech.txt");
             Top.frameSet.putFrame(inp);
             addNode(inp,runchain);
             DetermineMechanismIsomers mechiso = new DetermineMechanismIsomers(Top);
             BaseFrame mechisof = new BaseFrame(Top,mechiso,
                                "Isomers of Molecules",
                                "Isomers of Molecules",
                                "react/ops/isomers.txt");
             Top.frameSet.putFrame(mechisof);
             addNode(mechisof,runchain);
             LumpedMoleculesMenu lumped = new LumpedMoleculesMenu(Top);
             BaseFrame lumpedf = new BaseFrame(Top,lumped,
                                    "Lumping through Reaction Classes",
                                    "Lumping through Reaction Classes",
                                    "react/ops/lumping.txt");
             Top.frameSet.putFrame(lumpedf);
             addNode(lumpedf,runchain);
             
             BuildLumpedMechanism lumpedmech = new BuildLumpedMechanism(Top);
             BaseFrame lumpedmechf = new BaseFrame(Top,lumpedmech,
                                    "Lumping of reactions through Reaction Classes",
                                    "Lumping of reactions through Reaction Classes",
                                    "react/ops/lumpingrxns.txt");
             Top.frameSet.putFrame(lumpedmechf);
             addNode(lumpedmechf,runchain);
            
             BuildMechanism buildmech = new BuildMechanism(Top);
             BaseFrame buildmechf = new BaseFrame(Top,buildmech,
                                    "Building a Mechanism through Reactions and Molecules",
                                    "Building a Mechanism through Reactions and Molecules",
                                    "react/ops/buildmech.txt");
             Top.frameSet.putFrame(buildmechf);
             addNode(buildmechf,runchain);
            
            MechanismAsGraphMenu mechanalysis = new MechanismAsGraphMenu(Top);
            BaseFrame fmechanalysis = new BaseFrame(Top,mechanalysis,
                                    "Mechanism Graph Analysis",
                                    "Mechanism Graph Analysis",
                                    "react/examine/mechanalysis.txt");
            Top.frameSet.putFrame(fmechanalysis);
            addNode(fmechanalysis,runchain);  
            
             RunGeneratedMechanism rungen = new RunGeneratedMechanism();
             BaseFrame rungenf = new BaseFrame(Top,rungen,
                                "Run Generated",
                                "Run Generated",
                                "react/ops/rungenerated.txt");
             Top.frameSet.putFrame(rungenf);
             addNode(rungenf,runchain);

             ComputeThermodynamics therm = new ComputeThermodynamics(Top);
             BaseFrame thermof = new BaseFrame(Top,therm,
                                "Calculate Thermodynamics",
                                "Calculate Thermodynamics",
                                "react/ops/calculatethermo.txt");
             Top.frameSet.putFrame(thermof);
             addNode(thermof,runchain);
             
            ClusterRegionAlpgorithmParameters algP = new ClusterRegionAlpgorithmParameters(Top);
             ConditionMatrix cond = new ConditionMatrix(algP);
             BaseFrame condinitf = new BaseFrame(Top,cond,
                                "Initialize Instance Data",
                                "Initialize Instance Data",
                                "react/cluster/initialdata.txt");
             Top.frameSet.putFrame(condinitf);
             addNode(condinitf,regions);
             
             /*
             InitializeRegionClusteringPanel clsinit = new InitializeRegionClusteringPanel(Top);
             BaseFrame clusinitf = new BaseFrame(Top,clsinit,
                                "Initial Data",
                                "Initial Data",
                                "react/cluster/initialdata.txt");
             Top.frameSet.putFrame(clusinitf);
             addNode(clusinitf,regions);
             */
 
              DataPreparationPanel clsprep = new DataPreparationPanel(Top);
             BaseFrame clusprepf = new BaseFrame(Top,clsprep,
                                "Preparation of Data",
                                "Preparation of Data",
                                "react/cluster/preparedata.txt");
             Top.frameSet.putFrame(clusprepf);
             addNode(clusprepf,regions);
             
             PredicatesClusteringPanel clspred = new PredicatesClusteringPanel(Top,algP);
             BaseFrame cluspredf = new BaseFrame(Top,clspred,
                                "Fuzzy Predicates for Clustering",
                                "Fuzzy Predicates for Clustering",
                                "react/cluster/predicatesdata.txt");
             Top.frameSet.putFrame(cluspredf);
             addNode(cluspredf,regions);

             FormationClusteringPanel clsform = new FormationClusteringPanel(Top);
             BaseFrame clusformf = new BaseFrame(Top,clsform,
                                "Form Cluster",
                                "Form Cluster",
                                "react/cluster/formcluster.txt");
             Top.frameSet.putFrame(clusformf);
             addNode(clusformf,regions);
             
             
             DecisionTreePanel dectree = new DecisionTreePanel(Top);
             BaseFrame dectreef = new BaseFrame(Top,dectree,
                                "Form Decision Tree",
                                "Form Decision Tree",
                                "react/Decision/Decisiontree.txt");
             Top.frameSet.putFrame(dectreef);
             addNode(dectreef,regions);
             
             /*
             ConsecutiveClusteringPanel clsregion = new ConsecutiveClusteringPanel(Top);
             BaseFrame clusregionf = new BaseFrame(Top,clsregion,
                                "Find Regions",
                                "Find Regions",
                                "react/cluster/findregions.txt");
             Top.frameSet.putFrame(clusregionf);
             addNode(clusregionf,regions);
             */
        }
        private void initializeExamineFrames(DefaultMutableTreeNode top) {

            DefaultMutableTreeNode examine = new DefaultMutableTreeNode(new String("Objects"));
            top.add(examine);
            System.out.println("------>initializeExamineMolFrames");
            initializeExamineMolFrames(examine);
            System.out.println("------>initializeExamineRxnFrames");
            initializeExamineRxnFrames(examine);
            System.out.println("------>initializeExamineMechFrames");
            initializeExamineMechFrames(examine);
                    }
        private void initializeExamineRxnFrames(DefaultMutableTreeNode examine) {
            DefaultMutableTreeNode rxn = 
                new DefaultMutableTreeNode(new String("Reactions and Patterns"));
            examine.add(rxn);
            
            RxnPatMenu rxnPatMenu = new RxnPatMenu();
            rxnPatMenu.setupList(Top,panelSize);
            BaseFrame rxnpat = new BaseFrame(Top,rxnPatMenu,
                                    "Reaction Patterns",
                                    "Manipulate Reaction Patterns",
                                    "react/examine/rxnpats.txt");
            Top.frameSet.putFrame(rxnpat);
            addNode(rxnpat,rxn);
        }
        
        private void initializeExamineMechFrames(DefaultMutableTreeNode examine) {
            DefaultMutableTreeNode mech = 
                new DefaultMutableTreeNode(new String("Mechanisms and Generation Schemes"));
            examine.add(mech);
            ReactMechanismGenerationMenu mechgenmenu = 
                    new ReactMechanismGenerationMenu(Top,panelSize);
            BaseFrame mechgen = new BaseFrame(Top,mechgenmenu,
                                    "Mechanism Schemes",
                                    "Manipulate Mechanism Steps",
                                    "react/examine/mechgen.txt");
            Top.frameSet.putFrame(mechgen);
            addNode(mechgen,mech);            
        DefaultMutableTreeNode mechrw = new DefaultMutableTreeNode("Full Mechanism");
        examine.add(mechrw);
        ReactionMechanismMenu mechmenu = new ReactionMechanismMenu(Top);
            BaseFrame m = new BaseFrame(Top,mechmenu,
                                    "Full Mechanism",
                                    "Read and Write Mechanisms",
                                    "react/examine/mechrw.txt");
            Top.frameSet.putFrame(m);
            addNode(m,mechrw);            
        ReactionMechanismGraphMenu mechgmenu = new ReactionMechanismGraphMenu(Top);
            BaseFrame mg = new BaseFrame(Top,mechgmenu,
                                    "Mechanism As Graph",
                                    "Mechanism As Graph",
                                    "react/examine/mechg.txt");
            Top.frameSet.putFrame(mg);
            addNode(mg,mechrw);            
        
        }
        private void initializeExamineMolFrames(DefaultMutableTreeNode examine) {

            DefaultMutableTreeNode mol = new DefaultMutableTreeNode(new String("Molecules and Substructures"));

            examine.add(mol);

            MoleculeMenu molmenu = new MoleculeMenu(Top,panelSize);
            molmenu.setup(Top,panelSize);
            BaseFrame molecule = new BaseFrame(Top,molmenu,
                                                "Molecules",
                                                "Examine Molecule Structures",
                                                "react/examine/molecules.txt");
            Top.frameSet.putFrame(molecule);
            addNode(molecule,mol);

            SubstructureMenu submenu = new SubstructureMenu();
            submenu.setup(Top,panelSize);
            BaseFrame substructures = new BaseFrame(Top,submenu,
                                                    "SubStructures",
                                                    "Examine Substructure Structures",
                                                    "react/examine/substructures.txt");
            Top.frameSet.putFrame(substructures);
            addNode(substructures,mol);

        }

        private void addNode(BaseFrame frame, DefaultMutableTreeNode node) {
            DefaultMutableTreeNode n = new DefaultMutableTreeNode(frame.frameName);
            node.add(n);
      }
        public void setSelected(String name) {
            System.out.println("NodeClicked:" + name);
            BaseFrame frame = Top.frameSet.getFrame(name);
            //informationPanel.setInformationFromFile(frame.textExplanation);
        }
        public void setFrame(String name) {
            System.out.println("Open Frame: " + name);
            BaseFrame frame = Top.frameSet.getFrame(name);
            frame.setVisible(true);
        }
        /** This method is called from within the constructor to
         * initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the FormEditor.
         */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jToolBar2 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        frameHierarchy = new JTree(topNode);
        jEditorPane1 = new javax.swing.JEditorPane();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jToolBar2.setBackground(java.awt.Color.white);
        getContentPane().add(jToolBar2, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        jPanel2.setMinimumSize(new java.awt.Dimension(600, 400));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 500));
        frameHierarchy.setBorder(new javax.swing.border.TitledBorder("Menu Hierarchy"));
        jPanel2.add(frameHierarchy);

        jEditorPane1.setBorder(new javax.swing.border.TitledBorder("Information"));
        jPanel2.add(jEditorPane1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

    }
    // </editor-fold>//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit (0);
    }//GEN-LAST:event_exitForm

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        ReactProperties.start();
        String baseInfoDirectory = "/usr/local/Software/Interface";
        String reactdir = ReactProperties.getProperty("react.home");
        System.out.println("Starting Directory: " + baseInfoDirectory);
        System.out.println("Reaction Directory: " + reactdir);
        new TopReactionFrame (baseInfoDirectory,reactdir).show ();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTree frameHierarchy;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables

    public TopReactionMenu Top;
}
