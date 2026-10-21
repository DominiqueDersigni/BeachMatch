package nl.novi.beachmatch.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "beaches")
public class BeachEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long beachId;

    private String name;
    private String description;
    private String province;
    private Double latitude;
    private Double longitude;



    @OneToOne
    @JoinColumn(name = "photo_id")
    private BeachPhotoEntity photo;

    @OneToMany(mappedBy = "beach")
    private List<ReviewEntity> reviewList;

    @OneToMany(mappedBy = "beach")
    private List<FavoriteEntity> favorites;

    @ManyToMany()
    @JoinTable(
            name = "beaches_beachfeatures",
            joinColumns = @JoinColumn(name = "beach_id"),
            inverseJoinColumns = @JoinColumn(name = "beachfeature_id")
    )
    private Set<BeachFeatureEntity> beachFeatures = new HashSet<>();

    public Long getBeachId() {
        return beachId;
    }

    public void setBeachId(Long beachId) {
        this.beachId = beachId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BeachPhotoEntity getPhoto() {
        return photo;
    }

    public void setPhoto(BeachPhotoEntity photo) {
        this.photo = photo;
    }

    public List<ReviewEntity> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewEntity> reviewList) {
        this.reviewList = reviewList;
    }

    public List<FavoriteEntity> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<FavoriteEntity> favorites) {
        this.favorites = favorites;
    }

    public Set<BeachFeatureEntity> getBeachFeatures() {
        return beachFeatures;
    }

    public void setBeachFeatures(Set<BeachFeatureEntity> beachFeatures) {
        this.beachFeatures = beachFeatures;
    }
}
