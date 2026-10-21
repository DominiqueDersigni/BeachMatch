package nl.novi.beachmatch.dtos.beach;

import jakarta.validation.constraints.*;

public class BeachRequestDto {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 100, message = "Name needs to be between 3 and 100 characters")
    private String name;
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description cannot be longer than 255 characters")
    private String description;
    @NotBlank(message = "Province cannot be blank")
    private String province;
    @NotNull(message = "Latitude cannot be empty")
    @DecimalMin(value = "50.7", message = "Latitude must be within the Netherlands")
    @DecimalMax(value = "53.6", message = "Latitude must be within the Netherlands")
    private Double latitude;
    @NotNull(message = "Longitude cannot be empty")
    @DecimalMin(value = "3.3", message = "Longitude must be within the Netherlands")
    @DecimalMax(value = "7.3", message = "Longitude must be within the Netherlands")
    private Double longitude;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
