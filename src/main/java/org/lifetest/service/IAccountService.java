package org.lifetest.service;

public interface IAccountService {

  /**
   * @param userId
   * @param amount 存款金額
   * @return 餘額
   */
  Double deposit(Long userId, Double amount);

  /**
   * @param userId
   * @param amount 取款金額
   * @return 餘額
   */
  Double withdraw(Long userId, Double amount);
}
