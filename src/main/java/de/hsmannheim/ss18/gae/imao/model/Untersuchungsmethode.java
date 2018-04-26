package de.hsmannheim.ss18.gae.imao.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Untersuchungsmethode {
	private String name;
	private long behandlungsKosten;
	private long anschaffungsKosten;
	private long unterhaltsKosten;
	private boolean freigeschaltet;

	public Untersuchungsmethode() {
	}

	public Untersuchungsmethode(String name, int behandlungsKosten, int anschaffungsKosten, int unterhaltsKosten,
			boolean freigeschaltet) {
		this.name = name;
		this.behandlungsKosten = behandlungsKosten;
		this.anschaffungsKosten = anschaffungsKosten;
		this.unterhaltsKosten = unterhaltsKosten;
		this.freigeschaltet = freigeschaltet;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode objectNode = mapper.createObjectNode();
		objectNode.put("name", this.name);
		objectNode.put("behandlungsKosten", this.behandlungsKosten);
		objectNode.put("anschaffungsKosten", this.anschaffungsKosten);
		objectNode.put("unterhaltsKosten", this.unterhaltsKosten);
		objectNode.put("freigeschaltet", this.freigeschaltet);

		return objectNode.toString();
	}

	public String getName() {
		return name;
	}

	public long getBehandlungsKosten() {
		return behandlungsKosten;
	}

	public void setBehandlungsKosten(long kosten) {
		this.behandlungsKosten = kosten;
	}

	public long getAnschaffungsKosten() {
		return anschaffungsKosten;
	}

	public void setAnschaffungsKosten(long anschaffungsKosten) {
		this.anschaffungsKosten = anschaffungsKosten;
	}

	public boolean isFreigeschaltet() {
		return freigeschaltet;
	}

	public void setFreigeschaltet(boolean freigeschaltet) {
		this.freigeschaltet = freigeschaltet;
	}

	public long getUnterhaltsKosten() {
		return unterhaltsKosten;
	}

	public void setUnterhaltsKosten(long unterhaltsKosten) {
		this.unterhaltsKosten = unterhaltsKosten;
	}

}
