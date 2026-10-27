package nl.novi.beachmatch.controllers;

import jakarta.validation.Valid;
import nl.novi.beachmatch.dtos.favorite.FavoriteRequestDTO;
import nl.novi.beachmatch.dtos.favorite.FavoriteResponseDTO;
import nl.novi.beachmatch.helpers.UrlHelper;
import nl.novi.beachmatch.services.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UrlHelper urlHelper;

    public FavoriteController(FavoriteService favoriteService, UrlHelper urlHelper) {
        this.favoriteService = favoriteService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<FavoriteResponseDTO>> getFavoritesByUser (@AuthenticationPrincipal Jwt jwt) {
        String keycloakId = jwt.getClaimAsString("sub");
        List<FavoriteResponseDTO> favorites = favoriteService.findFavoritesByUser(keycloakId);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FavoriteResponseDTO> addFavorite(@AuthenticationPrincipal Jwt jwt,@Valid @RequestBody FavoriteRequestDTO favoriteRequestDTO) {
        String keycloakId = jwt.getClaimAsString("sub");
        FavoriteResponseDTO favoriteDTO = favoriteService.addFavorite(keycloakId, favoriteRequestDTO);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(favoriteDTO.getId())).body(favoriteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {
        String keycloakId = jwt.getClaimAsString("sub");
        favoriteService.deleteFavorite(keycloakId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
