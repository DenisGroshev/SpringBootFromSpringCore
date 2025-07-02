package evil.dalas.yandex.ru.database.Pool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionPool {

    private final String username;
    private final int poolSize;

    public ConnectionPool(String username, int poolSize) {
        this.username = username;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        log.info("Init connection pool: username={}, poolSize={}", username, poolSize);
    }

    @PreDestroy
    private void destroy() {
        log.info("Clean connection pool");
    }
}
