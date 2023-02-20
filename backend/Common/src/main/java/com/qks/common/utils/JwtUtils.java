package com.qks.common.utils;

import com.qks.common.model.dto.WxAuthDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.http.entity.EntityTemplate;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-08-05 18:58
 */
public class JwtUtils {
    private static final String KEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijke";
    private static final SecretKey SECRET_KEY = new SecretKeySpec(KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    public static String createToken(Map<String, Object> user) {
        // 过期时间为1天
        return Jwts.builder()
                .setClaims(user)
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String createToken(WxAuthDTO authDTO) {
        Map<String, Object> map = EntityUtils.entityToMap(authDTO);
        // 过期时间为1天
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .setIssuedAt(new Date(System.currentTimeMillis()))
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

    public static Claims parser(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
