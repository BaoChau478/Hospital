package app.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {

    // Secret key cấu hình (bạn có thể đọc từ application.properties)
    private final String SECRET = "mySecretKeyForJwtToken1234567890"; // tối thiểu 32 ký tự
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 giờ

    // Trả về signing key
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Sinh JWT token từ email
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // set email làm subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
