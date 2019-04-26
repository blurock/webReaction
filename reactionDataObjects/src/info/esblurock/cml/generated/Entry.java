//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.15 at 02:16:50 PM CEST 
//


package info.esblurock.cml.generated;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{http://www.xml-cml.org/schema}metadataList"/>
 *           &lt;element ref="{http://www.xml-cml.org/schema}alternative"/>
 *           &lt;element ref="{http://www.xml-cml.org/schema}annotation"/>
 *           &lt;element ref="{http://www.xml-cml.org/schema}definition"/>
 *           &lt;element ref="{http://www.xml-cml.org/schema}description"/>
 *           &lt;element ref="{http://www.xml-cml.org/schema}enumeration"/>
 *           &lt;element ref="{http://www.xml-cml.org/schema}relatedEntry"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}whiteSpace"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}unitType"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}maxExclusive"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}convention"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}columns"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}units"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}minInclusive"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}fractionDigits"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}id"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}maxInclusive"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}pattern"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}title"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}maxLength"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}minExclusive"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}rows"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}length"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}dataType"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}totalDigits"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}minLength"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}term"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "metadataListOrAlternativeOrAnnotation"
})
@XmlRootElement(name = "entry")
public class Entry {

    @XmlElements({
        @XmlElement(name = "description", type = Description.class),
        @XmlElement(name = "enumeration", type = Enumeration.class),
        @XmlElement(name = "relatedEntry", type = RelatedEntry.class),
        @XmlElement(name = "definition", type = Definition.class),
        @XmlElement(name = "annotation", type = Annotation.class),
        @XmlElement(name = "metadataList", type = MetadataList.class),
        @XmlElement(name = "alternative", type = Alternative.class)
    })
    protected List<java.lang.Object> metadataListOrAlternativeOrAnnotation;
    @XmlAttribute(name = "whiteSpace")
    protected java.lang.String whiteSpace;
    @XmlAttribute(name = "unitType")
    protected java.lang.String unitType;
    @XmlAttribute(name = "maxExclusive")
    protected Double maxExclusive;
    @XmlAttribute(name = "convention")
    protected java.lang.String convention;
    @XmlAttribute(name = "columns")
    protected BigInteger columns;
    @XmlAttribute(name = "units")
    protected java.lang.String units;
    @XmlAttribute(name = "minInclusive")
    protected Double minInclusive;
    @XmlAttribute(name = "fractionDigits")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger fractionDigits;
    @XmlAttribute(name = "id")
    protected java.lang.String id;
    @XmlAttribute(name = "maxInclusive")
    protected Double maxInclusive;
    @XmlAttribute(name = "pattern")
    protected java.lang.String pattern;
    @XmlAttribute(name = "title")
    protected java.lang.String title;
    @XmlAttribute(name = "maxLength")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maxLength;
    @XmlAttribute(name = "minExclusive")
    protected Double minExclusive;
    @XmlAttribute(name = "rows")
    protected BigInteger rows;
    @XmlAttribute(name = "length")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger length;
    @XmlAttribute(name = "dataType")
    protected java.lang.String dataType;
    @XmlAttribute(name = "totalDigits")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger totalDigits;
    @XmlAttribute(name = "minLength")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minLength;
    @XmlAttribute(name = "term", required = true)
    protected java.lang.String term;

    /**
     * Gets the value of the metadataListOrAlternativeOrAnnotation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadataListOrAlternativeOrAnnotation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadataListOrAlternativeOrAnnotation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * {@link Enumeration }
     * {@link RelatedEntry }
     * {@link Definition }
     * {@link Annotation }
     * {@link MetadataList }
     * {@link Alternative }
     * 
     * 
     */
    public List<java.lang.Object> getMetadataListOrAlternativeOrAnnotation() {
        if (metadataListOrAlternativeOrAnnotation == null) {
            metadataListOrAlternativeOrAnnotation = new ArrayList<java.lang.Object>();
        }
        return this.metadataListOrAlternativeOrAnnotation;
    }

    /**
     * Gets the value of the whiteSpace property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getWhiteSpace() {
        return whiteSpace;
    }

    /**
     * Sets the value of the whiteSpace property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setWhiteSpace(java.lang.String value) {
        this.whiteSpace = value;
    }

    /**
     * Gets the value of the unitType property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getUnitType() {
        return unitType;
    }

    /**
     * Sets the value of the unitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setUnitType(java.lang.String value) {
        this.unitType = value;
    }

    /**
     * Gets the value of the maxExclusive property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxExclusive() {
        return maxExclusive;
    }

    /**
     * Sets the value of the maxExclusive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxExclusive(Double value) {
        this.maxExclusive = value;
    }

    /**
     * Gets the value of the convention property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getConvention() {
        return convention;
    }

    /**
     * Sets the value of the convention property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setConvention(java.lang.String value) {
        this.convention = value;
    }

    /**
     * Gets the value of the columns property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getColumns() {
        return columns;
    }

    /**
     * Sets the value of the columns property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setColumns(BigInteger value) {
        this.columns = value;
    }

    /**
     * Gets the value of the units property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getUnits() {
        return units;
    }

    /**
     * Sets the value of the units property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setUnits(java.lang.String value) {
        this.units = value;
    }

    /**
     * Gets the value of the minInclusive property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinInclusive() {
        return minInclusive;
    }

    /**
     * Sets the value of the minInclusive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinInclusive(Double value) {
        this.minInclusive = value;
    }

    /**
     * Gets the value of the fractionDigits property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFractionDigits() {
        return fractionDigits;
    }

    /**
     * Sets the value of the fractionDigits property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFractionDigits(BigInteger value) {
        this.fractionDigits = value;
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
     * Gets the value of the maxInclusive property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxInclusive() {
        return maxInclusive;
    }

    /**
     * Sets the value of the maxInclusive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxInclusive(Double value) {
        this.maxInclusive = value;
    }

    /**
     * Gets the value of the pattern property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getPattern() {
        return pattern;
    }

    /**
     * Sets the value of the pattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setPattern(java.lang.String value) {
        this.pattern = value;
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

    /**
     * Gets the value of the maxLength property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxLength() {
        return maxLength;
    }

    /**
     * Sets the value of the maxLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxLength(BigInteger value) {
        this.maxLength = value;
    }

    /**
     * Gets the value of the minExclusive property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinExclusive() {
        return minExclusive;
    }

    /**
     * Sets the value of the minExclusive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinExclusive(Double value) {
        this.minExclusive = value;
    }

    /**
     * Gets the value of the rows property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRows() {
        return rows;
    }

    /**
     * Sets the value of the rows property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRows(BigInteger value) {
        this.rows = value;
    }

    /**
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLength(BigInteger value) {
        this.length = value;
    }

    /**
     * Gets the value of the dataType property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getDataType() {
        return dataType;
    }

    /**
     * Sets the value of the dataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setDataType(java.lang.String value) {
        this.dataType = value;
    }

    /**
     * Gets the value of the totalDigits property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalDigits() {
        return totalDigits;
    }

    /**
     * Sets the value of the totalDigits property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalDigits(BigInteger value) {
        this.totalDigits = value;
    }

    /**
     * Gets the value of the minLength property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinLength() {
        return minLength;
    }

    /**
     * Sets the value of the minLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinLength(BigInteger value) {
        this.minLength = value;
    }

    /**
     * Gets the value of the term property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getTerm() {
        return term;
    }

    /**
     * Sets the value of the term property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setTerm(java.lang.String value) {
        this.term = value;
    }

}
