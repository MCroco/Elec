package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.TemperatureController;
import model.Temperature;


public class TemperatureVueGui extends TemperatureVue implements ActionListener, MouseListener{
	private JFrame frame;
	private JPanel panel;
	private JButton modifier;
	private JButton fermer;
	private JButton btnDefault;
	private JTextField seuil;
	private JLabel msg;
	private JLabel temperature;
	private JLabel message;
	
	public TemperatureVueGui(Temperature model, TemperatureController controller) {
		super(model, controller);
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Cambria", Font.PLAIN, 20));
		frame.getContentPane().setBackground(new Color(218, 165, 32));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{290, 0, 0, 33, 0, 393, 0};
		gridBagLayout.rowHeights = new int[]{36, 0, 30, 38, 32, 69, 0, 101, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel seuilLabel = new JLabel("Seuil De La Temp\u00E9rature: ");
		seuilLabel.setToolTipText("");
		seuilLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_seuilLabel = new GridBagConstraints();
		gbc_seuilLabel.anchor = GridBagConstraints.EAST;
		gbc_seuilLabel.insets = new Insets(0, 0, 5, 5);
		gbc_seuilLabel.gridx = 0;
		gbc_seuilLabel.gridy = 2;
		frame.getContentPane().add(seuilLabel, gbc_seuilLabel);
		
		seuil = new JTextField();
		seuil.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_seuil = new GridBagConstraints();
		gbc_seuil.gridwidth = 4;
		gbc_seuil.insets = new Insets(0, 0, 5, 5);
		gbc_seuil.fill = GridBagConstraints.HORIZONTAL;
		gbc_seuil.gridx = 1;
		gbc_seuil.gridy = 2;
		frame.getContentPane().add(seuil, gbc_seuil);
		seuil.setColumns(20);
		
		JLabel temp = new JLabel("Temp\u00E9rature: ");
		temp.setIcon(new ImageIcon("C:\\Users\\manue\\OneDrive\\GOMAND\\EPHEC\\Cours\\2TI\\D\u00E9v. informatique avanc\u00E9 application_Th\u00E9orie\\TP\\TP_Java\\ProjetElec\\src\\img\\thermometer (3).png"));
		temp.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_temp = new GridBagConstraints();
		gbc_temp.anchor = GridBagConstraints.NORTHEAST;
		gbc_temp.insets = new Insets(0, 0, 5, 5);
		gbc_temp.gridx = 0;
		gbc_temp.gridy = 3;
		frame.getContentPane().add(temp, gbc_temp);
		
		temperature = new JLabel("");
		temperature.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_temperature = new GridBagConstraints();
		gbc_temperature.gridwidth = 4;
		gbc_temperature.insets = new Insets(0, 0, 5, 5);
		gbc_temperature.gridx = 1;
		gbc_temperature.gridy = 3;
		frame.getContentPane().add(temperature, gbc_temperature);
		
		msg = new JLabel("message:");
		msg.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_msg = new GridBagConstraints();
		gbc_msg.anchor = GridBagConstraints.EAST;
		gbc_msg.insets = new Insets(0, 0, 5, 5);
		gbc_msg.gridx = 0;
		gbc_msg.gridy = 4;
		frame.getContentPane().add(msg, gbc_msg);
		
		message = new JLabel("");
		message.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_message = new GridBagConstraints();
		gbc_message.gridheight = 2;
		gbc_message.gridwidth = 4;
		gbc_message.insets = new Insets(0, 0, 5, 5);
		gbc_message.gridx = 1;
		gbc_message.gridy = 4;
		frame.getContentPane().add(message, gbc_message);
		
		panel = new JPanel();
		panel.setBackground(new Color(218, 165, 32));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 6;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 6;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnDefault = new JButton("Default");
		btnDefault.setIcon(new ImageIcon("C:\\Users\\manue\\OneDrive\\GOMAND\\EPHEC\\Cours\\2TI\\D\u00E9v. informatique avanc\u00E9 application_Th\u00E9orie\\TP\\TP_Java\\ProjetElec\\src\\img\\temperature (4).png"));
		btnDefault.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_btnDefault = new GridBagConstraints();
		gbc_btnDefault.insets = new Insets(0, 0, 0, 5);
		gbc_btnDefault.gridx = 5;
		gbc_btnDefault.gridy = 1;
		panel.add(btnDefault, gbc_btnDefault);
		
		modifier = new JButton("Modifier");
		modifier.setIcon(new ImageIcon("C:\\Users\\manue\\OneDrive\\GOMAND\\EPHEC\\Cours\\2TI\\D\u00E9v. informatique avanc\u00E9 application_Th\u00E9orie\\TP\\TP_Java\\ProjetElec\\src\\img\\reset (1).png"));
		modifier.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_modifier = new GridBagConstraints();
		gbc_modifier.insets = new Insets(0, 0, 0, 5);
		gbc_modifier.gridx = 9;
		gbc_modifier.gridy = 1;
		panel.add(modifier, gbc_modifier);
		
		fermer = new JButton("Fermer");
		fermer.setIcon(new ImageIcon("C:\\Users\\manue\\OneDrive\\GOMAND\\EPHEC\\Cours\\2TI\\D\u00E9v. informatique avanc\u00E9 application_Th\u00E9orie\\TP\\TP_Java\\ProjetElec\\src\\img\\delete (1).png"));
		fermer.setFont(new Font("Cambria", Font.PLAIN, 20));
		GridBagConstraints gbc_fermer = new GridBagConstraints();
		gbc_fermer.insets = new Insets(0, 0, 0, 5);
		gbc_fermer.gridx = 14;
		gbc_fermer.gridy = 1;
		panel.add(fermer, gbc_fermer);
		frame.setBackground(new Color(244, 164, 96));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Thermometre");
		frame.setResizable(false);
		frame.setFont(new Font("Cambria", Font.PLAIN, 20));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\manue\\OneDrive\\GOMAND\\EPHEC\\Cours\\2TI\\D\u00E9v. informatique avanc\u00E9 application_Th\u00E9orie\\TP\\TP_Java\\ProjetElec\\src\\img\\thermometer.png"));
		frame.setVisible(true);
		frame.setSize(705, 345);
		
		temperature.setText(String.valueOf(model.getTemperature()));
		seuil.setText(String.valueOf(model.getSeuil()));
		modifier.addActionListener(this);
		fermer.addActionListener(this);
		btnDefault.addActionListener(this);
		frame.addMouseListener(this);
		
	}


	@Override
	public void affiche(String string) {
		seuil.setText(string);
	}

	@Override
	public void update(Observable o, Object arg) {
		controller.temperatureEnvoi();
		if((int) arg < model.getSeuil()) {
			temperature.setIcon(new ImageIcon("C:\\Users\\manue\\OneDrive\\GOMAND\\EPHEC\\Cours\\2TI\\D\u00E9v. informatique avanc\u00E9 application_Th\u00E9orie\\TP\\TP_Java\\ProjetElec\\src\\img\\checked.png"));
		}
		else {
			temperature.setIcon(new ImageIcon("C:\\Users\\manue\\OneDrive\\GOMAND\\EPHEC\\Cours\\2TI\\D\u00E9v. informatique avanc\u00E9 application_Th\u00E9orie\\TP\\TP_Java\\ProjetElec\\src\\img\\warning (4).png"));
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Default":
			controller.temperatureRecue(Temperature.SEUIL_DEFAULT);
			update(null, model.getTemperature());
			seuil.setEditable(false);
			break;
		case "Modifier":
			controller.temperatureRecue(Integer.parseInt(temperature.getText()));
			update(null, model.getTemperature());
			seuil.setEditable(false);
			break;
		default:
			frame.dispose();
			break;
		}
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource().getClass() == seuil.getClass()) {
			seuil.setEditable(true);
		}		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

	
}
