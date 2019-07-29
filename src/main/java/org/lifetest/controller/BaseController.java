package org.lifetest.controller;

import org.lifetest.bean.BaseUserDetail;
import org.lifetest.bean.RestResult;
import org.lifetest.exception.ErrorCode;
import org.lifetest.exception.ErrorException;
import org.springframework.security.core.context.SecurityContextHolder;


public abstract class BaseController {

  protected BaseUserDetail getUserDetail() {
    BaseUserDetail userProfile = null;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal != null && principal instanceof BaseUserDetail) {
      userProfile = (BaseUserDetail) principal;
    } else {
      throw new ErrorException(ErrorCode.ERR_005);
    }
    return userProfile;
  }
  
  
  protected RestResult buildResult(Object data) {
    return new RestResult(data, null);
  }
}
