package evil.dalas.yandex.ru.database.repository;

import evil.dalas.yandex.ru.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Integer> {

//    Optional, Entity, Future
//    @Query(name = "Company.findByName")
@Query("select distinct c from Company c " +
        "left join fetch c.locales " +
        "where c.name = :name2")
Optional<Company> findByName(@Param("name2") String name);

//    Collection, Stream (batch, close)
    List<Company> findAllByNameContainingIgnoreCase(String fragment);

}
