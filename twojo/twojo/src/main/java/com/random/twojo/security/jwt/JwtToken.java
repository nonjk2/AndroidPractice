package com.random.twojo.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtToken {
    static String key ="eunseokyooseopjungheynjaeyoonimtae";


    public static String createToken(Integer idx){


        Map<String, Object> headers = new HashMap<>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        Map<String, Object> payloads = new HashMap<>();
        Long expiredTime = 1800000L;//30*60*1000L; 30분
        Date now = new Date();
        now.setTime(now.getTime()+expiredTime);
        payloads.put("exp",now);
        payloads.put("data",idx);

        String jwt = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .signWith(SignatureAlgorithm.HS256,key.getBytes())
                .compact();






        return jwt;

    }
    public static int getTokenFromJwtSting(String jwtToken) throws IllegalArgumentException{
        Claims claims = Jwts.parser()
                .setSigningKey(key.getBytes())
                .parseClaimsJws(jwtToken)
                .getBody();

        Integer data = claims.get("data",Integer.class);
        return data;

    }

}
