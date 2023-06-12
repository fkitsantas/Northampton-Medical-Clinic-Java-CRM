package com.northamptonmedicalclinic.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.northamptonmedicalclinic.app.models.Appointment;
import com.northamptonmedicalclinic.app.models.MedicalStaff;
import com.northamptonmedicalclinic.app.models.Patient;
import com.northamptonmedicalclinic.app.models.Treatment;

/**
 * Serves as the mediator between storage class and the rest of the application.
 * It temporarily stores the data read from the disk before passing it on to the requesting controller.
 * This class is needed throughout the application. Therefore, we store a static instance instead of 
 * recreating it every time we need it. This also allows us to re-use data read from the hard disk.
 */
public class DataRepository {

	private static DataRepository instance;
	
	private ArrayList<MedicalStaff> staffCache;
	private HashMap<String, ArrayList<Appointment>> appointmentCache;
	private HashMap<String, ArrayList<Treatment>> treatmentCache;
	private ArrayList<Patient> patientCache;
	
	private final DiskStorageService storageService;
	
	//We need this class accessible from everywhere so saving the instance in a static variable.
	public static DataRepository getInstance() {
		if(instance == null)
			instance = new DataRepository();
		return instance;
	}
	
	/**
	 * Private constructor helps us keep a single instance.
	 */
	private DataRepository() {
		storageService = new DiskStorageService();
	}
	
	
	public ArrayList<MedicalStaff> getStaffList(){
		if(staffCache == null) {
			try {
				staffCache = storageService.getMedicalStaff();
			} catch (IOException e) {
				//We have no medical staff.
				staffCache = new ArrayList<>();
			}
		}
		
		return staffCache;
	}
	
	
	public void saveStaffList(ArrayList<MedicalStaff> staffList) {
		try {
			storageService.saveStaffList(staffList);
			staffCache = staffList;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<Patient> getPatientList(){
		if(patientCache == null) {
			try {
				patientCache = storageService.getPatients();
			} catch (Exception e) {
				e.printStackTrace();
				patientCache = new ArrayList<>();
			}
		}
		return patientCache;
	}
	
	
	public void savePatientList(ArrayList<Patient> patients) {
		try {
			storageService.savePatientList(patients);
			patientCache = patients;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveAppointmentInfo(HashMap<String, ArrayList<Appointment>> appointments) {
		try {
			storageService.saveAppointments(appointments);
			this.appointmentCache = appointments;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, ArrayList<Appointment>> getAppointments(){
		if(appointmentCache == null) {
			try {
				appointmentCache = storageService.getAppointments();
			} catch (Exception e) {
				appointmentCache = new HashMap<>();
			}
		} 
		return appointmentCache;
	}
	
	public void saveTreatments(HashMap<String, ArrayList<Treatment>> treatments) {
		try {
			storageService.saveTreatments(treatments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HashMap<String, ArrayList<Treatment>> getTreatments(){
		if(treatmentCache == null) {
			try {
				treatmentCache = storageService.getTreatments();
			} catch (Exception e) {
				treatmentCache = new HashMap<>();
			}
		}
		return treatmentCache;
	}
}
