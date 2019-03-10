package test;

import controller.TemperatureController;
import model.Temperature;
import view.TemperatureVueGui;

public class TemperatureMVC {

	public TemperatureMVC() {
		Temperature model = new Temperature();
		
		TemperatureController ctrGUI = new TemperatureController(model);
		
		TemperatureVueGui gui = new TemperatureVueGui(model, ctrGUI);
		
		ctrGUI.addVue(gui);
	}
	
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TemperatureMVC();
			}
		});
	}
}
