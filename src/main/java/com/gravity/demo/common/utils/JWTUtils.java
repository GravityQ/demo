package com.gravity.demo.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author qijunlin
 * @date 2019-08-13 17:54
 */

public class JWTUtils {
    private static final String UID = "uid";
    private static final String SECRET = "uid";
    private static final long EXPIRE = 60 * 1000;

    /**
     * 生产token
     *
     * @param uid
     * @return
     */
    public static String generate(Integer uid) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + EXPIRE * 1000);
        Map<String, Object> claims = new HashMap(1);
        claims.put(UID, uid);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * 解析claim
     *
     * @return
     */
    public static Claims getClaim(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

    }

    public static Integer getUid(String token) {
        return getClaim(token).get(UID, Integer.class);
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpiration(String token) {
        return getClaim(token).getExpiration();
    }

    /**
     * 验证token是否失效
     *
     * @param token
     * @return true:过期   false:没过期
     */
    public static boolean isExpired(String token) {
        Date expiration = getExpiration(token);
        return expiration.before(new Date());
    }
}
