package test.jee.listener;

import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;

import test.jee.server.WebServiceBeanService;

@Stateless
public class WebServiceClient {

    @WebServiceRef
    private WebServiceBeanService service;

    public String service(String message) {
        System.out.println("APP - service " + service);
        return service.getWebServiceBeanPort().service(message);
    }
}
