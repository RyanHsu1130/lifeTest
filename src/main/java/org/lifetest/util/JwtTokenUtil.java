package org.lifetest.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.lifetest.bean.BaseUserDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {


  @Value("${token.secret}")
  private String secret;

  @Value("${token.expiration}")
  private Long expiration;


  /**
   * @description 產生jwt token
   * @auto Ryan Hsu
   * @param userDetails
   * @return jwt string
   */
  public String generateToken(BaseUserDetail userDetails) {
    Map<String, Object> claims = new HashMap<>(2);
    claims.put("sub", userDetails.getUsername());
    claims.put("id", userDetails.getUserId());
    claims.put("created", new Date());
    return generateToken(claims);
  }

  /**
   * @description 產生user details
   * @auto Ryan Hsu
   * @param token
   * @return userDetail
   */
  public UserDetails getUserDetailsFromToken(String token) {
    UserDetails userDetails = null;
    Claims claims = this.getClaimsFromToken(token);
    if (claims != null) {
      String userName = claims.getSubject();
      Long userId = claims.get("id", Long.class);
      userDetails = new BaseUserDetail(userName, userId);
    }
    return userDetails;

  }

  /**
   * @description refresh token
   * @auto Ryan Hsu
   * @param token
   * @return refresh token
   */
  public String refreshToken(String token) {
    String refreshedToken;
    try {
      Claims claims = getClaimsFromToken(token);
      claims.put("created", new Date());
      refreshedToken = generateToken(claims);
    } catch (Exception e) {
      refreshedToken = null;
    }
    return refreshedToken;
  }


  /**
   * @description isTokenExpired
   * @auto Ryan Hsu
   * @param token
   * @return
   */
  public Boolean isTokenExpired(String token) {
    try {
      Claims claims = getClaimsFromToken(token);
      Date expiration = claims.getExpiration();
      return expiration.before(new Date());
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * @description generateToken
   * @author Ryan Hsu
   * @param claims
   * @return token
   */
  private String generateToken(Map<String, Object> claims) {
    return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate())
        .signWith(SignatureAlgorithm.HS512, this.secret).compact();
  }

  /**
   * @description generateExpirationDate
   * @author Ryan Hsu
   * @return
   */
  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + this.expiration * 1000);
  }

  /**
   * @description getClaimsFromToken
   * @auto Ryan Hsu
   * @param token
   * @return claim
   */
  private Claims getClaimsFromToken(String token) {
    Claims claims;
    try {
      claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }

}
