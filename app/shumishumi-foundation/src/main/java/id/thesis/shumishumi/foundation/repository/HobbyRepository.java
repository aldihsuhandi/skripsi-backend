package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.HobbyDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HobbyRepository extends JpaRepository<HobbyDO, String> {
    Optional<HobbyDO> findByHobbyName(String hobbyName);
}