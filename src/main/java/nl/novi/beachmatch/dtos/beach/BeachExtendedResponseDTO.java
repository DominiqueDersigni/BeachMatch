package nl.novi.beachmatch.dtos.beach;


import nl.novi.beachmatch.dtos.beach.BeachResponseDTO;
import nl.novi.beachmatch.dtos.review.ReviewResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class BeachExtendedResponseDTO extends BeachResponseDTO {
    private List<ReviewResponseDTO> review = new ArrayList<>();

    public List<ReviewResponseDTO> getReview() {
        return review;
    }

    public void setReview(List<ReviewResponseDTO> review) {
        this.review = review;
    }
}



