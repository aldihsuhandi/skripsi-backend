package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.InterestLevelDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterestLevelRepository extends JpaRepository<InterestLevelDO, String> {
    Optional<InterestLevelDO> findByInterestLevelName(String interestLevelName);
}