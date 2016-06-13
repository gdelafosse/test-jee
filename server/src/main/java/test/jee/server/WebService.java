package test.jee.server;

import javax.jws.WebMethod;

@javax.jws.WebService
public interface WebService
{
    @WebMethod
    String service(String message);
}
