package com.mito.user.service;

import com.mito.user.pojo.dto.UserRegister;
import com.mito.user.pojo.dto.UserUpdateDTO;
import com.mito.user.pojo.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mito.user.pojo.vo.UserInfoListVo;
import com.mito.user.pojo.vo.UserInfoVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2024-06-04
 */
public interface UserService extends IService<User> {

    UserInfoVo userInfo(long id);

    void updateUserInfo(User user);

    void register(UserRegister userRegister);

    UserInfoListVo getByPage(Long pageNum, Long pageSize, String nickname);

    void add(UserRegister userRegister);

    void updateUser(UserUpdateDTO userUpdateDTO);

    void deleteById(Long id);
}
