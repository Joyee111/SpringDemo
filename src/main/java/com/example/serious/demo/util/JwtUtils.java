package com.example.serious.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    public static String createJwt(String id , String subject , long tll){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Jwts.builder().setId(id)
                .setSubject(subject).setExpiration(now);
        return "";
    }
}
