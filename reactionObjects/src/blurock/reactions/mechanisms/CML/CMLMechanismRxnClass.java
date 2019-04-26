/*
 * CMLMechanismRxnClass.java
 *
 * Created on February 22, 2004, 4:38 PM
 */
package blurock.reactions.mechanisms.CML;

import blurock.reaction.common.IRestorableElement;
import blurock.reaction.common.SProperties;
import blurock.reaction.reactions.CML.CMLReactionConstants;
import blurock.reaction.reactions.CML.ICMLReactionConstants;
import info.esblurock.cml.generated.MechanismComponent;
import info.esblurock.cml.generated.Name;
import info.esblurock.cml.generated.ObjectFactory;
import info.esblurock.cml.generated.Property;
import info.esblurock.cml.generated.PropertyList;
import info.esblurock.cml.generated.Reaction;
import info.esblurock.cml.generated.ReactionList;
import info.esblurock.reaction.data.gwt.client.data.mechanisms.ReactMechanismRxn;
import info.esblurock.reaction.data.gwt.client.data.mechanisms.ReactMechanismRxnClass;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactMolecule;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;

/**
 *
 * @author  moliate
 */
@SuppressWarnings("serial")
public class CMLMechanismRxnClass extends ReactMechanismRxnClass implements IRestorableElement, ICMLReactionConstants {

    protected HashMap<String,ReactMolecule> molecules;

    /** Creates a new instance of CMLMechanismRxnClass */
    public CMLMechanismRxnClass(HashMap<String,ReactMolecule> molecules) {
        this.molecules = molecules;
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
           ObjectFactory factory = new ObjectFactory();
            MechanismComponent mechanismComponent = factory.createMechanismComponent();


            Name name = factory.createName();
            name.setConvention("IUPAC");
            name.setValue(className);
            mechanismComponent.getAny().add(name);



            PropertyList info = factory.createPropertyList();
            info.setTitle("Reaction constants");
            //PropertyList constants = factory.createPropertyList(); 
            CMLReactionConstants c = new CMLReactionConstants();
            c.setData(forwardConstants);
            Property con = c.toCML();
            info.getPropertyOrPropertyListOrObservation().add(con);
            c.setData(reverseConstants);
            Property con1 = c.toCML();
            info.getPropertyOrPropertyListOrObservation().add(con1);
            mechanismComponent.getAny().add(info);

            ReactionList reactionList = factory.createReactionList();
            for (int i = 0; i < reactions.size(); i++) {
                ReactMechanismRxn rxn = (ReactMechanismRxn) reactions.get(i);
                CMLMechanismRxn cmlRxn = new CMLMechanismRxn(molecules);
                cmlRxn.setData(rxn);
                Reaction reaction = cmlRxn.toCML();
                if (null != reaction) {
                    reactionList.getReaction().add(reaction);
                }
            }
            mechanismComponent.getAny().add(reactionList);

            return mechanismComponent;
    }
    public void fromCML(MechanismComponent mechanismComponent) {
        ReactionList reactionList = null;
        System.out.println("CMLMechanismRxnClass.fromCML " + mechanismComponent.getAny().size());
        for (int i = 0; i < mechanismComponent.getAny().size(); i++) {
            Object o = mechanismComponent.getAny().get(i);
            if (o instanceof ReactionList) {
                reactionList = (ReactionList) o;
                @SuppressWarnings("rawtypes")
				java.util.List Reactions = reactionList.getReaction();
                reactions = new ArrayList<ReactMechanismRxn>();
                for (int j = 0; j < Reactions.size(); j++) {
                    Reaction reaction = (Reaction) Reactions.get(j);
                    CMLMechanismRxn rxn = new CMLMechanismRxn(molecules);
                    rxn.fromCML(reaction);
                    reactions.add(rxn);
                }
            } else if (o instanceof PropertyList) {
                PropertyList pl = (PropertyList) o;
                @SuppressWarnings("rawtypes")
				java.util.List PoO = pl.getPropertyOrPropertyListOrObservation();
                for (int iii = 0; iii < PoO.size(); iii++) {
                    if (PoO.get(iii) instanceof Property) {
                        Property el = (Property) PoO.get(iii);
                        if (el.getDictRef().equals(CONST_DICTREF_COMBUSTIONCONSTANTS) &&
                                el.getRole().equals(CONST_ROLE_FORWARD)) {
                            CMLReactionConstants c = new CMLReactionConstants();
                            c.fromCML(el);
                            forwardConstants = c;
                        } else if (el.getDictRef().equals(CONST_DICTREF_COMBUSTIONCONSTANTS) &&
                                el.getRole().equals(CONST_ROLE_REVERSE)) {
                            CMLReactionConstants c = new CMLReactionConstants();
                            c.fromCML(el);
                            reverseConstants = c;
                        }
                    }
                }
            } else if (o instanceof Name) {
                Name name = (Name) o;
                className = name.getValue();

            }
        }
    }
}
