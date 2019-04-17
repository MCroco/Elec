package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.TemperatureController;
import model.Temperature;


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
	
	public TemperatureVueGui(Temperature model, TemperatureController controller){
		super(model, controller);
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Cambria", Font.PLAIN, 20));
		frame.getContentPane().setBackground(new Color(218, 165, 32));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{290, 0, 0, 33, 0, 393, 0};
		gridBagLayout.rowHeights = new int[]{36, 0, 30, 0, 38, 32, 69, 0, 0, 0, 101, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel seuilLabel = new JLabel("Seuil De La Temp\u00E9rature: ");
		seuilLabel.setToolTipText("");
		seuilLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_seuilLabel = new GridBagConstraints();
		gbc_seuilLabel.anchor = GridBagConstraints.EAST;
		gbc_seuilLabel.insets = new Insets(0, 0, 5, 5);
		gbc_seuilLabel.gridx = 0;
		gbc_seuilLabel.gridy = 3;
		frame.getContentPane().add(seuilLabel, gbc_seuilLabel);
		
		seuil = new JLabel("0");
		seuil.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_seuil = new GridBagConstraints();
		gbc_seuil.gridwidth = 4;
		gbc_seuil.insets = new Insets(0, 0, 5, 5);
		gbc_seuil.gridx = 1;
		gbc_seuil.gridy = 3;
		frame.getContentPane().add(seuil, gbc_seuil);
		
		JLabel temp = new JLabel("Temp\u00E9rature: ");
		temp.setIcon(new ImageIcon("src\\img\\thermometer (3).png"));
		temp.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_temp = new GridBagConstraints();
		gbc_temp.anchor = GridBagConstraints.NORTHEAST;
		gbc_temp.insets = new Insets(0, 0, 5, 5);
		gbc_temp.gridx = 0;
		gbc_temp.gridy = 4;
		frame.getContentPane().add(temp, gbc_temp);
		
		temperature = new JLabel("");
		temperature.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_temperature = new GridBagConstraints();
		gbc_temperature.gridwidth = 4;
		gbc_temperature.insets = new Insets(0, 0, 5, 5);
		gbc_temperature.gridx = 1;
		gbc_temperature.gridy = 4;
		frame.getContentPane().add(temperature, gbc_temperature);
		
		msg = new JLabel("message:");
		msg.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_msg = new GridBagConstraints();
		gbc_msg.anchor = GridBagConstraints.EAST;
		gbc_msg.insets = new Insets(0, 0, 5, 5);
		gbc_msg.gridx = 0;
		gbc_msg.gridy = 5;
		frame.getContentPane().add(msg, gbc_msg);
		
		message = new JLabel("");
		message.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_message = new GridBagConstraints();
		gbc_message.gridheight = 2;
		gbc_message.gridwidth = 4;
		gbc_message.insets = new Insets(0, 0, 5, 5);
		gbc_message.gridx = 1;
		gbc_message.gridy = 5;
		frame.getContentPane().add(message, gbc_message);
		
		field = new JTextField();
		field.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_field = new GridBagConstraints();
		gbc_field.gridwidth = 3;
		gbc_field.fill = GridBagConstraints.BOTH;
		gbc_field.insets = new Insets(0, 0, 5, 5);
		gbc_field.gridx = 1;
		gbc_field.gridy = 7;
		frame.getContentPane().add(field, gbc_field);
		field.setColumns(10);
		frame.setBackground(new Color(244, 164, 96));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Thermostat");
		frame.setResizable(false);
		frame.setFont(new Font("Cambria", Font.PLAIN, 20));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\img\\thermometer.png"));
		frame.setVisible(true);
		frame.setSize(800, 345);
		
		temperature.setText(String.valueOf(model.getTemperature()));
		
		panel = new JPanel();
		panel.setBackground(new Color(218, 165, 32));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 6;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 9;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnDefault = new JButton("Default");
		btnDefault.setIcon(new ImageIcon("src\\img\\temperature (4).png"));
		btnDefault.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_btnDefault = new GridBagConstraints();
		gbc_btnDefault.fill = GridBagConstraints.BOTH;
		gbc_btnDefault.insets = new Insets(0, 0, 0, 5);
		gbc_btnDefault.gridx = 5;
		gbc_btnDefault.gridy = 1;
		panel.add(btnDefault, gbc_btnDefault);
		
		modifier = new JButton("Modifier");
		modifier.setIcon(new ImageIcon("src\\img\\reset (1).png"));
		modifier.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_modifier = new GridBagConstraints();
		gbc_modifier.fill = GridBagConstraints.BOTH;
		gbc_modifier.insets = new Insets(0, 0, 0, 5);
		gbc_modifier.gridx = 9;
		gbc_modifier.gridy = 1;
		panel.add(modifier, gbc_modifier);
		
		fermer = new JButton("Fermer");
		fermer.setIcon(new ImageIcon("src\\img\\delete (1).png"));
		fermer.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_fermer = new GridBagConstraints();
		gbc_fermer.fill = GridBagConstraints.BOTH;
		gbc_fermer.insets = new Insets(0, 0, 0, 5);
		gbc_fermer.gridx = 14;
		gbc_fermer.gridy = 1;
		panel.add(fermer, gbc_fermer);
		modifier.addActionListener(this);
		fermer.addActionListener(this);
		btnDefault.addActionListener(this);
		
		update(model, null);
		affiche("Rien à Signaler: Température normale.");
	}


	@Override
	public void affiche(String string) {
		message.setText(string);
	}

	@Override
	public void update(Observable o, Object arg) {
		seuil.setText(String.valueOf(model.getSeuil()));
		temperature.setText(String.valueOf(model.getTemperature()));
		if(model.getTemperature() < model.getSeuil()) {
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
			field.setText("");
			break;
		case "Modifier":
			try {
				if(field.getText().length() != 0 ) {
					controller.modifierSeuil(Integer.parseInt(field.getText()));
					update(model, model.getTemperature());
					field.setText("");
				}
				else {
					update(model, model.getTemperature());
				}
			}
			catch(Exception e1) {
				field.setText("");
				affiche("Veillez introduire un chiffre de 0 à 100!!!!");
			}
			break;
		default:
			frame.dispose();
			break;
		}
		
	}

}
