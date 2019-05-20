package model;

import java.util.Observable;

public class Temperature2 extends Observable{

	/**
	 * Entier gardant en memoire la temperature introduite par l'utilisateur comme 
	 * seuil qui est aura une valeur par defaut
	 */
	private int seuil;
	
	public static final int SEUIL_DEFAULT = 20;
	
	/**
	 * Entier représentant la temperature mesuree et transmis
	 */
	private int temperature = 0;
	
	
	public Temperature2() {
		seuil = 20;
	}


	public int getSeuil() {
		return seuil;
	}


	public void setSeuil(int seuil) {
		this.seuil = seuil;
		setChanged();
		notifyObservers();

	}


	public int getTemperature() {
		return temperature;
	}


	public void setTemperature(int temperature) {
		this.temperature = temperature;
		setChanged();
		notifyObservers();
	}
	
	@Override
	public String toString() {
		return  "\n\n\n\n*** Le Seuil de la température vaut:   " + seuil +
				"\n*** La Temperature vaut:               " + temperature;
	}
}
