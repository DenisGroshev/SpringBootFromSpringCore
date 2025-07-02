package evil.dalas.yandex.ru.database.service;

import evil.dalas.yandex.ru.database.entity.Company;
import evil.dalas.yandex.ru.database.repository.CompanyRepository;
import evil.dalas.yandex.ru.dto.CompanyReadDto;
import evil.dalas.yandex.ru.entity.AccessType;
import evil.dalas.yandex.ru.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;


    public Optional<CompanyReadDto> findById(Integer id){
        return companyRepository.findById(id)
                .map(entity-> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                  return new CompanyReadDto(entity.getId());
                });
    }
}
