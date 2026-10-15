package nl.novi.beachmatch.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "beachfeatures")
public class BeachFeatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long beachfeatureId;

    private String name;
    private String description;

    public Long getBeachfeatureId() {
        return beachfeatureId;
    }

    public void setBeachfeatureId(Long beachfeatureId) {
        this.beachfeatureId = beachfeatureId;
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
}
