/*
 * CMLMechanismGeneration.java
 *
 * Created on February 26, 2004, 2:54 PM
 */
package blurock.reactions.mechanisms.CML;

import blurock.reaction.common.IRestorableElement;
import blurock.reaction.common.SProperties;
import blurock.reactions.mechanisms.*;
import info.esblurock.cml.generated.MechanismComponent;
import info.esblurock.cml.generated.Molecule;
import info.esblurock.cml.generated.MoleculeList;
import info.esblurock.cml.generated.ObjectFactory;
import info.esblurock.reaction.data.gwt.client.data.ReactionLog;
import info.esblurock.reaction.data.gwt.client.data.mechanisms.ReactMechanismGeneration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import java.io.*;

/**
 *
 * @author  moliate
 */
public class CMLMechanismGeneration extends ReactMechanismGeneration implements IRestorableElement {

    /** Creates a new instance of CMLMechanismGeneration */
    public CMLMechanismGeneration() {
    }

    public void parse(byte[] data) throws java.text.ParseException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        try {
            JAXBContext jc = JAXBContext.newInstance(SProperties.getProperty("reaction.cml.root"));
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            //unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            MechanismComponent reaction = (MechanismComponent) unmarshaller.unmarshal(bis);
            fromCML(reaction);

        } catch (Exception e) {
            ReactionLog.logError(e.toString());
            return;
        }
    }

    public byte[] restore() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            MechanismComponent reaction = toCML();
            JAXBContext jc = JAXBContext.newInstance(reaction.getClass().getPackage().getName());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(reaction, new PrintStream(bos));
        } catch (Exception e) {
            return e.toString().getBytes();
        }

        return bos.toString().getBytes();
    }

    public MechanismComponent toCML() {
        try {
            ObjectFactory factory = new ObjectFactory();
            MechanismComponent me = factory.createMechanismComponent();
            me.setTitle(mechanismName);
            MoleculeList mlist = new MoleculeList();
            for (int i = 0; i < initialMolecules.length; i++) {
                Molecule mol = factory.createMolecule();
                mol.setTitle(initialMolecules[i]);
                mol.setId("m" + i);
                mlist.getMolecule().add(mol);
            }
            me.getAny().add(mlist);

            CMLMechanismGenerationStep step = new CMLMechanismGenerationStep();
            for (int i = 0; i < Steps.length; i++) {
                step.setData(Steps[i]);
                MechanismComponent reaction = step.toCML();
                me.getAny().add(reaction);
            }

            return me;
        } catch (Exception e) {
            ReactionLog.logError(e.toString());
            return null;
        }


    }

    public void fromCML(MechanismComponent el) {
        mechanismName = el.getTitle();


        for (int i = 0; i < el.getAny().size(); i++) {
            Object o = el.getAny().get(i);
            if (o instanceof MoleculeList) {
                MoleculeList mlist = (MoleculeList) o;
                int n = mlist.getMolecule().size();
                initialMolecules = new String[n];
                for (int im = 0; im < n; im++) {
                    Molecule mol = (Molecule) mlist.getMolecule().get(im);
                    initialMolecules[im] = mol.getTitle();
                }
            }
            if (o instanceof MechanismComponent) {
                MechanismComponent c = (MechanismComponent) o;
                int n = c.getAny().size();
                Steps = new CMLMechanismGenerationStep[n];
                for (int in = 0; in < n; in++) {
                    MechanismComponent mc = (MechanismComponent) c.getAny().get(in);
                    ((CMLMechanismGenerationStep) Steps[i]).fromCML(mc);
                }
            }
        }
    }
}
