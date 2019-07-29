package org.lifetest.bean;

/**
 * @Description 登入請求Body
 * @ClassName: RestResult
 * @author Ryan Hsu
 *
 */
public class LoginModel {

  private String userName;

  private String password;

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

  public boolean isValid() {
    if (userName == null || userName.trim().length() == 0 || password == null
        || password.trim().length() == 0)
      return false;
    return true;
  }

}
