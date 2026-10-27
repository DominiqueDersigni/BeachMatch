package nl.novi.beachmatch.controllers;

import jakarta.validation.Valid;
import nl.novi.beachmatch.dtos.review.ReviewRequestDTO;
import nl.novi.beachmatch.dtos.review.ReviewResponseDTO;
import nl.novi.beachmatch.helpers.UrlHelper;
import nl.novi.beachmatch.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UrlHelper urlHelper;

    public ReviewController(ReviewService reviewService, UrlHelper urlHelper) {
        this.reviewService = reviewService;
        this.urlHelper = urlHelper;
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(@AuthenticationPrincipal Jwt jwt,@Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
        String keycloakId = jwt.getClaimAsString("sub");
        ReviewResponseDTO reviewDTO = reviewService.createReview(keycloakId, reviewRequestDTO);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(reviewDTO.getId())).body(reviewDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id, @Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
        String keycloakId = jwt.getClaimAsString("sub");
        ReviewResponseDTO reviewDTO = reviewService.updateReview(keycloakId, id, reviewRequestDTO);
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        String keycloakId = jwt.getClaimAsString("sub");
        reviewService.deleteReview(keycloakId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
