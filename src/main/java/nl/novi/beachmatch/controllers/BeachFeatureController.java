package nl.novi.beachmatch.controllers;

import jakarta.validation.Valid;
import nl.novi.beachmatch.dtos.beachfeature.BeachFeatureRequestDTO;
import nl.novi.beachmatch.dtos.beachfeature.BeachFeatureResponseDTO;
import nl.novi.beachmatch.helpers.UrlHelper;
import nl.novi.beachmatch.services.BeachFeatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beachfeatures")
public class  BeachFeatureController {

    private final BeachFeatureService beachFeatureService;
    private final UrlHelper urlHelper;


    public BeachFeatureController(BeachFeatureService beachFeatureService, UrlHelper urlHelper) {
        this.beachFeatureService = beachFeatureService;
        this.urlHelper = urlHelper;

    }

    @GetMapping
    public ResponseEntity<List<BeachFeatureResponseDTO>> getAllBeachFeatures() {
        List<BeachFeatureResponseDTO> beachFeatures = beachFeatureService.findAllBeachFeatures();
        return new ResponseEntity<>(beachFeatures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeachFeatureResponseDTO> getBeachFeatureById(@PathVariable Long id) {
        BeachFeatureResponseDTO beachFeature = beachFeatureService.findBeachFeatureById(id);
        return new ResponseEntity<>(beachFeature, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeachFeatureResponseDTO> createBeachFeature(@RequestBody @Valid BeachFeatureRequestDTO beachFeatureModel) {
        BeachFeatureResponseDTO newBeachFeature = beachFeatureService.createBeachFeature(beachFeatureModel);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newBeachFeature.getId())).body(newBeachFeature);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeachFeatureResponseDTO> updateBeachFeature(@PathVariable Long id, @RequestBody @Valid BeachFeatureRequestDTO beachFeatureModel) {
        BeachFeatureResponseDTO updatedBeachFeature = beachFeatureService.updateBeachFeature(id, beachFeatureModel);
        return new ResponseEntity<>(updatedBeachFeature, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeachFeature(@PathVariable Long id) {
        beachFeatureService.deleteBeachFeature(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}