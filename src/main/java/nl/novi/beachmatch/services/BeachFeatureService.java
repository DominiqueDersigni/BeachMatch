package nl.novi.beachmatch.services;

import nl.novi.beachmatch.dtos.beachfeature.BeachFeatureRequestDTO;
import nl.novi.beachmatch.dtos.beachfeature.BeachFeatureResponseDTO;
import nl.novi.beachmatch.entities.BeachEntity;
import nl.novi.beachmatch.entities.BeachFeatureEntity;
import nl.novi.beachmatch.exceptions.RecordNotFoundException;
import nl.novi.beachmatch.mappers.BeachFeatureDTOMapper;
import nl.novi.beachmatch.repositories.BeachFeatureRepository;
import nl.novi.beachmatch.repositories.BeachRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BeachFeatureService {
    private final BeachFeatureRepository beachFeatureRepository;
    private final BeachRepository beachRepository;
    private final BeachFeatureDTOMapper beachFeatureDTOMapper;

    public BeachFeatureService(BeachFeatureRepository beachFeatureRepository, BeachRepository beachRepository, BeachFeatureDTOMapper beachFeatureDTOMapper) {
        this.beachFeatureRepository = beachFeatureRepository;
        this.beachRepository = beachRepository;
        this.beachFeatureDTOMapper = beachFeatureDTOMapper;
    }

    @Transactional
    private BeachFeatureEntity getBeachFeatureEntity(Long id) {
        BeachFeatureEntity existingBeachFeatureEntity = beachFeatureRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("BeachFeature " + id +" not found"));
        return existingBeachFeatureEntity;
    }

    @Transactional(readOnly = true)
    public BeachFeatureResponseDTO findBeachFeatureById(Long id)  {
        BeachFeatureEntity beachFeatureEntity = getBeachFeatureEntity(id);
        return beachFeatureDTOMapper.mapToDto(beachFeatureEntity);
    }

    @Transactional(readOnly = true)
    public List<BeachFeatureResponseDTO> findAllBeachFeatures() {
        return beachFeatureDTOMapper.mapToDto(beachFeatureRepository.findAll());
    }

    @Transactional
    public BeachFeatureResponseDTO createBeachFeature(BeachFeatureRequestDTO beachFeatureDTO) {
        BeachFeatureEntity beachFeatureEntity = beachFeatureDTOMapper.mapToEntity(beachFeatureDTO);
        beachFeatureEntity = beachFeatureRepository.save(beachFeatureEntity);
        return beachFeatureDTOMapper.mapToDto(beachFeatureEntity);
    }

    @Transactional
    public BeachFeatureResponseDTO updateBeachFeature(Long id, BeachFeatureRequestDTO requestDto)  {
        BeachFeatureEntity existingBeachFeatureEntity = getBeachFeatureEntity(id);

        existingBeachFeatureEntity.setName(requestDto.getName());
        existingBeachFeatureEntity.setDescription(requestDto.getDescription());

        existingBeachFeatureEntity = beachFeatureRepository.save(existingBeachFeatureEntity);
        return beachFeatureDTOMapper.mapToDto(existingBeachFeatureEntity);
    }



    @Transactional
    public void deleteBeachFeature(Long id) {
        BeachFeatureEntity beachFeature = getBeachFeatureEntity(id);
        for (BeachEntity beach : beachRepository.findByBeachFeatures_BeachFeatureId(id)) {
            beach.getBeachFeatures().remove(beachFeature);
            beachRepository.save(beach);
        }
        beachFeatureRepository.delete(beachFeature);
    }
}
