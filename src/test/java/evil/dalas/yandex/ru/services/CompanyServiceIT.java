package evil.dalas.yandex.ru.services;


import evil.dalas.yandex.ru.annotation.IT;
import evil.dalas.yandex.ru.config.DataBaseProperties;
import evil.dalas.yandex.ru.database.entity.Company;
import evil.dalas.yandex.ru.database.service.CompanyService;
import evil.dalas.yandex.ru.dto.CompanyReadDto;
import evil.dalas.yandex.ru.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class CompanyServiceIT {


    private static final Integer COMPANY_ID = 1;

    @Autowired
    private CompanyService companyService;

    @Test
    void findById() {
        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }
}
