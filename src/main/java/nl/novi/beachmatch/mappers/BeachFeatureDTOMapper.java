package nl.novi.beachmatch.mappers;


import nl.novi.beachmatch.dtos.beachfeature.BeachFeatureRequestDTO;
import nl.novi.beachmatch.dtos.beachfeature.BeachFeatureResponseDTO;
import nl.novi.beachmatch.entities.BeachFeatureEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeachFeatureDTOMapper implements DTOMapper<BeachFeatureResponseDTO, BeachFeatureRequestDTO, BeachFeatureEntity> {

    @Override
    public BeachFeatureResponseDTO mapToDto(BeachFeatureEntity model) {
        var result = new BeachFeatureResponseDTO();

        result.setId(model.getBeachFeatureId());
        result.setName(model.getName());
        result.setDescription(model.getDescription());

        return result;
    }

    @Override
    public List<BeachFeatureResponseDTO> mapToDto(List<BeachFeatureEntity> models) {
        var result = new ArrayList<BeachFeatureResponseDTO>();
        for (BeachFeatureEntity model : models) {
            result.add(mapToDto(model));
        }
        return result;
    }

    @Override
    public BeachFeatureEntity mapToEntity(BeachFeatureRequestDTO featureModel) {
        var result = new BeachFeatureEntity();
        result.setName(featureModel.getName());
        result.setDescription(featureModel.getDescription());
        return result;
    }
}
