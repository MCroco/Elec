package view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.TemperatureController;
import model.Temperature;

public class TemperatureVueConsole extends TemperatureVue implements Observer{
	protected Scanner sc;
	
	public TemperatureVueConsole(Temperature model, TemperatureController controller) {
		super(model, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread(new ReadInput()).start();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(model);
		informer();		
	}
	
	public void informer() {
		affiche("Entrez un nombre entier si vous voulez modifier le seuil température: ");
	}
	
	private class ReadInput implements Runnable{
		public void run() {
			while(true) {
				try {
					
					int i = sc.nextInt();
					if(i < -15 || i > 150) {
						affiche("Valeur de donnée introduite incorrecte!");
						informer();
					}
					else {
						controller.temperatureRecue(i);
						informer();
					}
					
				}
				catch (Exception e) {
					affiche("Veuillez introduire une valeur correcte s'il vous plaît: ");
				}
			}
		}
	}
	
	public void affiche(String Str) {
		System.out.println(Str);
	}

}
