package nl.novi.beachmatch.services;

import nl.novi.beachmatch.entities.BeachPhotoEntity;
import nl.novi.beachmatch.exceptions.RecordNotFoundException;
import nl.novi.beachmatch.repositories.BeachPhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BeachPhotoService {

    private final BeachPhotoRepository beachPhotoRepository;

    public BeachPhotoService(BeachPhotoRepository beachPhotoRepository) {
        this.beachPhotoRepository = beachPhotoRepository;
    }

    public BeachPhotoEntity storeFile(MultipartFile file) throws IOException {

        String originalFileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] bytes = file.getBytes();

        BeachPhotoEntity  beachPhoto = new BeachPhotoEntity (originalFileName, contentType, bytes);

        return beachPhotoRepository.save(beachPhoto);
    }

    public BeachPhotoEntity  getBeachPhotoById(Long id){
        return beachPhotoRepository.findById(id).orElseThrow( () -> new RecordNotFoundException("BeachPhoto with id " + id + " not found"));
    }
}
