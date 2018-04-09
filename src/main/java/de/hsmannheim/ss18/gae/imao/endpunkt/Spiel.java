package de.hsmannheim.ss18.gae.imao.endpunkt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;

import de.hsmannheim.ss18.gae.imao.model.Blutbild;
import de.hsmannheim.ss18.gae.imao.model.Patient;

@Path("/spiel")
public class Spiel extends ResourceConfig {

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "It works";
	}

	
	@GET
	@Path("/start/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public String start(@PathParam("param") String msg) {

		String output = "";
		String arzt = "arzt";
		String wirtschaft = "wirtschaft";
		
		if (msg.equals(arzt)) {
			output += "Spiel startet als : Arzt";
		} else if (msg.equals(wirtschaft)) {
			output += "Spiel startet als : Wirtschaft";
		} else {
			output += "Falsche eingabe";
		}
		return output;
	}
	
	@GET
	@Path("/getPatatient")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPatatient() {		
		return new Patient().toString();
	}
	
	@GET
	@Path("/getBlutbild")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBlutbild() {
		return new Blutbild(1234).toString();
	}

}