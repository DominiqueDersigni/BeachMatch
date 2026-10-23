package nl.novi.beachmatch.mappers;

import nl.novi.beachmatch.dtos.review.ReviewRequestDTO;
import nl.novi.beachmatch.dtos.review.ReviewResponseDTO;
import nl.novi.beachmatch.entities.ReviewEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewDTOMapper implements DTOMapper<ReviewResponseDTO, ReviewRequestDTO, ReviewEntity> {

    @Override
    public ReviewResponseDTO mapToDto(ReviewEntity model) {

        var result = new ReviewResponseDTO();
        result.setId(model.getReviewId());
        result.setTitle(model.getTitle());
        result.setText(model.getText());
        result.setScore(model.getScore());
        result.setCreateDate(model.getCreateDate());
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
    public List<ReviewResponseDTO> mapToDto(List<ReviewEntity> models) {
        var result = new ArrayList<ReviewResponseDTO>();
        for (ReviewEntity model : models) {
            result.add(mapToDto(model));
        }
        return result;
    }


    @Override
    public ReviewEntity mapToEntity(ReviewRequestDTO reviewModel) {
        var result = new ReviewEntity();
        result.setTitle(reviewModel.getTitle());
        result.setText(reviewModel.getText());
        result.setScore(reviewModel.getScore());
        return result;
    }
}
