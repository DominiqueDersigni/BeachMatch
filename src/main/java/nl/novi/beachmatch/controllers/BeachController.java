package nl.novi.beachmatch.controllers;

import nl.novi.beachmatch.dtos.beach.BeachExtendedResponseDTO;
import nl.novi.beachmatch.dtos.beach.BeachRequestDTO;
import nl.novi.beachmatch.dtos.beach.BeachResponseDTO;
import nl.novi.beachmatch.helpers.UrlHelper;
import nl.novi.beachmatch.services.BeachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beaches")
public class BeachController {
    private final BeachService beachService;
    private final UrlHelper urlHelper;


    public BeachController(BeachService beachService, UrlHelper urlHelper) {
        this.beachService = beachService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<BeachResponseDTO>> findAllBeaches() {
        List<BeachResponseDTO> beaches = beachService.findAllBeaches();
        return new ResponseEntity<>(beaches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeachExtendedResponseDTO> getBeachById(@PathVariable Long id) {
        BeachExtendedResponseDTO beach = beachService.findBeachById(id);
        return new ResponseEntity<>(beach, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeachResponseDTO> createBeach(@RequestBody BeachRequestDTO beachRequestDTO) {
        ;
        BeachResponseDTO beachDTO = beachService.createBeach(beachRequestDTO);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(beachDTO.getId())).body(beachDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeachResponseDTO> updateBeach(@PathVariable Long id, @RequestBody BeachRequestDTO beachRequestDTO) {
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
}