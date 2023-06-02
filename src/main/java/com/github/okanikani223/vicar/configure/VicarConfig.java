package com.github.okanikani223.vicar.configure;

import com.github.okanikani223.vicar.stereotype.Usecase;
import jakarta.jms.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan(basePackages = "com.github.okanikani223.vicar", includeFilters = {@ComponentScan.Filter(Component.class), @ComponentScan.Filter(Usecase.class)})
public class VicarConfig {
    @Value("${vicar.download.thread.pools}")
    private int downloadThreadPools;

    @Bean
    ExecutorService executorService() {
        return Executors.newFixedThreadPool(downloadThreadPools);
    }

    @Bean
    JmsListenerContainerFactory<?> vicarMessagingServiceFactory(ConnectionFactory connectionFactory,
                                                                DefaultJmsListenerContainerFactoryConfigurer configurer) {
        var factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    MessageConverter jacksonJmsMessageConverter() {
        var converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
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
