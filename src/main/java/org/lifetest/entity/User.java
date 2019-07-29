package org.lifetest.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "PASSWORD")
  @JsonIgnore
  private String password;

  @Column(name = "IS_LOGIN")
  private Boolean isLogin;

  @Column(name = "ACCOUNT")
  private Double account;

  @Column(name = "CREATE_DATE")
  private Date createDate;

  @Column(name = "UPDATE_DATE")
  private Date updateDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getIsLogin() {
    return isLogin;
  }

  public void setIsLogin(Boolean isLogin) {
    this.isLogin = isLogin;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public Double getAccount() {
    return account;
  }

  public void setAccount(Double account) {
    this.account = account;
  }



}
