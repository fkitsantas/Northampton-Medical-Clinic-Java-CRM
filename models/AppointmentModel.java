package com.northamptonmedicalclinic.app.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.northamptonmedicalclinic.app.service.DataRepository;

public class AppointmentModel {
	private User user;
	
	private Appointment appointment;
	
	private String errorMessage;
	private boolean didSet;
	private boolean isError;
	
	private ArrayList<MedicalStaff> staffList;
	private ArrayList<Patient> patientList;
	private String[] patients, staffNames;


	
	public AppointmentModel(User user) {
		this.user = user;
		initPatientList();
		initStaffList();
	}
	
	public User getUser() {
		return user;
	}
	
	//Initialises the Patient list with their names to be used in the combo box.
	private void initPatientList() {
		patientList = DataRepository.getInstance().getPatientList();
		patients = new String[patientList.size()];
			
		for (int i = 0; i < patientList.size(); i++) {
			final Patient patient = patientList.get(i);
			patients[i] = patient.getId() + " - " + patient.getName();
		}
		
		if(patients.length == 0)
			setErrorMessage("Please add patients before adding an appointment.");
	}
	
	//Initialises the Staff list with their names.
	private void initStaffList() {
		staffList = DataRepository.getInstance().getStaffList();
		staffNames  = new String[staffList.size()];
		
		for (int i = 0; i < staffList.size(); i++) {
			final MedicalStaff staff = staffList.get(i);
			staffNames[i] = staff.getName() + " - " + staff.getUsername();
		}
		
		if(staffNames.length == 0)
			setErrorMessage("Please add Medical Staff before adding a new appointment.");
	}
	
	/**
	 * Returns the staff username for a given index
	 */
	public String getStaffID(int index) {
		return staffList.get(index).getUsername();
	}
	
	/**
	 * Returns the patient ID for a given index
	 */
	public String getPatientID(int index) {
		return patientList.get(index).getId();
	}
	
	/**
	 * Returns the patient name for a given index.
	 */
	public String getPatientName(int index) {
		return patientList.get(index).getName();
	}
	
	/**
	 * Returns an array of patient names
	 */
	public String[] getPatients() {
		return patients;
	}

	/**
	 * Returns an array of medical staff names
	 */
	public String[] getStaffNames() {
		return staffNames;
	}

	/**
	 * Returns the currently selected appointment.
	 */
	public Appointment getAppointment() {
		return appointment;
	}
	
	
	/**
	 * Is there a validation error?
	 */
	public boolean isError() {
		return isError;
	}
	
	
	/**
	 * Returns the error message.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	
	/**
	 * @param the appointment to set
	 */
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
		this.didSet = true;
	}

	
	/**
	 * @param the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Saves the appointment to disk.
	 */
	public void save() {
		//First, we check if there is an error.
		//Then we check if the appointment has been set.
		//After that we ensure that the appointment is not null.
		//If the above three conditions are met, we can safely save the appointment to disk.
		if(!isError && didSet && appointment != null) {
			
			//Get a map of all appointments for all medical staff.
			final HashMap<String, ArrayList<Appointment>> appts = DataRepository.getInstance().getAppointments();
			
			//Get a list of appointments of for the current doctor. These are saved with staff username as key.
			ArrayList<Appointment> appointmentList =  appts.get(appointment.getDoctorID());
			
			//If there are no prior appointment for a staff, the list will be null. Therefore, we ensure that its initialised.
			if(appointmentList == null) {
				appointmentList = new ArrayList<>();
			}
			appts.put(appointment.getDoctorID(), appointmentList);
			appointmentList.add(appointment);
			
			//Save the appointment to disk.
			DataRepository.getInstance().saveAppointmentInfo(appts);
		}
	}
}
