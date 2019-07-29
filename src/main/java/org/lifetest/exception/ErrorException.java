package org.lifetest.exception;

public class ErrorException extends RuntimeException {


  private static final long serialVersionUID = 1L;

  ErrorCode errorCode;

  public ErrorException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return errorCode.getErrorMsg();
  }
}
