package pl.kobietydokodu.cats.webservice;

import javax.jws.WebService;

@WebService(endpointInterface = "pl.kobietydokodu.cats.webservice.PongServiceInterface", serviceName = "PongService")
public class PongServiceImpl implements PongServiceInterface {

	@Override
	public String ping() {
		return "pong";
	}

}
