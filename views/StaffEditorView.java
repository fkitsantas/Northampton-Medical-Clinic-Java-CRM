package com.northamptonmedicalclinic.app.views;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.northamptonmedicalclinic.app.controller.StaffEditorController;
import com.northamptonmedicalclinic.app.models.MedicalStaff;
import com.northamptonmedicalclinic.app.models.StaffEditorModel;

public class StaffEditorView extends JFrame {
	
	private JTextField username;
	private JTextField txtName;
	private JPasswordField password;
	private JTextField regNo;
	private JPanel contentPane;
	private JButton btnSave;
	private JButton btnCancel;
	private JCheckBox chckbxCanPrescribe;
	private final JComboBox<String> gender;
	
	private final Insets default_inset = new Insets(0, 0,8, 8);
	private JTextField qualifyDate;
	private JTextField regDate;
	private int HEIGHT = 50;
	private JComboBox<String> staffType;
	
	private JTextField grade;
	private JLabel title;
	
	private StaffEditorController controller;
	private StaffEditorModel model;
	private JLabel error;
	private JLabel bg;
	
	
	public StaffEditorView(final StaffEditorController controller, StaffEditorModel model) {
		setTitle("Northampton Medical Clinic");
		
		//Set size and center view.
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		this.controller = controller;
		this.model = model;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		GridBagLayout gbl_pnlStaffEditor = new GridBagLayout();
		gbl_pnlStaffEditor.columnWidths = new int[]{0, 100, 0, 100};
		gbl_pnlStaffEditor.rowHeights = new int[] {100, 50, 50, 50, 50, 50, 50, 50, 48};
		gbl_pnlStaffEditor.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0};
		gbl_pnlStaffEditor.rowWeights = new double[]{0,0, 0, 0,0,0,0,0, 1};
		contentPane.setLayout(gbl_pnlStaffEditor);
		
		title = new JLabel(model.getTitle());
		title.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 4;
		gbc_title.insets = new Insets(50, 0, 5, 0);
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		contentPane.add(title, gbc_title);
		
		JLabel lblUserName = new JLabel("User Name");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.fill = GridBagConstraints.BOTH;
		gbc_lblUserName.anchor = GridBagConstraints.EAST;
		gbc_lblUserName.insets = default_inset;
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 1;
		contentPane.add(lblUserName, gbc_lblUserName);
		
		username = new JTextField();
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.insets = default_inset;
		gbc_username.fill = GridBagConstraints.HORIZONTAL;
		gbc_username.gridx = 1;
		gbc_username.gridy = 1;
		contentPane.add(username, gbc_username);
		
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = default_inset;
		gbc_lblPassword.gridx = 2;
		gbc_lblPassword.gridy = 1;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		password = new JPasswordField();
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.insets = default_inset;
		gbc_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_password.gridx = 3;
		gbc_password.gridy = 1;
		contentPane.add(password, gbc_password);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.BOTH;
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = default_inset;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		contentPane.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = default_inset;
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 2;
		contentPane.add(txtName, gbc_txtName);
		
		JLabel lblGender = new JLabel("Gender");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.EAST;
		gbc_lblGender.fill = GridBagConstraints.BOTH;
		gbc_lblGender.insets = default_inset;
		gbc_lblGender.gridx = 2;
		gbc_lblGender.gridy = 2;
		contentPane.add(lblGender, gbc_lblGender);
		
		gender = new JComboBox<>();
		gender.setModel(new DefaultComboBoxModel<String>(new String[] {"Male", "Female"}));
		GridBagConstraints gbc_gender = new GridBagConstraints();
		gbc_gender.insets = new Insets(0, 0, 5, 0);
		gbc_gender.fill = GridBagConstraints.HORIZONTAL;
		gbc_gender.gridx = 3;
		gbc_gender.gridy = 2;
		contentPane.add(gender, gbc_gender);
		
