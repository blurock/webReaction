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
 * <p>Java class for angleUnitsType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="angleUnitsType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="degrees"/>
 *     &lt;enumeration value="radians"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "angleUnitsType")
@XmlEnum
public enum AngleUnitsType {

    @XmlEnumValue("degrees")
    DEGREES("degrees"),
    @XmlEnumValue("radians")
    RADIANS("radians");
    private final java.lang.String value;

    AngleUnitsType(java.lang.String v) {
        value = v;
    }

    public java.lang.String value() {
        return value;
    }

    public static AngleUnitsType fromValue(java.lang.String v) {
        for (AngleUnitsType c: AngleUnitsType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}