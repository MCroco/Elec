package model;

import java.util.Observable;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Temperature extends Observable{

	/**
	 * Entier gardant en memoire la temperature introduite par l'utilisateur comme 
	 * seuil qui est aura une valeur par defaut
	 */
	private int seuil;
	public static SerialPort serialPort;
	
	public static final int SEUIL_DEFAULT = 20;
	
	/**
	 * Entier représentant la temperature mesuree et transmis
	 */
	private String temperature = "0";
	
	
	public Temperature() {
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


	public String getTemperature() {
		return temperature;
	}


	public void setTemperature(String temperature) {
		this.temperature = temperature;
		setChanged();
		notifyObservers();
	}
	
	@Override
	public String toString() {
		return  "\n\n\n\n*** Le Seuil de la température vaut:   " + seuil +
				"\n*** La Temperature vaut:               " + temperature;
	}
	
	public static SerialPort getSerialPort() {
		return serialPort;
	}
	
	public static void setSerialPort(SerialPort serialPort) {
		Temperature.serialPort = serialPort;
	}
	
	public String lectureEntree() {
		if(isChanged(getTemperature())) {
			try {
			       
			       byte buffer[]= Temperature.getSerialPort().readBytes(2);//Read 10 bytes from serial port
			        String str =  new String(buffer);
			        setTemperature(str);
			        return str;
			        //Close serial port
			 	}
			    catch (SerialPortException ex) {
			        //return ex.toString();
			    	return "0";
			    }
		}
    	return "0";
	}
	
	public boolean isChanged(String str) {
		return temperature == str;
	}
	
	public void setTempSortie(String text) {
		try {
			
			//text = ";" + field.getText();
			Temperature.getSerialPort().setParams(SerialPort.BAUDRATE_9600, 
	                             SerialPort.DATABITS_8,
	                             SerialPort.STOPBITS_1,
	                             SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
			Temperature.getSerialPort().writeBytes(text.getBytes());//Write data to port
	        
	      
	    
	        
			Temperature.getSerialPort().setParams(9600, 8, 1, 0);//Set params.
	 
	    }
	    catch (SerialPortException ex) {
	        System.out.println(ex);
	    }
	}
}
