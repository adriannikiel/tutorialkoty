package pl.kobietydokodu.koty.webservice;

import javax.jws.WebService;

@WebService(endpointInterface = "pl.kobietydokodu.koty.webservice.PongServiceInterface", serviceName = "PongService")
public class PongServiceImpl implements PongServiceInterface {

	@Override
	public String ping() {
		return "pong";
	}

}
