/*
 * DecisionTreeFORTRAN.java
 *
 * Created on September 22, 2004, 12:11 PM
 */

package blurock.DecisionTree;
import java.util.Hashtable;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import utilities.ErrorFrame;
import blurock.DirectedTreeObjects.BaseDataDirectedTree;
import graph.DrawGraph;
import graph.DrawGraphNode;
import blurock.core.ObjectNotFoundException;
import blurock.logic.predicates.BaseDataPredicate;
import javax.swing.table.DefaultTableModel;
import blurock.DecisionTree.BaseDataDecisionTree;

/**
 *
 * @author  reaction
 */
public class DecisionTreeFORTRAN  {
    DecisionTreeProgramOut programOut;
    BaseDataDecisionTree DecisionTree;
    /** Creates a new instance of DecisionTreeFORTRAN */
    public DecisionTreeFORTRAN(DecisionTreeProgramOut prg) {
        programOut = prg;
        DecisionTree = programOut.DecisionTree;
    }
    public String toString() {
        StringBuffer buf = new StringBuffer();
        String phase = stringPhaseReductionModule();
        buf.append(phase);
        String state = stringLundStateFunction();
        buf.append(state);
        return buf.toString();
    }
    public String stringPhaseReductionModule() {
        StringBuffer buf = new StringBuffer();
        String ModulePreamble = stringModulePreamble();
	buf.append(ModulePreamble);
        String DetermineReactivePhase = stringDetermineReactivePhase();
        buf.append(DetermineReactivePhase);
	String FullMechanismNumberOfSpecies = stringFullMechanismNumberOfSpecies();
	buf.append(FullMechanismNumberOfSpecies);
        String InitializePhases = stringInitializePhases();
        buf.append(InitializePhases);
	String FillToFullVector = stringFillToFullVector();
	buf.append(FillToFullVector);
	String GotoFullMechanism = stringGotoFullMechanism();
	buf.append(GotoFullMechanism);
	String AdjustToReactivePhase = stringAdjustToReactivePhase();
	buf.append(AdjustToReactivePhase);
        String LundOptimizedChemicalSourcesPhase = stringLundOptimizedChemicalSourcesPhase();
        buf.append(LundOptimizedChemicalSourcesPhase);
        String FullToReducedSpeciesPhase = stringFullToReducedSpeciesPhase();
        buf.append(FullToReducedSpeciesPhase);
        String ModulePost = ModulePostfix();
	buf.append(ModulePost);
        
        return buf.toString();
    }
    public String stringLundStateFunction() {
        StringBuffer buf = new StringBuffer();
        String preamble = stringLundStatePreamble();
        buf.append(preamble);
        String enthalpy = stringCalculateEnthalpy();
        buf.append(enthalpy);
        String entropy = stringCalculateEntropy();
        buf.append(entropy);              
        String heat = stringCalculateHeatCapacity();
        buf.append(heat);
        String mixt = stringGetMixtureCoefficients();
        buf.append(mixt);
        String post = stringLundStatePost();
        buf.append(post);
        return buf.toString();
    }
    public String stringMechanismDetails() {
        StringBuffer buf = new StringBuffer();
        String preamble = stringMechanismDetailsPreamble();
        buf.append(preamble);
        String species = stringNumberOf(true);
        buf.append(species);
        String rxns = stringNumberOf(false);
        buf.append(rxns);
        String ssspecies = stringNumberSteadyState();
        buf.append(ssspecies);
        String names = stringGetSpeciesNames();
        buf.append(names);
        String ssnames = stringGetSSSpeciesNames();
        buf.append(ssnames);
        String weights = stringGetSpeciesWeights();
        buf.append(weights);
        String atomno = stringGetSpeciesAtomNo();
        buf.append(atomno);
        String post =  stringMechanismDetailsPost();
        buf.append(post);
        return buf.toString();
    }
    public String stringLundKineticModule() {
         StringBuffer buf = new StringBuffer();
         String sources = stringChemicalSources();
         buf.append(sources);
         return buf.toString();
    }
    String stringDetermineReactivePhase() {        
        StringBuffer buf = new StringBuffer();
        buf.append("INTEGER FUNCTION DetermineReactivePhase(Concentration)\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("!     INPUT DATA\n");
        buf.append("!               1. Concentration                 [mol/m^3]\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("   IMPLICIT NONE\n");
        buf.append("   DOUBLE PRECISION Concentration(:)\n");
        buf.append("   !  Fill in the full concentration vector with the current valuesn\n");
        buf.append("   !  Concentrations of species not in the current mechanism are unchanged\n");
        buf.append("   ! Select the current phase\n");
        buf.append("    CALL FillToFullVector(Concentration, FullMechanismConcentration)\n");
        buf.append("   !------------------------------------------------------------------------------\n");
        String prefix = new String("    ");
        String treeS = addSons(programOut.DecisionTree.Tree.rootNode,prefix,false);
        buf.append(treeS);
        buf.append("   RETURN\n");
        buf.append("END FUNCTION DetermineReactivePhase\n");
        buf.append("\n");
        return buf.toString();
    }
    String addSons(String parent, String prefix, boolean addelse) {
        StringBuffer buf = new StringBuffer();
        BaseDataDirectedTree tree = DecisionTree.Tree;
        DrawGraph g = tree.Graph;
            int index = g.getNode(parent);
            DrawGraphNode node = (DrawGraphNode) g.Nodes.elementAt(index);
            
            System.out.println("predname " + node.getNameTag());
            BaseDataPredicate pred = (BaseDataPredicate) programOut.predicates.get(node.getNameTag());
            if(pred != null) {
                String predname = pred.Name;
                System.out.println(predname);
                String predtrans     = (String) programOut.PredicateTranslations.get(predname);
                buf.append(prefix);
                if(addelse) buf.append("ELSE ");
                buf.append("IF( ");
                buf.append(predtrans);
                buf.append(") THEN \n");    
            }
            Vector sons = g.getSons(parent);
            if(sons.size() > 0) {
                for(int i=0; i< sons.size();i++) {
                String npref = new String(prefix + "     ");
                boolean elseif = true;
                if(i == 0 ) elseif = false;
                String son = addSons((String) sons.elementAt(i),npref,elseif);
                buf.append(son);
                if(i == sons.size() -1 )
                     buf.append(npref + "END IF\n");
   
                }
            } else {
                try {
                    BaseDataDecisionTreeNodeStats nodestat = (BaseDataDecisionTreeNodeStats) 
                    DecisionTree.Tree.findObject(parent);
                    int ans = programOut.determineNodeGoal(nodestat);
                    buf.append(prefix + "DetermineReactivePhase = " + ans +"\n");
                    } catch(ObjectNotFoundException ex) {
                        buf.append(prefix + "DetermineReactivePhase = -1\n");
                    }
                }
        return buf.toString();
    }  
    String stringFullMechanismNumberOfSpecies() {
        StringBuffer buf = new StringBuffer();
        buf.append("INTEGER FUNCTION FullMechanismNumberOfSpecies()\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("!  This gives the highest dimension needed (the size of the full mechanism).\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("   FullMechanismNumberOfSpecies = ");
	Integer numI = new Integer(programOut.FullMechanismData.numberOfSpecies());
        buf.append(numI.toString() + "\n");
        buf.append("   RETURN\n");
        buf.append("END FUNCTION FullMechanismNumberOfSpecies\n");
        buf.append("\n");
        return buf.toString();
    }
    String stringFillToFullVector() {
        DefaultTableModel tmodel = (DefaultTableModel) programOut.goalTable.getModel();
        StringBuffer buf = new StringBuffer();
        buf.append("SUBROUTINE FillToFullVector(PhaseConcentration,FullConcentration)\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("!  Fills out to full vector of full mechanism\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("IMPLICIT NONE\n");
        buf.append("   DOUBLE PRECISION PhaseConcentration(:)\n");
        buf.append("   DOUBLE PRECISION FullConcentration(:)\n");
        buf.append("      SELECT CASE(ReactivePhase)\n");
        int numsubmechs = tmodel.getRowCount();
        for(int i=0;i<numsubmechs;i++) {
            
        buf.append("         CASE (");
        Integer iI = new Integer(i);
        buf.append(iI.toString());
        buf.append(")\n");
        buf.append("            CALL ReducedToFullSpeciesPhase");
        buf.append(tmodel.getValueAt(i,1));
                  buf.append("(PhaseConcentration,FullConcentration)\n");
        }
        buf.append("      END SELECT\n");
        buf.append("END SUBROUTINE\n");
        buf.append("\n");
        return buf.toString();
    }
    String stringGotoFullMechanism() {
        StringBuffer buf = new StringBuffer();
	int numsubmechs = programOut.SubMechanismInfo.size();
	Integer iI = new Integer(numsubmechs);
        buf.append("SUBROUTINE GotoFullMechanism(Concentration,n_Species, n_Reactions)\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("!  This adjusts n_Species, n_Reactions and the concentrations (through\n");
        buf.append("!  (through AdjustToReactivePhase) to reflect the phase of the full mechanism\n");
        buf.append("!\n");
        buf.append("!  The n_Species, n_Reactions and the concentrations are local variables in this\n");
        buf.append("!  routine but should reflect the proper ones in the calling routine.\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("      DOUBLE PRECISION, POINTER ::  Concentration(:)\n");
        buf.append("      INTEGER n_Species, n_Reactions\n");
        buf.append("      ReactivePhase = ");
	buf.append(iI.toString());
        buf.append("\n");
        buf.append("      CALL AdjustToReactivePhase(");
	buf.append(iI.toString());
        buf.append(",Concentration,n_Species, n_Reactions)\n");
        buf.append("END SUBROUTINE GotoFullMechanism\n");
        buf.append("\n");
        return buf.toString();
    }
    String stringAdjustToReactivePhase() {
        StringBuffer buf = new StringBuffer();
        DefaultTableModel tmodel = (DefaultTableModel) programOut.goalTable.getModel();
        buf.append("SUBROUTINE AdjustToReactivePhase(NewReactivePhase,Concentration,n_Species, n_Reactions)\n");
        buf.append("!-------------------------------------------------------------------------------------------\n");
        buf.append("!  The n_Species, n_Reactions and the concentrations are adjusted according to the\n");
        buf.append("!  new phase.  The concentrations are reduced from the 'FullMechanismConcentration'\n");
        buf.append("!  of this module. The new 'ReactivePhase' is updated.\n");
        buf.append("!\n");
        buf.append("!  The n_Species, n_Reactions and the concentrations are local variables in this\n");
        buf.append("!  routine but should reflect the proper ones in the calling routine.\n");
        buf.append("!-------------------------------------------------------------------------------------------\n");
        buf.append("   IMPLICIT NONE\n");
        buf.append("   !-------------------------------------------------------------------------------------------\n");
        buf.append("      INTEGER NewReactivePhase\n");
        buf.append("      DOUBLE PRECISION, POINTER ::  Concentration(:)\n");
        buf.append("      INTEGER n_Species, n_Reactions\n");
        buf.append("   !-------------------------------------------------------------------------------------------\n");
        buf.append("      DEALLOCATE(Concentration)\n");
        buf.append("\n");        
        buf.append("      ReactivePhase = NewReactivePhase\n");
        buf.append("      SELECT CASE(ReactivePhase)\n");
        buf.append("\n");
        int numsubmechs = tmodel.getRowCount();
        for(int i=0;i<numsubmechs;i++) {
	    buf.append("           CASE(");
            Integer iI = new Integer(i);
            buf.append(iI.toString());
            buf.append(")\n");
	    buf.append("              n_Species =");
            Integer nsI = (Integer) tmodel.getValueAt(i,3);
            buf.append(nsI.toString() + "\n");
	    buf.append("              n_Reactions =");
            Integer nrI = (Integer) tmodel.getValueAt(i,4);
            buf.append(nrI.toString() + "\n");
	    buf.append("              ALLOCATE(Concentration(n_Species))\n");
	    buf.append("              CALL FullToReducedSpeciesPhase");
            buf.append(tmodel.getValueAt(i,1));
            buf.append("(FullMechanismConcentration,Concentration)\n");
	}

        buf.append("      END SELECT\n");
        buf.append("\n");
        buf.append("END SUBROUTINE AdjustToReactivePhase\n");
        buf.append("\n");
        return buf.toString();
    }
    String stringLundOptimizedChemicalSourcesPhase() {
        DefaultTableModel tmodel = (DefaultTableModel) programOut.goalTable.getModel();
	StringBuffer buf = new StringBuffer();
        int numsubmechs = tmodel.getRowCount();
        for(int i=0;i<numsubmechs;i++) {
	    String submech = (String) tmodel.getValueAt(i,1);
	    Integer nummolsI    = (Integer) tmodel.getValueAt(i,3);
            int nummols = nummolsI.intValue();
            
	    Integer numrxnsI    = (Integer) tmodel.getValueAt(i,4);
            int numrxns = numrxnsI.intValue();
            
	    stringLundOptimizedChemicalSources2PhaseSub(submech,nummols,numrxns,buf);
	    stringCalculateEnthalpyPhaseSub(submech,nummols,buf);
            stringCalculateEntropyPhaseSub(submech,nummols,buf);
	    stringCalculateHeatCapacityPhase(submech,nummols,buf);
            stringGetSpeciesWeightsPhase(submech,nummols,buf);
            stringGetSpeciesAtomNoPhase(submech,buf);
            stringGetMixtureCoefficientsPhase(submech,nummols,buf);
	}
	return buf.toString();
    }
    void stringLundOptimizedChemicalSources2PhaseSub(String sub,int nummols, int numrxns, StringBuffer buf) {
    buf.append("!------------------------------------------------------------------------------\n");
    buf.append("SUBROUTINE LundOptimizedChemicalSources2Phase");
    buf.append(sub);
    buf.append("( RateW,Concentration,Temperature, & \n");
    buf.append("                                               & SaRateK,SaTemperature,Mode)\n");
    buf.append(" !------------------------------------------------------------------------------\n");
    buf.append("   USE LundReactionMechanismPhase");
    buf.append(sub);
    buf.append("\n");
    buf.append(" !------------------------------------------------------------------------------\n");
    buf.append("   !     OUTPUT DATA\n");
    buf.append("   !               1. vector of omega_i             RateW  [kg/m^3/sec]\n");
    buf.append("   !     INPUT DATA\n");
    buf.append("   !               1. Concentration                 [mol/m^3]\n");
    buf.append("   !               2. temperature                   [K]\n");
    buf.append("   !------------------------------------------------------------------------------\n");
    buf.append("   IMPLICIT NONE\n");
    buf.append("   DOUBLE PRECISION RateW(");
    buf.append(nummols);
    buf.append(")\n");
    buf.append("   DOUBLE PRECISION Concentration(");
    buf.append(nummols);
    buf.append(")\n");
    buf.append("   DOUBLE PRECISION Temperature\n");
    buf.append("   DOUBLE PRECISION :: SaRateK(");
    buf.append(numrxns);
    buf.append(")                ! Arrheneus approximation [mole,cm,sec]\n");
    buf.append("   DOUBLE PRECISION SaTemperature\n");
    buf.append("   INTEGER Mode                  ! Flag - 0: Calculate Arrhenius and copy to SaRateK\n");
    buf.append("                                 ! Flag - 1: Calculate Arrhenius, no copy to SaRateK\n");
    buf.append("                                 ! Flag - 2: Copy Arrhenius from SaRateK\n");
    buf.append("   !------------------------------------------------------------------------------\n");
    buf.append("        CALL LundOptimizedChemicalSources2( RateW,Concentration,Temperature, & \n");
    buf.append("                                          & SaRateK,SaTemperature,Mode)\n");
    buf.append("   !------------------------------------------------------------------------------\n");
    buf.append("   RETURN\n");
    buf.append("END SUBROUTINE LundOptimizedChemicalSources2Phase");
    buf.append(sub);
    buf.append("\n");
    buf.append("\n");
   }
    void stringCalculateEnthalpyPhaseSub(String sub, int nummols, StringBuffer buf) {
    buf.append("!------------------------------------------------------------------------------\n");
    buf.append("SUBROUTINE CalculateEnthalpyPhase");
    buf.append(sub);
    buf.append("(Temperature,Hi)\n");
    buf.append("   USE LundStateFunctionsPhase");
    buf.append(sub);
    buf.append("\n");
    buf.append("   !------------------------------------------------------------------------------\n");
    buf.append("     ! Calculation of the species enthalpy\n");
    buf.append("     ! OUTPUT DATA\n");
    buf.append("     !   1. enthalpy (species)               hi  [J/kg]\n");
    buf.append("     ! INPUT DATA\n");
    buf.append("     !   1. temperature                      t [K]\n");
    buf.append("     !------------------------------------------------------------------------------\n");
    buf.append("     IMPLICIT NONE\n");
    buf.append("     DOUBLE PRECISION Temperature\n");
    buf.append("     DOUBLE PRECISION Hi(");
    buf.append(nummols);
    buf.append(")\n");
    buf.append(" !------------------------------------------------------------------------------\n");
    buf.append("        CALL CalculateEnthalpy(Temperature,Hi)\n");
    buf.append("     RETURN\n");
    buf.append("END SUBROUTINE CalculateEnthalpyPhase");
    buf.append(sub);
    buf.append("\n");
    buf.append("\n");
    }
    void stringCalculateEntropyPhaseSub(String sub, int nummols, StringBuffer buf) {
    buf.append("!------------------------------------------------------------------------------\n");
    buf.append("SUBROUTINE CalculateEntropyPhase");
    buf.append(sub);
    buf.append("(Temperature,Si)\n");
    buf.append("   USE LundStateFunctionsPhase");
    buf.append(sub);
    buf.append("\n");
    buf.append("   !------------------------------------------------------------------------------\n");
    buf.append("     ! Calculation of the species entropy\n");
    buf.append("     ! OUTPUT DATA\n");
    buf.append("     !   1. entropy (species)               si  [J/kg]\n");
    buf.append("     ! INPUT DATA\n");
    buf.append("     !   1. temperature                      t [K]\n");
    buf.append("     !------------------------------------------------------------------------------\n");
    buf.append("     IMPLICIT NONE\n");
    buf.append("     DOUBLE PRECISION Temperature\n");
    buf.append("     DOUBLE PRECISION Si(");
    buf.append(nummols);
    buf.append(")\n");
    buf.append(" !------------------------------------------------------------------------------\n");
    buf.append("        CALL CalculateEntropy(Temperature,Si)\n");
    buf.append("     RETURN\n");
    buf.append("END SUBROUTINE CalculateEntropyPhase");
    buf.append(sub);
    buf.append("\n");
    buf.append("\n");
    }    
    void stringCalculateHeatCapacityPhase(String sub, int nummols, StringBuffer buf) {
	buf.append("!------------------------------------------------------------------------------\n");
	buf.append("SUBROUTINE CalculateHeatCapacityPhase");
	buf.append(sub);
        buf.append("(Temperature,Cpi)\n");
	buf.append("   USE LundStateFunctionsPhase");
	buf.append(sub);
        buf.append("\n");
	buf.append("   !------------------------------------------------------------------------------\n");
	buf.append("     ! Calculation of the species enthalpy\n");
	buf.append("     ! OUTPUT DATA\n");
	buf.append("     !   1. enthalpy (species)               hi  [J/kg]\n");
	buf.append("     ! INPUT DATA\n");
	buf.append("     !   1. temperature                      t [K]\n");
	buf.append("     !------------------------------------------------------------------------------\n");
	buf.append("     IMPLICIT NONE\n");
	buf.append("     DOUBLE PRECISION Temperature\n");
	buf.append("     DOUBLE PRECISION Cpi(");
	buf.append(nummols);
        buf.append(")\n");
	buf.append(" !------------------------------------------------------------------------------\n");
	buf.append("        CALL CalculateHeatCapacity(Temperature,Cpi)\n");
	buf.append("     RETURN\n");
 	buf.append("END SUBROUTINE CalculateHeatCapacityPhase");
	buf.append(sub);
        buf.append("\n");
        buf.append("\n");
   }
    void stringGetSpeciesWeightsPhase(String sub, int nummols, StringBuffer buf) {
        buf.append("SUBROUTINE GetSpeciesWeightsPhase");
        buf.append(sub);
        buf.append("(SpeciesWeight)\n");
        buf.append("   USE LundMechanismDetailsPhase");
        buf.append(sub);
        buf.append("\n");
        buf.append("   !------------------------------------------------------------------------------\n");
        buf.append("     IMPLICIT NONE\n");
        buf.append("     ! molecular weight list [kg/mole] \n");
        buf.append("     DOUBLE PRECISION SpeciesWeight(");
        buf.append(nummols);
        buf.append(")\n");
        buf.append("       CALL GetSpeciesWeights(SpeciesWeight)\n");
        buf.append("     RETURN\n");
        buf.append("END SUBROUTINE GetSpeciesWeightsPhase");
        buf.append(sub);
        buf.append("\n");
        buf.append("\n");
   }
    void stringGetSpeciesAtomNoPhase(String sub, StringBuffer buf) {
        buf.append("SUBROUTINE GetSpeciesAtomNoPhase");
        buf.append(sub);
        buf.append("(n_Atoms)\n");
        buf.append("   USE LundMechanismDetailsPhase");
        buf.append(sub);
        buf.append("\n");
        buf.append("   !------------------------------------------------------------------------------\n");
        buf.append("     IMPLICIT NONE\n");
        buf.append("     INTEGER ::n_Atoms(:,:)\n");
        buf.append("       CALL GetSpeciesAtomNo(n_Atoms)\n");
        buf.append("     RETURN\n");
        buf.append("END SUBROUTINE GetSpeciesAtomNoPhase");
        buf.append(sub);
        buf.append("\n");
        buf.append("\n");
   }
    void stringGetMixtureCoefficientsPhase(String sub, int nummols, StringBuffer buf) {
	buf.append("!------------------------------------------------------------------------------\n");
	buf.append("SUBROUTINE GetMixtureCoefficientsPhase");
	buf.append(sub);
        buf.append("(Yi,Temperature,A16,TLow,THigh)\n");
	buf.append("   USE LundStateFunctionsPhase");
	buf.append(sub);
        buf.append("\n");
	buf.append("   !------------------------------------------------------------------------------\n");
	buf.append("     ! Calculates the NASA coefficients for gas mixtures\n");
	buf.append("     ! OUTPUT DATA\n");
	buf.append("     !   1. coefficients (6)               A16\n");
        buf.append("     !   2. lower bound for the polynomial TLow  [K]\n");
        buf.append("     !   3. upper bound for the polynomial THigh [K]\n");
	buf.append("     ! INPUT DATA\n");
        buf.append("     !   1. species massfraction (species) Yi[:]\n");
	buf.append("     !   2. temperature                      T [K]\n");
	buf.append("     !------------------------------------------------------------------------------\n");
	buf.append("     IMPLICIT NONE\n");
	buf.append("     DOUBLE PRECISION Yi(");
	buf.append(nummols);
        buf.append(")\n");
        buf.append("     DOUBLE PRECISION A16(6)\n");
        buf.append("     DOUBLE PRECISION Temperature\n");
        buf.append("     DOUBLE PRECISION TLow,THigh\n");
	buf.append(" !------------------------------------------------------------------------------\n");
	buf.append("        CALL GetMixtureCoefficients(Yi,Temperature,A16,TLow,THigh)\n");
	buf.append("     RETURN\n");
 	buf.append("END SUBROUTINE GetMixtureCoefficientsPhase");
	buf.append(sub);
        buf.append("\n");
        buf.append("\n");
   }       
    String stringFullToReducedSpeciesPhase() {
        StringBuffer buf = new StringBuffer();
        SpeciesTableModel tmodel = (SpeciesTableModel) programOut.speciesTable.getModel();
        DefaultTableModel gmodel = (DefaultTableModel) programOut.goalTable.getModel();
        int numsubmechs = programOut.SubMechanismInfo.size();
        int totalspecies = tmodel.getRowCount();
        for(int j=0;j<numsubmechs;j++) {
            String submech = (String) gmodel.getValueAt(j,1);
            buf.append("!------------------------------------------------------------------------------\n");
            buf.append("SUBROUTINE FullToReducedSpeciesPhase");
            buf.append(submech);
            buf.append("(FullVector,ReducedVector)\n");
            buf.append("   DOUBLE PRECISION FullVector(");
            buf.append(programOut.FullMechanismData.numberOfSpecies());
            buf.append(")\n");
            buf.append("   DOUBLE PRECISION ReducedVector(:)\n");
            setUpConversion(j,totalspecies,buf,tmodel,true);
            buf.append("   RETURN\n");
            buf.append("END SUBROUTINE FullToReducedSpeciesPhase");
            buf.append(submech);
            buf.append("\n");
            buf.append("!------------------------------------------------------------------------------\n");
            buf.append(" SUBROUTINE ReducedToFullSpeciesPhase");
            buf.append(submech);
            buf.append("(ReducedVector,FullVector)\n");
            buf.append("   DOUBLE PRECISION FullVector(");
            buf.append(programOut.FullMechanismData.numberOfSpecies());
            buf.append(")\n");
            buf.append("   DOUBLE PRECISION ReducedVector(:)\n");
            setUpConversion(j,totalspecies,buf,tmodel,false);
            buf.append("   RETURN\n");
            buf.append("END SUBROUTINE ReducedToFullSpeciesPhase");
            buf.append(submech);
            buf.append("\n");
            buf.append("\n");
           }
        return buf.toString();
        }
    void setUpConversion(int mech, int numspecies, 
                         StringBuffer buf, SpeciesTableModel tmodel, 
                         boolean fulltoreduced) {
        int tablemech = mech+1;
        int count = 0;
        int start = 1;
        int redcountstart = 1;
        int redcountend = 0;
      while(count < numspecies) {
        boolean notdone = true;
        /* Loop to find start point
         *  Find first valid point (not -1)
         */
        while(notdone) {
            Integer speciesused = (Integer) tmodel.getValueAt(count,tablemech);
            if(speciesused.intValue() < 0) {
                count++;
            } else {
                start = count + 1;
                notdone = false;
            }
            if(count >= numspecies)
                notdone = false;
        }
        /*  At this point, count is at a species that is not -1
         *    Loop through until finding a -1
         *    start points is this point
         */
        notdone = true;
        if(count >= numspecies)
             notdone = false;            
        int endcount = count;
        while(notdone) {
            Integer speciesused = (Integer) tmodel.getValueAt(count,tablemech);
            if(speciesused.intValue() >= 0) {
                endcount = count+1;
                count++;
            } else {
                notdone = false;
            }
            if(count >= numspecies)
                notdone = false;            
        }
        /* count points to a -1
         * endcount points to the last vaild point (not -1)
         */
        redcountend = redcountstart + endcount-start;
        if(fulltoreduced) 
            stringVectorOut("Reduced","Full",redcountstart,redcountend,start,endcount,buf);
        else
            stringVectorOut("Full","Reduced",start,endcount,redcountstart,redcountend,buf);
        redcountstart += endcount - start + 1;
      }
    }
    void stringVectorOut(String dest, String source, int start, int endcount, 
                            int redcountstart, int redcountend,StringBuffer buf) {
        buf.append("    " + dest + "Vector(");
        buf.append(start);
        buf.append(":");
        buf.append(endcount);
        buf.append(") = " + source + "Vector(");
        buf.append(redcountstart);
        buf.append(":");
        buf.append(redcountend);
        buf.append(")\n");
    }
    String stringInitializePhases() {
	StringBuffer buf = new StringBuffer();
        buf.append("SUBROUTINE InitializePhases(n_Species,  n_Reactions)\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("!  This is the initialization routine.  \n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("   IMPLICIT NONE\n");
        buf.append("   !-------------------------------------------------------------------------------------------\n");
        buf.append("      INTEGER n_Species, n_Reactions\n");
        buf.append("   !-------------------------------------------------------------------------------------------\n");
        buf.append("! Set to Full Mechanism\n");
        buf.append("              ReactivePhase =");
	Integer numI = 5; // Hardcoded fix this!!!
        buf.append(numI.toString() + "\n");
        buf.append("              n_Species =");
        int totalspecies = programOut.FullMechanismData.numberOfSpecies();
        buf.append(totalspecies);
        buf.append("\n");
        buf.append("              n_Reactions = ");
        int numrxns = programOut.FullMechanismData.numberOfReactions();
        buf.append(numrxns);
        buf.append("\n");
        buf.append(" END SUBROUTINE InitializePhases\n");
        buf.append("\n");
	return buf.toString();
    }
    String stringModulePreamble() {
	StringBuffer buf = new StringBuffer();
        buf.append("MODULE PhaseReductionModule\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("!  This is the main Phase Optimized Mechanism Module\n");
        buf.append("!  The top level routines are here along with those\n");
        buf.append("!  dealing with the actual calling of the the proper\n");
        buf.append("!  submechanism.\n");
        buf.append("!\n");
        buf.append("!  This module was specifically designed to be dependent only on the\n");
        buf.append("!  individual modules of the phases.  For this reason, n_Species and\n");
        buf.append("!  n_Reactions are used in the input/output variables of the subroutines.\n");
        buf.append("!\n");
        buf.append("!  The design of the use of phase optimized mechanisms minimizes the \n");
        buf.append("!  changes from a 'normal' call to gate_backtime0d.  The only additional\n");
        buf.append("!  routine needed is:\n");
        buf.append("!      ALL InitializePhases(n_Species,  n_Reactions)\n");
        buf.append("!  All other manipulations are handled 'internally' by the phase optimized\n");
        buf.append("!  mini-ignition.\n");
        buf.append("! \n");
        buf.append("! The gate_backtime0d interface to the POSM algorithm (input the same as gate_backtime0d:\n");
        buf.append("!  POSM_backtime\n");
        buf.append("!\n");
        buf.append("! Reduce the full set of species to reduced set:\n");
        buf.append("!   FullToReducedSpeciesPhaseXXX(FullVector,ReducedVector)\n");
        buf.append("!  \n");
        buf.append("! Expand the reduced set from the reduced set\n");
        buf.append("!   ReducedToFullSpeciesPhaseXXX(ReducedVector,FullVector)\n");
        buf.append("!\n");
        buf.append("! Calculate the Enthalpy for each phase (used in LundStateFunctions)\n");
        buf.append("!    CalculateEnthalpyPhaseXXX\n");
        buf.append("! Calculate the heat capacity for each phase (used in LundStateFunctions)\n");
        buf.append("!    CalculateHeatCapacityPhaseXXX\n");
        buf.append("! Get the vector of species weights for each phase (used in LundMechanismDetails)\n");
        buf.append("!    GetSpeciesWeightsPhaseXXX\n");
        buf.append("!\n");
        buf.append("!  Note that the other routines in LundMechanismDetails are not divided up into\n");
        buf.append("!   phases (they are not used externally yet).  They refer to the full mechanism.\n");
        buf.append(" !------------------------------------------------------------------------------\n");
        buf.append(" ! The current reactive phase\n");
        buf.append(" INTEGER ReactivePhase\n");
        buf.append(" ! The concentration of the all the species.  This is updated every cycle\n");
        buf.append(" DOUBLE PRECISION FullMechanismConcentration(");
	Integer numI = new Integer(programOut.FullMechanismData.numberOfSpecies());
        buf.append(numI.toString());
        buf.append(")\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("   CONTAINS\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("\n");
	return buf.toString();
    }
    String ModulePostfix() {
	StringBuffer buf = new StringBuffer();
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("END MODULE PhaseReductionModule");
        return buf.toString();
    }
    String generateCASE(String routineName, String args) {
	StringBuffer buf = new StringBuffer();
        DefaultTableModel tmodel = (DefaultTableModel) programOut.goalTable.getModel();
        int numsubmechs = tmodel.getRowCount();
        buf.append("      SELECT CASE(ReactivePhase)\n");
        buf.append("\n");
        for(int i=0;i<numsubmechs;i++) {
	    buf.append("           CASE(");
            Integer iI = new Integer(i);
            buf.append(iI.toString());
            buf.append(")\n");
            buf.append("              CALL " + routineName + "Phase");
            buf.append(tmodel.getValueAt(i,1));
            buf.append("("+ args + ")\n");
	}

        buf.append("      END SELECT\n");
        buf.append("\n");
        return buf.toString();
        
    }
    String stringCalculateEnthalpy() {
        StringBuffer buf = new StringBuffer();
        buf.append("   SUBROUTINE CalculateEnthalpy(Temperature,Hi)\n");
        buf.append("   USE PhaseReductionModule\n");
        buf.append("   !------------------------------------------------------------------------------\n");
        buf.append("     ! Calculation of the species enthalpy\n");
        buf.append("     ! OUTPUT DATA\n");
        buf.append("     !   1. enthalpy (species)               hi  [J/kg]\n");
        buf.append("     ! INPUT DATA\n");
        buf.append("     !   1. temperature                      t [K]\n");
        buf.append("     !------------------------------------------------------------------------------\n");
        buf.append("     IMPLICIT NONE\n");
        buf.append("     DOUBLE PRECISION Temperature\n");
        buf.append("     DOUBLE PRECISION Hi(:)\n");
        buf.append(" !------------------------------------------------------------------------------\n");
        
        String enthalpy = generateCASE("CalculateEnthalpy","Temperature,Hi");
        buf.append(enthalpy);
        buf.append("  !------------------------------------------------------------------------------\n");
        buf.append("  END SUBROUTINE CalculateEnthalpy\n");
        buf.append("\n");
        return buf.toString();       
    }
    String stringCalculateEntropy() {
        StringBuffer buf = new StringBuffer();
        buf.append("   SUBROUTINE CalculateEntropy(Temperature,Si)\n");
        buf.append("   USE PhaseReductionModule\n");
        buf.append("   !------------------------------------------------------------------------------\n");
        buf.append("     ! Calculation of the species entropy\n");
        buf.append("     ! OUTPUT DATA\n");
        buf.append("     !   1. entropy (species)                si  [J/kg]\n");
        buf.append("     ! INPUT DATA\n");
        buf.append("     !   1. temperature                      t [K]\n");
        buf.append("     !------------------------------------------------------------------------------\n");
        buf.append("     IMPLICIT NONE\n");
        buf.append("     DOUBLE PRECISION Temperature\n");
        buf.append("     DOUBLE PRECISION Si(:)\n");
        buf.append(" !------------------------------------------------------------------------------\n");       
        String entropy = generateCASE("CalculateEntropy","Temperature,Si");
        buf.append(entropy);
        buf.append("  !------------------------------------------------------------------------------\n");
        buf.append("  END SUBROUTINE CalculateEntropy\n");
        buf.append("\n");
        return buf.toString();        
    }

    String stringCalculateHeatCapacity() {
        StringBuffer buf = new StringBuffer();
        buf.append("   SUBROUTINE CalculateHeatCapacity(Temperature,Cpi)\n");
        buf.append("   USE PhaseReductionModule\n");
        buf.append("   !------------------------------------------------------------------------------\n");
        buf.append("   ! Calculation of the species enthalpy\n");
        buf.append("   ! OUTPUT DATA\n");
        buf.append("   !   1. enthalpy (species)               hi  [J/kg]\n");
        buf.append("   ! INPUT DATA\n");
        buf.append("   !   1. temperature                      t [K]\n");
        buf.append("   !------------------------------------------------------------------------------\n");
        buf.append("   IMPLICIT NONE\n");
        buf.append("   DOUBLE PRECISION Temperature\n");
        buf.append("   DOUBLE PRECISION Cpi(:)\n");
        buf.append("   !------------------------------------------------------------------------------\n");
        String heat = generateCASE("CalculateHeatCapacity","Temperature,Cpi");
        buf.append(heat);
        buf.append("   !------------------------------------------------------------------------------\n");
        buf.append("   END SUBROUTINE CalculateHeatCapacity\n");
        buf.append("\n");
        return buf.toString();
    }
    String stringGetMixtureCoefficients() {
	StringBuffer buf = new StringBuffer();
	buf.append("   SUBROUTINE GetMixtureCoefficients(Yi,Temperature,A16,TLow,THigh)\n");
        buf.append("   USE PhaseReductionModule\n");
	buf.append("   !------------------------------------------------------------------------------\n");
	buf.append("   ! Calculates the NASA coefficients for gas mixtures\n");
	buf.append("   ! OUTPUT DATA\n");
	buf.append("   !   1. coefficients (6)               A16\n");
        buf.append("   !   2. lower bound for the polynomial TLow  [K]\n");
        buf.append("   !   3. upper bound for the polynomial THigh [K]\n");
	buf.append("   ! INPUT DATA\n");
        buf.append("   !   1. species massfraction (species) Yi[:]\n");
	buf.append("   !   2. temperature                      T [K]\n");
	buf.append("   !------------------------------------------------------------------------------\n");
	buf.append("   IMPLICIT NONE\n");
	buf.append("   DOUBLE PRECISION Yi(:)\n");
        buf.append("   DOUBLE PRECISION A16(6)\n");
        buf.append("   DOUBLE PRECISION Temperature\n");
        buf.append("   DOUBLE PRECISION TLow,THigh\n");
	buf.append("   !------------------------------------------------------------------------------\n");
        String mixt = generateCASE("GetMixtureCoefficients","Yi,Temperature,A16,TLow,THigh");
        buf.append(mixt);
 	buf.append("   END SUBROUTINE GetMixtureCoefficients");
        buf.append("\n");
        return buf.toString();
   }       

    String stringLundStatePreamble() {
        StringBuffer buf = new StringBuffer();
        buf.append("MODULE LundStateFunctions\n");
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("   ! Author: GENERATED REACTION (Edward S. Blurock\n");
        buf.append("   !           after pattern MINI_IGNITION (Fabian Mauss) \n");
        buf.append("   CONTAINS\n");
        buf.append("   !------------------------------------------------------------------------------\n");
        buf.append("\n");
        return buf.toString();
    }
    String stringLundStatePost() {
        StringBuffer buf = new StringBuffer();
        buf.append("!------------------------------------------------------------------------------\n");
        buf.append("END MODULE LundStateFunctions\n");
        return buf.toString();
    }
    String stringGetSSSpeciesNames() {
        StringBuffer buf = new StringBuffer();
        buf.append("   SUBROUTINE GetSteadyStateSpeciesNames(SteadyStateSpeciesName)\n");
        buf.append("   !------------------------------------------------------------------------------\n");
        buf.append("     IMPLICIT NONE\n");
        buf.append("     CHARACTER (LEN=16) SteadyStateSpeciesName(   0)\n");
        buf.append(" \n");
        buf.append("     !------------------------------------------------------------------------------\n");
        buf.append("     ! Steady State Species list:\n");
        buf.append(" \n");
        buf.append("     RETURN\n");
        buf.append("   !------------------------------------------------------------------------------\n");
        buf.append("   END SUBROUTINE GetSteadyStateSpeciesNames\n");
        buf.append("\n");
        return buf.toString();
    }
    String stringGetSpeciesNames() {
         StringBuffer buf = new StringBuffer();
         SpeciesTableModel tmodel = (SpeciesTableModel) programOut.speciesTable.getModel();
        int totnumspecies = tmodel.getRowCount();
        int nummechs = tmodel.getColumnCount();
        buf.append("   SUBROUTINE GetSpeciesNames (SpeciesName)\n");
         buf.append("   USE PhaseReductionModule\n");
         buf.append("   !------------------------------------------------------------------------------\n");
         buf.append("     IMPLICIT NONE\n");
         buf.append("     CHARACTER (LEN=16) SpeciesName(" + totnumspecies + ")\n");
         buf.append("     SELECT CASE(ReactivePhase)\n");
         buf.append("\n");
         for(int i=0;i<nummechs;i++) {
 	    buf.append("           CASE(");
            Integer iI = new Integer(i);
            buf.append(iI.toString());
            buf.append(")\n");
            int spcount = 0;
            String blanks16 = "                ";
            for(int spcs = 0;spcs < totnumspecies;spcs++) {
              if(i<nummechs-1) {
                Integer Icnt = (Integer) tmodel.getValueAt(spcs,i+1);
                int cnt = Icnt.intValue();
                if(cnt >= 0) {
                    spcount++;
                    buf.append("          SpeciesName(" + spcount + ") = '");
                    String name = (String) tmodel.getValueAt(spcs,0);
                    buf.append("" + name + blanks16.substring(name.length()) + "'\n");
                }
              } else {
                  int sc = spcs + 1;
                  buf.append("          SpeciesName(" + sc + ") = '");
                  String name = (String) tmodel.getValueAt(spcs,0);
                  buf.append("" + name + blanks16.substring(name.length()) + "'\n");
              }
            }
         }
         buf.append("   END SELECT\n");         
         buf.append("   !------------------------------------------------------------------------------\n");         
         buf.append("   END SUBROUTINE GetSpeciesNames\n");
         buf.append("\n");
         return buf.toString();
    }
    String stringMechanismDetailsPreamble() {
        StringBuffer buf = new StringBuffer();
         buf.append("MODULE LundMechanismDetails\n");
         buf.append("!------------------------------------------------------------------------------\n");
         buf.append("  ! n_Species: number of species\n");
         buf.append("  INTEGER :: n_Species =    0\n");
         buf.append("  ! n_Reactions: number of reactions\n");
         buf.append("  INTEGER :: n_Reactions =   0\n");
         buf.append("\n");
         buf.append("  CONTAINS\n");
        buf.append("\n");
        return buf.toString();
    }
    String stringGetSpeciesWeights() {
        StringBuffer buf = new StringBuffer();
         buf.append("   SUBROUTINE GetSpeciesWeights(SpeciesWeight)\n");
         buf.append("   USE PhaseReductionModule\n");
         buf.append("   !------------------------------------------------------------------------------\n");
         buf.append("     IMPLICIT NONE\n");
         buf.append("     ! molecular weight list [kg/mole] \n");
         buf.append("     DOUBLE PRECISION SpeciesWeight(:)\n");
         buf.append("   !------------------------------------------------------------------------------\n");
         String weights = generateCASE("GetSpeciesWeights","SpeciesWeight");
         buf.append(weights);
         buf.append("   !------------------------------------------------------------------------------\n");
         buf.append("   END SUBROUTINE GetSpeciesWeights\n");
         buf.append("\n");
        return buf.toString();
    }
    String stringNumberOf(boolean species) {
        StringBuffer buf = new StringBuffer();
        
        String routine;
        if(species)
            routine = "GetNumberOfAllSpecies";
        else
            routine = "GetNumberOfReactions";
        
         buf.append("   INTEGER FUNCTION " + routine + "()\n");
         buf.append("   !------------------------------------------------------------------------------\n");
         buf.append("   USE PhaseReductionModule\n");
         buf.append("     IMPLICIT NONE\n");
         buf.append("\n");
        DefaultTableModel tmodel = (DefaultTableModel) programOut.goalTable.getModel();
        int numsubmechs = tmodel.getRowCount();
        buf.append("      SELECT CASE(ReactivePhase)\n");
        buf.append("\n");
        for(int i=0;i<numsubmechs;i++) {
	    buf.append("           CASE(");
            Integer iI = new Integer(i);
            buf.append(iI.toString());
            buf.append(")\n");
            if(species) {
	       buf.append("              GetNumberOfAllSpecies =");
               Integer nsI = (Integer) tmodel.getValueAt(i,3);
               buf.append(nsI.toString() + "\n");
            } else {
	       buf.append("              GetNumberOfReactions =");
               Integer nrI = (Integer) tmodel.getValueAt(i,4);
               buf.append(nrI.toString() + "\n");
            }
	}

        buf.append("      END SELECT\n");
        buf.append("\n");
         buf.append("     RETURN\n");
         buf.append("  !------------------------------------------------------------------------------\n");
         buf.append("  END FUNCTION " + routine + "\n");
         buf.append("\n");
         return buf.toString();
    }
    
    String stringNumberSteadyState() {
        StringBuffer buf = new StringBuffer();
         buf.append("   INTEGER FUNCTION GetNumberOfSteadySpecies()\n");
         buf.append("   !------------------------------------------------------------------------------\n");
         buf.append("     IMPLICIT NONE\n");
         buf.append("     GetNumberOfSteadySpecies =   0\n");
         buf.append("     RETURN\n");
         buf.append("   !------------------------------------------------------------------------------\n");
         buf.append("   END FUNCTION GetNumberOfSteadySpecies\n");
         buf.append("\n");
         return buf.toString();
    }
    String stringGetSpeciesAtomNo() {
        StringBuffer buf = new StringBuffer();
         buf.append("   SUBROUTINE GetSpeciesAtomNo(n_Atoms)\n");
         buf.append("   USE PhaseReductionModule\n");
         buf.append("   !------------------------------------------------------------------------------\n");
         buf.append("     IMPLICIT NONE\n");
         buf.append("     INTEGER :: n_Atoms(:,:)\n");
         buf.append("    !------------------------------------------------------------------------------\n");
         String atomno = generateCASE("GetSpeciesAtomNo","n_Atoms");
         buf.append(atomno);
         buf.append("   !------------------------------------------------------------------------------\n");
         buf.append("   END SUBROUTINE GetSpeciesAtomNo\n");
         buf.append("\n");
        return buf.toString();
    }

    String stringMechanismDetailsPost() {
        StringBuffer buf = new StringBuffer();
         buf.append(" !------------------------------------------------------------------------------\n");
         buf.append("END MODULE LundMechanismDetails\n");
        return buf.toString();
    }
     String stringChemicalSources() {
         StringBuffer buf = new StringBuffer();
         buf.append("SUBROUTINE LundOptimizedChemicalSources2( RateW,Concentration,Temperature, & \n");
         buf.append("                                         & SaRateK,SaTemperature,Mode)\n");
         buf.append(" ! n_Species: number of species in the mechanism\n");
         buf.append(" ! GetSpeciesWeights: Subroutine returning species molecular weights\n");
         buf.append(" USE LundMechanismDetails,   ONLY: n_Species,n_Reactions\n");
         buf.append("  USE PhaseReductionModule\n");
         buf.append("!------------------------------------------------------------------------------\n");
         buf.append("  !     OUTPUT DATA\n");
         buf.append("  !               1. vector of omega_i             RateW  [kg/m^3/sec]\n");
         buf.append("  !     INPUT DATA\n");
         buf.append("  !               1. Concentration                 [mol/m^3]\n");
         buf.append("  !               2. temperature                   [K]\n");
         buf.append("  !------------------------------------------------------------------------------\n");
         buf.append("  IMPLICIT NONE\n");
         buf.append("  DOUBLE PRECISION RateW(n_Species)\n");
         buf.append("  DOUBLE PRECISION Concentration(n_Species)\n");
         buf.append("  DOUBLE PRECISION Temperature\n");
         buf.append("  DOUBLE PRECISION :: SaRateK(n_Reactions)                ! Arrheneus approximation [mole,cm,sec]\n");
         buf.append("  DOUBLE PRECISION SaTemperature\n");
         buf.append("  INTEGER Phase\n");
         buf.append("  INTEGER Mode                  ! Flag - 0: Calculate Arrhenius and copy to SaRateK\n");
         buf.append("                                ! Flag - 1: Calculate Arrhenius, no copy to SaRateK\n");
         buf.append("                                ! Flag - 2: Copy Arrhenius from SaRateK\n");
         buf.append("  !------------------------------------------------------------------------------\n");
         buf.append("\n");
         String sources = generateCASE("LundOptimizedChemicalSources2","RateW,Concentration,Temperature,SaRateK,SaTemperature,Mode");
         buf.append(sources);
         buf.append(" !------------------------------------------------------------------------------\n");
         buf.append("  RETURN\n");
         buf.append("!------------------------------------------------------------------------------\n");
         buf.append(" END SUBROUTINE LundOptimizedChemicalSources2\n");
         buf.append("\n");
         return buf.toString();
       
    }
   public void stringImportance(String basedir) {
         SpeciesTableModel tmodel = (SpeciesTableModel) programOut.speciesTable.getModel();
        int nummechs = tmodel.getColumnCount();
     try {
         for(int i=0;i<nummechs;i++) {
            Integer iI = new Integer(i);
            String mechname;
            String ilist;
            if(i == nummechs-1) {
                mechname = "Full";
                ilist = stringImportanceListFull();
            } else {
                mechname = "mech" + iI.toString();
                ilist = stringImportanceList(iI.intValue());
           }
            File importanceF = new File(mechname,"importance.txt");
            File massfractionF = new File(mechname,"massfraction.txt");
            File mechimportance = new File(basedir,importanceF.toString());
            File mechmassfraction = new File(basedir,massfractionF.toString());
            System.out.println(mechimportance.toString());
            FileWriter wStr = new FileWriter(mechimportance);
            PrintWriter w = new PrintWriter(wStr);
            FileWriter mStr = new FileWriter(mechmassfraction);
            PrintWriter m = new PrintWriter(mStr);
            w.print(ilist);
            w.print("\nEnd\n");
            w.close();
            m.print(ilist);
            m.print("End\n");
            m.close();
         }
      } catch(FileNotFoundException io) {
           ErrorFrame fr = new ErrorFrame("Importance file could not be written:\n" + io);
      } catch(IOException io) {
           ErrorFrame fr = new ErrorFrame("Importance file could not be written:\n" + io);
      }
    }
    String stringImportanceList(int submech) {
       StringBuffer buf = new StringBuffer();
       SpeciesTableModel tmodel = (SpeciesTableModel) programOut.speciesTable.getModel();
       int totnumspecies = tmodel.getRowCount();
       String blanks16 = "                ";
       for(int spcs = 0;spcs < totnumspecies;spcs++) {
           Integer Icnt = (Integer) tmodel.getValueAt(spcs,submech+1);
           int cnt = Icnt.intValue();
           if(cnt >= 0) {
                String name = (String) tmodel.getValueAt(spcs,0);
                buf.append("   " + name + blanks16.substring(name.length()) + " = 1.0\n");
           }
       }
       return buf.toString();
    }
    String stringImportanceListFull() {
       StringBuffer buf = new StringBuffer();
       SpeciesTableModel tmodel = (SpeciesTableModel) programOut.speciesTable.getModel();
       int totnumspecies = tmodel.getRowCount();
       String blanks16 = "                ";
       for(int spcs = 0;spcs < totnumspecies;spcs++) {
           String name = (String) tmodel.getValueAt(spcs,0);
           buf.append("   " + name + blanks16.substring(name.length()) + " = 0.0\n");
       }
       return buf.toString();
    }
}
