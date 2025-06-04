package evil.dalas.yandex.ru.database.service;

import evil.dalas.yandex.ru.database.repository.CrudRepository;
import evil.dalas.yandex.ru.database.repository.UserRepository;
import evil.dalas.yandex.ru.database.entity.Company;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

}
