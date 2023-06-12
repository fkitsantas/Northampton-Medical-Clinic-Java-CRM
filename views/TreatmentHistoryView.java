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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.northamptonmedicalclinic.app.controller.NewTreatmentSelectionController;
import com.northamptonmedicalclinic.app.models.TreatmentHistoryModel;
import com.northamptonmedicalclinic.app.models.TreatmentSelectionModel;

public class TreatmentHistoryView extends JFrame {

	private JPanel contentPane;
	private TreatmentHistoryModel model;



	/**
	 * Create the frame.
	 */
	public TreatmentHistoryView(final TreatmentHistoryModel model) {
		setTitle("Northampton Medical Clinic");
		
		//Set size and center view.
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		this.model = model;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 100, 0, 100};
		gbl_contentPane.rowHeights = new int[]{100, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1, 1.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblname = new JLabel(model.getPatientName());
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblname = new GridBagConstraints();
		gbc_lblname.gridwidth = 4;
		gbc_lblname.insets = new Insets(50, 0, 5, 0);
		gbc_lblname.gridx = 0;
		gbc_lblname.gridy = 0;
		contentPane.add(lblname, gbc_lblname);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		final JTextArea txtTreatment = new JTextArea(model.getTreatmentDetails());
		scrollPane.setViewportView(txtTreatment);
		txtTreatment.setEditable(false);
		
		
		JButton btnSave = new JButton("Back");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 4;
		contentPane.add(btnSave, gbc_btnSave);
		
		final JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		bg.setIcon(new ImageIcon(TreatmentHistoryView.class.getResource("/res/bg.jpg")));
		GridBagConstraints gbc_bg = new GridBagConstraints();
		gbc_bg.gridwidth = 4;
		gbc_bg.gridheight = 5;
		gbc_bg.gridx = 0;
		gbc_bg.gridy = 0;
		getContentPane().add(bg, gbc_bg);
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final TreatmentSelectionModel tModel = 
						new TreatmentSelectionModel("Treatment History", "View", "Select Patient");
				tModel.setUser(model.getUser());
				final NewTreatmentSelectionController controller = new NewTreatmentSelectionController(tModel);
				final SelectionView sView = new SelectionView(tModel, controller);
				controller.setView(sView);
				sView.setVisible(true);
				dispose();
			}
		});
	}

}
