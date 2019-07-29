package org.lifetest.security.customize;

import org.lifetest.bean.BaseUserDetail;
import org.lifetest.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  JwtTokenUtil jwtTokenUtil;

  @Override
  public Authentication authenticate(Authentication authentication) {

    Authentication returnAuthenticationToken = null;
    if (authentication instanceof JWTAuthenticationToken) {
      JWTAuthenticationToken jwtAuthenticationToken = (JWTAuthenticationToken) authentication;
      String jwtToken = jwtAuthenticationToken.getCredentials();
      if (!jwtTokenUtil.isTokenExpired(jwtToken)) {
        BaseUserDetail userDetail = (BaseUserDetail) jwtTokenUtil.getUserDetailsFromToken(jwtToken);
        returnAuthenticationToken = new JWTAuthenticationToken(userDetail, jwtToken);
        // 設定為true 表示這個 Authentication 物件已驗證過
        returnAuthenticationToken.setAuthenticated(true);
      } else {
        throw new AccountExpiredException("JWTtoken time out");
      }
    }

    return returnAuthenticationToken;
  }

  /**
   * @Description 設定這個Provider要驗證哪個種類的Authentication物件
   * @author Ryan Hsu
   */
  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(JWTAuthenticationToken.class);
  }

}
