package nl.novi.beachmatch.repositories;


import nl.novi.beachmatch.entities.BeachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeachRepository extends JpaRepository<BeachEntity, Long> {

    List<BeachEntity> findByBeachFeatures_BeachFeatureId(Long beachFeatureId);

}