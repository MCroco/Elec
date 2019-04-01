package controller;

import model.Temperature;
import view.TemperatureVue;

public class TemperatureController {
	
	private Temperature model;
	private TemperatureVue vue;
	private String alerte ="Alerte: La Température a atteint le seuil fixé!!!";
	private String info = "Rien à Signaler: Température normale.";
	
	public TemperatureController(Temperature model) {
		this.model = model;
	}
	
	public void addVue(TemperatureVue vue) {
		this.vue = vue;
	}
	
	public void modifierSeuil(int seuil) {
		model.setSeuil(seuil);
		vue.affiche(model.toString());
		temperatureEnvoi();
	}
	
	public void temperatureEnvoi() {
		if(model.getSeuil() <= model.getTemperature()) {
			vue.affiche(alerte);
		}
		else {
			vue.affiche(info);
		}
	}
	
}
