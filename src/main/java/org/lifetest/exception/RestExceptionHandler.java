package org.lifetest.exception;

import javax.servlet.http.HttpServletRequest;
import org.lifetest.bean.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



/**
 * @Description 錯誤處理器: 功能代號 CONNIT(當訊息等級為ERROR時，會傳IT訊息監控系統)
 * @ClassName: RestExceptionHandler
 * @author Ryan Hsu
 * @date 2018/05/31
 *
 */
@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * @Description 自定義錯誤
   * @author Ryan Hsu
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler(ErrorException.class)
  public final ResponseEntity<RestResult> handleCtrlExceptions(ErrorException ex,
      WebRequest request) {
    return new ResponseEntity<>(new RestResult(null, ex.getErrorMsg()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AccountExpiredException.class)
  public final ResponseEntity<RestResult> handleTimeoutExceptions(Exception ex) {
    return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public final ResponseEntity<RestResult> handleAccessDeniedException(Exception ex) {
    return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
  }

  /**
   * @Description 沒有抓取到的錯誤
   * @author Ryan Hsu
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<RestResult> handleAllExceptions(Exception ex,
      HttpServletRequest request) {
    return new ResponseEntity<>(new RestResult(null, ErrorCode.ERR_004.getErrorMsg()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
