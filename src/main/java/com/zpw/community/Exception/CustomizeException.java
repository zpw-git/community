package com.zpw.community.Exception;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/12-21:37
 */
public class CustomizeException extends RuntimeException {

  private Integer code;
  private String message;

  public CustomizeException(String message){
    this.message = message;
  }
  public CustomizeException(CustomizeErrorCode errorCode){
    this.message=errorCode.getMessage();
    this.code = errorCode.getCode();
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public void setMessage(){
    this.message = message;
  }
  public String getMessage(){
    return this.message;
  }
}
