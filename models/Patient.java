package com.northamptonmedicalclinic.app.models;

import java.io.Serializable;

public class Patient implements Serializable{
	private String id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String birthDate;

	public Patient() {}
	public Patient(String id, String name, String address, String phone, String email, String birthDate) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.birthDate = birthDate;
	}
	
	public static Patient fromText(final String details) {
		final String[] info = details.split(",");
		final String id = info[0];
		final String name = info[1];
		final String birthDate = info[2];
		final String address = info[3];
		final String phone = info[4];
		final String email = info[5];
		return new Patient(id, name, address, phone, email, birthDate);
	}
	
	
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(id).append(",")
		.append(name).append(",")
		.append(birthDate).append(",")
		.append(address).append(",")
		.append(phone).append(",")
		.append(email);
		return sb.toString();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}




	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}




	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}




	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}




	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}




	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}




	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}




	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}




	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}




	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

}
