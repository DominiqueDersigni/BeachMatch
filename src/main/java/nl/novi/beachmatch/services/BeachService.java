package nl.novi.beachmatch.services;

import nl.novi.beachmatch.mappers.BeachDTOMapper;
import nl.novi.beachmatch.mappers.BeachFeatureDTOMapper;
import nl.novi.beachmatch.repositories.BeachFeatureRepository;
import nl.novi.beachmatch.repositories.BeachRepository;
import nl.novi.beachmatch.repositories.ReviewRepository;
import nl.novi.beachmatch.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BeachService {
    private final BeachRepository beachRepository;
    private final BeachFeatureRepository beachFeatureRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BeachDTOMapper beachDTOMapper;

}
