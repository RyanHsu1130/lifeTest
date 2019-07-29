package org.lifetest.service;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;
import org.lifetest.bean.BaseUserDetail;
import org.lifetest.bean.LoginModel;
import org.lifetest.entity.User;
import org.lifetest.exception.ErrorCode;
import org.lifetest.exception.ErrorException;
import org.lifetest.repo.IUserRepo;
import org.lifetest.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements IAuthService {

  private static Logger logger = Logger.getLogger(AuthServiceImpl.class.getName());

  PasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();

  @Autowired
  IUserRepo userRepo;

  @Autowired
  JwtTokenUtil jwtTokenUtil;

  @Override
  @Transactional
  public String login(LoginModel loginModel) {
    if (!loginModel.isValid())
      throw new ErrorException(ErrorCode.ERR_001);
    User user = userRepo.getUserByUserName(loginModel.getUserName());
    if (user == null || user.getIsLogin())
      throw new ErrorException(ErrorCode.ERR_003);
    if (!PasswordEncoder.matches(loginModel.getPassword(), user.getPassword())) {
      logger.info("帳號密碼錯誤。");
      throw new ErrorException(ErrorCode.ERR_002);
    }

    user.setIsLogin(true);
    user.setUpdateDate(new Date());
    logger.info("登入成功，拿取JWT TOKEN。");
    return "Bearer "
        + jwtTokenUtil.generateToken(new BaseUserDetail(user.getUserName(), user.getId()));
  }

  @Override
  @Transactional
  public Boolean logout(Long userId) {
    Optional<User> userOption = userRepo.findById(userId);
    if (!userOption.isPresent())
      throw new ErrorException(ErrorCode.ERR_006);
    userOption.get().setIsLogin(false);
    logger.info("登出成功。");
    return true;
  }

}
