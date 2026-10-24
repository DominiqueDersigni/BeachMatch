package nl.novi.beachmatch.services;

import nl.novi.beachmatch.dtos.beach.BeachRequestDTO;
import nl.novi.beachmatch.dtos.beach.BeachResponseDTO;
import nl.novi.beachmatch.dtos.review.ReviewRequestDTO;
import nl.novi.beachmatch.dtos.review.ReviewResponseDTO;
import nl.novi.beachmatch.dtos.user.UserResponseDTO;
import nl.novi.beachmatch.entities.BeachEntity;
import nl.novi.beachmatch.entities.ReviewEntity;
import nl.novi.beachmatch.entities.UserEntity;
import nl.novi.beachmatch.exceptions.AccessDeniedException;
import nl.novi.beachmatch.exceptions.RecordNotFoundException;
import nl.novi.beachmatch.mappers.ReviewDTOMapper;;
import nl.novi.beachmatch.repositories.BeachRepository;
import nl.novi.beachmatch.repositories.ReviewRepository;
import nl.novi.beachmatch.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewDTOMapper reviewDTOMapper;
    private final BeachRepository beachRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, ReviewDTOMapper reviewDTOMapper, BeachRepository beachRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewDTOMapper = reviewDTOMapper;
        this.beachRepository = beachRepository;
        this.userRepository = userRepository;
    }

    private ReviewEntity getReviewEntity(Long id) {
        ReviewEntity existingReviewEntity = reviewRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Review " + id + " not found"));
        return existingReviewEntity;
    }

    @Transactional
    public ReviewResponseDTO createReview(String keycloakId, ReviewRequestDTO reviewModel) {
        ReviewEntity reviewEntity = reviewDTOMapper.mapToEntity(reviewModel);
        BeachEntity beach = beachRepository.findById(reviewModel.getBeachId())
                .orElseThrow(() -> new RecordNotFoundException("Beach " + reviewModel.getBeachId() + " not found"));
        reviewEntity.setBeach(beach);

        UserEntity user = userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new RecordNotFoundException("User " + keycloakId  + " not found"));
        reviewEntity.setUser(user);
        reviewEntity = reviewRepository.save(reviewEntity);
        return reviewDTOMapper.mapToDto(reviewEntity);
    }

    @Transactional
    public ReviewResponseDTO updateReview(String keycloakId, Long reviewId, ReviewRequestDTO reviewModel) {
        ReviewEntity existingReviewEntity = getReviewEntity(reviewId);
        if (!existingReviewEntity.getUser().getKeycloakId().equals(keycloakId)) {
            throw new AccessDeniedException("You can only change your own reviews");
        }
        existingReviewEntity.setTitle(reviewModel.getTitle());
        existingReviewEntity.setText(reviewModel.getText());
        existingReviewEntity.setScore(reviewModel.getScore());
        existingReviewEntity = reviewRepository.save(existingReviewEntity);
        return reviewDTOMapper.mapToDto(existingReviewEntity);
    }

    @Transactional
    public void deleteReview(String keycloakId, Long reviewId) {
        ReviewEntity review = getReviewEntity(reviewId);
        if (!review.getUser().getKeycloakId().equals(keycloakId)) {
            throw new AccessDeniedException("You can only delete your own reviews");
        }
        reviewRepository.delete(review);
    }
}

