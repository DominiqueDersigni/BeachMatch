package nl.novi.beachmatch.dtos.user;

import nl.novi.beachmatch.enums.ModeOfTransport;

import java.util.List;

public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String city;
    private Integer age;
    private List<ModeOfTransport> modesOfTransport;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
