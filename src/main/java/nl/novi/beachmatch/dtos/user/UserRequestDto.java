package nl.novi.beachmatch.dtos.user;

import jakarta.validation.constraints.*;
import nl.novi.beachmatch.enums.ModeOfTransport;

import java.util.List;

public class UserRequestDto {
    @NotBlank(message = "Address cannot be blank")
    @Size(min = 3, max = 100, message = "Address needs to be between 3 and 100 characters")
    private String address;
    @NotBlank(message = "City cannot be blank")
    @Size(min = 3, max = 50, message = "City needs to be between 3 and 50 characters")
    private String city;
    @NotNull(message = "Age cannot be empty")
    @Min(value = 12, message = "Age must be at least 12")
    @Max(value = 100, message = "Age cannot be higher than 100")
    private Integer age;
    @NotEmpty(message = "Choose at least one mode of transport")
    private List<ModeOfTransport> modesOfTransport;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<ModeOfTransport> getModesOfTransport() {
        return modesOfTransport;
    }

    public void setModesOfTransport(List<ModeOfTransport> modesOfTransport) {
        this.modesOfTransport = modesOfTransport;
    }
}

