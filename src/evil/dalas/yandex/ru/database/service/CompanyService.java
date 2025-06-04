package evil.dalas.yandex.ru.database.service;

import evil.dalas.yandex.ru.database.entity.Company;
import evil.dalas.yandex.ru.database.repository.CrudRepository;
import evil.dalas.yandex.ru.dto.CompanyReadDto;
import evil.dalas.yandex.ru.listener.entity.AccessType;
import evil.dalas.yandex.ru.listener.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final UserService userService;
    private final CrudRepository<Integer, Company> companyRepository;
    private final ApplicationEventPublisher eventPublisher;


    public Optional<CompanyReadDto> findById(Integer id){
        return companyRepository.findById(id)
                .map(entity-> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                  return new CompanyReadDto(entity.id());
                });
    }
}
