package MIN.DosiNongBu.auth.jwt;

import MIN.DosiNongBu.auth.dto.JwtRequestDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


/*
* Jwt 토큰 발급 & 토큰 유효성 검증
* Access  Token & Refresh Token
* */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.token.secret}")
    private String SECRET_KEY;

    private static final int ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60; //1시간
    private static final int REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; //7일

    public Map<String, String> GenerateToken(JwtRequestDto jwtRequestDto){
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", generateAccessToken(jwtRequestDto));
        tokens.put("refreshToken", generateRefreshToken());
        return tokens;
    }

    public String RegenerateAccessToken(JwtRequestDto jwtRequestDto){
        return generateAccessToken(jwtRequestDto);
    }

    // Access 토큰 생성
    private String generateAccessToken(JwtRequestDto jwtRequestDto)   {
        long GENERATED_TIME = System.currentTimeMillis();

        // email, role
        return Jwts.builder()
                .claim("role", jwtRequestDto.getRole())
                .subject(jwtRequestDto.getEmail())
                .issuedAt(new Date(GENERATED_TIME))
                .expiration(new Date(GENERATED_TIME + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Refresh 토큰 생성
    private String generateRefreshToken()   {
        long GENERATED_TIME = System.currentTimeMillis();

        return Jwts.builder()
                .expiration(new Date(GENERATED_TIME + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 모든 Claim 추출하기
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    // username 추출 하기
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // role 추출 하기
    public String extractRole(String token){return extractClaim(token, claims -> claims.get("role", String.class));}

    public boolean isTokenValid(String token){
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
