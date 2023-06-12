package com.northamptonmedicalclinic.app.views;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.northamptonmedicalclinic.app.controller.PatientEditorController;
import com.northamptonmedicalclinic.app.models.Patient;
import com.northamptonmedicalclinic.app.models.PatientEditorModel;

public class PatientEditorView extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField birthDate;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private PatientEditorController controller;
	private PatientEditorModel model;
	private JLabel error;
	private JTextArea txtAddress;
	

	/**
	 * Create the frame.
	 */
	public PatientEditorView(PatientEditorController mController, PatientEditorModel mModel) {
		setTitle("Northampton Medical Clinic");
		
		//Set size and center view.
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		this.controller = mController;
		this.model = mModel;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{100, 0, 0, 0, 100, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTitle = new JLabel(model.getTitle());
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTitle.gridwidth = 4;
		gbc_lblTitle.insets = new Insets(50, 0, 0, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		JLabel lblId = new JLabel("ID");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblId.insets = new Insets(8, 8, 8, 8);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 1;
		contentPane.add(lblId, gbc_lblId);
		
		id = new JTextField();
		GridBagConstraints gbc_id = new GridBagConstraints();
		gbc_id.insets = new Insets(8, 8, 8, 8);
		gbc_id.fill = GridBagConstraints.HORIZONTAL;
		gbc_id.gridx = 1;
		gbc_id.gridy = 1;
		contentPane.add(id, gbc_id);
		id.setColumns(10);
		
		JLabel lblBirthDate = new JLabel("Birth Date");
		GridBagConstraints gbc_lblBirthDate = new GridBagConstraints();
		gbc_lblBirthDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBirthDate.insets = new Insets(8, 8, 8, 8);
		gbc_lblBirthDate.gridx = 2;
		gbc_lblBirthDate.gridy = 1;
		contentPane.add(lblBirthDate, gbc_lblBirthDate);
		
		birthDate = new JTextField();
		birthDate.setColumns(10);
		GridBagConstraints gbc_birthDate = new GridBagConstraints();
		gbc_birthDate.insets = new Insets(8, 8, 8, 8);
		gbc_birthDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_birthDate.gridx = 3;
		gbc_birthDate.gridy = 1;
		contentPane.add(birthDate, gbc_birthDate);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblName.insets = new Insets(8, 8, 8, 8);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		contentPane.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.gridwidth = 3;
		gbc_txtName.insets = new Insets(8, 8, 8, 8);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 2;
		contentPane.add(txtName, gbc_txtName);
		
		JLabel lblPhone = new JLabel("Phone");
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPhone.insets = new Insets(8, 8, 8, 8);
		gbc_lblPhone.gridx = 0;
		gbc_lblPhone.gridy = 3;
		contentPane.add(lblPhone, gbc_lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		GridBagConstraints gbc_txtPhone = new GridBagConstraints();
		gbc_txtPhone.insets = new Insets(8, 8, 8, 8);
		gbc_txtPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhone.gridx = 1;
		gbc_txtPhone.gridy = 3;
		contentPane.add(txtPhone, gbc_txtPhone);
		
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmail.insets = new Insets(8, 8, 8, 8);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 3;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(8, 8, 8, 8);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 3;
		gbc_txtEmail.gridy = 3;
		contentPane.add(txtEmail, gbc_txtEmail);
		
		JLabel lblAddress = new JLabel("Address");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAddress.insets = new Insets(8, 8, 8, 8);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 4;
		contentPane.add(lblAddress, gbc_lblAddress);
		
		txtAddress = new JTextArea();
		GridBagConstraints gbc_txtAddress = new GridBagConstraints();
		gbc_txtAddress.gridwidth = 3;
		gbc_txtAddress.insets = new Insets(8, 8, 8, 8);
		gbc_txtAddress.fill = GridBagConstraints.BOTH;
		gbc_txtAddress.gridx = 1;
		gbc_txtAddress.gridy = 4;
		contentPane.add(txtAddress, gbc_txtAddress);
		
		error = new JLabel("dd");
		error.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_error = new GridBagConstraints();
		gbc_error.fill = GridBagConstraints.BOTH;
		gbc_error.gridwidth = 3;
		gbc_error.insets = new Insets(0, 0, 5, 5);
		gbc_error.gridx = 1;
		gbc_error.gridy = 5;
		contentPane.add(error, gbc_error);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.insets = new Insets(8, 8, 8, 8);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 6;
		contentPane.add(btnCancel, gbc_btnCancel);
		
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 6;
		contentPane.add(btnSave, gbc_btnSave);
		
		final JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		bg.setIcon(new ImageIcon(PatientEditorView.class.getResource("/res/bg.jpg")));
		GridBagConstraints gbc_bg = new GridBagConstraints();
		gbc_bg.gridwidth = 4;
		gbc_bg.gridheight = 7;
		gbc_bg.gridx = 0;
		gbc_bg.gridy = 0;
		getContentPane().add(bg, gbc_bg);

		
		//Bind the buttons to the controller
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final Patient patient = new Patient(id.getText(), txtName.getText(),
						txtAddress.getText(), txtPhone.getText(), txtEmail.getText(), birthDate.getText());
				controller.onSave(patient);				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onCancel();
			}
		});
		
		updateForm(model.getDetails());
		update();
		
	}

	//Checks if there is an error message. If yes, its activated and displayed.
	public void update() {
		if(model.isError()) {
			error.setText(model.getErrorMessage());
			error.setVisible(true);
		} else {
			error.setVisible(false);	
		}
	}
	
	//Populate the form fields.
	private void updateForm(Patient patient) {
		id.setText(patient.getId());
		txtName.setText(patient.getName());
		birthDate.setText(patient.getBirthDate());
		txtEmail.setText(patient.getEmail());
		txtPhone.setText(patient.getPhone());
		txtAddress.setText(patient.getAddress());
	}
}
