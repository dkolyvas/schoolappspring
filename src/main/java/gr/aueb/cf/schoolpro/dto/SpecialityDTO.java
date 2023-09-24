package gr.aueb.cf.schoolpro.dto;

public class SpecialityDTO {
    private Long id;
    private String speciality;

    public SpecialityDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpecialityDTO(Long id, String speciality) {
        this.id = id;
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
