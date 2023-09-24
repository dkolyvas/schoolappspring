package gr.aueb.cf.schoolpro.dto;

public class CityDTO {
    private Long id;
    private String city;

    public CityDTO(Long id, String city) {
        this.id = id;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
