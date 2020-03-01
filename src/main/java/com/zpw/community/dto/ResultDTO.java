package com.zpw.community.dto;

import com.zpw.community.Exception.CustomizeErrorCode;
import com.zpw.community.Exception.CustomizeException;
import java.util.List;
import lombok.Data;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/1-12:11
 */
@Data
public class ResultDTO {
  private Integer code;
  private String message;

  public static ResultDTO errorOf(Integer code,String message){
    ResultDTO resultDTO = new ResultDTO();
    resultDTO.setCode(code);
    resultDTO.setMessage(message);
    return resultDTO;
  }

  public static ResultDTO errorOf(CustomizeErrorCode errorCode){
    ResultDTO resultDTO = new ResultDTO();
    resultDTO.setCode(errorCode.getCode());
    resultDTO.setMessage(errorCode.getMessage());
    return resultDTO;
  }
  public static ResultDTO errorOf(CustomizeException e){
    ResultDTO resultDTO = new ResultDTO();
    resultDTO.setCode(e.getCode());
    resultDTO.setMessage(e.getMessage());
    return resultDTO;
  }

  public static ResultDTO okOf(){
    ResultDTO resultDTO = new ResultDTO();
    resultDTO.setCode(200);
    resultDTO.setMessage("评论成功");
    return resultDTO;
  }



}
