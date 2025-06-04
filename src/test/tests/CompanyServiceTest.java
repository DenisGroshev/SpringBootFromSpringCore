package tests;

import evil.dalas.yandex.ru.database.entity.Company;
import evil.dalas.yandex.ru.database.repository.CrudRepository;
import evil.dalas.yandex.ru.database.service.CompanyService;
import evil.dalas.yandex.ru.dto.CompanyReadDto;
import evil.dalas.yandex.ru.listener.entity.EntityEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    private static final Integer COMPANY_ID = 1;

    @Mock
    private CrudRepository<Integer, Company> companyRepository;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById() {
        Company company = new Company(COMPANY_ID);

        when(companyRepository.findById(COMPANY_ID)).thenReturn(Optional.of(company));

        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());
        assertEquals(new CompanyReadDto(COMPANY_ID), actualResult.get());

        verify(eventPublisher).publishEvent(any(EntityEvent.class));
        verifyNoMoreInteractions(eventPublisher);
    }
}