package com.zpw.community.enums;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/14-20:35
 */
public enum  CommentTypeEnum {

  QUESTION(1),
  COMMENT(2);
  private Integer type;
  CommentTypeEnum(Integer type){
    this.type = type;
  }

  public static boolean isExit(Integer type) {
    for (CommentTypeEnum commentTypeEnum:CommentTypeEnum.values()){
      if(commentTypeEnum.getType()==type){
        return true;
      }
    }
    return  false;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }
}
