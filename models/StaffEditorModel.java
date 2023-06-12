package com.northamptonmedicalclinic.app.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.northamptonmedicalclinic.app.service.DataRepository;

public class StaffEditorModel {
	private MedicalStaff staff;
	
	//
	private boolean isNewStaff;
	
	//Index of the staff details in the array list if we are editing an existing staff.
	private int staffIndex;
	
	private static final String NEW_STAFF = "Add New Staff";
	private static final String EDIT_STAFF = "Edit Staff Information";
	private static final String buttonAddStaff = "Add";
	private static final String buttonEditStaff = "Save";
	
	private boolean isError;
	private String errorMessage;

	private User user;
	
	public StaffEditorModel(MedicalStaff staff, int staffIndex, User user) {
		this.user = user;
		
		//if staff is null, it means we are adding a new personnel.
		//If we provide a MedicalStaff instance along with the position of the staff in the Array List then we are in edit mode.
		isNewStaff = staff == null;
		
		if(isNewStaff) 
			staff = getDefaultValues();
		this.staff = staff;
		
		this.staffIndex = staffIndex;
		setErrorMessage(null);
	}
	
	public User getUser() {
		return user;
	}
	
	
	/**
	 * @return the isError
	 */
	public boolean isError() {
		return isError;
	}



	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}



	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		isError = errorMessage != null;
		this.errorMessage = errorMessage;
	}



	/**
	 * Returns a default MedicalSatff instance for use with new staff additions.
	 */
	private MedicalStaff getDefaultValues() {
		final MedicalStaff staff = new MedicalStaff("", "");
		staff.setStaffType(MedicalStaff.STAFF_DOCTOR);
		staff.setQualificationDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		staff.setRegistrationDate(staff.getQualificationDate());
		staff.setRegistrationNumber("");
		staff.setCanPresribe(true);
		staff.setName("");
		staff.setGender("Female");
		return staff;
	}
	
	
	/**
	 * Returns the title for the form.
	 */
	public String getTitle() {
		return isNewStaff ? NEW_STAFF:EDIT_STAFF;
	}
	
	
	/**
	 * Returns the lable for the save button.
	 */
	public String getButtonText() {
		return isNewStaff ? buttonAddStaff:buttonEditStaff;
	}
	
	
	/**
	 * Check if this is a new personnel being added
	 */
	public boolean isNewStaff() {
		return isNewStaff;
	}
	
	/**
	 * Save the staff information.
	 */
	public void saveStaff(MedicalStaff staff) {
		this.staff = staff;
	}
	
	
	/**
	 * Returns the staff details.
	 */
	public MedicalStaff getStaffDetails() {
		return this.staff;
	}
	
	/**
	 * Save the staff details, if set, to disk.
	 */
	public void saveToDisk() {
		if(staff != null) {
			final ArrayList<MedicalStaff> staffList = DataRepository.getInstance().getStaffList();
			if(isNewStaff) {
				staffList.add(staff);
			} else {
				staffList.set(staffIndex, staff);
			}
			DataRepository.getInstance().saveStaffList(staffList);
		}
	}
	
}
