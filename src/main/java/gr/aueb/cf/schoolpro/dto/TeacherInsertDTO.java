package gr.aueb.cf.schoolpro.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TeacherInsertDTO {
	@Size(min = 2, max = 50, message = "Firstname must have a length from 2 to 50 letters")
@Pattern(regexp = "^[a-zA-Z\\-].$", message = "Firstname must contain only letters")
	private String firstName;
	@Size(min = 2, max = 50, message = "Lastname must have a length from 2 to 50 letters")
	@Pattern(regexp = "^[a-zA-Z\\-].$", message = "Lastname must contain only letters")
	private String lastName;
	@Size(min = 9, max = 9, message = "ssn must have a length of 9 digits")
	@Pattern(regexp = "^\\d.$", message = "ssn must contain only digits")
	private String ssn;
	private Long speciality;
	private Long userId;

	
	public TeacherInsertDTO() {
		
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

	public TeacherInsertDTO(String firstName, String lastName, String ssn, Long speciality, Long userId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.speciality = speciality;
		this.userId =userId ;
	}

	public Long getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Long speciality) {
		this.speciality = speciality;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
