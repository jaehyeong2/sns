package jjfactory.sns.global.auth;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("spring.jwt.secret")
    private String secretKey;

    private long TOKEN_VALID_TIME = 1000L * 60 * 30; // 30분
    private long REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 7; // 7일

    private final RedisTemplate redisTemplate;
    private final PrincipalDetailsService principalDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // jwt 토큰 생성
    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        String compact = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return compact;
    }

    // jwt refresh 토큰 생성
    public String createRefreshToken() {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // jwt 토큰으로 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = principalDetailsService.loadUserByUsername(this.getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // jwt 토큰에서 회원 구별 정보 추출
    public String getUsername(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch(ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    //  header에서 token 얻음
    public String resolveToken(HttpServletRequest req){
        return req.getHeader(HttpHeaders.AUTHORIZATION);
    }

    public Duration getRemainingSeconds(String jwtToken) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
        long seconds = (claims.getBody().getExpiration().getTime() - claims.getBody().getIssuedAt().getTime()) / 1000;
        return Duration.ofSeconds(seconds < 0 ? 0 : seconds);
    }

    // jwt 토큰의 유효성 + 로그아웃 확인 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            if(isLogOut(jwtToken)) return false;
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // jwt 토큰의 유효성 + 로그아웃 확인 + 만료일자만 초과한 토큰이면 return true;
    public boolean validateTokenExpiration(String jwtToken) {
        try {
            if(isLogOut(jwtToken)) return false;
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration().before(new Date());
        } catch(ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogOut(String jwtToken) {
        return redisTemplate.opsForValue().get(CacheKey.TOKEN + ":" + jwtToken) != null;
    }

}
