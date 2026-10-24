package nl.novi.beachmatch.repositories;


import nl.novi.beachmatch.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByKeycloakId(String keycloakId);
}