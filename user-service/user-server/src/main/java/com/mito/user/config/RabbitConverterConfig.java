package com.mito.user.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置rabbit消息转换器
 * */
@Configuration
public class RabbitConverterConfig {

    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }

}