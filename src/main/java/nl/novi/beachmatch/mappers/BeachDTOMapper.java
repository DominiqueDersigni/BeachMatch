package nl.novi.beachmatch.mappers;

import nl.novi.beachmatch.dtos.beach.BeachRequestDTO;
import nl.novi.beachmatch.dtos.beach.BeachResponseDTO;
import nl.novi.beachmatch.entities.BeachEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class BeachDTOMapper implements DTOMapper<BeachResponseDTO, BeachRequestDTO, BeachEntity> {

    private final BeachFeatureDTOMapper beachFeatureDTOMapper;

    public BeachDTOMapper(BeachFeatureDTOMapper beachFeatureDTOMapper) {
        this.beachFeatureDTOMapper = beachFeatureDTOMapper;
    }

    @Override
    public BeachResponseDTO mapToDto(BeachEntity model) {
        return mapToDto(model, new BeachResponseDTO());
    }


    public <D extends BeachResponseDTO> D mapToDto(BeachEntity model, D target){
        target.setId(model.getBeachId());
        target.setName(model.getName());
        target.setDescription(model.getDescription());
        target.setProvince(model.getProvince());
        target.setLatitude(model.getLatitude());
        target.setLongitude(model.getLongitude());
        if(model.getBeachFeatures() != null){
            target.setBeachFeatures(beachFeatureDTOMapper.mapToDto(new ArrayList<>(model.getBeachFeatures())));
        }
        return target;
    }

    @Override
    public List<BeachResponseDTO> mapToDto(List<BeachEntity> models) {
        return models.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public BeachEntity mapToEntity(BeachRequestDTO requestDTO) {
        var model = new BeachEntity();
        model.setName(requestDTO.getName());
        model.setDescription(requestDTO.getDescription());
        model.setProvince(requestDTO.getProvince());
        model.setLatitude(requestDTO.getLatitude());
        model.setLongitude(requestDTO.getLongitude());
        return model;
    }
}