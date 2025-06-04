package evil.dalas.yandex.ru.config;

import evil.dalas.yandex.ru.config.condition.JpaCondition;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Conditional(JpaCondition.class)
@Configuration
public class JpaConfiguration {

    //    @Bean
//    @ConfigurationProperties(prefix = "db")
//    public DataBaseProperties dataBaseProperties(){
//        return new DataBaseProperties();

    @PostConstruct
    void init(){
        log.info("Jpa config is enabled ");
    }


}
