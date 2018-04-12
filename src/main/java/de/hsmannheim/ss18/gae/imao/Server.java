package de.hsmannheim.ss18.gae.imao;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import de.hsmannheim.ss18.gae.imao.endpunkt.Spiel;

@ApplicationPath("/api")
public class Server extends Application {
	
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(Spiel.class);
		return classes;
	}

}