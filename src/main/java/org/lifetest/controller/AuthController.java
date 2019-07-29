package org.lifetest.controller;

import org.lifetest.bean.BaseUserDetail;
import org.lifetest.bean.LoginModel;
import org.lifetest.bean.RestResult;
import org.lifetest.exception.ErrorCode;
import org.lifetest.exception.ErrorException;
import org.lifetest.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequestMapping("/auth")
public class AuthController extends BaseController {

  @Autowired
  IAuthService authService;

  @PostMapping("login")
  public RestResult login(@RequestBody LoginModel loginModel) {
    if (!loginModel.isValid())
      throw new ErrorException(ErrorCode.ERR_001);
    return buildResult(authService.login(loginModel));
  }

  @GetMapping("logout")
  public RestResult logout() {
    BaseUserDetail userDetail = getUserDetail();
    return buildResult(authService.logout(userDetail.getUserId()));
  }

}
