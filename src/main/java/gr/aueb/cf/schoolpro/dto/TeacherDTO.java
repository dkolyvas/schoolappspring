package gr.aueb.cf.schoolpro.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TeacherDTO {
	private Long id;
	@Size(min = 2, max = 50, message = "Firstname must have a length from 2 to 50 letters")
	@Pattern(regexp = "^[a-zA-Z\\-].$", message = "Firstname must contain only letters")
	private String firstName;
	@Size(min = 2, max = 50, message = "Lastname must have a length from 2 to 50 letters")
	@Pattern(regexp = "^[a-zA-Z\\-].$", message = "Lastname must contain only letters")
	private String lastName;
	@Size(min = 9, max = 9, message = "ssn must have a length of 9 digits")
	@Pattern(regexp = "^\\d.$", message = "ssn must contain only digits")
	private String ssn;
	private String speciality;
	private String userName;

	public TeacherDTO(Long id, String firstName, String lastName, String ssn, String speciality, String username) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.speciality = speciality;
		this.userName = username;
	}

	public TeacherDTO() {
		
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
