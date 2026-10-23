package nl.novi.beachmatch.mappers;

import nl.novi.beachmatch.dtos.favorite.FavoriteRequestDTO;
import nl.novi.beachmatch.dtos.favorite.FavoriteResponseDTO;
import nl.novi.beachmatch.entities.FavoriteEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FavoriteDTOMapper implements DTOMapper<FavoriteResponseDTO, FavoriteRequestDTO, FavoriteEntity> {

    @Override
    public FavoriteResponseDTO mapToDto(FavoriteEntity model) {

        var result = new FavoriteResponseDTO();
        result.setId(model.getFavoriteId());
        if(model.getBeach() != null){
            result.setBeachId(model.getBeach().getBeachId());
            result.setBeachName(model.getBeach().getName());
        }
        return result;
    }


    @Override
    public List<FavoriteResponseDTO> mapToDto(List<FavoriteEntity> models) {
        var result = new ArrayList<FavoriteResponseDTO>();
        for (FavoriteEntity model : models) {
            result.add(mapToDto(model));
        }
        return result;
    }


    @Override
    public FavoriteEntity mapToEntity(FavoriteRequestDTO favoriteModel) {
        var result = new FavoriteEntity();
        return result;
    }
}
