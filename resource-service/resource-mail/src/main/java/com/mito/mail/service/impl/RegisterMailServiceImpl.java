package com.mito.mail.service.impl;

import com.mito.mail.pojo.dto.RegisterMailDTO;
import com.mito.mail.service.RegisterMailService;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterMailServiceImpl implements RegisterMailService {

    @Resource
    JavaMailSender javaMailSender;

    @Override
    public void mail(RegisterMailDTO mailDTO) {

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);

            messageHelper.setFrom("mito.studio@qq.com");
            messageHelper.setTo(mailDTO.getEmail());
            messageHelper.setText(mailDTO.getContent());
            messageHelper.setSubject("心流社区注册");
            messageHelper.setSentDate(new Date());

            javaMailSender.send(messageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
