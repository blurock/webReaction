/*
 * CMLMechanism.java
 *
 * Created on February 22, 2004, 5:40 PM
 */

package blurock.reactions.mechanisms.CML;
import blurock.reaction.common.IRestorableElement;
import blurock.reaction.common.SProperties;
import blurock.reaction.data.molecules.CML.CMLMolecule;
import blurock.reaction.reactions.CML.ICMLReactionConstants;
import info.esblurock.cml.generated.MechanismComponent;
import info.esblurock.cml.generated.Molecule;
import info.esblurock.cml.generated.MoleculeList;
import info.esblurock.reaction.data.gwt.client.data.ReactionLog;
import info.esblurock.reaction.data.gwt.client.data.mechanisms.ReactMechanismRxnClass;
import info.esblurock.reaction.data.gwt.client.data.mechanisms.ReactionMechanism;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactMolecule;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import java.util.*;
import java.io.*;
/**
 *
 * @author  moliate
 */
@SuppressWarnings("serial")
public class CMLMechanism extends ReactionMechanism implements IRestorableElement, ICMLReactionConstants {    
    /** Creates a new instance of CMLMechanism */
    public CMLMechanism() {
    }
    
    public void parse(byte[] data) throws java.text.ParseException 
    {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        try
        {           
                JAXBContext jc = JAXBContext.newInstance(SProperties.getProperty("reaction.cml.root"));
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                //unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
                MechanismComponent mechanism = (MechanismComponent) unmarshaller.unmarshal(bis);
                fromCML(mechanism);

        } 
        catch (Exception e) 
        { 
                ReactionLog.logError("Could not parse mechanism. CML may be corrupted."); 
                return; 
        }  
    }
    
    public byte[] restore() 
    {       
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try
        {               
                MechanismComponent mechanism = toCML();   
                JAXBContext jc = JAXBContext.newInstance(mechanism.getClass().getPackage().getName());
                Marshaller marshaller = jc.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
                marshaller.marshal(mechanism, new PrintStream(bos) );
        } 
        catch (Exception e) 
        {                 
                ReactionLog.logError("Could not create cml from mechanism."); 
                return e.toString().getBytes(); 
        }  
        
        return bos.toString().getBytes();
    }
    
    public MechanismComponent toCML() {
        try {               
                //ObjectFactory factory = new ObjectFactory();
                MechanismComponent comp = new MechanismComponent();
                ReactionLog.logInfo("Mechanism toCML: number of molecules" + Molecules.size());
                Set<String> molnames = Molecules.keySet();
                MoleculeList mollist = new MoleculeList();
                for(String name : molnames) {
                    ReactMolecule molecule = Molecules.get(name);
                    CMLMolecule cmlMol = new CMLMolecule();
                    cmlMol.setData(molecule); 
                    ReactionLog.logInfo("Mechanism Molecule: " + molecule.getMoleculeName());
                    Molecule m = cmlMol.toCML();
                    if (seedMolecule.equals(cmlMol.getMoleculeName()))
                        m.setRole("mechanismSeed");
                    
                    if (null != m)
                        mollist.getMolecule().add(m);
                }
                comp.getAny().add(mollist);
                
                ReactionLog.logInfo("Mechanism toCML: Number of reactions" + this.rxnClasses.size());
                MechanismComponent rxnlist = new MechanismComponent();
                for(ReactMechanismRxnClass rxnClass : rxnClasses) {
                    CMLMechanismRxnClass cmlRxnClass = new CMLMechanismRxnClass(Molecules); 
                    cmlRxnClass.setData(rxnClass);
                    MechanismComponent mce = cmlRxnClass.toCML();
                    rxnlist.getAny().add(mce);
                }
                comp.getAny().add(rxnlist);
                return comp; 
        }  
        catch (Exception e) 
            { ReactionLog.logError(e.toString()); 
              return null; } 
    } 
    
    public void fromCML(MechanismComponent mechanism)
    {
        try {
            
            for (int i = 0; i < mechanism.getAny().size(); i++) {
                Object o = mechanism.getAny().get(i);
                if(o instanceof MoleculeList) {
                    MoleculeList mlist = (MoleculeList) o;
                    for(int im=0;im<mlist.getMolecule().size();im++) {
                        Molecule mol = mlist.getMolecule().get(im);
                        CMLMolecule cmlMol = new CMLMolecule(); 
                        cmlMol.fromCML(mol);
                        Molecules.put(cmlMol.getMoleculeName(), cmlMol); 
                        String role = mol.getRole();
                        ReactionLog.logError( ((null==role)?"molecule":"seed molecule") + " = " + cmlMol.getMoleculeName());
                        if (null != role && role.equals("mechanismSeed"))
                            seedMolecule = cmlMol.getMoleculeName();
                    }
                } if(o instanceof MechanismComponent) {
                    MechanismComponent mcomp = (MechanismComponent) o;
                    for(int ic=0; ic< mcomp.getAny().size(); ic++) {
                        MechanismComponent mce = (MechanismComponent) mcomp.getAny().get(i);
                        CMLMechanismRxnClass cmlRxnClass = new CMLMechanismRxnClass(Molecules); 
                        cmlRxnClass.fromCML(mce);
                        rxnClasses.add(cmlRxnClass);
                    }
                }
            }
       } catch (Exception e) { return; } 
    }

    
}
