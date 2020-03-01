package com.zpw.community.Exception;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/12-21:42
 */
public enum CustomizeErrorCode {

  QUESTION_NOT_FOUND(2001,"没有找到相应问题，请稍后再试！"),
  USER_NOT_FOUND(2002,"没有找到该用户"),
  IS_404(404,"访问的页面没有找到,请访问存在的页面"),
  IS_500(500,"服务器挂了。。。"),
  NOT_LOGIN(2003,"当前操作需要登录，请登录在试"),
  NOT_FOUND_PARENT(2004,"未选中任何问题和评论进行回复"),
  SYSTEM_EXCEPTION(2005,"系统异常"),
  TYPE_ERROR(2006,"回复类型异常"),
  COMMENT_NOT_FOUND(2007,"没有找到相应评论，请稍后再试！")
  ;

  private Integer code;
  private String message;
  CustomizeErrorCode(Integer code,String message) {
    this.message = message;
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage(){
    return message;
  }
  public void setMessage(String message){
    this.message = message;
  }

}
