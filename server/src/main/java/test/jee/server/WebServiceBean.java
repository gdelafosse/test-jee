package test.jee.server;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Stateless
@WebService
public class WebServiceBean
{
    @WebMethod
    public String service(String message) {
        return String.format("modified '%s'", message);
    }
}
