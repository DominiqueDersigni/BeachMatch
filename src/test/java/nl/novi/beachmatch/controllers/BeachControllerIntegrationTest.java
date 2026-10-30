package nl.novi.beachmatch.controllers;

import nl.novi.beachmatch.entities.BeachEntity;
import nl.novi.beachmatch.entities.BeachFeatureEntity;
import nl.novi.beachmatch.repositories.BeachFeatureRepository;
import nl.novi.beachmatch.repositories.BeachRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    @DisplayName("GET beach by id")
    void getBeach_shouldReturnBeach() throws Exception {
        // Act + Assert
        mockMvc.perform(get( "/beaches/" + beachId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Strand West"))
                .andExpect(jsonPath("$.beachFeatures[0].name").value("Surf beach"));
    }

    @Test
    @DisplayName("POST beach created returns 201")
    void createBeach_returns201() throws Exception {
        // Arrange:
        String requestJson = """
            {
                "name": "Strand Zuid",
                "description": "A wide sandy beach with some fun beach clubs.",
                "province": "Zuid-Holland",
                "longitude": 4.4,
                "latitude": 52.2
            }
            """;
        long countBefore = beachRepository.count();

        // Act
        mockMvc.perform(post("/beaches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Strand Zuid"));

        //Assert
        assertEquals(countBefore+1, beachRepository.count());
    }

    @Test
    @DisplayName("POST beach returns 400")
    void createBeach_returns400_whenDescriptionTooLong() throws Exception {
        // Arrange:
        String requestJson = """
            {
                "name": "Strand Oost",
                "description": "A wide sandy beach with white soft sand and gentle waves, perfect for families and long walks. Behind the dunes you find a cozy beach club with fresh seafood. Lifeguards are present in summer and there is plenty of parking nearby. In the evening you can enjoy beautiful sunsets over the North Sea from the terrace.",
                "province": "Zeeland",
                "longitude": 3.5,
                "latitude": 51.5
            }
            """;

        // Act + Assert
        mockMvc.perform(post("/beaches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0]").value("Description cannot be longer than 255 characters"));
    }
}