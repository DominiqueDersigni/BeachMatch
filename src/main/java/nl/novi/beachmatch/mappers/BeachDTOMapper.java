package nl.novi.beachmatch.mappers;

import nl.novi.beachmatch.dtos.beach.BeachRequestDTO;
import nl.novi.beachmatch.dtos.beach.BeachResponseDTO;
import nl.novi.beachmatch.entities.BeachEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BeachDTOMapper implements DTOMapper<BeachResponseDTO, BeachRequestDTO, BeachEntity> {

    public BeachResponseDTO mapToDto(BeachEntity entity) {
        BeachResponseDTO dto = new BeachResponseDTO();

        dto.setId(entity.getBeachId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setProvince(entity.getProvince());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());

        return dto;
    }

    @Override
    public List<BeachResponseDTO> mapToDto(List<BeachEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public BeachEntity mapToEntity(BeachRequestDTO dto) {
        BeachEntity entity = new BeachEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setProvince(dto.getProvince());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        return entity;
    }
}