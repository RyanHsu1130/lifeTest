package org.lifetest.service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;
import org.lifetest.entity.User;
import org.lifetest.exception.ErrorCode;
import org.lifetest.exception.ErrorException;
import org.lifetest.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements IAccountService {

  private static Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

  @Autowired
  IUserRepo userRepo;

  @Override
  @Transactional
  public Double deposit(Long userId, Double amount) {
    Optional<User> userOption = userRepo.findById(userId);
    if (!userOption.isPresent())
      throw new ErrorException(ErrorCode.ERR_006);
    User user = userOption.get();
    if (!user.getIsLogin())
      throw new ErrorException(ErrorCode.ERR_008);
    Double newAmount = user.getAccount().doubleValue() + amount;
    user.setAccount(newAmount);
    user.setUpdateDate(new Date());
    logger.info(MessageFormat.format("使用者 {0} 存款 {1} 成功。", user.getUserName(), amount));;
    return newAmount;
  }

  @Override
  @Transactional
  public Double withdraw(Long userId, Double amount) {
    Optional<User> userOption = userRepo.findById(userId);
    if (!userOption.isPresent())
      throw new ErrorException(ErrorCode.ERR_006);
    User user = userOption.get();
    if (!user.getIsLogin())
      throw new ErrorException(ErrorCode.ERR_008);
    Double newAmount = user.getAccount().doubleValue() - amount;
    if (newAmount.compareTo(0.0) < 0) {
      logger.info(MessageFormat.format("使用者 {0} 提款餘額不足。", user.getUserName()));
      throw new ErrorException(ErrorCode.ERR_007);
    }
    user.setAccount(newAmount);
    user.setUpdateDate(new Date());
    logger.info(MessageFormat.format("使用者 {0} 提款 {1} 成功。", user.getUserName(), amount));
    return newAmount;
  }

}
