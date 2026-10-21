package nl.novi.beachmatch.dtos.beachfeature;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BeachFeatureRequestDto {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 100, message = "Name needs to be between 3 and 100 characters")
    private String name;
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description cannot be longer than 255 characters")
    private String description;

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
