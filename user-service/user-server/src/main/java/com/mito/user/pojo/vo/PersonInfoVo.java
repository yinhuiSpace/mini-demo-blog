package com.mito.user.pojo.vo;

import com.mito.common.utils.DateTimeClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PersonInfoVo {

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;


    /**
     * 性别：0=男1=女
     */
    private String gender;

    /**
     * 头像
     */
    private String avatar;


    /**
     * 粉丝数
     * */
    private Long fans;

    /**
    * 文章数
    * */
    private Long articles;

    /**
    * 关注数
    * */
    private Long followers;

    /**
    * 入驻时间
    * */
    private String createTime;


    public void setId(Long id) {
        this.id = id.toString();
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = DateTimeClient.toStr(createTime,DateTimeClient.SIMPLE_FORMAT);
    }
}
