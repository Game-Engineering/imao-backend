package de.hsmannheim.ss18.gae.imao.model.wirtschaft;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.hsmannheim.ss18.gae.imao.model.Person;
import de.hsmannheim.ss18.gae.imao.model.enums.EGeschlecht;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager extends Person {
	private List<Mail> posteingang = new ArrayList<>();
	private List<Mail> postausgang = new ArrayList<>();
	private Map<String, Integer> einnahmen = new HashMap<>();
	private Map<String, Integer> ausgaben = new HashMap<>();
	private Map<String, Integer> rufzuwachs = new HashMap<>();
	private Map<String, Integer> rufverlust = new HashMap<>();
	private int rufbilanz = 0;
	private int budgetbilanz = 0;
	private int budget = 0;
	private int ruf = 50;
	
	private Interview interview; 
	private Sponsoren sponsoren;

	/**
	 *
	 * @param vorname
	 * @param nachname
	 * @param geschlecht
	 */
	public Manager(String vorname, String nachname, EGeschlecht geschlecht) {
		super(vorname, nachname, geschlecht);
		this.interview = new Interview(this);
		this.sponsoren=new Sponsoren(this);
	}

	/**
	 * Bereite den Rundenbeginn vor
	 * @param neueMails
	 */
	public void rundenanfang(List<Mail> neueMails) {
		List<Mail> alteMails=new ArrayList<Mail>() ;
		alteMails.addAll(posteingang);
		posteingang.clear();
		posteingang.addAll(neueMails);
		posteingang.addAll(alteMails);
		einnahmen.clear();
		ausgaben.clear();
		rufzuwachs.clear();
		rufverlust.clear();
		rufbilanz = 0;
		budgetbilanz = 0;
	}

	/**
	 *
	 * @param mail
	 */
	public void erhalteMail(Mail mail) {
		List<Mail> alteMails=new ArrayList<Mail>() ;
		alteMails.addAll(posteingang);
		posteingang.clear();
		posteingang.add(mail);
		posteingang.addAll(alteMails);
	}

	/**
	 *
	 * @param mail
	 */
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

	public void einnahme(String grund, int budget) {
		this.budget += budget;
		budgetbilanz += budget;
		einnahmen.put(grund, budget);
	}

	public void ausgabe(String grund, int budget) {
		this.budget -= budget;
		budgetbilanz -= budget;
		ausgaben.put(grund, budget);
	}

	public void rufZuwachs(String grund, int ruf) {
		this.ruf += ruf;
		rufbilanz += ruf;
		rufzuwachs.put(grund, ruf);
	}

	public void rufVerlust(String grund, int ruf) {
		this.ruf -= ruf;
		rufbilanz -= ruf;
		rufverlust.put(grund, ruf);
	}

	public long getBudget() {
		return budget;
	}

	public long getRuf() {
		return ruf;
	}

	public List<Mail> getPosteingang() {
		return posteingang;
	}

	public List<Mail> getPostausgang() {
		return postausgang;
	}

	public Map<String, Integer> getEinnahmen() {
		return einnahmen;
	}

	public Map<String, Integer> getAusgaben() {
		return ausgaben;
	}

	public Map<String, Integer> getRufzuwachs() {
		return rufzuwachs;
	}

	public Map<String, Integer> getRufverlust() {
		return rufverlust;
	}

	public int getRufbilanz() {
		return rufbilanz;
	}

	public int getBudgetbilanz() {
		return budgetbilanz;
	}
	
	public Interview getInterview() {
		return this.interview;
	}
	
	public Sponsoren getSponsoren() {
		return this.sponsoren;
	}

}
