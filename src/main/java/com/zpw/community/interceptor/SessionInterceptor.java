package com.zpw.community.interceptor;

import com.zpw.community.model.User;
import com.zpw.community.sevice.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author: zhangpengwei
 * @create: 2020-02-2020/2/10-12:13
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

  @Autowired
  UserService userService;
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    Cookie[] cookies = request.getCookies();
    User userByToken = null;
    if(cookies!=null && cookies.length!=0){
      for(Cookie cookie: cookies){
        if(cookie.getName().equals("githubtoken")){
          String value = cookie.getValue();
          userByToken = userService.findUserByToken(value);
          break;
        }
      }
    }

    HttpSession session = request.getSession();
    if(userByToken!=null){
      session.setAttribute("user",userByToken);
    }else{
      session.setAttribute("user",null);
    }
    return true;
  }
}
