package com.northamptonmedicalclinic.app.views;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.northamptonmedicalclinic.app.controller.AdminController;
import com.northamptonmedicalclinic.app.models.AdminRootModel;

public class AdminView extends JFrame {

	private JPanel contentPane;
	private final int HEIGHT = 40;
	private AdminController controller;
	private AdminRootModel model;
	
	
	
	/**
	 * Create the frame.
	 */
	public AdminView(final AdminController adminController, AdminRootModel aModel) {
		this.model = aModel;
		setTitle("Northampton Medical Clinic");

		//Set size and center view.
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.controller = adminController;
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{100, 0, 100};
		gbl_contentPane.rowHeights = new int[] {100, 40, 40, 40, 30, 40, 40, 40, 30, 40, 40};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnAddMedicalStaff = new JButton("Add Medical Staff");
		GridBagConstraints gbc_btnAddMedicalStaff = new GridBagConstraints();
		gbc_btnAddMedicalStaff.fill = GridBagConstraints.BOTH;
		gbc_btnAddMedicalStaff.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddMedicalStaff.gridx = 1;
		gbc_btnAddMedicalStaff.gridy = 1;
		contentPane.add(btnAddMedicalStaff, gbc_btnAddMedicalStaff);
		
		//Bind the button to the controller
		btnAddMedicalStaff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onClickAddStaff();
			}
		});
		
		JButton btnEditMedicalStaff = new JButton("Edit Medical Staff");
		GridBagConstraints gbc_btnEditMedicalStaff = new GridBagConstraints();
		gbc_btnEditMedicalStaff.fill = GridBagConstraints.BOTH;
		gbc_btnEditMedicalStaff.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditMedicalStaff.gridx = 1;
		gbc_btnEditMedicalStaff.gridy = 2;
		contentPane.add(btnEditMedicalStaff, gbc_btnEditMedicalStaff);

		//Bind the button to the controller
		btnEditMedicalStaff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onClickEditStaff();
				
			}
		});
		
		
		JButton btnDeleteMedicalStaff = new JButton("Delete Medical Staff");
		GridBagConstraints gbc_btnDeleteMedicalStaff = new GridBagConstraints();
		gbc_btnDeleteMedicalStaff.fill = GridBagConstraints.BOTH;
		gbc_btnDeleteMedicalStaff.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteMedicalStaff.gridx = 1;
		gbc_btnDeleteMedicalStaff.gridy = 3;
		contentPane.add(btnDeleteMedicalStaff, gbc_btnDeleteMedicalStaff);
		
		//Bind the button to the controller
		btnDeleteMedicalStaff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onClickDeleteStaff();
				
			}
		});
		
		JButton btnAddPatient = new JButton("Add Patient");
		GridBagConstraints gbc_btnAddPatient = new GridBagConstraints();
		gbc_btnAddPatient.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddPatient.fill = GridBagConstraints.BOTH;
		gbc_btnAddPatient.gridx = 1;
		gbc_btnAddPatient.gridy = 5;
		contentPane.add(btnAddPatient, gbc_btnAddPatient);
		
		//Bind the button to the controller
		btnAddPatient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onClickAddPatient();
				
			}
		});
		
		JButton btnEditPatient = new JButton("Edit Patient");
		GridBagConstraints gbc_btnEditPatient = new GridBagConstraints();
		gbc_btnEditPatient.fill = GridBagConstraints.BOTH;
		gbc_btnEditPatient.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditPatient.gridx = 1;
		gbc_btnEditPatient.gridy = 6;
		contentPane.add(btnEditPatient, gbc_btnEditPatient);
		
		//Bind the button to the controller
		btnEditPatient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onClickEditPatient();
				
			}
		});
		
		JButton btnDeletePatient = new JButton("Delete Patient");
		GridBagConstraints gbc_btnDeletePatient = new GridBagConstraints();
		gbc_btnDeletePatient.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeletePatient.fill = GridBagConstraints.BOTH;
		gbc_btnDeletePatient.gridx = 1;
		gbc_btnDeletePatient.gridy = 7;
		contentPane.add(btnDeletePatient, gbc_btnDeletePatient);

		//Bind the button to the controller
		btnDeletePatient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onClickDeletePatient();
				
			}
		});
		
		JButton btnNewAppointment = new JButton("New Appointment");
		GridBagConstraints gbc_btnNewAppointment = new GridBagConstraints();
		gbc_btnNewAppointment.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewAppointment.fill = GridBagConstraints.BOTH;
		gbc_btnNewAppointment.gridx = 1;
		gbc_btnNewAppointment.gridy = 9;
		contentPane.add(btnNewAppointment, gbc_btnNewAppointment);
		
		//Bind the button to the controller
		btnNewAppointment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onClickAddAppointment();
				
			}
		});
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_btnLogOut = new GridBagConstraints();
		gbc_btnLogOut.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnLogOut.gridx = 2;
		gbc_btnLogOut.gridy = 10;
		contentPane.add(btnLogOut, gbc_btnLogOut);
		
		//Bind the button to the controller
		btnLogOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.logOut();
			}
		});
		
		JLabel userLabel = new JLabel();
		userLabel.setText("Logged in as: " + model.getUser().getUsername() + " (" + model.getUser().getStaffType() + ")");
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.insets = new Insets(0, 8, 8, 0);
		gbc_userLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_userLabel.anchor = GridBagConstraints.SOUTH;
		gbc_userLabel.gridwidth = 1;
		gbc_userLabel.gridheight = 1;
		gbc_userLabel.gridx = 0;
		gbc_userLabel.gridy = 10;
		getContentPane().add(userLabel, gbc_userLabel);
		
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		bg.setIcon(new ImageIcon(LoginView.class.getResource("/res/bg.jpg")));
		GridBagConstraints gbc_bg = new GridBagConstraints();
		gbc_bg.gridwidth = 3;
		gbc_bg.gridheight = 11;
		gbc_bg.gridx = 0;
		gbc_bg.gridy = 0;
		getContentPane().add(bg, gbc_bg);
		
		
		
	}
}
