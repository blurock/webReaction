/*
 * RegisteredClasses.java
 *
 * Created on February 27, 2001, 3:31 PM
 */

package blurock.coreobjects;
import blurock.opandalgs.parameterized.*;
import blurock.opandalgs.ops.*;
import blurock.opandalgs.expressions.*;
import blurock.rules.prodsys.*;
import blurock.rules.actions.*;
import blurock.opt.base.*;
import blurock.pop.base.*;
import blurock.pop.genetic.*;
import blurock.logic.base.*;
import blurock.logic.description.*;
import blurock.logic.predicates.*;
import blurock.CobwebCluster.*;
import blurock.DecisionTree.*;
import blurock.EvaluationTree.*;
import blurock.DirectedTreeObjects.*;
import blurock.numeric.numops.*;
import blurock.core.ObjectNotFoundException;
import blurock.EquivalenceClasses.*;
/**
 * This is the standard set of classes distributed with the ReactImport system
 * @author Edward S. Blurock
 * @version 1.0
 */
public class RegisteredClasses extends blurock.coreobjects.DataSetOfObjectsClass {
    int IDCount = 90000;
    /** Creates new RegisteredClasses */
    public RegisteredClasses() {
        registerCoreDataObjects();
        registerOpsAndAlgs();
        registerRuleSystems();
        registerOptimization();
        registerPopulationBased();
        registerGeneticBased();
        registerLogic();
        registerClusterDecision();
        regisiterDirectedTreeObjects();
        registerEvaluationTree();
        registerDecisionTree();
        equivalenceClasses();
        constructedClasses();
    }
    /**
     * This returns the current class Identification number and increments the counter (to insure unique numbers for created IDs).
     * @return The next available class identification is returned
     */
    public int getNextICount() {
        IDCount++;
        return IDCount;
    }
    /**
     * This returns the current class identification number
     * @return The current class identification number
     */
    public int getIDCount() {
        return IDCount;
    }
    /**
     * This sets the current class identification number.
     * @param count The class identification number.
     */
    public void setIDCount(int count) {
        
    }
    private void registerCoreDataObjects() {
        DataObjectClass obj = new DataObjectClass(100,"Object","The Base Object");
        DataSetOfObjectsClass setobjs = new DataSetOfObjectsClass(101,"SetOfObjects",
                                "A Set of Classes");
        
        DataNumericClass num = new DataNumericClass(110,"Numeric","The Base Numeric Class");
        DataIntegerClass i   = new DataIntegerClass(111,"Integer","The Integer Class");
        DataRealClass r      = new DataRealClass(112,"Real","The Real Class");
        DataStringClass s    = new DataStringClass(113, "String", "The String Class");
        
        AddClass(obj);
        AddClass(setobjs);
        AddClass(num);
        AddClass(i);
        AddClass(r);
        AddClass(s);
    }
    private void registerOpsAndAlgs() {
        DataExpressionTreeClass exptree = new DataExpressionTreeClass(200,"ExpressionTree",
                                                "Expression as a Tree");
        DataOperationClass op = new DataOperationClass(210,"Operation","The Base Operation Class");
        DataParameterSetClass set = new DataParameterSetClass(220,"ParameterSet",
                                            "The Set of Parameters Class");
        DataParameterizedFunctionClass pfunc = new DataParameterizedFunctionClass(221,
                                            "ParameterizedFunction",
                                            "The Base Parameterized Function");
        DataNumericPredicateClass numpred = new DataNumericPredicateClass(3106,"NumericPredicate","Numeric Predicate Operation");
        System.out.println("NumericPredicate Type: " + numpred.Type);
        DataFuncReal1DClass fun1d = new DataFuncReal1DClass(2010,"ODReal","One dimensional real function");
        DataPyramidFunctionClass pyr = new DataPyramidFunctionClass(2060,"PyramidODReal","One Dimensional Pyramid Numeric Function");

        AddClass(exptree);
        AddClass(op);
        AddClass(set);
        AddClass(pfunc);
        AddClass(numpred);
        AddClass(fun1d);
        AddClass(pyr);
    }
    private void registerRuleSystems() {
        DataActionRuleClass act = new DataActionRuleClass(300,"ActionRule",
                                        "A single Action Rule with condition");
        DataSetOfRulesClass rset = new DataSetOfRulesClass(301,"SetOfRules",
                                        "A set of rules");
        DataProductionSystemClass prod = new DataProductionSystemClass(302,"ProductionSystem",
                                        "A full production system set of rules");
        
        AddClass(act);
        AddClass(rset);
        AddClass(prod);
    }
    private void registerOptimization() {
        DataOptimizeCostFunctionClass cost = new DataOptimizeCostFunctionClass(400,"OptimizeCostFunction",
                                        "The generic cost function for optimization");
        AddClass(cost);
    }
    private void registerPopulationBased() {
        DataPopulationObjectClass popobj = new DataPopulationObjectClass(501,"PopulationObject",
                                        "A single element in the population of optimized objects");
        DataPopulationClass pop = new DataPopulationClass(502,"Population",
                                        "The population of optimized objects");
        DataPopulationCostClass popcost = new DataPopulationCostClass(503,"PopulationCost",
                                        "The cost function related to an entire population");
        AddClass(popobj);
        AddClass(pop);
        AddClass(popcost);
    }
    private void registerGeneticBased() {
        DataGeneticObjectClass genobj = new DataGeneticObjectClass(500,"GeneticObject",
                                        "The base genetic object for optimization");
        DataGeneticDistributionClass dist = new DataGeneticDistributionClass(510,"GeneticDistribution",
                                        "Genetic Real based on distribution of points");
        DataGeneticSetOfParametersClass set = new DataGeneticSetOfParametersClass(511,
                                        "GeneticSetOfParameters",
                                        "Genetic set of parameters");
        AddClass(genobj);
        AddClass(dist);
        AddClass(set);
    }
    private void registerLogic() {
        DataLogicalClass log = new DataLogicalClass(600,"Logical","The base logical class");
        DataContinuousClass cont = new DataContinuousClass(601,"Continuous",
                    "The continuous logic class");
        DataNValuedClass nvl = new DataNValuedClass(602,"NValued","The descrete nValued class");
        DataLogicDescriptionClass ldesc = new DataLogicDescriptionClass(610,"LogicDescription"
                ,"A set of logical descriptions");
        DataSetOfLogicDescriptionsClass ldescset =
               new DataSetOfLogicDescriptionsClass(611,"SetOfLogicDescriptions"
                ,"A set of logical descriptions");
        DataDescriptionExpressionsClass dexp =
                new DataDescriptionExpressionsClass(612,"DescriptionExpressions",
                "A set of logical expressions");
        DataExactlyEqualPredicateClass exact =
                new DataExactlyEqualPredicateClass(606,"ExactlyEqualPredicate","Exact Comparison with an Object");
    DataConjunctionClass ConjunctionClass = new DataConjunctionClass(10006,"Conjunction","Conjunction");
    DataDescriptionClass DescriptionClass = new DataDescriptionClass(10002,"Description","Description of Conjunction");
    DataPredicateClass pred = new DataPredicateClass(10001,"Predicate","Predicate");
        AddClass(log);
        AddClass(cont);
        AddClass(nvl);
        AddClass(ldesc);
        AddClass(ldescset);
        AddClass(dexp);
        AddClass(ConjunctionClass);
        AddClass(DescriptionClass);
        AddClass(pred);
        AddClass(exact);
    }
    private void registerClusterDecision() {
        DataCobwebClusterClass clusterC = new DataCobwebClusterClass(14040,"CobwebClusterTree","Cobweb Cluster Tree");
        DataCobwebClusterNodeStatsClass nodestatsC = new DataCobwebClusterNodeStatsClass(14030,
                "CobwebClusterNodeStats","Cobweb Cluster Tree Node Statistics");
        clusterC.nodeClass = nodestatsC;
        AddClass(clusterC);
        AddClass(nodestatsC);
    }
    private void regisiterDirectedTreeObjects() {
        DataDirectedTreeClass dirtree = new DataDirectedTreeClass(15020,"DirectedTree","Directed Tree");
        DataTreeNodeClass treenode = new DataTreeNodeClass(15010,"TreeNode","Tree Node");
        
        AddClass(dirtree);
        AddClass(treenode);
    }
    private void registerEvaluationTree() {
        DataEvaluationTreeClass evaltree = new DataEvaluationTreeClass(16030,"EvaluationTree","Evaluation Tree");
        AddClass(evaltree);
    }
    private void registerDecisionTree() {
        DataDecisionTreeNodeSpecClass nodespec = new DataDecisionTreeNodeSpecClass();
        DataDecisionTreeClass dectree = new DataDecisionTreeClass();
        DataDecisionTreeNodeStatsClass nodestats = new DataDecisionTreeNodeStatsClass();
        AddClass(nodespec);
        AddClass(dectree);
        AddClass(nodestats);
    }
    private void constructedClasses() {
      try {
        DataPredicateClass pred = (DataPredicateClass) findClass("Predicate").Clone();
        pred.Name = "CriticalPointsFuzzyPredicate";
        pred.SubClass = "Predicate";
        pred.Type = "CriticalPointsFuzzyPredicate";
        pred.Identification = 100001;
        pred.derivedClass = true;
        DataNumericPredicateClass numpredclass = (DataNumericPredicateClass) findClass("NumericPredicate");
        DataNumericPredicateClass numpred = (DataNumericPredicateClass) numpredclass.Clone();
        DataPyramidFunctionClass pyr = (DataPyramidFunctionClass) findClass("PyramidODReal").Clone();
        DataLogicalClass log = (DataLogicalClass) findClass("Continuous").Clone();

        numpred.Name = "PyramidContinuousLogicNumericPredicate";
        numpred.SubClass = "NumericPredicate";
        numpred.Type = "PyramidContinuousLogicNumericPredicate";
        numpred.Identification = 100002;
        numpred.PredicateFunction = (DataFuncReal1DClass) pyr;
        numpred.LogicClass = log;
        numpred.Description = "Predicate Function for Critical Point Pairs";
        pred.OpClass = numpred;
        
        AddClass(pred);
        AddClass(numpred);
        
        DataObjectClass cls = findClass("CriticalPointsFuzzyPredicate");
        System.out.println("Constructed Class found: " + cls.Name);
        } catch(ObjectNotFoundException ex) {
            System.err.println( ex + " not defined in registered classes");
        }
    }
    private void equivalenceClasses() {
        int base = 31000;
        
        DataSetOfEquivalentSetsClass setsclass = new DataSetOfEquivalentSetsClass(base+3,
                "SetOfEquivalentSets","A partitioning of equivalent sets");
        AddClass(setsclass);
        DataEquivalentSetClass set = new DataEquivalentSetClass(base+2,
                "EquivalentSet","Equivalent Set");
        AddClass(set);
        DataDegreeOfEquivalenceClass degree = new DataDegreeOfEquivalenceClass(base+1,
                "DegreeOfEquivalence","The definition of the equivalence class");
        AddClass(degree);
        DataDegreeOfEquivalenceNumericClass numdegree = new DataDegreeOfEquivalenceNumericClass(base+5,
                "DegreeOfEquivalenceNumeric","Equivalence Definition with vector");
        AddClass(numdegree);
        DataRealClass rclass = new DataRealClass();
        set.CutOffCriteriaClass = (DataObjectClass) rclass;
        set.EquivalenceClass = (DataDegreeOfEquivalenceClass) degree;        
       
    }
}
