package com.northamptonmedicalclinic.app.controller;

import java.util.ArrayList;

import com.northamptonmedicalclinic.app.models.AdminRootModel;
import com.northamptonmedicalclinic.app.models.LoginModel;
import com.northamptonmedicalclinic.app.models.MedicalStaff;
import com.northamptonmedicalclinic.app.models.StaffRootModel;
import com.northamptonmedicalclinic.app.models.User;
import com.northamptonmedicalclinic.app.service.DataRepository;
import com.northamptonmedicalclinic.app.views.AdminView;
import com.northamptonmedicalclinic.app.views.LoginView;
import com.northamptonmedicalclinic.app.views.StaffRootView;


/**
 * This class handles the authentication. CUrrently we have hardcoded the reception staff
 * with the username/password of admin/123 . 
 */
public class LoginController {
	
	private LoginModel model;
	private LoginView view;
	
	// Field to hold the reception credentias.
	private final User adminUser;
	
	
	public LoginController(LoginModel loginModel) {
		adminUser = new User("admin","123");
		adminUser.setStaffType(User.STAFF_RECEPTION_ADMIN);
		model = loginModel;
	}
	
	/**
	 * Set the reference to the LoginView so that we can call update after the model changes.
	 */
	public void setView(LoginView view) {
		this.view = view;
	}
	
	
	/**
	 * Attempt to log the user in.
	 */
	public void login(String user, String password) {
		boolean isValidLogin = false;
		
		//First check if its an admin user, i.e. Reception Staff.
		if(adminUser.login(user, password)) {
			model.login(adminUser);
			showAdminPanel();
			isValidLogin = true;
			return;
		} else {
			//Check if the user is one of the medical staff.
			
			//Get a list of all medical staff.
			final ArrayList<MedicalStaff> staffList = DataRepository.getInstance().getStaffList();
			
			//Iterate over staff list and check user name.
			for (MedicalStaff staff : staffList) {
				if(staff.login(user, password)) {
					model.login(staff);
					showStaffPanel();
					isValidLogin = true;
					return;
				}
			}
		}
		
		if(!isValidLogin) {
			model.setError(true);
			view.updateView();
		} 
	}
	
	/**
	 * Shows the admin panel if an admin user successfully logs in.
	 */
	private void showAdminPanel() {
		final AdminRootModel aModel = new AdminRootModel(model.getCurrentUser());
		final AdminController  adminController = new AdminController(aModel);
		final AdminView adminView = new AdminView(adminController, aModel);
		adminController.setView(adminView);
		adminView.setVisible(true);
		view.dispose();
	}
	
	/**
	 * Show the Medical Staff view that can be used to record/view treatment.
	 */
	private void showStaffPanel() {
		final StaffRootModel staffModel = new StaffRootModel(model.getAsMedicalStaff());
		final StaffRootController staffRootController = new StaffRootController(staffModel);
		final StaffRootView staffView = new StaffRootView(staffRootController, staffModel);
		staffRootController.setView(staffView);
		staffView.setVisible(true);
		view.dispose();
		
	}

}
