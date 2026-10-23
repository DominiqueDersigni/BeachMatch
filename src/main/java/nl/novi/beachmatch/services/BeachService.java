package nl.novi.beachmatch.services;

import nl.novi.beachmatch.dtos.beach.BeachExtendedResponseDTO;
import nl.novi.beachmatch.dtos.beach.BeachRequestDTO;
import nl.novi.beachmatch.dtos.beach.BeachResponseDTO;
import nl.novi.beachmatch.entities.BeachEntity;
import nl.novi.beachmatch.exceptions.RecordNotFoundException;
import nl.novi.beachmatch.mappers.BeachDTOMapper;
import nl.novi.beachmatch.mappers.BeachExtendedDTOMapper;
import nl.novi.beachmatch.repositories.BeachRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BeachService {

    private final BeachRepository beachRepository;
    private final BeachDTOMapper beachDTOMapper;
    private final BeachExtendedDTOMapper beachExtendedDTOMapper;

    public BeachService(BeachRepository beachRepository, BeachDTOMapper beachDTOMapper, BeachExtendedDTOMapper beachExtendedDTOMapper) {
        this.beachRepository = beachRepository;
        this.beachDTOMapper = beachDTOMapper;
        this.beachExtendedDTOMapper = beachExtendedDTOMapper;
    }

    private BeachEntity getBeachEntity(Long id) {
        BeachEntity existingBeachEntity = beachRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Beach " + id + " not found"));
        return existingBeachEntity;
    }

    @Transactional(readOnly = true)
    public BeachExtendedResponseDTO findBeachById(Long id) {
        BeachEntity albumEntity = getBeachEntity(id);
        return beachExtendedDTOMapper.mapToDto(albumEntity);
    }

    @Transactional(readOnly = true)
    public List<BeachResponseDTO> findAllBeaches() {
        return beachDTOMapper.mapToDto(beachRepository.findAll());
    }

    @Transactional
    public BeachResponseDTO createBeach(BeachRequestDTO beachModel) {
        BeachEntity beachEntity = beachDTOMapper.mapToEntity(beachModel);
        beachEntity = beachRepository.save(beachEntity);
        return beachDTOMapper.mapToDto(beachEntity);
    }

    @Transactional
    public BeachResponseDTO updateBeach(Long id, BeachRequestDTO beachModel)  {
        BeachEntity existingBeachEntity = getBeachEntity(id);

        existingBeachEntity.setName(beachModel.getName());
        existingBeachEntity.setDescription(beachModel.getDescription());
        existingBeachEntity.setProvince(beachModel.getProvince());
        existingBeachEntity.setLatitude(beachModel.getLatitude());
        existingBeachEntity.setLongitude(beachModel.getLongitude());
        existingBeachEntity = beachRepository.save(existingBeachEntity);
        return beachDTOMapper.mapToDto(existingBeachEntity);
    }

    @Transactional
    public void deleteBeach(Long id) {
        BeachEntity beach = getBeachEntity(id);
        beachRepository.delete(beach);
    }
}