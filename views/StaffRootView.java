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

import com.northamptonmedicalclinic.app.controller.StaffRootController;
import com.northamptonmedicalclinic.app.models.StaffRootModel;

public class StaffRootView extends JFrame {

	private JPanel contentPane;
	private StaffRootModel model;

	

	/**
	 * Create the frame.
	 */
	public StaffRootView(final StaffRootController controller, StaffRootModel rootModel) {
		this.model = rootModel;
		setTitle("Northampton Medical Clinic");
		
		//Set size and center view.
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{100, 0, 100};
		gbl_contentPane.rowHeights = new int[]{100, 50, 50, 0, 0, 100};
		gbl_contentPane.columnWeights = new double[]{1, 0.0, 1};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnNew = new JButton("Record new treatment for patients");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNew = new GridBagConstraints();
		gbc_btnNew.fill = GridBagConstraints.BOTH;
		gbc_btnNew.insets = new Insets(0, 0, 5, 5);
		gbc_btnNew.gridx = 1;
		gbc_btnNew.gridy = 1;
		contentPane.add(btnNew, gbc_btnNew);
		
		JButton btnQueryDetailsOf = new JButton("Query details of patients");
		GridBagConstraints gbc_btnQueryDetailsOf = new GridBagConstraints();
		gbc_btnQueryDetailsOf.insets = new Insets(0, 0, 5, 5);
		gbc_btnQueryDetailsOf.fill = GridBagConstraints.BOTH;
		gbc_btnQueryDetailsOf.gridx = 1;
		gbc_btnQueryDetailsOf.gridy = 2;
		contentPane.add(btnQueryDetailsOf, gbc_btnQueryDetailsOf);
		
		JButton btnLogOut = new JButton("Log Out");
		GridBagConstraints gbc_btnLogOut = new GridBagConstraints();
		gbc_btnLogOut.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogOut.anchor = GridBagConstraints.SOUTH;
		gbc_btnLogOut.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogOut.gridx = 2;
		gbc_btnLogOut.gridy = 5;
		contentPane.add(btnLogOut, gbc_btnLogOut);
		
		JLabel userLabel = new JLabel();
		userLabel.setText("Logged in as: " + model.getStaff().getUsername() + " (" + model.getStaff().getStaffType() + ")");
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.insets = new Insets(0, 8, 8, 0);
		gbc_userLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_userLabel.anchor = GridBagConstraints.SOUTH;
		gbc_userLabel.gridwidth = 1;
		gbc_userLabel.gridheight = 1;
		gbc_userLabel.gridx = 0;
		gbc_userLabel.gridy = 5;
		getContentPane().add(userLabel, gbc_userLabel);
		
		final JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		bg.setIcon(new ImageIcon(StaffEditorView.class.getResource("/res/bg.jpg")));
		GridBagConstraints gbc_bg = new GridBagConstraints();
		gbc_bg.gridwidth = 3;
		gbc_bg.gridheight = 6;
		gbc_bg.gridx = 0;
		gbc_bg.gridy = 0;
		getContentPane().add(bg, gbc_bg);

		
		//Bind the buttons to the controller
		btnNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onClickNew();
				
			}
		});
		
		btnQueryDetailsOf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onClickViewTreatments();
				
			}
		});
		btnLogOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onClickLogOut();
				
			}
		});
	}

}
