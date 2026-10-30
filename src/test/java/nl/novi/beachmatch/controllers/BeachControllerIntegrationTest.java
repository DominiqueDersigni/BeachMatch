package nl.novi.beachmatch.controllers;

import nl.novi.beachmatch.entities.BeachEntity;
import nl.novi.beachmatch.entities.BeachFeatureEntity;
import nl.novi.beachmatch.repositories.BeachFeatureRepository;
import nl.novi.beachmatch.repositories.BeachRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)

@ActiveProfiles("test")
class BeachControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BeachRepository beachRepository;

    @Autowired
    BeachFeatureRepository beachFeatureRepository;

    Long beachId;

    @BeforeEach
    void setUp() {
        BeachFeatureEntity feature = new BeachFeatureEntity();
        feature.setName("Surf beach");
        feature.setDescription("Surfing, kitesurfing or windsurfing");
        BeachFeatureEntity savedFeature = beachFeatureRepository.save(feature);
        BeachEntity beach = new BeachEntity();
        beach.setName("Strand West");
        beach.setDescription("Beautiful quiet beach after small walk through the dunes. Unable to reach by car.");
        beach.setProvince("Noord-Holland");
        beach.setLongitude(4.6);
        beach.setLatitude(52.4);
        beach.getBeachFeatures().add(savedFeature);
        BeachEntity savedBeach = beachRepository.save(beach);
        beachId = savedBeach.getBeachId();
    }

    @Test
    @DisplayName("Get beach by id")
    void getBeach_shouldReturnBeach() throws Exception {
        // Act + Assert
        mockMvc.perform(get( "/beaches/" + beachId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Strand West"))
                .andExpect(jsonPath("$.beachFeatures[0].name").value("Surf beach"));
    }

//    String requestJson = """
//               "name: Strand West",
//               "description: Beautiful quiet beach after small walk through the dunes. Unable to reach by car.",
//               """;



}