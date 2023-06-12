package com.northamptonmedicalclinic.app.views;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.northamptonmedicalclinic.app.controller.BaseSelectionController;
import com.northamptonmedicalclinic.app.models.SelectionModel;

public class SelectionView extends JFrame {

	private JPanel contentPane;
	private JButton action;
	private JLabel status;
	private JComboBox list;
	private JLabel title;
	private JButton cancel;
	private JLabel select;
	
	private SelectionModel<?> model;
	private BaseSelectionController controller;
		
	public SelectionView(SelectionModel<?> model, BaseSelectionController mController) {
		setTitle("Northampton Medical Clinic");

		//Set size and center view.
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		this.model = model;
		this.controller = mController;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 100};
		gbl_contentPane.rowHeights = new int[]{100, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1};
		contentPane.setLayout(gbl_contentPane);
		
		title = new JLabel(model.getTitle());
		title.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(50, 0, 0, 0);
		gbc_title.gridx = 1;
		gbc_title.gridy = 0;
		contentPane.add(title, gbc_title);
		
		select = new JLabel(model.getSelectionLablel());
		GridBagConstraints gbc_select = new GridBagConstraints();
		gbc_select.insets = new Insets(0, 0, 5, 5);
		gbc_select.anchor = GridBagConstraints.EAST;
		gbc_select.gridx = 0;
		gbc_select.gridy = 1;
		contentPane.add(select, gbc_select);
		
		list = new JComboBox(model.getViewList().toArray());
		list.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				controller.onSelected(list.getSelectedIndex());
				
			}
		});
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.HORIZONTAL;
		gbc_list.gridx = 1;
		gbc_list.gridy = 1;
		contentPane.add(list, gbc_list);
		
		action = new JButton(model.getActionButtonLabel());
		GridBagConstraints gbc_action = new GridBagConstraints();
		gbc_action.fill = GridBagConstraints.HORIZONTAL;
		gbc_action.insets = new Insets(0, 0, 5, 0);
		gbc_action.gridx = 2;
		gbc_action.gridy = 1;
		contentPane.add(action, gbc_action);
		
		status = new JLabel("");
		status.setVisible(false);
		GridBagConstraints gbc_status = new GridBagConstraints();
		gbc_status.fill = GridBagConstraints.VERTICAL;
		gbc_status.insets = new Insets(0, 0, 5, 5);
		gbc_status.gridx = 1;
		gbc_status.gridy = 3;
		contentPane.add(status, gbc_status);
		
		cancel = new JButton("Cancel");
		cancel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_cancel = new GridBagConstraints();
		gbc_cancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_cancel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_cancel.gridx = 2;
		gbc_cancel.gridy = 4;
		contentPane.add(cancel, gbc_cancel);
		
		final JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		bg.setIcon(new ImageIcon(LoginView.class.getResource("/res/bg.jpg")));
		GridBagConstraints gbc_bg = new GridBagConstraints();
		gbc_bg.gridwidth = 3;
		gbc_bg.gridheight = 5;
		gbc_bg.gridx = 0;
		gbc_bg.gridy = 0;
		getContentPane().add(bg, gbc_bg);

		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onCancel();
			}
		});
		
		action.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onActionPressed();
			}
		});
		updateStatus();
	}
	
	public void update() {
		list.setModel(new DefaultComboBoxModel<>(model.getViewList().toArray()));
		
	}
	
	private void updateStatus() {
		if(model.getStatus() != null) {
			status.setText(model.getStatus());
			status.setVisible(true);
		} else {
			status.setVisible(false);
		}
	}

}
