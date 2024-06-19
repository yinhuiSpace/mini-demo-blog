package com.mito.mail.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RegisterMailDTO {

    //邮箱
    private String email;

    //收件人
    private String acceptor;

    //发送人
    private String sender;

    //内容
    private String content;



}
