/*
 * CMLMolecule.java
 *
 * Created on January 30, 2004, 3:57 PM
 */
package blurock.reaction.data.molecules.CML;

import blurock.reaction.data.common.IParsableElement;
import blurock.reaction.data.common.IRestorableElement;
import blurock.reaction.data.common.Parser;
import info.esblurock.cml.generated.Atom;
import info.esblurock.cml.generated.AtomArray;
import info.esblurock.cml.generated.Bond;
import info.esblurock.cml.generated.BondArray;
import info.esblurock.cml.generated.Cml;
import info.esblurock.cml.generated.Molecule;
import info.esblurock.cml.generated.Name;
import info.esblurock.cml.generated.ObjectFactory;
import info.esblurock.reaction.data.gwt.client.data.ReactionLog;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactAtom;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactBond;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactMolecule;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author  moliate
 */
@SuppressWarnings("serial")
public class CMLMolecule extends ReactMolecule implements IRestorableElement {

    public int reaction_id = Integer.MIN_VALUE;
    public boolean is_reactant;

    public void parse(byte[] data) throws java.text.ParseException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        try {
            JAXBContext jc = JAXBContext.newInstance("blurock.reaction.generated.core");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            //unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            Cml cml = (Cml) unmarshaller.unmarshal(bis);
            @SuppressWarnings("rawtypes")
			List list = cml.getAny();
            Molecule molecule = (Molecule) list.get(0);
            
            fromCML(molecule);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public byte[] restore() {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            Molecule molecule = toCML();
            JAXBContext jc = JAXBContext.newInstance(molecule.getClass().getPackage().getName());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(molecule, new PrintStream(bos));
        } catch (Exception e) {
            return e.toString().getBytes();
        }

        return bos.toString().getBytes();
    }

    public Molecule toCML() {
        try {
            ObjectFactory factory = new ObjectFactory();

            Molecule molecule = factory.createMolecule();
            molecule.setId("m" + ID + ((Integer.MIN_VALUE == reaction_id) ? "" : ("_" + (is_reactant ? 'r' : 'p') + reaction_id)));
            //Name
            Name name = factory.createName();
            name.setConvention("IUPAC");
            name.setValue(MoleculeName);
            molecule.getAngleOrArgOrArray().add(name);
            molecule.setTitle(name.getValue());
            //Atoms
            AtomArray aa = factory.createAtomArray();
            int count = 0;
            for (ReactAtom a : Atoms) {
                CMLAtom atom = new CMLAtom();
                atom.setData(a);
                atom.ID = "" + count;
                aa.getAtom().add(count, (Atom) atom.toCML());
                count++;
            }
            molecule.getAngleOrArgOrArray().add(aa);
            //Bonds
            BondArray ba = factory.createBondArray();
            for (ReactBond b : Bonds) {
                CMLBond bond = new CMLBond();
                bond.setData(b);
                ba.getBond().add((Bond) bond.toCML());
            }
            molecule.getAngleOrArgOrArray().add(ba);
            return molecule;
        } catch (Exception e) {
            return null;
        }
    }

    public void fromCML(Molecule molecule) {
        Parser parser = new Parser(molecule.getId());
        ID = parser.nextInt();
        reaction_id = parser.nextInt();

        java.util.List<Object> lst = molecule.getAngleOrArgOrArray();

        for (int i = 0; i < lst.size(); i++) {
            Object obj = lst.get(i);
            if (obj instanceof Name) {
                Name name = (Name) obj;
                //String id = name.getId();
                //String nnn = name.getValue();
                MoleculeName = name.getValue();

            } else if (obj instanceof AtomArray) {
                AtomArray aa = (AtomArray) obj;
                @SuppressWarnings("rawtypes")
				java.util.List la = aa.getAtom();
                for (int iaa = 0; iaa < la.size(); iaa++) {
                    //Atom atm = (Atom) aa.getAtom().get(iaa);
                    CMLAtom atom = new CMLAtom();
                    atom.fromCML((Atom) la.get(iaa));
                    Atoms.add(atom);
                }
            } else if (obj instanceof BondArray) {
                BondArray ba = (BondArray) obj;
                @SuppressWarnings("rawtypes")
				java.util.List lb = ba.getBond();
                for (int iba = 0; iba < lb.size(); iba++) {
                    //Bond bnd = (Bond) ba.getBond().get(iba);
                    CMLBond bond = new CMLBond();
                    bond.fromCML((Bond) lb.get(iba));
                    Bonds.add(bond);
                }
            }
        }
    }

	@Override
    public void setData(IParsableElement element) 
    {
	if (! (element instanceof ReactMolecule) )
        {   
            ReactionLog.logError(" > Tried to parse an element of wrong type: " + element.getClass().getName() + " where " + this.getClass().getName()+ " was expected.");
            return;
        }
        
        ReactMolecule e = (ReactMolecule)element;
        
        MoleculeName = e.getMoleculeName();
    	ID = e.getID();
    	NumberOfBonds = e.nbrOfBonds();
    	NumberOfAtoms = e.nbrOfAtoms();
        Atoms = new ArrayList<ReactAtom>();
        Iterator<ReactAtom> iteratm =  e.getAtoms().iterator();
        while(iteratm.hasNext()) {
            ReactAtom atm = iteratm.next();
            Atoms.add(atm);
        }
        Bonds = new ArrayList<ReactBond>();
        Iterator<ReactBond> iterbnd =  e.getBonds().iterator();
        while(iterbnd.hasNext()){
            ReactBond bnd = iterbnd.next();
            Bonds.add(bnd);
        }
    }
 }
