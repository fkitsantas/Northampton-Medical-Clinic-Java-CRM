package com.northamptonmedicalclinic.app.controller;

import java.util.ArrayList;

import com.northamptonmedicalclinic.app.models.AdminRootModel;
import com.northamptonmedicalclinic.app.models.MedicalStaff;
import com.northamptonmedicalclinic.app.models.SelectionModel;
import com.northamptonmedicalclinic.app.service.DataRepository;
import com.northamptonmedicalclinic.app.views.AdminView;
import com.northamptonmedicalclinic.app.views.SelectionView;

public class StaffDeleteController extends BaseSelectionController {

	private SelectionModel<MedicalStaff> model;
	private SelectionView view;

	public StaffDeleteController(SelectionModel<MedicalStaff> model) {
		this.model = model;
		initialiseModel();
	}
	
	
	/**
	 * Initializes the model. It performs two actions:
	 * 1) A list of medical staff is stored in the model.
	 * 2) A String array is populated in the model which is used by the view to display the ComboBox choices. 
	 */
	private void initialiseModel() {
		final ArrayList<MedicalStaff> staffList = getStaffList();
		final ArrayList<String> nameList = getStaffNameList(staffList);
		
		if(staffList.isEmpty())
			model.setStatus("No staff has been added yet.");
		
		model.setList(staffList);
		model.setViewList(nameList);
		
	}
	
	/**
	 * Get the list of all medical staff.
	 */
	private ArrayList<MedicalStaff> getStaffList(){
		final ArrayList<MedicalStaff> staffList = DataRepository.getInstance().getStaffList();
		return staffList;
	}
	
	/**
	 * Get a list with just the names of all medical staff.
	 */
	private ArrayList<String> getStaffNameList(ArrayList<MedicalStaff> staffList){
		final ArrayList<String> nameList = new ArrayList<>(staffList.size());
		
		for (MedicalStaff medicalStaff : staffList) {
			nameList.add(medicalStaff.getName() + " - " + medicalStaff.getUsername());
		}
		return nameList;
	}
	
	
	
	/**
	 * Set the SelectionView reference.
	 */
	@Override
	public void setView(SelectionView view) {
		this.view = view;
	}
	

	/**
	 * Save the currently selected patient index in our model.
	 */
	@Override
	public void onSelected(int index) {
		model.setSelectionIndex(index);
	}

	
	/**
	 * Return to reception staff root menu.
	 */
	@Override
	public void onCancel() {
		AdminRootModel aModel = new AdminRootModel(model.getUser());
		AdminController controller = new AdminController(aModel);
		AdminView aView = new AdminView(controller, aModel);
		controller.setView(aView);
		aView.setVisible(true);
		view.dispose();
		
	}

	/**
	 * Delete the staff.
	 */
	@Override
	public void onActionPressed() {
		if(model.getList().isEmpty())
			return;
		model.deleteItem();
		DataRepository.getInstance().saveStaffList(model.getList());
		view.update();
	}
	
	
}
