package view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.TemperatureController;
import model.Temperature;

public class TemperatureVueConsole extends TemperatureVue implements Observer{
	protected Scanner sc;
	private String alerte ="Alerte: La Temp�rature a atteint le seuil fix�!!!\n";
	private String info = "Rien � Signaler: Temp�rature normale.\n";
	
	public TemperatureVueConsole(Temperature model, TemperatureController controller) {
		super(model, controller);
		update(model, null);
		sc = new Scanner(System.in);
		new Thread(new ReadInput()).start();
	}
	
	public void informer() {
		affiche("Entrez: \n--> \"m\" si vous voulez modifier le seuil temp�rature \n	"
				+ "	ou  	"
				+ "\n--> \"d\" pour le seuil par d�faut: ");
	}
	
	private class ReadInput implements Runnable{
		public void run() {
			while(true) {
				try {
					String c = sc.next();
					switch(c) {
						case "m":
							affiche("Veuillez introduire une nombre compris entre 0 et 100");
							break;
						case "d":
							controller.modifierSeuil(20);
							break;
						default: 
							affiche ("\n");
							affiche("Introduire une valeur correcte s'il vous pla�t: ");
					}
					
					int i = sc.nextInt();
					if(i < 0 || i > 100) {
						affiche("Valeur de donn�e introduite incorrecte!");					
					}
					else {
						controller.modifierSeuil(i);
					}					
				}
				catch (Exception e) {
					affiche("Veuillez introduire une valeur correcte s'il vous pla�t: ");
				}
			}
		}
	}
	
	public void affiche(String Str) {
		System.out.println(Str);
	}

	@Override
	public void update(Observable o, Object arg) {
		affiche(model.toString());
		if(model.getSeuil() <= Integer.parseInt(model.getTemperature())) {
			affiche(alerte);
		}
		else {
			affiche(info);
		}
		informer();	
	}

}
