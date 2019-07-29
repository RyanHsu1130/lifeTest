package org.lifetest.service;

import org.lifetest.bean.LoginModel;

public interface IAuthService {

  /**
   * @param loginModel
   * @return JWT Token
   */
  String login(LoginModel loginModel);

  /**
   * @param userId
   * @return 是否登出
   */
  Boolean logout(Long userId);

}
