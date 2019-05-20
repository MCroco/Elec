package view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.TemperatureController;
import model.Temperature;

/**
 * @author mandamtang
 * Cette classe est responsable de la vue console
 */
public class TemperatureVueConsole extends TemperatureVue implements Observer{
	/**
	 * scanner récuperant les données entrées par l'utilisateur.
	 */
	protected Scanner sc;
	
	/**
	 * Le message en cas de température supérieure au seuil.
	 */
	private String alerte ="Alerte: La Température a atteint le seuil fixé!!!\n";
	
	/**
	 * Le message en cas de température inférieure au seuil fixée.
	 */
	private String info = "Rien à Signaler: Température normale.\n";
	
	/**
	 * Constructeur prennant en paramètre
	 * @param model le modèle qui est en fait , l'élement observé.
	 * @param controller le controlleur qui servira d'intermédiare entre la vue et le modèle
	 */
	public TemperatureVueConsole(Temperature model, TemperatureController controller) {
		super(model, controller);
		update(model, null);
		sc = new Scanner(System.in);
		new Thread(new ReadInput()).start();
	}
	
	/**
	 * Méthode informant du fonctionnement de l'application en vue console.
	 */
	public void informer() {
		affiche("Entrez: \n--> \"m\" si vous voulez modifier le seuil température \n	"
				+ "	ou  	"
				+ "\n--> \"d\" pour le seuil par défaut: ");
	}
	
	/**
	 * @author mandamtang
	 * Cette classe sera utilisée par le processus, raison pour laquelle on l'a implémenté comme Runnable
	 */
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
							affiche("Introduire une valeur correcte s'il vous plaît: ");
					}
					
					int i = sc.nextInt();
					//cas où l'entier ne se trouve pas entre 0 et 100, notre seuil de mesure de température.
					if(i < 0 || i > 100) {
						affiche("Valeur de donnée introduite incorrecte!");					
					}
					else {
						//appel de la méthode du controller responsable de la modification du seuil côté modèle.
						controller.modifierSeuil(i);
					}					
				}
				/*
				 * exception déclanchée au cas où on introduit une chaîne de caractères ou autre chose en déhors de
				 * ce qui est prévue pour l'application.
				*/
				catch (Exception e) {
					affiche("Veuillez introduire une valeur correcte s'il vous plaît: ");
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
		if(model.getSeuil() <= model.getTemperature()) {
			affiche(alerte);
		}
		else {
			affiche(info);
		}
		informer();	
	}

}
