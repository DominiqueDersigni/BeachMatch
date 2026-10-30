package nl.novi.beachmatch.dtos.beach;

import nl.novi.beachmatch.dtos.beachfeature.BeachFeatureResponseDTO;

import java.util.List;

public class BeachResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String province;
    private Double latitude;
    private Double longitude;
    private List<BeachFeatureResponseDTO> beachFeatures;
    private Double rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<BeachFeatureResponseDTO> getBeachFeatures() {
        return beachFeatures;
    }

    public void setBeachFeatures(List<BeachFeatureResponseDTO> beachFeatures) {
        this.beachFeatures = beachFeatures;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
