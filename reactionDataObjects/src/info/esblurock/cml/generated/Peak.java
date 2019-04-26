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
 *       &lt;sequence>
 *         &lt;element ref="{http://www.xml-cml.org/schema}metadataList" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{http://www.xml-cml.org/schema}atom"/>
 *           &lt;element ref="{http://www.xml-cml.org/schema}bond"/>
 *           &lt;element ref="{http://www.xml-cml.org/schema}molecule"/>
 *           &lt;element ref="{http://www.xml-cml.org/schema}peakStructure"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}peakMultiplicity"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}peakUnits"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}moleculeRefs"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}yMin"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}bondRefs"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}dictRef"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}peakShape"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}ref"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}xMin"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}convention"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}integral"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}yWidth"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}atomRefs"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}peakHeight"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}yValue"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}title"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}xWidth"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}xUnits"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}yUnits"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}xValue"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}id"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}yMax"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}xMax"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "metadataList",
    "atomOrBondOrMolecule"
})
@XmlRootElement(name = "peak")
public class Peak {

    protected MetadataList metadataList;
    @XmlElements({
        @XmlElement(name = "atom", type = Atom.class),
        @XmlElement(name = "bond", type = Bond.class),
        @XmlElement(name = "molecule", type = Molecule.class),
        @XmlElement(name = "peakStructure", type = PeakStructure.class)
    })
    protected List<java.lang.Object> atomOrBondOrMolecule;
    @XmlAttribute(name = "peakMultiplicity")
    protected java.lang.String peakMultiplicity;
    @XmlAttribute(name = "peakUnits")
    protected java.lang.String peakUnits;
    @XmlAttribute(name = "moleculeRefs")
    protected List<java.lang.String> moleculeRefs;
    @XmlAttribute(name = "yMin")
    protected Double yMin;
    @XmlAttribute(name = "bondRefs")
    protected List<java.lang.String> bondRefs;
    @XmlAttribute(name = "dictRef")
    protected java.lang.String dictRef;
    @XmlAttribute(name = "peakShape")
    protected java.lang.String peakShape;
    @XmlAttribute(name = "ref")
    protected java.lang.String ref;
    @XmlAttribute(name = "xMin")
    protected Double xMin;
    @XmlAttribute(name = "convention")
    protected java.lang.String convention;
    @XmlAttribute(name = "integral")
    protected java.lang.String integral;
    @XmlAttribute(name = "yWidth")
    protected Double yWidth;
    @XmlAttribute(name = "atomRefs")
    protected List<java.lang.String> atomRefs;
    @XmlAttribute(name = "peakHeight")
    protected Double peakHeight;
    @XmlAttribute(name = "yValue")
    protected Double yValue;
    @XmlAttribute(name = "title")
    protected java.lang.String title;
    @XmlAttribute(name = "xWidth")
    protected Double xWidth;
    @XmlAttribute(name = "xUnits")
    protected java.lang.String xUnits;
    @XmlAttribute(name = "yUnits")
    protected java.lang.String yUnits;
    @XmlAttribute(name = "xValue")
    protected Double xValue;
    @XmlAttribute(name = "id")
    protected java.lang.String id;
    @XmlAttribute(name = "yMax")
    protected Double yMax;
    @XmlAttribute(name = "xMax")
    protected Double xMax;

    /**
     * 
     *                         
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;h:div xmlns:h="http://www.w3.org/1999/xhtml" xmlns="http://www.xml-cml.org/schema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" class="summary"&gt;Allows &lt;h:i&gt;inter alia&lt;/h:i&gt; the provenance of the peak assignment to be recorde.&lt;/h:div&gt;
     * </pre>
     * 
     *                     
     * 
     * @return
     *     possible object is
     *     {@link MetadataList }
     *     
     */
    public MetadataList getMetadataList() {
        return metadataList;
    }

    /**
     * Sets the value of the metadataList property.
     * 
     * @param value
     *     allowed object is
     *     {@link MetadataList }
     *     
     */
    public void setMetadataList(MetadataList value) {
        this.metadataList = value;
    }

    /**
     * Gets the value of the atomOrBondOrMolecule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the atomOrBondOrMolecule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAtomOrBondOrMolecule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Atom }
     * {@link Bond }
     * {@link Molecule }
     * {@link PeakStructure }
     * 
     * 
     */
    public List<java.lang.Object> getAtomOrBondOrMolecule() {
        if (atomOrBondOrMolecule == null) {
            atomOrBondOrMolecule = new ArrayList<java.lang.Object>();
        }
        return this.atomOrBondOrMolecule;
    }

