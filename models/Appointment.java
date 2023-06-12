package com.northamptonmedicalclinic.app.models;

import java.io.Serializable;

public class Appointment implements Serializable {
	private String patientID;
	private String doctorID;
	private String appointmentTime;
	private String patientName;
	
	public void setPatientName(String name) {
		this.patientName = name;
	}
	
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @return the patientID
	 */
	public String getPatientID() {
		return patientID;
	}

	/**
	 * @return the doctorID
	 */
	public String getDoctorID() {
		return doctorID;
	}

	/**
	 * @return the appointmentTime
	 */
	public String getAppointmentDate() {
		return appointmentTime;
	}

	/**
	 * @param the patientID to set
	 */
	public void setPatientID(String string) {
		this.patientID = string;
	}

	/**
	 * @param the doctorID to set
	 */
	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}

	/**
	 * @param the appointmentTime to set
	 */
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

}