		JLabel lblRegistrationNumber = new JLabel("Registration Number");
		lblRegistrationNumber.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblRegistrationNumber = new GridBagConstraints();
		gbc_lblRegistrationNumber.fill = GridBagConstraints.BOTH;
		gbc_lblRegistrationNumber.anchor = GridBagConstraints.EAST;
		gbc_lblRegistrationNumber.insets = new Insets(0, 0, 8, 8);
		gbc_lblRegistrationNumber.gridx = 0;
		gbc_lblRegistrationNumber.gridy = 3;
		contentPane.add(lblRegistrationNumber, gbc_lblRegistrationNumber);
		
		regNo = new JTextField();
		GridBagConstraints gbc_regNo = new GridBagConstraints();
		gbc_regNo.insets = default_inset;
		gbc_regNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_regNo.gridx = 1;
		gbc_regNo.gridy = 3;
		contentPane.add(regNo, gbc_regNo);
		
		JLabel lblRegistrationDate = new JLabel("Registration Date");
		GridBagConstraints gbc_lblRegistrationDate = new GridBagConstraints();
		gbc_lblRegistrationDate.fill = GridBagConstraints.VERTICAL;
		gbc_lblRegistrationDate.anchor = GridBagConstraints.EAST;
		gbc_lblRegistrationDate.insets = new Insets(0, 0, 8, 8);
		gbc_lblRegistrationDate.gridx = 2;
		gbc_lblRegistrationDate.gridy = 3;
		contentPane.add(lblRegistrationDate, gbc_lblRegistrationDate);
		
		regDate = new JTextField();
		GridBagConstraints gbc_regDate = new GridBagConstraints();
		gbc_regDate.insets = new Insets(0, 0, 5, 0);
		gbc_regDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_regDate.gridx = 3;
		gbc_regDate.gridy = 3;
		contentPane.add(regDate, gbc_regDate);
		
		
		
		JLabel lblQualifyDate = new JLabel("Qualification Date");
		GridBagConstraints gbc_lblQualifyDate = new GridBagConstraints();
		gbc_lblQualifyDate.fill = GridBagConstraints.BOTH;
		gbc_lblQualifyDate.insets = default_inset;
		gbc_lblQualifyDate.gridx = 0;
		gbc_lblQualifyDate.gridy = 4;
		contentPane.add(lblQualifyDate, gbc_lblQualifyDate);
		
		qualifyDate = new JTextField();
		GridBagConstraints gbc_qualifyDate = new GridBagConstraints();
		gbc_qualifyDate.insets = new Insets(0, 0, 5, 5);
		gbc_qualifyDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_qualifyDate.gridx = 1;
		gbc_qualifyDate.gridy = 4;
		contentPane.add(qualifyDate, gbc_qualifyDate);
		
		
		
		JLabel lblStaffType = new JLabel("Staff Type");
		lblStaffType.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblStaffType = new GridBagConstraints();
		gbc_lblStaffType.fill = GridBagConstraints.BOTH;
		gbc_lblStaffType.anchor = GridBagConstraints.WEST;
		gbc_lblStaffType.insets = default_inset;
		gbc_lblStaffType.gridx = 0;
		gbc_lblStaffType.gridy = 5;
		contentPane.add(lblStaffType, gbc_lblStaffType);
		
		staffType = new JComboBox<>();
		staffType.setModel(new DefaultComboBoxModel<String>(new String[] {"Doctor", "Nurse"}));
		GridBagConstraints gbc_staffType = new GridBagConstraints();
		gbc_staffType.insets = new Insets(0, 0, 5, 5);
		gbc_staffType.fill = GridBagConstraints.HORIZONTAL;
		gbc_staffType.gridx = 1;
		gbc_staffType.gridy = 5;
		contentPane.add(staffType, gbc_staffType);
		
		
		JLabel lblGrade = new JLabel("Grade");
		GridBagConstraints gbc_lblGrade = new GridBagConstraints();
		gbc_lblGrade.anchor = GridBagConstraints.WEST;
		gbc_lblGrade.fill = GridBagConstraints.VERTICAL;
		gbc_lblGrade.insets = default_inset;
		gbc_lblGrade.gridx = 0;
		gbc_lblGrade.gridy = 6;
		contentPane.add(lblGrade, gbc_lblGrade);
		
