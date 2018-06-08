package de.hsmannheim.ss18.gae.imao.model.medizin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsmannheim.ss18.gae.imao.model.Person;
import de.hsmannheim.ss18.gae.imao.model.enums.EGeschlecht;
import de.hsmannheim.ss18.gae.imao.model.wirtschaft.Mail;

public class Arzt extends Person {
	private List<Mail> posteingang = new ArrayList<>();
	private List<Mail> postausgang = new ArrayList<>();
	private Map<String, Integer> rufzuwachs = new HashMap<>();
	private Map<String, Integer> rufverlust = new HashMap<>();
	private Map<String, Integer> ausgaben = new HashMap<>();
	private int budget;
	private int ruf;

	/**
	 * 
	 * @param vorname
	 * @param nachname
	 */
	public Arzt(String vorname, String nachname, EGeschlecht geschlecht) {
		super(vorname, nachname, geschlecht);
	}

	public void erhalteMail(Mail mail) {
		List<Mail> alteMails=new ArrayList<Mail>() ;
		alteMails.addAll(posteingang);
		posteingang.clear();
		posteingang.add(mail);
		posteingang.addAll(alteMails);
	}

	public void sendeMail(Mail mail) {
		List<Mail> alteMails=new ArrayList<Mail>() ;
		alteMails.addAll(posteingang);
		postausgang.clear();
		postausgang.add(mail);
		postausgang.addAll(alteMails);
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("vorname", this.vorname);
		objectNode.put("nachname", this.nachname);
		objectNode.put("geschlecht", this.geschlecht.toString());

		return objectNode.toString();
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public long getRuf() {
		return ruf;
	}

	public void setRuf(int ruf) {
		this.ruf = ruf;
	}

	public Map<String, Integer> getRufzuwachs() {
		return rufzuwachs;
	}

	public Map<String, Integer> getRufverlust() {
		return rufverlust;
	}

	public Map<String, Integer> getAusgaben() {
		return ausgaben;
	}

	public List<Mail> getPosteingang() {
		return posteingang;
	}

}
