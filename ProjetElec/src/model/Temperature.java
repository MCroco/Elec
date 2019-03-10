package model;

import java.util.Observable;

public class Temperature extends Observable{

	/**
	 * Entier gardant en memoire la temperature introduite par l'utilisateur comme 
	 * seuil qui est aura une valeur par defaut
	 */
	private int seuil;
	
	public static final int SEUIL_DEFAULT = 20;
	
	/**
	 * Entier repreésentant la temperature mesuree et transmis
	 */
	private int temperature = 19;
	
	
	public Temperature() {
		seuil = 20;
		setChanged();
		notifyObservers();

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
	}
	
	@Override
	public String toString() {
		return "La Temperature vaut: " + temperature + 
				"/nLe Seuil vaut: " + seuil;
	}
	
	public static void main(String[] args) {
		new Temperature();
	}
}
