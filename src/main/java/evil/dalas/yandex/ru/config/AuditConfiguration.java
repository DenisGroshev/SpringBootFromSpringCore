package evil.dalas.yandex.ru.config;

import evil.dalas.yandex.ru.Application;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditConfiguration {

    @Bean
    public AuditorAware<String > auditorAware(){
        //Security context
        return () -> Optional.of("skrillex");
    }
}
