package evil.dalas.yandex.ru.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.List;
import java.util.Map;

    @ConfigurationProperties(prefix = "db")
    public record DataBaseProperties(  String username,
            String password,
            String driver,
            String url,
            String hosts,
            PoolProperties pool,
            List<PoolProperties> pools,
            Map<String, Object> properties
    ) {



    public record PoolProperties(
            Integer size,
            Integer timeout) {

    }
}
