package com.gravity.demo.common.shiro;

import com.gravity.demo.common.Response;
import com.gravity.demo.common.enums.StatusEnum;
import com.gravity.demo.common.exception.BusinessException;
import com.gravity.demo.entity.sys.User;
import com.gravity.demo.service.sys.ResourceService;
import com.gravity.demo.service.sys.UserService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author qijunlin
 * @date 2019-08-07 16:46
 */
@Component
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    @Resource
    private ResourceService resourceService;

    /**
     * 权限相关
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        System.out.println("-----------"+user.toString());
        Set<String> roleIds = userService.getRoleIds(user.getUid()).stream().map(String::valueOf).collect(Collectors.toSet());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleIds);
        List<String> userPerms = resourceService.getUserPerms(user.getUid());
        info.addStringPermissions(userPerms);
        return info;
    }

    /**
     * 身份认证(登录时调用)
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        User user = userService.query().eq("login_name", username).one();
        if (user == null) {
            throw new BusinessException("用户名不存在");
        }
        String ps = Md5Crypt.apr1Crypt(password, username);
        if (!ps.equals(user.getPassword())) {
            throw new BusinessException("密码错误");
        }
        if (!StatusEnum.NORMAL.equals(user.getStatus())) {
            throw new BusinessException("用户已锁定");
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
