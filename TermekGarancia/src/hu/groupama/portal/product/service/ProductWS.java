
package hu.groupama.portal.product.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ProductWS", targetNamespace = "http://service.product.portal.groupama.hu")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ProductWS {


    /**
     * 
     * @param xml
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "storeData")
    @WebResult(name = "storeDataReturn", targetNamespace = "")
    @RequestWrapper(localName = "storeData", targetNamespace = "http://service.product.portal.groupama.hu", className = "hu.groupama.portal.product.service.StoreData")
    @ResponseWrapper(localName = "storeDataResponse", targetNamespace = "http://service.product.portal.groupama.hu", className = "hu.groupama.portal.product.service.StoreDataResponse")
    public String storeData(
        @WebParam(name = "xml", targetNamespace = "")
        String xml);

    /**
     * 
     * @param name
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "testApplication")
    @WebResult(name = "testApplicationReturn", targetNamespace = "")
    @RequestWrapper(localName = "testApplication", targetNamespace = "http://service.product.portal.groupama.hu", className = "hu.groupama.portal.product.service.TestApplication")
    @ResponseWrapper(localName = "testApplicationResponse", targetNamespace = "http://service.product.portal.groupama.hu", className = "hu.groupama.portal.product.service.TestApplicationResponse")
    public String testApplication(
        @WebParam(name = "name", targetNamespace = "")
        String name);

}
