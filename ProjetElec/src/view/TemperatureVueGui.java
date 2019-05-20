package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.TemperatureController;
import gnu.io.SerialPortEvent;
import jssc.SerialPort;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;
import model.Temperature;
import javax.swing.SwingConstants;


public class TemperatureVueGui extends TemperatureVue implements ActionListener, Observer{
	private JFrame frame;
	private JPanel panel;
	private JButton modifier;
	private JButton fermer;
	private JButton btnDefault;
	private JLabel msg;
	private JLabel temperature;
	private JLabel message;
	private JLabel seuil;
	private JTextField field;
	public static  InputStream inputStream;
	public static SerialPort portName;
	static JLabel msgstatus = new JLabel("");
	static JComboBox<String> portlst = new JComboBox<String>();
	static JButton disconnect  = new JButton("déconnexion");
	static JButton connect = new JButton("connexion");
	public String text ;
	public boolean flag = false;
	
	
	public TemperatureVueGui(Temperature model, TemperatureController controller){
		super(model, controller);
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Cambria", Font.PLAIN, 20));
		frame.getContentPane().setBackground(new Color(218, 165, 32));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{290, 0, 0, 0, 33, 0, 393, 0};
		gridBagLayout.rowHeights = new int[]{36, 0, 30, 0, 0, 38, 32, 69, 0, 0, 0, 0, 0, 101, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel seuilLabel = new JLabel("Seuil De La Temp\u00E9rature: ");
		seuilLabel.setToolTipText("");
		seuilLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_seuilLabel = new GridBagConstraints();
		gbc_seuilLabel.anchor = GridBagConstraints.EAST;
		gbc_seuilLabel.insets = new Insets(0, 0, 5, 5);
		gbc_seuilLabel.gridx = 0;
		gbc_seuilLabel.gridy = 4;
		frame.getContentPane().add(seuilLabel, gbc_seuilLabel);
		
		seuil = new JLabel("0");
		seuil.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_seuil = new GridBagConstraints();
		gbc_seuil.gridwidth = 5;
		gbc_seuil.insets = new Insets(0, 0, 5, 5);
		gbc_seuil.gridx = 1;
		gbc_seuil.gridy = 4;
		frame.getContentPane().add(seuil, gbc_seuil);
		
		JLabel temp = new JLabel("Temp\u00E9rature: ");
		temp.setIcon(new ImageIcon("src\\img\\thermometer (3).png"));
		temp.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_temp = new GridBagConstraints();
		gbc_temp.anchor = GridBagConstraints.NORTHEAST;
		gbc_temp.insets = new Insets(0, 0, 5, 5);
		gbc_temp.gridx = 0;
		gbc_temp.gridy = 5;
		frame.getContentPane().add(temp, gbc_temp);
		
		temperature = new JLabel("");
		temperature.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_temperature = new GridBagConstraints();
		gbc_temperature.gridwidth = 5;
		gbc_temperature.insets = new Insets(0, 0, 5, 5);
		gbc_temperature.gridx = 1;
		gbc_temperature.gridy = 5;
		frame.getContentPane().add(temperature, gbc_temperature);
		
		msg = new JLabel("message:");
		msg.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_msg = new GridBagConstraints();
		gbc_msg.anchor = GridBagConstraints.EAST;
		gbc_msg.insets = new Insets(0, 0, 5, 5);
		gbc_msg.gridx = 0;
		gbc_msg.gridy = 6;
		frame.getContentPane().add(msg, gbc_msg);
		
		message = new JLabel("");
		message.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_message = new GridBagConstraints();
		gbc_message.gridheight = 2;
		gbc_message.gridwidth = 5;
		gbc_message.insets = new Insets(0, 0, 5, 5);
		gbc_message.gridx = 1;
		gbc_message.gridy = 6;
		frame.getContentPane().add(message, gbc_message);
		frame.setBackground(new Color(244, 164, 96));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Thermostat");
		frame.setResizable(false);
		frame.setFont(new Font("Cambria", Font.PLAIN, 20));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\manue\\Documents\\Elec-master\\Elec-master\\ProjetElec\\src\\img\\thermometer (3).png"));
		frame.setVisible(true);
		frame.setSize(800, 345);
		
		temperature.setText(String.valueOf(model.getTemperature()));
		
		field = new JTextField();
		field.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_field = new GridBagConstraints();
		gbc_field.gridwidth = 3;
		gbc_field.fill = GridBagConstraints.BOTH;
		gbc_field.insets = new Insets(0, 0, 5, 5);
		gbc_field.gridx = 2;
		gbc_field.gridy = 8;
		frame.getContentPane().add(field, gbc_field);
		field.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(new Color(218, 165, 32));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridheight = 3;
		gbc_panel.gridwidth = 7;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 10;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		portlst.setBounds(10, 11, 112, 20);
		GridBagConstraints gbc_portlst = new GridBagConstraints();
		gbc_portlst.gridwidth = 2;
		gbc_portlst.insets = new Insets(0, 0, 5, 5);
		gbc_portlst.gridx = 0;
		gbc_portlst.gridy = 0;
		panel.add(portlst, gbc_portlst);
		msgstatus.setBounds(10, 61, 152, 31);
		GridBagConstraints gbc_msgstatus = new GridBagConstraints();
		gbc_msgstatus.insets = new Insets(0, 0, 5, 5);
		gbc_msgstatus.gridx = 4;
		gbc_msgstatus.gridy = 0;
		
		
		panel.add(msgstatus, gbc_msgstatus);
		disconnect.setHorizontalAlignment(SwingConstants.LEFT);
		disconnect.setIcon(new ImageIcon("src\\img\\disconnected-chains.png"));
		disconnect.setFont(new Font("Cambria", Font.PLAIN, 20));
		disconnect.setBounds(10, 42, 112, 23);
		disconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					disconnect.setVisible(false);
					connect.setVisible(true);
					Temperature.getSerialPort().closePort();
					msgstatus.setForeground(Color.red);
					msgstatus.setText("Déconnexion");
					System.out.println("Port est fermé ");
					
					} catch(SerialPortException e) {
						e.printStackTrace();
					}
			}
		});
		connect.setIcon(new ImageIcon("src\\img\\link.png"));
		connect.setFont(new Font("Cambria", Font.PLAIN, 20));
		connect.setBounds(10, 42, 112, 23);
		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			    
			
				try {
					Temperature.setSerialPort(new SerialPort( (String) portlst.getSelectedItem()));
					
					Temperature.getSerialPort().openPort();
					
					Temperature.getSerialPort().setParams(9600, 8, 1, 0);
					int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;
		
					connect.setVisible(false);
					disconnect.setVisible(true);
					msgstatus.setText("Connected");
					msgstatus.setForeground(Color.blue);
					
					System.out.println("Le port est ouvert");
				
				}
				catch (SerialPortException ex) {
					System.out.println(ex);
					msgstatus.setText("Port is busy");
					System.out.println("Port is busy");
					msgstatus.setForeground(Color.red);
					JOptionPane.showMessageDialog(null,"This port is busy", "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		

	  
		GridBagConstraints gbc_connect = new GridBagConstraints();
		gbc_connect.fill = GridBagConstraints.BOTH;
		gbc_connect.insets = new Insets(0, 0, 5, 5);
		gbc_connect.gridx = 5;
		gbc_connect.gridy = 1;
		panel.add(connect, gbc_connect);
		GridBagConstraints gbc_disconnect = new GridBagConstraints();
		gbc_disconnect.fill = GridBagConstraints.BOTH;
		gbc_disconnect.insets = new Insets(0, 0, 5, 5);
		gbc_disconnect.gridx = 7;
		gbc_disconnect.gridy = 1;
		panel.add(disconnect, gbc_disconnect);
		disconnect.setVisible(false);
		
		btnDefault = new JButton("Default");
		btnDefault.setIcon(new ImageIcon("src\\img\\temperature (4).png"));
		btnDefault.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_btnDefault = new GridBagConstraints();
		gbc_btnDefault.gridwidth = 5;
		gbc_btnDefault.fill = GridBagConstraints.BOTH;
		gbc_btnDefault.insets = new Insets(0, 0, 5, 10);
		gbc_btnDefault.gridx = 9;
		gbc_btnDefault.gridy = 1;
		panel.add(btnDefault, gbc_btnDefault);
		btnDefault.addActionListener(this);
		
		modifier = new JButton("Modifier");
		modifier.setIcon(new ImageIcon("src\\img\\reset (1).png"));
		modifier.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_modifier = new GridBagConstraints();
		gbc_modifier.insets = new Insets(0, 0, 5, 5);
		gbc_modifier.gridwidth = 2;
		gbc_modifier.anchor = GridBagConstraints.EAST;
		gbc_modifier.fill = GridBagConstraints.VERTICAL;
		gbc_modifier.gridx = 15;
		gbc_modifier.gridy = 1;
		panel.add(modifier, gbc_modifier);
		modifier.addActionListener(this);
		
		fermer = new JButton("Fermer");
		fermer.setIcon(new ImageIcon("src\\img\\delete (1).png"));
		fermer.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_fermer = new GridBagConstraints();
		gbc_fermer.insets = new Insets(0, 0, 5, 5);
		gbc_fermer.fill = GridBagConstraints.BOTH;
		gbc_fermer.gridx = 18;
		gbc_fermer.gridy = 1;
		panel.add(fermer, gbc_fermer);
		fermer.addActionListener(this);
		
		update(model, null);
		affiche("Rien à Signaler: Température normale.");
		String[] portNames = SerialPortList.getPortNames();
		for(int i = 0; i< portNames.length; i++) {
			portlst.addItem(portNames[i]);
		}
		
		/*while(true) {
			if(Temperature.getSerialPort().isOpened()) {
				if(model.isChanged(model.lectureEntree()))
					System.out.println(model.lectureEntree());
			}
		}*/
	}
	


	@Override
	public void affiche(String string) {
		message.setText(string);
	}

	@Override
	public void update(Observable o, Object arg) {
		seuil.setText(String.valueOf(model.getSeuil()));
		temperature.setText(String.valueOf(model.getTemperature()));
		if(Integer.parseInt(model.getTemperature()) < model.getSeuil()) {
			message.setIcon(new ImageIcon("src\\img\\checked.png"));
		}
		else {
			message.setIcon(new ImageIcon("src\\img\\warning (4).png"));
		}
		
	
			
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Default":
			controller.modifierSeuil(Temperature.SEUIL_DEFAULT);
			update(model, model.getTemperature());
			controller.temperatureEnvoi();
			//model.setTempSortie(";20");
			field.setText("");
			model.lectureEntree();
			//affiche(model.lectureEntree());
			break;
		case "Modifier":
			if(field.getText().length() != 0) {
				/*try {
					
					text = ";" + field.getText();
			        
					Temperature.getSerialPort().setParams(SerialPort.BAUDRATE_9600, 
			                             SerialPort.DATABITS_8,
			                             SerialPort.STOPBITS_1,
			                             SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
					Temperature.getSerialPort().writeBytes(text.getBytes());//Write data to port
			        
			      
			    
			        
					Temperature.getSerialPort().setParams(9600, 8, 1, 0);//Set params.
			 
			    }
			    catch (SerialPortException ex) {
			        System.out.println(ex);
			    }*/
				//model.setTempSortie(';' + field.getText());
				model.lectureEntree();
				
				controller.modifierSeuil(Integer.parseInt(field.getText()));
				update(model, model.getTemperature());
				controller.temperatureEnvoi();
				field.setText("");
			}
			else {
				update(null, null);
			}
			break;
		default:
			frame.dispose();
			break;
		}
	}
	
	public void miseAJourTemp() {
		while(true) {
				if(model.isChanged(model.lectureEntree()))
					System.out.println(model.lectureEntree());
		}
		
	}

}