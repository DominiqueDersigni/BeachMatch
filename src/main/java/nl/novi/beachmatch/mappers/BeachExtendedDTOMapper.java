package nl.novi.beachmatch.mappers;

import nl.novi.beachmatch.dtos.beach.BeachExtendedResponseDTO;
import nl.novi.beachmatch.entities.BeachEntity;
import org.springframework.stereotype.Component;


@Component
public class BeachExtendedDTOMapper extends BeachDTOMapper {

    private final ReviewDTOMapper reviewDTOMapper;

    public BeachExtendedDTOMapper(BeachFeatureDTOMapper beachFeatureDTOMapper, ReviewDTOMapper reviewDTOMapper) {
        super(beachFeatureDTOMapper);
        this.reviewDTOMapper = reviewDTOMapper;
    }

    @Override
    public BeachExtendedResponseDTO mapToDto(BeachEntity model) {
        BeachExtendedResponseDTO result = mapToDto(model, new BeachExtendedResponseDTO());
        if (model.getReviews() != null) {
            result.setReview(reviewDTOMapper.mapToDto(model.getReviews()));
        }
        return result;
    }
}
