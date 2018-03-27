package schach.server;

//TODO import

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.glassfish.jersey.server.ResourceConfig;

import schach.interfaces.iBackendSpielAdmin;

@Path("imao/spiel/admin")
public class SpielAdmin extends ResourceConfig implements iBackendSpielAdmin {

//TODO

}
