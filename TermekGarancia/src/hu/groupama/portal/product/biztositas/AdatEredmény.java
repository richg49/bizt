//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.11 at 05:06:35 PM CET 
//


package hu.groupama.portal.product.biztositas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for adat_Eredmény complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="adat_Eredmény">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Ajánlatszám" type="{}típus_Kötelező"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adat_Eredm\u00e9ny", propOrder = {
    "aj\u00e1nlatsz\u00e1m"
})
public class AdatEredmény {

    @XmlElement(name = "Aj\u00e1nlatsz\u00e1m", required = true)
    protected String ajánlatszám;

    /**
     * Gets the value of the ajánlatszám property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAjánlatszám() {
        return ajánlatszám;
    }

    /**
     * Sets the value of the ajánlatszám property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAjánlatszám(String value) {
        this.ajánlatszám = value;
    }

}
