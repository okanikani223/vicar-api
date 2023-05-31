package com.github.okanikani223.vicar.configure;

import jakarta.jms.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import java.util.Objects;

@Configuration
public class VicarConfig {

    @Bean
    JmsListenerContainerFactory<?> vicarMessagingServiceFactory(ConnectionFactory connectionFactory,
                                                                DefaultJmsListenerContainerFactoryConfigurer configurer) {
        var factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    @Scope("prototype")
    Logger logger(InjectionPoint ip) {
        return LoggerFactory.getLogger(getClazz(ip));
    }

    private Class<?> getClazz(InjectionPoint ip) {
        var methodParameter = ip.getMethodParameter();
        var field = ip.getField();

        if (Objects.isNull(methodParameter) && Objects.isNull(field)) throw new BeanCreationException("Cannot find type for Logger");

        return Objects.isNull(methodParameter) ? field.getDeclaringClass() : methodParameter.getDeclaringClass();
    }
}
