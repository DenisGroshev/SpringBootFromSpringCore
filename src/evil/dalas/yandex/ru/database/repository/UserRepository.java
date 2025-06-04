package evil.dalas.yandex.ru.database.repository;

import evil.dalas.yandex.ru.database.Pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class UserRepository {
    @Qualifier("pool2")
    private final ConnectionPool connectionPool;
}

