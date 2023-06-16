package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.DictionaryDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DictionaryRepository extends JpaRepository<DictionaryDO, String> {
    List<DictionaryDO> findByDictionaryType(String dictionaryType);

    Optional<DictionaryDO> findByDictionaryName(String dictionaryName);
}
