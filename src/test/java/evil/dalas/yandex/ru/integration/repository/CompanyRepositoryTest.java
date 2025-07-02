package evil.dalas.yandex.ru.integration.repository;

import evil.dalas.yandex.ru.annotation.IT;
import evil.dalas.yandex.ru.database.entity.Company;
import evil.dalas.yandex.ru.database.repository.CompanyRepository;
import evil.dalas.yandex.ru.integration.IntegrationTestBase;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RequiredArgsConstructor
@IT
public class CompanyRepositoryTest extends IntegrationTestBase {

    private static final Integer APPLE_ID=5;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EntityManager entityManager; // не final, с @Autowired
    @Autowired
    private TransactionTemplate transactionTemplate;


    @Test
    void checkFindByQueries(){
        companyRepository.findByName("google");
        companyRepository.findAllByNameContainingIgnoreCase("a");
    }

    @Test
    void delete(){
        var maybeCompany=companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);

    }

    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(tx -> {

            var company = entityManager.find(Company.class, 1L);
            assertNotNull(company);
            assertThat(company.getLocales()).hasSize(2);

        });
    }

    @Test
    void save() {
        var company = Company.builder()
                .name("Sberbank")
                .locales(Map.of(
                        "ru", "Sberbank описание",
                        "en", "Sberbank description"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}
