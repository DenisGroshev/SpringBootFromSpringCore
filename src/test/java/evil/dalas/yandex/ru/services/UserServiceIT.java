package evil.dalas.yandex.ru.services;

import evil.dalas.yandex.ru.annotation.IT;
import evil.dalas.yandex.ru.database.Pool.ConnectionPool;
import evil.dalas.yandex.ru.database.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
@IT
@RequiredArgsConstructor
public class UserServiceIT {

    @SpyBean(name = "1")
    private final ConnectionPool pool1;

    @Autowired
    private final UserService userService;

}
