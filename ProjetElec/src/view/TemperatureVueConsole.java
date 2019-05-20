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
	 * scanner r�cuperant les donn�es entr�es par l'utilisateur.
	 */
	protected Scanner sc;
	
	/**
	 * Le message en cas de temp�rature sup�rieure au seuil.
	 */
	private String alerte ="Alerte: La Temp�rature a atteint le seuil fix�!!!\n";
	
	/**
	 * Le message en cas de temp�rature inf�rieure au seuil fix�e.
	 */
	private String info = "Rien � Signaler: Temp�rature normale.\n";
	
	/**
	 * Constructeur prennant en param�tre
	 * @param model le mod�le qui est en fait , l'�lement observ�.
	 * @param controller le controlleur qui servira d'interm�diare entre la vue et le mod�le
	 */
	public TemperatureVueConsole(Temperature model, TemperatureController controller) {
		super(model, controller);
		update(model, null);
		sc = new Scanner(System.in);
		new Thread(new ReadInput()).start();
	}
	
	/**
	 * M�thode informant du fonctionnement de l'application en vue console.
	 */
	public void informer() {
		affiche("Entrez: \n--> \"m\" si vous voulez modifier le seuil temp�rature \n	"
				+ "	ou  	"
				+ "\n--> \"d\" pour le seuil par d�faut: ");
	}
	
	/**
	 * @author mandamtang
	 * Cette classe sera utilis�e par le processus, raison pour laquelle on l'a impl�ment� comme Runnable
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
							affiche("Introduire une valeur correcte s'il vous pla�t: ");
					}
					
					int i = sc.nextInt();
					//cas o� l'entier ne se trouve pas entre 0 et 100, notre seuil de mesure de temp�rature.
					if(i < 0 || i > 100) {
						affiche("Valeur de donn�e introduite incorrecte!");					
					}
					else {
						//appel de la m�thode du controller responsable de la modification du seuil c�t� mod�le.
						controller.modifierSeuil(i);
					}					
				}
				/*
				 * exception d�clanch�e au cas o� on introduit une cha�ne de caract�res ou autre chose en d�hors de
				 * ce qui est pr�vue pour l'application.
				*/
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
		if(model.getSeuil() <= model.getTemperature()) {
			affiche(alerte);
		}
		else {
			affiche(info);
		}
		informer();	
	}

}
