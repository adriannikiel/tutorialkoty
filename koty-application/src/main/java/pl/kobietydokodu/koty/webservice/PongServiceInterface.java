package pl.kobietydokodu.koty.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PongServiceInterface {
	@WebMethod(operationName="ping")
	public String ping();
}
