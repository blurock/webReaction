/*
 * CMLReactionConstants.java
 *
 * Created on February 4, 2004, 1:56 PM
 */

package blurock.reaction.reactions.CML;
import blurock.reaction.common.IRestorableElement;
import blurock.reaction.common.SProperties;
import blurock.reaction.reactions.*;
import info.esblurock.cml.generated.Metadata;
import info.esblurock.cml.generated.MetadataList;
import info.esblurock.cml.generated.ObjectFactory;
import info.esblurock.cml.generated.Property;
import info.esblurock.cml.generated.Scalar;
import info.esblurock.reaction.data.gwt.client.data.reactions.ReactReactionConstants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import java.io.*;
/**
 *
 * @author  moliate
 */
public class CMLReactionConstants extends ReactReactionConstants implements IRestorableElement, ICMLReactionConstants
{
  
    /** Creates a new instance of CMLAtomCorrespondance */
    public CMLReactionConstants() 
    {}
    
    public byte[] restore() 
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try
        {               
                Property constants = toCML();   
                JAXBContext jc = JAXBContext.newInstance(constants.getClass().getPackage().getName());
                Marshaller marshaller = jc.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
                marshaller.marshal(constants, new PrintStream(bos) );
        } 
        catch (Exception e) 
            { return e.toString().getBytes(); }  
        
        return bos.toString().getBytes();
    }
    
    public void parse(byte[] data) throws java.text.ParseException 
    {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        try
        {           
                JAXBContext jc = JAXBContext.newInstance(SProperties.getProperty("reaction.cml.root"));
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                //unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
                Property constants = (Property) unmarshaller.unmarshal(bis);
                fromCML(constants);
        } 
        catch (Exception e) 
            {
                return; 
            }  
    }
    
    public Property toCML()
    {
        try
        {                
	        ObjectFactory factory = new ObjectFactory();              
                Property property = factory.createProperty();
                Scalar n = factory.createScalar();
                n.setTitle(CONST_N);
                n.setValue(""+nFactor);
                n.setDataType("xsd:decimal");
                n.setUnits("units:1/K");
                property.getScalarOrArrayOrMatrix().add(n);
                
                Scalar a = factory.createScalar();
                a.setTitle(CONST_A);
                a.setValue(""+aFactor);
                a.setDataType("xsd:decimal");
                a.setUnits("units:1/s");
                property.getScalarOrArrayOrMatrix().add(a);
                
                Scalar e = factory.createScalar();
                e.setTitle(CONST_EA);
                e.setValue(""+eFactor);
                e.setDataType("xsd:decimal");
                e.setUnits("units:kJ");
                property.getScalarOrArrayOrMatrix().add(e);
                
                Scalar m = factory.createScalar();
                m.setTitle(CONST_MULTIPLICITY);
                m.setValue(""+multiplicity);
                m.setDataType("xsd:integer");
                property.getScalarOrArrayOrMatrix().add(m);
                
                property.setRole(direction?CONST_ROLE_FORWARD:CONST_ROLE_REVERSE); 
                property.setDictRef(CONST_DICTREF_COMBUSTIONCONSTANTS);
                
                if (null != reference)
                {
                	MetadataList mdl = factory.createMetadataList();
                	Metadata md = factory.createMetadata();
                	md.setName("dc:source");
                	md.setValue(reference);
                        mdl.getMetadataListOrMetadata().add(md);
                	property.getMetadataList().add(mdl);
            	}
                
                return property;
        } 
        catch (Exception e) 
            { return null; } 
    }
    
    public void fromCML(Property property)
    {
                if ( (property.getRole().equals(CONST_ROLE_FORWARD)) )
                    direction = true;
                else if ( (property.getRole().equals(CONST_ROLE_REVERSE)) )
                    direction = false;    
                else return;
                
                java.util.List values = property.getScalarOrArrayOrMatrix();
                
                for (int iii = 0; iii < values.size(); iii++)
                {
	            if ( !(values.get(iii) instanceof Scalar) )
	                	continue;
	                	
                    Scalar scalar = (Scalar)values.get(iii);
                    if (scalar.getTitle().equals(CONST_A))
                        aFactor = Double.parseDouble(scalar.getValue());
                    if (scalar.getTitle().equals(CONST_N))
                        nFactor = Double.parseDouble(scalar.getValue());
                    if (scalar.getTitle().equals(CONST_EA))
                        eFactor = Double.parseDouble(scalar.getValue());     
                    if (scalar.getTitle().equals(CONST_MULTIPLICITY))
                        multiplicity = Integer.parseInt(scalar.getValue());  
                }
                              
    }
    
}
