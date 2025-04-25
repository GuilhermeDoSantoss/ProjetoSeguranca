package com.guilhermesantos.authdemo.security;

import com.guilhermesantos.authdemo.dto.LoginDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    public static final String EMISSOR = "Guilherme";
    public static final long EXPIRATION = 24*30*1000;
    public static final String SECRET_KEY = "0123456789012345678901234567890123456789";

    public static AuthToken encode(LoginDTO dadosLogin){
        try{
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            String jwtToken = Jwts.builder()
                    .subject(dadosLogin.login())
                    .issuer(EMISSOR)
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .signWith(key)
                    .compact();
            return new AuthToken(jwtToken);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public static Authentication decode(HttpServletRequest request){
        try {
            String token = request.getHeader("Authorization");
            if (token != null){
                token = token.replace("Bearer ", "");
                SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
                JwtParser parser = Jwts.parser().verifyWith(secretKey).build();
                Claims claims = (Claims)parser.parse(token).getPayload();

                String subject = claims.getSubject();
                String issuper = claims.getIssuer();
                Date   exp      = claims.getExpiration();

                if (issuper.equals(EMISSOR) && subject.length() > 0 && exp.after(new Date(System.currentTimeMillis()))){
                    Authentication auth = new UsernamePasswordAuthenticationToken("usuario", null, Collections.emptyList());
                    return auth;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
