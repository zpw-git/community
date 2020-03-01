package com.zpw.community.advice;

import com.alibaba.fastjson.JSON;
import com.zpw.community.Exception.CustomizeErrorCode;
import com.zpw.community.Exception.CustomizeException;
import com.zpw.community.dto.ResultDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/12-19:25
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

  @ExceptionHandler(Exception.class)
  ModelAndView handle(HttpServletRequest request,Throwable e, Model model, HttpServletResponse response){
    String contentType = request.getContentType();
    if("application/json".equals(contentType)){
     //返回json
      ResultDTO resultDTO;
      if(e instanceof CustomizeException){
        resultDTO = ResultDTO.errorOf((CustomizeException) e);
      }else {
        resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_EXCEPTION);
      }
      try {
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(resultDTO));
        writer.close();

      } catch (IOException ex) {
        ex.printStackTrace();
      }
      return  null;
    }else{
      //返回页面
      if(e instanceof CustomizeException){
        model.addAttribute("message",e.getMessage());
      }else {
        model.addAttribute("message","有点小问题");
      }
      return new ModelAndView("error");
    }

  }

}
