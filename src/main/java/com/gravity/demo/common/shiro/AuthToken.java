package com.gravity.demo.common.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义token
 *
 * @author qijunlin
 * @date 2020/9/19 3:09 下午
 */

public class AuthToken implements AuthenticationToken {
    private static final long serialVersionUID = 8819906397581059862L;

    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
