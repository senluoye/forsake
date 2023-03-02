package com.qks.commen.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class AuthHelper {
    public static String SECRET = "谢浚霖永远的神！";

    /**
     * 颁发合法 token
     */
    public static String sign() {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                // todo 添加载荷
                .sign(algorithm);
    }

    /**
    * 校验 token 是否合法
    */
    public static boolean verity(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
        }catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }
}
