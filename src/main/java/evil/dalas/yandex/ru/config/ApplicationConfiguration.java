package evil.dalas.yandex.ru.config;

import evil.dalas.yandex.ru.database.Pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
public class ApplicationConfiguration {

    @Bean("pool2")
    public ConnectionPool pool2(@Value("${spring.datasource.username}") String username) {
        return new ConnectionPool(username, 20);
    }

    @Bean("pool3")
    public ConnectionPool pool3() {
        return new ConnectionPool("test-pool", 20);
    }

}
