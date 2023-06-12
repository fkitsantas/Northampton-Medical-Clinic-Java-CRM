package com.northamptonmedicalclinic.app.views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.northamptonmedicalclinic.app.controller.LoginController;
import com.northamptonmedicalclinic.app.models.LoginModel;


/**
 * This class defines the Login View. It has been generated using the Window Builder tool.
 * The constructor accepts the model and controller. The button's action listeners call methods
 * in the controller.
 *
 */
public class LoginView extends JFrame{
	private JPasswordField pwdD;
	private JButton btnExit, btnLogIn;
	private JTextField txtUsername;
	private JLabel lblError;
	
	
	private final int WIDTH = 200;
	private final int[] rows = new int[] {64,48,48,48,48,64};
	private final int[] columns = new int[] {WIDTH, WIDTH, WIDTH};
	
	private LoginController controller;
	private LoginModel model;
	
	/**
	 * Create the frame.
	 */
	public LoginView(LoginController controller, LoginModel model) {
		setTitle("Northampton Medical Clinic");
		//Set size and center view.
		setSize(800, 600);
		setLocationRelativeTo(null);

		this.controller = controller;
		this.model = model;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = columns;
		gridBagLayout.rowHeights = rows;
		gridBagLayout.columnWeights = new double[]{1, 0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0,0,0,0,0,1};
		getContentPane().setLayout(gridBagLayout);
		
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 1;
		getContentPane().add(lblUserName, gbc_lblUserName);
		
		
		txtUsername = new JTextField();
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.fill = GridBagConstraints.BOTH;
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 1;
		getContentPane().add(txtUsername, gbc_txtUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		getContentPane().add(lblPassword, gbc_lblPassword);
		
		pwdD = new JPasswordField();
		pwdD.setToolTipText("Enter Password");
		GridBagConstraints gbc_pwdD = new GridBagConstraints();
		gbc_pwdD.insets = new Insets(0, 0, 5, 5);
		gbc_pwdD.fill = GridBagConstraints.BOTH;
		gbc_pwdD.gridx = 1;
		gbc_pwdD.gridy = 2;
		getContentPane().add(pwdD, gbc_pwdD);
		
		btnLogIn = new JButton("Log In");
		GridBagConstraints gbc_btnLogIn = new GridBagConstraints();
		gbc_btnLogIn.fill = GridBagConstraints.BOTH;
		gbc_btnLogIn.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogIn.gridx = 1;
		gbc_btnLogIn.gridy = 3;
		getContentPane().add(btnLogIn, gbc_btnLogIn);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblError.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 4;
		getContentPane().add(lblError, gbc_lblError);
		
		btnExit = new JButton("Exit");
		btnExit.setHorizontalAlignment(SwingConstants.RIGHT);
		btnExit.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(16, 16, 16, 16);
		gbc_btnExit.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnExit.gridx = 2;
		gbc_btnExit.gridy = 5;
		getContentPane().add(btnExit, gbc_btnExit);

		final JLabel bg = new JLabel();
		bg.setOpaque(true);
		bg.setBounds(0, 0, 800, 600);
		bg.setIcon(new ImageIcon(LoginView.class.getResource("/res/bg.jpg")));
		GridBagConstraints gbc_bg = new GridBagConstraints();
		gbc_bg.gridwidth = 3;
		gbc_bg.gridheight = 6;
		gbc_bg.gridx = 0;
		gbc_bg.gridy = 0;
		getContentPane().add(bg, gbc_bg);

		
		initController();
		updateView();
	}
	
	private void initController() {
		btnLogIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				final String user = txtUsername.getText();
				final String password = pwdD.getText();
				controller.login(user, password);
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
	}
	
	/**
	 * Updates the view if there is an error message in the model.
	 */
	public void updateView() {
		if (model.isError()) {
			lblError.setVisible(true);
			lblError.setText(model.getError());
		} else {
			lblError.setVisible(false);
		}
		
	}

	
	

}
