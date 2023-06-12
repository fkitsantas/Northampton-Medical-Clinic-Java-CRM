package com.northamptonmedicalclinic.app.views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.northamptonmedicalclinic.app.controller.AppointmentController;
import com.northamptonmedicalclinic.app.models.Appointment;
import com.northamptonmedicalclinic.app.models.AppointmentModel;

public class AppointmentView extends JFrame {

	private JPanel contentPane;
	private JTextField apptDate;
	private AppointmentModel model;
	private AppointmentController controller;

	
	
	
	/**
	 * Create the frame.
	 */
	public AppointmentView(AppointmentController mController, AppointmentModel mModel) {
		setTitle("Northampton Medical Clinic");
		
		//Set size and center view.
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		this.model = mModel;
		this.controller = mController;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[] {100, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblAddAppointment = new JLabel("Add Appointment");
		lblAddAppointment.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAddAppointment.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblAddAppointment = new GridBagConstraints();
		gbc_lblAddAppointment.gridwidth = 4;
		gbc_lblAddAppointment.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAddAppointment.insets = new Insets(50, 0, 12, 5);
		gbc_lblAddAppointment.gridx = 0;
		gbc_lblAddAppointment.gridy = 0;
		contentPane.add(lblAddAppointment, gbc_lblAddAppointment);
		
		JLabel lblNewLabel = new JLabel("Staff");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JComboBox cmbStaff = new JComboBox(model.getStaffNames());
		GridBagConstraints gbc_cmbStaff = new GridBagConstraints();
		gbc_cmbStaff.insets = new Insets(0, 0, 5, 5);
		gbc_cmbStaff.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbStaff.gridx = 1;
		gbc_cmbStaff.gridy = 1;
		contentPane.add(cmbStaff, gbc_cmbStaff);
		
		JLabel lblPatient = new JLabel("Patient");
		GridBagConstraints gbc_lblPatient = new GridBagConstraints();
		gbc_lblPatient.anchor = GridBagConstraints.EAST;
		gbc_lblPatient.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatient.gridx = 2;
		gbc_lblPatient.gridy = 1;
		contentPane.add(lblPatient, gbc_lblPatient);
		
		final JComboBox cmbPatients = new JComboBox(model.getPatients());
		GridBagConstraints gbc_cmbPatients = new GridBagConstraints();
		gbc_cmbPatients.insets = new Insets(0, 0, 5, 0);
		gbc_cmbPatients.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbPatients.gridx = 3;
		gbc_cmbPatients.gridy = 1;
		contentPane.add(cmbPatients, gbc_cmbPatients);
		
		JLabel lblAppointmentDate = new JLabel("Date");
		GridBagConstraints gbc_lblAppointmentDate = new GridBagConstraints();
		gbc_lblAppointmentDate.anchor = GridBagConstraints.EAST;
		gbc_lblAppointmentDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblAppointmentDate.gridx = 0;
		gbc_lblAppointmentDate.gridy = 2;
		contentPane.add(lblAppointmentDate, gbc_lblAppointmentDate);
		
		apptDate = new JTextField();
		GridBagConstraints gbc_apptDate = new GridBagConstraints();
		gbc_apptDate.insets = new Insets(0, 0, 5, 5);
		gbc_apptDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_apptDate.gridx = 1;
		gbc_apptDate.gridy = 2;
		contentPane.add(apptDate, gbc_apptDate);
		apptDate.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 4;
		contentPane.add(btnCancel, gbc_btnCancel);
		
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 4;
		contentPane.add(btnSave, gbc_btnSave);
		
		JLabel bg = new JLabel("");
		bg.setIcon(new ImageIcon(AppointmentView.class.getResource("/res/bg.jpg")));
		GridBagConstraints gbc_bg = new GridBagConstraints();
		gbc_bg.gridwidth = 4;
		gbc_bg.gridheight = 5;
		gbc_bg.gridx = 0;
		gbc_bg.gridy = 0;
		contentPane.add(bg, gbc_bg);
		
		//Bind the buttons to the controller
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final Appointment appointment = new Appointment();
				appointment.setAppointmentTime(apptDate.getText());
				appointment.setDoctorID(model.getStaffID(cmbPatients.getSelectedIndex()));
				appointment.setPatientID(model.getPatientID(cmbPatients.getSelectedIndex()));
				appointment.setPatientName(model.getPatientName(cmbPatients.getSelectedIndex()));
				controller.onSave(appointment);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controller.onCancel();
			}
		});
		
		
	}

}
