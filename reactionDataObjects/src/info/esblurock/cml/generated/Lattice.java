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
 *         &lt;choice>
 *           &lt;element ref="{http://www.xml-cml.org/schema}scalar" maxOccurs="6" minOccurs="3"/>
 *           &lt;element ref="{http://www.xml-cml.org/schema}latticeVector" maxOccurs="3"/>
 *           &lt;element ref="{http://www.xml-cml.org/schema}matrix"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.xml-cml.org/schema}symmetry" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}dictRef"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}convention"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}title"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}spaceType"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}latticeType"/>
 *       &lt;attGroup ref="{http://www.xml-cml.org/schema}id"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "scalar",
    "latticeVector",
    "matrix",
    "symmetry"
})
@XmlRootElement(name = "lattice")
public class Lattice {

    protected List<Scalar> scalar;
    protected List<LatticeVector> latticeVector;
    protected Matrix matrix;
    protected Symmetry symmetry;
    @XmlAttribute(name = "dictRef")
    protected java.lang.String dictRef;
    @XmlAttribute(name = "convention")
    protected java.lang.String convention;
    @XmlAttribute(name = "title")
    protected java.lang.String title;
    @XmlAttribute(name = "spaceType")
    protected java.lang.String spaceType;
    @XmlAttribute(name = "latticeType")
    protected java.lang.String latticeType;
    @XmlAttribute(name = "id")
    protected java.lang.String id;

    /**
     * 
     *                             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;h:div xmlns:h="http://www.w3.org/1999/xhtml" xmlns="http://www.xml-cml.org/schema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" class="summary"&gt;All appropriate cell parameters must be given, even 
     *                             where angles are fixed by symmetry. The order is fixed as a,b,c,alpha,beta,gamma 
     *                             and software can neglect any title or dictRef attributes. Error estimates 
     *                             can be given if required. Any units can be used, but the defaults are 
     *                             Angstrom (10^-10 m) and degrees. To be developed for lower dimensionality.&lt;/h:div&gt;
     * </pre>
     * 
     *                         Gets the value of the scalar property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scalar property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScalar().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Scalar }
     * 
     * 
     */
    public List<Scalar> getScalar() {
        if (scalar == null) {
            scalar = new ArrayList<Scalar>();
        }
        return this.scalar;
    }

    /**
     * Gets the value of the latticeVector property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the latticeVector property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLatticeVector().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LatticeVector }
     * 
     * 
     */
    public List<LatticeVector> getLatticeVector() {
        if (latticeVector == null) {
            latticeVector = new ArrayList<LatticeVector>();
        }
        return this.latticeVector;
    }

    /**
     * Gets the value of the matrix property.
     * 
     * @return
     *     possible object is
     *     {@link Matrix }
     *     
     */
    public Matrix getMatrix() {
        return matrix;
    }

    /**
     * Sets the value of the matrix property.
     * 
     * @param value
     *     allowed object is
     *     {@link Matrix }
     *     
     */
    public void setMatrix(Matrix value) {
        this.matrix = value;
    }

    /**
     * Gets the value of the symmetry property.
     * 
     * @return
     *     possible object is
     *     {@link Symmetry }
     *     
     */
    public Symmetry getSymmetry() {
        return symmetry;
    }

    /**
     * Sets the value of the symmetry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Symmetry }
     *     
     */
    public void setSymmetry(Symmetry value) {
        this.symmetry = value;
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
     * Gets the value of the spaceType property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getSpaceType() {
        return spaceType;
    }

    /**
     * Sets the value of the spaceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setSpaceType(java.lang.String value) {
        this.spaceType = value;
    }

    /**
     * Gets the value of the latticeType property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getLatticeType() {
        return latticeType;
    }

    /**
     * Sets the value of the latticeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setLatticeType(java.lang.String value) {
        this.latticeType = value;
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

}