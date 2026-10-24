package nl.novi.beachmatch.services;

import nl.novi.beachmatch.dtos.user.UserRequestDTO;
import nl.novi.beachmatch.dtos.user.UserResponseDTO;
import nl.novi.beachmatch.entities.UserEntity;
import nl.novi.beachmatch.exceptions.RecordNotFoundException;
import nl.novi.beachmatch.mappers.UserDTOMapper;
import nl.novi.beachmatch.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    private UserEntity getUserEntity(String keycloakId) {
        UserEntity existingUserEntity = userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new RecordNotFoundException("User " + keycloakId + " not found"));
        return existingUserEntity;
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findUserByKeycloakId(String keycloakId) {
        UserEntity userEntity = getUserEntity(keycloakId);
        return userDTOMapper.mapToDto(userEntity);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAllUsers() {
        return userDTOMapper.mapToDto(userRepository.findAll());
    }

    @Transactional
    public UserResponseDTO createUser(String keycloakId, String name, String email) {
        Optional<UserEntity> existingUser = userRepository.findByKeycloakId(keycloakId);
        if (existingUser.isPresent()) {
            return userDTOMapper.mapToDto(existingUser.get());
        }
        var userEntity = new UserEntity();
        userEntity.setKeycloakId(keycloakId);
        userEntity.setName(name);
        userEntity.setEmail(email);
        userEntity = userRepository.save(userEntity);
        return userDTOMapper.mapToDto(userEntity);
    }

    @Transactional
    public UserResponseDTO updateUser(String keycloakId, UserRequestDTO userModel)  {
        UserEntity existingUserEntity = getUserEntity(keycloakId);

        existingUserEntity.setAddress(userModel.getAddress());
        existingUserEntity.setCity(userModel.getCity());
        existingUserEntity.setAge(userModel.getAge());
        existingUserEntity.setModesOfTransport(userModel.getModesOfTransport());
        existingUserEntity = userRepository.save(existingUserEntity);
        return userDTOMapper.mapToDto(existingUserEntity);
    }

    @Transactional
    public void deleteUser(String keycloakId) {
        UserEntity user = getUserEntity(keycloakId);
        userRepository.delete(user);
    }
}
