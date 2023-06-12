package com.northamptonmedicalclinic.app.models;

public class MedicalStaff extends User {
	private String registrationNumber;
	private String name, gender;
	private String registrationDate, qualificationDate;
	
	private String grade;
	private int canPrescribe;
	
	
	//Serialization separator used in toString method for storing in file.
	private final String SEPARATOR = ",";
	
	
	public MedicalStaff(String user, String pass) {
		super(user, pass);
	}
	
	
	/**
	 * Deserialize the staff details from a line read from the file.
	 * @param userInfo Comma spearated string consisting of staff details.
	 * @return MedicalStaff
	 */
	public static MedicalStaff fromText(String userInfo) {
		final String[] details = userInfo.split(",");
		
		final String user = details[0];
		final String password = details[1];
		
		final MedicalStaff staff = new MedicalStaff(user, password);
		staff.setName(details[2]);
		staff.setGender(details[3]);
		staff.setRegistrationNumber(details[4]);
		staff.setRegistrationDate(details[5]);
		staff.setQualificationDate(details[6]);
		staff.setStaffType(Integer.parseInt(details[7]));
		if(staff.isNurse()) {
			staff.setGrade(details[8]);
			staff.setCanPresribe(Integer.parseInt(details[9]) == 1);
		}
		
		return staff;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getUsername()).append(SEPARATOR)
			.append(getPassword()).append(SEPARATOR)
			.append(name).append(SEPARATOR)
			.append(gender).append(SEPARATOR)
			.append(registrationNumber).append(SEPARATOR)
			.append(registrationDate).append(SEPARATOR)
			.append(qualificationDate).append(SEPARATOR)
			.append(staffType).append(SEPARATOR)
			.append(grade).append(SEPARATOR)
			.append(canPrescribe);
		return sb.toString();
	}
	
	


		
	/**
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}




	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}




	/**
	 * @return the registrationDate
	 */
	public String getRegistrationDate() {
		return registrationDate;
	}




	/**
	 * @return the qualificationDate
	 */
	public String getQualificationDate() {
		return qualificationDate;
	}




	/**
	 * @return the grade
	 */
	public String getGrade() {
		assert isNurse() == true:"getGrade called for a Doctor";
		return grade;
	}




	




	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}




	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}




	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}




	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}




	/**
	 * @param qualDate the qualificationDate to set
	 */
	public void setQualificationDate(String qualDate) {
		this.qualificationDate = qualDate;
	}
	
	
	


	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}




	/**
	 * Check if this medical staff prescribe medicines
	 */
	public boolean getCanPrescribe() {
		if(isDoctor())
			return true;
		return canPrescribe == 1;
	}


	/**
	 * @param canPrescribe the canPrescribe to set
	 */
	public void setCanPresribe(boolean canPresribe) {
		//All doctors can prescribe.
		if(isDoctor())
			this.canPrescribe = 1;
		this.canPrescribe = canPresribe ? 1:0;
	}


	
}
