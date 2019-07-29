package org.lifetest.security.customize;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

  private static final long serialVersionUID = 1L;

  String jwtToken;
  
  private UserDetails principle;

  public JWTAuthenticationToken(String jwtToken) {
    super(null);
    super.setAuthenticated(false);
    this.jwtToken = jwtToken;
  }
  
  public JWTAuthenticationToken(UserDetails principle, String jwtToken) {
    super(principle.getAuthorities());
    this.principle = principle;
    this.jwtToken = jwtToken;
  }

  @Override
  public String getCredentials() {
    return jwtToken;
  }

  @Override
  public UserDetails getPrincipal() {
    return principle;
  }

}
