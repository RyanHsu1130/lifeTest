package org.lifetest.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseUserDetail implements UserDetails {


  private static final long serialVersionUID = 5623227672206205593L;
  /**
   * @Fields userName: 名稱
   */
  private String userName;
  /**
   * @Fields userId: 編碼
   */
  private Long userId;


  public BaseUserDetail() {}

  /**
   * @Description 建構子
   * @author Ryan Hsu
   * @param userName
   * @param userId
   */
  public BaseUserDetail(String userName, Long userId) {
    this.userName = userName;
    this.userId = userId;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authority = new ArrayList<>();
    return authority;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return this.userName;
  }

  /**
   * @return userId
   */
  public Long getUserId() {
    return userId;
  }


  /**
   * @param userId set userId
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
