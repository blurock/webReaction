//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.15 at 02:16:50 PM CEST 
//


package info.esblurock.cml.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://www.xml-cml.org/schema}annotation"/>
 *         &lt;element ref="{http://www.xml-cml.org/schema}dimension" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.xml-cml.org/schema}description" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.xml-cml.org/schema}definition" minOccurs="0"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}parentSI"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}abbreviation"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}symbol"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}name"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}id"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}preserve"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}title"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "annotationOrDimensionOrDescription"
})
@XmlRootElement(name = "unitType")
public class UnitType {

    @XmlElements({
        @XmlElement(name = "definition", type = Definition.class),
        @XmlElement(name = "annotation", type = Annotation.class),
        @XmlElement(name = "description", type = Description.class),
        @XmlElement(name = "dimension", type = Dimension.class)
    })
    protected List<java.lang.Object> annotationOrDimensionOrDescription;
    @XmlAttribute(name = "parentSI")
    protected java.lang.String parentSI;
    @XmlAttribute(name = "abbreviation")
    protected java.lang.String abbreviation;
    @XmlAttribute(name = "symbol")
    protected java.lang.String symbol;
    @XmlAttribute(name = "name")
    protected java.lang.String name;
    @XmlAttribute(name = "id")
    protected java.lang.String id;
    @XmlAttribute(name = "preserve")
    protected Boolean preserve;
    @XmlAttribute(name = "title")
    protected java.lang.String title;

    /**
     * Gets the value of the annotationOrDimensionOrDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the annotationOrDimensionOrDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnnotationOrDimensionOrDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Definition }
     * {@link Annotation }
     * {@link Description }
     * {@link Dimension }
     * 
     * 
     */
    public List<java.lang.Object> getAnnotationOrDimensionOrDescription() {
        if (annotationOrDimensionOrDescription == null) {
            annotationOrDimensionOrDescription = new ArrayList<java.lang.Object>();
        }
        return this.annotationOrDimensionOrDescription;
    }

    /**
     * Gets the value of the parentSI property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getParentSI() {
        return parentSI;
    }

    /**
     * Sets the value of the parentSI property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setParentSI(java.lang.String value) {
        this.parentSI = value;
    }

    /**
     * Gets the value of the abbreviation property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Sets the value of the abbreviation property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setAbbreviation(java.lang.String value) {
        this.abbreviation = value;
    }

    /**
     * Gets the value of the symbol property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getSymbol() {
        return symbol;
    }

    /**
     * Sets the value of the symbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setSymbol(java.lang.String value) {
        this.symbol = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setName(java.lang.String value) {
        this.name = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setId(java.lang.String value) {
        this.id = value;
    }

    /**
     * Gets the value of the preserve property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPreserve() {
        return preserve;
    }

    /**
     * Sets the value of the preserve property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPreserve(Boolean value) {
        this.preserve = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setTitle(java.lang.String value) {
        this.title = value;
    }

}
