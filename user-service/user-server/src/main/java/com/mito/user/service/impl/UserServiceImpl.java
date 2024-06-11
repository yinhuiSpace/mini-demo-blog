package com.mito.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.mito.common.enums.RestResultEnum;
import com.mito.common.utils.BeanCopyUtil;
import com.mito.user.constants.TypeConstants;
import com.mito.user.exceptions.UserException;
import com.mito.user.pojo.dto.UserRegister;
import com.mito.user.pojo.po.User;
import com.mito.user.mapper.UserMapper;
import com.mito.user.pojo.vo.UserInfoVo;
import com.mito.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author root
 * @since 2024-06-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public UserInfoVo userInfo(long id) {

        User user = getById(id);

        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(user, UserInfoVo.class);

        return userInfoVo;
    }

    @Override
    public void updateUserInfo(User user) {

//        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
//        wrapper.
//        update()
        updateById(user);
    }

    @Override
    public void register(UserRegister userRegister) {

        //1.参数校验
        checkRegisterArgs(userRegister);
        //密码加密
        userRegister.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        //2.插入数据库
        User user = BeanCopyUtil.copyBean(userRegister, User.class);
        user.setId(IdUtil.getSnowflake().nextId())
                .setType(TypeConstants.TYPE_USER)
                .setNickname(userRegister.getUsername());
        save(user);
    }

    private void checkRegisterArgs(UserRegister userRegister) {

        if (isExists(User::getUsername, userRegister.getUsername())) {
            throw new UserException(RestResultEnum.USERNAME_EXIST);
        }

        if (isExists(User::getEmail, userRegister.getEmail())) {
            throw new UserException(RestResultEnum.EMAIL_EXIST);
        }

        if (isExists(User::getPhone, userRegister.getPhone())) {
            throw new UserException(RestResultEnum.PHONE_NUMBER_EXIST);
        }

    }

    private boolean isExists(SFunction<User, ?> column, Object val) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(column, val);

        return count(wrapper) != 0;
    }
}
