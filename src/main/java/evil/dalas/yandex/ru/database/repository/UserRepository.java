package evil.dalas.yandex.ru.database.repository;


import evil.dalas.yandex.ru.dto.PersonalInfo2;
import evil.dalas.yandex.ru.entity.Role;
import evil.dalas.yandex.ru.entity.User;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>,
        FilterUserRepository,
        QuerydslPredicateExecutor<User> {

    @Query("select u from User u where u.firstname like ?1 and u.lastname like ?2")
    List<User> findAllBy(String firstname, String lastname);

    @Query(value = "select u.* from users u when u.username = :username",
            nativeQuery = true)
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "update User u set u.role = :role where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    Optional<User> findTopByOrderByIdDesc();

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);


    // Collection, Stream
//    Streamable, Slice, Page
    @EntityGraph(attributePaths = {"company", "company.locales"})
    @Query(value = "select u from User u",
            countQuery = "select count (distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);

//    List<PersonalInfo> findAllByCompanyId(Integer companyId);
//    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);
        @Query(value ="Select firstname, " +
                "lastname," +
                "birth_date " +
                "from users " +
                "WHERE company_id = :companyId",
        nativeQuery = true)
        List<PersonalInfo2>  findAllByCompanyId(Integer companyId);

}
