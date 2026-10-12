package nl.novi.beachmatch.entities;

import jakarta.persistence.*;

@Entity
@Table (name = "beaches")
public class BeachEntity extends BaseEntity {
    private String name;
    private int coordinate;

}
