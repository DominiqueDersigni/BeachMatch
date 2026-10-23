package nl.novi.beachmatch.mappers;

import nl.novi.beachmatch.dtos.user.UserRequestDTO;
import nl.novi.beachmatch.dtos.user.UserResponseDTO;
import nl.novi.beachmatch.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDTOMapper implements DTOMapper<UserResponseDTO, UserRequestDTO, UserEntity> {

    @Override
    public UserResponseDTO mapToDto(UserEntity model) {

        var result = new UserResponseDTO();
        result.setId(model.getUserId());
        result.setName(model.getName());
        result.setAddress(model.getAddress());
        result.setCity(model.getCity());
        result.setAge(model.getAge());
        result.setEmail(model.getEmail());
        result.setModesOfTransport(model.getModesOfTransport());
        return result;
    }


    @Override
    public List<UserResponseDTO> mapToDto(List<UserEntity> models) {
        var result = new ArrayList<UserResponseDTO>();
        for (UserEntity model : models) {
            result.add(mapToDto(model));
        }
        return result;
    }


    @Override
    public UserEntity mapToEntity(UserRequestDTO userModel) {
        var result = new UserEntity();
        result.setAddress(userModel.getAddress());
        result.setCity(userModel.getCity());
        result.setAge(userModel.getAge());
        result.setModesOfTransport(userModel.getModesOfTransport());
        return result;
    }
}
