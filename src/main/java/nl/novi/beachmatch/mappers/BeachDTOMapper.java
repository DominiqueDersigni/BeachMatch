package nl.novi.beachmatch.mappers;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

public class BeachDTOMapper {

    @Component
    @Primary
    public class BeachDTOMapper implements DTOMapper<BeachResponseDTO, AlbumRequestDTO, AlbumEntity> {

        private final PublisherDTOMapper publisherDtoMapper;
        private final GenreDTOMapper genreDTOMapper;

        public AlbumDTOMapper(PublisherDTOMapper publisherDtoMapper, GenreDTOMapper genreDTOMapper) {
            this.publisherDtoMapper = publisherDtoMapper;
            this.genreDTOMapper = genreDTOMapper;
        }
}
