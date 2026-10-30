package nl.novi.beachmatch.controllers;

import jakarta.validation.Valid;
import nl.novi.beachmatch.dtos.beach.BeachExtendedResponseDTO;
import nl.novi.beachmatch.dtos.beach.BeachRequestDTO;
import nl.novi.beachmatch.dtos.beach.BeachResponseDTO;
import nl.novi.beachmatch.entities.BeachEntity;
import nl.novi.beachmatch.entities.BeachPhotoEntity;
import nl.novi.beachmatch.helpers.UrlHelper;
import nl.novi.beachmatch.services.BeachPhotoService;
import nl.novi.beachmatch.services.BeachService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/beaches")
public class BeachController {
    private final BeachService beachService;
    private final BeachPhotoService beachPhotoService;
    private final UrlHelper urlHelper;


    public BeachController(BeachService beachService, BeachPhotoService beachPhotoService, UrlHelper urlHelper) {
        this.beachService = beachService;
        this.beachPhotoService = beachPhotoService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<BeachResponseDTO>> getAllBeaches() {
        List<BeachResponseDTO> beaches = beachService.findAllBeaches();
        return new ResponseEntity<>(beaches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeachExtendedResponseDTO> getBeachById(@PathVariable Long id) {
        BeachExtendedResponseDTO beach = beachService.findBeachById(id);
        return new ResponseEntity<>(beach, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeachResponseDTO> createBeach(@Valid @RequestBody BeachRequestDTO beachRequestDTO) {
        BeachResponseDTO beachDTO = beachService.createBeach(beachRequestDTO);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(beachDTO.getId())).body(beachDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeachResponseDTO> updateBeach(@PathVariable Long id, @Valid @RequestBody BeachRequestDTO beachRequestDTO) {
        BeachResponseDTO beachDTO = beachService.updateBeach(id, beachRequestDTO);
        return new ResponseEntity<>(beachDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeach(@PathVariable Long id) {
        beachService.deleteBeach(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{beachId}/beachFeatures/{beachFeatureId}")
    public ResponseEntity<Void> linkBeachFeature(@PathVariable Long beachId, @PathVariable Long beachFeatureId) {
        beachService.linkBeachFeature(beachId, beachFeatureId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{beachId}/beachFeatures/{beachFeatureId}")
    public ResponseEntity<Void> unlinkBeachFeature(@PathVariable Long beachId, @PathVariable Long beachFeatureId) {
        beachService.unlinkBeachFeature(beachId, beachFeatureId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<BeachResponseDTO> addPhotoToBeach(@PathVariable("id") Long beachId,
                                                            @RequestBody MultipartFile file) throws IOException {
        BeachPhotoEntity beachPhoto = beachPhotoService.storeFile(file);
        BeachResponseDTO beachDTO = beachService.addBeachPhoto(beachId, beachPhoto);
        return ResponseEntity.created(urlHelper.getCurrentUrl()).body(beachDTO);
    }


    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getBeachPhoto(@PathVariable("id") Long beachId) {
        BeachPhotoEntity beachPhoto = beachService.getPhotoFromBeach(beachId);
        MediaType mediaType;
        try {
            mediaType = MediaType.parseMediaType(beachPhoto.getBeachPhotoContentType());
        } catch (InvalidMediaTypeException ignore) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + beachPhoto.getBeachPhotoFileName())
                .body(beachPhoto.getData());
    }
}