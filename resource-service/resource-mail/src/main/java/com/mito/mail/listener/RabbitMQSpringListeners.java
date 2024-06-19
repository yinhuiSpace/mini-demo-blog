package com.mito.mail.listener;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.mito.mail.pojo.dto.RegisterMailDTO;
import com.mito.mail.service.RegisterMailService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitMQSpringListeners {


    @Resource
    RegisterMailService registerMailService;

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue(name = "register",durable = "true"),
                            exchange = @Exchange(name = "mail",type = ExchangeTypes.TOPIC),
                            key = {"verifyCode"}
                    )
            }
    )
    public void listenOnCode(Message message){

        String mailDTOJson = new String(message.getBody());

        RegisterMailDTO mailDTO = BeanUtil.toBean(JSONUtil.parseObj(mailDTOJson), RegisterMailDTO.class);

        registerMailService.mail(mailDTO);
    }
}
