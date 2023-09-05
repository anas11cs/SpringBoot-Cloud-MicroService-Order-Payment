package com.tkxel.securityserver.service.impl;

import com.tkxel.securityserver.entity.User;
import com.tkxel.securityserver.service.JwtGenerator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtGeneratorImpl implements JwtGenerator {
        @Override
        public Map<String, String> generateToken(User user) {
            String message="JWT Token Generated";
            String tokenStrength = "secretKEYsecretKEYsecretKEYsecretKEYsecretKEYsecretKEYsecretKEY";



            String jwtToken = Jwts
                    .builder()
                    .setSubject(user.getUsername())
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, tokenStrength)
                    .compact();

            Map<String, String> jwtTokenGen = new HashMap<>();
            jwtTokenGen.put("token", jwtToken);
            jwtTokenGen.put("message", message);
            return jwtTokenGen;
        }
}
