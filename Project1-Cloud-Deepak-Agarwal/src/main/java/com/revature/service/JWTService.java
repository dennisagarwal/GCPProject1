package com.revature.service;

import com.revature.model.User;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTService {

    private Key key;

    //Instance initialization block
    //This will run immediately after the cinstruction

    {
        byte[] secret = "my_secret_password_my_secret_password_my_secret_password_my_secret_password".getBytes();
            key = Keys.hmacShaKeyFor(secret); //create a key using
    }


    public String createJWT(User user){
        String jwt = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("user_id",  user.getId())
                .claim("user_role", user.getUserRole())
                .signWith(key)
                .compact();

    return jwt;
    }

    //validating a JWT with the key
    public Jws<Claims> parseJwt(String jwt){
        try{
            Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

            return token;

        }catch(JwtException e){
            e.printStackTrace();
            throw new UnauthorizedResponse("JWT was invalid");
        }
    }
}
