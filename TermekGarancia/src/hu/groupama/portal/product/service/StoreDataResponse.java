
package hu.groupama.portal.product.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="storeDataReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "storeDataReturn"
})
@XmlRootElement(name = "storeDataResponse")
public class StoreDataResponse {

    @XmlElement(required = true, nillable = true)
    protected String storeDataReturn;

    /**
     * Gets the value of the storeDataReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreDataReturn() {
        return storeDataReturn;
    }

    /**
     * Sets the value of the storeDataReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreDataReturn(String value) {
        this.storeDataReturn = value;
    }

}
