package blurock.DecisionTree;
import blurock.SeparateUnderCondition.*;

public class DetermineReactivePhase implements DetermineCondition {
//------------------------------------------------------------------------------
//     INPUT DATA
//              1. Concentration                 [mol/m^3]
//-----------------------------------------------------------------------------
   public DetermineReactivePhase() {
   }
    public ConditionChoice determineCondition(ConditionParameters parameters) {
     ConditionParametersAsDoubles parametersD = (ConditionParametersAsDoubles) parameters;
      double[] Concentration = parametersD.Parameters;
      int DetermineReactivePhaseAns = 0;
      int HO2       = 5;
      int CO        = 0;
      int IC8H16    = 66;
      int CH3OH     = 16;
      int C3H6      = 29;
      int H2O2      = 8;
      int H         = 3;
      int O         = 6;
      int HCO       = 11;
      
        if( Concentration[HO2] < 1.0E-6) { 
              if( Concentration[CO] < 1.0E-7) { 
                   if( Concentration[IC8H16] < 1.0E-7) { 
                   DetermineReactivePhaseAns = 2;
                   } else if( Concentration[IC8H16] > 1.0E-7) { 
                   DetermineReactivePhaseAns = 1;
                   }
              } else if( Concentration[CO] > 1.0E-7) { 
                   if( Concentration[CH3OH] < 1.0E-7) { 
                   DetermineReactivePhaseAns = 6;
                   } else if( Concentration[CH3OH] > 1.0E-7) { 
                   DetermineReactivePhaseAns = 5;
                   }
              }
         } else if( Concentration[HO2] > 1.0E-6) { 
              if( Concentration[C3H6] < 1.0E-7) { 
                   if( Concentration[H2O2] < 1.0E-6) { 
                        if( Concentration[O] < 1.0E-4) { 
                             if( Concentration[H] < 1.0E-6) { 
                             DetermineReactivePhaseAns = 3;
                             } else if( Concentration[H] > 1.0E-6) { 
                             DetermineReactivePhaseAns = 3;
                             }
                        } else if( Concentration[O] > 1.0E-4) { 
                        DetermineReactivePhaseAns = 3;
                        }
                   } else if( Concentration[H2O2] > 1.0E-6) { 
                        if( Concentration[HCO] < 1.0E-7) { 
                             if( Concentration[HO2] < 1.0E-5) { 
                             DetermineReactivePhaseAns = 3;
                             } else if( Concentration[HO2] > 1.0E-5) { 
                             DetermineReactivePhaseAns = 0;
                             }
                        } else if( Concentration[HCO] > 1.0E-7) { 
                        DetermineReactivePhaseAns = 5;
                        }
                   }
              } else if( Concentration[C3H6] > 1.0E-7) { 
              DetermineReactivePhaseAns = 1;
              }
         }
   ConditionChoiceAsInteger ans = new ConditionChoiceAsInteger(DetermineReactivePhaseAns);
   return ans;

    /*
      ConditionParametersAsDoubles parametersD = (ConditionParametersAsDoubles) parameters;
      double[] Concentration = parametersD.Parameters;
     int DetermineReactivePhaseAns = 0;
   
         if( Concentration[39] < 1.0E-8) { 
              if( Concentration[57] < 1.0E-8) { 
                   if( Concentration[18] < 1.0E-8) { 
                        if( Concentration[88] < 1.0E-8) { 
                        DetermineReactivePhaseAns = 0;
                        } else if( Concentration[88] > 1.0E-8) { 
                             if( Concentration[16] < 1.0E-8) { 
                                  if( Concentration[86] < 1.0E-8) { 
                                       if( Concentration[11] < 1.0E-8) { 
                                       DetermineReactivePhaseAns = 1;
                                       } else if( Concentration[11] > 1.0E-8) { 
                                            if( Concentration[15] < 1.0E-8) { 
                                            DetermineReactivePhaseAns = 1;
                                            } else if( Concentration[15] > 1.0E-8) { 
                                            DetermineReactivePhaseAns = 1;
                                            }
                                       }
                                  } else if( Concentration[86] > 1.0E-8) { 
                                  DetermineReactivePhaseAns = 2;
                                  }
                             } else if( Concentration[16] > 1.0E-8) { 
                             DetermineReactivePhaseAns = 3;
                             }
                        }
                   } else if( Concentration[18] > 1.0E-8) { 
                        if( Concentration[77] < 1.0E-8) { 
                        DetermineReactivePhaseAns = 2;
                        } else if( Concentration[77] > 1.0E-8) { 
                        DetermineReactivePhaseAns = 3;
                        }
                   }
              } else if( Concentration[57] > 1.0E-8) { 
              DetermineReactivePhaseAns = 3;
              }
         } else if( Concentration[39] > 1.0E-8) { 
         DetermineReactivePhaseAns = 4;
         }
   ConditionChoiceAsInteger ans = new ConditionChoiceAsInteger(DetermineReactivePhaseAns);
   return ans;
    */
   }
}

