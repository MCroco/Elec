package view;

import java.util.Observer;

import controller.TemperatureController;
import model.Temperature;

public abstract class TemperatureVue implements Observer{

	protected Temperature model;
	protected TemperatureController controller;
	
	public TemperatureVue(Temperature model, TemperatureController controller) {
		this.model = model;
		this.controller = controller;
	}
	
	public abstract void affiche(String string);
}
