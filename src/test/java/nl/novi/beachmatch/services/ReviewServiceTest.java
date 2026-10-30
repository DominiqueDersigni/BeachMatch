package nl.novi.beachmatch.services;

import nl.novi.beachmatch.dtos.review.ReviewRequestDTO;
import nl.novi.beachmatch.dtos.review.ReviewResponseDTO;
import nl.novi.beachmatch.entities.BeachEntity;
import nl.novi.beachmatch.entities.ReviewEntity;
import nl.novi.beachmatch.entities.UserEntity;
import nl.novi.beachmatch.exceptions.AccessDeniedException;
import nl.novi.beachmatch.mappers.ReviewDTOMapper;
import nl.novi.beachmatch.repositories.BeachRepository;
import nl.novi.beachmatch.repositories.ReviewRepository;
import nl.novi.beachmatch.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    ReviewDTOMapper reviewDTOMapper;

    @Mock
    BeachRepository beachRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    ReviewService reviewService;

    BeachEntity beach1;
    BeachEntity beach2;

    UserEntity user1;
    UserEntity user2;

    ReviewEntity review1;

    ReviewRequestDTO reviewRequest;
    ReviewResponseDTO reviewResponse1;

    @BeforeEach
    void setUp() {
        beach1 = new BeachEntity();
        beach1.setBeachId(1L);
        beach1.setName("Strand Zuid");

        beach2 = new BeachEntity();
        beach2.setBeachId(2L);
        beach2.setName("Strand Noord");

        user1 = new UserEntity();
        user1.setUserId(1L);
        user1.setName("John Doe");
        user1.setKeycloakId("a1b2c3d4-1111-2222-3333-444455556666");

        user2 = new UserEntity();
        user2.setUserId(2L);
        user2.setName("Jane Doe");
        user2.setKeycloakId("f9e8d7c6-9999-8888-7777-666655554444");

        review1 = new ReviewEntity();
        review1.setReviewId(1L);
        review1.setBeach(beach1);
        review1.setUser(user1);
        review1.setTitle("Lovely beach");
        review1.setText("Had a great day, very clean and safe beach");
        review1.setScore(5);

        reviewResponse1 = new ReviewResponseDTO();
        reviewResponse1.setId(1L);
        reviewResponse1.setTitle("Lovely beach");
        reviewResponse1.setBeachName("Strand Zuid");

        reviewRequest = new ReviewRequestDTO();
        reviewRequest.setBeachId(1L);
        reviewRequest.setTitle("Nice beach");
        reviewRequest.setText("Great for swimming but not for surfing");
        reviewRequest.setScore(4);
    }


    @Test
    @DisplayName("Create review")
    void createReview_ShouldReturnReviewDTO() {
        //Arrange
        ReviewEntity review3 = new ReviewEntity();
        review3.setTitle("Nice beach");
        review3.setText("Great for swimming but not for surfing");
        review3.setScore(4);

        when(reviewDTOMapper.mapToEntity(reviewRequest)).thenReturn(review3);
        when(beachRepository.findById(1L)).thenReturn(Optional.of(beach1));
        when(userRepository.findByKeycloakId(user1.getKeycloakId())).thenReturn(Optional.of(user1));
        when(reviewRepository.save(review3)).thenReturn(review3);
        when(reviewDTOMapper.mapToDto(review3)).thenReturn(reviewResponse1);

        //Act
        ReviewResponseDTO result =
                reviewService.createReview(user1.getKeycloakId(), reviewRequest);

        //Assert
        assertEquals(reviewResponse1, result);
        assertEquals(beach1, review3.getBeach());
        assertEquals(user1, review3.getUser());
        verify(reviewRepository).save(review3);
    }


    @Test
    @DisplayName("Update review by owner")
    void updateReview_byOwner_shouldUpdateFields() {
        //Arrange
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review1));
        when(reviewRepository.save(review1)).thenReturn(review1);
        when(reviewDTOMapper.mapToDto(review1)).thenReturn(reviewResponse1);

        //Act
        ReviewResponseDTO result =
                reviewService.updateReview(user1.getKeycloakId(), 1L, reviewRequest);

        //Assert
        assertEquals(reviewResponse1, result);
        assertEquals("Nice beach", review1.getTitle());
        assertEquals("Great for swimming but not for surfing", review1.getText());
        assertEquals(4, review1.getScore());
        verify(reviewRepository).save(review1);
    }

    @Test
    @DisplayName("Update review by another user")
    void updateReview_byOtherUser_shouldThrowAccessDeniedException() {
        //Arrange
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review1));

        //Act + Assert
        assertThrows(
                AccessDeniedException.class,
                () -> reviewService.updateReview(user2.getKeycloakId(), 1L, reviewRequest)
        );
        verify(reviewRepository, never()).save(any(ReviewEntity.class));
    }

    @Test
    @DisplayName("Delete review by owner")
    void deleteReview_byOwner_shouldDeleteReview() {
        //Arrange
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review1));

        //Act
        reviewService.deleteReview(user1.getKeycloakId(), 1L);

        //Assert
        verify(reviewRepository).delete(review1);
    }

    @Test
    @DisplayName("Delete review by another user")
    void deleteReview_byOtherUser_shouldThrowAccessDeniedException() {
        //Arrange
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review1));

        //Act + Assert
        assertThrows(
                AccessDeniedException.class,
                () -> reviewService.deleteReview(user2.getKeycloakId(), 1L)
        );
        verify(reviewRepository, never()).delete(any(ReviewEntity.class));
    }
}

