package nl.novi.beachmatch.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "beachphotos")
public class BeachPhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long beachPhotoId;

    private String beachPhotoFileName;
    private String beachPhotoContentType;

    @Lob
    private byte[] data;

    public BeachPhotoEntity(String beachPhotoFileName, String beachPhotoContentType, byte[] data) {
        this.beachPhotoFileName = beachPhotoFileName;
        this.beachPhotoContentType = beachPhotoContentType;
        this.data = data;
    }

    public BeachPhotoEntity() {
    }

    @OneToOne(mappedBy = "beachPhoto")
    private BeachEntity beach;

    public Long getBeachPhotoId() {
        return beachPhotoId;
    }

    public void setBeachPhotoId(Long beachPhotoId) {
        this.beachPhotoId = beachPhotoId;
    }

    public String getBeachPhotoFileName() {
        return beachPhotoFileName;
    }

    public void setBeachPhotoFileName(String beachPhotoFileName) {
        this.beachPhotoFileName = beachPhotoFileName;
    }

    public String getBeachPhotoContentType() {
        return beachPhotoContentType;
    }

    public void setBeachPhotoContentType(String beachPhotoContentType) {
        this.beachPhotoContentType = beachPhotoContentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public BeachEntity getBeach() {
        return beach;
    }

    public void setBeach(BeachEntity beach) {
        this.beach = beach;
    }
}

