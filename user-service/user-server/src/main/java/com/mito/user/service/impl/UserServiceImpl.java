package com.mito.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mito.common.enums.RestResultEnum;
import com.mito.common.utils.BeanCopyUtil;
import com.mito.user.constants.RegisterConstants;
import com.mito.user.constants.TypeConstants;
import com.mito.user.exceptions.UserException;
import com.mito.user.mapper.UserMapper;
import com.mito.user.pojo.dto.UserRegister;
import com.mito.user.pojo.dto.UserUpdateDTO;
import com.mito.user.pojo.po.User;
import com.mito.user.pojo.vo.UserInfoListVo;
import com.mito.user.pojo.vo.UserInfoVo;
import com.mito.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Resource
    StringRedisTemplate stringRedisTemplate;

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

    @Override
    public UserInfoListVo getByPage(Long pageNum, Long pageSize, String nickname) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(nickname),User::getNickname,nickname);

        Page<User> page = page(Page.of(pageNum, pageSize), wrapper);
        List<UserInfoVo> userInfoVos = page.getRecords().stream().map(new Function<User, UserInfoVo>() {
            @Override
            public UserInfoVo apply(User user) {

                return BeanCopyUtil.copyBean(user, UserInfoVo.class);
            }
        }).collect(Collectors.toList());

        UserInfoListVo userInfoListVo = new UserInfoListVo();
        userInfoListVo.setRows(userInfoVos)
                .setTotal(page.getTotal());

        return userInfoListVo;
    }

    @Override
    public void add(UserRegister userRegister) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,userRegister.getUsername())
                .or()
                .eq(User::getNickname,userRegister.getNickname())
                .or()
                .eq(User::getEmail,userRegister.getEmail())
                .or()
                .eq(User::getPhone,userRegister.getPhone());

        List<User> list = list(wrapper);
        if (list.size()!=0){
            throw new UserException(RestResultEnum.ACCOUNT_EXISTS);
        }

        User user = BeanCopyUtil.copyBean(userRegister, User.class);
        user.setId(IdUtil.getSnowflake().nextId())
                        .setType(TypeConstants.TYPE_USER);

        save(user);
    }

    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO) {

        User user = BeanCopyUtil.copyBean(userUpdateDTO, User.class);
        user.setId(Long.parseLong(userUpdateDTO.getId()));

        updateById(user);

    }

    @Override
    public void deleteById(Long id) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId,id);

        remove(wrapper);
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

        String verifyCode = stringRedisTemplate.opsForValue().get(RegisterConstants.REGISTER_VERIFY_CODE + userRegister.getEmail());

        if (verifyCode==null){
            throw new UserException(RestResultEnum.VERIFY_CODE_EXPIRATION);
        }

        if (!verifyCode.equalsIgnoreCase(userRegister.getVerifyCode())){
            throw new UserException(RestResultEnum.VERIFY_CODE);
        }
    }

    private boolean isExists(SFunction<User, ?> column, Object val) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(column, val);

        return count(wrapper) != 0;
    }
}
