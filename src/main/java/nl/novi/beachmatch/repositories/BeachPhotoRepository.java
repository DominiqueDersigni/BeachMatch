package nl.novi.beachmatch.repositories;


import nl.novi.beachmatch.entities.BeachPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeachPhotoRepository extends JpaRepository<BeachPhotoEntity, Long> {
    }