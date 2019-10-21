package com.gravity.demo.service.sys;

import com.gravity.demo.common.Response;
import com.gravity.demo.entity.sys.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author gravity
 * @since 2019-08-08
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户id获取所有角色
     * @param userId
     * @return
     */
    List<Integer> getRoleIds(Integer userId);

    Response<String> login(String username, String password, String ipAdress);

    boolean updatePassword(String username, String oldPassword, String newPassword);

    Response<User> info(String username);
}
