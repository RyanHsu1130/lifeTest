package org.lifetest.bean;

/**
 * @Description Restful 回傳物件
 * @ClassName: RestResult
 * @author Ryan Hsu
 *
 */
public class RestResult {

  private String errorMsg;
  private Object restData;


  public RestResult() {}


  public RestResult(Object restData, String errorMsg) {
    this.restData = restData;
    this.errorMsg = errorMsg;
  }


  public String getErrorMsg() {
    return errorMsg;
  }


  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }


  public Object getRestData() {
    return restData;
  }


  public void setRestData(Object restData) {
    this.restData = restData;
  }



}
