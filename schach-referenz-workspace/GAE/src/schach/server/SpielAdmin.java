package schach.server;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.glassfish.jersey.server.ResourceConfig;

import schach.daten.Xml;
import schach.interfaces.iBackendSpielAdmin;

@Path("schach/spiel/admin")
public class SpielAdmin extends ResourceConfig implements iBackendSpielAdmin{

	@GET
	@Path("neuesSpiel")
	@Consumes("text/plain")
	@Produces("application/xml")
	@Override
	public String neuesSpiel(){
		try {
			schach.backend.Spiel spiel=new schach.backend.Spiel();
			spiel.initStandardbelegung();
			schach.server.Spiel.setSpiel(spiel);
			return Xml.verpackeOK("Spiel erfolgreich erstellt.");
		} catch (Exception e) {
			return Xml.verpackeFehler(e);
		}
	}

	@GET
	@Path("ladenSpiel/{pfad}")
	@Consumes("text/plain")
	@Produces("application/xml")
	@Override
	public String ladenSpiel(
			@PathParam("pfad")String pfad) {
		try{
			schach.backend.Spiel spiel=new schach.backend.Spiel(pfad);
			schach.server.Spiel.setSpiel(spiel);
			return Xml.verpackeOK("Spiel erfolgreich geladen.");
		} catch (Exception e) {
			return Xml.verpackeFehler(e);
		}		
	}
	
	@GET
	@Path("speichernSpiel/{pfad}")
	@Consumes("text/plain")
	@Produces("application/xml")
	@Override
	public String speichernSpiel(
			@PathParam("pfad")String pfad) {
		try {
			if (!pfad.endsWith(".xml")) pfad=pfad+".xml";
			Spiel.getSpiel().speichern(pfad);
			return Xml.verpackeOK("Spiel erfolgreich gespeichert.");
		} catch (Exception e) {
			return Xml.verpackeFehler(e);
		}
	}
}
