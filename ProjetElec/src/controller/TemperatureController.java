package controller;

import model.Temperature;
import view.TemperatureVue;

public class TemperatureController {
	
	private Temperature model;
	private TemperatureVue vue;
	private String alerte ="Alerte: La Temp�rature a atteint le seuil fix�!!!";
	private String info = "Rien � Signaler: Temp�rature normale.";
	
	public TemperatureController(Temperature model) {
		this.model = model;
	}
	
	public void addVue(TemperatureVue vue) {
		this.vue = vue;
	}
	
	public void temperatureRecue(int temp) {
		model.setSeuil(temp);
		vue.affiche(model.toString());
		if(model.getTemperature() >= temp) {
			vue.affiche(alerte + model.getTemperature());
		}
		else {
			vue.affiche(info + model.getTemperature());
		}
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
