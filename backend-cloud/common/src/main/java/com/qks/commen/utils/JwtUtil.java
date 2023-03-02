package com.qks.commen.utils;

import com.qks.commen.exception.ServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-08-05 18:58
 */
public class JwtUtil {

    private static final String KEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijke";
    private static final SecretKey SECRET_KEY = new SecretKeySpec(KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    private static final long aDay = 24 * 60 * 60 * 1000;

    public static String createToken(Map<String, Object> object) {
        // 过期时间为30天
        return Jwts.builder()
                .setClaims(object)
                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 10 * aDay))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static Boolean verify(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException e) {
            return false;
        }

        return true;
    }

    public static Claims parser(String token) throws ServiceException {
        Claims body;
        try {
            body = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("凭证认证失败");
        }
        return body;
    }

    public static String createTokenByUserId(Long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        return createToken(map);
    }

    public static Long getUserId(String token) throws ServiceException {
        return Long.valueOf(parser(token).get("userId").toString());
    }
}
