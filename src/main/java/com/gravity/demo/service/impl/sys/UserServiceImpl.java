package com.gravity.demo.service.impl.sys;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gravity.demo.common.ResultResponse;
import com.gravity.demo.common.exception.BusinessException;
import com.gravity.demo.common.utils.RedisUtil;
import com.gravity.demo.common.constants.RedisConst;
import com.gravity.demo.entity.sys.User;
import com.gravity.demo.mapper.sys.UserMapper;
import com.gravity.demo.service.sys.UserRoleService;
import com.gravity.demo.service.sys.UserService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;


/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author gravity
 * @since 2019-08-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public List<Integer> getRoleIds(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("role_id").eq("uid", userId);
        List<Integer> roleIds = userRoleService.listObjs(queryWrapper);
        return roleIds;
    }

    @Override
    public ResultResponse login(String username, String password, String ipAdress) {
        User user = this.baseMapper.selectOne(new QueryWrapper<User>().eq("user_name", username));
        if (user == null) {
            return ResultResponse.error("用户不存在");
        }
        if (!user.getPassword().equals(SecureUtil.md5(password))) {
            String errorKey = RedisConst.LOGIN_ERROR + username;
            redisUtil.incr(errorKey, 5 * 60, 1);
            int errorTimes = Integer.parseInt(stringRedisTemplate.opsForValue().get(errorKey));
            if (errorTimes > RedisConst.LOGIN_ERROR_TIMES) {
                return ResultResponse.error("登录错误次数过多,账号锁定5分钟");
            }
            return ResultResponse.error("密码不正确");
        }
        String token = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        //有效时间两小时
        redisUtil.set(RedisConst.LOGIN_PRE + username, user, 2 * 60 * 60);
        return ResultResponse.success(token);
    }

    @Override
    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        User user = query().eq("login_name", username).one();
        oldPassword = Md5Crypt.apr1Crypt(oldPassword, username);
        if (!user.getPassword().equals(oldPassword)) {
            throw new BusinessException("原密码不正确");
        }
        user.setPassword(Md5Crypt.apr1Crypt(newPassword, user.getLoginName()));
        return updateById(user);
    }

    @Override
    public ResultResponse<User> info(String username) {
        return ResultResponse.success(query().eq("login_name", username).one());
    }


}
