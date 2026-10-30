package nl.novi.beachmatch.services;

import nl.novi.beachmatch.dtos.favorite.FavoriteRequestDTO;
import nl.novi.beachmatch.dtos.favorite.FavoriteResponseDTO;
import nl.novi.beachmatch.entities.BeachEntity;
import nl.novi.beachmatch.entities.FavoriteEntity;
import nl.novi.beachmatch.entities.UserEntity;
import nl.novi.beachmatch.exceptions.AccessDeniedException;
import nl.novi.beachmatch.mappers.FavoriteDTOMapper;
import nl.novi.beachmatch.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceTest {

    @Mock
    FavoriteRepository favoriteRepository;

    @Mock
    FavoriteDTOMapper favoriteDTOMapper;

    @Mock
    BeachRepository beachRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    FavoriteService favoriteService;

    BeachEntity beach1;
    BeachEntity beach2;

    UserEntity user1;
    UserEntity user2;

    FavoriteEntity favorite1;
    FavoriteEntity favorite2;

    FavoriteRequestDTO favoriteRequest;
    FavoriteResponseDTO favoriteResponse1;

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

        favorite1 = new FavoriteEntity();
        favorite1.setFavoriteId(1L);
        favorite1.setBeach(beach1);
        favorite1.setUser(user1);

        favorite2 = new FavoriteEntity();
        favorite2.setFavoriteId(2L);
        favorite2.setBeach(beach2);
        favorite2.setUser(user2);

        favoriteResponse1 = new FavoriteResponseDTO();
        favoriteResponse1.setId(1L);
        favoriteResponse1.setBeachId(1L);
        favoriteResponse1.setBeachName("Strand Zuid");

        favoriteRequest = new FavoriteRequestDTO();
        favoriteRequest.setBeachId(1L);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Count of favorite by beach id")
        void countFavoritesByBeach_shouldReturnCount() {
        // Arrange
        when(beachRepository.findById(1L)).thenReturn(Optional.of(beach1));
        when(favoriteRepository.countFavoritesByBeach_BeachId(1L)).thenReturn(3L);

        //Act
        long result = favoriteService.countFavoritesByBeach(1L);
        //Assert
        assertEquals(3L, result);
    }

    @Test
    @DisplayName("Find favorite beaches of a user")
    void findFavoritesByUser_shouldReturnFavorites() {
        // Arrange
        user1.setFavorites(List.of(favorite1));
        when (userRepository.findByKeycloakId(user1.getKeycloakId())).thenReturn(Optional.of(user1));
        when(favoriteDTOMapper.mapToDto(user1.getFavorites())).thenReturn(List.of(favoriteResponse1));

        //Act
        List <FavoriteResponseDTO> result = favoriteService.findFavoritesByUser(user1.getKeycloakId());
        //Assert
        assertEquals(1, result.size());
        assertEquals("Strand Zuid", result.get(0).getBeachName());
    }

    @Test
    @DisplayName("Add favorite beach")
    void addFavorite_shouldSaveAndReturnFavorite() {
       //Arrange
        FavoriteEntity favorite3 = new FavoriteEntity();
        when(favoriteDTOMapper.mapToEntity(favoriteRequest)).thenReturn(favorite3);
        when(beachRepository.findById(1L)).thenReturn(Optional.of(beach1));
        when(userRepository.findByKeycloakId(user1.getKeycloakId()))
                .thenReturn(Optional.of(user1));
        when(favoriteRepository.save(favorite3)).thenReturn(favorite3);
        when(favoriteDTOMapper.mapToDto(favorite3)).thenReturn(favoriteResponse1);
        //Act
        FavoriteResponseDTO result =
                favoriteService.addFavorite(user1.getKeycloakId(), favoriteRequest);
        //Assert
        assertEquals(favoriteResponse1, result);
        assertEquals(beach1, favorite3.getBeach());
        assertEquals(user1, favorite3.getUser());
        verify(favoriteRepository).save(favorite3);
    }


    @Test
    @DisplayName("Favorite beach can be deleted by owner")
    void deleteFavorite_byOwner_shouldDeleteFavorite() {
        //Arrange
        when(favoriteRepository.findById(1L)).thenReturn(Optional.of(favorite1));
        //Act
        favoriteService.deleteFavorite(user1.getKeycloakId(), 1L);
        //Assert
        verify(favoriteRepository).delete(favorite1);
    }

    @Test
    @DisplayName("Other user cannot delete favorite")
    void deleteFavorite_byOtherUser_shouldThrowAccessDeniedException() {
        when(favoriteRepository.findById(2L)).thenReturn(Optional.of(favorite2));

        assertThrows(
                AccessDeniedException.class,
                () -> favoriteService.deleteFavorite(user1.getKeycloakId(), 2L)
        );

        verify(favoriteRepository, never()).delete(any(FavoriteEntity.class));
    }
}
