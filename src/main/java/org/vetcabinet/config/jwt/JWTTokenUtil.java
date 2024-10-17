package org.vetcabinet.config.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JWTTokenUtil {
    // 7 * 24 * 60 * 1000 = 1 week
    private static final long JWT_TOKEN_VALIDITY = 604800000;
    private final String secret = "zdrt223jsahdsab213masdjbaldsb";
    private static final ObjectMapper OBJECT_MAPPER = getDefaultObjectMapper();

    public String generateToken(final UserDetails payload) {
        return Jwts.builder()
                .setSubject(payload.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private static ObjectMapper getDefaultObjectMapper() {
        return new ObjectMapper();
    }

    public Boolean validateToken(final String token,
                                 final UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(final String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }


    public String getUsernameFromToken(final String token) {
        String userNameClaim = getClaimsFromToken(token, Claims::getSubject);
        log.info("userNameClaim: {}", userNameClaim);
        JsonNode userNameNode = null;
        try {
            userNameNode = OBJECT_MAPPER.readTree(userNameClaim);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        if (userNameNode != null) {
            return userNameNode.get("username").asText();
        } else return null;
    }


    private <T> T getClaimsFromToken(final String token,
                                     Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(final String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}