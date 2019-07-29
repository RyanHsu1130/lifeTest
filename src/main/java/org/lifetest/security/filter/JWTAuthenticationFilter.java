package org.lifetest.security.filter;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.lifetest.security.customize.JWTAuthenticationToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;



/**
 * @Description 驗證所有請求帶有 JWT 的filter 繼承 BasicAuthenticationFilter
 * @ClassName: JWTAuthenticationFilter
 * @author Ryan Hsu
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {


  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
      FilterChain chain) throws ServletException, IOException {
    // 從 Request Header 拿取 JWT TOKEN
    Optional<String> jwt = this.getJWT(httpRequest.getHeader(HttpHeaders.AUTHORIZATION));
    if (jwt.isPresent()) {
      // 這裡先建立一個沒有驗證過的 Authentication 物件，等等傳入AuthenticationProvider 認證
      Authentication authentication = new JWTAuthenticationToken(jwt.get());
      // 呼叫註冊在 AuthenticationManager 的 AuthenticationProvider 進行驗證
      try {
        authentication = super.getAuthenticationManager().authenticate(authentication);
        // 將認證完的 Authentication 放到 SecurityContext 中
        SecurityContextHolder.getContext().setAuthentication(authentication);
      } catch (AuthenticationException authExcep) {
        httpResponse.setStatus(HttpStatus.REQUEST_TIMEOUT.value());
        httpResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpResponse.getWriter()
            .println("{\"code\":408,\"message\":\"session time out;\",\"data\":\"\"}");
        httpResponse.getWriter().flush();
      }
    }
    chain.doFilter(httpRequest, httpResponse);
  }

  /**
   * @Description 拿取JWT Token
   * @author Ryan Hsu
   * @param header
   * @return
   */
  private Optional<String> getJWT(String header) {
    String jwt = null;
    if (header != null) {
      String[] authString = header.trim().split(" ");
      if (authString.length == 2 && authString[0].equals("Bearer")) {
        jwt = authString[1];
      }
    }
    return Optional.ofNullable(jwt);
  }

}
