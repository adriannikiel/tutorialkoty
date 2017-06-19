package pl.kobietydokodu.cats.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PongServiceInterface {
	@WebMethod(operationName="ping")
	public String ping();
}
