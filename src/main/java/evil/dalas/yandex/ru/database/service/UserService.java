package evil.dalas.yandex.ru.database.service;

import evil.dalas.yandex.ru.database.repository.CompanyRepository;
import evil.dalas.yandex.ru.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
}