		grade = new JTextField();
		GridBagConstraints gbc_grade = new GridBagConstraints();
		gbc_grade.insets = new Insets(0, 0, 5, 5);
		gbc_grade.fill = GridBagConstraints.HORIZONTAL;
		gbc_grade.gridx = 1;
		gbc_grade.gridy = 6;
		contentPane.add(grade, gbc_grade);
		
		chckbxCanPrescribe = new JCheckBox("Can Prescribe?");
		chckbxCanPrescribe.setSelected(true);
		GridBagConstraints gbc_chckbxCanPrescribe = new GridBagConstraints();
		gbc_chckbxCanPrescribe.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxCanPrescribe.insets = default_inset;
		gbc_chckbxCanPrescribe.gridx = 3;
		gbc_chckbxCanPrescribe.gridy = 6;
		contentPane.add(chckbxCanPrescribe, gbc_chckbxCanPrescribe);
		
		error = new JLabel("New label");
		error.setForeground(Color.RED);
		GridBagConstraints gbc_error = new GridBagConstraints();
		gbc_error.fill = GridBagConstraints.BOTH;
		gbc_error.gridwidth = 4;
		gbc_error.insets = new Insets(8, 8, 8, 8);
		gbc_error.gridx = 0;
		gbc_error.gridy = 7;
		contentPane.add(error, gbc_error);
		
		
		btnCancel = new JButton("Cancel");
		btnCancel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.SOUTH;
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 8;
		contentPane.add(btnCancel, gbc_btnCancel);
		
		btnSave = new JButton(model.getButtonText());
		btnSave.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 8;
		contentPane.add(btnSave, gbc_btnSave);
		

		bg = new JLabel("");
		bg.setIcon(new ImageIcon(StaffEditorView.class.getResource("/res/bg.jpg")));
		GridBagConstraints gbc_bg = new GridBagConstraints();
		gbc_bg.gridwidth = 4;
		gbc_bg.gridheight = 9;
		gbc_bg.insets = new Insets(0, 0, 0, 5);
		gbc_bg.gridx = 0;
		gbc_bg.gridy = 0;
		contentPane.add(bg, gbc_bg);
		
		//Listen for staff type selection. We disable nurse only fields if "Doctor" is selected.
		staffType.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
			boolean isNurse = e.getItem().toString().equals("Nurse");
			setNurseOnlyFieldActive(isNurse);
			
			}
		});

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final MedicalStaff staff = new MedicalStaff(username.getText(), password.getText());
				staff.setStaffType(staffType.getSelectedItem().toString().equals("Doctor") ? 1:2);
				staff.setName(txtName.getText());
				staff.setGender(gender.getSelectedItem().toString());
				staff.setQualificationDate(qualifyDate.getText());
				staff.setRegistrationDate(regDate.getText());
				staff.setRegistrationNumber(regNo.getText());
				staff.setCanPresribe(staff.isDoctor() ? true:chckbxCanPrescribe.isSelected());
				staff.setGrade(grade.getText());
				controller.onSave(staff);				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onCancel();
				
			}
		});
		updateForm(model.getStaffDetails());
		update();
	}
	
	
	public void update() {
		if(model.isError()) {
			error.setText(model.getErrorMessage());
			error.setVisible(true);
		} else {
			error.setVisible(false);	
		}
	}
	
	private void updateForm(MedicalStaff staff) {
		username.setText(staff.getUsername());
		txtName.setText(staff.getName());
		gender.setSelectedItem(staff.getGender());
		regDate.setText(staff.getRegistrationDate());
		regNo.setText(staff.getRegistrationNumber());
		qualifyDate.setText(staff.getQualificationDate());
		staffType.setSelectedItem(staff.getStaffType());
		chckbxCanPrescribe.setSelected(staff.getCanPrescribe());
		grade.setText(staff.getGrade());
		setNurseOnlyFieldActive(staff.isNurse());
	}
	
	private void setNurseOnlyFieldActive(boolean isActive) {
		grade.setEditable(isActive);
		chckbxCanPrescribe.setEnabled(isActive);
		if(!isActive) {
			grade.setText("");
			chckbxCanPrescribe.setSelected(true);
		}
	}

}
