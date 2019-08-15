package com.gravity.demo.common.shiro;

import com.gravity.demo.common.utils.JWTUtils;
import com.gravity.demo.service.sys.ResourceService;
import com.gravity.demo.service.sys.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
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
public class JWTRealm extends AuthorizingRealm {
    @Resource
   private UserService userService;
    @Resource
    private ResourceService resourceService;
    /**
     * 权限相关
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer userId = JWTUtils.getUid(principalCollection.toString());
//        Integer userId = (Integer) principalCollection.getPrimaryPrincipal();
        Set<String> roleIds = userService.getRoleIds(userId).stream().map(String::valueOf).collect(Collectors.toSet());
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addRoles(roleIds);
        List<String> userPerms = resourceService.getUserPerms(userId);
        info.addStringPermissions(userPerms);
        return info;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        if (StringUtils.isNotBlank(token)&&JWTUtils.isExpired(token)) {
            throw new ExpiredJwtException(null,JWTUtils.getClaim(token),"token已过期");
        }
        //未过期
//        if (!JWTUtils.isExpired(token)) {
            return new SimpleAuthenticationInfo(token,token,getName());
//        }
    }
}
