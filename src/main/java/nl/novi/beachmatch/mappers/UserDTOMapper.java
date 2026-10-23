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
        if(model.getBeach() != null){
            result.setBeachId(model.getBeach().getBeachId());
            result.setBeachName(model.getBeach().getName());
        }
        if(model.getUser() != null){
            result.setUserName(model.getUser().getName());
        }
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
        result.setTitle(userModel.getTitle());
        result.setText(userModel.getText());
        result.setScore(userModel.getScore());
        return result;
    }
}
