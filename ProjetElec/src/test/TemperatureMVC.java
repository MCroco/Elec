package test;

import controller.TemperatureController;
import model.Temperature;
import view.TemperatureVueConsole;
import view.TemperatureVueGui;

public class TemperatureMVC {

	public TemperatureMVC() {
		Temperature model = new Temperature();
		
		TemperatureController ctrGUI = new TemperatureController(model);
		TemperatureController ctrConsole = new TemperatureController(model);
		
		TemperatureVueGui gui = new TemperatureVueGui(model, ctrGUI);
		TemperatureVueConsole console = new TemperatureVueConsole(model, ctrConsole);
		
		ctrGUI.addVue(gui);
		ctrConsole.addVue(console);	
		
		model.addObserver(gui);
		model.addObserver(console);
	}
	
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TemperatureMVC();
			}
		});
	}
}
