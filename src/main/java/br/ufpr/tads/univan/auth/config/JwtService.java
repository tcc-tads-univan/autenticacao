package br.ufpr.tads.univan.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "462D4A614E645267556B58703273357638792F423F4428472B4B625065536856";

    public String extrairUsername(String token) {
        return extrairClaim(token, Claims::getSubject);
    }

    public String gerarToken(UserDetails userDetails) {
        return gerarToken(new HashMap<>(), userDetails);
    }

    public String gerarToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15)) // 15 min
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValido(String token, UserDetails userDetails) {
        final String username = extrairUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpirado(token));
    }

    private boolean isTokenExpirado(String token) {
        return extrairDataValidade(token).before(new Date());
    }

    private Date extrairDataValidade(String token) {
        return extrairClaim(token, Claims::getExpiration);
    }

    private <T> T extrairClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extrairClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extrairClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
