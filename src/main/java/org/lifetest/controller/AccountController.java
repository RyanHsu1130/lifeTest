package org.lifetest.controller;

import java.text.MessageFormat;
import org.lifetest.bean.BaseUserDetail;
import org.lifetest.bean.RestResult;
import org.lifetest.exception.ErrorCode;
import org.lifetest.exception.ErrorException;
import org.lifetest.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AccountController extends BaseController {

  @Autowired
  IAccountService accountService;

  @GetMapping("deposit")
  public RestResult deposit(@RequestParam(required = false) Double amount) {
    BaseUserDetail userDetail = getUserDetail();
    if (amount == null || amount.compareTo(0.0) < 0) {
      throw new ErrorException(ErrorCode.ERR_001);
    }
    Double remain = accountService.deposit(userDetail.getUserId(), amount);
    return buildResult(MessageFormat.format("已存款 {0}，尚有餘額 {1} ", amount, remain));
  }

  @GetMapping("withdraw")
  public RestResult withdraw(@RequestParam(required = false) Double amount) {
    if (amount == null || amount.compareTo(0.0) < 0) {
      throw new ErrorException(ErrorCode.ERR_001);
    }
    BaseUserDetail userDetail = getUserDetail();
    Double remain = accountService.withdraw(userDetail.getUserId(), amount);
    return buildResult(MessageFormat.format("已提款 {0}，尚有餘額 {1} ", amount, remain));
  }
}
