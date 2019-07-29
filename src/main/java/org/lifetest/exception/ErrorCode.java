package org.lifetest.exception;

public enum ErrorCode {

  ERR_001("輸入參數格式不符"),
  ERR_002("帳號密碼輸入錯誤"),
  ERR_003("使用者不存在或使用者已登入"),
  ERR_004("系統發生未知錯誤"),
  ERR_005("JWT Token錯誤"),
  ERR_006("使用者不存在"),
  ERR_007("餘額不足"),
  ERR_008("使用已登出，請重新登入");

  private String errorMsg;

  private ErrorCode(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public String getErrorMsg() {
    return this.errorMsg;
  }

}
