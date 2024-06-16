package com.mito.user.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class AuthorInfoVo {

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
    * 是否已关注
    * */
    private boolean isFollow;

    /**
    * 创建时间
    * */
    private String createTime;



    public void setId(Long id) {
        this.id = id.toString();
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = DateTimeClient.toStr(createTime,DateTimeClient.SIMPLE_FORMAT);
    }
}
