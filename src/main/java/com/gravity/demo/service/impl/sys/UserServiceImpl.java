package com.gravity.demo.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gravity.demo.common.ResultResponse;
import com.gravity.demo.common.exception.BusinessException;
import com.gravity.demo.entity.sys.User;
import com.gravity.demo.mapper.sys.UserMapper;
import com.gravity.demo.service.sys.UserRoleService;
import com.gravity.demo.service.sys.UserService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<Integer> getRoleIds(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("role_id").eq("uid", userId);
        List<Integer> roleIds = userRoleService.listObjs(queryWrapper);
        return roleIds;
    }

    @Override
    public ResultResponse login(String username, String password, String ipAdress) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(token);
        return ResultResponse.success(token);
    }

    @Override
    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        User user = query().eq("login_name", username).one();
        oldPassword = Md5Crypt.apr1Crypt(oldPassword,username);
        if (!user.getPassword().equals(oldPassword)) {
            throw new BusinessException(1, "原密码不正确");
        }
        user.setPassword(Md5Crypt.apr1Crypt(newPassword, user.getLoginName()));
        return updateById(user);
    }

    @Override
    public ResultResponse<User> info(String username) {
        return ResultResponse.success(query().eq("login_name", username).one());
    }


}
