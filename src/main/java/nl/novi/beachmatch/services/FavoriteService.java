package nl.novi.beachmatch.services;

import nl.novi.beachmatch.dtos.favorite.FavoriteRequestDTO;
import nl.novi.beachmatch.dtos.favorite.FavoriteResponseDTO;
import nl.novi.beachmatch.entities.BeachEntity;
import nl.novi.beachmatch.entities.FavoriteEntity;
import nl.novi.beachmatch.entities.UserEntity;
import nl.novi.beachmatch.exceptions.AccessDeniedException;
import nl.novi.beachmatch.exceptions.RecordNotFoundException;
import nl.novi.beachmatch.mappers.FavoriteDTOMapper;
import nl.novi.beachmatch.repositories.BeachRepository;
import nl.novi.beachmatch.repositories.FavoriteRepository;
import nl.novi.beachmatch.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteService  {

    private final FavoriteRepository favoriteRepository;
    private final FavoriteDTOMapper favoriteDTOMapper;
    private final BeachRepository beachRepository;
    private final UserRepository userRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, FavoriteDTOMapper favoriteDTOMapper, BeachRepository beachRepository, UserRepository userRepository) {
        this.favoriteRepository = favoriteRepository;
        this.favoriteDTOMapper = favoriteDTOMapper;
        this.beachRepository = beachRepository;
        this.userRepository = userRepository;
    }

    private FavoriteEntity getFavoriteEntity(Long id) {
        FavoriteEntity existingFavoriteEntity = favoriteRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Favorite " + id + " not found"));
        return existingFavoriteEntity;
    }

    @Transactional(readOnly = true)
    public long countFavoritesByBeach(Long beachId) {
        beachRepository.findById(beachId)
                .orElseThrow(() -> new RecordNotFoundException("Beach " + beachId + " not found"));
        return favoriteRepository.countFavoritesByBeach_BeachId(beachId);
    }

    @Transactional(readOnly = true)
    public List<FavoriteResponseDTO> findFavoritesByUser(String keycloakId) {
        UserEntity user = userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new RecordNotFoundException("User " + keycloakId + " not found"));
        return favoriteDTOMapper.mapToDto(user.getFavorites());
    }

    @Transactional
    public FavoriteResponseDTO addFavorite(String keycloakId, FavoriteRequestDTO favoriteModel) {

        FavoriteEntity favoriteEntity = favoriteDTOMapper.mapToEntity(favoriteModel);

        BeachEntity beach = beachRepository.findById(favoriteModel.getBeachId())
                .orElseThrow(() -> new RecordNotFoundException("Beach " + favoriteModel.getBeachId() + " not found"));
        favoriteEntity.setBeach(beach);

        UserEntity user = userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new RecordNotFoundException("User " + keycloakId  + " not found"));
        favoriteEntity.setUser(user);
        favoriteEntity = favoriteRepository.save(favoriteEntity);
        return favoriteDTOMapper.mapToDto(favoriteEntity);
    }

       @Transactional
    public void deleteFavorite(String keycloakId, Long favoriteId) {
        FavoriteEntity favorite = getFavoriteEntity(favoriteId);
        if (!favorite.getUser().getKeycloakId().equals(keycloakId)) {
            throw new AccessDeniedException("You can only delete your own favorites");
        }
        favoriteRepository.delete(favorite);
    }
}