    /**
     * Gets the value of the peakMultiplicity property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getPeakMultiplicity() {
        return peakMultiplicity;
    }

    /**
     * Sets the value of the peakMultiplicity property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setPeakMultiplicity(java.lang.String value) {
        this.peakMultiplicity = value;
    }

    /**
     * Gets the value of the peakUnits property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getPeakUnits() {
        return peakUnits;
    }

    /**
     * Sets the value of the peakUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setPeakUnits(java.lang.String value) {
        this.peakUnits = value;
    }

    /**
     * Gets the value of the moleculeRefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the moleculeRefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMoleculeRefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link java.lang.String }
     * 
     * 
     */
    public List<java.lang.String> getMoleculeRefs() {
        if (moleculeRefs == null) {
            moleculeRefs = new ArrayList<java.lang.String>();
        }
        return this.moleculeRefs;
    }

    /**
     * Gets the value of the yMin property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getYMin() {
        return yMin;
    }

    /**
     * Sets the value of the yMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setYMin(Double value) {
        this.yMin = value;
    }

    /**
     * Gets the value of the bondRefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bondRefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBondRefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link java.lang.String }
     * 
     * 
     */
    public List<java.lang.String> getBondRefs() {
        if (bondRefs == null) {
            bondRefs = new ArrayList<java.lang.String>();
        }
        return this.bondRefs;
    }

    /**
     * Gets the value of the dictRef property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getDictRef() {
        return dictRef;
    }

    /**
     * Sets the value of the dictRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setDictRef(java.lang.String value) {
        this.dictRef = value;
    }

    /**
     * Gets the value of the peakShape property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getPeakShape() {
        return peakShape;
    }

    /**
     * Sets the value of the peakShape property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setPeakShape(java.lang.String value) {
        this.peakShape = value;
    }

    /**
     * Gets the value of the ref property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getRef() {
        return ref;
    }

    /**
     * Sets the value of the ref property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setRef(java.lang.String value) {
        this.ref = value;
    }

    /**
     * Gets the value of the xMin property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getXMin() {
        return xMin;
    }

    /**
     * Sets the value of the xMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setXMin(Double value) {
        this.xMin = value;
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
     * Gets the value of the integral property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getIntegral() {
        return integral;
    }

    /**
     * Sets the value of the integral property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setIntegral(java.lang.String value) {
        this.integral = value;
    }

    /**
     * Gets the value of the yWidth property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getYWidth() {
        return yWidth;
    }

    /**
     * Sets the value of the yWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setYWidth(Double value) {
        this.yWidth = value;
    }

    /**
     * Gets the value of the atomRefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the atomRefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAtomRefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link java.lang.String }
     * 
     * 
     */
    public List<java.lang.String> getAtomRefs() {
        if (atomRefs == null) {
            atomRefs = new ArrayList<java.lang.String>();
        }
        return this.atomRefs;
    }

    /**
     * Gets the value of the peakHeight property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPeakHeight() {
        return peakHeight;
    }

    /**
     * Sets the value of the peakHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPeakHeight(Double value) {
        this.peakHeight = value;
    }

    /**
     * Gets the value of the yValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getYValue() {
        return yValue;
    }

    /**
     * Sets the value of the yValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setYValue(Double value) {
        this.yValue = value;
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
     * Gets the value of the xWidth property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getXWidth() {
        return xWidth;
    }

    /**
     * Sets the value of the xWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setXWidth(Double value) {
        this.xWidth = value;
    }

    /**
     * Gets the value of the xUnits property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getXUnits() {
        return xUnits;
    }

    /**
     * Sets the value of the xUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setXUnits(java.lang.String value) {
        this.xUnits = value;
    }

    /**
     * Gets the value of the yUnits property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getYUnits() {
        return yUnits;
    }

    /**
     * Sets the value of the yUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setYUnits(java.lang.String value) {
        this.yUnits = value;
    }

    /**
     * Gets the value of the xValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getXValue() {
        return xValue;
    }

    /**
     * Sets the value of the xValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setXValue(Double value) {
        this.xValue = value;
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
     * Gets the value of the yMax property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getYMax() {
        return yMax;
    }

    /**
     * Sets the value of the yMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setYMax(Double value) {
        this.yMax = value;
    }

    /**
     * Gets the value of the xMax property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getXMax() {
        return xMax;
    }

    /**
     * Sets the value of the xMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setXMax(Double value) {
        this.xMax = value;
    }

}
