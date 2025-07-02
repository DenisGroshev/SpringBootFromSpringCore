package evil.dalas.yandex.ru.integration.repository;

import evil.dalas.yandex.ru.annotation.IT;
import evil.dalas.yandex.ru.database.repository.UserRepository;
import evil.dalas.yandex.ru.dto.PersonalInfo;
import evil.dalas.yandex.ru.dto.PersonalInfo2;
import evil.dalas.yandex.ru.dto.UserFilter;
import evil.dalas.yandex.ru.entity.Role;
import evil.dalas.yandex.ru.entity.User;
import evil.dalas.yandex.ru.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest extends IntegrationTestBase {
    @Autowired
    private UserRepository userRepository;


    @Test
    void checkBatch(){
        var users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
    }

    @Test
    void checkJdbcTemplate(){
        var users = userRepository.findAllByCompanyIdAndRole(2, Role.USER);
        assertThat(users).hasSize(1);
    }

    @Test
    void checkAuditing(){
        var ivan = userRepository.findById(1L).get();
        ivan.setBirthDate(ivan.getBirthDate().plusYears(1L));
        userRepository.flush();
        System.out.println();
    }


    @Test
    void checkCustomImpl(){
        UserFilter filter=new UserFilter(
                null, "ov", LocalDate.now()
        );
        var users = userRepository.findAllByFilter(filter);
        assertThat(users).hasSize(4);
        System.out.println();
    }

    @Test
    void checkProjections(){
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(1);
        System.out.println();
    }


    @Test
    void checkPageable(){
        var pageable = PageRequest.of(1, 2, Sort.by("id"));
        var slice = userRepository.findAllBy(pageable);
        slice.forEach(user -> System.out.println(user.getCompany().getName()));

        while (slice.hasNext()) {
           slice = userRepository.findAllBy(slice.nextPageable());
           slice.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkSort(){
       var sortBy=Sort.sort(User.class);
       var sort = sortBy.by(User::getFirstname)
               .and(sortBy.by(User::getLastname));
       var sortById=Sort.by("firstname").and(Sort.by("lastname"));
       var allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort);
       assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFirstTop(){

        var topUser=userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkQueries(){
        var users = userRepository.findAllBy("%a%", "%ov%");
        System.out.println(users);
    }

    @Test
    void checkUpdate(){
        var ivan = userRepository.getById(1L);
        assertSame(Role.ADMIN, ivan.getRole());
        ivan.setBirthDate(LocalDate.now());

        var resultCount = userRepository.updateRole(Role.USER, 1L, 5L);


    }
}