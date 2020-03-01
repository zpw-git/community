package com.zpw.community.controller;

import com.zpw.community.Exception.CustomizeErrorCode;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/13-10:02
 */
@Controller
@RequestMapping("/error")
public class CustomizeErrorController implements ErrorController {
  @Override
  public String getErrorPath() {
    return "error";
  }
  @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
  public ModelAndView errorHtml(HttpServletRequest request, Model model) {
    HttpStatus status = this.getStatus(request);
    if(status.is4xxClientError()){
      model.addAttribute("message",CustomizeErrorCode.IS_404.getMessage());
    }else if(status.is5xxServerError()){
      model.addAttribute("message",CustomizeErrorCode.IS_500.getMessage());
    }
    return new ModelAndView("error");
  }

  private HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
    if (statusCode == null) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    } else {
      try {
        return HttpStatus.valueOf(statusCode);
      } catch (Exception var4) {
        return HttpStatus.INTERNAL_SERVER_ERROR;
      }
    }
  }


}
