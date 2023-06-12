package com.northamptonmedicalclinic.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import com.northamptonmedicalclinic.app.models.Appointment;
import com.northamptonmedicalclinic.app.models.MedicalStaff;
import com.northamptonmedicalclinic.app.models.Patient;
import com.northamptonmedicalclinic.app.models.Treatment;



/**
 * Provides methods for saving and reading files from the disk. 
 * A generic method is used to save the Java Serializable Objects.
 * We use the binary file for instances where manually serialising a file to text (ex. with comma) might
 * induce errors. 
 */
public class DiskStorageService {
	
	private final static String MEDICAL_STAFF = "StaffList.txt";
	private final static String PATIENTS = "Patients.dat";
	private final static String APPOINTMENTS = "Appointments.dat";
	private final static String TREATMENTS = "Treatments.dat";
	
	
	
	/**
	 * Read the list of staff from the disk 
	 */
	public ArrayList<MedicalStaff> getMedicalStaff() throws IOException {
		final File file = new File(MEDICAL_STAFF);
		final Scanner reader = new Scanner(file);
		
		final ArrayList<MedicalStaff> staffList = new ArrayList<>();
		
		while(reader.hasNextLine()) {
			final String staffInfo = reader.nextLine();
			staffList.add(MedicalStaff.fromText(staffInfo));
		}
		reader.close();
		
		return staffList;
	}
	
	

	/**
	 * Save patient list to disk.
	 */
	public void savePatientList(ArrayList<Patient> patients) throws IOException {
		//saveCollectionToTextFile(patients, PATIENTS);
		Logger log = Logger.getLogger("save");
		log.warning(patients.get(0).toString());
		saveObject(patients, PATIENTS);
	}
	
	
	/**
	 * Save staff list to disk.
	 */
	public void saveStaffList(ArrayList<MedicalStaff> staffList) throws IOException {
		saveCollectionToTextFile(staffList, MEDICAL_STAFF);
	}
	
	
	/**
	 * Returns a list of patients from the disk.
	 */
	public ArrayList<Patient> getPatients() throws IOException, ClassNotFoundException {
		ArrayList<Patient> patients =  getObject(PATIENTS);
		return patients;
	}
	
	
	public void saveAppointments(HashMap<String, ArrayList<Appointment>> appointments) throws IOException{
		saveObject(appointments, APPOINTMENTS);
	}
	
	public HashMap<String, ArrayList<Appointment>> getAppointments() throws ClassNotFoundException, IOException{
		return getObject(APPOINTMENTS);
	}
	
	

	public void saveTreatments(HashMap<String, ArrayList<Treatment>> treatments) throws IOException {
		saveObject(treatments, TREATMENTS);
	}
	
	public HashMap<String, ArrayList<Treatment>> getTreatments() throws ClassNotFoundException, IOException{
		return getObject(TREATMENTS);
	}
	

	/**
	 * Generic method to get an object from disk
	 */
	private <T> T getObject(String file) throws IOException, ClassNotFoundException{
		final FileInputStream inputStream = new FileInputStream(file);
		final ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
		
		T object = (T)  objInputStream.readObject();
		objInputStream.close();
		return object;
	}
	
	/**
	 * Generic method to save an object to disk
	 */
	private <T> void saveObject(T object, String fileName) throws IOException{
		final FileOutputStream outStream = new FileOutputStream(fileName);
		final ObjectOutputStream objOutStream = new ObjectOutputStream(outStream);
		
		objOutStream.writeObject(object);
		objOutStream.close();
		outStream.close();
	}
	
	
	/**
	 * Generic method to save Array of objects to a text file. This method calls
	 * toString on each object and saves the output that in a text file.
	 */
	private void saveCollectionToTextFile(Collection collection, String filename) throws IOException{
		final FileWriter fileWriter = new FileWriter(filename);
		final PrintWriter printWriter = new PrintWriter(fileWriter, true);
		
		for(Object obj:collection) {
			printWriter.println(obj.toString());
		}
		printWriter.close();
		fileWriter.close();
	}
}
