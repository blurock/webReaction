//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.15 at 02:16:50 PM CEST 
//


package info.esblurock.cml.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for unitListTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="unitListTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="unit"/>
 *     &lt;enumeration value="unitType"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "unitListTypeType")
@XmlEnum
public enum UnitListTypeType {


    /**
     * 
     * 	            
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;h:div xmlns:h="http://www.w3.org/1999/xhtml" xmlns="http://www.xml-cml.org/schema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" class="description"&gt;child elements are unit&lt;/h:div&gt;
     * </pre>
     * 
     * 	        
     * 
     */
    @XmlEnumValue("unit")
    UNIT("unit"),

    /**
     * 
     * 	            
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;h:div xmlns:h="http://www.w3.org/1999/xhtml" xmlns="http://www.xml-cml.org/schema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" class="description"&gt;child elements are unitType&lt;/h:div&gt;
     * </pre>
     * 
     * 	        
     * 
     */
    @XmlEnumValue("unitType")
    UNIT_TYPE("unitType");
    private final java.lang.String value;

    UnitListTypeType(java.lang.String v) {
        value = v;
    }

    public java.lang.String value() {
        return value;
    }

    public static UnitListTypeType fromValue(java.lang.String v) {
        for (UnitListTypeType c: UnitListTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}