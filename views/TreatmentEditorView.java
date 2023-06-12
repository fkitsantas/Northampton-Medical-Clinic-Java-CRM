package com.northamptonmedicalclinic.app.views;

import java.awt.Font;
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
import javax.swing.JTextArea;

import com.northamptonmedicalclinic.app.controller.TreatmentEditorController;
import com.northamptonmedicalclinic.app.models.Treatment;
import com.northamptonmedicalclinic.app.models.TreatmentEditorModel;

public class TreatmentEditorView extends JFrame {

	private JPanel contentPane;
	private TreatmentEditorModel model;
	private TreatmentEditorController controller;
	private JLabel status;



	/**
	 * Create the frame.
	 */
	public TreatmentEditorView(final TreatmentEditorModel model, final TreatmentEditorController controller) {
		setTitle("Northampton Medical Clinic");
		
		//Set size and center view.
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		this.model = model;
		this.controller = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 100, 0, 100};
		gbl_contentPane.rowHeights = new int[]{100, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblEnterTreatmentDetails = new JLabel("Enter Treatment Details");
		lblEnterTreatmentDetails.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblEnterTreatmentDetails = new GridBagConstraints();
		gbc_lblEnterTreatmentDetails.gridwidth = 4;
		gbc_lblEnterTreatmentDetails.insets = new Insets(50, 0, 5, 0);
		gbc_lblEnterTreatmentDetails.gridx = 0;
		gbc_lblEnterTreatmentDetails.gridy = 0;
		contentPane.add(lblEnterTreatmentDetails, gbc_lblEnterTreatmentDetails);
		
		JLabel lblPatientName = new JLabel("Patient Name");
		GridBagConstraints gbc_lblPatientName = new GridBagConstraints();
		gbc_lblPatientName.insets = new Insets(8, 8, 8, 8);
		gbc_lblPatientName.gridx = 0;
		gbc_lblPatientName.gridy = 1;
		contentPane.add(lblPatientName, gbc_lblPatientName);
		
		JLabel patientName = new JLabel(model.getPatientName());
		GridBagConstraints gbc_patientName = new GridBagConstraints();
		gbc_patientName.fill = GridBagConstraints.HORIZONTAL;
		gbc_patientName.insets = new Insets(8, 8, 8, 8);
		gbc_patientName.gridx = 1;
		gbc_patientName.gridy = 1;
		contentPane.add(patientName, gbc_patientName);
		
		JLabel lblAppointment = new JLabel("Appointment");
		GridBagConstraints gbc_lblAppointment = new GridBagConstraints();
		gbc_lblAppointment.insets = new Insets(8, 8, 8, 8);
		gbc_lblAppointment.gridx = 2;
		gbc_lblAppointment.gridy = 1;
		contentPane.add(lblAppointment, gbc_lblAppointment);
		
		JLabel aptTime = new JLabel(model.getAppointmentTiming());
		GridBagConstraints gbc_aptTime = new GridBagConstraints();
		gbc_aptTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_aptTime.insets = new Insets(8, 8, 8, 8);
		gbc_aptTime.gridx = 3;
		gbc_aptTime.gridy = 1;
		contentPane.add(aptTime, gbc_aptTime);
		
		JLabel lblTreatment = new JLabel("Treatment");
		GridBagConstraints gbc_lblTreatment = new GridBagConstraints();
		gbc_lblTreatment.insets = new Insets(0, 0, 5, 5);
		gbc_lblTreatment.gridx = 0;
		gbc_lblTreatment.gridy = 2;
		contentPane.add(lblTreatment, gbc_lblTreatment);
		
		final JTextArea txtTreatment = new JTextArea(model.getDetails().getTreatment());
		GridBagConstraints gbc_txtTreatment = new GridBagConstraints();
		gbc_txtTreatment.gridwidth = 3;
		gbc_txtTreatment.insets = new Insets(8, 8, 8, 8);
		gbc_txtTreatment.fill = GridBagConstraints.BOTH;
		gbc_txtTreatment.gridx = 1;
		gbc_txtTreatment.gridy = 2;
		contentPane.add(txtTreatment, gbc_txtTreatment);
		
		status = new JLabel("");
		GridBagConstraints gbc_status = new GridBagConstraints();
		gbc_status.fill = GridBagConstraints.HORIZONTAL;
		gbc_status.gridwidth = 3;
		gbc_status.insets = new Insets(8, 8, 8, 8);
		gbc_status.gridx = 1;
		gbc_status.gridy = 3;
		contentPane.add(status, gbc_status);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 4;
		contentPane.add(btnCancel, gbc_btnCancel);
		
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 4;
		contentPane.add(btnSave, gbc_btnSave);
		
		final JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		bg.setIcon(new ImageIcon(TreatmentEditorView.class.getResource("/res/bg.jpg")));
		GridBagConstraints gbc_bg = new GridBagConstraints();
		gbc_bg.gridwidth = 4;
		gbc_bg.gridheight = 5;
		gbc_bg.gridx = 0;
		gbc_bg.gridy = 0;
		getContentPane().add(bg, gbc_bg);

		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onCancel();
				
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Treatment treatment = model.getDetails();
				treatment.setTreatment(txtTreatment.getText());
				controller.onSave(treatment);
			}
		});
	}

	public void update() {
		if(model.isError()) {
			status.setText(model.getErrorMessage());
			status.setVisible(true);
		} else {
			status.setVisible(false);
		}
	}
}
