package evil.dalas.yandex.ru.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class JpaCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            context.getClassLoader().loadClass("org.posgresql.Driver");
        } catch (ClassNotFoundException e) {
            return true;
        }
        return false;
    }
}
