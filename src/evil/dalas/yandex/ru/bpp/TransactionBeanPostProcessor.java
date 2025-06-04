package evil.dalas.yandex.ru.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class TransactionBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Object> originalBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Transaction.class)) {
            originalBeans.put(beanName, bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object originalBean = originalBeans.get(beanName);
        if (originalBean != null) {
            Class<?>[] interfaces = ClassUtils.getAllInterfacesForClass(originalBean.getClass());
            return Proxy.newProxyInstance(
                    originalBean.getClass().getClassLoader(),
                    interfaces,
                    (proxy, method, args) -> {
                        System.out.println("Open transaction");
                        try {
                            return method.invoke(originalBean, args);
                        } finally {
                            System.out.println("Close transaction");
                        }
                    });
        }
        return bean;
    }
}