package nl.novi.beachmatch.mappers;

import nl.novi.beachmatch.dtos.beach.BeachRequestDTO;
import nl.novi.beachmatch.dtos.beach.BeachResponseDTO;
import nl.novi.beachmatch.dtos.beachfeature.BeachFeatureRequestDTO;
import nl.novi.beachmatch.dtos.beachfeature.BeachFeatureResponseDTO;
import nl.novi.beachmatch.entities.BeachEntity;
import nl.novi.beachmatch.entities.BeachFeatureEntity;

import java.util.List;
import java.util.stream.Collectors;

public class BeachFeatureDTOMapper {implements DTOMapper<BeachFeatureResponseDTO, BeachFeatureRequestDTO, BeachFeatureEntity> {

    @Override
    public BeachFeatureResponseDTO mapToDto(BeachFeatureEntity feature) {
        BeachFeatureResponseDTO dto = new BeachFeatureResponseDTO();

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
}
